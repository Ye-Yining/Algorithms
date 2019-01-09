 public class Solution {
    public boolean Find(int target, int [][] array) {
        int col = array[0].length - 1;
        int row = 0;
        int len = array.length;
        while(row <= len - 1 && col >= 0){
            if(target == array[row][col])
                return true;
            else if (target > array[row][col])
                row++;
            else
                col--;
        }
        return false;
    }
}
