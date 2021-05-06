package sample.products.electronics;

import sample.company.Manufacturer;

public class Phone extends Electronics {
    private String memory;
    private String ram;

    public Phone(String brand, String name, double price,
                 Manufacturer manufacturer, double size, String resolution, String memory, String ram) {
        super(brand, name, price, manufacturer, size, resolution);
        this.memory = memory;
        this.ram = ram;
    }

    public String getMemory() {
        return memory;
    }

    public void setMemory(String memory) {
        this.memory = memory;
    }

    public String getRam() {
        return ram;
    }

    public void setRam(String ram) {
        this.ram = ram;
    }
}
