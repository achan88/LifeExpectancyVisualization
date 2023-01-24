package cpt;

import java.util.ArrayList;
import javafx.scene.chart.XYChart;
import java.util.Collections;

public class SelectionSort {
    public static void sortData(XYChart.Series<String, Number> data) {
        int n = data.getData().size();
        for (int i = 0; i < n - 1; i++) {
            int minIndex = i;
            for (int j = i + 1; j < n; j++) {
                if (data.getData().get(j).getYValue().doubleValue() < data.getData().get(minIndex).getYValue().doubleValue()) {
                    minIndex = j;
                }
            }
            Collections.swap(data.getData(), i, minIndex);
        }
    }
}

