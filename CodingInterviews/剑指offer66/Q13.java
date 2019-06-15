public class Solution {
    public void reOrderArray(int [] array) {
       int temp = 0;
       int len = array.length;
       for (int i = 0; i < len; i++){
           for (int j = len - 1; j > i; j--){
               if (array[j]%2 == 1 && array[j-1]%2 == 0){
                   temp = array[j];
                   array[j] = array[j-1];
                   array[j-1] = temp;
               }
           }
       }
    }
}