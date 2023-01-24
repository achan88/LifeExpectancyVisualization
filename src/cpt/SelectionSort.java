package cpt;

import java.util.ArrayList;
import javafx.scene.chart.XYChart;

public class SelectionSort {

    public static void sort(ArrayList<XYChart.Series<String, Number>> data) {
        for (int i = 0; i < data.size(); i++) {
            int minIndex = i;
            for (int j = i + 1; j < data.size(); j++) {
                if (data.get(j).getName().compareTo(data.get(minIndex).getName()) < 0) {
                    minIndex = j;
                }
            }
            XYChart.Series<String, Number> temp = data.get(minIndex);
            data.set(minIndex, data.get(i));
            data.set(i, temp);
        }
    }
}

