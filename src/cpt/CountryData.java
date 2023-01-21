package cpt;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.CheckBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
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
