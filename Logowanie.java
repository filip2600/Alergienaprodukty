import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Logowanie {

	//sprawdza tu dane z bazy jezeli sie zgadza to wchodzi i pokazuje komunikat poprawnie zalogowano
	// jesli nie to komunikat bledu
	//
	JFrame ramkalogowanie;
	JButton ok;
	JTextField login;
	JTextField haslo;
	
	Logowanie()
	{
		ramkalogowanie=new JFrame();
		ramkalogowanie.setMinimumSize(new Dimension(400, 150));
		ramkalogowanie.pack();
		ramkalogowanie.setLocationRelativeTo(null);
		JPanel ramkalogowaniepanel=new JPanel(new GridLayout(3,2,2,2));
		JLabel loginnapis=new JLabel("LOGIN: ");
		JLabel haslonapis=new JLabel("HASLO: ");
		ok=new JButton("Zaloguj !");
		
		login=new JTextField();
		haslo=new JTextField();
		ramkalogowaniepanel.add(loginnapis);
		ramkalogowaniepanel.add(login);
		ramkalogowaniepanel.add(haslonapis);
		ramkalogowaniepanel.add(haslo);
		
		ramkalogowanie.add(ramkalogowaniepanel);
		ramkalogowanie.add(ok,BorderLayout.SOUTH);
		ramkalogowanie.setVisible(true);
		
		
	}
	
	
	
}
