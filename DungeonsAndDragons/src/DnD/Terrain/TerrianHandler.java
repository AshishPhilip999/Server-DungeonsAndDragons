package DnD.Terrain;

import java.lang.module.ModuleDescriptor.Builder;
import java.util.*;

import DnD.Terrain.Constants.TileSpawnPositionConstants;
import DnD.Terrain.Constants.VariantRangeConstants;
import DnD.Terrain.TerrainOuterClass.Terrain;
import DnD.Terrain.TileOuterClass.Tile;
import DnD.Terrain.TileOuterClass.TileOrBuilder;
import DnD.Terrain.TileTypeOuterClass.TileType;
import Generic.RandomRange;
import MapGeneration.ForrestBiome;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class TerrianHandler {
    public static final int defaultTerrainMargin = 50;
    public static final float defaultTileSize = 1.0f;

    private static final Lock lock = new ReentrantLock();

    public static Map<Float, Map<Float, Terrain>> terrainMapX = new HashMap<>();

    public boolean needToGetTerrain() {

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

    public static float getDistance(float pos1X, float pos1Y, float pos2X, float pos2Y) {
        float dx = pos2X - pos1X;
        float dy = pos2Y - pos1Y;
        return (float) Math.sqrt(dx * dx + dy * dy);
    }

    public static List<Float[]> getNextTerrainPositions(float posX, float posY, float currentTerrainPosX,
            float currentTerrainPosY) {
        float threshold = 10.0f;
        /// Top Left threshold cordinates.
        float topLeftThresholdX = currentTerrainPosX - threshold;
        float topLeftThresholdY = currentTerrainPosY + threshold;

        /// Bottom Right threshold cordinates.
        float bottomLeftThresholdX = currentTerrainPosX + threshold;
        float bottomLeftThresholdY = currentTerrainPosY - threshold;

        List<Float[]> nextTerrainsToLoad = new ArrayList<>();

        boolean topLeftXLimitReached = (posX <= topLeftThresholdX);
        boolean topLeftYLimitReached = (posY >= topLeftThresholdY);

        boolean bottomRightXLimitReached = (posX >= bottomLeftThresholdX);
        boolean bottomRightYLimitReached = (posY <= bottomLeftThresholdY);

        /// Top Terrains.
        if (topLeftXLimitReached) {
            Float[] nextTerrainCordinates = new Float[] { currentTerrainPosX - 100.0f, currentTerrainPosY };
            nextTerrainsToLoad.add(nextTerrainCordinates);
        }

        if (topLeftYLimitReached) {
            Float[] nextTerrainCordinates = new Float[] { currentTerrainPosX, currentTerrainPosY + 100.0f };
            nextTerrainsToLoad.add(nextTerrainCordinates);
        }

        /// Diagonal Terrain Left.
        if (topLeftXLimitReached && topLeftYLimitReached) {
            Float[] nextTerrainCordinates = new Float[] { currentTerrainPosX - 100.0f,
                    currentTerrainPosY + 100.0f };
            nextTerrainsToLoad.add(nextTerrainCordinates);
        }

        /// Diagonal Terrain Right.
        if (bottomRightXLimitReached && topLeftYLimitReached) {
            Float[] nextTerrainCordinates = new Float[] { currentTerrainPosX + 100.0f,
                    currentTerrainPosY + 100.0f };
            nextTerrainsToLoad.add(nextTerrainCordinates);
        }

        /// Bottom Terrains.
        if (bottomRightXLimitReached) {
            Float[] nextTerrainCordinates = new Float[] { currentTerrainPosX + 100.0f, currentTerrainPosY };
            nextTerrainsToLoad.add(nextTerrainCordinates);
        }

        if (bottomRightYLimitReached) {
            Float[] nextTerrainCordinates = new Float[] { currentTerrainPosX, currentTerrainPosY - 100.0f };
            nextTerrainsToLoad.add(nextTerrainCordinates);
        }

        /// Diagonal Terrain Left.
        if (bottomRightXLimitReached && bottomRightYLimitReached) {
            Float[] nextTerrainCordinates = new Float[] { currentTerrainPosX + 100.0f,
                    currentTerrainPosY - 100.0f };
            nextTerrainsToLoad.add(nextTerrainCordinates);
        }

        /// Diagonal Terrain Right.
        if (topLeftXLimitReached && bottomRightYLimitReached) {
            Float[] nextTerrainCordinates = new Float[] { currentTerrainPosX - 100.0f,
                    currentTerrainPosY - 100.0f };
            nextTerrainsToLoad.add(nextTerrainCordinates);
        }

        return nextTerrainsToLoad;
    }

    public static float roundToMargin(float x) {
        return Math.round(x / (defaultTerrainMargin * 2)) * (defaultTerrainMargin * 2);
    }

    public static Float[] getCurrentTerrainPos(float posX, float posY) {
        float terrainPosX = roundToMargin(posX);
        float terrainPosY = roundToMargin(posY);

        return new Float[] { terrainPosX, terrainPosY };
    }

    public static List<Terrain> getTerrains(float x, float y, Float currentTerrainPosX, Float currentTerrainPosY,
            List<Float> terrainData) throws Exception {

        lock.lock();
        Float[] terrainPos = getCurrentTerrainPos(x, y);
        // System.out.println("[TerrainHandler:: getTerrains] Current Player terrain
        // Position. x: " + terrainPos[0]
        // + ",y: " + terrainPos[1]);
        currentTerrainPosX = terrainPos[0];
        currentTerrainPosY = terrainPos[1];
        // List<Float[]> nearestTerrainPos = getNearestTerrainPositions(x, y,
        // currentTerrainPosX, currentTerrainPosY);
        List<Float[]> nearestTerrainPos = getNextTerrainPositions(x, y, currentTerrainPosX, currentTerrainPosY);
        nearestTerrainPos.add(terrainPos);

        List<Terrain> terrains = new ArrayList<>();
        int terrainCount = 1;
        for (Float[] currNearestTerrainPos : nearestTerrainPos) {
            // System.out.println("[TerrainHandler:: getTerrains] " + "[" + terrainCount +
            // "] Generating terrain at x: "
            // + currNearestTerrainPos[0] + ", y: " + currNearestTerrainPos[1]);
            terrainCount++;
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

            // System.out.println("[Terrain Handler] Terrain exists at" + " x:" + px + " y:"
            // + py);
            Terrain terr = terrainMapY.get(py);

            terrains.add(terr);
        }
        List<Terrain> terrainsReturnCopy = new ArrayList<>(terrains);

        int index = 1;
        for (Terrain t : terrainsReturnCopy) {
            System.out.println("[" + index++ + "] PosX:" + t.getPosX() + ", PosY:" + t.getPosY());
        }
        // System.out.println("[TerrainHandler:: getTerrains] Total terrain count: " +
        // terrainsReturnCopy.size());
        lock.unlock();
        return terrainsReturnCopy;
    }

    public static boolean terrainExistsInTerrainData(float terrainPosX, float terrainPosY, List<Float> terrainData) {
        for (int i = 0; i < terrainData.size(); i += 2) {
            // System.out.println("[TerrainHandler:: terrainExistsInTerrainData] Checking
            // terrain at: posX: " + terrainData.get(i) + ", posY: " +terrainData.get(i +
            // 1));
            if (terrainPosX == terrainData.get(i) && terrainPosY == terrainData.get(i + 1)) {
                // System.out.println("[TerrainHandler:: terrainExistsInTerrainData] Terrain
                // exists on client");
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

        TileType[][] tilesType = ForrestBiome.generate();

        for (int i = 0; i < iterationLength - 1; i++) {
            for (int j = 0; j < iterationLength - 1; j++) {
                Tile.Builder newTile = Tile.newBuilder();
                int variantIndex = 0;

                TileType tileType = tilesType[i][j];

                Random rand = new Random();
                newTile = Tile.newBuilder();

                int min = 0; // lower bound (inclusive)
                int max = 0; // upper bound (inclusive)

                if (tileType == TileType.STANDARD_GRASS) {
                    max = VariantRangeConstants.standardGrassVariantRange;
                } else if (tileType == TileType.STANDARD_TREE) {
                    max = VariantRangeConstants.standardTreeVariantRange;
                } else if (tileType == TileType.ROCK) {
                    max = VariantRangeConstants.rockVariantRange;
                } else if (tileType == TileType.WOODEN_CABIN) {
                    max = VariantRangeConstants.woodenCabinVariantRange;
                    int maxSpawnPositions = RandomRange.range(0, TileSpawnPositionConstants.woodenCabinSpawnPositions);
                    List<Integer> spawnPositions = TileSpawnPositionConstants.woodenCabinSpawnPositionsList();
                    List<Integer> spawnObjects = TileSpawnPositionConstants.woodenCabinSpawnObjectsList();
                    for (int index = 0; index <= maxSpawnPositions; index++) {
                        int spawnPositionIndex = RandomRange.range(0, spawnPositions.size() - 1);
                        int currSpawnIndex = spawnPositions.get(spawnPositionIndex);

                        int spawnObjectIndex = RandomRange.range(0, spawnObjects.size() - 1);
                        int spawnObject = spawnObjects.get(spawnObjectIndex);

                        newTile.addSpawnPositionIndicies(currSpawnIndex);
                        newTile.addSpawnPositionObjects(spawnObject);

                        spawnPositions.remove(spawnPositionIndex);
                        spawnObjects.remove(spawnObjectIndex);
                    }
                }

                if (tileType != TileType.WOODEN_CABIN && tileType != TileType.LIGHT_PATCH_GRASS && tileType != TileType.WATER_BODY) {
                    float offSetX = RandomRange.range(-0.4f, 0.4f);
                    float offSetY = RandomRange.range(-0.4f, 0.4f);
                    newTile.setTileOffSetX(offSetX);
                    newTile.setTileOffSetY(offSetY);
                }

                variantIndex = RandomRange.range(min, max);

                newTile.setPosX(posX)
                        .setPosY(posY)
                        .setType(tilesType[i][j])
                        .setVariant(variantIndex);

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

    public static boolean isPointInsideAny(Float[] point, List<Float[]> rectangles) {
        for (Float[] rect : rectangles) {
            if (isPointInsideRect(point, rect)) {
                System.out.println("[Terrain Handler] Point is already generated inside an object. x:" + point[0]
                        + " ,y:" + point[1]);
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

        float left = Math.min(rect[0], rect[2]);
        float right = Math.max(rect[0], rect[2]);
        float top = Math.max(rect[1], rect[3]);
        float bottom = Math.min(rect[1], rect[3]);

        return (x >= left && x <= right && y <= top && y >= bottom);
    }

    private static boolean isOverlap(Float[] a, Float[] b) {
        float aLeft = Math.min(a[0], a[2]);
        float aRight = Math.max(a[0], a[2]);
        float aTop = Math.max(a[1], a[3]);
        float aBottom = Math.min(a[1], a[3]);

        float bLeft = Math.min(b[0], b[2]);
        float bRight = Math.max(b[0], b[2]);
        float bTop = Math.max(b[1], b[3]);
        float bBottom = Math.min(b[1], b[3]);

        return !(aRight <= bLeft || aLeft >= bRight || aBottom >= bTop || aTop <= bBottom);
    }
}