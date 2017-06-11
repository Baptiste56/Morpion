package model;

import javax.swing.JOptionPane;

public class Main {

	public static void main(String[] args) {
		boolean init = true;
		Game game = null;
		
		if(init){
			String name = JOptionPane.showInputDialog(null, "Pseudo joueur 1",
					"Nouveau Joueur", JOptionPane.QUESTION_MESSAGE);
			Administrator admin = new Administrator(name);
			name = JOptionPane.showInputDialog(null, "Pseudo joueur 2",
					"Nouveau Joueur", JOptionPane.QUESTION_MESSAGE);
			Guest guest = new Guest(name);
			game = new Game(admin, guest);
		}
		else
			game = new Game();
		game.lauchGame();
	}

}
