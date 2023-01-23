package cpt;

public class LifeExpectancyData {
    private String country;
    private String year;
    private double lifeExpectancy;

    public LifeExpectancyData(String country, String year, double lifeExpectancy) {
        this.country = country;
        this.year = year;
        this.lifeExpectancy = lifeExpectancy;
    }

    public String getCountry() {
        return country;
    }

    public String getYear() {
        return year;
    }

    public double getLifeExpectancy() {
        return lifeExpectancy;
    }
}
