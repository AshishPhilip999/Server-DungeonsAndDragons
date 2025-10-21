package MapGeneration;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.awt.Color;
import java.io.File;
import java.io.IOException;
import java.util.Random;

import DnD.Terrain.TileTypeOuterClass.TileType;

public class ForrestBiome {
    private static final int MAP_SIZE = 100;
    private static final float TREE_THRESHOLD = 0.47f;
    private static final float ROCK_CHANCE = 0.02f;
    private static final float GIANT_ROCK_CHANCE = 0.003f;
    private static final int GIANT_ROCK_SIZE = 3;

    private static final float CABIN_CHANCE = 0.001f; // Chance of a wooden cabin
    private static final int CABIN_CLEAR_SIZE = 4; // clearing around cabin (3x3)

    private static final int RIVER_THICKNESS = 20;
    private static final int RIVER_LENGTH = 300; // how long the river can flow

    public static TileType[][] generateBiome() throws IOException {
        return generate();
    }

    public static TileType[][] generate() throws IOException {
        BufferedImage image = new BufferedImage(MAP_SIZE, MAP_SIZE, BufferedImage.TYPE_INT_RGB);
        TileType[][] tileMap = new TileType[MAP_SIZE][MAP_SIZE];
        Random rand = new Random();

        float[][] noise = generateNoiseMap(MAP_SIZE, rand);

        // Step 1: Initialize all tiles as null
        for (int y = 0; y < MAP_SIZE; y++) {
            for (int x = 0; x < MAP_SIZE; x++) {
                tileMap[y][x] = null;
            }
        }

        // Step 2: Place giant rocks
        for (int y = GIANT_ROCK_SIZE; y < MAP_SIZE - GIANT_ROCK_SIZE; y++) {
            for (int x = GIANT_ROCK_SIZE; x < MAP_SIZE - GIANT_ROCK_SIZE; x++) {
                if (rand.nextFloat() < GIANT_ROCK_CHANCE) {
                    placeGiantRock(tileMap, x, y);
                }
            }
        }

        // Step 3: Place wooden cabins
        for (int y = CABIN_CLEAR_SIZE; y < MAP_SIZE - CABIN_CLEAR_SIZE; y++) {
            for (int x = CABIN_CLEAR_SIZE; x < MAP_SIZE - CABIN_CLEAR_SIZE; x++) {
                if (rand.nextFloat() < CABIN_CHANCE) {
                    placeCabin(tileMap, x, y);
                }
            }
        }

        // Step 4: Carve river stream
        carveRiver(tileMap, rand);

        // Step 5: Fill remaining tiles
        for (int y = 0; y < MAP_SIZE; y++) {
            for (int x = 0; x < MAP_SIZE; x++) {
                TileType type;
                int rgb;

                if (tileMap[y][x] != null) {
                    type = tileMap[y][x];
                } else if (rand.nextFloat() < ROCK_CHANCE) {
                    type = TileType.ROCK;
                } else if (noise[y][x] > TREE_THRESHOLD) {
                    type = TileType.STANDARD_TREE;
                } else {
                    type = TileType.STANDARD_GRASS;
                }

                tileMap[y][x] = type;

                // Color for visualization
                if (type == TileType.ROCK)
                    rgb = Color.RED.getRGB();
                else if (type == TileType.STANDARD_TREE)
                    rgb = Color.BLACK.getRGB();
                else if (type == TileType.GIANT_ROCK)
                    rgb = Color.PINK.getRGB();
                else if (type == TileType.WATER_BODY)
                    rgb = Color.BLUE.getRGB();
                else if (type == TileType.WOODEN_CABIN)
                    rgb = new Color(139, 69, 19).getRGB(); // Brown
                else
                    rgb = Color.WHITE.getRGB();

                image.setRGB(x, y, rgb);
            }
        }

        // Save image
        ImageIO.write(image, "png", new File("map_output.png"));
        System.out.println("Map with cabins, rocks, and river generated and saved.");

        return tileMap;
    }

    private static void placeGiantRock(TileType[][] tileMap, int centerX, int centerY) {
        for (int y = centerY - GIANT_ROCK_SIZE; y <= centerY + GIANT_ROCK_SIZE; y++) {
            for (int x = centerX - GIANT_ROCK_SIZE; x <= centerX + GIANT_ROCK_SIZE; x++) {
                if (x >= 0 && x < MAP_SIZE && y >= 0 && y < MAP_SIZE) {
                    tileMap[y][x] = TileType.STANDARD_GRASS;
                }
            }
        }
        tileMap[centerY][centerX] = TileType.GIANT_ROCK;
    }

    private static void placeCabin(TileType[][] tileMap, int centerX, int centerY) {
        for (int y = centerY - CABIN_CLEAR_SIZE; y <= centerY + CABIN_CLEAR_SIZE; y++) {
            for (int x = centerX - CABIN_CLEAR_SIZE; x <= centerX + CABIN_CLEAR_SIZE; x++) {
                if (x >= 0 && x < MAP_SIZE && y >= 0 && y < MAP_SIZE) {
                    // force overwrite everything with LIGHTPATCH_GRASS
                    tileMap[y][x] = TileType.LIGHT_PATCH_GRASS;
                }
            }
        }
        // cabin itself overwrites the center
        tileMap[centerY][centerX] = TileType.WOODEN_CABIN;
    }

    private static void carveRiver(TileType[][] tileMap, Random rand) {
        int x = rand.nextInt(MAP_SIZE);
        int y = rand.nextInt(MAP_SIZE);

        int dx = 0, dy = 0;
        while (dx == 0 && dy == 0) {
            dx = rand.nextInt(3) - 1;
            dy = rand.nextInt(3) - 1;
        }

        for (int step = 0; step < RIVER_LENGTH; step++) {
            if (x <= 0 || x >= MAP_SIZE - 1 || y <= 0 || y >= MAP_SIZE - 1)
                break;

            for (int ry = -RIVER_THICKNESS; ry <= RIVER_THICKNESS; ry++) {
                for (int rx = -RIVER_THICKNESS; rx <= RIVER_THICKNESS; rx++) {
                    int nx = x + rx;
                    int ny = y + ry;
                    if (nx >= 0 && nx < MAP_SIZE && ny >= 0 && ny < MAP_SIZE) {
                        tileMap[ny][nx] = TileType.WATER_BODY;
                    }
                }
            }

            x += dx;
            y += dy;

            if (rand.nextFloat() < 0.2f) {
                dx += rand.nextInt(3) - 1;
                dy += rand.nextInt(3) - 1;
                if (dx == 0 && dy == 0)
                    dx = 1;
                dx = Math.max(-1, Math.min(1, dx));
                dy = Math.max(-1, Math.min(1, dy));
            }
        }
    }

    private static float[][] generateNoiseMap(int size, Random rand) {
        float[][] base = new float[size][size];

        for (int y = 0; y < size; y++) {
            for (int x = 0; x < size; x++) {
                base[y][x] = rand.nextFloat();
            }
        }

        float[][] smoothed = new float[size][size];
        for (int y = 1; y < size - 1; y++) {
            for (int x = 1; x < size - 1; x++) {
                float avg = (base[y][x] +
                        base[y - 1][x] + base[y + 1][x] +
                        base[y][x - 1] + base[y][x + 1]) / 5f;
                smoothed[y][x] = avg;
            }
        }

        return smoothed;
    }
}
