import java.util.ArrayList;
public class Solution {
    public int minNumberInRotateArray(int [] array) {
        int low = 0;
        int high = array.length - 1;
        int mid = 0;
        while (low <= high){
            mid = low + (high - low)/2;
            if (array[mid] > array[high])
                low = mid + 1;
            else if (array[mid] == array[high])
                high = high - 1;
            else 
                high = mid;
        }
        return array[mid];
    }
}