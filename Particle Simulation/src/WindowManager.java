import java.awt.*;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.util.LinkedList;

public class WindowManager {
    public AppWindow appWindow;
    public LinkedList<WindowObject> instructions = new LinkedList<>();

    public void init(AppManager appManager, String title, int width, int height, int numBuffers) {
        appWindow = new AppWindow(appManager, title, width, height, numBuffers);
    }

    public void drawRect(Color color, boolean fill, int x, int y, int width, int height) {
        instructions.add(new WindowObject(WindowObject.RECT, color, fill, x, y, width, height));
    }

    public void drawRect(Color color, boolean fill, int lineThickness, int x, int y, int width, int height) {
        instructions.add(new WindowObject(WindowObject.RECT, color, fill, lineThickness, x, y, width, height));
    }

    public void drawOval(Color color, boolean fill, int x, int y, int width, int height) {
        instructions.add(new WindowObject(WindowObject.OVAL, color, fill, x, y, width, height));
    }

    public void drawOval(Color color, boolean fill, int lineThickness, int x, int y, int width, int height) {
        instructions.add(new WindowObject(WindowObject.OVAL, color, fill, lineThickness, x, y, width, height));
    }

    public void drawLine(Color color, int lineThickness, int x1, int y1, int x2, int y2) {
        instructions.add(new WindowObject(WindowObject.LINE, color, lineThickness, x1, y1, x2, y2));
    }

    public void drawPolygon(Color color, boolean fill, int[] xPoints, int[] yPoints) {
        instructions.add(new WindowObject(WindowObject.POLYGON, color, fill, xPoints, yPoints));
    }

    public void drawPolygon(Color color, boolean fill, int lineThickness, int[] xPoints, int[] yPoints) {
        instructions.add(new WindowObject(WindowObject.POLYGON, color, fill, lineThickness, xPoints, yPoints));
    }

    public void drawText(Color color, String fontName, int style, int size, String text, int x, int y) {
        instructions.add(new WindowObject(WindowObject.TEXT, color, fontName, style, size, text, x, y));
    }

    public void drawImage(BufferedImage image, int x, int y, int width, int height) {
        instructions.add(new WindowObject(WindowObject.IMAGE, image, x, y, width, height));
    }

    public void drawScreen() {
        BufferStrategy strategy = appWindow.getBufferStrategy();

        do {
            do {
                Graphics2D g = (Graphics2D) strategy.getDrawGraphics();

                g.clearRect(0, 0, appWindow.getWidth(), appWindow.getHeight());
                for (WindowObject i : instructions) { drawInstruction(g, i); }
                instructions = new LinkedList<>();

                g.dispose();
            } while (strategy.contentsRestored());
            strategy.show();
        } while (strategy.contentsLost());
    }

    public void drawInstruction(Graphics2D g, WindowObject instruction) {
        switch (instruction.id) {
            case WindowObject.RECT:
                g.setColor(instruction.color);
                g.setStroke(new BasicStroke(instruction.lineWidth));
                if (instruction.fill) { g.fillRect(instruction.x, instruction.y, instruction.width, instruction.height); }
                else { g.drawRect(instruction.x, instruction.y, instruction.width, instruction.height); }
                break;
            case WindowObject.OVAL:
                g.setColor(instruction.color);
                g.setStroke(new BasicStroke(instruction.lineWidth));
                if (instruction.fill) { g.fillOval(instruction.x, instruction.y, instruction.width, instruction.height); }
                else { g.drawOval(instruction.x, instruction.y, instruction.width, instruction.height); }
                break;
            case WindowObject.LINE:
                g.setStroke(new BasicStroke(instruction.lineWidth));
                g.setColor(instruction.color);
                g.drawLine(instruction.x1, instruction.y1, instruction.x2, instruction.y2);
                break;
            case WindowObject.POLYGON:
                g.setColor(instruction.color);
                g.setStroke(new BasicStroke(instruction.lineWidth));
                if (instruction.fill) { g.fillPolygon(instruction.xPoints, instruction.yPoints, instruction.xPoints.length); }
                else { g.drawPolygon(instruction.xPoints, instruction.yPoints, instruction.xPoints.length); }
                break;
            case WindowObject.TEXT:
                g.setColor(instruction.color);
                g.setFont(new Font(instruction.fontName, instruction.style, instruction.size));
                g.drawString(instruction.text, instruction.x, instruction.y);
                break;
            case WindowObject.IMAGE:
                g.drawImage(instruction.image, instruction.x, instruction.y, instruction.width, instruction.height, null);
                break;
        }
    }
}
