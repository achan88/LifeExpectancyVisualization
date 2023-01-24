package cpt;

/**
 * Simple linear search algorithm that searches through a list of LifeExpectancyData objects
 * 
 * @author A. Chan
 */
import java.util.List;

public class LinearSearch {

    /**
     * Linear search algorithm
     * 
     * @param data inputted data set
     * @param targetCountry country key (what is to be searched for)
     * @return life expectancy value
     */
    public static double search(List<LifeExpectancyData> data, String targetCountry) {
        for (LifeExpectancyData d : data) {
            if (d.getCountry().equals(targetCountry)) {
                return d.getLifeExpectancy();
            }
        }
        return -1;
    }
}
