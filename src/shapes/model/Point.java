package shapes.model;

public  class Point{

    private  double x;
    private double y;

    public Point(double x, double y){
        this.x = x;
        this.y = y;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public double distanceTo(Point other){
        return Math.hypot(x - other.x, y - other.y);
    }

    public double distanceToOrigin(){
        return Math.hypot(x, y);
    }

    public void move(double dx, double dy){
        x += dx;
        y += dy;
    }

    public void scaleFrom(Point center, double factor){
        x = center.x + (x - center.x) * factor;
        y = center.y + (y - center.y) * factor;
    }

    @Override
    public String toString(){
        return String.format("(%.2f, %.2f)", x, y);
    }
}