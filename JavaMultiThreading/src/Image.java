import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Polygon;
import java.util.Random;

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
	public static int count;
	public Random rand;
	private int randomX,randomY;
	
	public Image() {
		count = 0;
		rand = new Random();
		randomX = 0;
		randomY = 0;
	}

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
		
	
		if(count % 10 == 0) {
			randomX = rand.nextInt(windowWidth);
			randomY = rand.nextInt(windowHeight);
		}
//		g.fillRect(x + (windowWidth - canvas.getWidth()), y, 1, 1);
//		g.setColor(Color.BLUE);
//		g.fillRect(x + (windowWidth - canvas.getWidth()), y + (canvas.getHeight()), 1, 1);
	//	g.setColor(Color.GREEN);
	
		
		if(count % 10 == 1) {
			g.setColor(Color.BLACK);
			g.draw3DRect(x+randomX, Factorial(x+y), randomY, x+y, true);
		} 
		
		
	     if (count % 20 == 1) {
			g.setColor(Color.green);
//			g.fill3DRect(windowWidth - x / 2, y, y-x, y+x, true);
			g.fill3DRect(windowWidth - x*y, y, (y-randomX), x-randomY, true);
		}
		
		
//		if(count % 10 == 1 && count % 5 == 1) 
//		{
//			g.setColor(Color.MAGENTA);
//			
			
//			g.setColor(Color.black);
			
			if(count < 250) {
				
				///Gud
				g.setColor(Color.GREEN);
				g.drawLine(x + randomX, Factorial(y + windowHeight + randomY), (randomX / (randomX+1)), randomY);
				g.setColor(Color.RED);
				g.drawLine(x, Factorial(y+randomY), randomX, randomY);
				///
				
				
				
				
				
//				g.drawOval(x, randomY,randomX, randomY);
//			g.draw3DRect(randomX, randomY, y-x, randomX, true);
//				g.drawOval(randomX + x, randomY, randomY, randomX*randomX);
			}
			else {
				
				
				if(count > 1000) {
					g.setColor(Color.DARK_GRAY);
				} else if (count > 1000 || count < 1500)
				{
					g.setColor(Color.GRAY);
				} 
				g.draw3DRect(-randomX, randomY, (x-randomY)*y, -(y-randomX)*x, true);
//				g.drawOval(x, randomY + y * 2,randomX, randomX);
				
			}
			
			if(count ==3000) {
				
				count = 0;
			}
		
		 count++;
		
		
		
		
		
//		g.setColor(Color.RED);
//		g.fillArc(x, y + x, x-y, 1, x, y);
		
		
		
	}
	
	public int Factorial(int x) {
		int result;
		if(x == 1) {
			return 1;
		}
		result = Factorial(x-1) * x;
		
		return result;
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