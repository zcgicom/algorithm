package com.itcat;

/**
 * 05-String、StringBuffer和StringBuilder
 */
public class TestString {
    /**
     * 1.String与StringBuffer以及StringBuilder的区别：
     *   String是final类型，每次声明的都是不可变的对象，而StringBuffer和StringBuilder声明的对象都是可变的
     *   所以每次使用String都会产生新的String对象，然后将指针指向新的String对象
     * 2.StringBuffer和StringBuilder都是在原有的独享上进行操作，所以如果需要改变字符串的内容，建议采用这两个
     * 3.StringBuffer和StringBuilder的区别：
     *   StringBuffer是线程安全的，StringBuilder是线程不安全的
     *   StringBuilder性能更高，所以在开发中，优先采用StringBuilder
     *   性能比较：StringBuilder > StringBuffer > String
     * 注意：
     * 什么是线程安全？
     * 1.多线程环境下
     * 2.对该对象的访问不需要额外添加同步控制
     * 3.操作的数据结果依然正确
     *
     * 什么时候考虑线程安全？
     * 多个线程同时访问同一个资源时，考虑线程安全
     *
     * StringBuffer的每个方法都加了Synchronized关键字实现线程安全
     * 开发中，经常使用StringBuilder解决什么问题？
     * 字符拼接问题：stringBuilder.append("");
     *
     * 通常的书写方式是在方法内书写该拼接方法：
     * 方法内:
     *      //由于方法会在栈中都是私有占有栈内存，因此多个线程分别对应一个资源，所以使用该方式不存在线程不安全的问题
     *      //换句话说，多个线程对应一个资源时是线程不安全，一个线程对应一个资源时是线程安全的(不是共享的资源不会存在线程安全)，
     *      //也就是说每个线程访问一个StringBuilder方法
     *      //ArrayList、HashMap也是这样用
     *
     *      StringBuilder sb = new StringBuilder();
     *      sb.append("");
     */

}
