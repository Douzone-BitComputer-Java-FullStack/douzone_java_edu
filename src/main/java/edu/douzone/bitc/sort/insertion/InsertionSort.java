package edu.douzone.bitc.sort.insertion;

import java.util.Arrays;

/**
 * Some description here.
 *
 * @author : 강명관
 * @since : 1.0
 **/
public class InsertionSort {
    public static void main(String[] args) {
        int[] arr = {20, 9, 3, 16, 50};

        for (int i = 1; i < arr.length; i++) {
            int key = arr[i];
            int j = i - 1;

            while ((j >= 0) && arr[j] > key) {
                arr[j + 1] = arr[j];
                j--;
            }

            arr[j + 1] = key;
        }

        System.out.println(Arrays.toString(arr));
    }
}
