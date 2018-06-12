import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Scanner;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

/**
 * @author ronlo
 * 
 *         User interface and main functionality.
 *
 */
public class MultiThreadGUIPanel extends JPanel {

	/**
	 * Automated
	 */
	private static final long serialVersionUID = 1L;

	public static final int HIRES_FONTSIZE = 26, LOWRES_FONTSIZE = 12;

	private JButton showThreadsButton, startButton, stopButton;

	private JLabel threadLabel;

	private JList<String> threadListJList; // Added to JScrollPane

	private int numThreads; // Current number of threads

	private Threads thread; // Thread variable.

	private ThreadList threadList;

	private boolean isStarted = false;

	/**
	 * Constructs the window
	 */
	public MultiThreadGUIPanel() {
		setLayout(new BorderLayout());
		threadList = new ThreadList();

		westPanel();
		centerPanel();
		southPanel();
		eastPanel();
	}

	/**
	 * To contain a graph or other information
	 */
	private void eastPanel() {
		JPanel eastPanel = new JPanel();
		eastPanel.setLayout(new BoxLayout(eastPanel, BoxLayout.X_AXIS));
		add(eastPanel, BorderLayout.EAST);

		/*
		 * TODO: Remove later
		 */
		JLabel testLabel = new JLabel();
		testLabel.setText(" - - - - Testing some stuff - - - - ");
		eastPanel.add(testLabel);
	}

	/**
	 * Contains the text label for number of threads.
	 */
	private void southPanel() {
		JPanel southPanel = new JPanel();
		southPanel.setLayout(new BoxLayout(southPanel, BoxLayout.X_AXIS));
		add(southPanel, BorderLayout.SOUTH);

		threadLabel = new JLabel();
		threadLabel.setText("Number of Threads: 0");

		southPanel.add(threadLabel);
	}

	/**
	 * General buttons
	 */
	private void westPanel() {
		JPanel westPanel = new JPanel();
		westPanel.setLayout(new BoxLayout(westPanel, BoxLayout.Y_AXIS));
		add(westPanel, BorderLayout.WEST);

		/*
		 * Create buttons
		 */
		startButton = new JButton();
		showThreadsButton = new JButton();
		stopButton = new JButton();

		// Set text
		startButton.setText("Start");
		showThreadsButton.setText("Show Threads");
		stopButton.setText("Stop");

		// Listeners
		startButton.addActionListener(new ButtonListener());
		showThreadsButton.addActionListener(new ButtonListener());
		stopButton.addActionListener(new ButtonListener());

		// Add to panel
		westPanel.add(startButton);
		westPanel.add(showThreadsButton);
		westPanel.add(stopButton);

	}

	/**
	 * Text for thread information to go here
	 */
	private void centerPanel() {
		JPanel centerPanel = new JPanel();
		centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.X_AXIS));
		add(centerPanel, BorderLayout.CENTER);

		threadListJList = new JList<String>();
		threadListJList.setSelectionMode(0);

		// Lines up the text in the scroll pane. //TODO: Add resolution changer
		threadListJList.setFont(new Font(Font.MONOSPACED, Font.BOLD, HIRES_FONTSIZE));

		// Add a ListSelectionListener
		threadListJList.addListSelectionListener(new ListListener());
		// Create a JScrollPane
		JScrollPane scrollPane = new JScrollPane(threadListJList, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
				JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scrollPane.setPreferredSize(new Dimension(600, 400));
		// add the scroll pane to the left panel
		scrollPane.setBorder(BorderFactory.createEtchedBorder());
		centerPanel.add(scrollPane, BorderLayout.WEST);
	}

	/**
	 * Creates threads and stores corresponding information.
	 */
	private void startProcess() {

		if (isStarted) {
			stopProcess();
		}

		for (int i = 0; i < numThreads; i++) {
			thread = new Threads();
			thread.start();
			threadList.addThread((i + 1) + " - Thread ID: " + thread.getId());
		}

		isStarted = true;
	}

	/**
	 * Clears threads and thread information
	 */
	private void stopProcess() {

		if (isStarted) {
			thread.stopThreads();
			threadList.clearList();
			threadLabel.setText("Number of Threads: 0");
			refreshList();
			isStarted = false;
		}

	}

	/**
	 * Maintaining readability
	 */
	private void refreshList() {
		threadListJList.setListData(threadList.getThreadArray());
	}

	/**
	 * @author ronlo
	 * 
	 *         Listens for button clicks
	 *
	 */
	private class ButtonListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent arg0) {
			if (arg0.getSource() == showThreadsButton) {
				refreshList();
			} else if (arg0.getSource() == startButton) {

				String result = JOptionPane.showInputDialog("Enter number of threads (Max 32)");
				if (result == null || result.length() == 0) {
					// Do nothing
				} else {
					Scanner in = new Scanner(result);
					try {
						numThreads = Integer.parseInt(in.next());
					} catch (NumberFormatException e) {
						// TODO: Add popup info
						System.err.println("Must be an integer in the range (0 - 32)");
					}
					if (numThreads < 1 || numThreads > 32) {
						startButton.doClick();
					} else {
						/*
						 * Creates threads
						 */
						startProcess();
						threadLabel.setText("Number of Threads: " + numThreads);
					}
					in.close();
				}

			} else if (arg0.getSource() == stopButton) {
				stopProcess();
			}
		}

	}

	/**
	 * @author ronlo
	 * 
	 *         Listens for List clicks
	 *
	 */
	private class ListListener implements ListSelectionListener {

		@Override
		public void valueChanged(ListSelectionEvent e) {

		}

	}
}
