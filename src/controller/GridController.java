package controller;

import model.*;

public class GridController {
	private Grid grid = null;
	private int tabGrid [] = new int[9];
	
	public GridController(Grid grid){
		this.grid = grid;
		tabGrid = grid.getTabGrid();
	}
	
	public void addSymbol(int s){
		if(tabGrid[s-1] == 0)
			grid.addSymbol(s);
	}
}
