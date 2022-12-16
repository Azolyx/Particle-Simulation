import java.awt.*;
import java.util.LinkedList;

public class Simulation {
    WindowManager windowManager;
    LinkedList<Particle> particles = new LinkedList<>();
    LinkedList<Wall> walls = new LinkedList<>();

    public void init(WindowManager windowManager) {
        this.windowManager = windowManager;
        for (int i = 0; i < 100; i++) {
            particles.add(new Particle(Math.random() * windowManager.appWindow.getWidth(), Math.random() * windowManager.appWindow.getHeight(), Math.random() * 10 - 5, Math.random() * 10 - 5, 10, Color.BLACK));
        }
        int screenWidth = windowManager.appWindow.getWidth();
        int screenHeight = windowManager.appWindow.getHeight();
        walls.add(new Wall(screenWidth / 2, 0, screenWidth / 10, screenWidth, Math.PI / 2, Color.BLACK));
        walls.add(new Wall(screenWidth / 2, screenHeight, screenWidth / 10, screenWidth, Math.PI / 2, Color.BLACK));
        walls.add(new Wall(0, screenHeight / 2, screenWidth / 10, screenWidth, 0, Color.BLACK));
        walls.add(new Wall(screenWidth, screenHeight / 2, screenWidth / 10, screenWidth, 0, Color.BLACK));

        walls.add(new Wall(screenWidth / 4, screenHeight / 4, screenWidth / 4, screenWidth / 16, Math.PI / 4 * 3, Color.BLUE));
        walls.add(new Wall(screenWidth - screenWidth / 4, screenHeight / 4, screenWidth / 4, screenWidth / 16, Math.PI / 4, Color.BLUE));
        walls.add(new Wall(screenWidth - screenWidth / 4, screenHeight - screenHeight / 4, screenWidth / 4, screenWidth / 16, Math.PI / 4 * 3, Color.BLUE));
        walls.add(new Wall(screenWidth / 4, screenHeight - screenHeight / 4, screenWidth / 4, screenWidth / 16, Math.PI / 4, Color.BLUE));
    }

    public void tick() {
        detectCollision();
        applyVelocity();
        drawParticles();
        drawWalls();
    }

    public void drawParticles() {
        for (Particle i : particles) {
            i.drawParticle(windowManager);
        }
    }

    public void drawWalls() {
        for (Wall i : walls) {
            i.drawWall(windowManager);
        }
    }

    public void detectCollision() {
        for (int i = 0; i < particles.size(); i++) {
            for (int j = 0; j < particles.size(); j++) {
                if (i == j) continue;
                if (!Physics.particleCollision(particles.get(i), particles.get(j))) continue;

                particles.get(i).applyCollision(particles.get(j));
            }
            for (Wall j : walls) {
                if (Physics.particleWallCollision(particles.get(i), j)) particles.get(i).applyCollision(j);
            }
        }
    }

    public void applyVelocity() {
        for (Particle i : particles) {
            i.applyVelocity();
        }
    }
}
