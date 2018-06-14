import java.util.ArrayList;

public class Threads extends Thread {
	
//	private ArrayList<Thread> threads;
//	private Thread thread;
	
//	public Threads() {
//		setThread(new Thread());
//	}
	
	public void run() {
		try {
			System.out.println("Thread ID: " + Thread.currentThread().getId());
//			if(threads == null) {
//				threads = new ArrayList<Thread>();
//			}
//			threads.add(getThread());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
//	public void stopThreads() {
//		for(int i = 0; i < threads.size(); i++) {
//			threads.set(i, null);
//		}
//	}
//
//	public Thread getThread() {
//		return thread;
//	}
//
//	public void setThread(Thread thread) {
//		this.thread = thread;
//	}

	
	
	

}
