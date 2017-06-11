package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import model.*;

public class GameWindow extends JFrame {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Game game;
	private GridDisplay grid = null;
	private JLabel labelInfo = new JLabel("",JLabel.CENTER);
	private JLabel labelNamePlayer = new JLabel("",JLabel.CENTER);
	private JLabel labelNameOpponent = new JLabel("",JLabel.CENTER);
	private JPanel labelSymbolPlayer = null;
	private JPanel labelSymbolOpponent = null;
	private JLabel labelPointPlayer = new JLabel("",JLabel.CENTER);
	private JLabel labelPointOpponent = new JLabel("",JLabel.CENTER);
	private JLabel labelPointDraw = new JLabel("",JLabel.CENTER);
	
	public GameWindow(Game game){
		this.game = game;
		new InfoDisplay(this);
		new ScoreDisplay(this);
		this.addKeyListener(new KeyboardListener(this));
		//On met un titre à la fenêtre. 
		this.setTitle("MortPion");
		//On définie la taille et on empêche le redimensionnement.
	    this.setSize(400, 600);
	    this.setResizable(false);
	    //On la place au milieu.
	    this.setLocationRelativeTo(null);
	    //On active le bouton quit.
	    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    
	    initComponent();
	    
	    //On affiche la fenêtre.
	    this.setVisible(true);
	   
	}
	
	private void initComponent(){
		//Le conteneur principal
	    JPanel content = new JPanel();
	    content.setPreferredSize(new Dimension(400, 600));
	    
	    //On définit le layout manager
	    content.setLayout(new GridBagLayout());
	    
	    //On définit les composants
	    labelInfo.setText("C'est le tour de " + game.getPlayerTurn().getName());
	    labelInfo.setPreferredSize(new Dimension(400, 70));		
	    JPanel cell2 = new JPanel();
	    cell2.setPreferredSize(new Dimension(340, 300));
	    grid = new GridDisplay(cell2, game);
	    
	    //L'objet servant à positionner les composants
	    GridBagConstraints gbc = new GridBagConstraints();
	    
	    gbc.weightx = 1;
	    gbc.weighty = 1;
	    
	    //On positionne la case de départ du composant
	    gbc.gridx = 0;
	    gbc.gridy = 0;
	    //La taille en hauteur et en largeur
	    gbc.gridheight = 1;
	    gbc.gridwidth = 1;
	    //Ligne qui indique une fin de ligne.
	    gbc.gridwidth = GridBagConstraints.REMAINDER;
	    content.add(labelInfo, gbc);
	    //---------------------------------------------
	    gbc.gridx = 0;
	    gbc.gridy = 1;
	    gbc.gridwidth = 1;
	    gbc.gridheight = 3;
	    //Ligne qui indique que la cellule se réplique de façon verticale
	    gbc.fill = GridBagConstraints.VERTICAL;
	    gbc.gridwidth = GridBagConstraints.REMAINDER;
	    content.add(putBorders(30,30,0,20,cell2), gbc);
	    //---------------------------------------------
	    gbc.gridx = 0;
	    gbc.gridy = 4;
	    gbc.gridwidth = 1;
	    gbc.gridheight = 2;
	    gbc.fill = GridBagConstraints.VERTICAL;
	    gbc.gridwidth = GridBagConstraints.REMAINDER;
	    content.add(putBorders(0,0,0,20,initCell()), gbc);
	    //---------------------------------------------
	    
	    this.setContentPane(content);
	}

