package cpt;

/**
 * Selection sort algorithm
 * 
 * @author A. Chan
 */
public class SelectionSort {
    
    /**
     * Selection sorts an array
     * 
     * @param array inputted integer array
     * @author A. Chan
     */
    public void sort(int[] array) {
        for (int i = 0; i < array.length - 1; i++) {

            int minIndex = i;

            for (int j = i + 1; j < array.length; j++) {
                
                if (array[j] < array[minIndex]) {

                    minIndex = j;
                }
            }
            
            int temp = array[minIndex];
            array[minIndex] = array[i];
            array[i] = temp;
        }
    }
}

