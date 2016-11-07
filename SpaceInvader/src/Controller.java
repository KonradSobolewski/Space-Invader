import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Collections;

/**
 * 
 * klasa controllera 
 * znajduj¹ sie w niej metody obslugi plików , klawiatury,myszy oraz uruchamia zmiane stanu modelu
 *
 */
public class Controller extends KeyAdapter implements Runnable
{
	private thehandler handler=new thehandler();//klasa wewnêtrzna
    private AnimationPanel aPanel;
    private Model model;
    private ScoresWindow scores;
    
    
    public void SetScores(ScoresWindow score)
    {
    	this.scores=score;
    }
    /**
     * 
     * @param anPanel dostaje klase view odp za odœwie¿anie obrazu
     * @param model dostaje model
     */
	public Controller(AnimationPanel anPanel,Model model)
	{
		aPanel=anPanel;
		aPanel.addMouseListener(handler);//ustawiam s³uchaczy na panel w view
		aPanel.addMouseMotionListener(handler);	
		this.model=model;
		this.model.setFacingLeft(false); //postaæ stoi w prawo na starcie
	}

	public void run()
	{	
		model.initStart();//uruchamiam funkcje odp za restarowanie sta³ych w programie
		while(true)
		{
			try
			{
		     		model.updateGame();//odœwie¿am model
					Thread.sleep(3);//wstrzymuje akcje 
					
			}
			catch(Exception e)
			{
				System.out.println("Error");
			}
		}
	}
	
	/**
	 * obs³uga klawiatury w czasie wciœniêcia
	 */
	public void keyPressed(KeyEvent e)
	{
		int keyCode=e.getKeyCode();
		if(keyCode==e.VK_SPACE && model.getGameStarted())//atak specjalny , mo¿liwe jak nie ma pauzy 
		{
			model.setUseAbilityTwo(true); 
			// zak³adam toczenie sie przed siebie 
			if(model.isLeft())
			{
				model.getGunner().setXDi(-2);
			}
			else if(model.isRight())
			{
				model.getGunner().setXDi(2);
			}
		}
		if(keyCode==e.VK_A && model.getGameStarted())//chodzenie jeœli nie ma pauzy 
		{
			 model.KeyWSADX(-2,-1,true,false,true);
		}
		if(keyCode==e.VK_D && model.getGameStarted())
		{
			model.KeyWSADX(2,1,false,true,true);
		}
		if(keyCode==e.VK_W && model.getGameStarted())
		{
			model.KeyWSADY(-2,-1,false,true,true);
		}
		if(keyCode==e.VK_S && model.getGameStarted())
		{
			model.KeyWSADY(2,1,true,false,true);
		}
		
		if(keyCode==e.VK_1 && model.getGameStarted() && model.getGameOver()==false)
		{
			model.setGameStarted(false);//pauzuje gre
		}
		else if(keyCode==e.VK_1 && model.getGameStarted()==false && model.getGameOver()==false)
		{
			model.setGameStarted(true); // odpauzuje gre
		}
		else if(keyCode==e.VK_1 && model.getGameStarted()==false && model.getGameOver())
		{
			model.setGameStarted(true);//restartuje gre
			model.setGameOver(false);
			model.initStart();//uruchamiam funkcje odp za restarowanie sta³ych w programie
		}
	}
	/**
	 * obs³uga w czasie wciœniêtych guzików
	 */
	public void keyReleased(KeyEvent e)
	{
		//wy³¹czam chodzenie
		int keyCode=e.getKeyCode();
		if(keyCode==e.VK_A)
		{
			model.getGunner().setXDi(0);
			model.setWalking(false);
		}
		else if(keyCode==e.VK_D)
		{
			model.getGunner().setXDi(0);
			model.setWalking(false);
		}
		else if(keyCode==e.VK_W)
		{
			model.getGunner().setYDi(0);
			model.setWalking(false);
		}
		else if(keyCode==e.VK_S)
		{
			model.getGunner().setYDi(0);
			model.setWalking(false);
		}
	}