	private JPanel initCell(){
		//Le conteneur principal
	    JPanel content = new JPanel();
	    content.setPreferredSize(new Dimension(400, 160));
	    
	    //On définit le layout manager
	    content.setLayout(new GridBagLayout());
	    
	    //On définit les composants
	    //------JOUEUR------
	    JPanel cell1 = new JPanel();
	    cell1.setPreferredSize(new Dimension(160,160));
	    cell1.setLayout(new GridLayout(4,1));
	    labelNamePlayer.setText(game.getAdmin().getName());
	    labelNamePlayer.setBorder(BorderFactory.createLineBorder(Color.BLACK));
	    cell1.add(labelNamePlayer);
	    labelSymbolPlayer = new Cross();
	    cell1.add(putBorders(65,65,10,0,labelSymbolPlayer));
	    cell1.add(new JLabel("Victoire", JLabel.CENTER));
	    labelPointPlayer.setText("0");
	    cell1.add(putBorders(55,55,0,0,labelPointPlayer, true));
	    
	    //------VS------
	    JPanel cell2 = new JPanel();
	    cell2.setPreferredSize(new Dimension(50, 160));
	    cell2.setLayout(new GridLayout(4,1));
	    cell2.add(new JLabel("VS", JLabel.CENTER));
	    cell2.add(new JLabel(""));
	    cell2.add(new JLabel("Nul", JLabel.CENTER));
	    labelPointDraw.setText("0");
	    labelPointDraw.setBorder(BorderFactory.createLineBorder(Color.BLACK));
	    cell2.add(labelPointDraw);
	    
	    //------ADVERSAIRE-----
	    JPanel cell3 = new JPanel();
	    cell3.setPreferredSize(new Dimension(160, 160));
		cell3.setLayout(new GridLayout(4,1));
	    labelNameOpponent.setText(game.getGuest().getName());
	    labelNameOpponent.setBorder(BorderFactory.createLineBorder(Color.BLACK));
	    cell3.add(labelNameOpponent);
	    labelSymbolOpponent = new Circle();
	    cell3.add(putBorders(65,65,10,0,labelSymbolOpponent));
	    cell3.add(new JLabel("Defaite", JLabel.CENTER));
	    labelPointOpponent.setText("0");
	    cell3.add(putBorders(55,55,0,0,labelPointOpponent, true));
	    
	    //L'objet servant à positionner les composants
	    GridBagConstraints gbc = new GridBagConstraints();
	    
	    gbc.gridx = 0;
	    gbc.gridy = 0;
	    gbc.gridheight = 1;
	    gbc.gridwidth = 2;
	    gbc.fill = GridBagConstraints.HORIZONTAL;
	    gbc.gridheight = GridBagConstraints.REMAINDER;
	    content.add(cell1, gbc);
	    //---------------------------------------------
	    gbc.gridx = 2;
	    gbc.gridy = 0;
	    gbc.gridwidth = 1;
	    gbc.gridheight = 1;
	    gbc.gridheight = GridBagConstraints.REMAINDER;
	    content.add(cell2, gbc);
	    //---------------------------------------------
	    gbc.gridx = 3;
	    gbc.gridy = 0;
	    gbc.gridwidth = 2;
	    gbc.gridheight = 1;
	    gbc.fill = GridBagConstraints.HORIZONTAL;
	    gbc.gridwidth = GridBagConstraints.REMAINDER;
	    gbc.gridheight = GridBagConstraints.REMAINDER;
	    content.add(cell3, gbc);
	    //---------------------------------------------
	    
	    return content;
	}

	private JComponent putBorders(int w, int e, int n, int s, JComponent comp){
		JComponent resComp = new JPanel();
		resComp.setLayout(new BorderLayout());
		resComp.add(Box.createRigidArea(new Dimension(w,0)), BorderLayout.WEST);
		resComp.add(Box.createRigidArea(new Dimension(e,0)), BorderLayout.EAST);
		resComp.add(Box.createRigidArea(new Dimension(0,n)), BorderLayout.NORTH);
		resComp.add(Box.createRigidArea(new Dimension(0,s)), BorderLayout.SOUTH);
		resComp.add(comp, BorderLayout.CENTER);		
		
		return resComp;
	}
	
	private JComponent putBorders(int w, int e, int n, int s, JComponent comp, boolean line){
		JComponent resComp = new JPanel();
		resComp.setLayout(new BorderLayout());
		resComp.add(Box.createRigidArea(new Dimension(w,0)), BorderLayout.WEST);
		resComp.add(Box.createRigidArea(new Dimension(e,0)), BorderLayout.EAST);
		resComp.add(Box.createRigidArea(new Dimension(0,n)), BorderLayout.NORTH);
		resComp.add(Box.createRigidArea(new Dimension(0,s)), BorderLayout.SOUTH);
		if(line)
			comp.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		resComp.add(comp, BorderLayout.CENTER);		
		
		return resComp;
	}
	
	public GridDisplay getGridDisplay(){
		return grid;
	}
	
	public void setLabelInfo(String message){
		labelInfo.setText(message);
	}
	
	public void setLabelPointPlayer(int point){
		labelPointPlayer.setText(Integer.toString(point));
	}
	
	public void setLabelPointOpponent(int point){
		labelPointOpponent.setText(Integer.toString(point));
	}
	
	public void setLabelPointDraw(int point){
		labelPointDraw.setText(Integer.toString(point));
	}
	
	public Game getGame(){
		return game;
	}
	
	public class Cross extends JPanel { 
		  /**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		public void paintComponent(Graphics g){
		    g.drawLine(0, 0, this.getWidth(), this.getHeight());
		    g.drawLine(0, this.getHeight(), this.getWidth(), 0);
		  }               
		}
	
	public class Circle extends JPanel { 
		  /**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		public void paintComponent(Graphics g){                                     
		    g.drawOval(0, 0, this.getWidth(), this.getHeight());
		  }       
		}

}