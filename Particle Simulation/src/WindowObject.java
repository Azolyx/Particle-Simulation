import java.awt.*;
import java.awt.image.BufferedImage;

public class WindowObject {
    public static final int RECT = 1;
    public static final int OVAL = 2;
    public static final int LINE = 3;
    public static final int POLYGON = 4;
    public static final int TEXT = 5;
    public static final int IMAGE = 6;
    public int id;
    public Color color;
    public boolean fill;
    public int lineWidth, x, y, x1, x2, y1, y2, width, height, style, size;
    public int[] xPoints, yPoints;
    public String fontName, text;
    public BufferedImage image;

    WindowObject(int id, Color color, boolean fill, int x, int y, int width, int height) {
        this.id = id;
        this.color = color;
        this.fill = fill;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    WindowObject(int id, Color color, boolean fill, int lineWidth, int x, int y, int width, int height) {
        this.id = id;
        this.color = color;
        this.fill = fill;
        this.lineWidth = lineWidth;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    WindowObject(int id, Color color, int lineWidth, int x1, int y1, int x2, int y2) {
        this.id = id;
        this.color = color;
        this.lineWidth = lineWidth;
        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;
    }

    WindowObject(int id, Color color, boolean fill, int[] xPoints, int[] yPoints) {
        this.id = id;
        this.color = color;
        this.fill = fill;
        this.xPoints = xPoints;
        this.yPoints = yPoints;
    }

    WindowObject(int id, Color color, boolean fill, int lineWidth, int[] xPoints, int[] yPoints) {
        this.id = id;
        this.color = color;
        this.lineWidth = lineWidth;
        this.fill = fill;
        this.xPoints = xPoints;
        this.yPoints = yPoints;
    }

    WindowObject(int id, Color color, String fontName, int style, int size, String text, int x, int y) {
        this.id = id;
        this.color = color;
        this.fontName = fontName;
        this.style = style;
        this.size = size;
        this.text = text;
        this.x = x;
        this.y = y;
    }

    WindowObject(int id, BufferedImage image, int x, int y, int width, int height) {
        this.id = id;
        this.image = image;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }
}
