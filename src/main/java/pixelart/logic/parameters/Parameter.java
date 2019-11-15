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
                0.05, -Math.PI, Math.PI, 0.05));
        params.add(new Parameter("max-angle", "Max angle change",
                0.01, 0, Math.PI, 0.05));
        params.add(new Parameter("branch-angle-off", "Avg branch angle change",
                0.01, 0, Math.PI, 0.2));
        params.add(new Parameter("branch-angle-var", "Branch angle variance",
                0.01, 0, Math.PI, 0.1));

        return params;
    }
}
