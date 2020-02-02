/*
 * it.unipr.informatica.concurrent.Task
 *
 * (c) 2019 Federico Bergenti. All rights reserved.
 */
package it.unipr.informatica.active;

public class Task implements Runnable {
	private int num;
	  
    public Task(int n) {
        num = n;
    }
 
    public void run() {
        System.out.println("Task " + num + " is running.");
    }

	public void stop() {
		
	}
}
