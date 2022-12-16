import java.awt.*;

public class Wall {
    public double x;
    public double y;
    public double width;
    public double height;
    public double rotation;
    public Color color;

    public Wall(double x, double y, double width, double height, double rotation, Color color) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.rotation = rotation;
        this.color = color;
    }

    public void drawWall(WindowManager windowManager) {
        double xOffset = width / 2;
        double yOffset = height / 2;

        int[] xPoints = new int[4];
        int[] yPoints = new int[4];

        double[] point1 = Physics.rotateAroundPoint(x + xOffset, y + yOffset, x, y, rotation);
        double[] point2 = Physics.rotateAroundPoint(x + xOffset, y - yOffset, x, y, rotation);
        double[] point3 = Physics.rotateAroundPoint(x - xOffset, y - yOffset, x, y, rotation);
        double[] point4 = Physics.rotateAroundPoint(x - xOffset, y + yOffset, x, y, rotation);

        xPoints[0] = (int) point1[0];
        xPoints[1] = (int) point2[0];
        xPoints[2] = (int) point3[0];
        xPoints[3] = (int) point4[0];
        yPoints[0] = (int) point1[1];
        yPoints[1] = (int) point2[1];
        yPoints[2] = (int) point3[1];
        yPoints[3] = (int) point4[1];

        windowManager.drawPolygon(color, true, xPoints, yPoints);
    }
}
