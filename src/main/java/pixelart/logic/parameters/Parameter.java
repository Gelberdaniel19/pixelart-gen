package pixelart.logic.parameters;

import java.util.ArrayList;

public class Parameter {
    protected String name;
    protected String prompt;
    protected double step;
    protected double min;
    protected double max;
    protected double def;

    public Parameter(String name, String prompt, double step, double min, double max, double def) {
        this.name = name;
        this.prompt = prompt;
        this.step = step;
        this.min = min;
        this.max = max;
        this.def = def;
    }

    public static ArrayList<Parameter> getParameters() {
        ArrayList<Parameter> params = new ArrayList<>();

        params.add(new Parameter("upwards-pull", "Upwards pull",
                0.05, -1.6, 1.6, 0.05));
        params.add(new Parameter("max-angle", "Max angle change",
                0.01, 0, 3.15, 0.05));
        params.add(new Parameter("branch-angle-off", "Avg branch angle change",
                0.01, 0, 3.15, 0.2));
        params.add(new Parameter("branch-angle-var", "Branch angle variance",
                0.01, 0, 3.15, 0.1));
        params.add(new Parameter("min-depth", "Minimum depth",
                1, 2, 100, 7));
        params.add(new Parameter("max-depth", "Maximum depth",
                1, 2, 100, 20));
        params.add(new Parameter("branch-depth-start", "Minimum depth for branches",
                1, 1, 100, 4));
        params.add(new Parameter("branch-chance", "Branch chance",
                0.01, 0, 1, 0.3));
        params.add(new Parameter("branch-max-nodes", "Maximum branch depth",
                1, 1, 100, 2));
        params.add(new Parameter("term-chance-base", "Base chance to stop at node",
                0.01, 0, 1, 0));
        params.add(new Parameter("term-chance-mult", "Terminate height factor",
                0.0025, 0, 1, 0));
        params.add(new Parameter("term-chance-off", "Where terminate height factor starts",
                1, 0, 100, 7));
        params.add(new Parameter("avg-node-len", "Average length between nodes",
                0.1, 0.1, 100, 5));
        params.add(new Parameter("var-node-len", "Variance in length between nodes",
                0.1, 0, 100, 2));

        return params;
    }

    public String getName() {
        return name;
    }

    public String getPrompt() {
        return prompt;
    }

    public double getStep() {
        return step;
    }

    public double getMin() {
        return min;
    }

    public double getMax() {
        return max;
    }

    public double getDef() {
        return def;
    }
}
