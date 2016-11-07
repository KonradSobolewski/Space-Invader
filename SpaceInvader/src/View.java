import java.io.File;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

/**
 * 
 * klasa odpowiedzialna za wyœwietlanie okna gry oraz menu akcji w grze i jej obslugi 
 *
 */
public class View implements ActionListener
{
	private final int WIDTH,HEIGHT;
	private JMenuBar menubar ;
	private JMenu file ;
	private JMenu info ;
	private JMenuItem exit ;
	private JMenuItem save ;
	private JMenuItem open ;
	private JMenuItem pouse ;
	private JMenuItem Checkscore ;
	private JMenuItem help ;
	private JMenuItem SaveScore ;
	private AnimationPanel Ap;
	private JFrame frame;
	
	public AnimationPanel getAPanel()
	{
		return Ap;
	}
	/**
	 * 
	 * @param width - szerokoœæ okienka
	 * @param height - wysokoœæ okienka
	 * 
	 * konstruktor tworzy okienko gry
	 */
	public View(int width , int height)
	{
		frame=new JFrame("Space Invader");
	    WIDTH=width;
        HEIGHT=height;
		frame.setSize(WIDTH,HEIGHT);
		frame.setDefaultCloseOperation(frame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		
	    Ap=new AnimationPanel(WIDTH, HEIGHT);
	    frame.add(Ap);
		menu();
		frame.setVisible(true);
	}
	
	/**
	 * odpowiada za stowrzenie menu gry
	 */
	public void menu()
	{
		menubar=new JMenuBar();
		frame.setJMenuBar(menubar);

		file=new JMenu("File");
		menubar.add(file);
		info=new JMenu("Info");
		menubar.add(info);
		
		save=new JMenuItem("Save");
		file.add(save);
		save.addActionListener(this);
		
		open=new JMenuItem("Load");
		file.add(open);
		open.addActionListener(this);
		
		file.addSeparator();
		pouse=new JMenuItem("Pause - 1");
		file.add(pouse);
		pouse.addActionListener(this);
		
		SaveScore=new JMenuItem("Save Score");
		file.add(SaveScore);
		SaveScore.addActionListener(this);
		
		Checkscore=new JMenuItem("Open Scores");
		file.add(Checkscore);
		Checkscore.addActionListener(this);
		
		file.addSeparator();
		exit=new JMenuItem("Exit");
		file.add(exit);
		exit.addActionListener(this);
		
		help=new JMenuItem("Help");
		info.add(help);
		help.addActionListener(this);
	}

	/**
	 * funkcja obs³uguj¹ca okienka menu 
	 */
	public void actionPerformed(ActionEvent e)
	{
		Object key =e.getSource();
		if(key==open)
		{
			Ap.getModel().setGameStarted(false); //pauzuje gre
			JFileChooser fc=new  JFileChooser();
			if(fc.showOpenDialog(null)==JFileChooser.APPROVE_OPTION)//tworzy sie okno wczytania
			{
				File plik=fc.getSelectedFile();
				Ap.getController().loadFile(plik);
				JOptionPane.showMessageDialog(null, "Wybrany plik to "+plik.getAbsolutePath());
			}
		}
		else if(key==save)
		{
			Ap.getModel().setGameStarted(false);//pauzuje gre
			JFileChooser fc=new JFileChooser();
			if(fc.showSaveDialog(null)==JFileChooser.APPROVE_OPTION)//tworzy sie okno zapisu
			{
				File plik=fc.getSelectedFile();
				Ap.getController().saveFile(plik);
				JOptionPane.showMessageDialog(null, "Wybrany plik to "+plik);
			}
		}
		else if(key==exit)
		    System.exit(0);//koñcze dzia³anie programu
		else if(key==pouse && Ap.getModel().getGameStarted()==true)
		{
			Ap.getModel().setGameStarted(false);//pauzuje gre
		}
		else if(key==pouse && Ap.getModel().getGameStarted()==false)
		{
			if(Ap.getModel().getGameOver()==false)//jesli gra sie nie skoñczy³a mozna j¹ tylko pauzowaæ
				Ap.getModel().setGameStarted(true);//pauzuje gre
		}
		else if(key==help)
		{
			Ap.getModel().setGameStarted(false);//pauzuje gre
			new HelpWindow();//tworze okienko zawieraj¹ce info o grze
		}
		else if(key==Checkscore)
		{
			Ap.getModel().setGameStarted(false);//pauzuje gre
			new ScoresWindow(Ap.getController());//tworze okienko odp za pokazaniu wyników 
		}
		else if(key==SaveScore && Ap.getModel().getGameOver()==true)//mo¿liwe zapisanie wyniku tylko po skonczeniu gry
		{
			new SaveWindow(Ap.getModel(),Ap.getController());//tworze okienku zapisu wyniku
		}
		else if(key==SaveScore && Ap.getModel().getGameOver()==false)//informacje dla klikniêcia gdy wci¹¿ gramy
		{
			Ap.getModel().setGameStarted(false);
			JOptionPane.showMessageDialog(null, "Musisz najpierw zakoñczyæ grê!");
		} 
	}
}
