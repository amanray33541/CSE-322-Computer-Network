package offlineone;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Date;

public class Server {
   // ServerSocket welcomeSocket = new ServerSocket(6666);

    private final int portNumber = 8000;
    private final int backLog = 80;
    private final String address = "127.0.0.1";

    private int bufferLength = 1024 * 32;

    private class ClientInfo {

        private final Socket clientSocket;
        private final String userName;

        public ClientInfo(Socket clientSocket, String userName) {
            this.clientSocket = clientSocket;
            this.userName = userName;
        }

        public Socket getClientSocket() {
            return clientSocket;
        }

        public String getUserName() {
            return userName;
        }

    }

    private ArrayList<ClientInfo> clients = new ArrayList<>();

    private Socket isLoggedIn(String user) {
        Socket socket = null;

        for (int i = 0; i < clients.size(); i++) {
            ClientInfo clientInfo = clients.get(i);
            if (clientInfo.getUserName().equals(user)) {
                return clientInfo.getClientSocket();
            }
        }

        return socket;
    }

    public Server() {

        try {
            System.out.println("Binding to port " + portNumber + ", please wait  ...");
            InetAddress ip = InetAddress.getByName(address);
            ServerSocket serverSocket = new ServerSocket(portNumber, backLog, ip);
            System.out.println(serverSocket + " is started at " + new Date() + "\n" + "waiting for a client ...");
            //bufferLength = serverSocket.getReceiveBufferSize();

            File dataDir = new File("Data");
            if (!dataDir.exists()) {
                dataDir.mkdir();
            }

            enrolFile.createNewFile();
            friendsFile.createNewFile();

            int clientNo = 1;

            while (true) {
                Socket client = serverSocket.accept();
                bufferLength = client.getSendBufferSize();
                SingleClient singleClient = new SingleClient(client, clientNo);
                new Thread(singleClient).start();
                clientNo++;
            }

        } catch (Exception ex) {
            System.err.println(ex.toString());
        }
    }

    private File enrolFile = new File("Data\\enrollment");
    private File friendsFile = new File("Data\\friends");

    private int read;

    class SingleClient implements Runnable {

        int clientNo;
        Socket client;
        private ClientInfo clientInfo;

        public SingleClient(Socket client, int clientNo) {
            this.client = client;
            this.clientNo = clientNo;
        }

        @Override
        public void run() {

            try {
                BufferedInputStream inputFromClient = new BufferedInputStream(client.getInputStream());
                BufferedOutputStream outputToClient = new BufferedOutputStream(client.getOutputStream());

                while (true) {
                    byte[] inBuffer = new byte[bufferLength];

                    read = inputFromClient.read(inBuffer);

                    String packet = new String(inBuffer, 0, read).split("\\$\\$\\$\\$")[0];

                    String[] data = packet.split("####");

                    switch (data[0]) {
                        case "reg":
                            boolean isEnrolled = false;
                            boolean wasOffline = false;
                            clientInfo = new ClientInfo(client, data[1]);
                            BufferedReader readReg = new BufferedReader(new FileReader(enrolFile));
                            String user = null;
                            while ((user = readReg.readLine()) != null) {
                                String[] userInfo = user.split("####");
                                if (userInfo[1].equals(data[1])) {
                                    if (userInfo[2].equals(data[2])) {
                                        packet = "msg####login successful####";
                                        clients.add(clientInfo);
                                        System.out.println(clientNo + ": Logged In user " + clientInfo.getUserName() + " : "
                                                + clientInfo.getClientSocket() + " at " + new Date());
                                        wasOffline = true;
                                    } else {
                                        packet = "err####wrong password####";
                                    }
                                    isEnrolled = true;
                                    break;
                                }
                            }

                            if (!isEnrolled) {
                                try (BufferedWriter writeReg = new BufferedWriter(new FileWriter(enrolFile, true))) {
                                    writeReg.write(data[0] + "####" + data[1] + "####" + data[2] + "\n");
                                }

                                packet = "msg####sign up and login successful####";
                                clients.add(clientInfo);
                                System.out.println(clientNo + ": Logged In user " + clientInfo.getUserName() + " : "
                                        + clientInfo.getClientSocket() + " at " + new Date());
                            }

                            outputToClient.write(makePacket(packet, ""));
                            outputToClient.flush();

                            broadcastAll();

                            if (wasOffline) {
                                sendStoredPacket(clientInfo);
                            }

                            break;

                        case "req":
                        case "chat":
                        case "noreq":
                        case "yesreq":
                        case "filename":
                        case "filechunk":
                            if (data[0].equals("yesreq")) {
                                try (BufferedWriter writeReq = new BufferedWriter(new FileWriter(friendsFile, true))) {
                                    writeReq.write(data[0] + "####" + data[1] + "####" + data[2] + "\n");
                                }
                                packet = "yesreq####" + data[1] + " has accepted your friend request####";

                            } else if (data[0].equals("noreq")) {
                                packet = "noreq####" + data[1] + " has rejected your friend request####";
                            }

                            Socket fr = isLoggedIn(data[2]);
                            if (fr != null) {
                                sendData(fr, inBuffer, data, packet);
                            } else {
                                storeTempData(inBuffer, data[2]);
                            }

                            if (data[0].equals("yesreq")) {
                                broadcastAll();
                            }

                            break;
                        default:
                            break;
                    }

                }
            } catch (Exception ex) {
                clients.remove(clientInfo);
                broadcastAll();
                System.out.println(clientNo + ": Logged Out user " + clientInfo.getUserName() + " : "
                        + clientInfo.getClientSocket() + " at " + new Date());
            }
        }
    }

