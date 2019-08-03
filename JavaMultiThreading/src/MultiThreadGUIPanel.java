import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.plaf.FontUIResource;

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
	private JPanel centerPanel, settings;
	JCheckBox randomized, fourEightyP, sevenTwentyP, tenEightyP, collatz;
//	private static final int canvasWidth = 1280;
//	private static final int canvasHeight = 1;
	public Long startTime, stopTime;
	protected boolean drawing;
	

	/**
	 * Constructs the window
	 */
	public MultiThreadGUIPanel() {
		setLayout(new BorderLayout());
		southPanel();
		westPanel();
		centerPanel();
		
		settingsPanel();
		
		
		tabPanel();
	}

	private void settingsPanel() {
		settings = new JPanel();
		settings.setLayout(new BoxLayout(settings, BoxLayout.X_AXIS));
		add(settings);
		
		randomized = new JCheckBox("Randomized");
		collatz = new JCheckBox("Collatz Conjecture");
		collatz.setSelected(true);
		
		settings.add(randomized);
		settings.add(collatz);
		
		JPanel res = new JPanel();
		res.setLayout(new BoxLayout(res, BoxLayout.X_AXIS));
		res.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "Resolution"));
		
		fourEightyP = new JCheckBox("480p");
		sevenTwentyP = new JCheckBox("720p");
		tenEightyP = new JCheckBox("1080p");
		tenEightyP.setSelected(true); // default
		
		fourEightyP.addActionListener(new CheckBoxListener());
		sevenTwentyP.addActionListener(new CheckBoxListener());
		tenEightyP.addActionListener(new CheckBoxListener());
		
		res.add(fourEightyP);
		res.add(sevenTwentyP);
		res.add(tenEightyP);
		
		settings.add(res);
		
		
	}

	private void tabPanel() {
		JTabbedPane tabbedPane = new JTabbedPane();
		JPanel tabs = new JPanel();
		tabs.setLayout(new FlowLayout());
	
		add(tabbedPane);
		
		tabbedPane.add("Canvas", centerPanel);
		tabbedPane.add("Settings", settings);
		
	}

	private void centerPanel() {
		centerPanel = new JPanel();
		centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.X_AXIS));
		add(centerPanel, BorderLayout.CENTER);
		
		createCanvas();
		
		centerPanel.add(image);
	}

	/**
	 * Creates the canvas
	 */
	private void createCanvas() {
		image = new Image();
		image.setCanvas(new Image());
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
		westPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "Control Panel"));
		westPanel.setPreferredSize(new Dimension(400,400));
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
						for (int x = 0; x <= image.getWidth(); x++) {
							for (int y = 0; y <= image.getHeight(); y++) {
								threadDraw(x = x == image.getWidth() - 1 ? 0 : x, y = y == image.getHeight() - 1 ? 0 : y);
								if (t.isInterrupted()) {
									break;
								}
								if (x == image.getWidth() - 1) {
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
		image.repaint();
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
	 * Calls the paint method to draw
	 * 
	 * @param x
	 * @param y
	 */
	public void threadDraw(int x, int y) {
		
		if(randomized.isSelected()) 
		{
			image.paintRandom((Graphics2D) image.getGraphics(), x, y);
		} 
		else if (collatz.isSelected()) {
			image.paintCollatz((Graphics2D) image.getGraphics(), x, y);
		}
		else
		{
			image.paint(image.getGraphics(), x, y);
		}
		
		
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
	 * Listens for checkbox clicks
	 *
	 */
	public class CheckBoxListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent arg0) {
			if(arg0.getSource() == fourEightyP) {
					sevenTwentyP.setSelected(false);
					tenEightyP.setSelected(false);
					image.setDimension(640,480);
			} else if(arg0.getSource() == sevenTwentyP) {
					fourEightyP.setSelected(false);
					tenEightyP.setSelected(false);
					image.setDimension(1280,720);
			} else if(arg0.getSource() == tenEightyP) {
					fourEightyP.setSelected(false);
					sevenTwentyP.setSelected(false);
					image.setDimension(1920,1080);
			}
		}

	}
}