/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package editor;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author vali
 */
public class GUI extends javax.swing.JFrame {

    /**
     * Creates new form GUI
     */
    public GUI() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jMenu1 = new javax.swing.JMenu();
        jMenu2 = new javax.swing.JMenu();
        jMenu3 = new javax.swing.JMenu();
        buttonGroup1 = new javax.swing.ButtonGroup();
        buttonGroup2 = new javax.swing.ButtonGroup();
        jRadioButtonMenuItem7 = new javax.swing.JRadioButtonMenuItem();
        jMenu5 = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenu7 = new javax.swing.JMenu();
        buttonGroup3 = new javax.swing.ButtonGroup();
        buttonGroup4 = new javax.swing.ButtonGroup();
        buttonGroup5 = new javax.swing.ButtonGroup();
        buttonGroup6 = new javax.swing.ButtonGroup();
        buttonGroup7 = new javax.swing.ButtonGroup();
        buttonGroup8 = new javax.swing.ButtonGroup();
        buttonGroup9 = new javax.swing.ButtonGroup();
        buttonGroup10 = new javax.swing.ButtonGroup();
        jRadioButtonMenuItem1 = new javax.swing.JRadioButtonMenuItem();
        jRadioButtonMenuItem2 = new javax.swing.JRadioButtonMenuItem();
        jRadioButtonMenuItem3 = new javax.swing.JRadioButtonMenuItem();
        jScrollPane1 = new javax.swing.JScrollPane();
        Textbox = new javax.swing.JTextArea();
        StartComm = new javax.swing.JButton();
        Status = new javax.swing.JLabel();
        TestButton = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        sent = new javax.swing.JLabel();
        received = new javax.swing.JLabel();
        MenuBar = new javax.swing.JMenuBar();
        OptionsMenu = new javax.swing.JMenu();
        PeersMenu = new javax.swing.JMenu();
        Peers1 = new javax.swing.JRadioButtonMenuItem();
        Peers2 = new javax.swing.JRadioButtonMenuItem();
        Peers3 = new javax.swing.JRadioButtonMenuItem();
        Peers4 = new javax.swing.JRadioButtonMenuItem();
        Peers5 = new javax.swing.JRadioButtonMenuItem();
        Peers6 = new javax.swing.JRadioButtonMenuItem();
        Peers7 = new javax.swing.JRadioButtonMenuItem();
        IDMenu = new javax.swing.JMenu();
        ID0 = new javax.swing.JRadioButtonMenuItem();
        ID1 = new javax.swing.JRadioButtonMenuItem();
        ID2 = new javax.swing.JRadioButtonMenuItem();
        ID3 = new javax.swing.JRadioButtonMenuItem();
        ID4 = new javax.swing.JRadioButtonMenuItem();
        ID5 = new javax.swing.JRadioButtonMenuItem();
        ID6 = new javax.swing.JRadioButtonMenuItem();
        CommType = new javax.swing.JMenu();
        CausalOrder = new javax.swing.JRadioButtonMenuItem();
        TotalOrderToken = new javax.swing.JRadioButtonMenuItem();
        TotalOrderServer = new javax.swing.JRadioButtonMenuItem();
        ConnectToMenu = new javax.swing.JMenu();
        Peer1 = new javax.swing.JMenu();
        Peer1Server = new javax.swing.JRadioButtonMenuItem();
        Peer1Client = new javax.swing.JRadioButtonMenuItem();
        Peer2 = new javax.swing.JMenu();
        Peer2Server = new javax.swing.JRadioButtonMenuItem();
        Peer2Client = new javax.swing.JRadioButtonMenuItem();
        Peer3 = new javax.swing.JMenu();
        Peer3Server = new javax.swing.JRadioButtonMenuItem();
        Peer3Client = new javax.swing.JRadioButtonMenuItem();
        Peer4 = new javax.swing.JMenu();
        Peer4Server = new javax.swing.JRadioButtonMenuItem();
        Peer4Client = new javax.swing.JRadioButtonMenuItem();
        Peer5 = new javax.swing.JMenu();
        Peer5Server = new javax.swing.JRadioButtonMenuItem();
        Peer5Client = new javax.swing.JRadioButtonMenuItem();
        Peer6 = new javax.swing.JMenu();
        Peer6Server = new javax.swing.JRadioButtonMenuItem();
        Peer6Client = new javax.swing.JRadioButtonMenuItem();

