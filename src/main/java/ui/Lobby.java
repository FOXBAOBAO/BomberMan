package ui;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.TextArea;
import java.awt.TextField;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Enumeration;
import java.util.Hashtable;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import logic.Message;
import logic.MessageControl;
import component.ImageLoading;
import component.ImageButton;
import component.ImageLabel;
import component.ImagePanel;
import ui.BattleFrame;
import util.Key;
import util.Util;

public class Lobby extends JFrame implements MouseListener, MouseMotionListener,Runnable,KeyListener{
	
	String userName;
	int number;
	int team;
	JButton bt1=new JButton();
	JButton bt2=new JButton();
	JPanel readyPanel=new JPanel();
	JPanel statePanel=new JPanel();
	JPanel buttonPanel=new JPanel();
	JPanel charPanel=new JPanel();
	
	TextArea showMsg=new TextArea();
	TextField send=new TextField();

	Socket mysocket;
	ObjectInputStream in;
	ObjectOutputStream out;
	
	Thread thread;
	ImagePanel bgPanel = new ImagePanel(new ImageIcon("images/house2.jpg").getImage());
	ImageButton bule_role;
	ImageButton red_role;
	ImageButton sendImg;
	ImageButton ready;
	ImageButton leave;
	ImageButton player1_b =  new ImageButton(new ImageIcon("images/player1.jpg"));
	ImageButton player1_r =  new ImageButton(new ImageIcon("images/player2.jpg"));
	ImageButton player2_b =  new ImageButton(new ImageIcon("images/player1.jpg"));
	ImageButton player2_r =  new ImageButton(new ImageIcon("images/player2.jpg"));
	ImageButton player3_b =  new ImageButton(new ImageIcon("images/player1.jpg"));
	ImageButton player3_r =  new ImageButton(new ImageIcon("images/player2.jpg"));
	ImageButton player4_b =  new ImageButton(new ImageIcon("images/player1.jpg"));
	ImageButton player4_r =  new ImageButton(new ImageIcon("images/player2.jpg"));
	ImageButton player5_b =  new ImageButton(new ImageIcon("images/player1.jpg"));
	ImageButton player5_r =  new ImageButton(new ImageIcon("images/player2.jpg"));
	ImageButton player6_b =  new ImageButton(new ImageIcon("images/player1.jpg"));
	ImageButton player6_r =  new ImageButton(new ImageIcon("images/player2.jpg"));
	ImageButton player7_b =  new ImageButton(new ImageIcon("images/player1.jpg"));
	ImageButton player7_r =  new ImageButton(new ImageIcon("images/player2.jpg"));
	ImageButton player8_b =  new ImageButton(new ImageIcon("images/player1.jpg"));
	ImageButton player8_r =  new ImageButton(new ImageIcon("images/player2.jpg"));
	Cursor handcur;
	Cursor customcur;

	ImageLabel il;
	Message meMessage=new Message();
	int x;
	int y;
	int inf_x = 563;
	int inf_y = 360;
	boolean Ready =false;
	

	Hashtable htPlayer=null;
	
//	public Lobby(){
//		init();
//		this.addMouseMotionListener(this);
//		this.addMouseListener(this);
//		this.setSize(805,620);
//		this.setVisible(true);
//	}

