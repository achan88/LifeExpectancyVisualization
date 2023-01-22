package cpt;

import java.util.ArrayList;

public class CountryData {

    private String country;
    private ArrayList<Integer> years = new ArrayList<>();
    private ArrayList<Double> lifeExpectancy = new ArrayList<>();

    public CountryData(String country, int year, double lifeExpectancy) {
        this.country = country;
        addData(year, lifeExpectancy);
    }

    public String getCountry() {
        return country;
    }

    public ArrayList<Integer> getYears() {
        return years;
    }

    public ArrayList<Double> getLifeExpectancy() {
        return lifeExpectancy;
    }

    public void addData(int year, double lifeExpectancy) {
        this.years.add(year);
        this.lifeExpectancy.add(lifeExpectancy);
    }

}
