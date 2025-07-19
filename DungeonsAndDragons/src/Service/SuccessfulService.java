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
    public static void handleSuccessFulConnectionRequest(DnD.service.ClientOuterClass.Client clientData) throws Exception {
        ServerResponse serverResponse = ServerResponse.newBuilder()
                                        .setResponse(ServerResponseType.CONNECTION_SUCCESS)
                                        .build();
        System.out.println("[Successful Service] Making response for client");
        ServerResponder.sendResponse(serverResponse, clientData);
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

        ServerResponder.sendResponse(serverResponse, clientData);
    }
}
