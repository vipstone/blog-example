package com.example.structure;

import org.apache.commons.lang3.ArrayUtils;

import java.util.Arrays;
import java.util.Collections;

/**
 * 查找数组中的最大值
 */
public class ArrayMax {
    public static void main(String[] args) {
        int[] arr = {3, 7, 2, 1, -4};
        int max = findMaxByFor(arr); // 查找最大值
        int max2 = findMaxBySort(arr); // 根据 Arrays.sort 查找最大值
        int max3 = findMaxByStream(arr); // 根据 stream 查找最大值
        int max4 = findMaxByCollections(arr); // 根据 Collections 查找最大值
        int max5 = findMaxByRecursive(arr, 0, arr.length - 1, 0); // 根据 Collections 查找最大值
        System.out.println("最大值是：" + max);
    }

    /**
     * 根据递归查询最大的值
     * @param arr  待查询数组
     * @param head 最前面的元素的下标
     * @param last 最末尾的元素的下标
     * @param max  （临时）最大值
     * @return 最大值
     */
    private static int findMaxByRecursive(int[] arr, int head, int last, int max) {
        if (head == last) {
            // 递归完了，直接返回结果
            return max;
        } else {
            if (arr[head] > arr[last]) {
                max = arr[head]; // 赋最大值
                // 从后往前移动递归
                return findMaxByRecursive(arr, head, last - 1, max);
            } else {
                max = arr[last]; // 赋最大值
                // 从前往后移动递归
                return findMaxByRecursive(arr, head + 1, last, max);
            }
        }
    }

    /**
     * 根据 Collections 查找最大值
     * @param arr 待查询数组
     * @return 最大值
     */
    private static int findMaxByCollections(int[] arr) {
        return Collections.max(Arrays.asList(ArrayUtils.toObject(arr)));
    }

    /**
     * 根据 stream 查找最大值
     * @param arr 待查询数组
     * @return 最大值
     */
    private static int findMaxByStream(int[] arr) {
        return Arrays.stream(arr).max().getAsInt();
    }

    /**
     * 根据 Arrays.sort 查找最大值
     * @param arr 待查询数组
     * @return 最大值
     */
    private static int findMaxBySort(int[] arr) {
        Arrays.sort(arr);
        return arr[arr.length - 1];
    }

    /**
     * 通过 for 循环查找最大值
     * @param arr 待查询数组
     * @return 最大值
     */
    private static int findMaxByFor(int[] arr) {
        int max = 0; // 最大值
        for (int item : arr) {
            if (item > max) { // 当前值大于最大值，赋值为最大值
                max = item;
            }
        }
        return max;
    }
}
