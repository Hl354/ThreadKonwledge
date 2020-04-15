package chapterC;

import java.util.concurrent.ConcurrentHashMap;

public class Test {

    private static ConcurrentHashMap<String, Integer> map = new ConcurrentHashMap();
    /**
     * HashMap->非线程安全。HashTable->线程安全,使用synchronized同步。
     * ConcurrentMap->线程安全,java8之前使用锁分段技术(非绝对安全)提高访问效率,后面重新使用synchronized。
     * 锁分段->将数据分为一段一段,对每一段数据上锁,不同的线程访问不同的数据区域时,则不会存在竞争。
     * @param args
     */
    /**
     * HashMap非线程安全的原因
     * 1->在JDK1.8之前是由于在数据扩容时,由于采用的是头插法(每次将插入节点变为头节点的next),所以会产生一个死循环
     * 2->在JDK1.8中上述操作做了优化,由头插法变为尾插法(插入到尾部),会发生数据覆盖问题。
     * @param args
     */
    public static void main(String[] args) {
        execution();
        execution();
    }

    public static void execution() {
        for (int i = 0; i < 10; i++) {
            int finalI = i;
            Thread thread = new Thread(new Runnable() {
                @Override
                public void run() {
                    String key = finalI + "";
                    addData(key);
                    System.out.println(key + ":" + map.get("" + finalI));
                }
            });
            thread.start();
        }
    }

    public static void addData(String key) {
        int value = map.get(key) == null ? 0 : map.get(key) + 1;
        map.put(key, value);
    }

}
