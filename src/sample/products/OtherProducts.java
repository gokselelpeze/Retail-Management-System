package sample.products;

import sample.company.Manufacturer;

public class OtherProducts extends Item {
    private String categoryName;

    public OtherProducts(String brand, String name, double price, Manufacturer manufacturer, String categoryName) {
        super(brand, name, price, manufacturer);
        this.categoryName = categoryName;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }
}