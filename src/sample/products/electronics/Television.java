package sample.products.electronics;

import sample.company.Manufacturer;

public class Television extends Electronics {
    private String smart;

    public Television(String brand, String name, double price,
                      Manufacturer manufacturer, double size, String resolution, String smart) {
        super(brand, name, price, manufacturer, size, resolution);
        this.smart = smart;
    }

    public String isSmart() {
        return smart;
    }

    public void setSmart(String smart) {
        this.smart = smart;
    }

    @Override
    public String toString() {
        return "Television{" +
            "smart=" + smart +
            '}' + super.toString();
    }
}
