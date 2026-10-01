package shapes.model;

/**
 * @author Camila Méndez
 * @version 1.0.0
 * @since 30-09-2026
 */

public final class Triangle extends Shape{

    private final Point a;
    private final Point b;
    private final Point c;


    public Triangle(Point a, Point b, Point c) {
        super("Triangle");
        this.a= new Point(a.getX(), a.getY());
        this.b=new Point (b.getX(), b.getY());
        this.c=new Point (c.getX(), c.getY());
        validatePositive(this.a.distanceTo(this.b), "Side AB");
        validatePositive(this.b.distanceTo(this.c), "Side BC");
        validatePositive(this.c.distanceTo(this.a), "Side CA");
        validatePositive(calculateHeight(), "Height");

    }


    private double calculateBase(){
        return a.distanceTo(b);
    }

    private double calculateHeight(){
        double crossProduct = (b.getX() - a.getX()) *(c.getY() - a.getY())
                - (b.getY() - a.getY()) * (c.getX() - a.getX());

        return Math.abs(crossProduct)/ calculateBase();
    }

    @Override
    public double calculateArea() {
        return calculateBase()*calculateHeight()/2;
    }

    @Override
    public double calculatePerimeter() {
        return a.distanceTo(b) + b.distanceTo(c) + c.distanceTo(a);
    }

    @Override
    public double calculateDimension() {
        return calculatePerimeter();
    }

    @Override
    public void move(double dx, double dy) {
        a.move(dx, dy);
        b.move(dx, dy);
        c.move(dx, dy);
    }

    @Override
    public String getDimensions() {
        return String.format("vertices=%s %s %s, base=%.2f, height=%.2f",
                a,b,c, calculateBase(), calculateHeight());
    }

    @Override
    protected void applyScale(double factor) {
        b.scaleFrom(a,factor);
        c.scaleFrom(a,factor);
    }
}
