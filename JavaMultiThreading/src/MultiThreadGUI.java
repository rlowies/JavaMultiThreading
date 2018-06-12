import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.UIManager;

/**
 * @author ronlo
 * 
 *
 */
public class MultiThreadGUI {

	
	public static void main(String[] args) {
		// So it looks consistent on Mac/Windows/Linux
				try {
					UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
				} catch (Exception e) {
					e.printStackTrace();
				}
				
				JFrame frame = new JFrame("GUI");
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				frame.getContentPane().add(new MultiThreadGUIPanel());
				frame.setPreferredSize(new Dimension(1920, 1080));
				frame.pack();
				frame.setVisible(true);
				
				
	}
}
