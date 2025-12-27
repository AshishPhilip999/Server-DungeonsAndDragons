package DnD.NPCs;

import DnD.NPCs.Animals.Cat;
import DnD.Terrain.TerrianHandler;
import DnD.Terrain.TerrainOuterClass.Terrain;
import DnD.NPCs.NPCDataOuterClass.NPCData;
import DnD.service.ServerResponseOuterClass.ServerResponse;
import DnD.service.ServerResponseOuterClass.ServerResponseType;
import Generic.Math.RandomRange;
import Generic.Math.Vector2;
import Generic.Service.TickHandler;
import ServerHandler.ServerResponder;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.concurrent.atomic.AtomicInteger;

import com.google.protobuf.*;

public class NPCHandler {
    private TickHandler tickHandler;

    public NPCHandler() {
        tickHandler = new TickHandler(1);
    }

    public void move(NPC npc, Vector2 by) {
        tickHandler.onTick(() -> {
            npc.move(by);
            // npc.position.posX += by.posX;
            // npc.position.posY += by.posY;
            System.out.println(
                    "[NPCHandler] curr NPC pos = x:" + npc.getPosition().posX + " ,y:" + npc.getPosition().posY);
            NPCData npcData = NPCData.newBuilder().setNpcID(npc.npcID).setPosX(npc.getPosition().posX)
                    .setPosY(npc.getPosition().posY).build();

            byte[] catData = npcData.toByteArray();

            ServerResponse serverResponse = ServerResponse.newBuilder()
                    .setResponse(ServerResponseType.NPC_UPDATE)
                    .setResponseData(ByteString.copyFrom(catData))
                    .build();

            ServerResponder.sendResponseToAllClients(serverResponse);
        });
        tickHandler.start();
    }

    public void moveToDestination(NPC npc) {
        setDestination(npc);
        System.out.println("[NPCHandler:: moveToDestination] curr Destination - x:" + npc.currDestination.posX + ", y:"
                + npc.currDestination.posY);
        for (List<Vector2> vv : npc.destinationVectorMatrix) {
            System.out.println("---------------------------------------------");
            for (Vector2 v : vv) {
                System.out.println("[NPCHandler:: moveToDestination] destinationMatrix- x:" + v.posX + ", y:" + v.posY);
            }
            System.out.println("---------------------------------------------");
            System.out.println();
        }
        List<Vector2> path = findShortestPath(npc.destinationVectorMatrix);

        System.out.println("[NPCHandler:: moveToDestination] Path -----------------");
        for (Vector2 v : path) {
            System.out.println("[NPCHandler:: moveToDestination] path- x:" + v.posX + ", y:" + v.posY);
        }

        AtomicInteger index = new AtomicInteger(0);

        Runnable[] tickTask = new Runnable[1];

        tickTask[0] = () -> {
            int i = index.getAndIncrement();

            if (i >= path.size()) {
                tickHandler.removeTick(tickTask[0]); // STOP after path
                moveToDestination(npc);
                return;
            }

            Vector2 p = path.get(i);
            npc.moveTo(p);

            System.out.println(
                    "[NPCHandler] curr NPC pos = x:" +
                            npc.getPosition().posX + " ,y:" +
                            npc.getPosition().posY);

            NPCData npcData = NPCData.newBuilder()
                    .setNpcID(npc.npcID)
                    .setPosX(npc.getPosition().posX)
                    .setPosY(npc.getPosition().posY)
                    .build();

            ServerResponse serverResponse = ServerResponse.newBuilder()
                    .setResponse(ServerResponseType.NPC_UPDATE)
                    .setResponseData(ByteString.copyFrom(npcData.toByteArray()))
                    .build();

            ServerResponder.sendResponseToAllClients(serverResponse);
        };

        tickHandler.onTick(tickTask[0]);
        tickHandler.start();
    }

