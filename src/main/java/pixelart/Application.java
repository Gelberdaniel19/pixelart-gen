package pixelart;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import pixelart.controller.HomeController;

import static spark.Spark.port;
import static spark.Spark.staticFiles;

public class Application {
    public static void main(String[] args) {
        // Set up logger
        BasicConfigurator.configure();
        Logger.getRootLogger().setLevel(Level.INFO);

        // Configure Spark
        port(4567);
        staticFiles.location("/");
        staticFiles.expireTime(600L);

        // Set up controllers
        new HomeController().initialize();

//        for (int i = 0; i < 10; i++) {
//            Tree tree = new Tree();
//            tree.generate();
//            ArrayList<Line> lines = tree.getLines();
//
//            int width = 100;
//            int height = 130;
//
//            PNGImage image = new PNGImage(width, height, new Color(255, 255, 255));
//            BitPlane plane = new BitPlane(width, height);
//
//            for (Line line : lines) {
//                Line trans = new Line(Point.addX(line.getStart(), 50), Point.addX(line.getEnd(), 50));
//                plane.drawLine(trans);
//            }
//
//            image.fromPlane(plane, new Color(0, 0, 0));
//            image.write("C:\\Users\\Daniel\\Pictures\\test" + i + ".png");
//        }
    }
}
