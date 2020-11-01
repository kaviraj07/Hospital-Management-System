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
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.simple.parser.JSONParser;

/**
 *
 * @author Kaviraj
 */
public class client_doctor {

    public String server_address = environment.server_address;
    public int port = environment.port;
    public int listening_port = environment.listening_port;
    
    public String doctor_id = "2";
    public JTable patientsTbl;

    //constructor
    public client_doctor(JTable patientsTbl) {
        this.patientsTbl = patientsTbl;
    }

    DefaultTableModel model;

    //updates table with values from UDP
    public void refresh() throws SocketException, UnknownHostException, IOException {
        JSONObject request = new JSONObject();
        JSONObject docDet = new JSONObject();
        JSONArray reqArr = new JSONArray();

        try {

            docDet.put("doctorid", doctor_id);
            docDet.put("status", "incomplete");
            reqArr.put(docDet);
            request.put("data", reqArr);
            request.put("action", "get_checkups_of_doctor");

            DatagramSocket clientSocket = new DatagramSocket();
            InetAddress IPAddress = InetAddress.getByName(server_address);
            byte[] sendData = new byte[1024];
            byte[] receiveData = new byte[1024];

            sendData = request.toString().getBytes();

            DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, IPAddress, port);
            clientSocket.send(sendPacket);

            DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
            clientSocket.receive(receivePacket);

            String receivedResponse = new String(receivePacket.getData());
            System.out.println("FROM SERVER:" + receivedResponse);

            JSONArray receivedJson = new JSONArray(receivedResponse);
            JSONParser parser = new JSONParser();
            model.setRowCount(0);
            for (int i = 0; i < receivedJson.length(); i++) {
                JSONObject o = receivedJson.getJSONObject(i);

                String data[] = {
                    o.getString("checkup_id"),
                    o.getString("checkup_date"),
                    o.getString("patient_lname"),
                    o.getString("patient_fname"),
                    o.getString("checkup_reason"),
                    o.getString("checkup_status")

                };

                DefaultTableModel patientmdl = (DefaultTableModel) patientsTbl.getModel();
                patientmdl.addRow(data);

            }

            clientSocket.close();

        } catch (JSONException ex) {
            Logger.getLogger(myPatients.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    //define table model
    public void setMyTable() {
        model = new DefaultTableModel();
        patientsTbl.setModel(model);
        model.addColumn("Checkup ID");
        model.addColumn("Date");
        model.addColumn("Surname");
        model.addColumn("Name");
        model.addColumn("Reason");
        model.addColumn("Status");

    }

    //Real Time update using local server - same network
    public void rt_updater() throws SocketException, UnknownHostException, IOException {
        DatagramSocket clientSocket = new DatagramSocket();

        //InetAddress IPAddress = InetAddress.getByName(local_address);
        InetAddress IPAddress = InetAddress.getByName(server_address);

        byte[] sendData = new byte[1024];

        //client identification for server
        String request = "{'action':'identify_client',data:[{'device_name':'General Doctor'}]}";

        sendData = request.toString().getBytes();

        DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, IPAddress, port);
        clientSocket.send(sendPacket);

        Timer t = new Timer();
        t.schedule(new TimerTask() {
            @Override
            public void run() {

                try {
                    DatagramSocket serverSocket2;
                    serverSocket2 = new DatagramSocket(listening_port);
                    byte[] receiveData2 = new byte[1024];
                    System.out.println("Client ready...");

                    DatagramPacket receivePacket2 = new DatagramPacket(receiveData2, receiveData2.length);
                    serverSocket2.receive(receivePacket2);
                    System.out.println("Client connected");

                    String sentence = new String(receivePacket2.getData());

                    if (sentence.trim().equals("success")) {
                        refresh();
                    }
                    System.out.println(sentence);
                    serverSocket2.close();

                } catch (SocketException ex) {
                    Logger.getLogger(myPatients.class.getName()).log(Level.SEVERE, null, ex);
                } catch (IOException ex) {
                    Logger.getLogger(myPatients.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }, 0, 1000);

    }

}
