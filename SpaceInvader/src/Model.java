import java.util.ArrayList;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;
/**
 * 
 * klasa modelu 
 * odpowiada za zmiane parametrów w grze oraz ostanu obiektów 
 *
 */
public class Model
{
	private ArrayList<Bullets> gunList=new ArrayList<Bullets>();
	private  ArrayList<Enemy> enemyList=new ArrayList<Enemy>();
	
	private AnimationGunner sheets;//postac g³ówna
	private Bullets gun;//pociski
	private Random rand=new Random();//potrzebna do lasowej pozycji wroga
	private Timer myTimer;//liczymy czas gry w sekundach
	private TimerTask timeTask;

	private int FinalScore;//wynik ostateczny
	private final int WIDTH,HEIGHT;//szerokosc i wysokosc gry
	private int create;//czas wstêpny po jakim tworzy sie przeciwnik
	private static int enemyKilled=0;//zabici wrogowie w trakcie
	private String name;//imie gracza
	private int MissingEnemy;//pominiêci wrogowie
	private int Killed;//zabici wrogowie na koniec
	private long startTime=0;
	private int secondPassed=0;
	
	private boolean gameOver=false;
	private boolean gameStarted=true;
	private boolean letItGo=false;
	private boolean RunnigTime=true;
	private boolean facingLeft;
	private boolean walking=false;
	private boolean left;
	private boolean right;
	private boolean useAbilityOne=false;
	private boolean useAbilityTwo=false;
	
	/**
	 * masa getterów i setterów....
	 *
	 */
	public String getName() 
	{
		return name;
	}
	public void setName(String name) 
	{
		this.name = name;
	}
	public int getFinalScore() 
	{
		return FinalScore;
	}
	public ArrayList<Bullets> getGunList() 
	{
		return gunList;
	}
	public ArrayList<Enemy> getEnemyList()
	{
		return enemyList;
	}
	public boolean isWalking()
	{
		return walking;
	}
	
	public int getMissingEnemy()
	{
		return MissingEnemy;
	}
	public void setMissingEnemy(int MissingEnemy) 
	{
		this.MissingEnemy = MissingEnemy;
	}
	public void setWalking(boolean walking) 
	{
		this.walking = walking;
	}
	public boolean isLeft() 
	{
		return left;
	}
	public boolean isRight()
	{
		return right;
	}
	public void setFacingLeft(boolean facingLeft)
	{
		this.facingLeft=facingLeft;
	}
	public boolean getFacingLeft()
	{
		return facingLeft;
	}
	
	public boolean getGameOver()
	{
		return gameOver;
	}
	public void setGameOver(boolean GameOver)
	{
		gameOver=GameOver;
	}
	
	public boolean getGameStarted()
	{
		return gameStarted;
	}
	public void setGameStarted(boolean gameStarted)
	{
		this.gameStarted=gameStarted;
	}

	public int getKilled()
	{
		return Killed;
	}
	public void setEnemyKilled(int kill)
	{
		enemyKilled=kill;
	}
	public int getEnemyKilled()
	{
		return enemyKilled;
	}
	
	public AnimationGunner getGunner()
	{
		return sheets;
	}

	public boolean getUseAbilityTwo()
	{
		return useAbilityTwo;
	}
	public void setUseAbilityTwo(boolean two)
	{
		this.useAbilityTwo=two;
	}
	
	public void setStartTime(long startTime)
	{
		this.startTime=startTime;
	}
	public long getStartTime()
	{
		return startTime;
	}

	public void setCreate(int create)
	{
		this.create=create;
	}
	public int getCreate()
	{
		return create;
	}
	
