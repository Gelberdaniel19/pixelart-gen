package pixelart.controller;

import pixelart.controller.util.Path;
import pixelart.controller.util.VelocityRenderer;
import pixelart.logic.parameters.Parameter;

import java.util.HashMap;

import static spark.Spark.*;

public class HomeController {
    public void initialize() {
        get(Path.HOME, (req, res) -> {
            HashMap<String, Object> params = new HashMap<>();
            params.put("parameters", Parameter.getParameters());

            return VelocityRenderer.render(params, "/home.vtl");
        });
    }
}
