package com.w.np.push;

import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class PushManager {

	private Executor dbWorkers;

	private ExecutorService[] pushWorkers;
	
	private static final int PUSH_THREAD_COUNT = 4;
	private static final int PUSH_THREAD_COUNT_TO_MOD = PUSH_THREAD_COUNT - 1;
	
	private PushManager(){}
	
	private static final PushManager pushManager = new PushManager();
	public static PushManager getInstance() {
		return pushManager;
	}
	public void initWorkers() {
		System.out.println("建立 " + PUSH_THREAD_COUNT + " 个worker");

		// ------------- 初始化 db worker ----------------

		dbWorkers = new ThreadPoolExecutor(PUSH_THREAD_COUNT * 2, 65536 << 1, 30, TimeUnit.SECONDS, new LinkedBlockingQueue<Runnable>());

		// ------------- 初始化 game worker -----------------
		pushWorkers = new ExecutorService[PUSH_THREAD_COUNT];

		for (int i = 0; i < PUSH_THREAD_COUNT; i++) {
			pushWorkers[i] = new PushWorkerThread("PUSH_WORKER_" + i);
		}

	}
	
	public Executor getExecutorService() {
		return dbWorkers;
	}

	public Executor getExecutorService(int id) {
		return pushWorkers[id & PUSH_THREAD_COUNT_TO_MOD];
	}

	public void executeDBEvent(Runnable event) {
		dbWorkers.execute(event);
	}
	
}
