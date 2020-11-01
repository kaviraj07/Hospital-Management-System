package hospital.management.system;

import java.awt.Color;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.simple.parser.ParseException;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Evans Henri
 */
public class ENTPatient extends javax.swing.JFrame {

    public ArrayList<HashMap> docs = new ArrayList<HashMap>();

    public String server_address = environment.server_address;
    public int port = environment.port;
    
    String fname, lname, p_id, stid;

    String doc_id = "";

    /**
     * Creates new form Receptionist
     */
    public ENTPatient(String fname, String lname, String pid, String stid) throws ParseException, JSONException, ClassNotFoundException, SQLException {
        initComponents();
        //jLabel2.setBackground(new Color(0, 51, 153, 180));
        jPanel1.setBackground(new Color(255, 255, 255, 180));
        jLabel14.setBackground(new Color(0, 51, 153, 180));
        jLabel2.setBackground(new Color(0, 51, 153, 180));

        this.fname = fname;
        this.lname = lname;
        this.p_id = pid;
        this.stid = stid;

        try {

            patientFirstNameField.setText(fname);
            patientLastNameField.setText(lname);
            getCheckup();
            getENTDoctors();
        } catch (Exception e) {

            JOptionPane.showMessageDialog(null, e);

        }

    }

    public void getCheckup() {
        // String treatmentDep = "ENT";
        // String patient_Id = patientLastNameField.getText();
        // String specialistId = specialistIdField.getText();
        // String diagnosis = diagnosisField.getText();
        //String treatment = treatmentField.getText();

        try {
            DatagramSocket clientSocket = new DatagramSocket();
            InetAddress IPAddress = InetAddress.getByName(server_address);

            byte[] send_Data = new byte[1024];
            byte[] receive_Data = new byte[1024];

            JSONObject objData = new JSONObject();
            objData.put("specialtreatment_id", stid);

            JSONArray dataArr = new JSONArray();
            dataArr.put(objData);

            JSONObject wrapperObj = new JSONObject();
            wrapperObj.put("action", "get_checkup_of_specialtreatment");
            wrapperObj.put("data", dataArr);

            String requestString = wrapperObj.toString();

            Arrays.fill(send_Data, (byte) 0);
            send_Data = requestString.getBytes();

            DatagramPacket sendPacket = new DatagramPacket(send_Data, send_Data.length, IPAddress, port);
            clientSocket.send(sendPacket);

            DatagramPacket receivePacket = new DatagramPacket(receive_Data, receive_Data.length);
            clientSocket.receive(receivePacket);
            String responseString = new String(receivePacket.getData());
            System.out.print(responseString);
            JSONArray responseArr = new JSONArray(responseString);
            JSONObject responseObj = responseArr.getJSONObject(0);
            diagnosisField.setText(responseObj.getString("diagnosis"));
            clientSocket.close();

        } catch (Exception e) {

            JOptionPane.showMessageDialog(null, e);

        }

    }

    public void getENTDoctors() {
        spec_dropdown.addItem("->Select Doctor<-");

        try {
            DatagramSocket clientSocket = new DatagramSocket();
            InetAddress IPAddress = InetAddress.getByName(server_address);

            byte[] send_Data = new byte[1024];
            byte[] receive_Data = new byte[1024];

            JSONObject objData = new JSONObject();
            objData.put("departmentid", "1");

            JSONArray dataArr = new JSONArray();
            dataArr.put(objData);

            JSONObject wrapperObj = new JSONObject();
            wrapperObj.put("action", "get_all_doctors");
            wrapperObj.put("data", dataArr);

            String requestString = wrapperObj.toString();

            Arrays.fill(send_Data, (byte) 0);
            send_Data = requestString.getBytes();

            DatagramPacket sendPacket = new DatagramPacket(send_Data, send_Data.length, IPAddress, port);
            clientSocket.send(sendPacket);

            DatagramPacket receivePacket = new DatagramPacket(receive_Data, receive_Data.length);
            clientSocket.receive(receivePacket);
            String responseString = new String(receivePacket.getData());
            System.out.print(responseString);

            JSONArray responseArr = new JSONArray(responseString);

            for (int i = 0; i < responseArr.length(); i++) {

                JSONObject o = responseArr.getJSONObject(i);
                HashMap<String, String> doctor = new HashMap<String, String>();

                String fname = o.getString("fname");
                String lname = o.getString("lname");
                String id = o.getString("id");
                String fullName = fname + " " + lname;

                doctor.put("name", fullName);
                doctor.put("id", id);

                spec_dropdown.addItem(fullName);

                docs.add(doctor);

            }

            //JSONObject responseObj = responseArr.getJSONObject(0);
            clientSocket.close();

        } catch (Exception e) {

            JOptionPane.showMessageDialog(null, e);

        }

    }

