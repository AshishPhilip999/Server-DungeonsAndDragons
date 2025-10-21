package DnD.Terrain.Constants;

import java.util.ArrayList;
import java.util.List;

/// Range is inclusive to java's random range, i.e, 0 means range up to 1 since 0 is taken as index value.
public class TileSpawnPositionConstants {
    public static int woodenCabinSpawnPositions = 3;
    public static int woodenCabinSpawnObjects = 4;

    public static List<Integer> woodenCabinSpawnPositionsList() {
        List<Integer> spawnPositions = new ArrayList<>();
        for(int i = 0; i <= woodenCabinSpawnPositions; i++) {
            spawnPositions.add(i);
        }
        return spawnPositions;
    }

    public static List<Integer> woodenCabinSpawnObjectsList() {
        List<Integer> spawnObjects = new ArrayList<>();
        for(int i = 0; i <= woodenCabinSpawnObjects; i++) {
            spawnObjects.add(i);
        }
        return spawnObjects;
    }
}
