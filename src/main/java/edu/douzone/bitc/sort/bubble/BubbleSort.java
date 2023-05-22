package edu.douzone.bitc.sort.bubble;

import java.util.Arrays;

/**
 * Some description here.
 *
 * @author : 강명관
 * @since : 1.0
 **/
public class BubbleSort {
    public static void main(String[] args) {
        int[] arr = {20, 9, 3, 16, 50};

        for (int i = 0; i < arr.length; i++) {
            for (int j = i + 1; j < arr.length; j++) {
                int temp = arr[i];
                if (temp > arr[j]) {
                    arr[i] = arr[j];
                    arr[j] = temp;
                }
            }
        }

        System.out.println(Arrays.toString(arr));

    }
}
