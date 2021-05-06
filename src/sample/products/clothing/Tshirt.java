package sample.products.clothing;

import sample.company.Manufacturer;

public class Tshirt extends Clothing {
    private String size;

    public Tshirt(String brand, String name, double price, Manufacturer manufacturer, String color, String gender, String size) {
        super(brand, name, price, manufacturer, color, gender);
        this.size = size;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }
}
