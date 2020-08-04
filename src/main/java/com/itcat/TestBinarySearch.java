package com.itcat;

import jdk.nashorn.internal.objects.annotations.Constructor;

/**
 * 折半查找
 */
public class TestBinarySearch {
    public static void main(String[] args) {
        //定义一个数组
        int [] array = {34,45,56,75,76,87,89,89,97,100};
        int item = 87;
        //使用折半查找
        int index = binarySearch(array, item);
        //输出索引
        System.out.println(index);
    }

    private static int binarySearch(int [] array, int key){
        //定义low和high
        int low = 0;
        int high = array.length - 1;
        //使用进行折半查找
        while (low <= high){
            //计算中间索引
            int mid = (low + high)/2;
            if(array[mid] < key){
                low = mid + 1;
            }else if (array[mid] > key){
                high = mid - 1;
            }else {return mid;}
        }
        //low大于high时，说明不存在
            return -1;
    }
}
