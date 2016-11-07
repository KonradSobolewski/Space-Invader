import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;
/**
 * jedna z klas modelu- model pocisku
 */
public class Bullets 
{
	private int X, Y,speedX;
	private BufferedImage[] frames;
	private int currentFrame;
	private long startTime;
	private long delay;
	private Rectangle rect;

	private BufferedImage[] bullets;

	public Rectangle getRect()
	{
		return rect;
	}
	
	public BufferedImage[] getBullets()
	{
		return bullets;
	}
	
	public int getX()
	{
		return X;
	}
	public int getY()
	{
		return Y;
	}

	/**
	 * 
	 * @param speed - ustawiam prêdkoœc lotu
	 */
	public void setSpeed(int speed)
	{
		speedX=speed;
	}
	/**
	 *tworze jego pozycje i animacje
	 */
	public Bullets(int posx,int posy)
	{
		X=posx;
		Y=posy;
		rect=new Rectangle(X, Y, 20, 20);
		
		try
        {
			/**
			 * tworze animacje pocisku 
			 */
        	bullets=new BufferedImage[6];
  
        	BufferedImage image5 = ImageIO.read(new File("C:/Users/KonradSob/Desktop/SpaceInvader/src/bullet.png"));
        
        	bullets[0]=image5.getSubimage(0, 0, 47, 22);
        	bullets[1]=image5.getSubimage(57, 0, 47, 22);
        	bullets[2]=image5.getSubimage(116, 0, 47, 22);
        	bullets[3]=image5.getSubimage(170, 0, 47, 22);
        	bullets[4]=image5.getSubimage(228, 0, 47, 22);
        	bullets[5]=image5.getSubimage(280, 0, 47, 22);
        }
        catch(Exception e)
        {
        	e.printStackTrace();
        }
	}
	/**
	 * leci ca³y czas w prawo
	 */
	public void move ()
	{
		X+=speedX;
		rect.setLocation(X, Y);
	}
	/**
	 * ustawiamy obecn¹ klatke 
	 * @param images
	 */
	public void setFrames(BufferedImage[] images)
	{
		frames=images;
		if(currentFrame>=frames.length) 
			currentFrame=0;
	}
	/**
	 * ustawiam czas po jakim ma byæ nastepna klatka
	 * @param d
	 */
	public void setDelay(long d)
	{
		delay=d;
	}
	/**
	 * odpowiada zmianie klatki w odp czasie
	 */
	public void update()
	{
		if(delay==-1)return;
		long elapsed=(System.nanoTime()-startTime)/1000000;
		if(elapsed>delay)
		{
			currentFrame++;
			startTime=System.nanoTime();
		}
		if(currentFrame==frames.length)
		{	
			currentFrame=0;
		}	
	}
	
	public BufferedImage getImage()
	{
		return frames[currentFrame];
	}
}
	