    public void saveData() {
        String selected_doc_name = spec_dropdown.getSelectedItem().toString();

        for (int i = 0; i < docs.size(); i++) {
            if (selected_doc_name.trim() == docs.get(i).get("name")) {
                doc_id = docs.get(i).get("id").toString();
            }
        }

        try {
            DatagramSocket clientSocket = new DatagramSocket();
            InetAddress IPAddress = InetAddress.getByName(server_address);

            byte[] send_Data = new byte[1024];
            byte[] receive_Data = new byte[1024];

            JSONObject objData = new JSONObject();
            objData.put("specialtreatment_id", stid);
            objData.put("specialist_id", doc_id);
            objData.put("status", "Complete");
            objData.put("given_treatment", treatmentField.getText());

            JSONArray dataArr = new JSONArray();
            dataArr.put(objData);

            JSONObject wrapperObj = new JSONObject();
            wrapperObj.put("action", "update_specialtreatment");
            wrapperObj.put("data", dataArr);

            String requestString = wrapperObj.toString();

            Arrays.fill(send_Data, (byte) 0);
            send_Data = requestString.getBytes();

            DatagramPacket sendPacket = new DatagramPacket(send_Data, send_Data.length, IPAddress, port);
            clientSocket.send(sendPacket);

            /* DatagramPacket receivePacket = new DatagramPacket(receive_Data, receive_Data.length);
            clientSocket.receive(receivePacket);
            String responseString = new String(receivePacket.getData());
           // System.out.print(responseString);
            JSONArray responseArr = new JSONArray(responseString);
            JSONObject responseObj = responseArr.getJSONObject(0);
            diagnosisField.setText(responseObj.getString("diagnosis"));*/
            clientSocket.close();
            JOptionPane.showMessageDialog(null, "Saved !");
            
        } catch (Exception e) {

            JOptionPane.showMessageDialog(null, e);

        }

    }

    /* public void getENTDoctors(){
        
        try {
            DatagramSocket clientSocket = new DatagramSocket();
            InetAddress IPAddress = InetAddress.getByName(server_address);

            byte[] send_Data = new byte[1024];
            byte[] receive_Data = new byte[1024];

            JSONObject objData = new JSONObject();
            objData.put("departmentid", "1");
           // objData.put("specialist_id", specialistIdField.getText());
            //objData.put("status", "Complete");
            //objData.put("given_treatment", treatmentField.getText());

            JSONArray dataArr = new JSONArray();
            dataArr.put(objData);

            JSONObject wrapperObj = new JSONObject();
            wrapperObj.put("action", "get_all_doctors");
            wrapperObj.put("data", dataArr);

            String requestString = wrapperObj.toString();

            Arrays.fill(send_Data, (byte) 0);
            send_Data = requestString.getBytes();

            DatagramPacket sendPacket = new DatagramPacket(send_Data, send_Data.length, IPAddress, 81);
            clientSocket.send(sendPacket);

            DatagramPacket receivePacket = new DatagramPacket(receive_Data, receive_Data.length);
            clientSocket.receive(receivePacket);
            String responseString = new String(receivePacket.getData());
            
            JSONArray responseArr = new JSONArray(responseString);
            Object[] row;

            for (int i = 0; i < responseArr.length(); i++) {

                row = new Object[];
                JSONObject o = responseArr.getJSONObject(i);
                row[0] = o.getString("fname");
                row[1] = o.getString("lname");
                row[2] = o.getString("address");
                row[3] = o.getString("gender");
                row[4] = o.getString("phone");
                row[5] = o.getString("dob");
                row[6] = o.getString("specialtreatment_id");
                //model.addRow(row);

            }

        } catch (Exception e) {

            JOptionPane.showMessageDialog(null, e);

        }
        
    }*/
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        jLabel14 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        patientLastNameField = new javax.swing.JTextField();
        pid = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        save = new javax.swing.JButton();
        clear = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        diagnosisField = new javax.swing.JTextArea();
        jScrollPane2 = new javax.swing.JScrollPane();
        treatmentField = new javax.swing.JTextArea();
        jLabel6 = new javax.swing.JLabel();
        patientFirstNameField = new javax.swing.JTextField();
        patientStatField = new javax.swing.JTextField();
        spec_dropdown = new javax.swing.JComboBox<>();
        jLabel2 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Hospital System");
        setMinimumSize(new java.awt.Dimension(400, 300));
        setUndecorated(true);
        setResizable(false);

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setMinimumSize(new java.awt.Dimension(800, 600));
        jPanel2.setPreferredSize(new java.awt.Dimension(800, 600));
        jPanel2.setLayout(null);

