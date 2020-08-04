package com.itcat;

import java.util.HashMap;

/**
 * 16-HashTable、HashMap和ConcurrentHashMap
 *
 * 1.效率低下的HashTable -- Hashtable是其唯一公开的子类
 *      -1. Hashtable继承的是Dictionary，HashTable使用synchronized来保证线程安全，但在线程竞争激烈的情况下HashTable的效率非常低下。
 *      -2. 因为当一个线程访问HashTable的同步方法时，其他线程访问HashTable的同步方法时，可能会进入阻塞或轮询状态。
 *      -3. 如线程1使用put进行添加元素，线程2不但不能使用put方法添加元素，并且也不能使用get方法来获取元素，所以竞争越激烈效率越低。
 * 2.HashMap
 * 为何出现死循环(死锁)：
 * 　　1.HashMap是非线程安全的，在并发场景中如果不保持足够的同步，就有可能在执行HashMap.get时进入死循环，将CPU的消耗到100%。
 * 　　2.HashMap采用链表解决Hash冲突。因为是链表结构，那么就很容易形成闭合的链路，这样在循环的时候只要有线程对这个HashMap进行get操作就会产生死循环，
 * 　　3.单线程情况下，只有一个线程对HashMap的数据结构进行操作，是不可能产生闭合的回路的。
 * 　　4.只有在多线程并发的情况下才会出现这种情况，那就是在put操作的时候，如果size>initialCapacity*loadFactor，hash表进行扩容，那么这时候HashMap就会进行rehash操作，随之HashMap的结构就会很大的变化。很有可能就是在两个线程在这个时候同时触发了rehash操作，产生了闭合的回路。
 * 　　5.推荐使用currentHashMap
 *
 * 多线程下[HashMap]的问题：
 *      1.多线程put操作后，get操作导致死循环。
 *      2.多线程put非NULL元素后，get操作得到NULL值。
 *      3.多线程put操作，导致元素丢失。
 * 3.ConcurrentHashMap
 *      cocurrentHashMap的底层机制:
 * 　　   -1. ConcurrentHashMap的读取并发，因为在读取的大多数时候都没有用到锁定，所以读取操作几乎是完全的并发操作，而写操作锁定的粒度又非常细。
 *        -2. 只有在求size等操作时才需要锁定整个表。而在迭代时，ConcurrentHashMap使用了不同于传统集合的快速失败迭代器的弱一致迭代器。
 *        -. 在这种迭代方式中，当iterator被创建后集合再发生改变就不再是抛出ConcurrentModificationException，
 *        -. 取而代之的是在改变时new新的数据从而不影响原有的数据，iterator完成后再将头指针替换为新的数据，这样iterator线程可以使用原来老的数据，而写线程也可以并发的完成改变，
 *        -. 更重要的，这保证了多个线程并发执行的连续性和扩展性，是性能提升的关键。
 *      ConcurrentHashMap的锁分段技术:
 *        -1. HashTable容器在竞争激烈的并发环境下表现出效率低下的原因，是因为所有访问HashTable的线程都必须竞争同一把锁，
 *        -. 那假如容器里有多把锁，每一把锁用于锁容器其中一部分数据，那么当多线程访问容器里不同数据段的数据时，线程间就不会存在锁竞争，
 *        -. 从而可以有效的提高并发访问效率，这就是ConcurrentHashMap所使用的锁分段技术，
 *        -2. 首先将数据分成一段一段的存储，然后给每一段数据配一把锁，当一个线程占用锁访问其中一个段数据的时候，其他段的数据也能被其他线程访问。
 *
 * 注意：
 *      Hashtable的实现方式---锁整个hash表；而ConcurrentHashMap的实现方式---锁桶（或段）
 */
public class TestHashMap {
    public static void main(String[] args) {
        //测试HashMap死锁，多线程下偶尔会产生死锁
        new TestHashMap();
    }

    private HashMap map = new HashMap();
    int count = 100000;
    public TestHashMap() {
        Thread t1 = new Thread() {
            public void run() {
                for (int i = 0; i < count; i++) {
                    map.put(new Integer(i), i);
                }
                System.out.println("t1 over");
            }
        };

        Thread t2 = new Thread() {
            public void run() {
                for (int i = 0; i < count; i++) {
                    map.put(new Integer(i), i);
                }

                System.out.println("t2 over");
            }
        };

        Thread t3 = new Thread() {
            public void run() {
                for (int i = 0; i < count; i++) {
                    map.put(new Integer(i), i);
                }

                System.out.println("t3 over");
            }
        };

        Thread t4 = new Thread() {
            public void run() {
                for (int i = 0; i < count; i++) {
                    map.put(new Integer(i), i);
                }

                System.out.println("t4 over");
            }
        };

        Thread t5 = new Thread() {
            public void run() {
                for (int i = 0; i < count; i++) {
                    map.put(new Integer(i), i);
                }

                System.out.println("t5 over");
            }
        };

        Thread t6 = new Thread() {
            public void run() {
                for (int i = 0; i < count; i++) {
                    map.get(new Integer(i));
                }

                System.out.println("t6 over");
            }
        };

        Thread t7 = new Thread() {
            public void run() {
                for (int i = 0; i < count; i++) {
                    map.get(new Integer(i));
                }

                System.out.println("t7 over");
            }
        };

        Thread t8 = new Thread() {
            public void run() {
                for (int i = 0; i < count; i++) {
                    map.get(new Integer(i));
                }

                System.out.println("t8 over");
            }
        };

        Thread t9 = new Thread() {
            public void run() {
                for (int i = 0; i < count; i++) {
                    map.get(new Integer(i));
                }

                System.out.println("t9 over");
            }
        };

        Thread t10 = new Thread() {
            public void run() {
                for (int i = 0; i < count; i++) {
                    map.get(new Integer(i));
                }

                System.out.println("t10 over");
            }
        };

        t1.start();
        t2.start();
        t3.start();
        t4.start();
        t5.start();

        t6.start();
        t7.start();
        t8.start();
        t9.start();
        t10.start();
    }
}

