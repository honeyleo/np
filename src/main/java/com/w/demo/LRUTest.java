package com.w.demo;

/**
 * @copyright SHENZHEN RONG WANG HUI ZHI TECHNOLOGY CORP
 * @author Lyon.liao
 * 创建时间：2015年3月3日
 * 类说明：
 * 
 * 最后修改时间：2015年3月3日
 * 修改内容： 新建此类
 */
public class LRUTest {

	private static LRU<Integer, Integer> lru = new LRU<Integer, Integer>(5000);
	public static void main(String[] args) {
		Thread t1 = new Thread(new Runnable() {
			
			@Override
			public void run() {
				for(int i = 0; i < 2000; i ++) {
					lru.put(i, i);
				}
			}
		});
		Thread t2 = new Thread(new Runnable() {
			
			@Override
			public void run() {
				for(int i = 2000; i < 4000; i ++) {
					lru.put(i, i);
				}
			}
		});
		Thread t3 = new Thread(new Runnable() {
	
			@Override
			public void run() {
				for(int i = 4000; i < 6000; i ++) {
					lru.put(i, i);
				}
			}
		});
		t1.start();
		t2.start();
		t3.start();
		try {
			t1.join();
		} catch(InterruptedException e) {
			e.printStackTrace();
		}
		try {
			t2.join();
		} catch(InterruptedException e) {
			e.printStackTrace();
		}
		try {
			t3.join();
		} catch(InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println(lru.get(4000));
		System.out.println(lru.get(1000));
		System.out.println(lru.get(2000));
		System.out.println(lru.get(2000));
		System.out.println(lru.size());
	}
}
