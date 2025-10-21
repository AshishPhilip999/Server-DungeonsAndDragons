package Service;

import java.io.OutputStream;
import java.net.InetAddress;
import java.util.*;

import DnD.Player.PlayerOuterClass.Player;
import DnD.Terrain.TerrianHandler;
import DnD.Terrain.TerrainOuterClass.Terrain;
import DnD.service.ClientOuterClass;
import DnD.service.ClientRequestOuterClass;
import DnD.service.ClientOuterClass.Client;
import DnD.service.ClientRequestOuterClass.ClientRequest;
import ServerHandler.ServerListener;

public class ServiceHandler {
    public static Map<UUID, Client> clientConnections = new HashMap<>();

    public static UUID currentUUID;

    public static void handleConnectionRequest(OutputStream clientStream, Client clientData) throws Exception {
        UUID clientUUID = UUID.fromString(clientData.getClientID());
        ServerListener.clientConnections.put(clientUUID, clientStream);
        clientConnections.put(clientUUID, clientData);

        currentUUID = clientUUID;

        System.out.println();
        System.out.println("[Service Handler] Connected to client of [client ID: " + clientData.getClientID() + "]");
        SuccessfulService.handleSuccessfullConnectionRequest(clientData);
    }

    public static void handleDisconnectionRequest(Client clientDataDisconnection) {
        try {
            UUID clientUUID = UUID.fromString(clientDataDisconnection.getClientID());
            OutputStream clientStream = ServerListener.clientConnections.remove(clientUUID);
            clientStream.close();
            System.out.println("[Service Handler:: handleDisconnectionRequest] Client: " + clientUUID + " disconnected");
            SuccessfulService.handleSuccessfullDisConnectionRequest(clientDataDisconnection);
        } catch (Exception e) {
            System.err.println("[Service Handler:: handleDiscoonectionReuqest] " + e);
        }
    }

    public static void handleTerrainGenerationRequest(Player playerData, Client client) throws Exception {
        // System.out.println("[Service Handler] Player Current Position. " + "x: " + playerData.getPosX() + " y: "
                // + playerData.getPosY());
        // List<Float> terrainData = playerData.getTerrainData().getExistingTerrainPositionsList();
        List<Float> terrainData = playerData.getTerrainData().getExistingTerrainPositionsList();
        List<Terrain> terrain = TerrianHandler.getTerrains(playerData.getPosX(), playerData.getPosY(),
                playerData.getCurrentTerrainPosX(), playerData.getCurrentTerrainPosY(), terrainData);

        // System.out.println("[ServiceHandler:: handleTerrainGenerationRequest] Sending terrains count: " + terrain.size());
        SuccessfulService.handleSuccessfullTerrainGeneration(terrain, client);
    }

    public static void handleClientUpdateRequest(Client client) throws Exception {
        SuccessfulService.handleSuccessfullPlayerUpdate(client);
    }
}
