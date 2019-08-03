import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
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
	private int collatzX2,collatzY2, randColor, randColor2, collatzX1, collatzY1;
	private Color[] colors, colors2;
	private Color newColor, boxColor;
	private double probability;
	private static int windowWidth = 1280;
	private static int windowHeight = 720;
	boolean wow = true;
	
	public Image() {
		count = 0;
		rand = new Random();
		collatzX2 = 0;
		collatzY2 = 0;
		colors = new Color[10];
		colors2 = new Color[10];
		
		
		
		initColors();
		initColors2();
	}
	
	private void initColors2() {
//		colors2[0] = Color.red;
		colors2[1] = Color.blue;
//		colors2[2] = Color.green;
//		colors2[3] = Color.yellow;
//		colors2[4] = Color.black;
//		colors2[5] = Color.orange;
//		colors2[6] = Color.white;
//		colors2[7] = Color.cyan;
//		colors2[8] = Color.gray;
//		colors2[9] = Color.magenta;
	}

	private void initColors() {
//		colors[0] = Color.red;
//		colors[1] = Color.blue;
//		colors[2] = Color.green;
//		colors[3] = Color.yellow;
		colors[4] = Color.black;
//		colors[5] = Color.orange;
		colors[6] = Color.white;
//		colors[7] = Color.cyan;
		colors[8] = Color.gray;
//		colors[9] = Color.magenta;
	}

	public void paint(Graphics g, int x, int y) {
//		paintRandom((Graphics2D) g, x, y);
		g.fillRect(x, y, 1, 1);
		g.setColor(Color.BLUE);
		g.fillRect(x, y - (windowHeight / 2), 1, 1);
	}
	
	/**
	 * Draws randomness
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
			collatzX2 = rand.nextInt(windowWidth);
			collatzY2 = rand.nextInt(windowHeight);
			randColor = rand.nextInt(colors.length);
			newColor = colors[randColor];
			
		}
		if(count < 50) {
			g.setColor(newColor);
			g.drawLine(collatzX2, collatzY2, 100, 100);	
			}
		if(count > 50 && count < 100) {
			g.setColor(newColor);
			g.drawLine(collatzX2, collatzY2, collatzX2, collatzY2);
		}
//		if(count > 150) {
//			g.setColor(newColor);
//			g.drawRect(randomX, randomY, 10, 100);
//			
//		}
		
		if (count == 200){
			count = 0;
		}
		
		 count++;
	}

	/**
	 * Draws with collatz conjecture and randomness
	 * 
	 * @param g
	 *            Graphics
	 * @param x
	 * @param y
	 * @param windowWidth
	 * @param windowHeight
	 */
	public void paintCollatz(Graphics2D g, int x, int y) {
		
		
		probability = rand.nextDouble();
		int polySize = 30000;
		int[] xPoints= new int[polySize], yPoints = new int[polySize];
		int[] xPoints2= new int[polySize], yPoints2 = new int[polySize];
		if(probability <= 0.80) { //50% chance of randomizing
			collatzX1 = collatzConjecture(rand.nextInt(windowWidth / 2));
			collatzY1 = collatzConjecture(rand.nextInt(windowHeight / 2));
			collatzX2 = collatzConjecture(myst(collatzX1 / 2));
			collatzY2 = collatzConjecture(myst(collatzY1 / 2));
			xPoints[0] = collatzX1;
			yPoints[0] = collatzY2;
			for(int i = 1; i < polySize; i++) {
				xPoints[i] = xPoints[i-1]*yPoints[i-1]+polySize*collatzX1 * (((collatzX2 + i*collatzX1)*xPoints[i-1])*yPoints[i-1] + 1);
				yPoints[i] = yPoints[i-1]*xPoints[i-1]+polySize*collatzY1 * (((collatzY2 + i*collatzY1)*yPoints[i-1])*xPoints[i-1] + 1);
				xPoints2[i] = xPoints[i-1]*yPoints[i-1]+polySize*collatzX1;
				yPoints2[i] = yPoints[i-1]*xPoints[i-1]+polySize*collatzY1;
			}
			
			randColor = rand.nextInt(colors.length);
			randColor2 = rand.nextInt(colors2.length);
			newColor = colors[randColor];
			boxColor = colors2[randColor2];
		}
		if(count < 50) {
			g.setColor(wow ? newColor : boxColor);
			g.drawPolygon(xPoints, yPoints, polySize);
//			g.drawLine(x, y, collatzX1 / (x+1), y);	
			}
		if(count > 50 && count < 100) {
			g.setColor(wow ? boxColor : newColor);
			g.drawPolygon(yPoints, xPoints, polySize);
		}
//		if(count > 150) {
//			g.setColor(newColor);
//			g.drawRect(x, y, collatzX1 / (x+1), collatzY2);
//			
//		}
		
		if (count == 100){
			count = 0;
			wow = !wow;
		}
		
		 count++;
	}
	
	private int myst(int x) {
		return x > windowWidth / 2 ?  windowWidth / 2 : windowWidth;
	}

	public int Factorial(int x) {
		int result;
		if(x == 1) {
			return 1;
		}
		result = Factorial(x-1) * x;
		
		return result;
	}
	
	public int collatzConjecture(int n) {
		return n % 2 == 0 ? n/2 : 3*n + 1;
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

