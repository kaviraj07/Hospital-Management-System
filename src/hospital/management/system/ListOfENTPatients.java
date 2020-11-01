package hospital.management.system;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import java.util.Timer;
import java.util.TimerTask;
import javax.swing.table.DefaultTableModel;
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
public class ListOfENTPatients extends javax.swing.JFrame {

    public String server_address = environment.server_address;
    public int port = environment.port;
    public int lport = environment.ent_port;
    
    
    String patientID;
    String specialTreatmentID;
    DatagramSocket clientSocket;
    InetAddress IPAddress;

    /**
     * Creates new form Checkup
     *
     * @throws java.lang.ClassNotFoundException
     * @throws java.sql.SQLException
     */
    public ListOfENTPatients() throws ClassNotFoundException, SQLException, JSONException, IOException {
        initComponents();
        // createSocket();
        identify();
        getPatients();

        Timer t = new Timer();
        t.schedule(new TimerTask() {
            @Override
            public void run() {
                try {
                    getNewPatients();
                } catch (IOException ex) {
                    Logger.getLogger(ListOfENTPatients.class.getName()).log(Level.SEVERE, null, ex);
                } catch (JSONException ex) {
                    Logger.getLogger(ListOfENTPatients.class.getName()).log(Level.SEVERE, null, ex);
                }

            }
        }, 0, 1000);

    }

    public void identify() throws JSONException, IOException {
        DatagramSocket clientSocket = new DatagramSocket();
        InetAddress IPAddress = InetAddress.getByName(server_address);
        byte[] send_Data = new byte[1024];
        // byte[] receive_Data = new byte[1024];

        //putting request parameters in JSONObject
        JSONObject objData = new JSONObject();
        objData.put("device_name", "ENT");

        JSONArray dataArr = new JSONArray();
        dataArr.put(objData);

        JSONObject wrapperObj = new JSONObject();
        wrapperObj.put("action", "identify_client");
        wrapperObj.put("data", dataArr);

        //converting object to string
        String requestString = wrapperObj.toString();

        Arrays.fill(send_Data, (byte) 0);
        send_Data = requestString.getBytes();

        //sending packet
        DatagramPacket sendPacket = new DatagramPacket(send_Data, send_Data.length, IPAddress, port);
        clientSocket.send(sendPacket);

        clientSocket.close();
    }

    public void getPatients() {
        try {
            clientSocket = new DatagramSocket();
            IPAddress = InetAddress.getByName(server_address);

            DefaultTableModel model = (DefaultTableModel) patient_table.getModel();
            model.setRowCount(0);

            byte[] send_Data = new byte[1024];
            byte[] receive_Data = new byte[1024];

            //putting request parameters in JSONObject
            JSONObject objData = new JSONObject();
            objData.put("dept_id", "1");
            objData.put("status", "incomplete");

            JSONArray dataArr = new JSONArray();
            dataArr.put(objData);

            JSONObject wrapperObj = new JSONObject();
            wrapperObj.put("action", "get_patients_for_specialtreatment");
            wrapperObj.put("data", dataArr);

            //converting object to string
            String requestString = wrapperObj.toString();

            Arrays.fill(send_Data, (byte) 0);
            send_Data = requestString.getBytes();

            //sending packet
            DatagramPacket sendPacket = new DatagramPacket(send_Data, send_Data.length, IPAddress, port);
            clientSocket.send(sendPacket);

            //receiving packet
            DatagramPacket receivePacket = new DatagramPacket(receive_Data, receive_Data.length);
            clientSocket.receive(receivePacket);
            String responseString = new String(receivePacket.getData());

            //parsing received data from string to object
            JSONArray responseArr = new JSONArray(responseString);

            //displaying data on UI
            Object[] row;

            for (int i = 0; i < responseArr.length(); i++) {

                row = new Object[7];
                JSONObject o = responseArr.getJSONObject(i);
                row[0] = o.getString("fname");
                row[1] = o.getString("lname");
                row[2] = o.getString("address");
                row[3] = o.getString("gender");
                row[4] = o.getString("phone");
                row[5] = o.getString("dob");
                row[6] = o.getString("specialtreatment_id");
                model.addRow(row);

            }
            clientSocket.close();

        } catch (Exception e) {

            JOptionPane.showMessageDialog(null, e);

        }

    }

