package pixelart.controller;

import pixelart.controller.util.Path;
import pixelart.controller.util.VelocityRenderer;
import pixelart.logic.gen.Tree;
import pixelart.logic.geometry.Line;
import pixelart.logic.geometry.Point;
import pixelart.logic.image.BitPlane;
import pixelart.logic.image.Color;
import pixelart.logic.image.PNGImage;
import pixelart.logic.parameters.Parameter;

import javax.servlet.http.HttpServletResponse;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;

import static spark.Spark.*;

public class HomeController {
    public void initialize() {
        get(Path.HOME, (req, res) -> {
            HashMap<String, Object> params = new HashMap<>();
            params.put("parameters", Parameter.getParameters());

            return VelocityRenderer.render(params, "/home.vtl");
        });

        post(Path.GENERATE, (req, res) -> {
            Tree tree = new Tree();
            tree.setpUpwardsPull(Double.parseDouble(req.queryParams("upwards-pull")));
            tree.setpAngleChangeMax(Double.parseDouble(req.queryParams("max-angle")));
            tree.setpBranchAngleOff(Double.parseDouble(req.queryParams("branch-angle-off")));
            tree.setpBranchAngleVar(Double.parseDouble(req.queryParams("branch-angle-var")));
            tree.setpMinDepth((int)Double.parseDouble(req.queryParams("min-depth")));
            tree.setpMaxDepth((int)Double.parseDouble(req.queryParams("max-depth")));
            tree.setpBranchDepthStart((int)Double.parseDouble(req.queryParams("branch-depth-start")));
            tree.setpBranchChance(Double.parseDouble(req.queryParams("branch-chance")));
            tree.setpBranchMaxNodes((int)Double.parseDouble(req.queryParams("branch-max-nodes")));
            tree.setpTerminateChanceBase(Double.parseDouble(req.queryParams("term-chance-base")));
            tree.setpTerminateChanceMult(Double.parseDouble(req.queryParams("term-chance-mult")));
            tree.setpTerminateChanceOff((int)Double.parseDouble(req.queryParams("term-chance-off")));
            tree.setpAvgNodeLength(Double.parseDouble(req.queryParams("avg-node-len")));
            tree.setpVarNodeLength(Double.parseDouble(req.queryParams("var-node-len")));
            tree.generate();
            ArrayList<Line> lines = tree.getLines();

            int width = 150;
            int height = 150;
            PNGImage image = new PNGImage(width, height, new Color(255, 255, 255));
            BitPlane plane = new BitPlane(width, height);
            for (Line line : lines) {
                Line trans = new Line(Point.addX(line.getStart(), 75), Point.addX(line.getEnd(), 75));
                plane.drawLine(trans);
            }

            image.fromPlane(plane, new Color(0, 0, 0));
            image.write("C:\\Users\\Daniel\\Pictures\\test.png");

            HashMap<String, Object> params = new HashMap<>();
            params.put("parameters", Parameter.getParameters());
            return "done";
        });

        get(Path.IMG, (req, res) -> {
             String imgPath = "C:\\Users\\Daniel\\Pictures\\test.png";
             byte[] bytes = Files.readAllBytes(Paths.get(imgPath));
             HttpServletResponse raw = res.raw();
             raw.getOutputStream().write(bytes);
             raw.getOutputStream().flush();
             raw.getOutputStream().close();
             return res.raw();
        });
    }
}
