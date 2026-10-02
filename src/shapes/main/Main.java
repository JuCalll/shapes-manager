package shapes.main;

import shapes.model.*;
import shapes.controller.ComparisonController;
import java.util.List;

public class Main {
    public static void main(String[] args){
        testCreationAndInfo();
        testMove();
        testScale();
        testComparison();
        testInvalidDimensions();
    }
    private static void testCreationAndInfo() {
        printTitle("1. Creation and information");
        List<Shape> shapes = List.of(
                new Circle(new Point(0, 0), 2),
                new Triangle(new Point(0, 0), new Point(4, 0), new Point(0, 3)),
                new Quadrilateral(new Point(0, 2), new Point(3, 0),new Point(0, -2), new Point(-3, 0)),
                new Pentagon(new Point(5, 0), 2));

        for (Shape shape : shapes) {
            System.out.println(shape.displayInfo());
        }
    }
    private static void testMove() {
        printTitle("2. Move");
        Shape circle = new Circle(new Point(0, 0), 2);
        double areaBefore = circle.calculateArea();
        circle.move(3, 4);
        System.out.println("Circle after move (3,4): " + circle.getDimensions());
        printChange("Circle Area", areaBefore, circle.calculateArea());

        Shape rhombus = new Quadrilateral(new Point(0, 2), new Point(3, 0), new Point(0, -2), new Point(-3, 0));
        double rhombusBefore = rhombus.calculateDimension();
        rhombus.move(1, 0);
        printChange("Quadrilateral dimension", rhombusBefore,rhombus.calculateDimension());

        Shape pentagon = new Pentagon(new Point(5, 0), 2);
        double pentagonBefore = pentagon.calculateDimension();
        pentagon.move(1,0);
        printChange("Pentagon dimension",pentagonBefore,pentagon.calculateDimension());
    }

    private static void testScale(){
        printTitle("3. Scale");
        Shape circle = new Circle(new Point(0, 0), 2);
        double circleAreaBefore = circle.calculateArea();
        circle.scale(1.5);
        printChange("Circle area", circleAreaBefore, circle.calculateArea());

        Shape triangle = new Triangle(new Point(0, 0), new Point(4, 0), new Point(0, 3));
        double areaBefore = triangle.calculateArea();
        double perimeterBefore = triangle.calculatePerimeter();
        triangle.scale(2);
        printChange("Triangle area", areaBefore, triangle.calculateArea());
        printChange("Triangle perimeter", perimeterBefore, triangle.calculatePerimeter());
    }
    private static void testComparison(){
        printTitle("4. Comparison");
        ComparisonController controller=new ComparisonController();

        Shape smallCircle = new Circle(new Point(0,0),2);
        Shape bigCircle = new Circle(new Point(0,0),3);
        System.out.println("Circle r=2 vs circle r=3: " + describeComparison(controller.compare(smallCircle, bigCircle)));

        Shape triangle = new Triangle(new Point(0, 0), new Point(4, 0), new Point(0, 3));
        Shape sameTriangle = new Triangle(new Point(0, 0), new Point(4, 0), new Point(0, 3));
        System.out.println("Two equal triangles: "+ describeComparison(controller.compare(triangle, sameTriangle)));

        expectRejection("Circle vs triangle", () -> controller.compare(smallCircle, triangle));
    }
    private static void testInvalidDimensions(){
        printTitle("5. Invalid dimensions");
        expectRejection("Circle with radius 0",() -> new Circle(new Point(0,0),0));
        expectRejection("Circle with radius -5", () -> new Circle(new Point(0, 0), -5));
        expectRejection("Triangle with collinear points", () -> new Triangle(new Point(0, 0), new Point(1, 1), new Point(2, 2)));
        expectRejection("Pentagon with side 0", () -> new Pentagon(new Point(0, 0), 0));

        Circle validCircle = new Circle(new Point(0,0),2);
        expectRejection("Set radius -1", ()->validCircle.setRadius(-1));
        expectRejection("Scale 0",()->validCircle.scale(0));
    }
    private static String describeComparison(int result) {
        if (result < 0) {
            return "the first is smaller";
        }
        if (result > 0) {
            return "the first is larger";
        }
        return "both are equal";
    }
    private static void expectRejection(String description, Runnable action) {
        try {
            action.run();
            System.out.println("ERROR, value was accepted: " + description);
        } catch (IllegalArgumentException e) {
            System.out.println("Rejected - " + description + ": " + e.getMessage());
        }
    }
    private static void printTitle(String title){
        System.out.println();
        System.out.println(" === " + title + " === ");
    }
    private static void printChange(String label, double before, double after) {
        System.out.printf("%s: %.2f -> %.2f%n", label, before, after);
    }
}
