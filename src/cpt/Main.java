package cpt;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.stage.Stage;
import java.util.ArrayList;

public class Main extends Application {
    @Override
    public void start(Stage stage) {
        // Create the X and Y axes for the line chart
        CategoryAxis xAxis = new CategoryAxis();
        xAxis.setLabel("Year");
        NumberAxis yAxis = new NumberAxis();
        yAxis.setLabel("Life Expectancy (years)");

        // Create the line chart
        LineChart<String, Number> lineChart = new LineChart<>(xAxis, yAxis);
        lineChart.setTitle("Life Expectancy by Year");

        // Create the X and Y axes for the bar chart
        CategoryAxis xAxis2 = new CategoryAxis();
        xAxis2.setLabel("Country");
        NumberAxis yAxis2 = new NumberAxis();
        yAxis2.setLabel("Life Expectancy (years)");

        // Create the bar chart
        BarChart<String, Number> barChart = new BarChart<>(xAxis2, yAxis2);
        barChart.setTitle("Life Expectancy by Country");

        // Read the data from the CSV file
        CSVReader reader = new CSVReader();
        ArrayList<LifeExpectancyData> data = reader.read("src/cpt/life_expectancy.csv");

        // Add the data to the line chart
        for (LifeExpectancyData d : data) {
            XYChart.Series<String, Number> series = new XYChart.Series<>();
            series.setName(d.getCountry());
            series.getData().add(new XYChart.Data<>(d.getYear(), d.getLifeExpectancy()));
            lineChart.getData().add(series);
        }

        // Add the data to the bar chart
        for (LifeExpectancyData d : data) {
            XYChart.Series<String, Number> series = new XYChart.Series<>();
            series.setName(d.getCountry());
            series.getData().add(new XYChart.Data<>(d.getCountry(), d.getLifeExpectancy()));
            barChart.getData().add(series);
        }

        // Create the tabs for the line chart and bar chart
        Tab lineChartTab = new Tab("Line Chart", lineChart);
        Tab barChartTab = new Tab("Bar Chart", barChart);
        TabPane tabPane = new TabPane(lineChartTab, barChartTab);

        // Create the scene and show the stage
        Scene scene = new Scene(tabPane, 800, 600);
        stage.setScene(scene);
        stage.setTitle("Life Expectancy Chart");
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}

