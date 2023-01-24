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
     * Accessor method for country variable
     * 
     * @return country
     * @author A. Chan
     */
    public String getCountry() {
        return country;
    }

    /**
     * Accessor method for year variable
     * 
     * @return year
     * @author A. Chan
     */
    public int getYear() {
        return year;
    }

    /**
     * Accessor method for life expectancy
     * 
     * @return life expectancy
     * @author A. Chan
     */
    public double getLifeExpectancy() {
        return lifeExpectancy;
    }
}
