package pixelart.controller;

import pixelart.controller.util.Path;
import pixelart.controller.util.VelocityRenderer;

import static spark.Spark.*;

public class HomeController {
    public void initialize() {
        get(Path.HOME, (req, res) -> {
            return VelocityRenderer.render("/home.vtl");
        });
    }
}
