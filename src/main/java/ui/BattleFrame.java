package ui;

import java.awt.Dimension;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Enumeration;
import java.util.Hashtable;

import javax.swing.JFrame;

import logic.Message;
import component.Windows;
import ui.Background;

public class BattleFrame extends JFrame{
	private static final long serialVersionUID = 1L;
	
  public BattleFrame(Hashtable ht,int num,ObjectInputStream input,ObjectOutputStream output) {

    System.out.println("<<<<<--------------------------------------------------------------->>>>>");
    System.out.println("---------------My number= " + num
				+ "-------------------------");
		for (Enumeration ee = ht.elements(); ee.hasMoreElements();) {
			Message test = (Message) ee.nextElement();
			System.out.println("BattleFrame--------------" + test.number
					+ "  type=" + test.type + "  team=" + test.team + " name="
					+ test.UserName + "  ready=" + test.ready + "  exit="
					+ test.exit);
		}

    System.out.println("<<<<<--------------------------------------------------------------->>>>>");
    setTitle("BomberMan");
    this.setResizable(false);
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    //setSize(new Dimension(805, 625));
    setSize(new Dimension(620, 625));
    new Windows(this);
    Background bg=new Background(this,ht,num,input,output);
    this.add(bg);
        
  }
}
