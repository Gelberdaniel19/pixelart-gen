package pixelart.logic.random;

import java.util.Map;

public class Random {
    public static int getInt(int min, int max) {
        if (min >= max) {
            throw new IllegalArgumentException("max must be greater than min");
        }

        return (int)(Math.random() * ((max - min) + 1)) + min;
    }

    public static double getDouble(double min, double max) {
        if (min >= max) {
            System.out.println("Min=" + min + ", Max=" + max);
            throw new IllegalArgumentException("max must be greater than min");
        }

        return Math.random() * (max - min) + min;
    }

    public static <T> T choice(Weights<T> w) {
        double totalWeight = 0;
        for (double d : w.get().values()) {
            totalWeight += d;
        }

        double d = getDouble(0, totalWeight);
        double accumulatedWeight = 0;
        for (Map.Entry<T, Double> entry : w.get().entrySet()) {
            accumulatedWeight += entry.getValue();
            if (accumulatedWeight >= d) {
                return entry.getKey();
            }
        }
        return null;
    }
}
