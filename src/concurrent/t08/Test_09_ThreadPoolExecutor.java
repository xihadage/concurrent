/**
 * 线程池
 * 固定容量线程池
 * 线程池底层实现。除ForkJoinPool外，其他常用线程池底层都是使用ThreadPoolExecutor实现的。
 * public ThreadPoolExecutor
 * (int corePoolSize, // 核心容量，创建线程池的时候，默认有多少线程。也是线程池保持的最少线程数
 * int maximumPoolSize, // 最大容量，线程池最多有多少线程
 * long keepAliveTime, // 生命周期，0为永久。当线程空闲多久后，自动回收。
 * TimeUnit unit, // 生命周期单位，为生命周期提供单位，如：秒，毫秒
 * BlockingQueue<Runnable> workQueue // 任务队列，阻塞队列。注意，泛型必须是Runnable
 );
 使用场景： 默认提供的线程池不满足条件时使用。如：初始线程数据4，最大线程数200，线程空闲周期30秒。

 */
package concurrent.t08;

import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class Test_09_ThreadPoolExecutor {
	
	public static void main(String[] args) {
		// 模拟fixedThreadPool， 核心线程5个，最大容量5个，线程的生命周期无限。
		ExecutorService service = 
				new ThreadPoolExecutor(5, 5, 0L, TimeUnit.MILLISECONDS, 
						new LinkedBlockingQueue<Runnable>());
		
		for(int i = 0; i < 6; i++){
			service.execute(() -> {
                try {
                    TimeUnit.MILLISECONDS.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName() + " - test executor");
            });
		}
		
		System.out.println(service);
		
		service.shutdown();
		System.out.println(service.isTerminated());
		System.out.println(service.isShutdown());
		System.out.println(service);
		
		try {
			TimeUnit.SECONDS.sleep(2);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		service.shutdown();
		System.out.println(service.isTerminated());
		System.out.println(service.isShutdown());
		System.out.println(service);
		
	}

}
