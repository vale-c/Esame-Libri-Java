/*
 * it.unipr.informatica.concurrent.ThreadPool
 *
 * (c) 2019 Federico Bergenti. All rights reserved.
 */
package it.unipr.informatica.active;


import java.util.concurrent.LinkedBlockingQueue;

public class ThreadPool {
    @SuppressWarnings("unused")
	private final int nThreads;
    private final PoolWorker[] threads;
    private final LinkedBlockingQueue<Runnable> queue;
 
    public ThreadPool(int nThreads) {
        this.nThreads = nThreads;
        queue = new LinkedBlockingQueue<Runnable>();
        threads = new PoolWorker[nThreads];
 
        for (int i = 0; i < nThreads; i++) {
            threads[i] = new PoolWorker();
            threads[i].start();
        }
    }
 
    public void add(Runnable task) {
        synchronized (queue) {
            queue.add(task);
            queue.notify();
        }
    }
    
    public void stop() {
		System.out.println("Task stopped");
	}
 
    private class PoolWorker extends Thread {
        public void run() {
            Runnable task;
 
            while (true) {
                synchronized (queue) {
                    while (queue.isEmpty()) {
                        try {
                            queue.wait();
                        } catch (InterruptedException e) {
                            System.out.println("An error occurred while queue is waiting: " + e.getMessage());
                        }
                    }
                    task = (Runnable) queue.poll();
                }
 
                // If we don't catch RuntimeException,
                // the pool could leak threads
                try {
                    task.run();
                } catch (RuntimeException e) {
                    System.out.println("Thread pool is interrupted due to an issue: " + e.getMessage());
                }
            }
        }
    }	
}