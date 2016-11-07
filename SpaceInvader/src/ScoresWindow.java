import java.awt.Container;
import java.awt.FlowLayout;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import javax.swing.JFrame;
import javax.swing.JTextField;

/**
 * 
 * otwiera okno z wynikami 
 * przetrzymuje wyniki w liscie 
 *
 */
public class ScoresWindow  
{
	private JTextField text;
	private JTextField[] wyniki;//tablica na pola tekstowe
	private JFrame frame; 
	private Controller event;
	private ArrayList<Score> ScoreList=new ArrayList<Score>();
	
	/**
	 * gettery
	 */
	public ArrayList<Score> getScoreList() 
	{
		return ScoreList;
	}
	
	public JFrame getFrame() {
		return frame;
	}
/**
 * konstrukotr
 * @param controller - klasa dostaje controller 
 */
	public ScoresWindow(Controller controller)
	{
		event=controller;
		frame=new JFrame("Best Scores");
        frame.setSize(400,350);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
       
        event.SetScores(this);
        event.LoadScores();
      /**
       * tworze szate graficzn¹ 
       */
        Container contentpane=frame.getContentPane();
        contentpane.setLayout(new FlowLayout());
        text=new JTextField("\t           Best scores",30);
        text.setEditable(false);
        contentpane.add(text); 
        int k;
        wyniki=new JTextField[ScoreList.size()];// tyle ile wyników - tyle pól tekstowych
        for(int i=0;i<ScoreList.size();i++)
        {
        	k=1+i;
        	wyniki[i]=new JTextField(String.valueOf(0),30);
        	wyniki[i].setText(k+". "+ScoreList.get(i).getName()+"  Wynik: "+String.valueOf(ScoreList.get(i).getScore()));
        	wyniki[i].setEditable(false);
        	contentpane.add(wyniki[i]);
        }
        
        frame.setVisible(true);
	}
	/**
	 * construktor tylko dla zapisu wyników 
	 * @param name - nazwa gracza 
	 */
	public ScoresWindow(Controller controller,String name)
	{
		event=controller;
		event.SetScores(this);
        event.LoadScores();
	}

	/**
	 * funkcja sortuje od najmniejszego do najwiêkszego wyniku w liscie 
	 */
	public void sort()
	{
		Collections.sort(ScoreList, new Comparator<Score>()
		{
	        @Override
	        public int compare(Score wynik1, Score  wynik2)
	        {
	        	return Integer.compare(wynik1.getScore(),wynik2.getScore());
	        }
	    });
	}

}