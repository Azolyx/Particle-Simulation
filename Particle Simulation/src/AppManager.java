import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

public class AppManager {
    public WindowManager windowManager = new WindowManager();
    public Thread thread;
    public boolean running = false;
    public Simulation simulation = new Simulation();

    public void init(String appName, int screenWidth, int screenHeight, int numBuffers) {
        windowManager.init(this, appName, screenWidth, screenHeight, numBuffers);
        running = true;
        thread = new Thread(this::ready);
        thread.start();
    }

    public void ready() {
        start();

        int fps = 60;
        long frameLength = 1000 / fps;
        long lastFrame = System.currentTimeMillis();
        while (running) {
            long deltaTime = System.currentTimeMillis() - lastFrame;
            long waitTime = frameLength - deltaTime;
            if (waitTime < 0) { waitTime = 0; }
            try { Thread.sleep(waitTime); }
            catch (InterruptedException e) { e.printStackTrace(); }

            tick();

            lastFrame = System.currentTimeMillis();
        }
    }

    public void start() {
        simulation.init(windowManager);
    }

    public void tick() {
        simulation.tick();
        windowManager.drawScreen();
    }

    public void stop() {
        running = false;
        thread.interrupt();
        windowManager.appWindow.dispose();
    }

    public void mouseClicked(MouseEvent e) {

    }

    public void mousePressed(MouseEvent e) {

    }

    public void mouseReleased(MouseEvent e) {

    }

    public void mouseEntered(MouseEvent e) {

    }

    public void mouseExited(MouseEvent e) {

    }

    public void keyTyped(KeyEvent e) {

    }

    public void keyPressed(KeyEvent e) {

    }

    public void keyReleased(KeyEvent e) {

    }
}
