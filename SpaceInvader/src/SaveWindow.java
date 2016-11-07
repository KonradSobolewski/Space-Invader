import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

/**
 * klasa odpowiedzialna za ma³e okienko zapisu wyniku
 */
public class SaveWindow implements ActionListener
{
	private JFrame frame;
	private JTextField text;
	private String name;
	private JLabel text1;
	private JLabel text2;
	private Model model;
	private Controller controller;
	private JButton button;
	
	public String getName()
	{
		return name;
	}
	/**
	 * 
	 * tworze szate graficzn¹
	 */
	public SaveWindow(Model model,Controller controller)
	{
		this.model=model;
		this.controller=controller;
		frame=new JFrame("Add Your Score");
		frame.setLocationRelativeTo(null);
		frame.setSize(300,150);
		frame.setResizable(false);
    
		Container contentpane=frame.getContentPane();
		contentpane.setLayout(new FlowLayout());
    
		text1 = new JLabel("Wpisz tu swoje Imie i Wcisnij Enter");
		text2 = new JLabel("");
		text=new JTextField(20);
		text.addActionListener(this);
		button=new JButton("Save");
		button.addActionListener(this);
		contentpane.add(text1);
		contentpane.add(text);
		contentpane.add(button);
		contentpane.add(text2);
    
		frame.setVisible(true);
    
	}
	/**
	 * obs³uga guzików
	 */
	public void actionPerformed(ActionEvent e)
	{
		Object key = e.getSource();
		if(key == text)//po potwiedzeniu wpisanego tekstu , tekst jest wysy³any do medelu oraz pojawia sie instrukcja co dalej
		{
			name=text.getText();
			System.out.println(name);
			model.setName(name);
			text2.setText("    Dobrze! Wciœnij Save    ");
		}
		else if ( key ==button && name!=null)//jesli imie zostalo wpisane zapisuje ca³y wynik do pliku
		{
			controller.SaveScore();
		}
		else if ( key ==button && name==null)//jesli imie zosta³o nie wpisane wynik nie zostanie dodany
			text2.setText("   Wpisz imie    ");
	}
}
