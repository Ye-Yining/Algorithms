/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Search;

/**
 *
 * @author Yanni
 */
/**
 * Java code for linear search
 * 
 *
 * @author Yining Ye
 * @version 1.0 27/12/2018
 */
public class LinearSearch
{

    /**
     * a method for Linearly searching a target key
     * If key is found, then return its index.Otherwise return -1.
     *
     * @param arr an array containing elements
     * @param key the target that we want to find from the array
     * @return    an int indicating the index of the key (target) or the failure
     */
     public static int linearSearch(int arr[], int key){
        for (int i = 0; i < arr.length; i++){      
            //if the element is found, then return its position
            if (arr[i] == key)
                return i;
        }
        //if the element is not found, then return -1.
        return -1;
    }
    /*
    *Testing with an array
    */
    public static void main (String[] args){
        int[] arr = {10, 20, 30};
        int result = LinearSearch.linearSearch(arr, 20);
        System.out.println(result);
        
    }
}

