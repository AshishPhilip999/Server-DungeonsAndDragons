package ServerHandler;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.*;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import DnD.Player.PlayerOuterClass.Player;
import DnD.service.ClientOuterClass.Client;
import DnD.service.ClientRequestOuterClass.ClientRequest;
import DnD.service.ClientRequestOuterClass.ClientRequestType;
import Service.ServiceHandler;

public class ServerListener {
    public static Map<UUID, OutputStream> clientConnections;

    public static void main(String[] args) throws Exception {
        // DatagramSocket serverSocket = new DatagramSocket(9999);
        clientConnections = new HashMap<>();
        ServerSocket serverSocket = new ServerSocket(9999);

        System.out.println("Server is running");
        while (true) {
            // byte[] requestData = new byte[1024 * 5];
            // DatagramPacket requestPacket = new DatagramPacket(requestData,
            // requestData.length);

            // serverSocket.receive(requestPacket);

            // byte[] croppedData = Arrays.copyOf(requestPacket.getData(),
            // requestPacket.getLength());
            Socket clientSocket = serverSocket.accept();
            System.out.println("[Server Listener] Recieved request");

            // ClientRequest clientRequest = ClientRequest.parseFrom(croppedData);
            handleRequest(clientSocket);
        }
    }

public static void handleRequest(Socket socket) throws Exception {
    InputStream input = socket.getInputStream();
    OutputStream output = socket.getOutputStream();

    while (true) {
        // Read 4-byte length
        byte[] lengthBytes = input.readNBytes(4);
        if (lengthBytes.length < 4) {
            System.out.println("[Server] Client disconnected");
            break;
        }

        int length = ByteBuffer.wrap(lengthBytes).order(ByteOrder.LITTLE_ENDIAN).getInt();

        // Now read full message
        byte[] messageBytes = input.readNBytes(length);
        if (messageBytes.length < length) {
            System.out.println("[Server] Incomplete message");
            break;
        }

        ClientRequest clientRequest = ClientRequest.parseFrom(messageBytes);
        handleClientRequest(output, clientRequest);
    }
}

    public static void handleClientRequest(OutputStream clientStream, ClientRequest request) throws Exception {
        ClientRequestType reqType = request.getRequestType();
        switch (reqType) {
            case CLIENT_CONNECTION:
                Client clientData = Client.parseFrom(request.getRequestData());
                System.out.println("[Server Listener] Client connection request received from [client ID: "
                        + clientData.getClientID() + "]" + " [port number: " + clientData.getPortNumber() + "]"
                        + " [local address: " + clientData.getLocalAddress() + "].");
                System.out.println();

                ServiceHandler.handleConnectionRequest(clientStream, clientData);
                break;

            case TILE_GENERATION_REQUEST:
                System.out.println("[Server Listener] Received Tile generation request");
                Client client = Client.parseFrom(request.getRequestData());
                Player playerData = client.getPlayer();
                ServiceHandler.handleTerrainGenerationRequest(playerData);
                break;
            default:
                break;
        }
    }
}
