package cpt;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import java.util.ArrayList;
import java.util.List;

public class Main extends Application {
    @Override
    public void start(Stage stage) {
        // Create the X and Y axes for the line chart
        NumberAxis xAxis = new NumberAxis("Year", 1800d, 2021d, 10d);
        xAxis.setLabel("Year");
        NumberAxis yAxis = new NumberAxis();
        yAxis.setLabel("Life Expectancy (years)");

        // Create the line chart
        LineChart<Number, Number> lineChart = new LineChart<>(xAxis, yAxis);
        lineChart.setTitle("Life Expectancy by Year");

        // Create the X and Y axes for the bar chart
        CategoryAxis xAxisBar = new CategoryAxis();
        xAxisBar.setLabel("Country");
        NumberAxis yAxisBar = new NumberAxis();
        yAxisBar.setLabel("Life Expectancy (years)");

        BarChart<String, Number> barChart = new BarChart<>(xAxisBar, yAxisBar);
        barChart.setTitle("Life Expectancy by Country");

        // Create a list to store the data for each year
        List<XYChart.Series<String, Number>> dataList = new ArrayList<>();
        for (int i = 1950; i <= 2021; i++) {
            XYChart.Series<String, Number> data = new XYChart.Series<>();
            data.setName(String.valueOf(i));
            dataList.add(data);
        }
        
        // Read the data from the CSV file
        CSVReader reader = new CSVReader();
        List<LifeExpectancyData> data = reader.read("src/cpt/life_expectancy.csv");
        

        

        // Create a VBox to store the checkboxes
        HBox checkBoxes = new HBox();
        for (LifeExpectancyData d : data) {
            // Check if the series already exists
            boolean seriesExists = false;
            for (XYChart.Series<Number, Number> series : lineChart.getData()) {
                if (series.getName().equals(d.getCountry())) {
                    series.getData().add(new XYChart.Data<>(Integer.parseInt(d.getYear()), d.getLifeExpectancy()));
                    seriesExists = true;
                    break;
                }
            }
            // If the series doesn't exist, create a new one
            if (!seriesExists) {
                XYChart.Series<Number, Number> series = new XYChart.Series<>();
                series.setName(d.getCountry());
                series.getData().add(new XYChart.Data<>(Integer.parseInt(d.getYear()), d.getLifeExpectancy()));
                lineChart.getData().add(series);

                // Create a new checkbox for the country
                CheckBox checkBox = new CheckBox(d.getCountry());
                checkBox.setSelected(true);
                checkBox.selectedProperty().addListener((observable, oldValue, newValue) -> {
                    if (newValue) {
                        lineChart.getData().add(series);
                    } else {
                        lineChart.getData().remove(series);
                    }
                });
                    checkBoxes.getChildren().add(checkBox);
                }
            }
            
            // Create the scene and show the stage
            TabPane tabPane = new TabPane();
            Tab tab1 = new Tab("Line Chart");
            tab1.setContent(new VBox(lineChart, checkBoxes));
            Tab tab2 = new Tab("Bar Chart");
            tab2.setContent(barChart);
            tabPane.getTabs().addAll(tab1, tab2);
            Scene scene = new Scene(tabPane, 800, 600);
            stage.setScene(scene);
            stage.setTitle("Life Expectancy Chart");
            stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

}

