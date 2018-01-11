import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;

public class Interfejs {

	JButton zaloguj;
	 JButton zarejestruj;
	 JFrame ramkainterfejs;
	
	Interfejs()
	{
		 ramkainterfejs=new JFrame();
		ramkainterfejs.setMinimumSize(new Dimension(400, 350));
		JPanel pramkainterfejs=new JPanel(new GridLayout());
		pramkainterfejs.setBackground(Color.DARK_GRAY);
		JPanel napisgora=new JPanel(new BorderLayout());
	   Border margines=BorderFactory.createEmptyBorder(0,150,0,0);
		JLabel powitanie=new JLabel("Hello");
		powitanie.setBorder(margines);
		powitanie.setFont(new Font("TimesRoman", Font.PLAIN, 30));
		powitanie.setForeground(Color.GREEN);
		zaloguj=new JButton("zaloguj");
		zarejestruj=new JButton("Zarejestruj");
		ImageIcon ikonkaobrazek = new ImageIcon("C:/ProjektyJava/TesterAlergii/obrazki/kciuk.jpg");
		Image obrazeki=ikonkaobrazek.getImage();
		Image nowyobrazek=obrazeki.getScaledInstance(250, 250, Image.SCALE_SMOOTH);
		ImageIcon gotowy=new ImageIcon(nowyobrazek);
		JLabel obrazek=new JLabel(gotowy);
		

		napisgora.add(powitanie);
		pramkainterfejs.add(zaloguj);
		pramkainterfejs.add(zarejestruj);
		
		
		ramkainterfejs.add(napisgora,BorderLayout.NORTH);
		ramkainterfejs.add(pramkainterfejs,BorderLayout.CENTER);
		ramkainterfejs.add(obrazek,BorderLayout.SOUTH);
		
	
		
	
		ramkainterfejs.setVisible(true);
	}
	
	
	
	
	
	
}
