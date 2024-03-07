package Factory;


public class Point {
    private double x;

    private double y;

    private Point(double x, double y) {
        //TODO CONSTRUCTORDA LOGIC YAZMAK UGLY
        this.x = x;
        this.y = y;
    }

    public static Point newCartesianPoint(double x, double y) {
        return new Point(x, y);
    }

    public static Point newPolarPoint(double rho, double theta) {
        return new Point(rho * Math.cos(theta), rho * Math.signum(theta));
    }
}


class Demo {
    public static void main(String[] args) {
        Point pt = Point.newCartesianPoint(3, 4); //TODO TADA!!!
    }
}
