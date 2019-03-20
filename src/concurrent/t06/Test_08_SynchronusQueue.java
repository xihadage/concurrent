/**
 * 并发容器 - SynchronousQueue
 * 同步队列，是一个容量为0的队列。是一个特殊的TransferQueue。
 * 必须现有消费线程等待，才能使用的队列。
 * add方法，无阻塞。若没有消费线程阻塞等待数据，则抛出异常。
 * put方法，有阻塞。若没有消费线程阻塞等待数据，则阻塞。

 */
package concurrent.t06;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.TimeUnit;

public class Test_08_SynchronusQueue {
	
	BlockingQueue<String> queue = new SynchronousQueue<>();
	
	public static void main(String[] args) {
		final Test_08_SynchronusQueue t = new Test_08_SynchronusQueue();
		
		new Thread(() -> {
            try {
                System.out.println(Thread.currentThread().getName() + " thread begin " );
                try {
                    TimeUnit.SECONDS.sleep(2);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName() + " - " + t.queue.take());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "output thread").start();
		
		/*try {
			TimeUnit.SECONDS.sleep(3);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}*/
		// t.queue.add("test add");
		try {
			t.queue.put("test put");
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		System.out.println(Thread.currentThread().getName() + " queue size : " + t.queue.size());
	}

}
