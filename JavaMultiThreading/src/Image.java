import java.awt.Canvas;
import java.awt.Graphics;

public class Image extends Canvas {
	/**
	 * Default Serial
	 */
	private static final long serialVersionUID = 1L;
	
	private Canvas canvas;
	
	public void paint(Graphics g) {
		g.fillOval(100, 100, 200, 200);
	}
	
	

	public Canvas getCanvas() {
		return canvas;
	}

	public void setCanvas(Canvas canvas) {
		this.canvas = canvas;
	}

}
