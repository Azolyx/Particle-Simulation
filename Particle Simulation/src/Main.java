import java.awt.*;

public class Main {
    public AppManager appManager = new AppManager();

    Main() {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        appManager.init("Particle Simulation", screenSize.width, screenSize.height, 3);
    }

    public static void main(String[] args) { new Main(); }
}
