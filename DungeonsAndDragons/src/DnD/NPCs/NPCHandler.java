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
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.concurrent.atomic.AtomicInteger;

import org.w3c.dom.Node;

import com.google.protobuf.*;

class PathNode {
    int row, col;
    int g; // cost from start
    int f; // g + h

    PathNode(int row, int col, int g, int f) {
        this.row = row;
        this.col = col;
        this.g = g;
        this.f = f;
    }
}

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

   public List<Vector2> findShortestPath(List<List<Vector2>> grid) {

        List<Vector2> path = new ArrayList<>();

        // Safety checks
        if (grid == null || grid.isEmpty() || grid.get(0).isEmpty())
            return path;

        int rows = grid.size();
        int cols = grid.get(0).size();

        // Start = top-left, Destination = bottom-right
        int startR = 0, startC = 0;
        int endR = rows - 1, endC = cols - 1;

        // If start or destination blocked → no path
        if (!TerrianHandler.isTileEmpty(grid.get(startR).get(startC)) ||
            !TerrianHandler.isTileEmpty(grid.get(endR).get(endC)))
            return path;

        PriorityQueue<PathNode> openSet = new PriorityQueue<>(
                Comparator.comparingInt(n -> n.f)
        );

        boolean[][] closedSet = new boolean[rows][cols];
        int[][] gCost = new int[rows][cols];
        int[][][] cameFrom = new int[rows][cols][2];

        for (int r = 0; r < rows; r++)
            Arrays.fill(gCost[r], Integer.MAX_VALUE);

        gCost[startR][startC] = 0;
        int h = heuristic(startR, startC, endR, endC);
        openSet.add(new PathNode(startR, startC, 0, h));

        // 8-direction movement
        int[][] directions = {
                { 1,  0}, {-1,  0}, { 0,  1}, { 0, -1},
                { 1,  1}, { 1, -1}, {-1,  1}, {-1, -1}
        };

        while (!openSet.isEmpty()) {
            PathNode current = openSet.poll();
            int r = current.row;
            int c = current.col;

            if (closedSet[r][c])
                continue;

            closedSet[r][c] = true;

            // Destination reached
            if (r == endR && c == endC)
                break;

            for (int[] dir : directions) {
                int nr = r + dir[0];
                int nc = c + dir[1];

                // Bounds check
                if (nr < 0 || nc < 0 || nr >= rows || nc >= cols)
                    continue;

                if (closedSet[nr][nc])
                    continue;

                Vector2 nextTile = grid.get(nr).get(nc);
                if (!TerrianHandler.isTileEmpty(nextTile))
                    continue;

                boolean diagonal = dir[0] != 0 && dir[1] != 0;

                // 🚫 Prevent corner cutting
                if (diagonal) {
                    if (!TerrianHandler.isTileEmpty(grid.get(r).get(c + dir[1])) ||
                        !TerrianHandler.isTileEmpty(grid.get(r + dir[0]).get(c)))
                        continue;
                }

                int moveCost = diagonal ? 14 : 10;
                int tentativeG = gCost[r][c] + moveCost;

                if (tentativeG < gCost[nr][nc]) {
                    gCost[nr][nc] = tentativeG;
                    cameFrom[nr][nc][0] = r;
                    cameFrom[nr][nc][1] = c;

                    int f = tentativeG + heuristic(nr, nc, endR, endC);
                    openSet.add(new PathNode(nr, nc, tentativeG, f));
                }
            }
        }

        // 🔴 No path found
        if (gCost[endR][endC] == Integer.MAX_VALUE)
            return path;

        // 🟢 Reconstruct path
        int cr = endR;
        int cc = endC;

        while (!(cr == startR && cc == startC)) {
            path.add(grid.get(cr).get(cc));
            int pr = cameFrom[cr][cc][0];
            int pc = cameFrom[cr][cc][1];
            cr = pr;
            cc = pc;
        }

        path.add(grid.get(startR).get(startC));
        Collections.reverse(path);
        return path;
    }

    // =======================
    // HEURISTIC (Octile)
    // =======================
    private static int heuristic(int r1, int c1, int r2, int c2) {
        int dx = Math.abs(r1 - r2);
        int dy = Math.abs(c1 - c2);
        return 10 * (dx + dy) + (14 - 20) * Math.min(dx, dy);
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