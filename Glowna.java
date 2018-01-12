
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class Glowna {
	static Interfejs interfejs;
	static Rejestracja rejestracja;
	static Logowanie logowanie;
	
	public static void main(String[] args) {
		
		//1.Interfejs poczatkowy 
		interfejs=new Interfejs();
		ustawakcje();
		
		
		//2.Logowanie  po zalogowaniu przechodzi do wyszukiwarki produktow i pojawia sie mozliwosc wyloguj ktora wraca do poczatku
		
		//3.Rejestracja -Po rejestracji powraca do poczatkowego interfejsu
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
