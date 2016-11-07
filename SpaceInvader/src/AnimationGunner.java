import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;

/**
 * 
 * klasa odp za animacje i ruch bohatera
 */
public class AnimationGunner
{
	private int x;
	private int y;
	private int xDirection,yDirection,WIDTH,HEIGHT;
	private int HP;
/**
 * sta³e odp za zmiane klatek postaci
 */
	private int currentFrame;
	private long startTime;
	private long delay;
	private Rectangle rect;
	/** 
	 * zmienne przetrzymuj¹ klatki postaci
	 */
	private BufferedImage[] frames;
	private BufferedImage[] idleSprites;
	private BufferedImage[] walkingSprites;
    private BufferedImage[] attackOne;
    private BufferedImage[] attackTwo;
    /**
     * gettery i settery
     *
     */
	public Rectangle getRect()
	{
		return rect;
	}
	public void setRect(Rectangle rect)
	{
		this.rect = rect;
	}
	public BufferedImage[] getIdleSprites()
	{
		return idleSprites;
	}
	public void setIdleSprites(BufferedImage[] idleSprites)
	{
		this.idleSprites = idleSprites;
	}
	public BufferedImage[] getWalkingSprites()
	{
		return walkingSprites;
	}
	public void setWalkingSprites(BufferedImage[] walkingSprites) 
	{
		this.walkingSprites = walkingSprites;
	}
	public BufferedImage getAttackOne(int i)
	{
		return attackOne[i];
	}
	public BufferedImage[] getAttackOne()
	{
		return attackOne;
	}
	public void setAttackOne(BufferedImage[] attackOne)
    {
		this.attackOne = attackOne;
	}
	public BufferedImage[] getAttackTwo() 
	{
		return attackTwo;
	}
	public BufferedImage getAttackTwo(int i) 
	{
		return attackTwo[i];
	}
	public void setAttackTwo(BufferedImage[] attackTwo) 
	{
		this.attackTwo = attackTwo;
	}
	
    public void setHP()
	{
		HP--;
	}
	public void setHP(int point)
	{
		HP=point;
	}
	public int getHP()
	{
		return HP;
	}
	public int getX()
	{
		return x;
	}
	public int getY()
	{
		return y;
	}
	public void setX(int x)
	{
		this.x=x;
	}
	public void setY(int y)
	{
		this.y=y;
	}
	public void setXDi(int xdir)
	{
		xDirection=xdir;
	}
	public void setYDi(int ydir)
	{
		yDirection=ydir;
	}
	
	/**
	 * metoda odp za ruch postaci
	 */
	public void move ()
	{
		x+=xDirection;
		y+=yDirection;
		if(x<=0)
			x=0;
		if(x>=WIDTH-30)
			x=WIDTH-30;
		if(y<=0)
			y=0;
		if(y>=HEIGHT-80)
			y=HEIGHT-80;
		rect.setLocation(x, y);
	}
	/**
	 * konstrukor
	 * @param width - okresla wstepne polozenie postaci i granice jej ruchu
	 * @param height - okresla wstepne polozenie postaci i granice jej ruchu
	 */
	public AnimationGunner(int width,int height)
	{
		x=width/2;
		y=height/2;
		WIDTH=width;
		HEIGHT=height;
		rect=new Rectangle(x, y, 22, 22);
		try
		{
			/**
			 * tworze animacje postaci w zale¿nosci od czynnosci 
			 * wczytuje palete klatek i zapisuje ka¿d¹ klatke do tablicy klatek
			 */
			idleSprites = new BufferedImage[2];
			walkingSprites = new BufferedImage[6];
			attackOne=new BufferedImage[8];
			attackTwo=new BufferedImage[17];
    	
			BufferedImage image = ImageIO.read(new File("C:/Users/KonradSob/Desktop/SpaceInvader/src/kirbywalk.png"));
			BufferedImage image2 = ImageIO.read(new File("C:/Users/KonradSob/Desktop/SpaceInvader/src/standing.png"));
			BufferedImage image3 = ImageIO.read(new File("C:/Users/KonradSob/Desktop/SpaceInvader/src/attack1.png"));
			BufferedImage image4 = ImageIO.read(new File("C:/Users/KonradSob/Desktop/SpaceInvader/src/attack2.png"));
    	
			for(int a =0 ;a<walkingSprites.length;a++)
			{
				walkingSprites[a]=image.getSubimage(a*26+a, 0, 22, 22);
			}
    	
			for(int a =0 ;a<idleSprites.length;a++)
			{
				idleSprites[a]=image2.getSubimage(a*26+a+1, 0, 22, 22);
			}
    	
			attackOne[0]=image3.getSubimage(0, 0, 27, 22);
			attackOne[1]=image3.getSubimage(0, 0, 27, 22);
			attackOne[2]=image3.getSubimage(43, 0, 27, 22);
			attackOne[3]=image3.getSubimage(53, 0, 25, 22);
			attackOne[4]=image3.getSubimage(82, 0, 25, 22);
			attackOne[5]=image3.getSubimage(144, 0, 25, 22);
			attackOne[6]=image3.getSubimage(175, 0, 25, 22);
			attackOne[7]=image3.getSubimage(204, 0, 25, 22);
    	
			attackTwo[0]=image4.getSubimage(10, 0, 27, 22);
			attackTwo[1]=image4.getSubimage(10, 0, 27, 22);
			attackTwo[2]=image4.getSubimage(40, 0, 27, 22);
    		attackTwo[3]=image4.getSubimage(71, 0, 25, 22);
    		attackTwo[4]=image4.getSubimage(100, 0, 25, 22);
    		attackTwo[5]=image4.getSubimage(128, 0, 25, 22);
    		attackTwo[6]=image4.getSubimage(157, 0, 25, 22);
    		attackTwo[7]=image4.getSubimage(185, 0, 25, 22);
    		attackTwo[8]=image4.getSubimage(211, 0, 25, 22);
    		attackTwo[9]=image4.getSubimage(10, 0, 27, 22);
    		attackTwo[10]=image4.getSubimage(40, 0, 27, 22);
    		attackTwo[11]=image4.getSubimage(71, 0, 25, 22);
    		attackTwo[12]=image4.getSubimage(100, 0, 25, 22);
    		attackTwo[13]=image4.getSubimage(128, 0, 25, 22);
    		attackTwo[14]=image4.getSubimage(157, 0, 25, 22);
    		attackTwo[15]=image4.getSubimage(185, 0, 25, 22);
    		attackTwo[16]=image4.getSubimage(211, 0, 25, 22);
    	
    		/*
    		 * odwracam klatki gdy¿ klatki na zdj s¹ odwrotnie
    		 */
    		reverseArray(idleSprites);
    		reverseArray(walkingSprites);
    		reverseArray(attackOne);
    		reverseArray(attackTwo);
		}
        catch(Exception e)
        {
        	e.printStackTrace();
        }
	}
	/**
	 * funkcja odwracaj¹ca klatki
	 * @param image - wybrana tablica klatek
	 */
	public void reverseArray(BufferedImage[] image){
		BufferedImage temp;
		for(int i = 0; i < image.length / 2; i++)
		{
		    temp = image[i];
		    image[i] = image[image.length - i - 1];
		    image[image.length - i - 1] = temp;
		}
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