        jMenu1.setText("jMenu1");

        jMenu2.setText("jMenu2");

        jMenu3.setText("jMenu3");

        jRadioButtonMenuItem7.setSelected(true);
        jRadioButtonMenuItem7.setText("jRadioButtonMenuItem7");

        jMenu5.setText("jMenu5");

        jMenuItem1.setText("jMenuItem1");

        jMenu7.setText("jMenu7");

        jRadioButtonMenuItem1.setSelected(true);
        jRadioButtonMenuItem1.setText("jRadioButtonMenuItem1");

        jRadioButtonMenuItem2.setSelected(true);
        jRadioButtonMenuItem2.setText("jRadioButtonMenuItem2");

        jRadioButtonMenuItem3.setSelected(true);
        jRadioButtonMenuItem3.setText("jRadioButtonMenuItem3");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        Textbox.setColumns(20);
        Textbox.setRows(5);
        Textbox.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                TextboxCaretUpdate(evt);
            }
        });
        jScrollPane1.setViewportView(Textbox);

        StartComm.setText("Start Communication Module");
        StartComm.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                StartCommActionPerformed(evt);
            }
        });

        Status.setText("Status: First choose an ID (from Options), then start the communication module");

        TestButton.setText("Start Test");
        TestButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TestButtonActionPerformed(evt);
            }
        });

        jButton1.setText("Stop Test");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        sent.setText(" Bytes Sent: 0");

        received.setText(" Bytes Received: 0");

        OptionsMenu.setText("Options");

        PeersMenu.setText("Peers");

        buttonGroup10.add(Peers1);
        Peers1.setText("1");
        Peers1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Peers1ActionPerformed(evt);
            }
        });
        PeersMenu.add(Peers1);

        buttonGroup10.add(Peers2);
        Peers2.setText("2");
        Peers2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Peers2ActionPerformed(evt);
            }
        });
        PeersMenu.add(Peers2);

        buttonGroup10.add(Peers3);
        Peers3.setText("3");
        Peers3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Peers3ActionPerformed(evt);
            }
        });
        PeersMenu.add(Peers3);

        buttonGroup10.add(Peers4);
        Peers4.setText("4");
        Peers4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Peers4ActionPerformed(evt);
            }
        });
        PeersMenu.add(Peers4);

        buttonGroup10.add(Peers5);
        Peers5.setText("5");
        Peers5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Peers5ActionPerformed(evt);
            }
        });
        PeersMenu.add(Peers5);

        buttonGroup10.add(Peers6);
        Peers6.setText("6");
        Peers6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Peers6ActionPerformed(evt);
            }
        });
        PeersMenu.add(Peers6);

        buttonGroup10.add(Peers7);
        Peers7.setText("7");
        Peers7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Peers7ActionPerformed(evt);
            }
        });
        PeersMenu.add(Peers7);

        OptionsMenu.add(PeersMenu);

        IDMenu.setText("ID");

        buttonGroup1.add(ID0);
        ID0.setText("0 ");
        ID0.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ID0ActionPerformed(evt);
            }
        });
        IDMenu.add(ID0);

        buttonGroup1.add(ID1);
        ID1.setText("1");
        ID1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ID1ActionPerformed(evt);
            }
        });
        IDMenu.add(ID1);

        buttonGroup1.add(ID2);
        ID2.setText("2");
        ID2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ID2ActionPerformed(evt);
            }
        });
        IDMenu.add(ID2);

        buttonGroup1.add(ID3);
        ID3.setText("3");
        ID3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ID3ActionPerformed(evt);
            }
        });
        IDMenu.add(ID3);

        buttonGroup1.add(ID4);
        ID4.setText("4");
        IDMenu.add(ID4);

        buttonGroup1.add(ID5);
        ID5.setText("5");
        IDMenu.add(ID5);

        buttonGroup1.add(ID6);
        ID6.setText("6");
        IDMenu.add(ID6);

        OptionsMenu.add(IDMenu);

        CommType.setText("Comm type");

        buttonGroup3.add(CausalOrder);
        CausalOrder.setText("3-Phase Total Order");
        CausalOrder.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CausalOrderActionPerformed(evt);
            }
        });
        CommType.add(CausalOrder);

        buttonGroup3.add(TotalOrderToken);
        TotalOrderToken.setText("Jupiter");
        TotalOrderToken.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TotalOrderTokenActionPerformed(evt);
            }
        });
        CommType.add(TotalOrderToken);

        buttonGroup3.add(TotalOrderServer);
        TotalOrderServer.setText("Total Order (central server)");
        TotalOrderServer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TotalOrderServerActionPerformed(evt);
            }
        });
        CommType.add(TotalOrderServer);

        OptionsMenu.add(CommType);

        MenuBar.add(OptionsMenu);

        ConnectToMenu.setText("Connect to");

        Peer1.setText("Peer 1");

        buttonGroup2.add(Peer1Server);
        Peer1Server.setText("as Server");
        Peer1.add(Peer1Server);

        buttonGroup2.add(Peer1Client);
        Peer1Client.setText("as Client");
        Peer1.add(Peer1Client);

        ConnectToMenu.add(Peer1);

        Peer2.setText("Peer 2");

        buttonGroup4.add(Peer2Server);
        Peer2Server.setText("as Server");
        Peer2.add(Peer2Server);

        buttonGroup4.add(Peer2Client);
        Peer2Client.setText("as Client");
        Peer2.add(Peer2Client);

        ConnectToMenu.add(Peer2);

        Peer3.setText("Peer 3");

        buttonGroup5.add(Peer3Server);
        Peer3Server.setText("as Server");
        Peer3.add(Peer3Server);

        buttonGroup5.add(Peer3Client);
        Peer3Client.setText("as Client");
        Peer3.add(Peer3Client);

        ConnectToMenu.add(Peer3);

        Peer4.setText("Peer 4");

        buttonGroup6.add(Peer4Server);
        Peer4Server.setText("as Server");
        Peer4.add(Peer4Server);

        buttonGroup6.add(Peer4Client);
        Peer4Client.setText("as Client");
        Peer4.add(Peer4Client);

        ConnectToMenu.add(Peer4);

        Peer5.setText("Peer 5");

        buttonGroup7.add(Peer5Server);
        Peer5Server.setText("as Server");
        Peer5.add(Peer5Server);

        buttonGroup7.add(Peer5Client);
        Peer5Client.setText("as Client");
        Peer5.add(Peer5Client);

        ConnectToMenu.add(Peer5);

        Peer6.setText("Peer 6");

        buttonGroup8.add(Peer6Server);
        Peer6Server.setText("as Server");
        Peer6.add(Peer6Server);

        buttonGroup8.add(Peer6Client);
        Peer6Client.setText("as Client");
        Peer6.add(Peer6Client);

        ConnectToMenu.add(Peer6);

        MenuBar.add(ConnectToMenu);

        setJMenuBar(MenuBar);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane1))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(19, 19, 19)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(StartComm)
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(TestButton)
                                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(sent, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(received, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                            .addComponent(Status, javax.swing.GroupLayout.PREFERRED_SIZE, 433, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(StartComm))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(TestButton)
                            .addComponent(sent))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButton1)
                            .addComponent(received))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(Status, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 278, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void ID1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ID1ActionPerformed
        // TODO add your handling code here:
        ID = 1;
    }//GEN-LAST:event_ID1ActionPerformed

    private void TotalOrderServerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TotalOrderServerActionPerformed
        // TODO add your handling code here:
        type = "totalOrderServer";
    }//GEN-LAST:event_TotalOrderServerActionPerformed
    
    // public method called by the communication protocol for the whole text
    public void TextboxUpdate(String s, int position){
        this.updateFromUser = false;
        this.Textbox.setText(s);
        this.Textbox.setCaretPosition(position);
        lastPosition = position;
        lastDimension = s.length();
        this.updateFromUser = true;
    }

    // public method called by the communication protocol for one command only
    public void TextboxUpdateLocal(String command, char c, int position){        
        this.updateFromUser = false;
        String s = this.Textbox.getText();
        String aux1 = s.substring(0, position);
        String aux2 = s.substring(position+1, s.length());
        if(command == "insert"){
            aux2 = c + aux2;
            position++;
        }
        s = aux1 + aux2;
                
        this.Textbox.setText(s);
        this.Textbox.setCaretPosition(position);
        lastPosition = position;
        lastDimension = s.length();
        this.updateFromUser = true;
    }
    
    private void TextboxCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_TextboxCaretUpdate
        // TODO add your handling code here:
        if (updateFromUser){
        String text="blabla";
        int position;
        char c = ' ';
        int dimension = 0;
        String command="arrow";
        try{
        //text = Textbox.getDocument().getText(0, Textbox.getDocument().getEndPosition().getOffset());
        text = Textbox.getText();
        position = Textbox.getCaretPosition();
        if(position < 0) position = 0;
        if(text.length() < lastDimension) 
            command = "delete"; //d"+position;
        else if(text.length() > lastDimension){
            command = "insert";
            c = Textbox.getText(position-1, 1).charAt(0);
            position--;
        }
            //command = "i" + position + Textbox.getText(position-1, 1);

        if(command.compareTo("arrow") != 0)
            comm.submitCommandFromGUI(command, c, position);
        lastPosition = position;
        lastDimension = text.length();
        }
        catch(Exception e) { e.printStackTrace(); }
        
        }
        else { // if update from communication module
            lastPosition = Textbox.getCaretPosition();
            lastDimension = Textbox.getText().length();
        }
    }//GEN-LAST:event_TextboxCaretUpdate

    private void StartCommActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_StartCommActionPerformed
        // TODO add your handling code here:
        if( ID<0 || type.length()==0 || PEERS<0)
            Status.setText("Error! Set first the total number of peers, an ID and the communication type");  
        else if (ID >= PEERS)
            Status.setText("Error! ID should be a number between 0 and PEERS-1"); 
        else {
            Status.setText("trying to establish connection with other peers");
            comm = new Communication(this, type, ID, this.PEERS);     
            comm.start();
            //Status.setText("Connection established with everyone");
        }
    }//GEN-LAST:event_StartCommActionPerformed

    private void ID0ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ID0ActionPerformed
        // TODO add your handling code here:
        ID = 0;
    }//GEN-LAST:event_ID0ActionPerformed

    private void ID2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ID2ActionPerformed
        // TODO add your handling code here:
        ID = 2;
    }//GEN-LAST:event_ID2ActionPerformed

    private void ID3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ID3ActionPerformed
        // TODO add your handling code here:
        ID = 3;
    }//GEN-LAST:event_ID3ActionPerformed

    private void CausalOrderActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CausalOrderActionPerformed
        // TODO add your handling code here:
        type = "3phase";
    }//GEN-LAST:event_CausalOrderActionPerformed

    private void TotalOrderTokenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TotalOrderTokenActionPerformed
        // TODO add your handling code here:
        type = "Jupiter";
    }//GEN-LAST:event_TotalOrderTokenActionPerformed

    private void Peers1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Peers1ActionPerformed
        // TODO add your handling code here:
        PEERS = 1;
    }//GEN-LAST:event_Peers1ActionPerformed

    private void Peers2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Peers2ActionPerformed
        // TODO add your handling code here:
        PEERS = 2;
    }//GEN-LAST:event_Peers2ActionPerformed

    private void Peers3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Peers3ActionPerformed
        // TODO add your handling code here:
        PEERS = 3;
    }//GEN-LAST:event_Peers3ActionPerformed

    private void Peers4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Peers4ActionPerformed
        // TODO add your handling code here:
        PEERS = 4;
    }//GEN-LAST:event_Peers4ActionPerformed

    private void Peers5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Peers5ActionPerformed
        // TODO add your handling code here:
        PEERS = 5;
    }//GEN-LAST:event_Peers5ActionPerformed

    private void Peers6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Peers6ActionPerformed
        // TODO add your handling code here:
        PEERS = 6;
    }//GEN-LAST:event_Peers6ActionPerformed

    private void Peers7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Peers7ActionPerformed
        // TODO add your handling code here:
        PEERS = 7;
    }//GEN-LAST:event_Peers7ActionPerformed

    private void TestButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TestButtonActionPerformed
        // TODO add your handling code here:
        this.test = new Test(this.comm);
        this.test.start();
    }//GEN-LAST:event_TestButtonActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        try {
            // TODO add your handling code here:
            
            this.test.interrupt();
            //this.test.join();
        } catch (Exception ex) {
            Logger.getLogger(GUI.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    public void updateStatus(String s){
        Status.setText("Status: "+s);   
    }
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(GUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(GUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(GUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(GUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new GUI().setVisible(true);
            }
        });
    }
    
    int PEERS = -1;
    int ID = -1;
    String type = "";
    Communication comm;
    Test test;
    int lastDimension;
    int lastPosition;
    boolean updateFromUser = true;
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JRadioButtonMenuItem CausalOrder;
    private javax.swing.JMenu CommType;
    private javax.swing.JMenu ConnectToMenu;
    private javax.swing.JRadioButtonMenuItem ID0;
    private javax.swing.JRadioButtonMenuItem ID1;
    private javax.swing.JRadioButtonMenuItem ID2;
    private javax.swing.JRadioButtonMenuItem ID3;
    private javax.swing.JRadioButtonMenuItem ID4;
    private javax.swing.JRadioButtonMenuItem ID5;
    private javax.swing.JRadioButtonMenuItem ID6;
    private javax.swing.JMenu IDMenu;
    private javax.swing.JMenuBar MenuBar;
    private javax.swing.JMenu OptionsMenu;
    private javax.swing.JMenu Peer1;
    private javax.swing.JRadioButtonMenuItem Peer1Client;
    private javax.swing.JRadioButtonMenuItem Peer1Server;
    private javax.swing.JMenu Peer2;
    private javax.swing.JRadioButtonMenuItem Peer2Client;
    private javax.swing.JRadioButtonMenuItem Peer2Server;
    private javax.swing.JMenu Peer3;
    private javax.swing.JRadioButtonMenuItem Peer3Client;
    private javax.swing.JRadioButtonMenuItem Peer3Server;
    private javax.swing.JMenu Peer4;
    private javax.swing.JRadioButtonMenuItem Peer4Client;
    private javax.swing.JRadioButtonMenuItem Peer4Server;
    private javax.swing.JMenu Peer5;
    private javax.swing.JRadioButtonMenuItem Peer5Client;
    private javax.swing.JRadioButtonMenuItem Peer5Server;
    private javax.swing.JMenu Peer6;
    private javax.swing.JRadioButtonMenuItem Peer6Client;
    private javax.swing.JRadioButtonMenuItem Peer6Server;
    private javax.swing.JRadioButtonMenuItem Peers1;
    private javax.swing.JRadioButtonMenuItem Peers2;
    private javax.swing.JRadioButtonMenuItem Peers3;
    private javax.swing.JRadioButtonMenuItem Peers4;
    private javax.swing.JRadioButtonMenuItem Peers5;
    private javax.swing.JRadioButtonMenuItem Peers6;
    private javax.swing.JRadioButtonMenuItem Peers7;
    private javax.swing.JMenu PeersMenu;
    private javax.swing.JButton StartComm;
    private javax.swing.JLabel Status;
    private javax.swing.JButton TestButton;
    private javax.swing.JTextArea Textbox;
    private javax.swing.JRadioButtonMenuItem TotalOrderServer;
    private javax.swing.JRadioButtonMenuItem TotalOrderToken;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.ButtonGroup buttonGroup10;
    private javax.swing.ButtonGroup buttonGroup2;
    private javax.swing.ButtonGroup buttonGroup3;
    private javax.swing.ButtonGroup buttonGroup4;
    private javax.swing.ButtonGroup buttonGroup5;
    private javax.swing.ButtonGroup buttonGroup6;
    private javax.swing.ButtonGroup buttonGroup7;
    private javax.swing.ButtonGroup buttonGroup8;
    private javax.swing.ButtonGroup buttonGroup9;
    private javax.swing.JButton jButton1;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenu jMenu5;
    private javax.swing.JMenu jMenu7;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JRadioButtonMenuItem jRadioButtonMenuItem1;
    private javax.swing.JRadioButtonMenuItem jRadioButtonMenuItem2;
    private javax.swing.JRadioButtonMenuItem jRadioButtonMenuItem3;
    private javax.swing.JRadioButtonMenuItem jRadioButtonMenuItem7;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel received;
    private javax.swing.JLabel sent;
    // End of variables declaration//GEN-END:variables

    int receivedBytes = 0;
    int sentBytes = 0;
    
    public void updateReceivedBytes(int size) {
        this.receivedBytes += size;
        this.received.setText("Bytes Received:" + this.receivedBytes);        
    }
    
    void updateSentBytes(int size) {
        this.sentBytes += size;
        this.sent.setText("Bytes Sent:" + this.sentBytes);
    }
}