package cpt;

import java.util.List;
public class SelectionSort {
    public static void sortByLifeExpectancy(List<LifeExpectancyData> dataArray) {
        for (int i = 0; i < dataArray.size() - 1; i++) {
            int minIndex = i;
            for (int j = i + 1; j < dataArray.size(); j++) {
                if (dataArray.get(j).getLifeExpectancy() < dataArray.get(minIndex).getLifeExpectancy()) {
                    minIndex = j;
                }
            }
            LifeExpectancyData temp = dataArray.get(i);
            dataArray.set(i, dataArray.get(minIndex));
            dataArray.set(minIndex, temp);
        }
    }
}