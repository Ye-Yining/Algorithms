import java.util.ArrayList;
public class Solution {
    public int minNumberInRotateArray(int [] array) {
        int len = array.length;
        if (len == 0)
            return 0;
        int left = 0;
        int right = len - 1;
        int mid = 0;
        while (array[left] >= array[right]){
            if (right = left - 1){
                mid = right;
                break;
            }
            mid = left + (right - left) / 2;
            //when the left, right and mid are the same,
            //we cannot identify whether the mid belongs to the first half array or the second half (they are in ascending order)
            //In this case, we have to search one by one from the rotated array.
            if (array[left] == array[right] && array[left] == array[mid])
                return findMin(array, left, right);
            //if the mid belongs to the first half array
            //then the mid must appear behind the mid
            else if (array[mid] >= array[left])
                left = mid;
            //if the mid belongs to the second half array
            //then the mid must appear before the mid
            else 
                right = mid;
        }
        return array[mid];
    }

    public int findMin(int[] array, int left, int right){
        int result = array[left];
        for (int i = left + 1; i < right; i++){
            if (array[i] < result)
                result = array[i];
        }
        return result;
    }
}