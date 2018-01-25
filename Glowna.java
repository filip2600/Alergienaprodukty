
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class Glowna {
	static Interfejs interfejs;
	static Rejestracja rejestracja;
	static Logowanie logowanie;
	
	public static void main(String[] args) {
		
		interfejs=new Interfejs();
		ustawakcje();

	}
	
	static void ustawakcje()
	{
	  interfejs.zaloguj.addActionListener(new ActionListener() {	
		@Override
		public void actionPerformed(ActionEvent e) {
			logowanie=new Logowanie();
			
			
         
		}
	});
	  
	  
	  interfejs.zarejestruj.addActionListener(new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			rejestracja=new Rejestracja();
			
			
		}
	});
	  
	  
	  
	  
	  
	  
	  
	  
	  
	  
	}
	static void pozalogowaniu()
	{
		
	}
	
	static void porejestracji()
	{
		
	}
	
	

}
