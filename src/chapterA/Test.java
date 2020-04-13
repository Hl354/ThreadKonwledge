package chapterA;

public class Test {

    static volatile int volatileNum = 1;
    static int synchronizedNum = 1;

    public static void main(String[] args) {
        /**
         * volatile是轻量级的synchronized,在多处理器开发中保证了共享变量的可见性以及禁止指令重排,且不会引发上下文调度。
         * 可见性是指当一个线程修改一个变量时,另一个线程能够读到修改后的值。
         * volatile的底层实现原理为当对声明了volatile关键字的变量进行写操作时,JVM会向处理器发送一条Lock前缀的指令,
         * 而Lock前缀指令会引起处理器缓存回写到内存,而一个处理器缓存回写到内存会引起其它处理器缓存无效(缓存一致性原则-嗅探技术)。
         */
        testVolatile();
        /**
         * synchronized的原理是JVM基于进入和退出Monitor对象来实现方法同步和代码块同步。
         * Monitor是一个原语,也可以说是一个同步机制,操作系统本身并不支持,属于编程语言的范畴。
         * Monitor的重要特点是同一个时刻,只有一个线程/进程可以进入Monitor定义的临界区,无法进入的则被阻塞,一定条件下被唤醒。
         * synchronized用的锁是存在Java对象头里的Mark Word。
         */
        testSynchronized();
    }

    /**
     * 以下代码并不能保证每次获取的都是正常结果,因为volatile只保证可见性,并没有保证原子性。
     * 原子性指的是操作是不可中断的,要么执行,要么不执行。
     */
    public static void testVolatile() {
        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                volatileNum++;
                System.out.println("thread1-" + volatileNum);
            }
        });

        Thread thread2 = new Thread(new Runnable() {
            @Override
            public void run() {
                volatileNum++;
                System.out.println("thread2-" +volatileNum);
            }
        });

        thread1.start();
        thread2.start();
    }

    /**
     * synchronized同步代码块,锁的是对象。
     */
    public static void testSynchronized() {
        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized ("A") {
                    synchronizedNum++;
                    System.out.println("thread1-" + synchronizedNum);
                }
            }
        });

        Thread thread2 = new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized ("A") {
                    synchronizedNum++;
                    System.out.println("thread1-" + synchronizedNum);
                }
            }
        });

        thread1.start();
        thread2.start();
    }
}
