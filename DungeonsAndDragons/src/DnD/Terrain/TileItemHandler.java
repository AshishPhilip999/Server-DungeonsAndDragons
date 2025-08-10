package DnD.Terrain;

import java.util.Map;

import DnD.Terrain.TerrainOuterClass.Terrain;
import DnD.Terrain.TileItemDataOuterClass.TileItemData;
import DnD.Terrain.TileOuterClass.Tile;
import DnD.Terrain.TileTypeOuterClass.TileType;

public class TileItemHandler {
    public static void deleteTile(TileItemData tileItem) {
        float tilePosX = tileItem.getPosX();
        float tilePosY = tileItem.getPosY();

        float tileTerrainPosX = tileItem.getTerrainPosX();
        float tileTerrainPosY = tileItem.getTerrainPosY();

        String tileMapKey = TerrianHandler.getPositionHashCode(tileTerrainPosX, tileTerrainPosY);
        String tileIndexKey = TerrianHandler.getPositionHashCode(tilePosX, tilePosY);

        System.out.println("[TileItemHandler:: deleteTile] tile position x:" + tilePosX + ", y:" + tilePosY);

        Map<String, Integer> tileMap = TerrianHandler.worldTileData.get(tileMapKey);
        int tilePositionInMap = tileMap.get(tileIndexKey);

        Map<Float, Terrain> terrainMapY = TerrianHandler.terrainMapX.get(tileTerrainPosX);
        Terrain currTerrain = terrainMapY.get(tileTerrainPosY);

        Terrain.Builder terrainBuilder = currTerrain.toBuilder();
        Tile currTile = terrainBuilder.getTileData(tilePositionInMap);

        /// Setting tile to standard grass.
        Tile.Builder tileBuilder = currTile.toBuilder();
        tileBuilder.setType(TileType.STANDARD_GRASS);

        Tile updatedTile = tileBuilder.build();

        terrainBuilder.setTileData(tilePositionInMap, updatedTile);
        Terrain updatedTerrain = terrainBuilder.build();

        terrainMapY.put(tileTerrainPosY, updatedTerrain);
    }
}
