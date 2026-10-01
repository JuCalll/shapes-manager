package shapes.model;

/**
 * @author Camila Méndez
 * @version 1.0.0
 * @since 30-09-2026
 */

public final class Quadrilateral extends Shape {

    private final Point a;
    private final Point b;
    private final Point c;
    private final Point d;


    public Quadrilateral(Point a, Point b, Point c, Point d) {
        super("Quadrilateral");
        this.a= new Point (a.getX(), a.getY());
        this.b= new Point(b.getX(), b.getY());
        this.c= new Point(c.getX(), c.getY());
        this.d= new Point(d.getX(), d.getY());
        validatePositive(this.a.distanceTo(this.b), "Side AB");
        validatePositive(this.b.distanceTo(this.c), "Side BC");
        validatePositive(this.c.distanceTo(this.d), "Side CD");
        validatePositive(this.d.distanceTo(this.a), "Side DA");
        validatePositive(calculateMinorDiagonal(), "Diagonal");
    }

    private double calculateMajorDiagonal(){
        return Math.max(a.distanceTo(c), b.distanceTo(d));
    }

    private double calculateMinorDiagonal(){
        return Math.min(a.distanceTo(c), b.distanceTo(d));
    }

    @Override
    public double calculateArea() {
        return calculateMajorDiagonal() * calculateMinorDiagonal()/2;
    }

    @Override
    public double calculatePerimeter() {
        return a.distanceTo(b) + b.distanceTo(c) + c.distanceTo(d) + d.distanceTo(a);
    }

    @Override
    public double calculateDimension() {
        return a.distanceToOrigin()+ b.distanceToOrigin()+ c.distanceToOrigin()+ d.distanceToOrigin();
    }

    @Override
    public void move(double dx, double dy) {
        a.move(dx, dy);
        b.move(dx, dy);
        c.move(dx, dy);
        d.move(dx, dy);
    }

    @Override
    public String getDimensions() {
        return String.format("vertices=%s %s %s %s, diagonals=%.2f and %.2f",
                a,b,c,d, calculateMajorDiagonal(), calculateMinorDiagonal());
    }

    @Override
    protected void applyScale(double factor) {
        b.scaleFrom(a,factor);
        c.scaleFrom(a,factor);
        d.scaleFrom(a,factor);

    }
}
