import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
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
	private static Canvas canvas;
	public static int count;
	public Random rand;
	private int randomX,randomY, randColor;
	private Color[] colors;
	private Color newColor;
	private double probability;
	private static int windowWidth = 1280;
	private static int windowHeight = 720;
	
	public Image() {
		count = 0;
		rand = new Random();
		randomX = 0;
		randomY = 0;
		colors = new Color[10];
		
		
		
		initColors();
	}

	private void initColors() {
//		colors[0] = Color.red;
//		colors[1] = Color.blue;
		colors[2] = Color.green;
//		colors[3] = Color.yellow;
		colors[4] = Color.black;
//		colors[5] = Color.orange;
//		colors[6] = Color.white;
//		colors[7] = Color.cyan;
//		colors[8] = Color.gray;
//		colors[9] = Color.magenta;
	}

	public void paint(Graphics g, int x, int y) {
//		paintRandom((Graphics2D) g, x, y);
		g.fillRect(x, y, 1, 1);
		g.setColor(Color.BLUE);
		g.fillRect(x, y - (windowHeight / 2), 1, 1);
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
	public void paintRandom(Graphics2D g, int x, int y) {
		
		probability = rand.nextDouble();
		
		if(probability <= 0.5) { //50% chance of randomizing
			randomX = rand.nextInt(windowWidth);
			randomY = rand.nextInt(windowHeight);
			randColor = rand.nextInt(colors.length);
			newColor = colors[randColor];
		}
		if(count < 50) {
			g.setColor(newColor);
			g.draw3DRect(randomX, randomY, 100, 100, true);	
			}
		if(count > 50 && count < 100) {
			g.setColor(newColor);
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
		Image.canvas = canvas;
	}
	@Override
	public int getWidth() {
		return windowWidth;
	}
	@Override
	public int getHeight() {
		return windowHeight;
	}

	public void setDimension(int width, int height) {
		windowWidth = width;
		windowHeight = height;
		
	}
	
	

}