	public void init(){
		this.setResizable(false);
		this.getContentPane().add(bgPanel);
		bgPanel.addMouseListener(this);
		bgPanel.addMouseMotionListener(this);
		
		bule_role = new ImageButton(new ImageIcon("images/team1.jpg"));
		bule_role.setRolloverIcon(ImageLoading.createImageIcon("images/team12.jpg"));
		bule_role.setSelectedIcon(ImageLoading.createImageIcon("images/team12.jpg"));
		bule_role.setDisabledIcon(ImageLoading.createImageIcon("images/a2.png"));
		bule_role.setLocation(526,185);
		bule_role.setSelected(true);
		bule_role.addMouseListener(this);
		bule_role.addMouseMotionListener(this);
		bgPanel.add(bule_role);

		red_role = new ImageButton(new ImageIcon("images/team2.jpg"));
		red_role.setRolloverIcon(ImageLoading.createImageIcon("images/team22.jpg"));
		red_role.setRolloverSelectedIcon(ImageLoading.createImageIcon("images/team22.jpg"));
		red_role.setSelectedIcon(ImageLoading.createImageIcon("images/team22.jpg"));
		red_role.setLocation(653,183);
		red_role.setSelected(true);
		red_role.addMouseListener(this);
		red_role.addMouseMotionListener(this);
		bgPanel.add(red_role);
		

		sendImg = new ImageButton(new ImageIcon("images/send.jpg"));
		sendImg.setRolloverIcon(ImageLoading.createImageIcon("images/send2.jpg"));
		sendImg.setLocation(408,528);
		sendImg.addMouseListener(this);
		sendImg.addMouseMotionListener(this);
		bgPanel.add(sendImg);
		
		
		ready = new ImageButton(new ImageIcon("images/ready.jpg"));
		ready.setRolloverIcon(ImageLoading.createImageIcon("images/ready2.jpg"));
		ready.setSelectedIcon(ImageLoading.createImageIcon("images/ready_ok.jpg"));
		ready.setLocation(572,538);
		ready.addMouseListener(this);
		ready.addMouseMotionListener(this);
		bgPanel.add(ready);
		

		leave = new ImageButton(new ImageIcon("images/leave.jpg"));
		leave.setRolloverIcon(ImageLoading.createImageIcon("images/leave2.jpg"));
		leave.setLocation(655,538);
		leave.addMouseListener(this);
		leave.addMouseMotionListener(this);
		bgPanel.add(leave);

		
		this.player1_b.setSelectedIcon(ImageLoading.createImageIcon("images/player_b_ready.jpg"));
		this.player2_b.setSelectedIcon(ImageLoading.createImageIcon("images/player_b_ready.jpg"));
		this.player3_b.setSelectedIcon(ImageLoading.createImageIcon("images/player_b_ready.jpg"));
		this.player4_b.setSelectedIcon(ImageLoading.createImageIcon("images/player_b_ready.jpg"));
		this.player5_b.setSelectedIcon(ImageLoading.createImageIcon("images/player_b_ready.jpg"));
		this.player6_b.setSelectedIcon(ImageLoading.createImageIcon("images/player_b_ready.jpg"));
		this.player7_b.setSelectedIcon(ImageLoading.createImageIcon("images/player_b_ready.jpg"));
		this.player8_b.setSelectedIcon(ImageLoading.createImageIcon("images/player_b_ready.jpg"));
		
		this.player1_r.setSelectedIcon(ImageLoading.createImageIcon("images/player_r_ready.jpg"));
		this.player2_r.setSelectedIcon(ImageLoading.createImageIcon("images/player_r_ready.jpg"));
		this.player3_r.setSelectedIcon(ImageLoading.createImageIcon("images/player_r_ready.jpg"));
		this.player4_r.setSelectedIcon(ImageLoading.createImageIcon("images/player_r_ready.jpg"));
		this.player5_r.setSelectedIcon(ImageLoading.createImageIcon("images/player_r_ready.jpg"));
		this.player6_r.setSelectedIcon(ImageLoading.createImageIcon("images/player_r_ready.jpg"));
		this.player7_r.setSelectedIcon(ImageLoading.createImageIcon("images/player_r_ready.jpg"));
		this.player8_r.setSelectedIcon(ImageLoading.createImageIcon("images/player_r_ready.jpg"));

		showMsg.setBounds(30,370,455,138);
		//showMsg.setBackground(Color.blue);
		showMsg.setBackground(new Color(120,225,247));
		showMsg.setEditable(false);
		//showMsg.setAutoscrolls(true);
		bgPanel.add(showMsg);
		
		send.addKeyListener(this);
		send.setBounds(102,530,275,23);
		bgPanel.add(send);
		
		handcur = Toolkit.getDefaultToolkit().createCustomCursor(ImageLoading.createImage("images/hand.png"),new Point(10,10),"hand"); 
		customcur = Toolkit.getDefaultToolkit().createCustomCursor(ImageLoading.createImage("images/custom.png"),new Point(10,10),"custom"); 
		this.setCursor(handcur);

	}
	
