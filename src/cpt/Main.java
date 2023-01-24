package cpt;

/**
 * Main program to display the line and bar charts
 * 
 * @author A. Chan
 */
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.geometry.Pos;
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
    
    /**
     * Start method
     */
    public void start(Stage stage) {

        // Create x-axis for bar chart
        CategoryAxis xAxisBar = new CategoryAxis();
        xAxisBar.setLabel("Country");

        // Create y-axis for bar chart
        NumberAxis yAxisBar = new NumberAxis();
        yAxisBar.setLabel("Life Expectancy (years)");

        // Create the bar chart
        BarChart<String, Number> barChart = new BarChart<>(xAxisBar, yAxisBar);
        barChart.setTitle("Life Expectancy by Country");

        // Create a year list to store the year data for bar chart
        ArrayList<XYChart.Series<String, Number>> yearList = new ArrayList<>();
        
        for (int i = 1950; i <= 2021; i++) {

            XYChart.Series<String, Number> TempDataSeries = new XYChart.Series<>();

            TempDataSeries.setName(String.valueOf(i));

            yearList.add(TempDataSeries);
        }

        // Create an ArrayList to store the countries and their index
        ArrayList<String> strCountriesArray = new ArrayList<>();
        
        // Create object of CSVReader
        CSVReader reader = new CSVReader();

        // Use CSVreader to add data to a list
        List<LifeExpectancyData> lifeDataList = reader.read("src/cpt/life_expectancy.csv");

        // Initial line chart set up

        // Create the y-axis for line chart
        NumberAxis yAxis = new NumberAxis();
        yAxis.setLabel("Life Expectancy (years)");
        
        // Create an array of integers to store the years
        int[] years = new int[lifeDataList .size()];

        int k = 0;

        for (LifeExpectancyData d : lifeDataList ) {

            years[k++] = d.getYear();

        }

        // Create an instance of the class that implements the selection sort algorithm
        SelectionSort selectionSort = new SelectionSort();

        // Pass the array of years to the sorting method
        selectionSort.sort(years);

        // Set the lower and upper bounds of the x-axis
        NumberAxis xAxis = new NumberAxis("Year", years[0], years[years.length - 1], 10d);

        // Create the line chart
        LineChart<Number, Number> lineChart = new LineChart<>(xAxis, yAxis);
        lineChart.setTitle("Life Expectancy by Year");
        
        // Add the data to the list for each year
        for (LifeExpectancyData d : lifeDataList ) {

            // get the current year 
            int year = d.getYear();

            // set bounds for bar chart (from 1950 to 2021)
            if (year >= 1950 && year <= 2021) {

                XYChart.Series<String, Number> yearData = yearList.get(year - 1950);

                // get country
                String country = d.getCountry();
                int index = strCountriesArray.indexOf(country);

                // if country was not found
                if (index == -1) {
                    
                    // add new country
                    strCountriesArray.add(country);
                    index = strCountriesArray.size() - 1;
                }

                // add the data to the list
                yearData.getData().add(new XYChart.Data<>(strCountriesArray.get(index), d.getLifeExpectancy()));
            }
        }

        // Add the list of countries to the x-axis
        xAxisBar.setCategories(FXCollections.observableArrayList(strCountriesArray));

        // Default for bar chart is 1950
        barChart.getData().add(yearList.get(0));

        // Create a dropdown menu to switch between different years
        ComboBox<Integer> yearSelector = new ComboBox<>();

        // bar chart starts from 1950, and ends at 2021 for a complete data set
        for (int i = 1950; i <= 2021; i++) {
            yearSelector.getItems().add(i);
        }

        // initial value set on bar chart is 1950
        yearSelector.setValue(1950);

        // action event, clears bar chart, and adds the selected year
        yearSelector.setOnAction(event -> {

            barChart.getData().clear();
            barChart.getData().add(yearList.get(yearSelector.getSelectionModel().getSelectedItem() - 1950));
            
        });
        
        // Create a VBox to store the checkboxes
        HBox checkBoxes = new HBox();
        checkBoxes.setAlignment(Pos.CENTER);


        // loop through the data list
        for (LifeExpectancyData d : lifeDataList) {

            // If the series exists, add to it
            boolean boolExistsAlready = false;
            for (XYChart.Series<Number, Number> series : lineChart.getData()) {

                // if the series name is equal to the country, add to the series
                if (series.getName().equals(d.getCountry())) {

                    series.getData().add(new XYChart.Data<>(d.getYear(), d.getLifeExpectancy()));
                    boolExistsAlready = true;

                    break;
                }
            }
            // If it doesn't exist, create a new one
            if (!boolExistsAlready) {
                XYChart.Series<Number, Number> series = new XYChart.Series<>();

                // Set new element of series
                series.setName(d.getCountry());

                // Add the new data point to the series
                series.getData().add(new XYChart.Data<>(d.getYear(), d.getLifeExpectancy()));

                lineChart.getData().add(series);

                // Create a new checkbox for the country
                CheckBox checkBox = new CheckBox(d.getCountry());

                // Set all checkboxes to be checked initially
                checkBox.setSelected(true);

                // Constantly check is they are checked or not. If they aren't checked, remove the series
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

        // Create the scene and stage

        // Tab pane to hold the tabs
        TabPane tabPane = new TabPane();

        // First tab; line chart
        Tab lineTab = new Tab("Line Chart");

        lineTab.setContent(new VBox(lineChart, checkBoxes));

        // Second tab; bar chart
        Tab barTab = new Tab("Bar Chart");
        VBox root = new VBox(yearSelector, barChart);
        
        barTab.setContent(root);

        // Both tabs
        tabPane.getTabs().addAll(lineTab, barTab);

        // scene, with tabPane
        Scene scene = new Scene(tabPane, 800, 600);

        // Show scene
        stage.setScene(scene);
        stage.setTitle("Life Expectancy Chart");
        stage.show();

    
    }

    /**
     * Main method to run the JavaFX program
     * @param args command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

}

