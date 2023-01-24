package cpt;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class CSVReader {
    public ArrayList<LifeExpectancyData> read(String fileName) {

        ArrayList<LifeExpectancyData> data = new ArrayList<>();

        String line;

        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {

            while ((line = br.readLine()) != null) {

                String[] values = line.split(",");
                String country = values[0];
                int year = Integer.parseInt(values[1]);
                double lifeExpectancy = Double.parseDouble(values[2]);

                data.add(new LifeExpectancyData(country, year, lifeExpectancy));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return data;
    }
}
