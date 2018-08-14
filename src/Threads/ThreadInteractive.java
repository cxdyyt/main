package Threads;

import java.util.LinkedList;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ThreadInteractive{

	FoodConsume fc = new FoodConsume();

	public static void main(String args[]) {
		ThreadInteractive thr = new ThreadInteractive();
		ExecutorService es = Executors.newCachedThreadPool();
		Runnable prd = thr.new Provider();
		Runnable con = thr.new Consume();
		es.execute(con);
		es.execute(con);
		es.execute(prd);
//		es.execute(prd);

		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		es.shutdownNow();
	}
	

	class Provider implements Runnable{
	
		@Override
		public void run() {
			try {
				while(!Thread.interrupted()) {
					fc.provideFood();
				} 
			}catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}
	class Consume implements Runnable{
	
		@Override
		public void run() {
			try {
				while(!Thread.interrupted()) {
					fc.consumeFood();
				} 
			}catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}
	class FoodConsume {
		LinkedList<Integer> foodStack = new LinkedList<Integer>();
		Random ran = new Random(37);
	
		synchronized void provideFood() throws InterruptedException {
			while (foodStack.size() > 10) {
				wait();
			}
			int food = ran.nextInt(100);
			foodStack.add(food);
			System.out.println("provide food:" + Thread.currentThread().getName() + "|" + food);
			notifyAll();
		}
	
		synchronized void consumeFood() throws InterruptedException {
			while (foodStack.size() == 0) {
				wait();
			}
			System.out.println("consume food:" + Thread.currentThread().getName() + "|" + foodStack.poll());
			notifyAll();
		}
	}
	
}

