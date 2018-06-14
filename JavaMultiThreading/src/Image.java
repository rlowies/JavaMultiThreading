import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

/**
 * This class controls drawing
 * 
 * @author ronlo
 *
 */
public class Image extends Canvas {
	/**
	 * Default Serial
	 */
	private static final long serialVersionUID = 1L;
	private Canvas canvas;

	public void paint(Graphics g, int x, int y, int windowWidth, int windowHeight) {
		paintMore((Graphics2D) g, x, y, windowWidth, windowHeight);
	}

	/**
	 * Draws a single pixel at the desired location
	 * 
	 * @param g
	 *            Graphics
	 * @param x
	 * @param y
	 * @param windowWidth
	 * @param windowHeight
	 */
	private void paintMore(Graphics2D g, int x, int y, int windowWidth, int windowHeight) {
		g.fillRect(x + (windowWidth - canvas.getWidth()), y, 1, 1);
		g.setColor(Color.BLUE);
		g.fillRect(x + (windowWidth - canvas.getWidth()), y + (canvas.getHeight()), 1, 1);
	}

	/**
	 * @return canvas
	 */
	public Canvas getCanvas() {
		return canvas;
	}

	/**
	 * @param canvas
	 */
	public void setCanvas(Canvas canvas) {
		this.canvas = canvas;
	}
}
