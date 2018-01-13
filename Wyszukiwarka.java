import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
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

public class Wyszukiwarka {
//Napis zalogowano jako
//Na gorze pasek z wyszukwianiem
//Po lewo opcja dodania alergi
//Potrzebne jset stale polaczenie z baza w tym oknie
	
	static Connection c=null;
	JFrame ramkawyszukiwarka;
	JTextField wyszukiwarka;
	JButton dodajalergie;
	static String uzytkownik;
	JButton[] dodaj;
	JTextField[] alergienr;
	static int zmiennanralergi=0;
	
	
	
	Wyszukiwarka()
	{
		ramkawyszukiwarka=new JFrame();
		ramkawyszukiwarka.setMinimumSize(new Dimension(400,400));
		ramkawyszukiwarka.pack();
		ramkawyszukiwarka.setLocationRelativeTo(null);
		JPanel ramkawpanel=new JPanel(new BorderLayout());
		JLabel napislogin=new JLabel();
		napislogin.setText(uzytkownik);
		napislogin.setFont(new Font("Serif", Font.PLAIN, 20));
		wyszukiwarka=new JTextField();
		dodajalergie=new JButton("Dodaj alergie");
		ramkawpanel.add(wyszukiwarka,BorderLayout.NORTH);
		
		
		
		ramkawyszukiwarka.add(ramkawpanel);
		ramkawyszukiwarka.add(napislogin,BorderLayout.NORTH);
		ramkawyszukiwarka.add(dodajalergie,BorderLayout.EAST);
		
		
		
		ramkawyszukiwarka.setVisible(true);
		dodajalergiea();
		polaczbaza();
		
		
		
	}
	void dodajalergiea()
	{
		dodajalergie.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				okienkododajalergie();
				akcjadodania();
				
				
				
			}
		});
		
	}
	void okienkododajalergie()
	{
		//Co gdy jest juz uzupelnione ?
		JFrame mokienkoalergie=new JFrame();
		mokienkoalergie.setMinimumSize(new Dimension(280,450));
		mokienkoalergie.pack();
		mokienkoalergie.setLocationRelativeTo(null);
		JPanel mokienkoalergiep=new JPanel(new GridLayout(10,1));
		JLabel[] napisy= new JLabel [5];
		alergienr=new JTextField [5];
		
		for(int i=0;i<5;i++)
		{	
			napisy[i]=new JLabel("Alergia nr: "+(i+1));
			alergienr[i]=new JTextField();	
			mokienkoalergiep.add(napisy[i]);
			mokienkoalergiep.add(alergienr[i]);
			
		}
		JPanel mokienkoaleriged=new JPanel(new GridLayout(10, 1));
		dodaj=new JButton[5];
		JLabel[] pusty=new JLabel[5];
		for(int i=0;i<5;i++)
		{
			dodaj[i]=new JButton("ok"+i);
			pusty[i]=new JLabel();
			mokienkoaleriged.add(pusty[i]);
			mokienkoaleriged.add(dodaj[i]);
			
		}
		JButton zamknij=new JButton("zamknij");
		
		
		mokienkoalergie.add(mokienkoalergiep,BorderLayout.CENTER);
		mokienkoalergie.add(mokienkoaleriged,BorderLayout.EAST);
		mokienkoalergie.add(zamknij,BorderLayout.SOUTH);
		
		
		mokienkoalergie.setVisible(true);
		
		
		
		
		
		
	}
	
	int akcjadodania()
	{

		dodaj[0].addActionListener(new ActionListener() {		
			@Override
			public void actionPerformed(ActionEvent e) {
				wykonajakcje(0);	
			}
		});
dodaj[1].addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				wykonajakcje(1);
				
			}
		});
dodaj[2].addActionListener(new ActionListener() {
	
	@Override
	public void actionPerformed(ActionEvent e) {
		wykonajakcje(2);
		
	}
});
dodaj[3].addActionListener(new ActionListener() {
	
	@Override
	public void actionPerformed(ActionEvent e) {
		wykonajakcje(3);
		
	}
});
dodaj[4].addActionListener(new ActionListener() {
	
	@Override
	public void actionPerformed(ActionEvent e) {
		wykonajakcje(4);
		
	}
});
		
		
		
		
		return 0;
		
		
	}
	
	void wykonajakcje(int ktorynr)
	{
		int czyjest=0;
		
		//sprawdzenie czy juz jest
		try {
			String query="Select * from Alergie where alergia1=? or alergia2=? or alergia3=? or alergia4=? or alergia5=? ";
			PreparedStatement ps;
			ps=c.prepareStatement(query);
			ps.setString(1,alergienr[ktorynr].getText());
			ps.setString(2,alergienr[ktorynr].getText());
			ps.setString(3,alergienr[ktorynr].getText());
			ps.setString(4,alergienr[ktorynr].getText());
			ps.setString(5,alergienr[ktorynr].getText());
			ResultSet odp=ps.executeQuery();
			while(odp.next())
			{
				czyjest=1;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		System.out.println(ktorynr);
		
		//moze w petli ?
		if(czyjest==0)
		{
			for(int i=0;i<5;i++)
			{
				if(i==ktorynr)
				{
					
					try {
						String query=String.format("UPDATE Alergie set %s=? where login=?","alergia"+(ktorynr+1) );
						//System.out.println(string);
						//String query="UPDATE Alergie set alergia1=? where login=?";
						PreparedStatement ps=c.prepareStatement(query);
						//ps.setString(1,"Alergia"+ktorynr+1);
						ps.setString(1,alergienr[ktorynr].getText());
						ps.setString(2,uzytkownik);
						ps.executeUpdate();
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		
		
		}
	}
	
	
	
	
	
	
	
	
	public static Connection polaczbaza()
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
	
}