    synchronized private void sendData(Socket fr, byte[] inBuffer, String[] data, String packet) {
        try {
            BufferedOutputStream sendMsg = new BufferedOutputStream(fr.getOutputStream());
            if (data[0].equals("yesreq") || data[0].equals("noreq")) {
                sendMsg.write(makePacket(packet, ""));
                sendMsg.flush();
            } else {
                sendMsg.write(inBuffer);
                sendMsg.flush();
            }
        } catch (Exception ex) {
            System.err.println(ex.toString());
        }
    }

    private byte[] makePacket(String header, String data) {

        byte[] headerBytes = header.getBytes();
        byte[] dataBytes = data.getBytes();
        byte[] trailerBytes = ("$$$$").getBytes();

        byte[] bytes = new byte[bufferLength];

        System.arraycopy(headerBytes, 0, bytes, 0, headerBytes.length);
        System.arraycopy(dataBytes, 0, bytes, headerBytes.length, dataBytes.length);
        System.arraycopy(trailerBytes, 0, bytes, bufferLength - trailerBytes.length, trailerBytes.length);

        return bytes;

    }

    private void storeTempData(byte[] packet, String user) {

        File tempFile = new File("Data\\" + user);

        try (BufferedOutputStream temp = new BufferedOutputStream(new FileOutputStream(tempFile, true))) {
            temp.write(packet);
        } catch (Exception ex) {
            System.err.println(ex.toString());
        }
    }

    private void sendStoredPacket(ClientInfo clientInfo) {

        File tempFile = new File("Data\\" + clientInfo.getUserName());
        if (!tempFile.exists()) {
            return;
        }

        try {
            BufferedOutputStream outputToClient = new BufferedOutputStream(clientInfo.getClientSocket().getOutputStream());

            try (BufferedInputStream readTemp = new BufferedInputStream(new FileInputStream(tempFile))) {
                byte[] fileBuffer = new byte[bufferLength];
                while (readTemp.read(fileBuffer) != -1) {
                    outputToClient.write(fileBuffer);
                    outputToClient.flush();

                }
                tempFile.delete();
            }

        } catch (Exception ex) {
            System.err.println(ex.toString());
        }

    }

    private String makeUserPacket() {
        String packet = "users####";
        for (int i = 0; i < clients.size(); i++) {
            packet = packet + clients.get(i).getUserName() + "####";
        }
        return packet;
    }

    private ArrayList<String> makeFriendPacket() {
        ArrayList<ArrayList<String>> friendList = generateFriendList();

        ArrayList<String> friendPacket = new ArrayList<>();

        for (int i = 0; i < friendList.size(); i++) {
            String packet = "friends####";
            for (int f = 0; f < friendList.get(i).size(); f++) {
                packet = packet + friendList.get(i).get(f) + "####";
            }
            friendPacket.add(packet);
        }

        return friendPacket;
    }

    private ArrayList<ArrayList<String>> generateFriendList() {
        ArrayList<ArrayList<String>> friendList = new ArrayList<>();

        try {
            ArrayList<String[]> tempList = new ArrayList<>();
            try (BufferedReader readFr = new BufferedReader(new FileReader(friendsFile))) {
                String totalFrInfo = null;
                while ((totalFrInfo = readFr.readLine()) != null) {
                    tempList.add(totalFrInfo.split("####"));
                }
            }

            for (int i = 0; i < clients.size(); i++) {
                ArrayList<String> friends = new ArrayList<>();
                String user = clients.get(i).getUserName();
                friends.add(user);
                for (int t = 0; t < tempList.size(); t++) {
                    if (tempList.get(t)[1].equals(user)) {
                        friends.add(tempList.get(t)[2]);
                    } else if (tempList.get(t)[2].equals(user)) {
                        friends.add(tempList.get(t)[1]);
                    }
                }
                friendList.add(friends);
            }

        } catch (Exception ex) {
            System.err.println(ex.toString());
        }
        return friendList;
    }

    private void broadcastAll() {
        try {
            byte[] userList = makePacket(makeUserPacket(), "");
            ArrayList<String> friendPacket = makeFriendPacket();

            for (int i = 0; i < clients.size(); i++) {
                BufferedOutputStream outputToClient = new BufferedOutputStream(clients.get(i).getClientSocket().getOutputStream());
                outputToClient.write(userList);
                outputToClient.flush();

                outputToClient.write(makePacket(friendPacket.get(i), ""));
                outputToClient.flush();
            }
        } catch (Exception ex) {
            System.err.println(ex.toString());
        }
    }

    public static void main(String[] args) {
        new Server();
    }

}