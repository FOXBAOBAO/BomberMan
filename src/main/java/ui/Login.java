package ui;

import java.awt.Cursor;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Random;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.Border;

import logic.Message;
import logic.MessageControl;
import component.Windows;
import component.ImageLoading;
import component.ImageButton;
import component.ImagePanel;
import ui.Lobby;
import util.Key;

public class Login extends JFrame implements  ActionListener , MouseMotionListener{
	
	ImagePanel panel = new ImagePanel(new ImageIcon("images/textBg.png").getImage());
	
	
	private JTextField user;
	private JPasswordField password;
	
	ImageButton login_bt;
	ImageButton exit_bt ;
	Cursor handcur,customcur;
	
	String userName;
	String psw;
	int number;
	
	Message msg=new Message();
	Socket mysocket;
	ObjectInputStream in;
	ObjectOutputStream out;
	
	
	public  Login(){
		init();
		this.pack();
	}
		
	
	
	public void init(){
		this.setTitle("Login");
		this.getContentPane().add(panel);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		login_bt = new ImageButton(new ImageIcon("images/login_bt1.png"));
		login_bt.setPressedIcon(ImageLoading.createImageIcon("images/login_bt.png"));
		login_bt.setRolloverIcon(ImageLoading.createImageIcon("images/login_bt.png"));
		login_bt.setLocation(210,153);
		login_bt.addActionListener(this);
		login_bt.addMouseMotionListener(this);
		panel.add(login_bt);
		
		exit_bt = new ImageButton(new ImageIcon("images/exit_bt1.png"));
		exit_bt.setPressedIcon(ImageLoading.createImageIcon("images/exit_bt2.png"));
		exit_bt.setRolloverIcon(ImageLoading.createImageIcon("images/exit_bt2.png"));
		exit_bt.setLocation(277,162);
		exit_bt.addActionListener(this);
		exit_bt.addMouseMotionListener(this);
		panel.add(exit_bt);
		
		handcur = Toolkit.getDefaultToolkit().createCustomCursor(ImageLoading.createImage("images/hand.png"),new Point(10,10),"hand") ; 
		customcur = Toolkit.getDefaultToolkit().createCustomCursor(ImageLoading.createImage("images/custom.png"),new Point(10,10),"custom") ; 
		this.setCursor(customcur);
		
		user = new JTextField();
		Border border = BorderFactory.createEmptyBorder(1, 0,0,0);
		user.setBorder(border);
		user.setLocation(105, 83);
		user.setSize(115,20);
		panel.add(user);
		
		password = new JPasswordField();
		password.setBorder(border);
		password.setLocation(105, 113);
		password.setSize(115,20);
		password.setEchoChar('*');
		panel.add(password);
		
		this.addMouseMotionListener(this);
	}
	
	public void connect(){
		try{
			mysocket=new Socket(Key.SERVER_IP,Key.PORT);
			in=new ObjectInputStream(mysocket.getInputStream());
			out=new ObjectOutputStream(mysocket.getOutputStream());
			
		}
		catch(IOException e){
			System.out.println("error");
		}

	}
	
	public void doMessage(Message msg){
		int type=msg.type;
		switch(type){
			case 3:{
				number=msg.number;
			}
		}
	}

	public void actionPerformed(ActionEvent e){
		if(e.getSource()==exit_bt){
			System.exit(0);
		}
		if(e.getSource()== login_bt){
			int team;
			Random random = new Random();
			team=random.nextInt(2);
			if(team==0){
				team=-1;
			}
			
			userName=user.getText();
			psw = String.valueOf( password.getPassword()); 
			System.out.println("Password: "+psw); 
			//psw=password.getText();
			
			msg.UserName=userName;
			msg.PassWord=psw;
			msg.team=team;
			msg.type=Key.MESSAGE_TYPE_LOGIN;
			connect();
			
			MessageControl.sendMessage(msg,out);
			doMessage(MessageControl.receiveMessage(in));
			System.out.println("num="+number);
			msg.number=number;
			this.dispose();
			
			new Windows(new Lobby(msg,in,out));
		
		} 
	}

	int o_x = 0;
	int o_y = 0 ;
	public void mouseDragged(MouseEvent e) {
		this.setLocation(this.getX()-o_x+e.getX(),this.getY()-o_y+e.getY());
		
	}
	public void mouseMoved(MouseEvent e) {
		 o_x = e.getX();
		 o_y = e.getY();
		 if(e.getSource() == this.login_bt || e.getSource() == this.exit_bt){
			 this.setCursor(this.handcur);
		 }else{
			 this.setCursor(this.customcur);
		 }
		
	}



}
