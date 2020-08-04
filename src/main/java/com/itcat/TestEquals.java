package com.itcat;

import com.itcat.entity.Student;

/**
 * 03-equals、final和==
 */
public class TestEquals {
    public static void main(String[] args) {
        //1.==比较基本数据类型时(比较的是数值)，比较引用类型时(比较的是引用指向的地址)
        //2.equals默认比较的是地址，因为这个方法的最初定义是在Object上，默认实现的是地址比较
        //3.如果需要比较String的内容，可以采取重写equals方法
        String s1 = new String("zs");
        String s2 = new String("zs");
        System.out.println(s1==s2);//false 此时new了两个对象，所以比较的是对象的地址

        String s3 = "zs";
        String s4 = "zs";
        System.out.println(s3==s4);//true 此时两个变量是常量池中的数据
        System.out.println(s3==s1);//false
        String s5 = "zszs";
        String s6 = s3 + s4;
        System.out.println(s5==s6);//false 此时会通过new String的方式创建s6

        final String s7 = "zs";
        final String s8 = "zs";
        String s9 = s7 + s8;
        System.out.println(s5==s9); //true s7和s8均是常量，s9会被保存为常量
        final String s10 = s3 + s4;//s3和s4均为变量，因此s3+s4会通过new String方法创建对象，加了final后只是影响了s10
        System.out.println(s5==s10);//false //s3和s4一加new，则表示s10和常量比较为false

        //4.final修饰类时，表示类不可变，不可继承；final修饰方法时，表示该方法不可重写；final修饰变量时，表示该变量为常量
        //注意：
        // final修饰基本数据类型时，表示这个值本身不可修改；final修饰引用类型时，表示引用的指向不能修改
        final Student student = new Student("Andy",1);
        System.out.println(student.getAge());
        student.setAge(18);//注意，由于是final关键字修饰，所以这个语句可以执行！但是其指向的地址不可修改。
        System.out.println(student.getAge());

//        student = new Student();//这个修改指向的操作时不对的！
    }
}