    public void setDestination(NPC npc) {
        List<Vector2> destinations = new ArrayList<>();
        Vector2 currPosition = npc.getPosition();
        int radius = npc.destinationRadius;
        Vector2 topLeftPos = new Vector2(currPosition.posX - radius, currPosition.posY + radius);
        Vector2 bottomRight = new Vector2(currPosition.posX + radius, currPosition.posY - radius);

        for (int i = 1; i <= radius * 2; i++) {
            // Top row and first column.
            destinations.add(new Vector2(topLeftPos.posX + i, topLeftPos.posY));
            destinations.add(new Vector2(topLeftPos.posX, topLeftPos.posY - i));

            // Bottom row and last colum.
            destinations.add(new Vector2(bottomRight.posX - i, bottomRight.posY));
            destinations.add(new Vector2(bottomRight.posX, bottomRight.posY + i));
        }

        for (Vector2 v : destinations) {
            System.out.println("[NPCHandler:: setDestination] destination x:" + v.posX + ", y:" + v.posY);
        }
        int destinationIndex = RandomRange.range(0, destinations.size() - 1);
        Vector2 destination = destinations.get(destinationIndex);
        npc.currDestination = destination;

        npc.destinationVectorMatrix = fillDestinationMatrix(currPosition, destination);
    }

    private List<Vector2> findShortestPath(List<List<Vector2>> grid) {
        // Always return a valid list
        List<Vector2> path = new ArrayList<>();

        // Edge case: empty grid
        if (grid == null || grid.isEmpty() || grid.get(0).isEmpty())
            return path;

        int rows = grid.size();
        int cols = grid.get(0).size();

        Vector2 start = grid.get(0).get(0);
        Vector2 destination = grid.get(rows - 1).get(cols - 1);

        Queue<int[]> queue = new ArrayDeque<>();
        boolean[][] visited = new boolean[rows][cols];
        int[][][] cameFrom = new int[rows][cols][2];

        queue.add(new int[] { 0, 0 });
        visited[0][0] = true;

        int[][] directions = {
                { 1, 0 }, // Down
                { -1, 0 }, // Up
                { 0, 1 }, // Right
                { 0, -1 } // Left
        };

        while (!queue.isEmpty()) {
            int[] current = queue.poll();
            int r = current[0];
            int c = current[1];

            // Stop as soon as destination is reached
            if (r == rows - 1 && c == cols - 1)
                break;

            for (int[] dir : directions) {
                int nr = r + dir[0];
                int nc = c + dir[1];

                if (nr < 0 || nc < 0 || nr >= rows || nc >= cols)
                    continue;

                if (visited[nr][nc])
                    continue;

                Vector2 nextTile = grid.get(nr).get(nc);

                if (!TerrianHandler.isTileEmpty(nextTile))
                continue;

                visited[nr][nc] = true;
                cameFrom[nr][nc][0] = r;
                cameFrom[nr][nc][1] = c;
                queue.add(new int[] { nr, nc });
            }
        }

        // 🔴 NO PATH CASE
        if (!visited[rows - 1][cols - 1])
            return path; // EMPTY list

        // 🟢 PATH EXISTS — reconstruct it
        int cr = rows - 1;
        int cc = cols - 1;

        while (!(cr == 0 && cc == 0)) {
            path.add(grid.get(cr).get(cc));
            int pr = cameFrom[cr][cc][0];
            int pc = cameFrom[cr][cc][1];
            cr = pr;
            cc = pc;
        }

        path.add(start);
        Collections.reverse(path);
        return path;
    }

    private List<List<Vector2>> fillDestinationMatrix(Vector2 currPosition, Vector2 destination) {
        List<List<Vector2>> destinationMatrix = new ArrayList<>();

        int xLength = (int) Math.abs((currPosition.posX - destination.posX));
        int yLength = (int) Math.abs((currPosition.posY - destination.posY));
        System.out.println("[NPCHandler:: fillDestinationMatrix] xLen & yLen: " + xLength + ":" + yLength);

        int xDirecton;
        int yDirecton;

        if (currPosition.posX > destination.posX) {
            xDirecton = -1;
        } else {
            xDirecton = 1;
        }

        if (currPosition.posY > destination.posY) {
            yDirecton = -1;
        } else {
            yDirecton = 1;
        }

        for (int i = 0; i <= xLength; i++) {
            List<Vector2> row = new ArrayList<>();
            for (int j = 0; j <= yLength; j++) {
                row.add(new Vector2(currPosition.posX + (i * xDirecton), currPosition.posY + (j * yDirecton)));
            }
            destinationMatrix.add(row);
        }

        return destinationMatrix;
    }
}
