package view;

import model.*;
import observer.Observable;
import observer.Observer;

public class InfoDisplay implements Observer {
	private GameWindow fen = null;
	private Player currentPlayer = new Administrator("Default");
	
	public InfoDisplay(GameWindow fen){
		this.fen = fen;
		fen.getGame().addObserver(this);
		fen.getGame().getInfo().addObserver(this);
	}
	
	public void changeInfoDisplay(String namePlayer, boolean endGame){
		if(!endGame)
			fen.setLabelInfo("C'est le tour de " + namePlayer);
		else
			fen.setLabelInfo("Victoire de " + namePlayer + 
					"  (press Enter)");
	}

	@Override
	public void update(Observable o, Object arg) {
		if(o.getClass() == Info.class){
			changeInfoDisplay(currentPlayer.getName(), true);
		}
		else if(o.getClass() == Game.class){
			currentPlayer = (Player)arg;
			changeInfoDisplay(currentPlayer.getName(), false);
		}
		else
			System.out.println("Il y a un problème");
	}
}
