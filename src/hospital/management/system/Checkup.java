package hospital.management.system;

import java.awt.Color;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.simple.parser.ParseException;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Evans Henri
 */
public class Checkup extends javax.swing.JFrame {

    public static String server_add = environment.server_address;

    /**
     * Creates new form Checkup
     */
    public static void createcheck_up() throws JSONException, ParseException {
        
        String server_address = server_add;
                

        try {
            DatagramSocket clientSocket = new DatagramSocket();
            InetAddress IPAddress = InetAddress.getByName(server_address);
            byte[] send_patient_Data = new byte[1024];
            byte[] send_doctor_Data = new byte[1024];

            byte[] receive_patient_Data = new byte[1024];
            byte[] receive_doctor_Data = new byte[1024];

            JSONObject patientdata = new JSONObject();
            JSONObject doctordata = new JSONObject();

            patientdata.put("action", "get_all_patients");

            JSONObject objData = new JSONObject();
            objData.put("departmentid", "2");

            JSONArray dataArr = new JSONArray();
            dataArr.put(objData);
            JSONObject wrapperObj = new JSONObject();
            wrapperObj.put("action", "get_all_doctors");
            wrapperObj.put("data", dataArr);

            doctordata.put("action", "get_all_doctors");

            String patientString = patientdata.toString();
            String requestString = wrapperObj.toString();

            Arrays.fill(send_patient_Data, (byte) 0);
            send_patient_Data = patientString.getBytes();

            Arrays.fill(send_doctor_Data, (byte) 0);
            send_doctor_Data = requestString.getBytes();

            DatagramPacket sendPacket = new DatagramPacket(send_patient_Data, send_patient_Data.length, IPAddress, 81);

            DatagramPacket sendPacket1 = new DatagramPacket(send_doctor_Data, send_doctor_Data.length, IPAddress, 81);

            clientSocket.send(sendPacket);
            clientSocket.send(sendPacket1);

            DatagramPacket receivePacket = new DatagramPacket(receive_patient_Data, receive_patient_Data.length);

            DatagramPacket receivePacket1 = new DatagramPacket(receive_doctor_Data, receive_doctor_Data.length);

            clientSocket.receive(receivePacket);
            clientSocket.receive(receivePacket1);

            String response = new String(receivePacket.getData());
            String response1 = new String(receivePacket1.getData());

            JSONArray a = new JSONArray(response);
            JSONArray b = new JSONArray(response1);

            DefaultTableModel model = (DefaultTableModel) patienttable.getModel();
            Object[] row;
            for (int i = 0; i < a.length(); i++) {

                row = new Object[6];
                JSONObject o = a.getJSONObject(i);
                row[0] = o.getString("id");
                row[1] = o.getString("fname");
                row[2] = o.getString("lname");
                row[3] = o.getString("gender");
                row[4] = o.getString("phone");
                row[5] = o.getString("dob");

                model.addRow(row);
            }

            DefaultTableModel model1 = (DefaultTableModel) doctortabledata.getModel();
            Object[] row1;
            for (int i = 0; i < b.length(); i++) {

                row1 = new Object[4];
                JSONObject ob = b.getJSONObject(i);
                row1[0] = ob.getString("id");
                row1[1] = ob.getString("fname");
                row1[2] = ob.getString("lname");
                row1[3] = ob.getString("gender");

                model1.addRow(row1);
            }

        } catch (IOException e) {
            System.out.println(e.toString());
        }

    }

    public Checkup() throws JSONException, ParseException {
        initComponents();
        jLabel2.setBackground(new Color(0, 51, 153, 180));
        backbtn.setBackground(new Color(0, 51, 153, 180));
        jPanel2.setBackground(new Color(255, 255, 255, 190));
        jLabel10.setBackground(new Color(0, 51, 153, 180));
        jLabel9.setBackground(new Color(0, 51, 153, 180));
        save.setBackground(new Color(0, 51, 153, 180));

        clear.setBackground(new Color(0, 51, 153, 180));
        patienttable.setBackground(new Color(255, 255, 255, 200));
        doctortabledata.setBackground(new Color(255, 255, 255, 200));

        LocalDate myDate = LocalDate.now();
        String myDateStr = myDate.toString();

        date.setText(myDateStr);

        createcheck_up();

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        patienttable = new javax.swing.JTable();
        jScrollPane2 = new javax.swing.JScrollPane();
        doctortabledata = new javax.swing.JTable();
        jPanel2 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        reason = new javax.swing.JComboBox<>();
        jLabel6 = new javax.swing.JLabel();
        save = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();
        date = new javax.swing.JTextField();
        pname = new javax.swing.JTextField();
        dname = new javax.swing.JTextField();
        clear = new javax.swing.JButton();
        backbtn = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(800, 600));
        setUndecorated(true);
        setResizable(false);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setMinimumSize(new java.awt.Dimension(800, 600));
        jPanel1.setName(""); // NOI18N
        jPanel1.setPreferredSize(new java.awt.Dimension(800, 600));
        jPanel1.setLayout(null);

