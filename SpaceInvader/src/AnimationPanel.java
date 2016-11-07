import javax.swing.*;
import java.awt.*;

/**
 * Klasa s³u¿y do odœwierzanie animacji gry w panelu
 * klasa ta jest extendowana poniwa¿ wymaga tego prawid³owe dzia³anie programu
 *
 */
public class AnimationPanel extends JPanel
{
	private final int WIDTH,HEIGHT; // dlugosc ,wysokoœæ gry
    private Controller controller;
	private int sizeFrame=29;// narzucam rozmiar postaci bohatera ( sheets )
	private Model model;
    
	/**
	 * gettery
	 */
	public Model getModel()
	{
		return model;
	}
	public Controller getController()
	{
		return controller;
	}

	public int getEnemyKilled(int enemyKilled)
	{
		return enemyKilled;
	}
	/**
	 * 
	 * @param width - szerokoœæ okienka
	 * @param height - wysokoœæ okienka
	 */
	public AnimationPanel(int width, int height)
	{
		WIDTH=width;
		HEIGHT=height;
		setFocusable(true);
		setSize(width, height);
	}
	
	/**
	 * dodaje s³uchacza dla panelu
	 * @param model - przekazuje klasie model
	 * @param control - przekazuje klasie controller
	 */
	public void setModel(Model model,Controller control)
	{
		this.model=model;
		controller=control;
		addKeyListener(controller); //dodaje s³uchacza dla panelu
		Thread t1 = new Thread(controller); //tworze w¹tek dla controllera
		t1.start();
	}
    /**
     * obie poni¿sze funkcje odpowiadaj¹ za wyœwietlanie obiektów na panelu
     */
	public void paint (Graphics g)
	{
		paintComponent(g);
		repaint();
	}
    public void paintComponent(Graphics g)
    {
       g.setColor(Color.WHITE);
       g.fillRect(0, 0, WIDTH, HEIGHT);
       /**
        * rysuje pociski
        */
       for (int i =0; i<model.getGunList().size();i++)
       {  
    	   g.drawImage(model.getGunList().get(i).getImage(), model.getGunList().get(i).getX()+5, model.getGunList().get(i).getY(),30,30,null);
       }
      /**
       * rysuje wrogów
       */
       for (int i =0; i<model.getEnemyList().size();i++)
       {
    	   g.setColor(Color.BLACK);
    	   g.fillOval(model.getEnemyList().get(i).getX(),model.getEnemyList().get(i).getY()+2,16,16);
       }
       /**
        * postaæ mo¿e staæ w lewo lub prawo w zale¿noœci od akcji
        */
       if(model.getFacingLeft())
       {
    	   g.drawImage(model.getGunner().getImage(), model.getGunner().getX(), model.getGunner().getY(),sizeFrame,sizeFrame,null);
       }
       else
       {
    	   g.drawImage(model.getGunner().getImage(), model.getGunner().getX()+sizeFrame, model.getGunner().getY(),-(sizeFrame),sizeFrame,null);
       }
       /**
        * dzia³anie podjête gdy koñczy sie gra 
        */
       if(model.getGameOver()==true)
       {
    	   
    	   g.setColor(Color.BLACK);
    	   g.drawString("GAME OVER", WIDTH/2-28, 200);
    	   g.drawString("Czas Gry: "+ model.getSecondPassed(), WIDTH/2-30, 230);
    	   g.drawString("Zabitych wrogów: "+model.getKilled(), WIDTH/2-40, 250);
    	   g.drawString("Twój WYNIK: "+model.getFinalScore(), WIDTH/2-30, 270);
    	   g.drawString("PRESS 1 TO RESTART GAME", WIDTH/2-60, 290);
    	 
       }
       /**
        * dzia³ania podjête dla zastopowanej gry
        */
	   if(model.getGameStarted()==false && model.getGameOver()==false)
	   {
		   g.drawString("GAME PAUSED", WIDTH/2-20, 200);
		   g.drawString("Czas Gry: "+model.getSecondPassed(), WIDTH/2-10, 230);
	   }
	   /**
	    * zawsze wyœwietlam statystyki gry 
	    */
	   g.setFont(new Font("Arial", 1, 50));
	   g.drawString(String.valueOf(model.getEnemyKilled()), WIDTH/2, 50);
	   g.setFont(new Font("Arial", 1, 20));
	   g.drawString("Punkty ¿ycia : "+String.valueOf(model.getGunner().getHP()), 10, 20);
	   g.drawString("Stracone cele : "+String.valueOf(model.getMissingEnemy()), 10, 40);
	  
    }
}