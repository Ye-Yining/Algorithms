/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Search;

import java.util.ArrayList;

/**
 * Java code for block search
 *
 * @author Yining Ye
 * @version 1.0 27/12/2018
 */
public class BlockSearch {

    private int[] index;
    private ArrayList[] list;


    /**
     * the Block Search method with parameter Initialise the index table
     *
     * @param index an index table containing the largest elements of each block
     */
    public BlockSearch(int[] index) {
        if (null != index && index.length != 0) {
            this.index = index;
            this.list = new ArrayList[index.length];
            for (int i = 0; i < list.length; i++) {
                list[i] = new ArrayList();//initialise the containers/blocks
            }
        } else {
            throw new Error("index cannot be null or empty");
        }
    }

    /**
     * A method to insert value into the index table
     *
     * @param value
     */
    public void insert(int value) {
        int i = binarySearch(value);
        list[i].add(value);
    }

    /**
     * Binary search to find out which block the key is in
     *
     * @param key
     * @return
     */
    public int binarySearch(int key) {
        int low = 0;
        int high = index.length;
        int mid = -1;
        while (low <= high) {
            mid = (low + high) / 2;
            if (index[mid] > key) {
                high = mid - 1;
            } else //if the middle element is less than or equal to the key
            //then return the block number
            {
                low = mid + 1;
            }
        }
        return low;
    }

    /**
     *
     * @param data
     * @return
     */
    public boolean search(int key) {
        //find out which block frist
        int i = binarySearch(key);
        //linear search the block for the target 
        for (int j = 0; j < list[i].size(); j++) {
            if (key == (int) list[i].get(j)) {
                System.out.println(String.format("the key is in : Block:%d  Index:%d", i + 1, j + 1));
                return true;
            }
        }
        return false;
    }

    /**
     * print out each element for each block
     *
     */
    public void printAll() {
        for (int i = 0; i < list.length; i++) {
            ArrayList l = list[i];
            System.out.print("ArrayList[" + i + "]: ");
            for (int j = 0; j < l.size(); j++) {
                System.out.print(l.get(j) + "    ");
            }
            System.out.println();
        }
    }

    /*
    *Testing with an array
     */
    public static void main (String[] args){
        int[] index = {10, 20, 30};
        BlockSearch blocksearch = new BlockSearch(index);
        //insert some numbers 
        blocksearch.insert(3);
        blocksearch.insert(6);
        blocksearch.insert(2);
        blocksearch.insert(12);
        blocksearch.insert(15);
        blocksearch.insert(25);

        //print the current partitions 
        blocksearch.printAll();

        System.out.println("find 12   Result: " + blocksearch.search(12));
        System.out.println("find 25  Result: " + blocksearch.search(25));
    }
}
