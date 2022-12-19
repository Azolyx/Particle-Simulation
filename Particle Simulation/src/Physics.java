public class Physics {
    public static double distance(double x1, double y1, double x2, double y2) {
        double x = x1 - x2;
        double y = y1 - y2;
        return Math.sqrt(x * x + y * y);
    }

    public static boolean particleCollision(Particle a, Particle b) {
        return distance(a.x, a.y, b.x, b.y) <= a.radius + b.radius;
    }

    public static boolean particleWallCollision(Particle a, Wall b) {
        double[] relativePos = rotateAroundPoint(a.x - b.x, a.y - b.y, 0, 0, -b.rotation);

        double circleDistanceX = Math.abs(relativePos[0]);
        double circleDistanceY = Math.abs(relativePos[1]);

        if (circleDistanceX > (b.width / 2 + a.radius)) { return false; }
        if (circleDistanceY > (b.height / 2 + a.radius)) { return false; }

        if (circleDistanceX <= (b.width / 2)) { return true; }
        if (circleDistanceY <= (b.height / 2)) { return true; }

        double cornerDistanceSquare = (circleDistanceX - b.width / 2) * (circleDistanceX - b.width / 2) + (circleDistanceY - b.height / 2) * (circleDistanceY - b.height / 2);

        return (cornerDistanceSquare <= (a.radius * a.radius));
    }

    public static double[] rotateAroundPoint(double x, double y, double originX, double originY, double theta) {
        double relativeX = x - originX;
        double relativeY = y - originY;
        return new double[] {relativeX * Math.cos(theta) - relativeY * Math.sin(theta) + originX, relativeY * Math.cos(theta) + relativeX * Math.sin(theta) + originY};
    }

    public static double[] calculateBounce(Particle particle, Wall wall) {
        double beforeX = particle.x - particle.vx;
        double beforeY = particle.y - particle.vy;

        double[] rotatedV = rotateAroundPoint(particle.vx, particle.vy, 0, 0, -wall.rotation);

        double[] rotatedPos = rotateAroundPoint(beforeX, beforeY, wall.x, wall.y, -wall.rotation);
        if (Math.abs(rotatedPos[1] - wall.y) < wall.height / 2) rotatedV[0] = -rotatedV[0];
        else rotatedV[1] = -rotatedV[1];

        rotatedV = rotateAroundPoint(rotatedV[0], rotatedV[1], 0, 0, wall.rotation);
        return rotatedV;
    }
}
