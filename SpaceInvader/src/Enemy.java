import java.awt.Rectangle;
/**
 * jedna z klas modelu - model wroga
 *
 */

public class Enemy 
{
	private int x,y,speedX=1,hp=2;
	private Rectangle rect;
	/**
	 * settery i gettery
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
	public void setHp()
	{
		hp--;
	}
	public int getHp()
	{
		return hp;
	}
	
    public int getX()
 	{
 		return x;
 	}
    
 	public int getY()
 	{
 		return y;
 	}
 	/**
 	 * 
 	 * @param posx dostaje pozycje x gdzie ma sie stworzyæ
 	 * @param posy dostaje pozycje y gdzie ma sie stworzyæ
 	 */
    public Enemy(int posx,int posy)
    {
    	 x=posx;
    	 y=posy;
    	 rect=new Rectangle(x, y, 11, 11);
    }
   /**
    * rusza przeciwnika ca³y czas w lewo 
    */
    public void move ()
 	{
 		x-=speedX;
 		rect.setLocation(x, y);

 	}
}
    