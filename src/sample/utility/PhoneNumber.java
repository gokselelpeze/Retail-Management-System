package sample.utility;

public class PhoneNumber {
    private String countryCode;
    private int code;
    private int number;
    private String type;

    public PhoneNumber(String countryCode, int code, int number, String type) {
        this.countryCode = countryCode;
        this.code = code;
        this.number = number;
        this.type = type;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "PhoneNumber{" +
            "countryCode='" + countryCode + '\'' +
            ", code=" + code +
            ", number=" + number +
            ", type='" + type + '\'' +
            '}';
    }
    //phone number classK
}
