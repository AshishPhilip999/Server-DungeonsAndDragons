package DnD.Terrain;

import java.lang.module.ModuleDescriptor.Builder;
import java.util.*;

import DnD.Terrain.TerrainOuterClass.Terrain;
import DnD.Terrain.TileOuterClass.Tile;
import DnD.Terrain.TileOuterClass.TileOrBuilder;
import DnD.Terrain.TileTypeOuterClass.TileType;
import MapGeneration.ForrestBiome;

public class TerrianHandler {
    public static final int defaultTerrainMargin = 50;
    public static final float defaultTileSize = 0.5f;

    public static Map<Float, Map<Float, Terrain>> terrainMapX = new HashMap<>();

    public boolean needToGetTerrain() {

        // adding comment
        return false;
    }

    // Need to move this in a Math Package
    public static float roundToNearestMargin(float value) {
        float step = defaultTerrainMargin;
        float roundOfValue = Math.round(value / step) * step;
        return roundOfValue;
    }

    public static float[] roundOfPlayerPosition(float x, float y) {
        x = roundToNearestMargin(x);
        y = roundToNearestMargin(y);

        return new float[] { x, y };
    }

    public static float[][] gerSurroundingTerrainPositions(float currentTerrainPosX, float currentTerrainPosY) {
        float[][] surroundingTerrains = new float[8][2];

        float[] topLeftTerrain = new float[] { currentTerrainPosX - (defaultTerrainMargin * 2),
                currentTerrainPosY + (defaultTerrainMargin * 2) };
        float[] topTerrain = new float[] { currentTerrainPosX, currentTerrainPosY + (defaultTerrainMargin * 2) };
        float[] topRightTerrain = new float[] { currentTerrainPosX + (defaultTerrainMargin * 2),
                currentTerrainPosY + (defaultTerrainMargin * 2) };

        float[] leftTerrain = new float[] { currentTerrainPosX - (defaultTerrainMargin * 2), currentTerrainPosY };
        float[] rightTerrain = new float[] { currentTerrainPosX + (defaultTerrainMargin * 2), currentTerrainPosY };

        float[] bottomLeftTerrain = new float[] { currentTerrainPosX - (defaultTerrainMargin * 2),
                currentTerrainPosY - (defaultTerrainMargin * 2) };
        float[] bottomTerrain = new float[] { currentTerrainPosX, currentTerrainPosY - (defaultTerrainMargin * 2) };
        float[] bottomRightTerrain = new float[] { currentTerrainPosX + (defaultTerrainMargin * 2),
                currentTerrainPosY - (defaultTerrainMargin * 2) };

        surroundingTerrains[0] = topLeftTerrain;
        surroundingTerrains[1] = topTerrain;
        surroundingTerrains[2] = topRightTerrain;

        surroundingTerrains[3] = leftTerrain;
        surroundingTerrains[4] = rightTerrain;

        surroundingTerrains[5] = bottomLeftTerrain;
        surroundingTerrains[6] = bottomTerrain;
        surroundingTerrains[7] = bottomRightTerrain;

        return surroundingTerrains;
    }

    public static float getDistance(float pos1X, float pos1Y, float pos2X, float pos2Y) {
        float dx = pos2X - pos1X;
        float dy = pos2Y - pos1Y;
        return (float) Math.sqrt(dx * dx + dy * dy);
    }

    public static List<Float[]> getNearestTerrainPositions(float posX, float posY, float currentTerrainPosX,
            float currentTerrainPosY) {
        float maxDistance = defaultTerrainMargin * 1.5f;

        float[][] surroundingTerrains = gerSurroundingTerrainPositions(currentTerrainPosX, currentTerrainPosY);

        List<Float[]> nearestTerrains = new ArrayList<Float[]>();

        System.out.println("------------------");
        for (int i = 0; i < surroundingTerrains.length; i++) {
            float terrainPosX = surroundingTerrains[i][0];
            float terrainPosY = surroundingTerrains[i][1];
            float distance = getDistance(posX, posY, terrainPosX, terrainPosY);
            System.out.println("[Terrain Handler] X:" + terrainPosX + ", Y:" + terrainPosY + ". Distance: " + distance);
            if (distance <= maxDistance) {
                Float[] nearestTerrainPos = new Float[] { terrainPosX, terrainPosY };
                nearestTerrains.add(nearestTerrainPos);
            }
        }
        System.out.println("------------------");
        return nearestTerrains;
    }

    public static float floorToMargin(float x) {
        return (float) Math.floor(x / (defaultTerrainMargin * 2)) * (defaultTerrainMargin * 2);
    }

    public static Float[] getCurrentTerrainPos(float posX, float posY) {
        float terrainPosX = floorToMargin(posX);
        float terrainPosY = floorToMargin(posY);

        return new Float[] { terrainPosX, terrainPosY };
    }

