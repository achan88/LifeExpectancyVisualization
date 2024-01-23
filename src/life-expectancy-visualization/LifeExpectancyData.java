package cpt;

/**
 * A class for the data points on life expectancy from various countries and intYears
 * 
 * @author A. Chan
 */
public class LifeExpectancyData {

    // instance variables
    private String strCountry;
    private int intYear;
    private double dblLifeExpectancy;

    /**
     * Constructor for dblLifeExpectancyData class
     * 
     * @param strCountry strCountry of data
     * @param intYear intYear of data 
     * @param dblLifeExpectancy life expectancy data 
     * @author A. Chan
     */
    public LifeExpectancyData(String strCountry, int intYear, double dblLifeExpectancy) {
        this.strCountry = strCountry;
        this.intYear = intYear;
        this.dblLifeExpectancy = dblLifeExpectancy;
    }

    /**
     * Accessor method for strCountry variable
     * 
     * @return strCountry
     * @author A. Chan
     */
    public String getCountry() {
        return strCountry;
    }

    /**
     * Accessor method for intYear variable
     * 
     * @return intYear
     * @author A. Chan
     */
    public int getYear() {
        return intYear;
    }

    /**
     * Accessor method for life expectancy variable
     * 
     * @return life expectancy
     * @author A. Chan
     */
    public double getLifeExpectancy() {
        return dblLifeExpectancy;
    }
}
