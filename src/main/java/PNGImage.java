import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

public class PNGImage {
    private int width;
    private int height;
    private ArrayList<Color> buffer;

    public PNGImage(int width, int height, Color fill) {
        this.width = width;
        this.height = height;
        buffer = new ArrayList<Color>(Collections.nCopies(width * height, fill));
    }

    public PNGImage(int width, int height) {
        this.width = width;
        this.height = height;
        buffer = new ArrayList<Color>(Collections.nCopies(width * height, new Color(0, 0, 0)));
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public Color getPixel(int x, int y) {
        if (x < 0 || x >= width || y < 0 || y >= height) {
            return null;
        }

        return buffer.get(width * x + y);
    }

    public void setPixel(int x, int y, Color c) {
        if (x < 0 || x >= width || y < 0 || y >= height) {
            return;
        }

        buffer.set(width * x + y, c);
    }

    public void draw(PNGImage layer) {
        ArrayList<Color> newBuf = layer.getBuffer();
        for (int i = 0; i < newBuf.size(); i++) {
            if (newBuf.get(i).A != 0) {
                buffer.set(i, newBuf.get(i));
            }
        }
    }

    public void fromPlane(BitPlane plane, Color color) {
        for (int i = 0; i < width * height; i++) {
            if (plane.get(i / width, i % width).on) {
                buffer.set(i, color);
            }
        }
    }

    public ArrayList<Color> getBuffer() {
        return buffer;
    }

    public void write(String path) {
        BufferedImage img = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                Color c = buffer.get(i * width + j);
                int p = (c.A << 24) | (c.R << 16) | (c.G << 8) | c.B;
                img.setRGB(i, height - j - 1, p);
            }
        }

        try {
            File f = new File(path);
            ImageIO.write(img, "png", f);
        } catch(IOException e) {
            System.out.println(e);
        }
    }
}
