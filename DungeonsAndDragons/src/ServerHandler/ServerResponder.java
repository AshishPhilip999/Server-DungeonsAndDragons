package ServerHandler;

import java.io.OutputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.nio.ByteBuffer;
import java.util.UUID;

import DnD.service.ClientRequestOuterClass;
import DnD.service.ClientOuterClass.Client;
import DnD.service.ServerResponseOuterClass.ServerResponse;

public class ServerResponder {
    public static void sendResponse(ServerResponse response, Client clientData) throws Exception {
        // System.out.println();
        // System.out.println("[Server Responder] Responding to " + "[client ID: " + clientData.getClientID() + "]");
        byte[] responseData = response.toByteArray();
        int responseLength = responseData.length;
        OutputStream clientOutputStream = ServerListener.clientConnections.get(UUID.fromString(clientData.getClientID()));
        System.out.println("[Server Responder] Sending request to clientID: " + clientData.getClientID());

        // Sending the length first (4 bytes, big-endian)
        clientOutputStream.write(ByteBuffer.allocate(4).putInt(responseLength).array());

        // sending the actual data
        clientOutputStream.write(responseData);
        clientOutputStream.flush();

        // InetAddress localAddress = InetAddress.getByName(clientData.getLocalAddress());
        // DatagramPacket responsePacket = new DatagramPacket(responseData, responseData.length, localAddress,
        //                 clientData.getPortNumber());
        // responseSocket.send(responsePacket);
    }
}
