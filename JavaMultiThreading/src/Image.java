import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Polygon;
import java.util.Random;

import javax.swing.Box;
import javax.swing.BoxLayout;

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
	private int randomX,randomY, randColor;
	private Color[] colors;
	private Color newColor;
	private double probability;
	
	public Image() {
		count = 0;
		rand = new Random();
		randomX = 0;
		randomY = 0;
		colors = new Color[10];
		
		initColors();
		
	}

	private void initColors() {
		colors[0] = Color.red;
		colors[1] = Color.blue;
		colors[2] = Color.green;
		colors[3] = Color.yellow;
		colors[4] = Color.black;
		colors[5] = Color.orange;
		colors[6] = Color.white;
		colors[7] = Color.cyan;
		colors[8] = Color.gray;
		colors[9] = Color.magenta;
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
		
		probability = rand.nextDouble();
		
		if(probability <= 0.06) { //6% chance of randomizing
			randomX = rand.nextInt(windowWidth);
			randomY = rand.nextInt(windowHeight);
			randColor = rand.nextInt(10);
			newColor = colors[randColor];
		}

		
		if(count < 50) {
			g.setColor(newColor);
			g.draw3DRect(randomX, randomY, 100, 100, true);	
			}
		
		if(count > 50 && count < 100) {
			g.setColor(newColor);
//			g.drawOval(randomX, randomY, randomX, randomY);
			g.drawLine(randomX, randomY, randomX, randomY);
		}
		
		
		if(count > 150) {
			g.setColor(newColor);
			g.drawRect(randomX, randomY, 10, 100);
			
		}
		
		
		if (count == 200){
			count = 0;
		}
		
		
		 count++;
		
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