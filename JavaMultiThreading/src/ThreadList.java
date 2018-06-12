import java.util.ArrayList;

public class ThreadList {
	private ArrayList<String> threadList;
	public ThreadList() {
		if(isEmpty()) {
			this.threadList = new ArrayList<String>();
		}
	}
	private boolean isEmpty() {
		boolean retVal = false;
		if(this.threadList == null) {
			retVal = true;
		}
		return retVal;
	}
	public void addThread(String t) {
		threadList.add(t); 
	}
	public String[] getThreadArray() {
		String[] copy = new String[threadList.size()];
		
		for(int i = 0; i < threadList.size(); i++) {
			copy[i] = threadList.get(i);
		}
		return copy;
	}
	public void clearList() {
		threadList.clear();
	}
}
