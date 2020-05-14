package com.datastruct.base.sort;

import java.util.Arrays;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 基础排序算法
 *
 * @author phd
 * @date 2020-05-13.
 */
public class BaseSort {

    public static void main(String[] args) {
//        int[] arr = new int[]{4, 5, 6, 1, 3, 2};
        BaseSort sort = new BaseSort();
//
//        printArr(arr);
////        sort.bubbleSort(arr);
////        sort.insertSort(arr);
//        sort.selectSort(arr);
//        printArr(arr);
//
//        int[] arr2 = new int[]{1, 2, 3, 4, 5, 6, 8, 7};
//        arr2 = sort.mergeSort(arr2, 0, arr2.length - 1);
//        printArr(arr2);

        int[] arr3 = new int[]{16, 4, 9, 10, 20, 7, 12, 8, 5, 1, 15, 24, 17, 6, 3};
        sort.quickSort(arr3, 0, arr3.length - 1);
        printArr(arr3);
    }


    public void quickSort(int[] arr, int left, int right) {
        int l, r, s;
        while (right > left) {
            l = left;
            r = right;
            s = arr[left];
            while (l < r) {
                while (arr[r] > s) {
                    r--;
                }
                arr[l] = arr[r];
                while (s >= arr[l] && l < r) {
                    l++;
                }
                arr[r] = arr[l];
            }
            arr[l] = s;
            quickSort(arr, left, l - 1);
            left = l + 1;
        }
    }


    private static final AtomicInteger count = new AtomicInteger();

    /**
     * 并归排序
     * 排序思想：
     * 如果要排序一个数组，我们先把数组从中间分成前后两部分，然后对前后两部分分别排序，再将排好序的两部分合并在一起，这样整个数组就都有序了。
     *
     * @param arr 数组.
     * @param l   数组长度.
     * @param h   .
     */
    public int[] mergeSort(int[] arr, int l, int h) {
        System.out.print("第" + count.incrementAndGet() + "次 l=" + l + ",h=" + h);
        printArr(arr);
        if (l == h) {
            return new int[]{arr[l]};
        }
        int mid = l + (h - l) / 2;
        int[] leftArr = mergeSort(arr, l, mid); //左有序数组
        int[] rightArr = mergeSort(arr, mid + 1, h); //右有序数组
        int[] newNum = new int[leftArr.length + rightArr.length]; //新有序数组

        int m = 0, i = 0, j = 0;
        while (i < leftArr.length && j < rightArr.length) {
            newNum[m++] = leftArr[i] < rightArr[j] ? leftArr[i++] : rightArr[j++];
        }
        System.out.println(String.format("l=%d, h=%d,i=%d, j=%d, leftArr=%s, rightArr=%s", l, h, i, j, Arrays.toString(leftArr), Arrays.toString(rightArr)));
        while (i < leftArr.length)
            newNum[m++] = leftArr[i++];
        while (j < rightArr.length)
            newNum[m++] = rightArr[j++];
        System.out.println(String.format("l=%d, h=%d,i=%d, j=%d, leftArr=%s, rightArr=%s, number=%s", l, h, i, j, Arrays.toString(leftArr), Arrays.toString(rightArr), Arrays.toString(newNum)));
        return newNum;
    }

    /**
     * 选择排序
     * 排序思想:
     * 类似于插入排序算法
     * 1.每次会从未排序区间中找到最小的元素，将其放到已排序区间的末尾，初始化时已排序区间为0，未排序区间为整个数组
     *
     * @param arr 需要排序的数组.
     */
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

    /**
     * 插入排序
     * 排序思想：
     * 1. 首先将数组中的元素分为两个区间，已排序区间和未排序区间。初始化时已排序区间只有1一个元素，就是数组的第一个元素
     * 2. 去未排序区间中的元素，在已排序区间中找到适合的位置插入，并保证区间数组一直有序。
     * 比较大小关系，满足则让前一个元素到自己的位置来，腾出位置给元素插入
     * 3. 重复这个过程，直到未排序区间中元素为空，排序结束.
     *
     * @param arr 需要排序的数组.
     */
    private void insertSort(int[] arr) {
        for (int i = 1; i < arr.length; i++) {
            int index = i;
            int value = arr[i];
            for (int j = i - 1; j >= 0; j--) {
                if (arr[j] > value) {
                    arr[j + 1] = arr[j];
                    index = j;
                } else {
                    // 因为前面的都是已经排过序了，如果比前一个元素还小就证明前面的数据都不满足要求了，退出本次循环
                    break;
                }
            }
            if (index != i) {
                arr[index] = value;
            }
        }
    }


    /**
     * 冒泡排序
     * 排序思想:
     * 1. 操作相邻的两个元素，比较是否满足大小关系要求，不满足则交换位置
     * 一次冒泡至少让一个元素移动到它应该在的地方。
     * 2. 重复n次，就完成了n个元素的排序.
     *
     * @param arr 需要排序的数组.
     */
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

    /**
     * 交换数据
     *
     * @param arr 数组
     * @param i   交换下标1
     * @param j   交换下标2.
     */
    private void swapValue(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    /**
     * 打印数组
     *
     * @param arr 数组.
     */
    private static void printArr(final int[] arr) {
        System.out.println(Arrays.toString(arr));
    }
}
