import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

public class Wyszukiwarka {

	
	static Connection c=null;
	JFrame ramkawyszukiwarka;
	JTextField wyszukiwarka;
	JButton dodajalergie;
	static String uzytkownik;
	JButton[] dodaj;
	JTextField[] alergienr;
	static int zmiennanralergi=0;
	JTable tabela;
	DefaultTableModel model;
	List<String> a;
	JFrame ramkazobrazkiem;
	
	
	
	
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
		model=new DefaultTableModel();
		tabela=new JTable(model);
		model.addColumn("Produkty");
		ramkawpanel.add(tabela,BorderLayout.CENTER);
		a = new ArrayList<String>();
	
		
		
		ramkawyszukiwarka.add(ramkawpanel);
		ramkawyszukiwarka.add(napislogin,BorderLayout.NORTH);
		ramkawyszukiwarka.add(dodajalergie,BorderLayout.EAST);
		ramkazobrazkiem=new JFrame();
		
		
		ramkawyszukiwarka.setVisible(true);
		dodajalergiea();
		polaczbaza();
		wyszukiwarkaa();
		kliktabela();
		
		
		
	}
	
	void kliktabela()
	{ListSelectionModel lm=tabela.getSelectionModel();
	lm.addListSelectionListener(new ListSelectionListener() {
		
		@Override
		public void valueChanged(ListSelectionEvent e) {
			if(! lm.isSelectionEmpty())
			{
				if(e.getValueIsAdjusting()==false)
				{
				int wybrany=lm.getMinSelectionIndex();
				String value = tabela.getModel().getValueAt(wybrany,0).toString();
				System.out.println(value);
				obrazekznapisem(value);
				}
			}
			
		}
	});
		
	}
	
	void wyszukiwarkaa()
	{
	
		wyszukiwarka.getDocument().addDocumentListener(new DocumentListener() {
			
			@Override
			public void removeUpdate(DocumentEvent e) {
				// Pusta
			
			}
			
			@Override
			public void insertUpdate(DocumentEvent e) {
				
				szukanie();
			}
			
			@Override
			public void changedUpdate(DocumentEvent e) {
			//Pusta
				
			}
		});
		
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
		
	
		if(czyjest==0)
		{
			for(int i=0;i<5;i++)
			{
				if(i==ktorynr)
				{
					
					try {
						String query=String.format("UPDATE Alergie set %s=? where login=?","alergia"+(ktorynr+1) );					
						PreparedStatement ps=c.prepareStatement(query);
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
	
	void szukanie()
	{
		
		model.setRowCount(0);
		
		try {
			String wyszukiwarkatekst=wyszukiwarka.getText();
			String query="SELECT * from Produkty where Zawartosc1 LIKE ? or Zawartosc2 Like ? or Zawartosc3 Like ?"
					+ "or Zawartosc4 Like ? or Zawartosc5 Like ? or Zawartosc6 Like ?";
			PreparedStatement ps;
			ps=c.prepareStatement(query);
			ps.setString(1,wyszukiwarkatekst+"%");
			ps.setString(2,wyszukiwarkatekst+"%");
			ps.setString(3,wyszukiwarkatekst+"%");
			ps.setString(4,wyszukiwarkatekst+"%");
			ps.setString(5,wyszukiwarkatekst+"%");
			ps.setString(6,wyszukiwarkatekst+"%");
			ResultSet odp=ps.executeQuery();
			while(odp.next())
			{	
					model.addRow(new Object[] {odp.getString(1)});
				System.out.println(odp.getString(1));
				a.add(odp.getString(1));
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		
	}
	
	void obrazekznapisem(String wybrany)
	{
		String gdzieszukac="";
		if(ramkazobrazkiem.isVisible())
		{
		ramkazobrazkiem.dispose();
		}
		ramkazobrazkiem=new JFrame();
		ramkazobrazkiem.setMinimumSize(new Dimension(150,300));
		ramkazobrazkiem.move(1460, 500);
		
		
		DefaultTableModel zawieramodel=new DefaultTableModel();
		zawieramodel.addColumn("Zawiera");
		JTable zawiera=new JTable(zawieramodel);
		
		
		
		
		
		
		
		
		if(wybrany.contains("Pieczywo bia³e"))
		{
			ImageIcon ikonkaobrazek = new ImageIcon("C:/ProjektyJava/TesterAlergii/obrazki/pieczywo.jpg");
			Image obrazeki=ikonkaobrazek.getImage();
			Image nowyobrazek=obrazeki.getScaledInstance(150,110, Image.SCALE_SMOOTH);
			ImageIcon gotowy=new ImageIcon(nowyobrazek);
			JLabel obrazek=new JLabel(gotowy);
			
			ramkazobrazkiem.add(obrazek,BorderLayout.NORTH);
			gdzieszukac="Pieczywo bia³e";
			
			
			
		}
		if(wybrany.contains("Sernik"))
		{
			ImageIcon ikonkaobrazek = new ImageIcon("C:/ProjektyJava/TesterAlergii/obrazki/sernik.jpg");
			Image obrazeki=ikonkaobrazek.getImage();
			Image nowyobrazek=obrazeki.getScaledInstance(150,110, Image.SCALE_SMOOTH);
			ImageIcon gotowy=new ImageIcon(nowyobrazek);
			JLabel obrazek=new JLabel(gotowy);
			
			ramkazobrazkiem.add(obrazek,BorderLayout.NORTH);
			gdzieszukac="Sernik";
			
		}
		
		
		try {
			String query="Select * from Produkty where Nazwa =?";
			PreparedStatement ps;		
			ps=c.prepareStatement(query);
			ps.setString(1,gdzieszukac);
			ResultSet odp=ps.executeQuery();
			while(odp.next())
			{
				zawieramodel.addRow(new Object[] {odp.getString(2)});
				zawieramodel.addRow(new Object[] {odp.getString(3)});
				zawieramodel.addRow(new Object[] {odp.getString(4)});
				zawieramodel.addRow(new Object[] {odp.getString(5)});
				zawieramodel.addRow(new Object[] {odp.getString(6)});
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ramkazobrazkiem.add(zawiera,BorderLayout.SOUTH);
		ramkazobrazkiem.show();
		
		
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
