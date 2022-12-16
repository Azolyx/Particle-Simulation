import java.awt.*;

public class Particle {
    public double x;
    public double y;
    public double vx;
    public double vy;
    public double radius;
    public Color color;

    public Particle(double x, double y, double vx, double vy, double radius, Color color) {
        this.x = x;
        this.y = y;
        this.vx = vx;
        this.vy = vy;
        this.radius = radius;
        this.color = color;
    }

    public void drawParticle(WindowManager windowManager) {
        double offset = radius / 2;
        windowManager.drawOval(color, true, (int) (x - offset), (int) (y - offset), (int) radius * 2, (int) radius * 2);
    }

    public void applyVelocity() {
        x += vx;
        y += vy;
    }

    public void applyCollision(Particle particle) {
        double relativeX = x - particle.x;
        double relativeY = y - particle.y;
        double d = relativeX * relativeX + relativeY * relativeY;

        double u1 = (vx * relativeX + vy * relativeY) / d;
        double u2 = (relativeX * vy - relativeY * vx) / d;
        double u3 = (particle.vx * relativeX + particle.vy * relativeY) / d;
        double u4 = (relativeX * particle.vy - relativeY * particle.vx) / d;

        particle.vx = relativeX * u1 - relativeY * u4;
        particle.vy = relativeY * u1 + relativeX * u4;
        vx = relativeX * u3 - relativeY * u2;
        vy = relativeY * u3 + relativeX * u2;


        while (Physics.particleCollision(this,  particle)) {
            applyVelocity();
            particle.applyVelocity();
        }
        applyVelocity();
        particle.applyVelocity();
    }
    public void applyCollision(Wall wall) {
        double[] bouncedVelocity = Physics.calculateBounce(this, wall);
        vx = bouncedVelocity[0];
        vy = bouncedVelocity[1];

        while (Physics.particleWallCollision(this, wall)) {
            applyVelocity();
        }
        applyVelocity();
    }
}
