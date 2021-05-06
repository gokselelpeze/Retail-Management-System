package sample.utility;

public class Address {
    private String city;
    private String town;
    private String street;

    public Address(String city, String town, String street) {
        this.city = city;
        this.town = town;
        this.street = street;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getTown() {
        return town;
    }

    public void setTown(String town) {
        this.town = town;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    @Override
    public String toString() {
        return "city: '" + city + '\'' +
            ", town: '" + town + '\'' +
            ", street: '" + street + '\'';
    }

}
