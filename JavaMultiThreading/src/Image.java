import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

public class Image extends Canvas {
	/**
	 * Default Serial
	 */
	private static final long serialVersionUID = 1L;
	private Canvas canvas;
	
	public Image() {
		
	}

	public void paint(Graphics g, int x, int y, int windowWidth, int windowHeight) {
	paintMore((Graphics2D)g,x,y,windowWidth, windowHeight);
	}
	


	private void paintMore(Graphics2D g, int x, int y, int windowWidth, int windowHeight) {
		g.fillRect(x + (windowWidth - canvas.getWidth()), y, 1, 1);
		g.setColor(Color.BLUE);
		g.fillRect(x + (windowWidth - canvas.getWidth()), y + (canvas.getHeight()), 1, 1);
	}

	public Canvas getCanvas() {
		return canvas;
	}

	public void setCanvas(Canvas canvas) {
		this.canvas = canvas;
	}
}
