import java.awt.Image;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.swing.ImageIcon;

public class Minesweeper_engine {

	Polje[][] polja;
	public int ok;
	
	public Minesweeper_engine() {
		
		ok=0;
		inicijalizuj();
	}
	public void inicijalizuj() {
		
		List<Polje> poljaZaRandomIzbor = new ArrayList<>();
		
		for (int i = 0; i < 90; i++) {
			poljaZaRandomIzbor.add(new Polje(0, false));	
		}
		for (int i = 90; i < 100; i++) {
			poljaZaRandomIzbor.add(new Polje(-1, false));
		}
		
		polja = new Polje[10][10];
		Random r = new Random();
		ok=0;
		
		for (int i = 0; i < polja.length; i++) {
			for (int j = 0; j < polja[0].length; j++)
			{
				int indeks = r.nextInt(poljaZaRandomIzbor.size());
				polja[i][j] = poljaZaRandomIzbor.get(indeks);
				poljaZaRandomIzbor.remove(indeks);
			}
		}
	}
	public ImageIcon dajSliku(int i, int j) {
		
		String nazivFajla = "";
		String putanja;
		
		if(polja[i][j].isVisible)
		{
			if(polja[i][j].id == -1)
				nazivFajla = "bomba.PNG";
			else if(polja[i][j].id == 0)
				nazivFajla = "otvoreno.PNG";
			else if(polja[i][j].id == 1)
				nazivFajla = "1.PNG";
			else if(polja[i][j].id == 2)
				nazivFajla = "2.PNG";
			else if(polja[i][j].id == 3)
				nazivFajla = "3.PNG";
			else if(polja[i][j].id == 4)
				nazivFajla = "4.PNG";
			else if(polja[i][j].id == 5)
				nazivFajla = "5.PNG";
			else if(polja[i][j].id == 6)
				nazivFajla = "6.PNG";
			else if(polja[i][j].id == 7)
				nazivFajla = "7.PNG";
			else if(polja[i][j].id == 8)
				nazivFajla = "8.PNG";
		}
		else
			nazivFajla = "prazno.PNG";
		
		putanja = "src/mina slike/" + nazivFajla;
		
		ImageIcon imageIcon = new ImageIcon(new ImageIcon(putanja).getImage().getScaledInstance(29, 29, Image.SCALE_DEFAULT));
		return imageIcon;
		//return new ImageIcon(putanja);
	}
	
	Polje otvoreno;
	public void odigrajPotez(int i, int j) {

		otvoreno = polja[i][j];
		
		if(!otvoreno.isVisible)
		{
			
			if(otvoreno.id != -1)
			{
				int a,b;
				a=i;
				b=j;
				
				if( (b-1)>=0 && polja[i][j-1].id == -1 )
					polja[i][j].id = polja[i][j].id +1;
				
				if( (b+1)<10 && polja[i][j+1].id== -1)
					polja[i][j].id = polja[i][j].id +1;
				
				if( (a+1)<10 && (b+1)<10 && polja[i+1][j+1].id== -1)
					polja[i][j].id = polja[i][j].id +1;
				
				if( (a+1)<10 && (b-1)>=0 && polja[i+1][j-1].id== -1)
					polja[i][j].id = polja[i][j].id +1;
				
				if( (a-1)>=0 && (b-1)>=0 &&  polja[i-1][j-1].id== -1)
					polja[i][j].id = polja[i][j].id +1;
				
				if( (a-1)>=0 && (b+1)<10 && polja[i-1][j+1].id== -1)
					polja[i][j].id = polja[i][j].id +1;
				
				if( (a-1)>=0 && polja[i-1][j].id== -1)
					polja[i][j].id = polja[i][j].id +1;
				
				if( (a+1)<10 && polja[i+1][j].id== -1)
					polja[i][j].id = polja[i][j].id +1;
			
			}
			otvoreno.isVisible=true;
		}
	}
	public int daLiJeKraj() {

		if(otvoreno.id == -1) {
			ok=-1;
			return 0;
		}
		
		int k=0;
		for (int i = 0; i < polja.length; i++) {
			for (int j = 0; j < polja.length; j++) {
				if(polja[i][j].isVisible)
					k++;
			}
		}
		
		if(k==90) {
			ok=0;
			return 1;
		}
		else {
			ok=0;
			return -1;
		}
	}
	
	public ImageIcon dajSlikuBum(int i, int j) {
		
		String nazivFajla = "";
		String putanja="";
		
		if(polja[i][j].id == -1)
		{
			nazivFajla = "bum.jpg";
			polja[i][j].isVisible=true;
		}
		else if(polja[i][j].isVisible)
		{
			
			if(polja[i][j].id == 0)
				nazivFajla = "otvoreno.PNG";
			else if(polja[i][j].id == 1)
				nazivFajla = "1.PNG";
			else if(polja[i][j].id == 2)
				nazivFajla = "2.PNG";
			else if(polja[i][j].id == 3)
				nazivFajla = "3.PNG";
			else if(polja[i][j].id == 4)
				nazivFajla = "4.PNG";
			else if(polja[i][j].id == 5)
				nazivFajla = "5.PNG";
			else if(polja[i][j].id == 6)
				nazivFajla = "6.PNG";
			else if(polja[i][j].id == 7)
				nazivFajla = "7.PNG";
			else if(polja[i][j].id == 8)
				nazivFajla = "8.PNG";
		}
		else
			nazivFajla = "prazno.PNG";
		
		putanja = "src/mina slike/" + nazivFajla;
		
		ImageIcon imageIcon = new ImageIcon(new ImageIcon(putanja).getImage().getScaledInstance(29, 29, Image.SCALE_DEFAULT));
		return imageIcon;

		//return new ImageIcon(putanja);
	}

	public ImageIcon dajSlikuGore(){
	
		String nazivFajla = "";
		String putanja="";
		
		if(ok == 0)
			nazivFajla = "smiley.jpg";
		else if(ok == -1)
			nazivFajla="cry.png";
		
		putanja = "src/mina slike/" + nazivFajla;
		
		ImageIcon imageIcon = new ImageIcon(new ImageIcon(putanja).getImage().getScaledInstance(29, 29, Image.SCALE_DEFAULT));
		return imageIcon;
	}
}
