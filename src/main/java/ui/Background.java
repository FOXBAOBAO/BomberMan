package ui;

import java.awt.*;
import java.awt.event.*;
import java.net.*;
import java.util.*;
import java.io.*;
import java.util.ArrayList; 

import javax.swing.JFrame;

import logic.Message;
import component.ImageLoading;

public class Background extends Panel implements  Runnable , MouseListener,KeyListener{
	private static final long serialVersionUID = 1L;
	String fileName="map/map1.txt";
	Hashtable htPlayer=new Hashtable(); 

	Hashtable allPlayers=new Hashtable();//for saving all of players information, store Message Objects
	public static int myNum;
	
	public static int isClose=0;
	
	private static int NumX=15;
	private static int NumY=13;
	private static int myHead;
	private static int myTeam;
	private static int myLive=1;
	private static int myBombPower=3;
	
	private static int moveTypeX;
	private static int moveTypeY;
	private static int DX=40;
	protected static int map[][];
	protected static int map2[][];
	private static int MoveD=20;
	private static int mySpeed=MoveD/10;
	Cursor handcur,customcur;

	private static int Sum_Blue=0,Dead_Blue=0,Dead_Red=0,Sum_Red=0;
	private   boolean blue_team_alive=true ,red_team_alive=true ;
	
	Socket mysocket;
	ObjectInputStream in;
	ObjectOutputStream out;
	Message  meMessage=new Message();
	
	Image bg;
	Image bgImg;
	Image Box1,Box2,Box3;
	Image Player11,Player12,Player13,Player14;
	Image Player21,Player22,Player23,Player24;
	Image Player132;
	Image tree;
	Image paopao,bombing;
	Image deathImg,winImg,lossImg;
	Image TestImg;
	
	private static int myX=35;
	private static int myY=-5;
	private  int myNumX,myNumY;
	
	private Image bgImage;	//cache
	private Graphics bg2;
	Thread th;
	
	boolean isFirstPrint = true ;
	JFrame parentFrame;
	
	public Background(JFrame parentFrame ,Hashtable ht,int num,ObjectInputStream input,ObjectOutputStream output) {
			
			this.parentFrame = parentFrame;
			
			this.htPlayer=ht;
			this.myNum=num;
			
			this.in=input;
			this.out=output;
			this.addMouseListener(this);
			this.addKeyListener(this);
			init();
			initPlayers();
			
			th=new Thread(this);
			th.start();
		}	
	public void start(){
		//	thread.start();
	}
	
	public void init(){
		initparam();
		handcur = Toolkit.getDefaultToolkit().createCustomCursor(ImageLoading.createImage("images/hand.png"),new Point(10,10),"hand") ; 
		customcur = Toolkit.getDefaultToolkit().createCustomCursor(ImageLoading.createImage("images/custom.png"),new Point(10,10),"custom") ; 
		this.setCursor(handcur);
		map=new int [NumX][NumY];
		
		for(int i=0;i<NumX;i++){
			for (int j=0;j<NumY;j++){
				map[i][j]=0;
			}
		}
		map2=new int [NumX][NumY];
		for(int i=0;i<NumX;i++){
			for(int j=0;j<NumY;j++){
				map2[i][j]=0;
			}
		}
		
		myNumX=8;
		myNumY=8;
		map[myNumX][myNumY]=13;
		myTeam=1;
		myHead=3;
		
		for(Enumeration ee=htPlayer.elements();ee.hasMoreElements();){
			Message test =(Message)ee.nextElement();
			if(test.number==myNum){
				myTeam = test.team;
				meMessage=test;
				meMessage.isLive=1;
			}
			if(test.team==1){
				Sum_Blue++;
			}
			if(test.team==-1){
				Sum_Red++;
			}
			
		}
		
		
		try{
			initMap();	
		}
		catch(IOException e){}
		
		initImages();
		
	}
	
