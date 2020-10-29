import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class Minesweeper_GUI extends JFrame implements ActionListener{

	//private static final long serialVersionUID=1L;

	NaseDugme dugmici[][] = new NaseDugme[10][10];
	Minesweeper_engine engine = new Minesweeper_engine();
	JPanel panelHeder = new JPanel();
	JButton dugmeHeder = new JButton();
	
	public Minesweeper_GUI()
	{
		napraviHeder();
		setTabla();
		napraviFuter();
		osveziGui();
		
		setLocation(200,200);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		pack();
		setVisible(true);
	}

	private void napraviHeder() {

		dugmeHeder.setIcon(engine.dajSlikuGore());
		panelHeder.add(dugmeHeder);
		
		getContentPane().add(panelHeder, BorderLayout.NORTH);
	}
	private void osveziHeder() {
		dugmeHeder.setIcon(engine.dajSlikuGore());
	}

	private void setTabla() {
		
		JPanel p = new JPanel();
		
		p.setLayout(new GridLayout(10,10));
		for (int i = 0; i < dugmici.length; i++) {
			for (int j = 0; j < dugmici[0].length; j++) {
				
				dugmici[i][j] = new NaseDugme(i, j);
				dugmici[i][j].setPreferredSize(new Dimension(30,30));
				
				
				dugmici[i][j].addActionListener(this);
				
				p.add(dugmici[i][j]);
			}
		}
		
		getContentPane().add(p, BorderLayout.CENTER);
	}

	private void napraviFuter() {
		JPanel panel = new JPanel();
		JButton novaIgra = new JButton("Nova igra");
		
		novaIgra.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				engine.inicijalizuj();
				
				osveziGui();
				osveziHeder();
				
			}

		});
		
		panel.add(novaIgra);
		
		getContentPane().add(panel, BorderLayout.SOUTH);
	}

	protected void osveziGui() {
		for (int i = 0; i < dugmici.length; i++) {
			for (int j = 0; j < dugmici[0].length; j++) {
				dugmici[i][j].setIcon(engine.dajSliku(i,j));			//da stavlja slike figurica
			}
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		NaseDugme m = (NaseDugme)e.getSource();
		engine.odigrajPotez(m.i,m.j);
		osveziGui();
		
		if(engine.daLiJeKraj() == 1)		
		{
			osveziGuiBum();
			int vrednost = JOptionPane.showConfirmDialog(null, "Zavrsili ste igru! Da li zelite novu?", "Cestitamo!", JOptionPane.YES_NO_OPTION);
			if(vrednost == JOptionPane.YES_OPTION)
			{
				engine.inicijalizuj();
				osveziGui();
				osveziHeder();
			}
			else
			{
				System.exit(0);
			}
		}
		else if(engine.daLiJeKraj() == 0)		
		{
			osveziGuiBum();
			int vrednost = JOptionPane.showConfirmDialog(null, "Izgubili ste! Da li zelite novu igru?", "Zao nam je", JOptionPane.YES_NO_OPTION);
			if(vrednost == JOptionPane.YES_OPTION)
			{
				engine.inicijalizuj();
				osveziGui();
				osveziHeder();
			}
			else
			{
				System.exit(0);
			}
		}
	}

	private void osveziGuiBum() {
		
		osveziHeder();
		for (int i = 0; i < dugmici.length; i++) {
			for (int j = 0; j < dugmici[0].length; j++) {
				dugmici[i][j].setIcon(engine.dajSlikuBum(i,j));
			}
		}
	}
}
