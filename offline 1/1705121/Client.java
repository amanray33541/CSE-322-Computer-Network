package offlineone;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.ProgressMonitor;
import javax.swing.text.DefaultCaret;

public class Client extends javax.swing.JFrame {

    private final String hostAddress = "127.0.0.1";
    private final int portNumber = 8000;

    private int bufferLength = 1024 * 32;

    public Client() {
        initComponents();
    }
    @SuppressWarnings("unchecked")
    private void initComponents() {

        regPanel = new javax.swing.JPanel();
        enrolLabel = new javax.swing.JLabel();
        userNameLabel = new javax.swing.JLabel();
        userNameTF = new javax.swing.JTextField();
        passwordLabel = new javax.swing.JLabel();
        passwordField = new javax.swing.JPasswordField();
        enterButton = new javax.swing.JButton();
        chatPanel = new javax.swing.JPanel();
        onlineLabel = new javax.swing.JLabel();
        onlineSP = new javax.swing.JScrollPane();
        onlineList = new javax.swing.JList<>();
        allFrLabel = new javax.swing.JLabel();
        allFrSP = new javax.swing.JScrollPane();
        allFrList = new javax.swing.JList<>();
        anonymousLabel = new javax.swing.JLabel();
        anonymousSP = new javax.swing.JScrollPane();
        anonymousList = new javax.swing.JList<>();
        reqBtn = new javax.swing.JButton();
        msgSP = new javax.swing.JScrollPane();
        msgTA = new javax.swing.JTextArea();
        msgTF = new javax.swing.JTextField();
        msgBtn = new javax.swing.JButton();
        clientLabel = new javax.swing.JLabel();
        logSP = new javax.swing.JScrollPane();
        logTA = new javax.swing.JTextArea();
        filePathTF = new javax.swing.JTextField();
        selectFileBtn = new javax.swing.JButton();
        sendFileBtn = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setTitle("ClientFrame");
        setMinimumSize(new java.awt.Dimension(768, 512));
        setName("frame"); // NOI18N
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });
        getContentPane().setLayout(new java.awt.CardLayout());

        regPanel.setMinimumSize(new java.awt.Dimension(768, 512));

        enrolLabel.setFont(new java.awt.Font("Aman Ray", 0, 24)); 
        enrolLabel.setText("Log in  pannel.(userName & Password From file");

        userNameLabel.setFont(new java.awt.Font("Aman Ray", 0, 24));
        userNameLabel.setText("User Name :");

        userNameTF.setFont(new java.awt.Font("Aman Ray", 0, 24));
        userNameTF.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        passwordLabel.setFont(new java.awt.Font("Aman Ray", 0, 24)); 
        passwordLabel.setText("Password  :");

        passwordField.setFont(new java.awt.Font("Aman Ray", 0, 24)); 
        passwordField.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        enterButton.setFont(new java.awt.Font("Aman Ray", 0, 24));
        enterButton.setText("Login");
        enterButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                enterButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout regPanelLayout = new javax.swing.GroupLayout(regPanel);
        regPanel.setLayout(regPanelLayout);
        regPanelLayout.setHorizontalGroup(
            regPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, regPanelLayout.createSequentialGroup()
                .addGroup(regPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(regPanelLayout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(enterButton))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, regPanelLayout.createSequentialGroup()
                        .addGap(70, 70, 70)
                        .addGroup(regPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(regPanelLayout.createSequentialGroup()
                                .addComponent(enrolLabel)
                                .addGap(0, 436, Short.MAX_VALUE))
                            .addGroup(regPanelLayout.createSequentialGroup()
                                .addGroup(regPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(userNameLabel)
                                    .addComponent(passwordLabel))
                                .addGap(40, 40, 40)
                                .addGroup(regPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(userNameTF)
                                    .addComponent(passwordField))))))
                .addGap(70, 70, 70))
        );
        regPanelLayout.setVerticalGroup(
            regPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, regPanelLayout.createSequentialGroup()
                .addGap(50, 50, 50)
                .addComponent(enrolLabel)
                .addGap(70, 70, 70)
                .addGroup(regPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(userNameLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(userNameTF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(35, 35, 35)
                .addGroup(regPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(passwordLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(passwordField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(50, 50, 50)
                .addComponent(enterButton)
                .addContainerGap(173, Short.MAX_VALUE))
        );

        getContentPane().add(regPanel, "reg");

        chatPanel.setMinimumSize(new java.awt.Dimension(768, 512));
        chatPanel.setPreferredSize(new java.awt.Dimension(768, 512));

        onlineLabel.setFont(new java.awt.Font("Aman Ray", 0, 18)); 
        onlineLabel.setText("Online ");

        onlineList.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        onlineList.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                onlineListValueChanged(evt);
            }
        });
        onlineSP.setViewportView(onlineList);

        allFrLabel.setFont(new java.awt.Font("Aman Ray", 0, 18)); 
        allFrLabel.setText("All Friends");

        allFrList.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        allFrList.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                allFrListValueChanged(evt);
            }
        });
        allFrSP.setViewportView(allFrList);

        anonymousLabel.setFont(new java.awt.Font("Aman Ray", 0, 18)); 
        anonymousLabel.setText("connecter to server");

        anonymousList.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        anonymousList.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                anonymousListValueChanged(evt);
            }
        });
        anonymousSP.setViewportView(anonymousList);

        reqBtn.setFont(new java.awt.Font("Aman Ray", 0, 18)); 
        reqBtn.setText("Send Request");
        reqBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                reqBtnActionPerformed(evt);
            }
        });

        msgTA.setEditable(false);
        msgTA.setColumns(20);
        msgTA.setRows(5);
        msgSP.setViewportView(msgTA);
        DefaultCaret msgCaret = (DefaultCaret)msgTA.getCaret();
        msgCaret.setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);

        msgTF.setFont(new java.awt.Font("Aman Ray", 0, 18)); 

        msgBtn.setFont(new java.awt.Font("Aman Ray", 0, 18));
        msgBtn.setText("Send Message");
        msgBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                msgBtnActionPerformed(evt);
            }
        });

        clientLabel.setFont(new java.awt.Font("Aman Ray", 0, 18)); 
        clientLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        clientLabel.setText("Client Name");

        logSP.setToolTipText("");
        logSP.setAutoscrolls(true);

        logTA.setEditable(false);
        logTA.setColumns(20);
        logTA.setRows(5);
        logTA.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        logSP.setViewportView(logTA);
        DefaultCaret caret = (DefaultCaret)logTA.getCaret();
        caret.setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);

        filePathTF.setEditable(false);
        filePathTF.setBackground(new java.awt.Color(255, 255, 255));
        filePathTF.setFont(new java.awt.Font("Aman Ray", 0, 14)); 
        filePathTF.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        selectFileBtn.setFont(new java.awt.Font("Aman Ray", 0, 18));
        selectFileBtn.setText("Choose Files");
        selectFileBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                selectFileBtnActionPerformed(evt);
            }
        });

        sendFileBtn.setFont(new java.awt.Font("Aman Ray", 0, 18)); 
        sendFileBtn.setText("Send Files");
        sendFileBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sendFileBtnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout chatPanelLayout = new javax.swing.GroupLayout(chatPanel);
        chatPanel.setLayout(chatPanelLayout);
        chatPanelLayout.setHorizontalGroup(
            chatPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(chatPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(chatPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(chatPanelLayout.createSequentialGroup()
                        .addGroup(chatPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(onlineLabel)
                            .addComponent(onlineSP, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(chatPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(allFrSP, javax.swing.GroupLayout.DEFAULT_SIZE, 135, Short.MAX_VALUE)
                            .addComponent(allFrLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(18, 18, 18)
                        .addGroup(chatPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(reqBtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(anonymousSP, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                            .addComponent(anonymousLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(chatPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(msgBtn)
                        .addGroup(chatPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(msgSP, javax.swing.GroupLayout.DEFAULT_SIZE, 438, Short.MAX_VALUE)
                            .addComponent(msgTF))))
                .addGap(18, 18, 18)
                .addGroup(chatPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(logSP)
                    .addComponent(filePathTF)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, chatPanelLayout.createSequentialGroup()
                        .addComponent(selectFileBtn)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 25, Short.MAX_VALUE)
                        .addComponent(sendFileBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(clientLabel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        chatPanelLayout.setVerticalGroup(
            chatPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(chatPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(chatPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(onlineLabel)
                    .addComponent(allFrLabel)
                    .addComponent(anonymousLabel)
                    .addComponent(clientLabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(chatPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(chatPanelLayout.createSequentialGroup()
                        .addGroup(chatPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(allFrSP, javax.swing.GroupLayout.DEFAULT_SIZE, 195, Short.MAX_VALUE)
                            .addComponent(anonymousSP)
                            .addComponent(onlineSP))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(reqBtn)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(msgSP, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(logSP))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(chatPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(msgTF, javax.swing.GroupLayout.DEFAULT_SIZE, 42, Short.MAX_VALUE)
                    .addComponent(filePathTF))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(chatPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(msgBtn)
                    .addComponent(sendFileBtn)
                    .addComponent(selectFileBtn))
                .addContainerGap())
        );

        getContentPane().add(chatPanel, "chat");

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private String userName;
    private void enterButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_enterButtonActionPerformed
        userName = userNameTF.getText();
        clientLabel.setText(userName);
        char[] password = passwordField.getPassword();
        if (!userName.isEmpty() && password.length != 0) {
            connectToServer(password);
        } else {
            showMessage("userName or password can not be empty");
        }
    }

    private void formWindowClosing(java.awt.event.WindowEvent evt) {
        int answer = JOptionPane.showConfirmDialog(this, "Want To Exit?");
        if (answer == JOptionPane.YES_OPTION) {
            System.exit(0);
        }
    }

    private void msgBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_msgBtnActionPerformed
        String chatMsg = msgTF.getText();

        if (!chatMsg.isEmpty()) {
            if (reqFr != null) {
                showMessage("You cannot chat with anonymous users. Send friend request first.");
            } else 
            	if (chatOnFr != null) {
                sendChatMessage(chatOnFr, chatMsg);
            } else if (chatOffFr != null) {
                sendChatMessage(chatOffFr, chatMsg);
            } else {
                showMessage("A friend user must be selected");
            }
        } else {
            showMessage("Message field cannot be empty");
        }
    }//GEN-LAST:event_msgBtnActionPerformed

    private void reqBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_reqBtnActionPerformed
        if (reqFr != null) {
            String outPacket = "req####" + userName + "####" + reqFr + "####";
            sendPacket(outPacket, "");
            appendLog("A friend request has been sent to " + reqFr);
            reqFr = null;
        } else {
            showMessage("An anonymous user must be selected");
        }
    }//GEN-LAST:event_reqBtnActionPerformed

    private void anonymousListValueChanged(javax.swing.event.ListSelectionEvent evt) {//GEN-FIRST:event_anonymousListValueChanged
        reqFr = anonymousList.getSelectedValue();

        allFrList.clearSelection();
        onlineList.clearSelection();

        chatOffFr = null;
        chatOnFr = null;
    }//GEN-LAST:event_anonymousListValueChanged

    private String fileName;

    private void sendFileBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sendFileBtnActionPerformed
        fileName = filePathTF.getText();
        if (!fileName.isEmpty()) {
            if (reqFr != null) {
                showMessage("You cannot send file to anonymous users. Send friend request first.");
            } else if (chatOnFr != null) {
                sendFile(chatOnFr);
            } else if (chatOffFr != null) {
                sendFile(chatOffFr);
            } else {
                showMessage("A friend user must be selected");
            }
        } else {
            showMessage("No file is selected");
        }
    }//GEN-LAST:event_sendFileBtnActionPerformed

    private void allFrListValueChanged(javax.swing.event.ListSelectionEvent evt) {//GEN-FIRST:event_allFrListValueChanged
        chatOffFr = allFrList.getSelectedValue();

        onlineList.clearSelection();
        anonymousList.clearSelection();

        reqFr = null;
        chatOnFr = null;
    }//GEN-LAST:event_allFrListValueChanged

    private void onlineListValueChanged(javax.swing.event.ListSelectionEvent evt) {//GEN-FIRST:event_onlineListValueChanged
        chatOnFr = onlineList.getSelectedValue();

        allFrList.clearSelection();
        anonymousList.clearSelection();

        chatOffFr = null;
        reqFr = null;
    }//GEN-LAST:event_onlineListValueChanged

    JFileChooser fileChooser = new JFileChooser();
    private String pathToFile;
    private long fileLength;

    private void selectFileBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_selectFileBtnActionPerformed
        fileChooser.showOpenDialog(this);
        File selectedFile = fileChooser.getSelectedFile();
        if (selectedFile != null) {
            filePathTF.setText(selectedFile.getName());
            pathToFile = selectedFile.getAbsolutePath();
            fileLength = new File(pathToFile).length();
            appendLog(pathToFile + " is selected :: " + fileLength + " bytes");
        }
    }//GEN-LAST:event_selectFileBtnActionPerformed

    private String reqFr = null;
    private String chatOnFr = null;
    private String chatOffFr = null;

    private void sendChatMessage(String friend, String chatMsg) {
        String data = chatMsg + " :: " + new Date();
        String header = "chat####" + userName + "####" + friend + "####";
        sendPacket(header, data);
        msgTA.append(userName + " --> " + friend + " : " + data + "\n::Sent at " + new Date() + "\n");
        msgTF.setText("");
    }

    private double upProgress = 0;
    private double downProgress = 0;
/*    
    public void uploadFileRequest() throws IOException, ClassNotFoundException {
        if(data.size() == 0){
            System.out.println("No request found.");
            out.writeObject(0);
            return;
        }
        else out.writeObject(1);
        for(int id : userName.setText()){
            System.out.println("Request ID : " + userName + ", Message : " + userName.get(data));
        }
        System.out.println("Enter the request ID for upload : ");
        int id = input.nextInt();
        out.writeObject(data);
        userName.remove(data);
        UploadingMonitor(0);
    }
*/
    private void sendFile(String friend) {

        String header = "filename####" + userName + "####" + friend + "####" + fileName + "####" + fileLength + "####";
        sendPacket(header, "");

        msgTA.append(userName + " is going to sent a file to " + friend + " named as " + fileName + " of "
                + fileLength + "bytes\n::Sent at " + new Date() + "\n");

        new UploadingMonitor();

        new Thread() {
            @Override
            public void run() {
                int sent = 0;
                try (BufferedInputStream inputFile = new BufferedInputStream(new FileInputStream(pathToFile))) {
                    int count;

                    byte[] maxHeader = ("filechunk####" + userName + "####" + friend + "####" + fileName + "####"
                            + bufferLength + "####" + fileLength + "####" + fileLength + "####" + bufferLength + "####").getBytes();

                    byte[] trailer = ("$$$$").getBytes();

                    byte[] fileBuffer = new byte[bufferLength - maxHeader.length - trailer.length];

                    while ((count = inputFile.read(fileBuffer)) != -1) {
                        sent += count;

                        byte[] header = ("filechunk####" + userName + "####" + friend + "####" + fileName + "####"
                                + count + "####" + sent + "####" + fileLength + "####" + maxHeader.length + "####").getBytes();

                        byte[] bytes = new byte[bufferLength];
 
                        System.arraycopy(header, 0, bytes, 0, header.length);
                        System.arraycopy(fileBuffer, 0, bytes, maxHeader.length, count);
                        System.arraycopy(trailer, 0, bytes, bufferLength - trailer.length, trailer.length);

                        sendToServer(bytes);

                        upProgress = sent * 100.0 / fileLength;
                        logTA.append(fileName + "::Uploaded " + upProgress + " %\n");

                        if (upProgress == 100.00) {
                            showMessage("Uploading Complete");
                        }

                    }

                    filePathTF.setText("");
                } catch (Exception ex) {
                    connectionFailure(ex);
                }
            }
        }.start();

    }

    synchronized private void sendToServer(byte[] bytes) throws Exception {
        outputToServer.write(bytes);
        outputToServer.flush();
    }

    class UploadingMonitor implements Runnable {

        private final ProgressMonitor progressMonitor;

        public UploadingMonitor() {
            String message = filePathTF.getText() + " is uploading";
            progressMonitor = new ProgressMonitor(chatPanel, message, "uploaded 0.00%", 0, 100);
            progressMonitor.setProgress(0);
            new Thread(this).start();
        }

        @Override
        public void run() {
            int round = 0;
            while (round != 100) {
                round = (int) Math.round(upProgress);
                progressMonitor.setProgress(round);
                String percent = String.format("%.2f", upProgress);
                progressMonitor.setNote("uploaded : " + percent + "%");
            }
        }
    }

    class DownloadingMonitor implements Runnable {

        private final ProgressMonitor progressMonitor;

        public DownloadingMonitor() {
            String message = fileName + " is downloading";
            progressMonitor = new ProgressMonitor(chatPanel, message, "downloaded 0.00%", 0, 100);
            progressMonitor.setProgress(0);
            new Thread(this).start();
        }

        @Override
        public void run() {
            int round = 0;
            while (round != 100) {
                round = (int) Math.round(downProgress);
                progressMonitor.setProgress(round);
                String percent = String.format("%.2f", downProgress);
                progressMonitor.setNote("downloaded : " + percent + "%");
            }
        }
    }

    private void connectionFailure(Exception ex) {
        showMessage(ex.toString());
        appendLog(ex.toString());
        ex.printStackTrace();
    }

    private Socket socket;
    private BufferedOutputStream outputToServer;
    private BufferedInputStream inputFromServer;
    private int read;

    private void connectToServer(char[] password) {
        try {
            InetAddress ip = InetAddress.getByName(hostAddress);
            socket = new Socket(ip, portNumber);
            outputToServer = new BufferedOutputStream(socket.getOutputStream());
            inputFromServer = new BufferedInputStream(socket.getInputStream());
            bufferLength = socket.getReceiveBufferSize();
            String header = "reg" + "####" + userName + "####" + new String(password) + "####";

            sendPacket(header, "");

            byte[] buffer = new byte[bufferLength];

            read = inputFromServer.read(buffer);

            String[] data = new String(buffer, 0, read).split("\\$\\$\\$\\$")[0].split("####");

            if (data[0].equals("msg")) {
                showMessage(data[1]);
                regPanel.setVisible(false);
                chatPanel.setVisible(true);
            } else if (data[0].equals("err")) {
                showMessage(data[1]);
                throw new Exception(data[1]);
            }

            new InputFromServer();

        } catch (Exception ex) {
            connectionFailure(ex);

        }
    }

    class InputFromServer implements Runnable {

        public InputFromServer() {
            new Thread(this).start();
        }

        @Override
        public void run() {
            try {
                while (true) {
                    byte[] buffer = new byte[bufferLength];
                    read = inputFromServer.read(buffer);

                    String[] data = new String(buffer, 0, read).split("\\$\\$\\$\\$")[0].split("####");
                    switch (data[0]) {
                        case "users":
                        case "friends":
                            makeList(data);
                            break;
                        case "req":
                            friendReq(data);
                            break;
                        case "chat":
                            chatMessage(data);
                            break;
                        case "noreq":
                        case "yesreq":
                            showMessage(data[1]);
                            break;
                        case "filename":
                            incomingFileName(data);
                            break;
                        case "filechunk":
                            incomingFileChunk(buffer);
                            break;
                        default:
                            break;
                    }
                }
            } catch (Exception ex) {
                connectionFailure(ex);
            }
        }
    }

    private void showMessage(String msg) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                JOptionPane.showMessageDialog(chatPanel, msg);
            }
        }).start();

    }

    private void appendLog(String log) {
        logTA.append(log + " at " + new Date() + "\n");
    }

    private void sendPacket(String header, String data) {

        try {

            byte[] headerBytes = header.getBytes();
            byte[] dataBytes = data.getBytes();
            byte[] trailerBytes = ("$$$$").getBytes();

            byte[] bytes = new byte[bufferLength];

            System.arraycopy(headerBytes, 0, bytes, 0, headerBytes.length);
            System.arraycopy(dataBytes, 0, bytes, headerBytes.length, dataBytes.length);
            System.arraycopy(trailerBytes, 0, bytes, bufferLength - trailerBytes.length, trailerBytes.length);

            outputToServer.write(bytes);
            outputToServer.flush();
        } catch (Exception ex) {
            connectionFailure(ex);
        }
    }

    private void chatMessage(String[] data) {
        msgTA.append(data[1] + " --> " + data[2] + " : " + data[3] + "\n::Received at " + new Date() + "\n");
    }

    private void friendReq(String[] data) {

        int answer = JOptionPane.showConfirmDialog(this, "user " + data[1] + " wants to be your friend. Accept?");
        String outPacket;
        if (answer == JOptionPane.YES_OPTION) {
            outPacket = "yes";
        } else {
            outPacket = "no";
        }
        outPacket = outPacket + "req####" + data[2] + "####" + data[1] + "####";
        sendPacket(outPacket, "");
    }

    private void incomingFileName(String[] data) {
        fileName = data[3];

        //showMessage(fileName + "(" + data[4] + "bytes)" + " is going to be received from " + data[1]);
        new File(fileName).delete();

        msgTA.append(data[1] + " is going to sent a file to " + data[2] + " named as " + fileName
                + " of " + data[4] + "bytes\n::Received at " + new Date() + "\n");

        new DownloadingMonitor();
    }

    private void incomingFileChunk(byte[] buffer) {
        String[] header = new String(buffer).split("####");

        byte[] fileChunk = new byte[Integer.valueOf(header[4])];
        System.out.println(new String(buffer));
        System.arraycopy(buffer, Integer.valueOf(header[7]), fileChunk, 0, fileChunk.length);

        fileName = header[3];
        try (BufferedOutputStream outFile = new BufferedOutputStream(new FileOutputStream(header[1] + header[2] + fileName, true))) {
            outFile.write(fileChunk);
        } catch (Exception ex) {
            connectionFailure(ex);
        }

        downProgress = Integer.valueOf(header[5]) * 100.0 / Long.valueOf(header[6]);
        String percent = String.format("%.2f", downProgress);

        logTA.append(header[3] + ":: Downloaded " + percent + " %\n");

        if (downProgress == 100.00) {
            showMessage("Downloading Complete");
        }
    }

    private String[] anonymousUser;
    private String[] allFriend;
    private String[] onFriend;

    private final ArrayList<String> users = new ArrayList<>();
    private final ArrayList<String> friends = new ArrayList<>();

    private void makeList(String[] data) {
        if (data[0].equals("users")) {
            users.clear();
            for (int i = 1; i < data.length; i++) {
                if (data[i].trim().length() > 0) {
                    users.add(data[i]);
                }
            }
            users.remove(userName);
        } else {
            friends.clear();
            for (int i = 2; i < data.length; i++) {
                if (data[i].trim().length() > 0) {
                    friends.add(data[i]);
                }
            }
        }

        ArrayList<String> anonymousUsers = new ArrayList<>();

        for (int i = 0; i < users.size(); i++) {
            anonymousUsers.add(users.get(i));
        }

        ArrayList<String> onFriends = new ArrayList<>();
        ArrayList<String> allFriends = new ArrayList<>();

        for (int i = 0; i < friends.size(); i++) {
            allFriends.add(friends.get(i));
        }

        for (int i = 0; i < friends.size(); i++) {
            String fr = friends.get(i);
            if (users.contains(fr)) {
                onFriends.add(fr);
                anonymousUsers.remove(fr);
            }
        }

        onFriend = new String[onFriends.size()];
        onFriend = onFriends.toArray(onFriend);

        onlineList.setModel(new javax.swing.AbstractListModel() {
            String[] strings = onFriend;

            @Override
            public int getSize() {
                return strings.length;
            }

            @Override
            public Object getElementAt(int i) {
                return strings[i];
            }
        });

        allFriend = new String[allFriends.size()];
        allFriend = allFriends.toArray(allFriend);

        allFrList.setModel(new javax.swing.AbstractListModel() {
            String[] strings = allFriend;

            @Override
            public int getSize() {
                return strings.length;
            }

            @Override
            public Object getElementAt(int i) {
                return strings[i];
            }
        });

        anonymousUser = new String[anonymousUsers.size()];
        anonymousUser = anonymousUsers.toArray(anonymousUser);

        anonymousList.setModel(new javax.swing.AbstractListModel() {
            String[] strings = anonymousUser;

            @Override
            public int getSize() {
                return strings.length;
            }

            @Override
            public Object getElementAt(int i) {
                return strings[i];
            }
        });

    }

    public static void main(String args[]) {

        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;

                }
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Client.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        }

        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new Client().setVisible(true);
            }
        });
    }

    private javax.swing.JLabel allFrLabel;
    private javax.swing.JList<String> allFrList;
    private javax.swing.JScrollPane allFrSP;
    private javax.swing.JLabel anonymousLabel;
    private javax.swing.JList<String> anonymousList;
    private javax.swing.JScrollPane anonymousSP;
    private javax.swing.JPanel chatPanel;
    private javax.swing.JLabel clientLabel;
    private javax.swing.JLabel enrolLabel;
    private javax.swing.JButton enterButton;
    private javax.swing.JTextField filePathTF;
    private javax.swing.JScrollPane logSP;
    private javax.swing.JTextArea logTA;
    private javax.swing.JButton msgBtn;
    private javax.swing.JScrollPane msgSP;
    private javax.swing.JTextArea msgTA;
    private javax.swing.JTextField msgTF;
    private javax.swing.JLabel onlineLabel;
    private javax.swing.JList<String> onlineList;
    private javax.swing.JScrollPane onlineSP;
    private javax.swing.JPasswordField passwordField;
    private javax.swing.JLabel passwordLabel;
    private javax.swing.JPanel regPanel;
    private javax.swing.JButton reqBtn;
    private javax.swing.JButton selectFileBtn;
    private javax.swing.JButton sendFileBtn;
    private javax.swing.JLabel userNameLabel;
    private javax.swing.JTextField userNameTF;
}