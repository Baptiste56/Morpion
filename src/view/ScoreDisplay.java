package view;

import model.*;
import observer.*;

public class ScoreDisplay implements Observer{
	private GameWindow fen = null;
	private int[] score = {0,0,0}; // 0 : Draw / 1 : PLayer / 2 : Opponent
	
	public ScoreDisplay(GameWindow fen){
		this.fen = fen;
		fen.getGame().getScore().addObserver(this);
	}
	
	public void changeScore(Result result){
		if(result == Result.Administrator){
			score[1] ++;
			fen.setLabelPointPlayer(score[1]);
		}
		else if(result == Result.Draw){
			score[0] ++;
			fen.setLabelPointDraw(score[0]);
			System.out.println("Passage Draw");
		}
		else if(result == Result.Guest){
			score[2] ++;
			fen.setLabelPointOpponent(score[2]);
		}
	}

	@Override
	public void update(Observable o, Object arg) {
		if(o.getClass() == Score.class)
			changeScore((Result)arg);
	}
}
