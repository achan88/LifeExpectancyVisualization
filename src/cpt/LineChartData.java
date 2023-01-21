package cpt;

import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Orientation;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ScrollBar;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class LineChartData extends Application {

    private List<CountryData> dataList = new ArrayList<>();

    @Override
    public void start(Stage stage) {
        // Create the chart
        final NumberAxis xAxis = new NumberAxis();
        final NumberAxis yAxis = new NumberAxis();
        xAxis.setLabel("Year");
        yAxis.setLabel("Life Expectancy (years)");
        final LineChart<Number, Number> lineChart = new LineChart<>(xAxis, yAxis);
        lineChart.setTitle("Life Expectancy over Time");
        lineChart.setCreateSymbols(false);

        // Read data from CSV file
        readDataFromCSV("life_expectancy.csv");

        // Set the lower and upper bound of the x-axis to the lowest and highest year in the data
        int minYear = Integer.MAX_VALUE;
        int maxYear = Integer.MIN_VALUE;
        for (CountryData data : dataList) {
            for (int year : data.getYears()) {
                if (year < minYear) {
                    minYear = year;
                }
                if (year > maxYear) {
                    maxYear = year;
                }
            }
        }
        xAxis.setAutoRanging(false);
        xAxis.setLowerBound(minYear);
        xAxis.setUpperBound(maxYear);

        // Create check boxes for each country
        List<CheckBox> checkBoxes = new ArrayList<>();
        for (CountryData data : dataList) {
            CheckBox checkBox = new CheckBox(data.getCountry());
            checkBox.setSelected(true);
            checkBox.setOnAction(event -> updateChart(lineChart, checkBoxes));
            checkBoxes.add(checkBox);
        }
        
        // Add the chart and check boxes to the scene
        VBox root = new VBox(lineChart);
        root.getChildren().addAll(checkBoxes);
        Scene scene = new Scene(root, 800, 600);
        stage.setScene(scene);
        stage.setTitle("Life Expectancy Chart");
        stage.show();

        // Populate the chart with initial data
        updateChart(lineChart, checkBoxes);
    }

    private void updateChart(LineChart<Number, Number> chart, List<CheckBox> checkBoxes) {
        chart.getData().clear();
        for (CountryData data : dataList) {
            XYChart.Series<Number, Number> series = new XYChart.Series<>();
            series.setName(data.getCountry());
            CheckBox checkBox = checkBoxes.get(dataList.indexOf(data));
            if (checkBox.isSelected()) {
                for (int i = 0; i < data.getYears().size(); i++) {
                    series.getData().add(new XYChart.Data<>(data.getYears().get(i), data.getLifeExpectancy().get(i)));
                }
                chart.getData().add(series);
            }
        }
    }

    private void readDataFromCSV(String fileName) {
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");
                String country = values[0];
                int year = Integer.parseInt(values[1]);
                double lifeExpectancy = Double.parseDouble(values[2]);
                addData(country, year, lifeExpectancy);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void addData(String country, int year, double lifeExpectancy) {
        for (CountryData data : dataList) {
            if (data.getCountry().equals(country)) {
                data.addData(year, lifeExpectancy);
                return;
            }
        }
        dataList.add(new CountryData(country, year, lifeExpectancy));
    }

    public static void main(String[] args) {
        launch(args);
    }
}




