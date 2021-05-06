package sample.products.clothing;

import sample.company.Manufacturer;

public class Footwear extends Clothing {
    private double size;

    public Footwear(String brand, String name, double price, Manufacturer manufacturer, String color, String gender, double size) {
        super(brand, name, price, manufacturer, color, gender);
        this.size = size;
    }

    public double getSize() {
        return size;
    }

    public void setSize(double size) {
        this.size = size;
    }
}
