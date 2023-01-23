package cpt;

public class LifeExpectancyData {
    private String country;
    private int year;
    private double lifeExpectancy;

    public LifeExpectancyData(String country, int year, double lifeExpectancy) {
        this.country = country;
        this.year = year;
        this.lifeExpectancy = lifeExpectancy;
    }

    public String getCountry() {
        return country;
    }

    public int getYear() {
        return year;
    }

    public double getLifeExpectancy() {
        return lifeExpectancy;
    }
}
