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
    private static final float TREE_THRESHOLD = 0.45f;
    private static final float ROCK_CHANCE = 0.02f;

    public static TileType[][] generateBiome() throws IOException {
        return generate();
    }

    public static TileType[][] generate() throws IOException {
        BufferedImage image = new BufferedImage(MAP_SIZE, MAP_SIZE, BufferedImage.TYPE_INT_RGB);
        TileType[][] tileMap = new TileType[MAP_SIZE][MAP_SIZE];
        Random rand = new Random();

        float[][] noise = generateNoiseMap(MAP_SIZE, rand);

        for (int y = 0; y < MAP_SIZE; y++) {
            for (int x = 0; x < MAP_SIZE; x++) {
                TileType type;
                int rgb;

                if (rand.nextFloat() < ROCK_CHANCE) {
                    type = TileType.ROCK;
                    rgb = Color.RED.getRGB();
                } else if (noise[y][x] > TREE_THRESHOLD) {
                    type = TileType.STANDARD_TREE;
                    rgb = Color.BLACK.getRGB();
                } else {
                    type = TileType.STANDARD_GRASS;
                    rgb = Color.WHITE.getRGB();
                }

                image.setRGB(x, y, rgb);
                tileMap[y][x] = type;
            }
        }

        // Save the image for reference (optional)
        ImageIO.write(image, "png", new File("map_output.png"));
        System.out.println("Map generated and saved.");

        return tileMap;
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

    private static void printTileMap(TileType[][] map) {
        for (int y = 0; y < map.length; y++) {
            for (int x = 0; x < map[y].length; x++) {
                switch (map[y][x]) {
                    case STANDARD_GRASS:
                        System.out.print(" ");
                        break;
                    case STANDARD_TREE:
                        System.out.print("T");
                        break;
                    case ROCK:
                        System.out.print("R");
                        break;
                    default:
                        return;
                }
            }
            System.out.println();
        }
    }
}