	public void initparam(){
		myLive = 1 ;
		blue_team_alive=true ;
		red_team_alive=true ;
		Sum_Blue=0;
		Dead_Blue=0 ;
		Dead_Red=0 ;
		Sum_Red=0;
		isFirstPrint = true;
	}
	public void initPlayers(){
		
		for(Enumeration ee=htPlayer.elements();ee.hasMoreElements();){
			Message test =(Message)ee.nextElement();
			allPlayers.put(test.number,test);
			int m=test.x;
			int n=test.y;
			
			switch(test.number){
				case 1:{
					m=2;
					n=2;
					map[m][n]=1322;
				}break;
				case 2:{
					m=12;
					n=10;
					map[m][n]=1322;
				}break;
				case 3:{
					m=NumX-3;
					n=3-1;
					map[m][n]=1322;
				}break;
				case 4:{
					m=2;
					n=10;
					map[m][n]=1322;
				}break;
				case 5:{
					m=3;
					n=1;
					map[m][n]=1322;
				}break;
				case 6:{
					m=3;
					n=1;
					map[m][n]=1322;
				}break;
				case 7:{
					m=3;
					n=1;
					map[m][n]=1322;
				}break;
				case 8:{
					m=3;
					n=1;
					map[m][n]=1322;
				}break;
				
				
				
			}
			test.isLive=1;
			if(test.number==myNum){
				myX=40*m+5;
				myY=40*n+20;
				
			}
		}
		
	}
	
public void initMap() throws IOException{
		ArrayList lines=new ArrayList();
		int width=0;
		int height=0;
		BufferedReader reader=null;
		try{
			reader=new BufferedReader(new FileReader(fileName));
		}
		catch(IOException e){}
		
		while(true){
			String line=reader.readLine();
			if(line==null){
				reader.close();
				break;
			}
			if(!line.startsWith("#")){
				line=line.trim();
				lines.add(line);
				width=Math.max(width,line.length());
			}
		}
		
		height=lines.size();
		
		for(int i=0;i<height;i++){
			String line=(String)lines.get(i);
			for(int j=0;j<line.length();j++){
				int c=(int)line.charAt(j);
				c=c-48;								//transfer ASC code to number
			}
		}
		
		
	
		//speed
		for(int i=0;i<6;i++){
			int m,n;
			Random random = new Random();
			m=random.nextInt(15);
			n=random.nextInt(13);
			map2[m][n]=6;
		}
		//power
		for(int i=0;i<8;i++){
			int m,n;
			Random random = new Random();
			m=random.nextInt(15);
			n=random.nextInt(13);
			map2[m][n]=7;
		}
		//bomb
		for(int i=0;i<8;i++){
			int m=0;
			int n=0;
			Random random = new Random();
			m=random.nextInt(15);
			n=random.nextInt(13);
		}		
	}

	public void initImages(){
		Toolkit tool;
		tool=getToolkit();
		
		bg=tool.getImage("images/item.png");
		bgImg=tool.getImage("images/bgImg.jpg");
		Box1=tool.getImage("images/Box1.png");
		Box2=tool.getImage("images/Box2.png");
		Box3=tool.getImage("images/Box3.png");
		
		Player11=tool.getImage("images/Player11.png");
		Player12=tool.getImage("images/Player12.png");
		Player13=tool.getImage("images/Player13.png");
		Player14=tool.getImage("images/Player14.png");
		
		Player21=tool.getImage("images/Player21.png");
		Player22=tool.getImage("images/Player22.png");
		Player23=tool.getImage("images/Player23.png");
		Player24=tool.getImage("images/Player24.png");
		
		//Player132=tool.getImage("images/Player132.png");
		
		paopao=tool.getImage("images/paopao.png");
		bombing=tool.getImage("images/bombing.png");
		tree=tool.getImage("images/tree.png");
		deathImg=tool.getImage("images/death.png");
		winImg=tool.getImage("images/win.png");
		lossImg=tool.getImage("images/loss.png");
		
		TestImg=Player13;
	}
	public void run() {}
	public void mousePressed(MouseEvent e){}
	public void mouseReleased(MouseEvent e){}
	public void mouseEntered(MouseEvent e){}
	public void mouseExited(MouseEvent e){}
	public void mouseClicked(MouseEvent e){}
	public void keyPressed(KeyEvent e){}
	public void keyTyped(KeyEvent e){}
	public void keyReleased(KeyEvent e){}	
}
