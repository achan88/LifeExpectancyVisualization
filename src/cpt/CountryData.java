package cpt;

import java.util.ArrayList;
import java.util.List;

public class CountryData {
    private String country;
    private List<Integer> years = new ArrayList<>();
    private List<Double> lifeExpectancy = new ArrayList<>();

    public CountryData(String country, int year, double lifeExpectancy) {
        this.country = country;
        addData(year, lifeExpectancy);
    }

    public String getCountry() {
        return country;
    }

    public List<Integer> getYears() {
        return years;
    }

    public List<Double> getLifeExpectancy() {
        return lifeExpectancy;
    }

    public void addData(int year, double lifeExpectancy) {
        this.years.add(year);
        this.lifeExpectancy.add(lifeExpectancy);
    }

}
