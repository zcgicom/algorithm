package com.itcat;

/**
 * 08-Integer和Int  缓存、自动装箱和拆箱
 */
public class TestInteger {
    public static void main(String[] args) {
        Integer i1 = new Integer(12);
        Integer i2 = new Integer(12);
        //引用类型(使用new关键字在堆内存开辟了新内存)两个引用对象的地址不同，所以此处为False
        System.out.println(i1==i2);//false

        Integer i3 = 126;
        Integer i4 = 126;//引用对象未超标(其值在-127到128之间)，使用的是缓存数据(也就是此时的126)进行赋值
        //反编译
        Integer.valueOf(126);
        int i5 = 126;
        // 基本数据类型赋值给引用数据类型时，有个自动装箱机制(JDK1.5)，如果引用类型的数值不在(-127到128)之间，
        // 则会进行自动装箱，为该数据开辟新的地址，此时也就相当于对对象进行了“Integer i3 = new Integer(128);”操作

        // 当引用类型与基本类型数据比较时，此处会有个自动拆箱动作，
        // 也就是说这时比较的不再是地址，而是数值，所以这个时候的数据都是126，所以都为true
        System.out.println(i3==i4);//true
        System.out.println(i3==i5);//true

        // 此时由于赋值为128，则会进行自动装箱，为该数据开辟新的地址，
        // 此时也就相当于对对象进行了如下操作：
        // “Integer i6 = new Integer(128);”
        // “Integer i7 = new Integer(128);”
        // 同理，比较i6和i8时又进行了拆箱操作
        Integer i6 = new Integer(128);
        Integer i7 = new Integer(128);
        int i8 = 128;
        System.out.println(i6==i7);//false
        System.out.println(i6==i8);//true
    }
}
