public class Darts {
    private double x;
    private double y;

    public Darts(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public int score() {
        double distance = Math.sqrt(Math.pow(x, 2) + Math.pow(y, 2));
        if (distance <= 1) return 10;
        else if (distance <= 5) return 5;
        else if (distance <= 10) return  1;
        else return 0;
    }
}
