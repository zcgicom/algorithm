package com.itcat;

/**
 * 递归实现ArrayList
 */
public class TestBinaryRecursion {
    public static void main(String[] args) {
        //定义一个数组
        int [] array = {34,45,56,75,76,87,89,89,97,100};
        int item = 87;
        //定义low和high
        int low = 0;
        int high = array.length - 1;
        //使用折半查找
        int index = rec(array, item, low, high);
        //输出索引
        System.out.println(index);
    }

    private static int rec(int [] array, int key, int low,int high) {
        int mid = (low+high)/2;
        if(low>high){
            return -1;
        }
        if(array[mid]>key){
            return rec(array,key,low,mid-1);
        }else if (array[mid]<key){
            return rec(array,key,mid+1,high);
        }else {
            return mid;
        }
    }
}
