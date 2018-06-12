import java.util.ArrayList;

/**
 * @author ronlo
 * 
 * Maintains the information identifying each thread.
 *
 */
public class ThreadList {

	private ArrayList<String> threadList; // Stores the JList text

	/**
	 * Instantiate list
	 */
	public ThreadList() {
		if (isEmpty()) {
			this.threadList = new ArrayList<String>();
		}
	}

	/**
	 * @return boolean
	 */
	private boolean isEmpty() {
		boolean retVal = false;
		if (this.threadList == null) {
			retVal = true;
		}
		return retVal;
	}

	/**
	 * Adds text to array that will be added to JList
	 * 
	 * @param t (thread information)
	 */
	public void addThread(String t) {
		threadList.add(t);
	}

	/**
	 * @return copy of list
	 */
	public String[] getThreadArray() {
		String[] copy = new String[threadList.size()];

		for (int i = 0; i < threadList.size(); i++) {
			copy[i] = threadList.get(i);
		}
		return copy;
	}

	/**
	 * Clears the list
	 */
	public void clearList() {
		threadList.clear();
	}
}
