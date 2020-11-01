package hospital.management.system;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Arrays;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class send_checkup {

    
    public String server_address = environment.server_address;
    public int port = environment.port;
    
    
    String patient_id;
    //String diagnosis;
    String doctor_id;
    String reason;
    String date;

    public send_checkup(String patient_id, String doctor_id, String reason, String date) {
        this.patient_id = patient_id;
        this.doctor_id = doctor_id;
//this.diagnosis=diagnosis;
        this.reason = reason;
        this.date = date;

    }

    public void send_patientData() throws JSONException {
        try {
            DatagramSocket clientSocket = new DatagramSocket();
            InetAddress IPAddress = InetAddress.getByName(server_address);
            byte[] send_Data = new byte[1024];
            byte[] receive_Data = new byte[1024];

            JSONObject clientJson = new JSONObject();

            clientJson.put("patientid", patient_id);
            clientJson.put("doctorid", doctor_id);
            //clientJson.put("diagnosis", diagnosis);
            clientJson.put("reason", reason);
            clientJson.put("date", date);

            JSONArray clientJsonArr = new JSONArray();
            clientJsonArr.put(clientJson);

            JSONObject finalObject = new JSONObject();
            finalObject.put("action", "add_checkup");
            finalObject.put("data", clientJsonArr);
            String clientString = finalObject.toString();
            System.out.println(clientString);
            Arrays.fill(send_Data, (byte) 0);
            send_Data = clientString.getBytes();

            DatagramPacket sendPacket = new DatagramPacket(send_Data, send_Data.length, IPAddress, port);
            clientSocket.send(sendPacket);
/*
            DatagramPacket receivePacket = new DatagramPacket(receive_Data, receive_Data.length);
            clientSocket.receive(receivePacket);
            String serverResponse = new String(receivePacket.getData());

            System.out.print("RESPONSE FROM SERVER: " + serverResponse);
*/
        } catch (IOException e) {
            System.out.println(e.toString());

        }

    }

}
