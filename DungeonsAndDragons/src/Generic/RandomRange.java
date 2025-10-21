package Generic;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class RandomRange {
    public static int range(int min, int max) {
        return new Random().nextInt((max - min) + 1) + min;
    }

    public static float range(float min, float max) {
        return ThreadLocalRandom.current().nextFloat() * (max - min) + min;
    }
}
