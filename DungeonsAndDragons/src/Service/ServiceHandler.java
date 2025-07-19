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
        System.out.println("[Service Handler] Connected to client of [client ID: " + clientData.getClientID() + "]" + " [port number: " + clientData.getPortNumber() + "]" + " [local address: " + clientData.getLocalAddress() + "].");
        SuccessfulService.handleSuccessFulConnectionRequest(clientData);
    }

    public static void handleTerrainGenerationRequest(Player playerData) throws Exception {
        System.out.println("[Service Handler] Player Current Position. " + "x: " +playerData.getPosX() + " y: " + playerData.getPosY());
        List<Terrain> terrainData = playerData.getTerrainDataList();
        List<Terrain> terrain = TerrianHandler.getTerrains(playerData.getPosX(), playerData.getPosY(), playerData.getCurrentTerrainPosX(), playerData.getCurrentTerrainPosY(), terrainData);

        Client client = clientConnections.get(currentUUID);
        SuccessfulService.handleSuccessfullTerrainGeneration(terrain, client);
    }
}