    public void getNewPatients() throws IOException, JSONException {

        DatagramSocket serverSocket2 = new DatagramSocket(lport);
        byte[] receiveData2 = new byte[1024];

        DatagramPacket packet2 = new DatagramPacket(receiveData2, receiveData2.length);

        serverSocket2.receive(packet2);
        String responseString = new String(packet2.getData());

        if (responseString.trim().equals("success")) {
            getPatients();

        }

        serverSocket2.close();

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
        jScrollPane1 = new javax.swing.JScrollPane();
        patient_table = new javax.swing.JTable();
        cancel_btn = new javax.swing.JButton();
        editPatient_button = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(800, 600));
        setResizable(false);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setMinimumSize(new java.awt.Dimension(800, 600));
        jPanel1.setName(""); // NOI18N
        jPanel1.setPreferredSize(new java.awt.Dimension(800, 600));
        jPanel1.setLayout(null);

        patient_table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "First name", "Last name", "Address", "Gender", "Phone number", "DOB", "STID"
            }
        ));
        jScrollPane1.setViewportView(patient_table);

        jPanel1.add(jScrollPane1);
        jScrollPane1.setBounds(20, 140, 760, 250);

        cancel_btn.setText("Cancel");
        cancel_btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancel_btnActionPerformed(evt);
            }
        });
        jPanel1.add(cancel_btn);
        cancel_btn.setBounds(400, 440, 140, 25);

        editPatient_button.setText("Edit Patient");
        editPatient_button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                editPatient_buttonActionPerformed(evt);
            }
        });
        jPanel1.add(editPatient_button);
        editPatient_button.setBounds(240, 440, 140, 25);

        jLabel2.setBackground(new java.awt.Color(0, 51, 153));
        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("ENT Department");
        jLabel2.setOpaque(true);
        jPanel1.add(jLabel2);
        jLabel2.setBounds(140, 60, 520, 30);

        jLabel3.setBackground(new java.awt.Color(0, 51, 153));
        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 36)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("Hospital Management System");
        jLabel3.setOpaque(true);
        jPanel1.add(jLabel3);
        jLabel3.setBounds(140, 10, 520, 50);

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Picture_icon/checkupbg.jpg"))); // NOI18N
        jPanel1.add(jLabel1);
        jLabel1.setBounds(0, 0, 800, 600);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void editPatient_buttonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_editPatient_buttonActionPerformed
        // TODO add your handling code here:

        DefaultTableModel model = (DefaultTableModel) patient_table.getModel();
        int row = patient_table.getSelectedRow();
        String selectedPatientFname = model.getValueAt(row, 0).toString();
        String selectedPatientLname = model.getValueAt(row, 1).toString();
        String selectedPatientID = this.patientID;
        String selectedPatientSpecialTreatmentID = model.getValueAt(row, 6).toString();

        ENTPatient object;
        try {
            object = new ENTPatient(selectedPatientFname, selectedPatientLname, selectedPatientID, selectedPatientSpecialTreatmentID);
            object.setVisible(true);

            //setVisible(false); //you can't see me!
            //dispose();
        } catch (ParseException | JSONException | ClassNotFoundException | SQLException ex) {
            Logger.getLogger(ListOfENTPatients.class.getName()).log(Level.SEVERE, null, ex);
        }

    }//GEN-LAST:event_editPatient_buttonActionPerformed

    private void cancel_btnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancel_btnActionPerformed
        // TODO add your handling code here:
        //setVisible(false); //you can't see me!
        this.dispose();

    }//GEN-LAST:event_cancel_btnActionPerformed

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
            java.util.logging.Logger.getLogger(ListOfENTPatients.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ListOfENTPatients.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ListOfENTPatients.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ListOfENTPatients.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {
                try {
                    new ListOfENTPatients().setVisible(true);
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(ListOfENTPatients.class.getName()).log(Level.SEVERE, null, ex);
                } catch (SQLException ex) {
                    Logger.getLogger(ListOfENTPatients.class.getName()).log(Level.SEVERE, null, ex);
                } catch (JSONException ex) {
                    Logger.getLogger(ListOfENTPatients.class.getName()).log(Level.SEVERE, null, ex);
                } catch (IOException ex) {
                    Logger.getLogger(ListOfENTPatients.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton cancel_btn;
    private javax.swing.JButton editPatient_button;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable patient_table;
    // End of variables declaration//GEN-END:variables
}