  public Lobby(Message ms,ObjectInputStream in,ObjectOutputStream out){

    htPlayer = new Hashtable();
    this.number=ms.number;
    this.userName=ms.UserName;
    this.team=ms.team;
    this.meMessage=ms;
    Util.printMessage("Tank House",this.meMessage);
    this.in=in;
    this.out=out;
    this.setTitle("User Name: "+userName);

    init();
		
		if(this.meMessage.team==Key.TEAM_BLUE){
			this.bule_role.setSelected(true);
			this.red_role.setSelected(false);
		}else {
			this.bule_role.setSelected(false);
			this.red_role.setSelected(true);
    }
		
    this.setSize(805,630);
    this.setVisible(true);
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
    thread=new Thread(this);
    thread.start();
  }
	
  public Lobby(Hashtable ht,Message ms,ObjectInputStream in,ObjectOutputStream out){
    htPlayer = new Hashtable();
				
		if(!htPlayer.isEmpty()){
			this.htPlayer.clear();
		}		
    this.htPlayer = ht;
		
		
    this.number=ms.number;
    this.userName=ms.UserName;
    this.team=ms.team;
    this.meMessage=ms;
    Util.printMessage("Tank House",this.meMessage);
    this.in=in;
    this.out=out;
    this.setTitle("Player:"+userName);

    init();
		
		if(this.meMessage.team==Key.TEAM_BLUE){
			this.bule_role.setSelected(true);
			this.red_role.setSelected(false);
		}else {
			this.bule_role.setSelected(false);
			this.red_role.setSelected(true);
    }
		
    this.setSize(805,630);
    this.setVisible(true);
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
    thread=new Thread(this);
    thread.start();
  }
	
//	public void start(){
	//	thread.start();
	//}
	public void run(){
		System.out.println("run Start");
//		
		while(true){
			//bgPanel.repaint();
			this.paint();
			this.repaint();
			//this.setPlayerLocation(1, -1, "");
			try{
				Message msg=new Message();
				msg=MessageControl.receiveMessage(in);
				
				doMessage(msg);				
				this.paint();
				this.repaint();
				//Thread.sleep(1000);
				if(this.Ready){
					BattleFrame qq =new BattleFrame(htPlayer,this.number,in,out);
					qq.setVisible(true);
					
					Message mm=new Message();
					mm =msg;
					mm.type=44;
					//bg.sendMessage(mm);
					MessageControl.sendMessage(mm,out);
					htPlayer.clear();
					Ready = false;
					this.dispose();
					break;
//					Message msgtest=new Message();
//					msg.type=44;
//					bg.sendMessage(msgtest);
					
					
				}
			}
      catch(Exception e){}
		}
		
		
	}
	