	public int getSecondPassed() 
	{
		return secondPassed;
	}
	public void setSecondPassed(int secondPassed) 
	{
		this.secondPassed = secondPassed;
	}
	/**
	 * metoda odp za wybieranie jaka animacja ma sie dziaæ w danym momencie
	 * ustawiam tak¿e opóŸnienie po którym ma nast¹piæ nastêpna klatka
	 */
	public void movingGunner()
	{
		if((walking  && useAbilityTwo==false) && (walking  && useAbilityOne==false))//chodzenie
		{
			sheets.setFrames(sheets.getWalkingSprites());
			sheets.setDelay(50);
		}
		else if(useAbilityOne || (walking && useAbilityOne)  )//atak pocisku
		{
			sheets.setFrames(sheets.getAttackOne());
			sheets.setDelay(50);
			if(sheets.getImage()==sheets.getAttackOne(7))
			{
				useAbilityOne=false;
			}
		}
		else if(useAbilityTwo || (walking && useAbilityTwo) || (useAbilityTwo && useAbilityOne==false))//atak toczenia
		{
			sheets.setFrames(sheets.getAttackTwo());
			sheets.setDelay(50);
			if(sheets.getImage()==sheets.getAttackTwo(16))
			{
				useAbilityTwo=false;
			}
		}
		else
		{
			sheets.setFrames(sheets.getIdleSprites());//stanie
			sheets.setDelay(200);
		}
		sheets.update();
		if(left)
		{
			facingLeft=true;
		}
		if(right)
		{
			facingLeft=false;
		}
	}
	/**
	 * wczytuje wrogra z pliku i dodaje do listy
	 * @param x - poz x
	 * @param y - poz y
	 * 
	 */
	public Enemy loadEnemy(int x,int y){
		Enemy evil = new Enemy(x,y);
		enemyList.add(evil);
		return evil;
	}
	/**
	 * tworze wroga
	 */
	public void createEnemy()
	{
		int y=rand.nextInt(HEIGHT-80);//losowy wysokoœæ
		long elapsed=(System.nanoTime()-startTime)/1000000;//czas który up³yna³ od ostaniego wykonania startTime
		if(elapsed>create)//jeœli up³yna³ za³o¿ony czas to...
		{
			Enemy evil=new Enemy(WIDTH,y);
			enemyList.add(evil);
			startTime=System.nanoTime();//od nowa licze czas
		}
	}
	/**
	 * zmiana pozycji pocisku
	 */
	public void movingBullets(){
		for(int i=0;i<gunList.size();i++)
		{
				gunList.get(i).update();
		}
	}
	/**
	 * metoda porusza postaci¹
	 * @param XDi - predkosc x
	 * @param XDi2 - predkosc -x 
	 * @param left - czy idzie w lewo
	 * @param right - czy idzie w prawo
	 * @param walking - czy wogole chodzi 
	 */
	public void KeyWSADX(int XDi,int XDi2,boolean left,boolean right,boolean walking)
	{
		if(useAbilityTwo)
			sheets.setXDi(XDi);
			else sheets.setXDi(XDi2);
		this.left=left;
		this.right=right;
		this.walking=walking;
	}
	/**
	 * metoda porusza postaci¹
	 * @param XDi - predkosc y
	 * @param XDi2 - predkosc -y 
	 * @param left - czy idzie w lewo
	 * @param right - czy idzie w prawo
	 * @param walking - czy wogole chodzi 
	 */
	public void KeyWSADY(int YDi,int YDi2,boolean left,boolean right,boolean walking)
	{
		if(useAbilityTwo)
			sheets.setYDi(YDi);
			else sheets.setYDi(YDi2);
		this.left=left;
		this.right=right;
		this.walking=walking;
	}
	/**
	 * tworze pociski
	 */
	public void newBullet()
	{
		gun=new Bullets(sheets.getX(),sheets.getY());
		gun.setSpeed(2);
		gun.setFrames(gun.getBullets());
		gun.setDelay(50);
		gunList.add(gun);
		useAbilityOne=true;
	}
	/**
	 * konstruktor
	 * @param width
	 * @param height
	 */
	public Model(int width,int height)
	{
		sheets=new AnimationGunner(width, height);
		WIDTH=width;
		HEIGHT=height;
		StartTime();//zaczynam liczyæ czas
		myTimer.scheduleAtFixedRate(timeTask, 1000, 1000);
	}
	/**
	 * liczy czas gry
	 */
	public void StartTime()
	{
		secondPassed=0;
		
		myTimer= new Timer();
		timeTask = new TimerTask() {
			
			@Override
			public void run() {
				if(RunnigTime == true)
				{
					secondPassed++;//dodaje sekunde
					letItGo=true;
					//System.out.println(secondPassed);
				}
				else 
				{
					
				}
			}
		};
	}
	/**
	 * klasa zeruje sta³e i ustawia podstwowe parametry
	 */
	public void initStart()
	{
		sheets.setX(WIDTH/2);
		sheets.setY(HEIGHT/2-50);
		sheets.setHP(3);
		setCreate(1200);
		facingLeft=false;
		secondPassed=0;
		MissingEnemy=0;
	}
	/**
	 * funkcja g³ówna gry,obs³uguje zdarzenia
	 */
	public void updateGame()
	{
		if(getGameStarted())
		{
			RunnigTime = true; //nie ma pauzy
			
			/**
			 * Co 10 sekund na mapie tworzy sie wiecej przeciwników
			 */
						
			if((secondPassed+1)%7==0 && letItGo)
			{
				//System.out.println("Zmieniony czas   " + create);
				if(create>401)//zmniejszam czas po jakim utworzy sie przeciwnik
				create-=50;
				letItGo=false;
			}
		
			sheets.move(); //ruch animacji postaci
			movingGunner();//ruch pocisków
			createEnemy(); // tworze wroga jesli mo¿liwe
			movingBullets();// ruszam pociski i animacje
			for(int i=0;i<gunList.size();i++)
			{
				gunList.get(i).move();//jeœli pocisk dojdzie do krañca mapy do usuwany
				if(gunList.get(i).getX()>WIDTH-30)
				{
					gunList.remove(i);
				}
			}
			for(int i=0;i<enemyList.size();i++)
			{
				enemyList.get(i).move();//ruch wroga
				if(enemyList.get(i).getX()<=0)
				{
					enemyList.remove(i);//jesli dojdzie do koñca usuwam i zwiêkszam licznik pominiêtych wrogów
					MissingEnemy++;
				}
				else if(enemyList.get(i).getRect().intersects(sheets.getRect()))//jesli wrog i bohater ma kontakt...
				{
					enemyList.remove(i);//...usuwam wroga
					enemyKilled++;//..zwiêkszam licznik
					if(useAbilityTwo==false)//...jak sie nie toczy traci zycie
						sheets.setHP();
					if(sheets.getHP()==0)//jeœli giniemy
					{
				    	RunnigTime=false;//czas zatrzymuje
						setGameOver(true);
						Killed=enemyKilled;
						setGameStarted(false);
						FinalScore=getKilled()*10-getSecondPassed()-getMissingEnemy()*5;//obliczam nasz wynik 
						if (FinalScore<0)//jesli mniejszy od zera to zero
				    		   FinalScore=0;
					    enemyList.removeAll(enemyList);//czyszcze listy
					    gunList.removeAll(gunList);
					    enemyKilled=0;
					    break;
					}
					
				}
				for(int s=0;s<gunList.size();s++)
				{
					if(gunList.get(s).getRect().intersects(enemyList.get(i).getRect()))//jesli pocisk ma kontakt z wrogiem
					{
						enemyList.get(i).setHp();//zmniejszamy ¿ycie wroga
						if(enemyList.get(i).getHp()==0)//kasuje gdy jest rowne 0
						{
							enemyList.remove(i);
							enemyKilled++;
						}
						gunList.remove(s);//zawsze usuwam pocisk
						break;
					}
				}
			}//for
		}
		else //pauza
		{
			RunnigTime =false;//zatrzymuje czas
		}//gamestarted
	}//update
}//clasa