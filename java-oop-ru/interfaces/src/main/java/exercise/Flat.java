package exercise;

// BEGIN
public class Flat implements Home {
    private double area;
    private double balconyArea;
    private int floor;

    public Flat(double area, double balconyArea, int floor) {
        this.area = area;
        this.balconyArea = balconyArea;
        this.floor = floor;
    }

    public double getArea() {
        return area + balconyArea;
    }

    @Override
    public String toString() {
        return "Квартира площадью " + getArea() + " метров на " + floor + " этаже";
    }

    @Override
    public int compareTo(Home another) {
        if (another instanceof Flat) {
            double anotherArea = ((Flat) another).getArea();
            if (this.getArea() > anotherArea) {
                return 1;
            } else if (this.getArea() < anotherArea) {
                return -1;
            } else {
                return 0;
            }
        }
        return -2;
    }
}
// END