	public void paint(){
		//int t_y = inf_y;
		this.removeAllPlayer();
		for(Enumeration e=htPlayer.elements();e.hasMoreElements();){
			Message msg =(Message)e.nextElement();
			showPlayer(msg);
			
		}
		
	}
	public void removeAllPlayer(){
		this.bgPanel.remove(this.player1_b);
		this.bgPanel.remove(this.player2_b);
		this.bgPanel.remove(this.player3_b);
		this.bgPanel.remove(this.player4_b);
		this.bgPanel.remove(this.player5_b);
		this.bgPanel.remove(this.player6_b);
		this.bgPanel.remove(this.player7_b);
		this.bgPanel.remove(this.player8_b);
		this.bgPanel.remove(this.player1_r);
		this.bgPanel.remove(this.player2_r);
		this.bgPanel.remove(this.player3_r);
		this.bgPanel.remove(this.player4_r);
		this.bgPanel.remove(this.player5_r);
		this.bgPanel.remove(this.player6_r);
		this.bgPanel.remove(this.player7_r);
		this.bgPanel.remove(this.player8_r);
	}
	public void showPlayer(Message msg){
		int playerNum=msg.number;
		int playerTeam=msg.team;
		String playerName=msg.UserName;
		setPlayerLocation(playerNum,playerTeam,playerName,msg.ready);
	}
	public void showPlayerInf(Graphics g,Message msg,int X,int Y){
		int playerNum=msg.number;
		int playerTeam=msg.team;
		String playerName=msg.UserName;
		g.setColor(Color.yellow);
		g.drawString("Num="+playerNum+"   Name="+playerName+"  Team="+playerTeam,X,Y);
		g.drawLine(X,Y+5,X+180,Y+5);
		
		
	}
	public void setPlayerLocation(int num,int team,String palyerName,int ready){
		int x =0;
		int y =0;
		switch (num){
      case 1: {
        x=31;y=81;
				if(team==1){
					this.bgPanel.add(player1_b);
					player1_b.setLocation(x, y);
					this.bgPanel.add(player1_b);
					if(ready == 1){
						this.player1_b.setSelected(true);
					}else{
						this.player1_b.setSelected(false);
          }
					
				}
				if(team==-1){
					this.bgPanel.add(player1_r);
					player1_r.setLocation(x, y);
					this.bgPanel.add(player1_r);
					if(ready == 1){
						this.player1_r.setSelected(true);
					}else{
						this.player1_r.setSelected(false);
          }
				}
      }break;
      case 2: {
        x=152;y=81;
				if(team==1){
					this.bgPanel.add(player2_b);
					player2_b.setLocation(x, y);
					this.bgPanel.add(player2_b);
					if(ready == 1){
						this.player2_b.setSelected(true);
					}else{
						this.player2_b.setSelected(false);
          }
				}
				if(team==-1){
					this.bgPanel.add(player2_r);
					player2_r.setLocation(x, y);
					this.bgPanel.add(player2_r);
					if(ready == 1){
						this.player2_r.setSelected(true);
					}else{
						this.player2_r.setSelected(false);
          }
				}
      }break;
      case 3: {
        x=274;y=81;
				if(team==1){
					player3_b.setLocation(x, y);
					this.bgPanel.add(player3_b);
					if(ready == 1){
						this.player3_b.setSelected(true);
					}else{
						this.player3_b.setSelected(false);
          }
				}
				if(team==-1){
					player3_r.setLocation(x, y);
					this.bgPanel.add(player3_r);
					if(ready == 1){
						this.player3_r.setSelected(true);
					}else{
						this.player3_r.setSelected(false);
          }
				}
      }break;
      case 4: {
        x=397;y=81;
				if(team==1){
					player4_b.setLocation(x, y);
					this.bgPanel.add(player4_b);
					if(ready == 1){
						this.player4_b.setSelected(true);
					}else{
						this.player4_b.setSelected(false);
          }
				}
				if(team==-1){
					player4_r.setLocation(x, y);
					this.bgPanel.add(player4_r);
					if(ready == 1){
						this.player4_r.setSelected(true);
					}else{
						this.player4_r.setSelected(false);
					}
				}
      }break;
      case 5: {
        x=31; y=234;
				if(team==1){
					player5_b.setLocation(x, y);
					this.bgPanel.add(player5_b);
					if(ready == 1){
						this.player5_b.setSelected(true);
					}else{
						this.player5_b.setSelected(false);
					}
				}
				if(team==-1){
					player5_r.setLocation(x, y);
					this.bgPanel.add(player5_r);
					if(ready == 1){
						this.player5_r.setSelected(true);
					}else{
						this.player5_r.setSelected(false);
          }
				}
      }break;
      case 6: {
        x=152; y=234;
				if(team==1){
					player6_b.setLocation(x, y);
					this.bgPanel.add(player6_b);
					if(ready == 1){
						this.player6_b.setSelected(true);
					}else{
						this.player6_b.setSelected(false);
					}
				}
				if(team==-1){
					player6_r.setLocation(x, y);
					this.bgPanel.add(player6_r);
					if(ready == 1){
						this.player6_r.setSelected(true);
					}else{
						this.player6_r.setSelected(false);
					}
				}
      }break;
      case 7: {
        x=274; y=234;
				if(team==1){
					player7_b.setLocation(x, y);
					this.bgPanel.add(player7_b);
					if(ready == 1){
						this.player7_b.setSelected(true);
					}else{
						this.player7_b.setSelected(false);
					}
				}
				if(team==-1){
					player7_r.setLocation(x, y);
					this.bgPanel.add(player7_r);
					if(ready == 1){
						this.player7_r.setSelected(true);
					}else{
						this.player7_r.setSelected(false);
					}
				}
      }break;
      case 8: {
        x=397; y=234;
				if(team==1){
					player8_b.setLocation(x, y);
					this.bgPanel.add(player8_b);
					if(ready == 1){
						this.player8_b.setSelected(true);
					}else{
						this.player8_b.setSelected(false);
					}
				}
				if(team==-1){
					player8_r.setLocation(x, y);
					this.bgPanel.add(player8_r);
					if(ready == 1){
						this.player8_r.setSelected(true);
					}else{
						this.player8_r.setSelected(false);
					}
				}
      }break;
		}
		
	
		
		
	}

