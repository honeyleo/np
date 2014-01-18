package com.w.np.push;

import java.util.List;
import java.util.concurrent.AbstractExecutorService;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedTransferQueue;
import java.util.concurrent.TimeUnit;


public class PushWorkerThread extends AbstractExecutorService{

    private final BlockingQueue<Runnable> tasks;

    private volatile boolean shuttingDown;
    private volatile boolean stoped;
    private Worker worker;
    
    public PushWorkerThread() {
        this("GameWorkerThread");
    }

    public PushWorkerThread(String _name) {
        worker = new Worker(_name);
        tasks = new LinkedTransferQueue<Runnable>();
        worker.setPriority(Thread.MAX_PRIORITY);
        worker.start();
    }
    
    @Override
    public String toString(){
        return worker.toString();
    }

    public void execute(Runnable task) {
        tasks.offer(task);
    }
    
    public void awaitTermination(){
        if (shuttingDown){
            while(!stoped){
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        } else{
            throw new IllegalStateException("Worker not shutting down");
        }
    }
    
    private class Worker extends Thread{
        public Worker(String name){
            super(name);
        }
        
        public void run() {
            Runnable task = null;
            for (;;) {
                try {
                    task = tasks.take();
                    task.run();
                } catch (ShutDownException ex){
                    stoped = true;
                    
                    return;
                }
                
                catch (Throwable ex) {
                    try {
                        ex.printStackTrace();
                    } catch (Throwable ex1) {
                    }
                }
            }
        }
    }

    private static class ShutDownTask implements Runnable{
        public void run(){
            throw new ShutDownException();
        }
    }
    
    private static class ShutDownException extends RuntimeException{

	private static final long serialVersionUID = -823943996139206850L;}

    @Override
    public void shutdown() {
        shuttingDown = true;
        tasks.offer(new ShutDownTask());
    }

    @Override
    public List<Runnable> shutdownNow() {
        shutdown();
        return null;
    }

    @Override
    public boolean isShutdown() {
        return shuttingDown;
    }

    @Override
    public boolean isTerminated() {
        return stoped;
    }

    @Override
    public boolean awaitTermination(long timeout, TimeUnit unit)
            throws InterruptedException {
        awaitTermination();
        return true;
    }
}
