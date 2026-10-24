public class HurricaneRowData {

    //Creates five private instance variables to hold the data for one row of the hurricane data file

    private int year;
    private double ace;
    private int tropicalStorms;
    private int hurricanes;
    private int majorHurricanes;

    //Creates the constructor for HurricaneRowData
    public HurricaneRowData(int year, double ace, int tropicalStorms,
                            int hurricanes, int majorHurricanes) {
        this.year = year;
        this.ace = ace;
        this.tropicalStorms = tropicalStorms;
        this.hurricanes = hurricanes;
        this.majorHurricanes = majorHurricanes;
    }

    //Creates the getter and setters

    public int getYear() {
        return year;   
    }

    public void setYear(int year) {
        this.year = year;   
    }

    public double getAce() {
        return ace;
    }

    public void setAce(double ace) {
        this.ace = ace;
    }

    public int getTropicalStorms() {
        return tropicalStorms;
    }

    public void setTropicalStorms(int tropicalStorms) {
        this.tropicalStorms = tropicalStorms;
    }

    public int getHurricanes() {
        return hurricanes;
    }

    public void setHurricanes(int hurricanes) {
        this.hurricanes = hurricanes;
    }

    public int getMajorHurricanes() {
        return majorHurricanes;
    }

    public void setMajorHurricanes(int majorHurricanes) {
        this.majorHurricanes = majorHurricanes;
    }

    //Creates the toString method to return a string representation of the data in the row
    @Override
    public String toString() {
        return String.format("Year: %d | ACE: %.1f | Tropical storms: %d | "
                        + "Hurricanes: %d | Major hurricanes: %d",
                year, ace, tropicalStorms, hurricanes, majorHurricanes);
    }
}