	public void doMessage(Message msg){
		
		int type=msg.type;

		Util.printMessage("TankHost DoMessage ",msg);
		
   		switch(type){

   			//change group
      case 5:{
   				for(Enumeration e=htPlayer.elements();e.hasMoreElements();){
					Message test =(Message)e.nextElement();
   					System.out.println("^^^^^^^^^case 5 team="+test.team);
					if(test.number==msg.number){
						test.team=msg.team;
						htPlayer.put(test.number,test);
						Util.printMessage("Put Message Ht",test);
					}
				}
				//this.bgPanel.repaint();
        this.paint();			
        this.repaint();
   				
      }
   			break;
   			//login
      case 3:{
   				
        int s=0;
   				
   				for(Enumeration e=htPlayer.elements();e.hasMoreElements();){
					Message test =(Message)e.nextElement();
					if(test.number==msg.number){
						s=1;
					}
					
			
				}
				if(s==0){
					htPlayer.put(msg.number,msg);
				}

				//this.bgPanel.repaint();
        this.paint();
        this.repaint();
   				
      }
   			break;
   			//处理聊天消息
      case 2:{   				
        this.showMsg.append(msg.msg+"\n");   				
      }break;
      case 31:{
   			//	htPlayer.put(Integer.toString(msg.number),msg);
   				//allMessage=msg;
   				
   			//	for(int i=0;i<msg.PlayersInf.length;i++){
   			//		String playerInf=msg.PlayersInf[i];
   			//		playerNum=playerInf.substring(0,1);
   			//		playerName=playerInf.substring(1);
   					
   					
   			//	}
   			
      }break;
   			
   			
      case 10:{
   				for(Enumeration e=htPlayer.elements();e.hasMoreElements();){
					Message test =(Message)e.nextElement();
					
					if(test.number==msg.number){
						test.ready=msg.ready;
						htPlayer.put(test.number,test);
					}
				}
   				
        int b=0;
				for(Enumeration e=htPlayer.elements();e.hasMoreElements();){
					Message test =(Message)e.nextElement();
					if(test.ready!=1){
						b++;
					}
				}
				if(b==0){
					this.Ready = true;
				}
				//this.bgPanel.repaint();
        this.paint();
        this.repaint();
      }break;
   			
      case 11:{
   				
   				for(Enumeration e=htPlayer.elements();e.hasMoreElements();){
					Message test =(Message)e.nextElement();
					if(test.number==msg.number){
						//int i=test.number;
						//test.type=msg.type;
						test.exit=msg.exit;
						//test.team=msg.team;
						htPlayer.remove(test.number);
						System.out.println("^^^^^^^^^case 11  number"+test.number+"  type="+test.type+"  team="+test.team);
					}
				}
				//EXIT=1;
        System.out.println("********************EXIT");

				//this.bgPanel.repaint();
        this.paint();
        this.repaint();
      }break;
   		}
	}
	
	public ImageButton getMyPlayerIcon(int num,int team){
		if(team == 1){
			switch(num){
        case 1:return this.player1_b;
        case 2:return this.player2_b;
        case 3:return this.player3_b;
        case 4:return this.player4_b;
        case 5:return this.player5_b;
        case 6:return this.player6_b;
        case 7:return this.player7_b;
        case 8:return this.player8_b;
			}
		}else if(team == -1){
			switch(num){
        case 1:return this.player1_r;
        case 2:return this.player2_r;
        case 3:return this.player3_r;
        case 4:return this.player4_r;
        case 5:return this.player5_r;
        case 6:return this.player6_r;
        case 7:return this.player7_r;
        case 8:return this.player8_r;
			}
		}

		return null;
	}
	public void mouseMoved(MouseEvent e){
		
		if(e.getSource() == this.bule_role 
				|| e.getSource() == this.red_role
				|| e.getSource() == this.sendImg
				|| e.getSource() == this.ready
				|| e.getSource() == this.leave){
			this.setCursor(handcur);
			return;
		}

		this.setCursor(customcur);
	}
	public void mouseDragged(MouseEvent arg0) {
		
	}

