package game;

import javax.swing.ImageIcon;
import javax.swing.JFrame;


import component.Windows;
import component.ImagePanel;
import ui.Login;
import util.Util;

public class Game extends JFrame{
	
	public Game(){
		//this.add(panel);
		//panel.setDoubleBuffered(true);
		//panel.setIgnoreRepaint(true);
		this.setUndecorated(true);
		this.pack();
		this.setVisible(true);
		new Windows(this);
	} 
	public static  void main(String[] args){
		Game game =new Game();
		Util.showWindow(game);
		try {
			Thread.sleep(5);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		Util.closeWindow(game);
       Login frame = new Login();
       
       new Windows(frame);
       frame.setVisible(true);
       game.setVisible(false);
	       
	}
}
