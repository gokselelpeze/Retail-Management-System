package sample.products.electronics;

import sample.company.Manufacturer;

public class Computer extends Electronics {
    private String ram;
    private String storage;


    public Computer(String brand, String name, double price, Manufacturer manufacturer,
                    double size, String resolution,
                    String ram, String storage) {
        super(brand, name, price, manufacturer, size, resolution);
        this.ram = ram;
        this.storage = storage;
    }

    public String getRam() {
        return ram;
    }


    public void setRam(String ram) {
        this.ram = ram;
    }


    public String getStorage() {
        return storage;
    }


    public void setStorage(String storage) {
        this.storage = storage;
    }
}
