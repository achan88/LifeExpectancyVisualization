package cpt;

public class LifeExpectancyData {

    // instance variables
    private String country;
    private int year;
    private double lifeExpectancy;

    /**
     * Constructor for LifeExpectancyData class
     * 
     * @param country country of data
     * @param year year of data 
     * @param lifeExpectancy life expectancy data 
     * @author A. Chan
     */
    public LifeExpectancyData(String country, int year, double lifeExpectancy) {
        this.country = country;
        this.year = year;
        this.lifeExpectancy = lifeExpectancy;
    }

    /**
     * Getter method for country variable
     * 
     * @return country
     * @author A. Chan
     */
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
