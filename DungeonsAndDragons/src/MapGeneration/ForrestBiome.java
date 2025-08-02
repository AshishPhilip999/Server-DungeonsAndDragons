package MapGeneration;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.awt.Color;
import java.io.File;
import java.io.IOException;
import java.util.Random;
import DnD.Terrain.TileTypeOuterClass.TileType;

public class ForrestBiome {
    private static final int MAP_SIZE = 250;
    private static final float TREE_THRESHOLD = 0.5f;
    private static final float ROCK_CHANCE = 0.02f;
    private static final float GIANT_ROCK_CHANCE = 0.001f;
    private static final int GIANT_ROCK_SIZE = 3;
    private static final int RIVER_THICKNESS = 25;

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

        // Step 3: Carve river stream
        carveRiver(tileMap, rand);

        // Step 4: Fill remaining tiles
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
                else
                    rgb = Color.WHITE.getRGB();

                image.setRGB(x, y, rgb);
            }
        }

        // Save image
        ImageIO.write(image, "png", new File("map_output.png"));
        System.out.println("Map with river stream generated and saved.");

        return tileMap;
    }

    private static void placeGiantRock(TileType[][] tileMap, int centerX, int centerY) {
        for (int y = centerY - GIANT_ROCK_SIZE; y <= centerY + GIANT_ROCK_SIZE; y++) {
            for (int x = centerX - GIANT_ROCK_SIZE; x <= centerX + GIANT_ROCK_SIZE; x++) {
                tileMap[y][x] = TileType.STANDARD_GRASS;
            }
        }
        tileMap[centerY][centerX] = TileType.GIANT_ROCK;
    }

    private static void carveRiver(TileType[][] tileMap, Random rand) {
        int x = rand.nextInt(MAP_SIZE / 2) + MAP_SIZE / 4; // Start near middle
        for (int y = 0; y < MAP_SIZE; y++) {
            // Random wiggle left/right
            x += rand.nextInt(3) - 1; // -1, 0, or +1
            x = Math.max(RIVER_THICKNESS, Math.min(MAP_SIZE - RIVER_THICKNESS - 1, x));

            // Carve river thickness
            for (int dx = -RIVER_THICKNESS; dx <= RIVER_THICKNESS; dx++) {
                int nx = x + dx;
                if (nx >= 0 && nx < MAP_SIZE) {
                    tileMap[y][nx] = TileType.WATER_BODY;
                }
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
