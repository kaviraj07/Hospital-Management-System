/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hospital.management.system;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.time.LocalDate;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 * @author Kaviraj
 */
public class save_diagnostic {

    public JComboBox<String> specialtrtCombo;
    public JTextArea txtAreaDiagnosis;

    
    public String server_address = environment.server_address;
    public int port = environment.port;
    
    public String cid = "";

    //constructor
    public save_diagnostic(JTextArea txtAreaDiagnosis, JComboBox<String> specialtrtCombo, String cid) {
        this.txtAreaDiagnosis = txtAreaDiagnosis;
        this.specialtrtCombo = specialtrtCombo;
        this.cid = cid;
    }

    //used to check for empty diagnostic
    public boolean checkDiag() {
        String mydiagnosis = txtAreaDiagnosis.getText().trim();
        if (mydiagnosis.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Enter a diagnosis please !");
            return false;
        }
        return true;
    }

    //used to update the checkup status only
    public void updateChkUp() {
        // change status and diagnosis of a checkup
        String mydiagnosis = txtAreaDiagnosis.getText().trim();
        JSONObject dataObj = new JSONObject();
        JSONObject serviceObj = new JSONObject();
        JSONArray dataArr = new JSONArray();

        try {
            dataObj.put("checkupid", cid);
            dataObj.put("status", "complete");
            dataObj.put("diagnosis", mydiagnosis);
            dataArr.put(dataObj);

            serviceObj.put("action", "update_checkup");
            serviceObj.put("data", dataArr);

        } catch (JSONException ex) {
            Logger.getLogger(diagnosticScreen.class.getName()).log(Level.SEVERE, null, ex);
        }

        try {
            DatagramSocket clientSocket = new DatagramSocket();
            InetAddress IPAddress = InetAddress.getByName(server_address);
            byte[] sendData = new byte[1024];
            byte[] receiveData = new byte[1024];

            sendData = serviceObj.toString().getBytes();

            DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, IPAddress, port);
            clientSocket.send(sendPacket);

            //DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
            //clientSocket.receive(receivePacket);

            //String receivedResponse = new String(receivePacket.getData());
            //System.out.println("FROM SERVER:" + receivedResponse);

        } catch (SocketException ex) {
            Logger.getLogger(diagnosticScreen.class.getName()).log(Level.SEVERE, null, ex);
        } catch (UnknownHostException ex) {
            Logger.getLogger(diagnosticScreen.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(diagnosticScreen.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    //used to add the special treatment only
    public void addTreatment() {
        String spTrt = specialtrtCombo.getSelectedItem().toString();
        String deptId = "";

        if (!spTrt.equals(
                "None")) {

            switch (spTrt) {
                case "ENT":
                    deptId = "1";
                    break;
                case "X-Ray":
                    deptId = "1";
                    break;
                case "ICU":
                    deptId = "1";
                    break;
                case "MRI":
                    deptId = "1";
                    break;

            }

            //System.out.println(deptId);
            //JSON object/array initialisation
            JSONObject infoObj = new JSONObject();
            JSONObject sendObj = new JSONObject();
            JSONArray infoArr = new JSONArray();

            //get the actual date and convert tostring
            LocalDate myDate = LocalDate.now();
            String myDateStr = myDate.toString();

            DatagramSocket clientSocket;

            try {
                infoObj.put("departmentid", deptId);
                infoObj.put("checkupid", cid);
                infoObj.put("date", myDateStr);
                infoArr.put(infoObj);

                sendObj.put("action", "add_special_treatment");
                sendObj.put("data", infoArr);

                clientSocket = new DatagramSocket();
                InetAddress IPAddress = InetAddress.getByName(server_address);
                byte[] sendData = new byte[1024];
                byte[] receiveData = new byte[1024];

                sendData = sendObj.toString().getBytes();

                DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, IPAddress, port);
                clientSocket.send(sendPacket);

              /*  DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
                clientSocket.receive(receivePacket);

                String receivedResponse = new String(receivePacket.getData());
                System.out.println("FROM SERVER:" + receivedResponse);
*/
            } catch (JSONException ex) {
                Logger.getLogger(diagnosticScreen.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(diagnosticScreen.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
