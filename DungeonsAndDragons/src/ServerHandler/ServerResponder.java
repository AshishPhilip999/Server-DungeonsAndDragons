package ServerHandler;

import java.io.OutputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.nio.ByteBuffer;
import java.util.Map;
import java.util.UUID;

import DnD.service.ClientRequestOuterClass;
import DnD.service.ClientOuterClass.Client;
import DnD.service.ServerResponseOuterClass.ServerResponse;

public class ServerResponder {
    public static void sendResponse(ServerResponse response, Client clientData) throws Exception {
        // System.out.println();
        // System.out.println("[Server Responder:: sendResponse] Responding to " + "[client ID: " +
        // clientData.getClientID() + "]");
        byte[] responseData = response.toByteArray();
        int responseLength = responseData.length;
        OutputStream clientOutputStream = ServerListener.clientConnections
                .get(UUID.fromString(clientData.getClientID()));
        // System.out.println("[Server Responder] Sending request to clientID: " + clientData.getClientID() + " of response type: " + response.getResponse().toString());

        // Sending the length first (4 bytes, big-endian)
        clientOutputStream.write(ByteBuffer.allocate(4).putInt(responseLength).array());

        // sending the actual data
        clientOutputStream.write(responseData);
        clientOutputStream.flush();
    }

    public static void sendResponseToAllOtherClients(ServerResponse response, Client currClient) {
        byte[] responseData = response.toByteArray();
        int responseLength = responseData.length;

        byte[] lengthBytes = ByteBuffer.allocate(4).putInt(responseLength).array();

        for (Map.Entry<UUID, OutputStream> client : ServerListener.clientConnections.entrySet()) {
            try {
                if (client.getKey().toString().equals(currClient.getClientID())) {
                    continue;
                }

                OutputStream clientOutputStream = client.getValue();

                // System.out.println("[Server Responder:: sendResponseToAllOtherClients] Sending request to clientID: "
                        // + client.getKey());

                clientOutputStream.write(lengthBytes);

                clientOutputStream.write(responseData);
                clientOutputStream.flush();
            } catch (Exception e) {
                System.out.println("[Server Responder:: sendResponseToAllOtherClients] Error: " + e);
            }
        }
    }
}