	public void mouseClicked(MouseEvent e){

		//choose group
		if(e.getSource() == this.bule_role){

			if(!this.bule_role.isSelected()){
				Message test=new Message();
				test.team=Key.TEAM_BLUE;
				test.type=Key.MESSAGE_TYPE_CHANGE_TEAM;
				test.number=meMessage.number;
				test.UserName=meMessage.UserName;
				team=Key.TEAM_BLUE;
				meMessage.team=Key.TEAM_BLUE;
				
				meMessage.setTeam(Key.TEAM_BLUE);
				meMessage.setType(Key.MESSAGE_TYPE_CHANGE_TEAM);
				MessageControl.sendMessage(test,out);
				Util.printMessage("Select Team ",test);
				this.bule_role.setSelected(true);
				this.red_role.setSelected(false);
			}
			return;
		}else if(e.getSource() == this.red_role){
			if(!this.red_role.isSelected()){
				Message test=new Message();
				test.team=Key.TEAM_RED;
				//test.type=5;
				test.type=Key.MESSAGE_TYPE_CHANGE_TEAM;
				test.number=meMessage.number;
				test.UserName=meMessage.UserName;				
				team=Key.TEAM_RED;
				meMessage.team=Key.TEAM_RED;
				
				meMessage.setTeam(Key.TEAM_RED);
				meMessage.setType(Key.MESSAGE_TYPE_CHANGE_TEAM);
				MessageControl.sendMessage(test,out);
	
				Util.printMessage("Select Team ",test);
				this.red_role.setSelected(true);
				this.bule_role.setSelected(false);
			}
			return;
		}

		//chat
		else if(e.getSource() == this.sendImg){
			String s=send.getText();
			send.setText("");
			Message msg =new Message();
			msg.type=Key.MESSAGE_TYPE_CHART;
			msg.msg=userName+" --> "+s;
			MessageControl.sendMessage(msg,out);
		}
		//ready
		else if(e.getSource() == this.ready){
			
			
			for (Enumeration ee = htPlayer.elements(); ee.hasMoreElements();) {
				Message test = (Message) ee.nextElement();
				if(test.ready!=1){
					
				}
			}
			if(this.ready.isSelected()){

				Message msg= new Message();
				
				msg.type=10;
				msg.number=meMessage.number;
				msg.ready=-1;
				msg.UserName=meMessage.UserName;
				meMessage.setType(Key.MESSAGE_TYPE_READY);
				meMessage.setReady(-1);
				Util.printMessage("Get Ready ",msg);
				MessageControl.sendMessage(msg,out);
				this.ready.setSelected(false);
			}else {

				Message msg= new Message();
				
				msg.type=10;
				msg.number=meMessage.number;
				msg.ready=1;
				msg.UserName=meMessage.UserName;
				meMessage.setType(Key.MESSAGE_TYPE_READY);
				meMessage.setReady(1);
				Util.printMessage("Get Ready ",msg);
				MessageControl.sendMessage(msg,out);
				this.ready.setSelected(true);
      }
		}
		//exit
		if(e.getSource() == this.leave){
			Message msg=new Message();
			msg.type=Key.MESSAGE_TYPE_EXIT;
			msg.number=meMessage.number;
			msg.exit=meMessage.exit;
			MessageControl.sendMessage(msg,out);
			System.exit(0);
		}
		
	}
	public void mouseEntered(MouseEvent arg0) {
		
	}
	public void mouseExited(MouseEvent arg0) {
		
	}
	public void mousePressed(MouseEvent e) {
		
	}
	public void mouseReleased(MouseEvent arg0) {
		
	}
	public void keyPressed(KeyEvent e) {
		if(e.getKeyCode()==KeyEvent.VK_ENTER){
			String s=send.getText();
			send.setText("");
			
			Message msg =new Message();
			msg.type=Key.MESSAGE_TYPE_CHART;
			msg.msg=userName+" --> "+s;
			MessageControl.sendMessage(msg,out);
		}
		
	}
	public void keyReleased(KeyEvent arg0) {
		
	}
	public void keyTyped(KeyEvent arg0) {
		
	}
	
}

