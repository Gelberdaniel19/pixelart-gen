package pixelart;

public class Bit {
    public int x;
    public int y;
    public boolean on = false;

    public Bit(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public Bit up() {
        return new Bit(x, y+1);
    }
    public Bit down() {
        return new Bit(x, y-1);
    }
    public Bit left() {
        return new Bit(x-1, y);
    }
    public Bit right() {
        return new Bit(x+1, y);
    }
}