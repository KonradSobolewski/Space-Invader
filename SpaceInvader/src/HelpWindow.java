import javax.swing.*;
import java.awt.event.*;
import java.awt.BorderLayout;
import java.awt.TextArea;

 /**
  * 
  * klasa tworzy okienko z informacjami o grze 
  *
  */
public class HelpWindow implements ActionListener
{
	private JFrame frame;
    private JButton button1;
    private JButton button2;
    private JButton button3;
    private JButton button4;
    private JPanel thePanel;
    private TextArea text;

	/**
	 * konsrukor , towrzy okno ,obiekty oraz wczytuje obrazy z folder¹ z gr¹
	 */
    public HelpWindow()
	{   
    	frame=new JFrame("HELP");
        frame.setSize(500,370);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);   

        /**
         * wczytanie ikon guzików
         */
        Icon myIcon1 = new ImageIcon("C:/Users/KonradSob/Desktop/SpaceInvader/src/guzikone.png");
        Icon myIcon2 = new ImageIcon("C:/Users/KonradSob/Desktop/SpaceInvader/src/guziktwo.png");
        Icon myIcon3 = new ImageIcon("C:/Users/KonradSob/Desktop/SpaceInvader/src/guzikthree.png");
        Icon myIcon4 = new ImageIcon("C:/Users/KonradSob/Desktop/SpaceInvader/src/guzikfour.png");
        
        button1 = new JButton(myIcon1);
        button2 = new JButton(myIcon2);
        button3 = new JButton(myIcon3);
        button4 = new JButton(myIcon4);
        thePanel = new JPanel();
        frame.add(thePanel,BorderLayout.WEST);
        
        text=new TextArea("Wybierz Opcje do uzyskania informacji");
        text.setEditable(false);
        frame.add(text,BorderLayout.CENTER);
        
        thePanel.setLayout(new BoxLayout(thePanel, BoxLayout.Y_AXIS));
        thePanel.add(button1);
        thePanel.add(button2);
        thePanel.add(button3);
        thePanel.add(button4);
	    button1.addActionListener(this);
	    button2.addActionListener(this);
	    button3.addActionListener(this);
	    button4.addActionListener(this);
        
	    frame.setVisible(true);
         	
	}
    /**
     * obs³uga guzików 
     */
	public void actionPerformed(ActionEvent e)
	{
		Object key =e.getSource();
		if(key==button1)//wyœwietla info o sterowaniu
		{
			text.setText("Sterowanie\n\n");
			text.append("Poruszanie \n\n");
			text.append("\tW - Góre\n");
			text.append("\tS - Dó³\n");
			text.append("\tD - Prawo\n");
			text.append("\tA - Lewo\n\n");
			text.append("Atak Podstawowy - Mysz \n\n");
			text.append("\tNiewielkie obra¿enia i szybko strzelna broñ\n\n");
			text.append("Atak Specjalny - Spacja \n\n");
			text.append("\tDu¿e obra¿enie , nietykalnoœæ \n\n");
			text.append("Pausa - 1 \n\n");

		}
		if(key==button2)//wyœwietla info o zasadach gry
		{
			text.setText("Zasady Gry\n\n");
			text.append("Strzelaj do przeciwników. Licznik liczy iloœæ zestrzelonych celów\n"
					+ "Twoja postaæ oraz przeciwnicy posiadaj¹ w³asne HP \n"
					+ "Wynik ostateczny zalezy od liczby zabitych i niezabitych \nprzeciwników oraz czasu gry\n"
					+ "Najlepsze winiki zapisywane s¹ do tabeli\n\n");
			   
		}
		if(key==button3)//wyœwietla info o info podstawowych
		{
			text.setText("Informacje Podstawowe\n\n");
			text.append("Twórca\n\n");
			text.append("\tSobolewski Konrad 3AR \n\n");
			text.append("Dokumentacja \n\n");
			text.append("\tW produkcji\n\n");
			
		}
		if(key==button4)//wy³¹cza okno
		{
			frame.setVisible(false);
			
		}
	}    
}