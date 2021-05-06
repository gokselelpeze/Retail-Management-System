package sample.utility;

public class Position {
    private String positionName;
    private int baseWage;

    public Position(String positionName, int baseWage) {
        this.positionName = positionName;
        this.baseWage = baseWage;
    }

    /**
     * Default position name is Sales Associate
     * Default wage is 12 dollars per hour
     */
    public Position() {
        this.positionName = "Sales Associate";
        this.baseWage = 12;
    }

    public String getPositionName() {
        return positionName;
    }

    public void setPositionName(String positionName) {
        this.positionName = positionName;
    }

    public int getBaseWage() {
        return baseWage;
    }

    public void setBaseWage(int baseWage) {
        this.baseWage = baseWage;
    }
}