    public static List<Terrain> getTerrains(float x, float y, Float currentTerrainPosX, Float currentTerrainPosY,
            List<Terrain> terrainData) throws Exception {
        Float[] terrainPos = getCurrentTerrainPos(x, y);
        currentTerrainPosX = terrainPos[0];
        currentTerrainPosY = terrainPos[1];
        List<Float[]> nearestTerrainPos = getNearestTerrainPositions(x, y, currentTerrainPosX, currentTerrainPosY);
        nearestTerrainPos.add(terrainPos);

        List<Terrain> terrains = new ArrayList<>();
        for (Float[] currNearestTerrainPos : nearestTerrainPos) {
            Float px = currNearestTerrainPos[0];
            Float py = currNearestTerrainPos[1];

            if (terrainExistsInTerrainData(px, py, terrainData)) {
                continue;
            }

            Map<Float, Terrain> terrainMapY = terrainMapX.get(px);
            if (terrainMapY == null || terrainMapY.get(py) == null) {
                System.out.println("[Terrain Handler] Creating new terrain at" + " x:" + px + " y:" + py);
                Terrain newTerrain = generateTerrain(px, py);
                addTerrainToMap(newTerrain);
                terrains.add(newTerrain);
                continue;
            }

            System.out.println("[Terrain Handler] Terrain exists at" + " x:" + px + " y:" + py);
            Terrain terr = terrainMapY.get(py);

            terrains.add(terr);
        }
        return terrains;
    }

    public static boolean terrainExistsInTerrainData(float terrainPosX, float terrainPosY, List<Terrain> terrainData) {
        for (Terrain currTerrain : terrainData) {
            if (terrainPosX == currTerrain.getPosX() && terrainPosY == currTerrain.getPosY()) {
                return true;
            }
        }
        return false;
    }

    public static void addTerrainToMap(Terrain terrain) throws Exception {
        float posX = terrain.getPosX();
        float posY = terrain.getPosY();
        Map<Float, Terrain> terrainMapY = terrainMapX.get(posX);

        // If terrain at pos X does not exist create new Map.
        if (terrainMapY == null) {
            Map<Float, Terrain> newTerrainMapY = new HashMap<>();
            newTerrainMapY.put(posY, terrain);
            terrainMapX.put(posX, newTerrainMapY);
        } else {
            if (terrainMapY.get(posY) != null) {
                System.out.println("[Terrain Handler] Terrain already exists");
            } else {
                terrainMapY.put(posY, terrain);
            }
        }
    }

    public static Terrain generateTerrain(float x, float y) throws Exception {
        Terrain.Builder newTerrain = Terrain.newBuilder()
                .setPosX(x)
                .setPosY(y);

        float posX = x - defaultTerrainMargin;
        float posY = y + defaultTerrainMargin;
        int iterationLength = (int) ((defaultTerrainMargin / defaultTileSize) * 2) + 1;

        System.out.println(
                "[Terrain Handler] Terrain cordinatea. x:" + newTerrain.getPosX() + ", y:" + newTerrain.getPosY());
        System.out.println("+==========================================================================+");

        int giantRockSize = 5; // size is count from the start point

        List<Float[]> terrainObjectBoundries = new ArrayList<>();

        int min = 1;
        int max = 6;

        TileType[][] tilesType = ForrestBiome.generate();

        for (int i = 0; i < iterationLength - 1; i++) {
            for (int j = 0; j < iterationLength - 1; j++) {
                Float[] currPoint = new Float[] { posX, posY };
                if(isPointInsideAny(currPoint, terrainObjectBoundries)) { posX += defaultTileSize; continue; }

                Tile.Builder newTile = Tile.newBuilder();

                // // Generate random number between min and max(inclusive)
                // int tileTypeIndex = (int) (Math.random() * (max - min + 1)) + min;
                // TileType tileType = getTileType(tileTypeIndex);

                if (tilesType[j][i] == TileType.GIANT_ROCK) {
                    Float[] fullRect = getObjectRect(posX, posY, giantRockSize);
                    if (canGenerateObject(fullRect,x, y, terrainObjectBoundries)) {
                        generateGiantRock(giantRockSize, currPoint, newTerrain);
                        terrainObjectBoundries.add(fullRect);
                         posX += defaultTileSize;
                        continue;
                    } else {
                        System.out.println("[Terrain Handler] Cannot create object due to overlap.");
                        // tileTypeIndex = (int) (Math.random() * (5 - min + 1)) + min;
                        // tileType = getTileType(tileTypeIndex);
                    }
                }

                newTile = Tile.newBuilder()
                        .setPosX(posX)
                        .setPosY(posY)
                        .setType(tilesType[i][j]);

                System.out.println("tX: " + newTerrain.getPosX() + ", tY: " + newTerrain.getPosY()
                        + "[Terrain Handler] x:" + posX + ", y:" + posY);

                Tile currTile = newTile.build();
                newTerrain.addTileData(currTile);

                posX += defaultTileSize;
            }

            posX = x - defaultTerrainMargin;
            posY -= defaultTileSize;
            System.out.println("------------------------------------------------");
        }
        System.out.println("+==========================================================================+");
        return newTerrain.build();
    }

