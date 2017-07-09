package view;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

import model.*;
import observer.*;
import controller.*;

public class GridDisplay implements Observer, ActionListener{
	private Game game;
	private GridController gridController = null;
	private Player playerTurn;
	private ImageIcon cross = new ImageIcon("../image/cross.png");
	private ImageIcon circle = new ImageIcon("../image/circle.jpg");
	
	private JButton square11 = new JButton("");
	private JButton square12 = new JButton("");
	private JButton square13 = new JButton("");
	private JButton square21 = new JButton("");
	private JButton square22 = new JButton("");
	private JButton square23 = new JButton("");
	private JButton square31 = new JButton("");
	private JButton square32 = new JButton("");
	private JButton square33 = new JButton("");
	
	public GridDisplay(JPanel component, Game game){
		this.game = game;
		playerTurn = game.getPlayerTurn();
		game.addObserver(this);
		gridController = game.getGrid().getGridController();
		game.getGrid().addObserver(this);
		component.setLayout(new GridLayout(3,3));
		component.add(square11);
		component.add(square12);
		component.add(square13);
		component.add(square21);
		component.add(square22);
		component.add(square23);
		component.add(square31);
		component.add(square32);
		component.add(square33);
		
		square11.addActionListener(this);
		square12.addActionListener(this);
		square13.addActionListener(this);
		square21.addActionListener(this);
		square22.addActionListener(this);
		square23.addActionListener(this);
		square31.addActionListener(this);
		square32.addActionListener(this);
		square33.addActionListener(this);
	}
	
	public void actionPerformed(ActionEvent arg0){
		if(arg0.getSource() == square11){
			gridController.addSymbol(1);}
		else if(arg0.getSource() == square12){
			gridController.addSymbol(2);}
		else if(arg0.getSource() == square13){
			gridController.addSymbol(3);}
		else if(arg0.getSource() == square21){
			gridController.addSymbol(4);}
		else if(arg0.getSource() == square22){
			gridController.addSymbol(5);}
		else if(arg0.getSource() == square23){
			gridController.addSymbol(6);}
		else if(arg0.getSource() == square31){
			gridController.addSymbol(7);}
		else if(arg0.getSource() == square32){
			gridController.addSymbol(8);}
		else if(arg0.getSource() == square33){
			gridController.addSymbol(9);}
}
	
	private void displayNewSymbol(int s){
		ImageIcon temp = null;
		if(playerTurn.getSymbol() == Symbol.Circle)
			temp = circle;
		else if(playerTurn.getSymbol() == Symbol.Cross)
			temp = cross;
		switch(s) {
			case 0 :
				resetGridDisplay();
				break;
			case 1 :
				if(square11.getIcon() == null)
					square11.setIcon(temp);
				else 
					square11.setEnabled(false);
				break;
			case 2 :
				if(square12.getIcon() == null)
					square12.setIcon(temp);
				else
					square12.setEnabled(false);
				break;
			case 3 :
				if(square13.getIcon() == null)
					square13.setIcon(temp);
				else
					square13.setEnabled(false);
				break;
			case 4 :
				if(square21.getIcon() == null)
					square21.setIcon(temp);
				else
					square21.setEnabled(false);
				break;
			case 5 :
				if(square22.getIcon() == null)
					square22.setIcon(temp);
				else
					square22.setEnabled(false);
				break;
			case 6 :
				if(square23.getIcon() == null)
					square23.setIcon(temp);
				else
					square23.setEnabled(false);
				break;
			case 7 :
				if(square31.getIcon() == null)
					square31.setIcon(temp);
				else
					square31.setEnabled(false);
				break;
			case 8 :
				if(square32.getIcon() == null)
					square32.setIcon(temp);
				else
					square32.setEnabled(false);
				break;
			case 9 :
				if(square33.getIcon() == null)
					square33.setIcon(temp);
				else
					square33.setEnabled(false);
				break;
		}
	}
	
	public void resetGridDisplay(){
		square11.setEnabled(true);
		square11.setIcon(null);
		square12.setEnabled(true);
		square12.setIcon(null);
		square13.setEnabled(true);
		square13.setIcon(null);
		square21.setEnabled(true);
		square21.setIcon(null);
		square22.setEnabled(true);
		square22.setIcon(null);
		square23.setEnabled(true);
		square23.setIcon(null);
		square31.setEnabled(true);
		square31.setIcon(null);
		square32.setEnabled(true);
		square32.setIcon(null);
		square33.setEnabled(true);
		square33.setIcon(null);
	}
	
	public void update(Observable o, Object arg){
		if(o == game)
			playerTurn = (Player)arg;
		else if(o == game.getGrid())
			displayNewSymbol((int)arg);
	}
	
}
