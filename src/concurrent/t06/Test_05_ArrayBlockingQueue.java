/**
 * 并发容器 - ArrayBlockingQueue
 *  有界容器,当容量不足的时候，有阻塞能力
 *  add方法在容量不足的时候，抛出异常。
 *  put方法在容量不足的时候，阻塞等待。
 *  单参数offer方法，不阻塞。容量不足的时候，返回false。当前新增数据操作放弃。
 *  三参数offer方法（offer(value,times,timeunit)），容量不足的时候，阻塞times时长（单位为timeunit），
 *  如果在阻塞时长内，有容量空闲，新增数据返回true。如果阻塞时长范围内，无容量空闲，放弃新增数据，返回false
 */
package concurrent.t06;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

public class Test_05_ArrayBlockingQueue {
	
	final BlockingQueue<String> queue = new ArrayBlockingQueue<>(3);
	
	public static void main(String[] args) {
		final Test_05_ArrayBlockingQueue t = new Test_05_ArrayBlockingQueue();
		
		for(int i = 0; i < 5; i++){
			// System.out.println("add method : " + t.queue.add("value"+i));
			/*try {
				t.queue.put("put"+i);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println("put method : " + i);*/
			// System.out.println("offer method : " + t.queue.offer("value"+i));
			try {
				System.out.println("offer method : " + 
							t.queue.offer("value"+i, 1, TimeUnit.SECONDS));
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
		System.out.println(t.queue);
	}

}
