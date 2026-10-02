package shapes.model;

import java.util.ArrayList;
import java.util.List;

public final class Pentagon extends Shape{
    private final Point center;
    private double side;

    public Pentagon(Point center, double side){
        super("Pentagon");
        this.center= new Point(center.getX(),center.getY());
        setSide(side);
    }

    public void setSide(double side){
        this.side=validatePositive(side, "Side");
    }

    private double calculateApothem(){
        double halfCentralAngle = Math.toRadians(36);
        return side / (2 * Math.tan(halfCentralAngle));
    }

    private List<Point> calculateVertices(){
        int sides = 5;
        double halfCentralAngle = Math.toRadians(36);
        double circumradius = side / (2 * Math.sin(halfCentralAngle));
        double stepDegrees = 360.0 /sides;

        List<Point> vertices = new ArrayList<>();
        for(int i=0;i<sides;i++){
            double angle= Math.toRadians(90 + stepDegrees * i);
            vertices.add(new Point(center.getX() + circumradius * Math.cos(angle),center.getY() + circumradius * Math.sin(angle)));
        }
        return vertices;
    }

    @Override
    public double calculateArea(){
        return calculatePerimeter() * calculateApothem() /2;
    }

    @Override
    public double calculatePerimeter(){
        return 5 * side;
    }

    @Override
    public double calculateDimension(){
        double sumOfX= 0;
        for(Point vertex : calculateVertices()){
            sumOfX+=vertex.getX();
        }
        return sumOfX;
    }

    @Override
    public void move(double dx, double dy){
        center.move(dx,dy);
    }

    @Override
    protected void applyScale(double factor){
        setSide(side*factor);
    }

    @Override
    public String getDimensions(){
        return String.format("center=%s, side=%.2f, apothem=%.2f", center,side,calculateApothem());
    }
}
