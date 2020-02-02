/*
 * it.unipr.informatica.example3.Main
 * 
 */
package it.unipr.informatica.esame;

import it.unipr.informatica.active.Task;
import it.unipr.informatica.active.ThreadPool;

public class Main {

	public static void main(String[] args) {
		
		ThreadPool pool = new ThreadPool(10);
 
    	for (int i = 0; i < 10; i++) {
            Task task = new Task(i);
            pool.add(task);
            pool.stop();
    	}
	}
}





