package cpt;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class DataExtract {
    
    private ArrayList<CountryData> dataList = new ArrayList<>();

    public DataExtract() {
        try (BufferedReader br = new BufferedReader(new FileReader("src/cpt/life_expectancy.csv"))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");
                String country = values[0];
                int year = Integer.parseInt(values[1]);
                double lifeExpectancy = Double.parseDouble(values[2]);
                for (CountryData data : dataList) {
                    if (data.getCountry().equals(country)) {
                        data.addData(year, lifeExpectancy);
                        return;
                    }
                }
                dataList.add(new CountryData(country, year, lifeExpectancy));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
