package chapterD;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class Test {

    /**
     * 线程池—>一种并发框架,优点有以下三个
     * 降低资源消耗,通过重复利用已创建的线程降低创建和销毁线程的消耗
     * 提高响应速度,当任务到达时,可以不需要等到线程创建完才执行
     * 提高线程的可管理性,线程是稀缺资源,如果无限制地创建,会消耗大量的系统资源和降低系统稳定性,使用线程池可以统一管理
     * @param args
     */
    /**
     * 线程池工作流程
     * 1->检查核心线程池中的线程是否都在执行任务,如果没有则创建一个新的工作线程来工作,否则进入步骤二
     * 2->检查工作队列是否已满,如果没满则加入工作队列,否则进入步骤三
     * 3->检查非核心线程池中的线程是否都在执行任务,如果没有则创建一个新的工作线程来工作,否则交给饱和策略来处理任务
     * @param args
     */
    /**
     * 创建线程池参数说明
     * corePoolSize->核心线程最大数量
     * maximumPoolSize->最大线程数量
     * keepAliveTime->非核心线程执行完任务后存户的时间
     * workQueue->任务队列
     * handler->饱和策略
     * @param args
     */
    /**
     * workQueue任务队列类别
     * ArrayBlockingQueue->是一个基于数组结构的有界阻塞队列，此队列按FIFO（先进先出）原 则对元素进行排序
     * LinkedBlockingQueue->一个基于链表结构的阻塞队列，此队列按FIFO排序元素，吞吐量通 常要高于ArrayBlockingQueue
     * SynchronousQueue->一个不存储元素的阻塞队列。每个插入操作必须等到另一个线程调用 移除操作，否则插入操作一直处于阻塞状态
     * PriorityBlockingQueue->一个具有优先级的无限阻塞队列
     * @param args
     */
    /**
     * handler饱和策略种类
     * AbortPolicy->直接抛出异常。
     * CallerRunsPolicy->只用调用者所在线程来运行任务
     * DiscardOldestPolicy->丢弃队列里最近的一个任务，并执行当前任务
     * DiscardPolicy->不处理，丢弃掉
     * @param args
     */
    public static void main(String[] args) {
        testAbortPolicy();
        testCallerRunsPolicy();
        testDiscardOldestPolicy();
        testDiscardPolicy();
    }

    public static void testAbortPolicy(){
        ThreadPoolExecutor executor = new ThreadPoolExecutor(1,
                2,
                10,
                TimeUnit.SECONDS,
                new ArrayBlockingQueue<Runnable>(3),
                new ThreadPoolExecutor.AbortPolicy());
        for (int i = 0; i < 10; i++) {
            executor.execute(new MyRunnable("AbortPolicy" + i));
        }
    }

    public static void testCallerRunsPolicy(){
        ThreadPoolExecutor executor = new ThreadPoolExecutor(1,
                2,
                10,
                TimeUnit.SECONDS,
                new ArrayBlockingQueue<Runnable>(3),
                new ThreadPoolExecutor.CallerRunsPolicy());
        for (int i = 0; i < 10; i++) {
            executor.execute(new MyRunnable("CallerRunsPolicy" + i));
        }
    }

    public static void testDiscardOldestPolicy(){
        ThreadPoolExecutor executor = new ThreadPoolExecutor(1,
                2,
                10,
                TimeUnit.SECONDS,
                new ArrayBlockingQueue<Runnable>(3),
                new ThreadPoolExecutor.DiscardOldestPolicy());
        for (int i = 0; i < 10; i++) {
            executor.execute(new MyRunnable("DiscardOldestPolicy" + i));
        }
    }

    public static void testDiscardPolicy(){
        ThreadPoolExecutor executor = new ThreadPoolExecutor(1,
                2,
                10,
                TimeUnit.SECONDS,
                new ArrayBlockingQueue<Runnable>(3),
                new ThreadPoolExecutor.DiscardPolicy());
        for (int i = 0; i < 10; i++) {
            executor.execute(new MyRunnable("DiscardPolicy" + i));
        }
    }

}

class MyRunnable implements Runnable {

    private String name;

    public MyRunnable(String name) {
        this.name = name;
    }

    @Override
    public void run() {
        System.out.println(name + "线程执行");
        while (true) {

        }
    }
}