    private static Float[] getObjectRect(float posX, float posY, int size) {
        float posMaxX = posX + (defaultTileSize * (size-1));
        float posMaxY = posY - (defaultTileSize * (size-1));

        return new Float[] { posX, posY, posMaxX, posMaxY };
    }

    private static boolean canGenerateObject(Float[] arrOfSize4, float terrainX, float terrainY, List<Float[]> objectsGenerated) {
        if(arrOfSize4[2] > terrainX + defaultTerrainMargin || arrOfSize4[3] < terrainY - defaultTerrainMargin) {
            System.out.println("[Terrain Handler] Object out of terrain bounds");
            return false;
        }

        return !isOverlapping(arrOfSize4, objectsGenerated);
    }

    private static void generateGiantRock(int size, Float[] position, Terrain.Builder terrain) {
        float posX = position[0];
        float posY = position[1];

        float posMaxX = posX + (defaultTileSize * (size-1));
        float posMaxY = posY - (defaultTileSize * (size-1));

        float centrePosX = (posX + posMaxX) / 2.0f;
        float centrePosY = (posY + posMaxY) / 2.0f;

        System.out.println("[Terrain Handler Rock Generation begin] _______________________________________________");
        for(int i = 0; i < size; i++) {
            System.out.println("------------------------");
            for(int j = 0; j < size; j++) {
                Tile.Builder builder = Tile.newBuilder();
                if(posX == centrePosX && posY == centrePosY) {
                    builder = Tile.newBuilder()
                            .setPosX(posX)
                            .setPosY(posY)
                            .setType(TileType.GIANT_ROCK);
                } else {
                    builder = Tile.newBuilder()
                            .setPosX(posX)
                            .setPosY(posY)
                            .setType(TileType.STANDARD_GRASS);
                }
                Tile tile = builder.build();
                terrain.addTileData(tile);
                System.out.println("[Rock Generation] X:" + posX + " ,Y:" + posY);
                posX += defaultTileSize;
            }
            posX = position[0];
            posY -= defaultTileSize;
        }
        System.out.println("[Terrain Handler Rock Generation End] _______________________________________________");
        System.out.println("[Terrain Handler] Giant rock generated at: X:" + position[0] + ", Y:" + position[1]);
    }

    public static boolean isPointInsideAny(Float[] point, List<Float[]> rectangles) {
        for (Float[] rect : rectangles) {
            if (isPointInsideRect(point, rect)) {
                System.out.println("[Terrain Handler] Point is already generated inside an object. x:" + point[0] + " ,y:" + point[1]);
                return true;
            }
        }
        return false;
    }

    public static boolean isOverlapping(Float[] rect, List<Float[]> rectangles) {
        for (Float[] existing : rectangles) {
            if (isOverlap(rect, existing)) {
                return true;
            }
        }
        return false;
    }

    private static boolean isPointInsideRect(Float[] point, Float[] rect) {
        float x = point[0];
        float y = point[1];

        float left   = Math.min(rect[0], rect[2]);
        float right  = Math.max(rect[0], rect[2]);
        float top    = Math.max(rect[1], rect[3]);
        float bottom = Math.min(rect[1], rect[3]);

        return (x >= left && x <= right && y <= top && y >= bottom);
    }

    private static boolean isOverlap(Float[] a, Float[] b) {
        float aLeft   = Math.min(a[0], a[2]);
        float aRight  = Math.max(a[0], a[2]);
        float aTop    = Math.max(a[1], a[3]);
        float aBottom = Math.min(a[1], a[3]);

        float bLeft   = Math.min(b[0], b[2]);
        float bRight  = Math.max(b[0], b[2]);
        float bTop    = Math.max(b[1], b[3]);
        float bBottom = Math.min(b[1], b[3]);

        return !(aRight <= bLeft || aLeft >= bRight || aBottom >= bTop || aTop <= bBottom);
    }

    private static TileType getTileType(int tileTypeIndex) {
        switch (tileTypeIndex) {
            case 1:
                return TileType.STANDARD_GRASS;

            case 2:
                return TileType.LIGHT_PATCH_GRASS;

            case 3:
                return TileType.DARK_PATCH_GRASS;

            case 4:
                return TileType.STANDARD_TREE;

            case 5:
                return TileType.ROCK;

            case 6:
                return TileType.GIANT_ROCK;

            default:
                return null;
        }
    }
}

class ObjectGenerator {
    float startPosition = -1.1f;
    float endPosition = -1.1f;

    int objectSpawnSize;
    int spawnIteration;

    float[] objectPosition = new float[] { -1, 1f, -1.1f };
}