        jLabel14.setBackground(new java.awt.Color(0, 51, 153));
        jLabel14.setFont(new java.awt.Font("Tahoma", 0, 36)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(255, 255, 255));
        jLabel14.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel14.setText("Hospital Management System");
        jLabel14.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jLabel14.setOpaque(true);
        jPanel2.add(jLabel14);
        jLabel14.setBounds(160, 10, 510, 70);

        jPanel1.setBackground(new java.awt.Color(204, 204, 204));
        jPanel1.setForeground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel4.setBackground(new java.awt.Color(204, 204, 204));
        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(0, 51, 153));
        jLabel4.setText("Specialist: ");
        jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 30, -1, -1));

        jLabel5.setBackground(new java.awt.Color(0, 51, 153));
        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(0, 51, 153));
        jLabel5.setText("Patient Last name:");
        jPanel1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 100, -1, -1));

        patientLastNameField.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        patientLastNameField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                patientLastNameFieldActionPerformed(evt);
            }
        });
        jPanel1.add(patientLastNameField, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 100, 460, -1));

        pid.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        pid.setForeground(new java.awt.Color(0, 51, 153));
        pid.setText("Diagnosis:");
        jPanel1.add(pid, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 140, -1, -1));

        jLabel7.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(0, 51, 153));
        jLabel7.setText("Given Treatment:");
        jPanel1.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 260, -1, -1));

        save.setText("Save");
        save.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 51, 153), 1, true));
        save.setBorderPainted(false);
        save.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        save.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saveActionPerformed(evt);
            }
        });
        jPanel1.add(save, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 420, 100, 20));

        clear.setText("Back");
        clear.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 51, 153), 1, true));
        clear.setBorderPainted(false);
        clear.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        clear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                clearActionPerformed(evt);
            }
        });
        jPanel1.add(clear, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 420, 100, 20));

        diagnosisField.setColumns(20);
        diagnosisField.setRows(5);
        jScrollPane1.setViewportView(diagnosisField);

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 140, 460, -1));

        treatmentField.setColumns(20);
        treatmentField.setRows(5);
        jScrollPane2.setViewportView(treatmentField);

        jPanel1.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 250, 460, 110));

        jLabel6.setBackground(new java.awt.Color(0, 51, 153));
        jLabel6.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(0, 51, 153));
        jLabel6.setText("Patient First name:");
        jPanel1.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 60, -1, -1));

        patientFirstNameField.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        patientFirstNameField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                patientFirstNameFieldActionPerformed(evt);
            }
        });
        jPanel1.add(patientFirstNameField, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 60, 460, -1));

        patientStatField.setText("");
        jPanel1.add(patientStatField, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 380, 460, -1));

        spec_dropdown.setSelectedItem("select doctor");
        spec_dropdown.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                spec_dropdownActionPerformed(evt);
            }
        });
        jPanel1.add(spec_dropdown, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 30, 330, -1));

        jPanel2.add(jPanel1);
        jPanel1.setBounds(40, 100, 720, 480);

        jLabel2.setBackground(new java.awt.Color(0, 51, 153));
        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("ENT Patient Profile");
        jLabel2.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jLabel2.setOpaque(true);
        jPanel2.add(jLabel2);
        jLabel2.setBounds(160, 70, 510, 30);

        jPanel4.setBackground(new java.awt.Color(0, 51, 153));

        jLabel3.setFont(new java.awt.Font("Dialog", 0, 24)); // NOI18N
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("X");
        jLabel3.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel3.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jLabel3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel3MouseClicked(evt);
            }
        });
        jPanel4.add(jLabel3);

        jPanel2.add(jPanel4);
        jPanel4.setBounds(820, 0, 50, 40);

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Picture_icon/entbg.jpg"))); // NOI18N
        jLabel1.setText("jLabel1");
        jPanel2.add(jLabel1);
        jLabel1.setBounds(0, -40, 870, 650);

        getContentPane().add(jPanel2, java.awt.BorderLayout.CENTER);

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void patientLastNameFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_patientLastNameFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_patientLastNameFieldActionPerformed

    private void saveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_saveActionPerformed

        saveData();

    }//GEN-LAST:event_saveActionPerformed

    private void clearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_clearActionPerformed

        ListOfENTPatients o;
        // o = new ListOfENTPatients();
        // o.setVisible(true);

        // setVisible(false); //you can't see me!
        this.dispose();
        //  }

        // catch (ClassNotFoundException ex) {
        //   Logger.getLogger(ENTPatient.class.getName()).log(Level.SEVERE, null, ex);
        //} catch (SQLException ex) {
        // Logger.getLogger(ENTPatient.class.getName()).log(Level.SEVERE, null, ex);
        // } catch (JSONException ex) {
        //   Logger.getLogger(ENTPatient.class.getName()).log(Level.SEVERE, null, ex);

    }//GEN-LAST:event_clearActionPerformed

    private void patientFirstNameFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_patientFirstNameFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_patientFirstNameFieldActionPerformed

    private void jLabel3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel3MouseClicked
        // TODO add your handling code here:
        System.exit(0);
    }//GEN-LAST:event_jLabel3MouseClicked

    private void spec_dropdownActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_spec_dropdownActionPerformed
        // TODO add your handling code here:


    }//GEN-LAST:event_spec_dropdownActionPerformed
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
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    new ENTPatient("d", "d", "d", "d").setVisible(true);
                } catch (ParseException ex) {
                    Logger.getLogger(ENTPatient.class.getName()).log(Level.SEVERE, null, ex);
                } catch (JSONException ex) {
                    Logger.getLogger(ENTPatient.class.getName()).log(Level.SEVERE, null, ex);
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(ENTPatient.class.getName()).log(Level.SEVERE, null, ex);
                } catch (SQLException ex) {
                    Logger.getLogger(ENTPatient.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }
//public static void main(String args[]) {
    /* Set the Nimbus look and feel */
    //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
    /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
     */
    //  try {
    // for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
    // if ("Nimbus".equals(info.getName())) {
    //  javax.swing.UIManager.setLookAndFeel(info.getClassName());
    // break;
    // }
    // }
    //  } catch (ClassNotFoundException ex) {
    //    java.util.logging.Logger.getLogger(ENTPatient.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
    //  } catch (InstantiationException ex) {
    //      java.util.logging.Logger.getLogger(ENTPatient.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
    //   } catch (IllegalAccessException ex) {
    //      java.util.logging.Logger.getLogger(ENTPatient.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
    //   } catch (javax.swing.UnsupportedLookAndFeelException ex) {
    //   java.util.logging.Logger.getLogger(ENTPatient.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
    //  }
    //</editor-fold>
    //</editor-fold>

    /* Create and display the form */
    //java.awt.EventQueue.invokeLater(new Runnable() {
    // public void run() {
    //   try {
    // new ENTPatient().setVisible(true);
    // } catch (ParseException ex) {
    //      Logger.getLogger(ENTPatient.class.getName()).log(Level.SEVERE, null, ex);
    //   } catch (JSONException ex) {
    //       Logger.getLogger(ENTPatient.class.getName()).log(Level.SEVERE, null, ex);
    // } catch (ClassNotFoundException ex) {
    //       Logger.getLogger(ENTPatient.class.getName()).log(Level.SEVERE, null, ex);
    //  } catch (SQLException ex) {
    //Logger.getLogger(ENTPatient.class.getName()).log(Level.SEVERE, null, ex);
    //  }
    //  }
    // });
    // }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton clear;
    private javax.swing.JTextArea diagnosisField;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextField patientFirstNameField;
    private javax.swing.JTextField patientLastNameField;
    private javax.swing.JTextField patientStatField;
    private javax.swing.JLabel pid;
    private javax.swing.JButton save;
    private javax.swing.JComboBox<String> spec_dropdown;
    private javax.swing.JTextArea treatmentField;
    // End of variables declaration//GEN-END:variables

}
