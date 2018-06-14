import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
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
	 * Automated Serial
	 */
	private static final long serialVersionUID = 1L;

	private static final int HIRES_FONTSIZE = 26;// , LOWRES_FONTSIZE = 12;

	private JButton clearButton, drawButton, stopButton;


	private JLabel threadLabel, timeLabel; // To display number of threads.

	private JList<String> threadListJList; // List of the threads.

	private Thread t;

	private Image image;

	private static final int canvasWidth = 600;

	private static final int canvasHeight = 400;

	public Long startTime, stopTime;

	protected boolean drawing = false;

	/**
	 * Constructs the window
	 */
	public MultiThreadGUIPanel() {

		setLayout(new BorderLayout());

		southPanel();
		westPanel();
//		centerPanel();

		createCanvas();
	}

	/**
	 * Creates the canvas
	 */
	private void createCanvas() {
		image = new Image();
		image.setCanvas(new Image());
		image.getCanvas().setSize(canvasWidth, canvasHeight);
		image.getCanvas().setVisible(true);
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

		timeLabel = new JLabel();
		timeLabel.setText(" - Time elapsed: ");

		southPanel.add(threadLabel);
		southPanel.add(timeLabel);
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
	//	showThreadsButton = new JButton();
		clearButton = new JButton();
		drawButton = new JButton();
		stopButton = new JButton();

		// Set text
		//showThreadsButton.setText("Show Threads");
		clearButton.setText("Clear");
		drawButton.setText("Draw");
		stopButton.setText("Stop");

		// Listeners
		//showThreadsButton.addActionListener(new ButtonListener());
		clearButton.addActionListener(new ButtonListener());
		stopButton.addActionListener(new ButtonListener());

		// Threaded
		drawButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent evt) {

				t = new Thread() {
					@Override
					public void run() {
						buttonControl();
						
						startTime = System.currentTimeMillis(); // 1000L;//(System.currentTimeMillis() * (long) 0.001);
						for (int x = 1; x < canvasWidth; x++) {
							for (int y = 1; y < canvasHeight; y++) {
								threadDraw(x, y);
								
								if(t.isInterrupted())
								{
									break;
								}
								// TODO: Add interrupt to allow button clicks for stopping
								if (x == canvasWidth - 2) {
									try {
										stopTime = System.currentTimeMillis(); // 1000L;
										timeLabel.setText(
												" - Time elapsed: " + Long.toString(stopTime - startTime) + " ms");
										t.join();
									} catch (InterruptedException e) {
										// TODO Auto-generated catch block
										e.printStackTrace();
									}
								}
							}
						}
					}

					private synchronized void buttonControl() {
							drawButton.setEnabled(false);
					}
				};
				t.start();
			}

		});

		// Add to panel
		//westPanel.add(showThreadsButton);
		westPanel.add(clearButton);
		westPanel.add(drawButton);
		westPanel.add(stopButton);

	}

	/**
	 * Text for thread information to go here
	 */
	
	@SuppressWarnings("unused") //for now
	private void centerPanel() {

		JPanel centerPanel = new JPanel();
		centerPanel.setLayout(null);
		centerPanel.setBorder(BorderFactory.createEtchedBorder());
		centerPanel.setSize(100, 100);
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
		scrollPane.setSize(new Dimension(600, 400));

		// add the scroll pane to the left panel
		scrollPane.setBorder(BorderFactory.createEtchedBorder());

		centerPanel.add(scrollPane, BorderLayout.WEST);
	}

	/**
	 * Clears image
	 */
	private synchronized void clearDraw() {
		repaint();
		drawButton.setEnabled(true);
	}
	
	private synchronized void stopDraw() {
		if(t != null) {
		t.interrupt();
		}
	}
	
	/**
	 * @param x
	 * @param y
	 */
	public void threadDraw(int x, int y) {
		Graphics g = getGraphics();
		image.paint(g, x, y, getWidth(), canvasHeight);
		revalidate(); // TODO: remove this line and draw twice to see bug
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
			 if (arg0.getSource() == clearButton) {
				clearDraw();
			} else if (arg0.getSource() == stopButton) {
				stopDraw();
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