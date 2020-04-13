package chapterB;

import java.util.concurrent.locks.ReentrantLock;

public class Test {

    private static int num = 0;
    private static ReentrantLock lock = new ReentrantLock();

    /**
     * 锁的四种状态(级别从低到高):无锁->偏向锁->轻量级锁->重量级锁。
     * 锁升级:只能级别低的锁升级到级别高的锁。
     * 乐观锁:认为拿锁的线程不会去修改数据,但是在更新的时候会判断在此期间别的线程有没有更新数据,一般使用CAS或者版本号机制。
     * 悲观锁:认为拿锁的线程都会去修改数据,所以每次拿数据都会上锁。
     * 可重入锁:当前获取锁的线程可以重复的再次获取锁,优点是可以避免死锁。
     * 排他锁:同一时刻只允许一个线程访问。
     * 读写锁:由读锁和写锁组成,写线程访问时,其它线程均被阻塞。
     * @param args
     */
    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            Thread thread = new Thread(new Runnable() {
                @Override
                public void run() {
                    addOne();
                }
            });
            thread.start();
        }

        for (int i = 0; i < 10; i++) {
            Thread thread = new Thread(new Runnable() {
                @Override
                public void run() {
                    addOne();
                }
            });
            thread.start();
        }
    }

    public static void addOne() {
        lock.lock();
        num++;
        System.out.println(num);
        lock.unlock();
    }

}
