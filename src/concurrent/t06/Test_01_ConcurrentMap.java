/**
 * 并发容器 - ConcurrentMap
 * ConcurrentHashMap:底层哈希实现的同步Map(Set)。效率高，线程安全。使用系统底层技术实现线程安全。量级较synchronized低。key和value不能为null。
 * ConcurrentSkipListMap:底层跳表（SkipList）实现的同步Map(Set)。有序，效率比ConcurrentHashMap稍低
 */
package concurrent.t06;

import java.util.Hashtable;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentSkipListMap;
import java.util.concurrent.CountDownLatch;

public class Test_01_ConcurrentMap {
	
	public static void main(String[] args) {
//		final Map<String, String> map = new Hashtable<>();

        final Map<String, String> map = new ConcurrentHashMap<>();

		final Random r = new Random();
		Thread[] array = new Thread[100];
		final CountDownLatch latch = new CountDownLatch(array.length);
		
		long begin = System.currentTimeMillis();
		for(int i = 0; i < array.length; i++){
			array[i] = new Thread(() -> {
                for(int j = 0; j < 10000; j++){
                    map.put("key"+r.nextInt(100000), "value"+r.nextInt(100000));
                }
                latch.countDown();
            });
		}
		for(Thread t : array){
			t.start();
		}
		try {
			latch.await();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		long end = System.currentTimeMillis();
		System.out.println("执行时间为 ： " + (end-begin) + "毫秒！");
	}

}
