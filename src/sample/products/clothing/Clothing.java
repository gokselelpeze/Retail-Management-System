package sample.products.clothing;

import sample.company.Manufacturer;
import sample.products.Item;

public class Clothing extends Item {
    private String color;
    private String gender;

    public Clothing(String brand, String name, double price, Manufacturer manufacturer, String color, String gender) {
        super(brand, name, price, manufacturer);
        this.color = color;
        this.gender = gender;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }
}
