package shapes.model;

/**
 * @author Camila Méndez
 * @version 1.0.0
 * @since 30-09-2026
 */

public final class Circle extends Shape{

    private final Point center;
    private double radius;


    public Circle(Point center, double radius) {
        super("Circle");
        this.center = new Point(center.getX(), center.getY());
        setRadius(radius);
    }

    public void setRadius(double radius) {
        this.radius = validatePositive(radius, "Radius");
    }

    @Override
    public double calculateArea(){
        return Math.PI * radius * radius;
    }

    @Override
    public double calculatePerimeter(){
        return 2 * Math.PI * radius;
    }

    @Override
    public double calculateDimension(){
        return calculateArea();
    }

    @Override
    public void move (double dx, double dy){
        center.move(dx,dy);
    }


    @Override
    protected void applyScale(double factor){
        setRadius(radius * factor);
    }

    @Override
    public String getDimensions(){
        return String.format("center=%s, radius=%.2f", center, radius);
    }


}
