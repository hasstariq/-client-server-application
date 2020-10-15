/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.util.Map;
import javax.swing.JOptionPane;

public class MainFrame extends javax.swing.JFrame {

    public static ServerConnectionController scc;
    public Boolean running = true;
    public Thread clientThread;
    public static Boolean displayInBibTexFormat = false;
    /**
     * Creates new form MainFrame
     */
    public MainFrame(ServerConnectionController scc) {
        this.scc = scc;
        initComponents();
        clientThread = new Thread(){
            @Override
            public void run() {
                try{
                    while(running){
                        Integer action = (Integer) scc.readMessage();
                        jTextArea1.setText("");
                        switch(action){
                            case Constants.GET_MESSAGE_FROM_SERVER:
                                String message = (String) scc.readMessage();
                                jTextArea1.append("***************************************" + "\n");
                                jTextArea1.append(message + "\n");
                                jTextArea1.append("***************************************");
                            break;
                            case Constants.GET_MAP_FROM_SERVER:
                                    Integer mapSize = (Integer) scc.readMessage();
                                    if(mapSize != 0){
                                        for(int i=0; i<mapSize; i++){
                                            BibliographyBean bb = (BibliographyBean) scc.readMessage();
                                            if(!displayInBibTexFormat){
                                                jTextArea1.append("***************************************" + "\n");
                                                jTextArea1.append("ISBN: " + bb.getISBN() + "\n");
                                                jTextArea1.append("Title: " + bb.getTitle() + "\n");
                                                jTextArea1.append("Author: " + bb.getAuthor() + "\n");
                                                jTextArea1.append("Publisher: " + bb.getPublisher() + "\n");
                                                jTextArea1.append("Year: " + bb.getYear() + "\n");
                                                jTextArea1.append("***************************************");
                                            }else{
                                                jTextArea1.append("***************************************" + "\n");
                                                jTextArea1.append("@article{ " + bb.getISBN() + ",\n");
                                                jTextArea1.append("author = \"" + bb.getAuthor()+ "\",\n");
                                                jTextArea1.append("title = \"" + bb.getTitle() + "\",\n");
                                                jTextArea1.append("publisher = \"" + bb.getPublisher() + "\",\n");
                                                jTextArea1.append("year = " + bb.getYear() + ",\n");
                                                jTextArea1.append("}\n");
                                                jTextArea1.append("***************************************");
                                            }
                                        }
                                    }else{
                                        jTextArea1.append("***************************************" + "\n");
                                        jTextArea1.append(Constants.SERVER_ENTRIES_EMPTY + "\n");
                                        jTextArea1.append("***************************************");
                                    }
                            break;
                            case Constants.GET_SPECIFIC_ENTRIES_RESULT:
                                Map<String, BibliographyBean> queryMap = (Map<String, BibliographyBean>) scc.readMessage();
                                if(!queryMap.isEmpty()){
                                    queryMap.forEach((key, item)->{
                                        if(!displayInBibTexFormat){
                                            jTextArea1.append("***************************************" + "\n");
                                            jTextArea1.append("ISBN: " + item.getISBN() + "\n");
                                            jTextArea1.append("Title: " + item.getTitle() + "\n");
                                            jTextArea1.append("Author: " + item.getAuthor() + "\n");
                                            jTextArea1.append("Publisher: " + item.getPublisher() + "\n");
                                            jTextArea1.append("Year: " + item.getYear() + "\n");
                                            jTextArea1.append("***************************************");
                                        }else{
                                            jTextArea1.append("***************************************" + "\n");
                                            jTextArea1.append("@article{ " + item.getISBN() + ",\n");
                                            jTextArea1.append("author = \"" + item.getAuthor()+ "\",\n");
                                            jTextArea1.append("title = \"" + item.getTitle() + "\",\n");
                                            jTextArea1.append("publisher = \"" + item.getPublisher() + "\",\n");
                                            jTextArea1.append("year = " + item.getYear() + ",\n");
                                            jTextArea1.append("}\n");
                                            jTextArea1.append("***************************************");
                                        }
                                    });
                                }else{
                                    jTextArea1.append("***************************************" + "\n");
                                    jTextArea1.append(Constants.SERVER_ENTRIES_EMPTY + "\n");
                                    jTextArea1.append("***************************************");
                                }
                            break;
                            case Constants.REMOVE_SPECIFIC_ENTRIES_RESPONSE:
                                Integer entriesRemoved = (Integer) scc.readMessage();
                                jTextArea1.append("***************************************" + "\n");
                                jTextArea1.append(Constants.NUMBER_OF_ENTRIES_REMOVED + entriesRemoved + "\n");
                                jTextArea1.append("***************************************");
                            break;
                        }

                    }
                }catch(Exception ex){
                    System.out.println(Constants.CLIENT_DISCONNECTED);
                }
            }
        };
        clientThread.start();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setResizable(false);

        jTextArea1.setColumns(20);
        jTextArea1.setRows(5);
        jScrollPane1.setViewportView(jTextArea1);

        jButton1.setText("UPDATE");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("GET");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setText("SUBMIT");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton4.setText("DISCONNECT");
        jButton4.setToolTipText("");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jButton5.setText("REMOVE");
        jButton5.setMaximumSize(new java.awt.Dimension(97, 23));
        jButton5.setMinimumSize(new java.awt.Dimension(97, 23));
        jButton5.setPreferredSize(new java.awt.Dimension(97, 23));
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        jButton6.setText("GET ALL");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 428, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton6, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(21, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jButton6, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        new GetFrame().setVisible(true);
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        new RemoveFrame().setVisible(true);
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        this.running = false;
        this.scc.closeConnection();
        this.setVisible(Boolean.FALSE);
        this.dispose();
        new ConnectionFrame().setVisible(Boolean.TRUE);
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        new SubmitFrame().setVisible(Boolean.TRUE);
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        int choice = JOptionPane.showConfirmDialog(this,"Do you want to display in BibTex format?", "",
               JOptionPane.YES_NO_OPTION,
               JOptionPane.QUESTION_MESSAGE);
        if(choice == JOptionPane.YES_OPTION){
               displayInBibTexFormat = true;
        }else if (choice == JOptionPane.NO_OPTION){
               displayInBibTexFormat = false;
        }
        MainFrame.scc.sendMessage(Constants.RETRIEVE_ALL_ACTION);
    }//GEN-LAST:event_jButton6ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        new UpdateFrame().setVisible(true);
    }//GEN-LAST:event_jButton1ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea jTextArea1;
    // End of variables declaration//GEN-END:variables
}