        jLabel2.setBackground(new java.awt.Color(0, 51, 153));
        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 36)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("Hospital Management System");
        jLabel2.setOpaque(true);
        jPanel1.add(jLabel2);
        jLabel2.setBounds(250, 10, 520, 50);

        patienttable.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        patienttable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Patient ID", "FirstName", "LastName", "Gender", "Phone Number", "D.O.B"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        patienttable.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        patienttable.setSelectionBackground(new java.awt.Color(0, 51, 153));
        patienttable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                patienttableMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(patienttable);
        if (patienttable.getColumnModel().getColumnCount() > 0) {
            patienttable.getColumnModel().getColumn(0).setResizable(false);
            patienttable.getColumnModel().getColumn(1).setResizable(false);
            patienttable.getColumnModel().getColumn(2).setResizable(false);
            patienttable.getColumnModel().getColumn(3).setResizable(false);
            patienttable.getColumnModel().getColumn(4).setResizable(false);
            patienttable.getColumnModel().getColumn(5).setResizable(false);
        }

        jPanel1.add(jScrollPane1);
        jScrollPane1.setBounds(450, 150, 550, 120);

        doctortabledata.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        doctortabledata.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Doctor Id", "Firstname", "Lastname", "Gender"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        doctortabledata.setSelectionBackground(new java.awt.Color(0, 51, 153));
        doctortabledata.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                doctortabledataMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(doctortabledata);
        if (doctortabledata.getColumnModel().getColumnCount() > 0) {
            doctortabledata.getColumnModel().getColumn(0).setResizable(false);
            doctortabledata.getColumnModel().getColumn(1).setResizable(false);
            doctortabledata.getColumnModel().getColumn(2).setResizable(false);
            doctortabledata.getColumnModel().getColumn(3).setResizable(false);
        }

        jPanel1.add(jScrollPane2);
        jScrollPane2.setBounds(450, 360, 550, 70);

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(0, 51, 153));
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("Create Patient Checkup");

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(0, 0, 153));
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setText("Patient ID:");

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(0, 51, 153));
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel5.setText("Select Reason:");

        reason.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "---Select Reason---", "Heart Problem", "Pulmonary Problem", "Diabetes", "Infection.", "Hypertension", "Fever", "Diarrhea", "Influenza", "Bones Problem", "Headache", " " }));
        reason.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        reason.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                reasonActionPerformed(evt);
            }
        });

        jLabel6.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(0, 51, 153));
        jLabel6.setText("Doctor ID:");

        save.setForeground(new java.awt.Color(255, 255, 255));
        save.setText("Save Checkup");
        save.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 51, 153), 5, true));
        save.setBorderPainted(false);
        save.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        save.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saveActionPerformed(evt);
            }
        });

        jLabel7.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(0, 51, 153));
        jLabel7.setText("Date:");

        pname.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pnameActionPerformed(evt);
            }
        });

        clear.setForeground(new java.awt.Color(255, 255, 255));
        clear.setText("Clear");
        clear.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        clear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                clearActionPerformed(evt);
            }
        });

        backbtn.setForeground(new java.awt.Color(255, 255, 255));
        backbtn.setText("Back");
        backbtn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        backbtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                backbtnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(95, 95, 95)
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 198, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(31, 31, 31)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4)
                            .addComponent(jLabel5)
                            .addComponent(jLabel6)
                            .addComponent(jLabel7))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(pname)
                            .addComponent(reason, 0, 180, Short.MAX_VALUE)
                            .addComponent(dname)
                            .addComponent(date)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(82, 82, 82)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(backbtn, javax.swing.GroupLayout.DEFAULT_SIZE, 248, Short.MAX_VALUE)
                            .addComponent(clear, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(save, javax.swing.GroupLayout.DEFAULT_SIZE, 248, Short.MAX_VALUE))))
                .addGap(66, 66, 66))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 56, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(pname, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(39, 39, 39)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(reason, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(34, 34, 34)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(dname, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(34, 34, 34)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(date, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(40, 40, 40)
                .addComponent(clear)
                .addGap(18, 18, 18)
                .addComponent(save)
                .addGap(15, 15, 15)
                .addComponent(backbtn)
                .addContainerGap())
        );

        jPanel1.add(jPanel2);
        jPanel2.setBounds(30, 80, 410, 450);

        jPanel3.setBackground(new java.awt.Color(0, 51, 153));

        jLabel8.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel8.setText("x");
        jLabel8.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel8.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel8MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(jLabel8)
                .addGap(0, 1, Short.MAX_VALUE))
        );

        jPanel1.add(jPanel3);
        jPanel3.setBounds(960, 0, 40, 30);

        jLabel9.setBackground(new java.awt.Color(0, 51, 153));
        jLabel9.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel9.setText("Patient Details");
        jLabel9.setOpaque(true);
        jPanel1.add(jLabel9);
        jLabel9.setBounds(670, 100, 150, 22);

        jLabel10.setBackground(new java.awt.Color(0, 51, 153));
        jLabel10.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(255, 255, 255));
        jLabel10.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel10.setText("Doctor Details");
        jLabel10.setOpaque(true);
        jPanel1.add(jLabel10);
        jLabel10.setBounds(690, 310, 120, 22);

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Picture_icon/checkupbgedit.jpg"))); // NOI18N
        jPanel1.add(jLabel1);
        jLabel1.setBounds(0, 0, 1000, 600);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 1000, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        setSize(new java.awt.Dimension(1000, 600));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void saveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_saveActionPerformed

        if (pname.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Please select a patient!");

        } else if (reason.getSelectedItem().toString().contains("---Select Reason---")) {
            JOptionPane.showMessageDialog(null, "Please select a reason!");

        } else if (dname.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Please select a doctor!");

        } else if (date.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Please insert a date!");
        } else if (!date.getText().contains("-")) {
            JOptionPane.showMessageDialog(null, "Please insert following Format YYYY-MM-D");
        } else {
            try {
                send_checkup new_checkup = new send_checkup(pname.getText(), dname.getText(), reason.getSelectedItem().toString(), date.getText());
                new_checkup.send_patientData();
                JOptionPane.showMessageDialog(null, "Checkup successfully recorded!");
            } catch (JSONException ex) {
                Logger.getLogger(Checkup.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
    }//GEN-LAST:event_saveActionPerformed

    private void patienttableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_patienttableMouseClicked
        int i = patienttable.getSelectedRow();
        TableModel model = patienttable.getModel();
        pname.setText(model.getValueAt(i, 0).toString());
    }//GEN-LAST:event_patienttableMouseClicked

    private void doctortabledataMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_doctortabledataMouseClicked
        int i = doctortabledata.getSelectedRow();
        TableModel model = doctortabledata.getModel();
        dname.setText(model.getValueAt(i, 0).toString());
    }//GEN-LAST:event_doctortabledataMouseClicked

    private void clearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_clearActionPerformed
        pname.setText(null);
        dname.setText(null);
        date.setText(null);
        reason.setSelectedIndex(0);
    }//GEN-LAST:event_clearActionPerformed

    private void jLabel8MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel8MouseClicked
        System.exit(0);
    }//GEN-LAST:event_jLabel8MouseClicked

    private void reasonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_reasonActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_reasonActionPerformed

    private void pnameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pnameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_pnameActionPerformed

    private void backbtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_backbtnActionPerformed
        Receptionist rc = new Receptionist();
        rc.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_backbtnActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) throws JSONException, ParseException {
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
            java.util.logging.Logger.getLogger(Checkup.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Checkup.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Checkup.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Checkup.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {

                try {
                    new Checkup().setVisible(true);
                } catch (JSONException ex) {
                    Logger.getLogger(Checkup.class.getName()).log(Level.SEVERE, null, ex);
                } catch (ParseException ex) {
                    Logger.getLogger(Checkup.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });

    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton backbtn;
    private javax.swing.JButton clear;
    private javax.swing.JTextField date;
    private javax.swing.JTextField dname;
    private static javax.swing.JTable doctortabledata;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private static javax.swing.JTable patienttable;
    private javax.swing.JTextField pname;
    private javax.swing.JComboBox<String> reason;
    private javax.swing.JButton save;
    // End of variables declaration//GEN-END:variables
}
/*








 */
