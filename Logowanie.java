import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Logowanie {

	//sprawdza tu dane z bazy jezeli sie zgadza to wchodzi i pokazuje komunikat poprawnie zalogowano
	// jesli nie to komunikat bledu
	//
	Wyszukiwarka w;
	JFrame ramkalogowanie;
	JButton ok;
	JTextField login;
	JTextField haslo;
	static Connection c=null;
	
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
		akcjalogowanie();
		
	
	}
	
	void akcjalogowanie()
	{
		ok.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				//podaje dane
				//sprawdza w bazie czy haslo z loginem sie zgadzaja
				//gdy sie zgadzaja przechodzi do menu gdzie bedzie opcja dodania alergii i szukania
				polaczenie();
				if(logowanie()==1)
				{
					
					
					Wyszukiwarka.uzytkownik=login.getText();
					ramkalogowanie.dispose();
					w=new Wyszukiwarka();					
					System.out.println("Zalogowano !");
				}
				else if(logowanie()==0)
				{
					System.out.println("Bledne dane");
				}
				
				try {
					c.close();
				} catch (SQLException e1) {
					
					
				}
				
				
				//String query="Select * from Alergie where Login=? and Haslo=?";
				//PreparedStatement ps=c.
				
				
				
			}
		});
	}
	
	public static Connection polaczenie()
	{
		try
		{
			Class.forName("org.sqlite.JDBC");
			c=DriverManager.getConnection("jdbc:sqlite:C:\\ProjektyJava\\TesterAlergii\\AlergiaBaza.sqlite");
			
			return c;
			
		}
		catch(Exception e)
		{
		   
			return null;

		}
		
	}
	
	int logowanie()
	{
		int zalogowanie=0;
		try {
			String query="Select * from Alergie where Login=? and Haslo=?";
			PreparedStatement ps;
			ps = c.prepareStatement(query);
			ps.setString(1,login.getText());
			ps.setString(2, haslo.getText());
		ResultSet odp=ps.executeQuery();
		while(odp.next())
		{
			zalogowanie=1;
		}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return zalogowanie;
		
		
		
	}
	
	
	
}
