package Threads;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class InterruptTest {

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		// ExecutorService es = Executors.newCachedThreadPool();
		// es.submit(task1);
		// task1.notifyAll();
		 Task1 task1 = new Task1();
		 task1.start();
		 TimeUnit.MILLISECONDS.sleep(1000);
		 synchronized (task1) {
			task1.notifyAll();
			// synchronized (task1) {
			// task1.notifyAll();
			// }
			//		TaskMain tsm = new TaskMain();
			//		tsm.start();
			//		tsm.join();
			//		tsm.interrupt();
			//
			//		System.out.println("join");
		}
	}

}

class TaskMain extends Thread {

	public synchronized void testSleepSyn() throws InterruptedException {
		Thread.sleep(500);
	}

	public synchronized void testinreadSyn() throws IOException {
		System.in.read();
	}

	@Override
	public void run() {
		try {
			testSleepSyn();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// try {
		// testinreadSyn();
		// } catch (IOException e) {
		// // TODO Auto-generated catch block
		// e.printStackTrace();
		// }
	}

}

class Task1 extends Thread {

	public synchronized void testWait() {
		System.out.println("before wait");
		try {
			this.wait();
			System.out.println("after wait");
				
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public synchronized void testInterupt() {
		try {
			TimeUnit.MILLISECONDS.sleep(200);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		while(!this.interrupted()) {
//			System.out.println("in is intertupt:" + this.isInterrupted());
			try {
				TimeUnit.MILLISECONDS.sleep(500);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				System.out.println("exception is intertupt:" + this.isInterrupted());
			}
			System.out.println("wating interupt");
		}

		System.out.println("interupted");
	}

	@Override
	public void run() {
		testWait();
//		testInterupt();
	}
}
