package cpt;
 
/**
 * A class to read data from a .csv file
 * 
 * @author A. Chan
 */
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class CSVReader {

    /**
     * Scraps data from a .csv file in the format: country, year, lifeExpectancy. The data is then added to an ArrayList of objects.
     * @param fileName
     * @return
     */
    public ArrayList<LifeExpectancyData> read(String fileName) {

        // new ArrayList
        ArrayList<LifeExpectancyData> data = new ArrayList<>();

        // empty string
        String line;

        // read line used BufferedReader
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {

            // If the line is not empty, then break up the points at every ','. Add these values to an ArrayList of objects
            while ((line = br.readLine()) != null) {

                // split values where a comma is present
                String[] values = line.split(",");
                
                // first value
                String country = values[0];

                // second value
                int year = Integer.parseInt(values[1]);

                // third value
                double lifeExpectancy = Double.parseDouble(values[2]);

                // Create a new LifeExpectancyData object, and add it to the ArrayList
                data.add(new LifeExpectancyData(country, year, lifeExpectancy));
            }
        
        } catch (IOException e) {
            e.printStackTrace();
        }
        return data;
    }
}
