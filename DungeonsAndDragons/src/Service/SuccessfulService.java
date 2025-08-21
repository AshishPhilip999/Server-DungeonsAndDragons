package Service;

import java.net.DatagramPacket;
import java.util.List;

import DnD.Terrain.TerrianHandler;
import DnD.Terrain.TerrainListOuterClass.TerrainList;
import DnD.Terrain.TerrainOuterClass.Terrain;
import DnD.service.ClientOuterClass.Client;
import DnD.service.ServerResponseOuterClass.ServerResponse;
import DnD.service.ServerResponseOuterClass.ServerResponseType;
import ServerHandler.ServerResponder;
import com.google.protobuf.*;

public class SuccessfulService {
    public static void handleSuccessfullConnectionRequest(DnD.service.ClientOuterClass.Client clientData) throws Exception {
        ServerResponse serverResponse = ServerResponse.newBuilder()
                                        .setResponse(ServerResponseType.CONNECTION_SUCCESS)
                                        .build();
        System.out.println("[Successful Service] Making response for client");
        ServerResponder.sendResponse(serverResponse, clientData);
    }

    public static void handleSuccessfullDisConnectionRequest(Client clientData) {
        byte[] clientDataBytes = clientData.toByteArray();
        ServerResponse serverResponse = ServerResponse.newBuilder()
                                        .setResponse(ServerResponseType.CLIENT_DISCONNECTED)
                                        .setResponseData(ByteString.copyFrom(clientDataBytes))
                                        .build();

        ServerResponder.sendResponseToAllOtherClients(serverResponse, clientData);
    }

    public static void handleSuccessfullTerrainGeneration(List<Terrain> terrains, Client clientData) throws Exception {
        TerrainList.Builder terrainLisBuilder = TerrainList.newBuilder();
        terrainLisBuilder.addAllTerrains(terrains);

        TerrainList terrainList = terrainLisBuilder.build();

        byte[] terrainData = terrainList.toByteArray();
        ServerResponse serverResponse = ServerResponse.newBuilder()
                                        .setResponse(ServerResponseType.TILE_GENERATION_RESPONSE)
                                        .setResponseData(ByteString.copyFrom(terrainData))
                                        .build();

        System.out.println("[SuccessfulService:: handleSuccessfullTerrainGeneration] Sending terrain data to client");
        ServerResponder.sendResponse(serverResponse, clientData);
    }

    public static void handleSuccessfullPlayerUpdate(Client client) {
        byte[] clientData = client.toByteArray();
        ServerResponse serverResponse = ServerResponse.newBuilder()
                                        .setResponse(ServerResponseType.PLAYER_UPDATE)
                                        .setResponseData(ByteString.copyFrom(clientData))
                                        .build();

        System.out.println("[SuccessfulService:: handleSuccessfullPlayerUpdate] Sending player data to client");
        ServerResponder.sendResponseToAllOtherClients(serverResponse, client);
     }
}
