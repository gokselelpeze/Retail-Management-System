package sample.products;

import sample.company.Manufacturer;

public class Item {
    private static int _itemID = 0;
    private String brand;
    private String name;
    private double price;
    private int itemID;
    private Manufacturer manufacturer;

    public Item(String brand, String name, double price, Manufacturer manufacturer) {
        this.brand = brand;
        this.name = name;
        this.price = price;
        this.itemID = _itemID++;
        this.manufacturer = manufacturer;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getItemID() {
        return itemID;
    }

    public void setItemID(int itemID) {
        this.itemID = itemID;
    }

    public Manufacturer getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(Manufacturer manufacturer) {
        this.manufacturer = manufacturer;
    }

    @Override
    public String toString() {
        return "Item{" +
            "brand='" + brand + '\'' +
            ", name='" + name + '\'' +
            ", price=" + price +
            ", itemID=" + itemID +
            ", manufacturer=" + manufacturer +
            '}';
    }
}
