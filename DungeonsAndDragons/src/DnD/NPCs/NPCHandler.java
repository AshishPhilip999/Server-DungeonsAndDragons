package DnD.NPCs;

import DnD.NPCs.Animals.Cat;
import DnD.NPCs.NPCDataOuterClass.NPCData;
import DnD.service.ServerResponseOuterClass.ServerResponse;
import DnD.service.ServerResponseOuterClass.ServerResponseType;
import Generic.Math.Vector2;
import Generic.Service.TickHandler;
import ServerHandler.ServerResponder;
import com.google.protobuf.*;

public class NPCHandler {
    private TickHandler tickHandler;

    public NPCHandler() {
        tickHandler = new TickHandler(1);
    }

    public void move(NPC npc, Vector2 by) {
        tickHandler.onTick(() -> {
            // npc.move(by);
            npc.position.posX += by.posX;
            npc.position.posY += by.posY;
            System.out.println("[NPCHandler] curr NPC pos = x:" + npc.position.posX + " ,y:" + npc.position.posY);
            NPCData npcData = NPCData.newBuilder().setNpcID(npc.npcID).setPosX(npc.position.posX).setPosY(npc.position.posY).build();

            byte[] catData = npcData.toByteArray();

            ServerResponse serverResponse = ServerResponse.newBuilder()
                    .setResponse(ServerResponseType.NPC_UPDATE)
                    .setResponseData(ByteString.copyFrom(catData))
                    .build();

            ServerResponder.sendResponseToAllClients(serverResponse);
        });
        tickHandler.start();
    }
}
