public class Plugin6 extends Plugin {

    @Override
    public void PostAdd(R2Point[] points) {
        if (points == null || points.length < 3) {
            System.out.println("Угол между max/min рёбрами: n/a (нужно 3 и более вершин)");
            return;
        }
        int n = points.length;
        double maxLen = -1.0;
        double minLen = Double.POSITIVE_INFINITY;
        int maxIdx = -1;
        int minIdx = -1;
        for (int i = 0; i < n; i++) {
            double len = R2Point.dist(points[i], points[(i + 1) % n]);
            if (len > maxLen) { maxLen = len; maxIdx = i; }
            if (len < minLen) { minLen = len; minIdx = i; }
        }
        R2Point aMax = points[maxIdx];
        R2Point bMax = points[(maxIdx + 1) % n];
        R2Point aMin = points[minIdx];
        R2Point bMin = points[(minIdx + 1) % n];
        double ux = bMax.getX() - aMax.getX();
        double uy = bMax.getY() - aMax.getY();
        double vx = bMin.getX() - aMin.getX();
        double vy = bMin.getY() - aMin.getY();
        double dot = ux * vx + uy * vy;
        double cos = Math.abs(dot) / (maxLen * minLen);
        cos = Math.max(-1.0, Math.min(1.0, cos));
        double angle = Math.toDegrees(Math.acos(cos));
        System.out.printf("Угол между max и min рёбрами: %.2f\n", angle);
    }
}