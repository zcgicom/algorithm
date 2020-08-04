package com.itcat.Thread;

/**
 *24-线程的生命周期
 *线程的生命周期详细说明，共有6种状态：
 * new runnable blocked waiting time waiting terminated
 * 1.当进入synchronized同步代码块或同步方法时，且没有获取到锁，线程就进入了blocked状态，直到锁被释放，重新进入Runnable状态
 * 2.当线程调用wait()或join时，线程就会进入到waiting状态，当调用notify或notifyAll时，或者join的线程执行结束后，就会进入Runnable状态
 * 3.当线程调用sleep(time)，或者wait(time)时，进入time waiting状态；当休眠时间结束，或者调用notify或notifyAll时就会重新进入Runnable状态
 * 4.程序执行结束后，线程进入terminated状态
 * 注意：
 *      blocked，waiting，time waiting统称为阻塞状态，而就绪状态和运行状态都表现为Runnable状态
 */
public class TestThreadState {
    public static void main(String[] args) throws InterruptedException {
        Thread th = new Thread(new Task());
        System.out.println(th.getState());//获取线程当前状态，//new
        th.start();
        System.out.println(th.getState());//Runnable
        //保险起见，让主线程睡眠10ms
        Thread.sleep(10);
        System.out.println(th.getState());//terminated(终止状态/dead)

        System.out.println("=================================");
        //测试阻塞状态
        blockTask btask = new blockTask();
        Thread th1 = new Thread(btask);
        Thread th2 = new Thread(btask);
        th1.start();
        th2.start();
        //严格说来，th1和th2的执行顺序不一定，此处假设th1先执行
        System.out.println(th1.getState());//RUNNABLE
        System.out.println(th2.getState());//BLOCKED
        Thread.sleep(10);
        System.out.println(th1.getState());//TIME_WAITING
        Thread.sleep(1000);
        System.out.println(th2.getState());//WAITING
    }
}

//实现Runnable,创建一个任务
class Task implements Runnable{
    public void run(){
        for (int i = 0; i < 100; i++) {
            System.out.println(i);
        }
    }
}

//实现Runnable,创建一个任务，该任务用于测试阻塞状态
class blockTask implements Runnable{
    public void run() {
        synchronized (this){
            //一个线程获得了锁后，线程会进入block状态
            try {
                //目的是让线程金融waiting time状态
                Thread.sleep(1000);
                //进入waiting状态
                wait();
            }catch (InterruptedException e){
                e.printStackTrace();
            }
        }
    }
}
