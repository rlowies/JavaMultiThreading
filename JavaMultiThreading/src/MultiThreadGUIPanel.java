import java.awt.BorderLayout;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

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
	private JButton clearButton, drawButton, stopButton;
	private JLabel timeLabel; // To display time taken to run
	private Thread t;
	private Image image;
	private static final int canvasWidth = 2736;
	private static final int canvasHeight = 1824;
	public Long startTime, stopTime;
	protected boolean drawing;
	

	/**
	 * Constructs the window
	 */
	public MultiThreadGUIPanel() {
		setLayout(new BorderLayout());
		southPanel();
		westPanel();
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
		timeLabel = new JLabel();
		timeLabel.setText("Time elapsed: ");
		southPanel.add(timeLabel);
	}

	/**
	 * General buttons
	 */
	private void westPanel() {
		JPanel westPanel = new JPanel();
		westPanel.setLayout(new BoxLayout(westPanel, BoxLayout.Y_AXIS));
		add(westPanel, BorderLayout.WEST);
		// Create buttons
		clearButton = new JButton();
		drawButton = new JButton();
		stopButton = new JButton();
		// Set text
		clearButton.setText("Clear");
		drawButton.setText("Draw");
		stopButton.setText("Stop");
		// Listeners
		clearButton.addActionListener(new ButtonListener());
		stopButton.addActionListener(new ButtonListener());
		stopButton.setEnabled(false);
		// Threaded
		drawButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent evt) {
				t = new Thread() {
					@Override
					public void run() {
						buttonControl();
						drawing = true;
						startTime = System.currentTimeMillis();
						for (int x = 0; x <= canvasWidth; x++) {
							for (int y = 0; y <= canvasHeight; y++) {
								threadDraw(x, y);
								if (t.isInterrupted()) {
									break;
								}
								if (x == canvasWidth - 1) {
									stopButton.setEnabled(false);
									drawing = false;
									try {
										stopTime = System.currentTimeMillis();
										timeLabel.setText(
												"Time elapsed: " + Long.toString(stopTime - startTime) + " ms");
										t.join();
									} catch (InterruptedException e) {
										e.printStackTrace();
									}
								}
							}
						}
					}

					/**
					 * Controls the visibility of buttons during the drawing process.
					 */
					private synchronized void buttonControl() {
						drawButton.setEnabled(false);
						if (!stopButton.isEnabled()) {
							stopButton.setEnabled(true);
						}
					}
				};
				t.start();
			}
		});
		westPanel.add(clearButton);
		westPanel.add(drawButton);
		westPanel.add(stopButton);
	}

	/**
	 * Clears image
	 */
	private synchronized void clearDraw() {
		repaint();
		if (drawing) {
			drawButton.setEnabled(false);
		} else {
			drawButton.setEnabled(true);
		}
	}

	/**
	 * Interrupts the process
	 */
	private synchronized void stopDraw() {
		if (t != null) {
			t.interrupt();
			drawing = false;
		}
	}

	/**
	 * Calls the paint method to draw a single pixel at the desired location
	 * 
	 * @param x
	 * @param y
	 */
	public void threadDraw(int x, int y) {
		Graphics g = getGraphics();
		image.paint(g, x, y, canvasWidth, canvasHeight);
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
}