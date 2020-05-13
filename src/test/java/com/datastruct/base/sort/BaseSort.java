package com.datastruct.base.sort;

import java.util.Arrays;

/**
 * 基础排序算法
 *
 * @author phd
 * @date 2020-05-13.
 */
public class BaseSort {

    public static void main(String[] args) {
        int[] arr = new int[]{4, 5, 6, 1, 3, 2};
        BaseSort sort = new BaseSort();

        printArr(arr);
//        sort.bubbleSort(arr);
//        sort.insertSort(arr);
        sort.selectSort(arr);
        printArr(arr);
    }

    private void selectSort(int[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            int min = arr.length - 1;
            for (int j = arr.length - 2; j >= i; j--) {
                if (arr[j] < arr[min]) {
                    min = j;
                }
            }
            if (min != i) {
                swapValue(arr, min, i);
            }
        }
    }

    private void insertSort(int[] arr) {
        for (int i = 1; i < arr.length; i++) {
            int index = i;
            int value = arr[i];
            for (int j = i - 1; j >= 0; j--) {
                if (arr[j] > value) {
                    arr[j + 1] = arr[j];
                    index = j;
                } else {
                    break;
                }
            }
            if (index != i) {
                arr[index] = value;
            }
        }
    }


    private void bubbleSort(int[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            boolean hasSwap = false;
            for (int j = 0; j < arr.length - i - 1; j++) {
                if (arr[j] > arr[j + 1]) {
                    swapValue(arr, j, j + 1);
                    hasSwap = true;
                }
            }
            if (!hasSwap) {
                break;
            }
        }
    }

    private void swapValue(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    private static void printArr(final int[] arr) {
        System.out.println(Arrays.toString(arr));
    }
}