	/**
	 * klasa zagnie¿d¿ona odpowiadaj¹ca za obs³uge myszy , nas³uchiwany panel klasy view
	 *
	 */
	private class thehandler implements MouseMotionListener,MouseListener
	{
		@Override
		public void mouseDragged(MouseEvent e) {
		}
		@Override
		public void mouseMoved(MouseEvent e) {
		}
		@Override
		public void mouseClicked(MouseEvent e) {
		}
		@Override
		public void mouseEntered(MouseEvent e) {
		}
		@Override
		public void mouseExited(MouseEvent e) {
		}
		@Override
		public void mousePressed(MouseEvent e) //gdy wciœniêty...
		{
			if (model.getGameStarted() && model.getUseAbilityTwo()==false)//...i gra nie zpauzowana....
			{
				model.newBullet();//...tworze pocisk
			}
		}
		@Override
		public void mouseReleased(MouseEvent e) {	
		}
	}
	/**
	 * zapis do pliku gry
	 */
	public void saveFile(File selectedFile)
	{
		try
		{
			BufferedWriter writer= new BufferedWriter(new FileWriter(selectedFile));
			writer.write(String.valueOf(model.getGunner().getX()));//zapis pozycji X bohatera
			writer.newLine();
			writer.write(String.valueOf(model.getGunner().getY()));//zapis pozycji Y bohatera
			writer.newLine();
			writer.write(String.valueOf(model.getGunner().getHP()));//zapis ¿ycia bohatera
			writer.newLine();
			writer.write(String.valueOf(model.getEnemyKilled())); //zapis ilosci zabitych wrogow
			writer.newLine();
			writer.write(String.valueOf(model.getSecondPassed()));//zapis czasu gry
			writer.newLine();
			writer.write(String.valueOf(model.getCreate()));//zapis jak czêsto ma sie tworzyæ wróg
			writer.newLine();
			writer.write(String.valueOf(model.getMissingEnemy()));//zapis pominiêtych wrogów
			writer.newLine();
			for(int i =0;i<model.getEnemyList().size();i++)
			{
				writer.write(String.valueOf(model.getEnemyList().get(i).getX()));//zapis pozycji x wroga
				writer.newLine();
				writer.write(String.valueOf(model.getEnemyList().get(i).getY()));//zapis pozycji y wroga
				writer.newLine();
			}
			writer.close();
		}
		catch(IOException ex)
		{
			System.out.println("Nie mo¿na zapisaæ pliku");
			ex.printStackTrace();
		}
	}
	/**
	 * wczytanie z pliku gry
	 */
	public void loadFile(File selectedFile)
	{
		int tempx,tempy;
		try
		{
			model.getEnemyList().removeAll(model.getEnemyList()); // czyszcze aktualne listy
			model.getGunList().removeAll(model.getGunList());
			
			BufferedReader reader = new BufferedReader(new FileReader(selectedFile));
			String line=null;
			line=reader.readLine();
			model.getGunner().setX(Integer.parseInt(line));//wczytanie pozycji X bohatera
			line=reader.readLine();
			model.getGunner().setY(Integer.parseInt(line));//wczytanie  pozycji Y bohatera
			line=reader.readLine();
			model.getGunner().setHP(Integer.parseInt(line));//wczytanie  ¿ycia bohatera
			line=reader.readLine();
			model.setEnemyKilled(Integer.parseInt(line));//wczytanie  ilosci zabitych wrogow
			line=reader.readLine();
			model.setSecondPassed(Integer.parseInt(line));//wczytanie  czasu gry
			line=reader.readLine();
			model.setCreate(Integer.parseInt(line));//wczytanie  jak czêsto ma sie tworzyæ wróg
			line=reader.readLine();
			model.setMissingEnemy(Integer.parseInt(line));//wczytanie  pominiêtych wrogów
			while((line=reader.readLine())!=null)
			{
				tempx=Integer.parseInt(line);//wczytanie  pozycji x wroga
				line=reader.readLine();
				tempy=Integer.parseInt(line);//wczytanie  pozycji y wroga
				model.loadEnemy(tempx, tempy);
			}
			model.setGameStarted(false);
			reader.close();
		}
		catch(IOException ex)
		{
			System.out.println("Nie mo¿na wczytaæ pliku");
			ex.printStackTrace();
		}
		model.setGameOver(false);
	}
	/**
	 * wczytanie wyników
	 */
	public void LoadScores()
	{
		try
		{
			BufferedReader reader = new BufferedReader(new FileReader("C:/Users/KonradSob/Desktop/SpaceInvader/scores.txt"));//wczytanie z pliku
			String line=null;
			while((line=reader.readLine())!=null)
			{
				Score OneScore=new Score();
				OneScore.setName(line);//wczytanie imienia
				line=reader.readLine();
				OneScore.setScore(Integer.parseInt(line));//wczytanie wyniku
				scores.getScoreList().add(OneScore);
			}
			reader.close();
		}
		catch(IOException ex)
		{
			System.out.println("Nie mo¿na wczytaæ pliku");
			ex.printStackTrace();
		}
	}
	public void SaveScore()
	{ 
		ScoresWindow save= new ScoresWindow(this,"unvisible");//uruchamiam jeden z konstruktorów klasy Scores
		Score MyScore=new Score();
		
        MyScore.setName(model.getName());//pobieram imie
        MyScore.setScore(model.getFinalScore());//pobieram wynik
		scores.getScoreList().add(MyScore);//dodaje "wynik" do listy
		scores.sort();//sortuje
		Collections.reverse(scores.getScoreList());//odwracam
		
		
		if (scores.getScoreList().size()==11)//jeœli lista ma 11 element to kasuje ostatni
			scores.getScoreList().remove(10);
		try
		{
			BufferedWriter writer= new BufferedWriter(new FileWriter("C:/Users/KonradSob/Desktop/SpaceInvader/scores.txt"));
			for(int i =0;i<save.getScoreList().size();i++)
			{
				writer.write(String.valueOf(scores.getScoreList().get(i).getName()));//zapis imienia
				writer.newLine();
				writer.write(String.valueOf(scores.getScoreList().get(i).getScore()));//zapis wyniku
				writer.newLine();
			}
			writer.close();
		}
		catch(IOException ex)
		{
			System.out.println("Nie mo¿na zapisaæ pliku");
			ex.printStackTrace();
		}
		ScoresWindow save2= new ScoresWindow(this);//otwieram wyniki dla wgl¹du
	}
}