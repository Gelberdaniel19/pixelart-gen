package pixelart;

import java.util.HashMap;

public class Weights<T> {
    private HashMap<T, Double> weights;

    public Weights() {
        weights = new HashMap<T, Double>();
    }

    public Weights add(T n, double weight) {
        weights.put(n, weight);
        return this;
    }
    public HashMap<T, Double> get() {
        return weights;
    }
}