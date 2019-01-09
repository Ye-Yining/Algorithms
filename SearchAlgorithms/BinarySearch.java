/**
 * Java code for binary search 
 * containing tow methods of binary search
 * 
 * @author Yining Ye
 * @version 1.0 27/12/2018
 */
public class BinarySearch{
    /**
     * Constructor for objects of BinarySearch
     */
    public BinarySearch(){

    }

    /**
     * Binary search for a key
     * if key is found, then return its index.Otherwise return -1.
     * 
     * @param arr an array containing elements
     * @param key the target that we search
     * @return    an int indicating the index of the key or the failure
     */
    public static int binarySearch(int arr[], int key){
        //index of the middle element
        int mid = arr.length / 2;
        //if the key happens to be the middle one, then return the index
        if (key == arr[mid]){
            return mid;
        }
        int low = 0;
        int high = arr.length -1;
        //the condition to start the loop
        while (low <= hign){
            mid = (high - low) / 2 + low;
            if (key < arr[mid]){//if key is less than the middle element, then skip to the left subarray.
                high = mid - 1;
            }else if (key > arr[mid]){//if key is greater than the middle element, then skip to the righ subarray.
                low = mid + 1;
            }else{//if key is found, then return its index
                return mid;
            }
        }
        //fail to find the key, return -1
        return -1;
    }

    public static int recursiveBinarySearch(int arr[], int low, int high,int key){ 
        if (low  <= high){
            int mid = (high - low) / 2 + low;
            if (arr[mid] == key){//if key is found, then return its position
                return mid;
            } 
            else if (key < arr[mid]){//if key is less than the middle element, then skip to the left subarray.
                return recursiveBinarySearch(arr,low, mid - 1, key);
            }else{//if key is greater than the middle element, then skip to the righ subarray.
                retrun recursiveBinarySearch(arr,mid + 1, high, key);
        }

    } 
    /*
    *Testing with an array
    */
    public static void main (String[] args){
        int[] arr = {10, 20, 30, 40, 50, 60};
        //Test for the common Binary Search using loop
        int result = BinarySearch.binarySearch(arr, 20);
        if (result == -1)
            System.out.println("Could not find the key!");
        else 
            System.out.println("The key is on the position " + result); 
        //Test for the Recursive Binary Search
        result = BinarySearch.recursiveBinarySearch(arr, 20);
        if (result == -1)
        System.out.println("Could not find the key!");
    else 
        System.out.println("The key is on the position " + result); 
    }
}