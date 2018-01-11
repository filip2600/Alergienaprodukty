import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Rejestracja {
   //Wyswietla sie okno podawania danych
	//Login haslo
	//Na co alergia
	//dodaje do bazy 
	Rejestracja()
	{
		JFrame ramkarejestracja=new JFrame();
		ramkarejestracja.setMinimumSize(new Dimension(400, 150));	
		JPanel ramkarejestracjapanel=new JPanel(new GridLayout(3,2,2,2));
		JLabel loginnapis=new JLabel("LOGIN: ");
		JLabel haslonapis=new JLabel("HASLO: ");
		JButton ok=new JButton("ok");
		
		
		JTextField login=new JTextField();
		JTextField haslo=new JTextField();
		
		//po podaniu tego wyswietla sie kolejna strona w ktorej podaje sie
		//alergie osobno lub wybiera z przewijanej listy
		
		
		
		ramkarejestracjapanel.add(loginnapis);
		ramkarejestracjapanel.add(login);
		ramkarejestracjapanel.add(haslonapis);
		ramkarejestracjapanel.add(haslo);
		ramkarejestracjapanel.add(ok,0,4);
		
		ramkarejestracja.add(ramkarejestracjapanel);
		ramkarejestracja.setVisible(true);
		
		
	}
	
	
}
