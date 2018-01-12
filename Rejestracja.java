import java.awt.BorderLayout;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import java.sql.*;
import javax.swing.*;

public class Rejestracja {
   
	//Na co alergia
	//dodaje do bazy 
	JFrame ramkarejestracja;
	JButton ok;
	static Connection c=null;
	JTextField login;
	JTextField haslo;
	Rejestracja()
	{
		ramkarejestracja=new JFrame();
		ramkarejestracja.setMinimumSize(new Dimension(400, 150));
		ramkarejestracja.pack();
		ramkarejestracja.setLocationRelativeTo(null);
		JPanel ramkarejestracjapanel=new JPanel(new GridLayout(3,2,2,2));
		JLabel loginnapis=new JLabel("LOGIN: ");
		JLabel haslonapis=new JLabel("HASLO: ");
		ok=new JButton("Rejestruj !");
		
		
		login=new JTextField();
		haslo=new JTextField();
		
		//po podaniu tego wyswietla sie kolejna strona w ktorej podaje sie
		//alergie osobno lub wybiera z przewijanej listy
		
		
		
		ramkarejestracjapanel.add(loginnapis);
		ramkarejestracjapanel.add(login);
		ramkarejestracjapanel.add(haslonapis);
		ramkarejestracjapanel.add(haslo);
		
		//https://stackoverflow.com/questions/2510159/can-i-add-a-component-to-a-specific-grid-cell-when-a-gridlayout-is-used
		
		ramkarejestracja.add(ramkarejestracjapanel);
		ramkarejestracja.add(ok,BorderLayout.SOUTH);
		ramkarejestracja.setVisible(true);
		
		akcjarejestracja();
		
		
		
	}
	
	void akcjarejestracja()
	{
		ok.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				//Dodaje do bazy 
          
				
				
				polaczenie();
				int spr=czyjuzjest();
				if(spr==1)
				{
					System.out.println("Taki login juz jest");
				}
				else if(spr==0)
				{		
					dodaj();
					System.out.println("rejestracja przebiegla pomyslnie !");
					ramkarejestracja.dispose();
					
				}
				
				
				
				
				
				
				
				
				
				try {
					c.close();
				} catch (SQLException e1) {
					
					
				}
				
				
				
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
	
	int czyjuzjest()
	{
		int zwrot=0;
		
		try {
			String query="select * from Alergie where Login=? ";
			PreparedStatement ps=c.prepareStatement(query);
			ps.setString(1,login.getText());
			ResultSet odpowiedz=ps.executeQuery();
			while(odpowiedz.next())
			{
				zwrot=1;
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		return zwrot;
		
	}
	void dodaj()
	{
		
		try {
			
			String query="Insert into Alergie(login,haslo)"+"values (?,?)";
			PreparedStatement ps=c.prepareStatement(query);
			ps.setString(1,login.getText());
			ps.setString(2,haslo.getText());
			ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
		}
		
	}

	
	
}
