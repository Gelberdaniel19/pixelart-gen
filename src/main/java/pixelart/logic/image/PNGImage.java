package pixelart.logic.image;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class PNGImage {
    private int width;
    private int height;
    private Color[][] buffer;

    public PNGImage(int width, int height, Color fill) {
        this.width = width;
        this.height = height;
        buffer = new Color[width][height];
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                buffer[i][j] = fill;
            }
        }
    }

    public PNGImage(int width, int height) {
        this.width = width;
        this.height = height;
        buffer = new Color[width][height];
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                buffer[i][j] = new Color(255, 255, 255);
            }
        }
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

        return buffer[x][y];
    }

    public void setPixel(int x, int y, Color c) {
        if (x < 0 || x >= width || y < 0 || y >= height) {
            return;
        }

        buffer[x][y] = c;
    }

    public void draw(PNGImage layer) {
        Color[][] newBuf = layer.getBuffer();
        for (int i = 0; i < newBuf.length; i++) {
            for (int j = 0; j < newBuf[0].length; j++) {
                if (newBuf[i][j].A != 0) {
                    buffer[i][j] = newBuf[i][j];
                }
            }
        }
    }

    public void fromPlane(BitPlane plane, Color color) {
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                if (plane.get(i, j).on) {
                    buffer[i][j] = color;
                }
            }
        }
    }

    public Color[][] getBuffer() {
        return buffer;
    }

    public void write(String path) {
        BufferedImage img = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                Color c = buffer[i][j];
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
