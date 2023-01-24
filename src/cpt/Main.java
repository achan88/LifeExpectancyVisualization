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
import javafx.scene.control.Button;

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
        ArrayList<XYChart.Series<String, Number>> yearList = new ArrayList<>();
        for (int i = 1950; i <= 2021; i++) {
            XYChart.Series<String, Number> data1 = new XYChart.Series<>();
            data1.setName(String.valueOf(i));
            yearList.add(data1);
        }

        // Create an ArrayList to store the countries and their index
        ArrayList<String> countries = new ArrayList<>();
        
        // Read the data from the CSV file
        CSVReader reader = new CSVReader();
        List<LifeExpectancyData> data = reader.read("src/cpt/life_expectancy.csv");
        
        // Add the data to the list for each year
        for (LifeExpectancyData d : data) {
            int year = d.getYear();
            if (year >= 1950 && year <= 2021) {
                XYChart.Series<String, Number> yearData = yearList.get(year - 1950);

                String country = d.getCountry();
                int index = countries.indexOf(country);
                if (index == -1) {
                    countries.add(country);
                    index = countries.size() - 1;
                }

                yearData.getData().add(new XYChart.Data<>(countries.get(index), d.getLifeExpectancy()));
            }
        }

        // Add the list of countries to the x-axis
        xAxisBar.setCategories(FXCollections.observableArrayList(countries));

        // Default for bar chart is 1950
        barChart.getData().add(yearList.get(0));

        // Create a dropdown menu to switch between different years
        ComboBox<Integer> yearSelector = new ComboBox<>();
        for (int i = 1950; i <= 2021; i++) {
            yearSelector.getItems().add(i);
        }

        yearSelector.setValue(1950);

        yearSelector.setOnAction(event -> {
            barChart.getData().clear();
            barChart.getData().add(yearList.get(yearSelector.getSelectionModel().getSelectedItem() - 1950));
        });
        

        Button CanadaButton = new Button("Output Canada's Life Expectancy");

        CanadaButton.setOnAction(event -> {
            
            
        });
        


    

        // Create a VBox to store the checkboxes
        HBox checkBoxes = new HBox();
        for (LifeExpectancyData d : data) {
            // Check if the series already exists
            boolean boolExistsAlready = false;
            for (XYChart.Series<Number, Number> series : lineChart.getData()) {
                if (series.getName().equals(d.getCountry())) {
                    series.getData().add(new XYChart.Data<>(d.getYear(), d.getLifeExpectancy()));
                    boolExistsAlready = true;
                    break;
                }
            }
            // If the series doesn't exist, create a new one
            if (!boolExistsAlready) {
                XYChart.Series<Number, Number> series = new XYChart.Series<>();
                series.setName(d.getCountry());
                series.getData().add(new XYChart.Data<>(d.getYear(), d.getLifeExpectancy()));
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
        VBox root = new VBox(yearSelector, barChart);
        tab2.setContent(root);

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

