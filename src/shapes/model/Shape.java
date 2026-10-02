package shapes.model;

import shapes.controller.ComparisonController;
import shapes.interfaces.IDimensionable;

public abstract class Shape implements IDimensionable{

    private final String type;

    public Shape(String type){
        this.type = type;
    }

    public String getType(){
        return type;
    }

    public abstract double calculateArea();

    public abstract double calculatePerimeter();

    @Override

    public abstract double calculateDimension();

    public abstract void move(double dx, double dy);

    public abstract String getDimensions();

    protected abstract void applyScale(double factor);

    public void scale(double factor){
        validatePositive(factor, "Scale factor");
        applyScale(factor);
    }

    public String displayInfo(){
        return String.format(
                "Type: %s%n Dimensions: %s%n Area: %.2f%n Perimeter: %.2f%n Dimension: %.2f",
                type, getDimensions(), calculateArea(), calculatePerimeter(), calculateDimension()
        );
    }

    protected static double validatePositive(double value, String name){
        if (value <= 0){
            throw  new IllegalArgumentException(name + " must be greater than zero. Received: " + value);
        }
        return  value;
    }

    public int compareTo(Shape other) {
        return new ComparisonController().compare(this, other);
    }
}


