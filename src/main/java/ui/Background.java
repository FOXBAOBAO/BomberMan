package ui;

import java.awt.*;

import java.awt.event.*;
import java.net.*;
import java.util.*;
import java.io.*;

import javax.swing.JFrame;

import logic.Message;
import logic.MessageControl;
import logic.Bomb;
import util.Key;
import component.Windows;
import ui.Lobby;
import component.ImageLoading;

public class Background extends Panel implements Runnable,MouseListener,KeyListener{


	private static final long serialVersionUID = 1L;
	String fileName="map/map1.txt";
	Hashtable htPlayer=new Hashtable(); 

	Hashtable allPlayers=new Hashtable();
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
	
	private Image bgImage;	
	private Graphics bg2;
	Thread th ;
	
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
		handcur = Toolkit.getDefaultToolkit().createCustomCursor(ImageLoading.createImage("images/hand.png"),new Point(10,10),"hand");
		customcur = Toolkit.getDefaultToolkit().createCustomCursor(ImageLoading.createImage("images/custom.png"),new Point(10,10),"custom");
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
				//	myX=40*m+5;
				//	myY=40*n+20;
				}break;
				case 2:{
					m=12;
					n=10;
					map[m][n]=1322;
				//	myX=40*m+5;
				//	myY=40*n+20;
				}break;
				case 3:{
					m=NumX-3;
					n=3-1;
					map[m][n]=1322;
				//	myX=40*m+5;
				//	myY=40*n+20;
				}break;
				case 4:{
					m=2;
					n=10;
					map[m][n]=1322;
				//	myX=40*m+5;
				//	myY=40*n+20;
				}break;
				case 5:{
					m=3;
					n=1;
					map[m][n]=1322;
				//	myX=40*m+5;
				//	myY=40*n+20;
				}break;
				case 6:{
					m=3;
					n=1;
					map[m][n]=1322;
				//	myX=40*m+5;
				//	myY=40*n+20;
				}break;
				case 7:{
					m=3;
					n=1;
					map[m][n]=1322;
				//	myX=40*m+5;
				//	myY=40*n+20;
				}break;
				case 8:{
					m=3;
					n=1;
					map[m][n]=1322;
				//	myX=40*m+5;
					//myY=40*n+20;
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
				c=c-48;								
				map[j][i]=c;
			//	System.out.println(map[j][i]);
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
		//paopao
		for(int i=0;i<8;i++){
			int m=0;
			int n=0;
			Random random = new Random();
			m=random.nextInt(15);
			n=random.nextInt(13);
			//map2[m][n]=8;
		}
		
		
		
	}
	public void initImages(){
		Toolkit tool;
		tool=getToolkit();
		
		bg=tool.getImage("images/bg.png");
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
		
		Player132=tool.getImage("images/Player132.png");
		
		paopao=tool.getImage("images/paopao.png");
		bombing=tool.getImage("images/bombing.png");
		tree=tool.getImage("images/tree.png");
		deathImg=tool.getImage("images/death.png");
		winImg=tool.getImage("images/win.png");
		lossImg=tool.getImage("images/loss.png");
		
		TestImg=Player13;
	}
	public void run(){
		while(true){
			Message test =new Message();
			test = MessageControl.receiveMessage(in);
			doMessage(test);
			
			if(Sum_Blue==Dead_Blue ||Sum_Red==Dead_Red){
				int second = 5000 ;
				if(Sum_Blue==Dead_Blue){
					blue_team_alive = false;
					if(this.meMessage.team==Key.TEAM_RED)second = 5000;
				}
				if(Sum_Red==Dead_Red){
					red_team_alive = false;
					if(this.meMessage.team==Key.TEAM_BLUE)second = 5000;
				}
				try{
					Thread.sleep(second);	
				}
				catch(InterruptedException e){}

				Message meme= null ;
				for(Enumeration ee=allPlayers.elements();ee.hasMoreElements();){
					
					Message tempM =(Message)ee.nextElement();
					tempM.ready = 0 ;
					tempM.isLive = 1;
					if(tempM.number == this.myNum){
						meme = new Message();
						meme=tempM;
						meme.number = tempM.number ;
						
					}
					allPlayers.put(tempM.number, tempM);
					
				}
				if(meme != null ){
					meme.ready = 0 ; 
					meme.isLive=1;
					meme.number = this.myNum;
					meme.team = this.myTeam ;
					new Windows(new Lobby(allPlayers ,meme,in,out));
					this.parentFrame.setVisible(false);
				}
				
				
				isClose=1;
				break;
			}
		}
		//this.setVisible(false);
	}
	public void doMessage(Message msg){
		System.out.println("DOMessaging ......");

		if(msg.type==104){
			if(msg.team==Key.TEAM_BLUE){
				Dead_Blue++;
			}
			if(msg.team==Key.TEAM_RED){
				Dead_Red++;
			}
		}
		if(msg.type==101){
			int m=msg.x;
			int n=msg.y;
			int headtext=msg.head;
			int text=0;
			if(text!=1){
				setPosition(msg.head,msg.x,msg.y,msg.team,msg.moveTypeX,msg.moveTypeY,msg.isNext);	
				repaint();
			}
		}
		
		if(msg.type==108){
			
			Bomb bomb=new Bomb(msg.x,msg.y,msg.BombPower,this);
			bomb.start();
		}
	}
	public void paint(Graphics g){
		
		if(isFirstPrint){
			g.drawImage(bombing,-600,-600,this);
			g.drawImage(paopao,-600,-600,this);
			g.drawImage(Player12,-600,-600,this);
			g.drawImage(Player13,-600,-600,this);
			g.drawImage(Player14,-600,-600,this);
			g.drawImage(Player11,-600,-600,this);
			g.drawImage(deathImg,-600,-600,this);
			g.drawImage(Player132,-600,-600,this);
			g.drawImage(winImg,-600,-600,this);
			g.drawImage(lossImg,-600,-600,this);
			isFirstPrint = false ;
			
		}
		
		
		g.drawImage(bgImg,0,0,this);
		printPad(g);
		

		if(myLive==0 && blue_team_alive==true && red_team_alive == true){
			g.drawImage(deathImg,84,140,this);
		}
		
		if(myTeam==Key.TEAM_BLUE){
			if(!blue_team_alive){
				g.drawImage(lossImg,84,140,this);
			}
			if(!red_team_alive){
				g.drawImage(winImg,84,140,this);
			}
		}else if(myTeam==Key.TEAM_RED){

			if(!red_team_alive){
				g.drawImage(lossImg,84,140,this);
			}
			if(!blue_team_alive){
				g.drawImage(winImg,84,140,this);
			}
		}
		
		
	}
	public void update(Graphics g){
		if(bgImage==null){
			
			bgImage=createImage(this.getSize().width,this.getSize().height);
        	bg2=bgImage.getGraphics();
		}
		bg2.setColor(getBackground());
   		bg2.fillRect(0,0,this.getSize().width,this.getSize().height);

   		bg2.setColor(getForeground());
   		paint(bg2);
   		g.drawImage(bgImage,0,0,this);
	}
	
	public void setPosition(int head,int m,int n,int team,int mTypeX,int mTypeY,int isnext){
		//team+head+mTypeX+mTypeY		
		System.out.println("setPosition team="+team+"  head="+head+" mTypeX="+mTypeX+"  mTypeY="+mTypeY+" m="+m+"  n="+n);
		int typeX=mTypeX/10;
		int typeY=mTypeY/10;
		//int all=team*1000+head*100+typeX*10+typeY;
		int all=1*1000+head*100+typeX*10+typeY;
		map[m][n]=all;
			switch(isnext){
				case 1:map[m][n+1]=0;break;
				case 2:map[m-1][n]=0;break;
				case 3:map[m][n-1]=0;break;
				case 4:map[m+1][n]=0;break;
			}
	}
	public void setBomb(int m,int n){
		map2[m][n]=8;
		repaint();
	}
	
	
	public void Bombing(int x,int y,int p){
		
		int m=x;
		int n=y;
		int power=p;
		
		for(int i=m;i<=m+power;i++){   
			System.out.println("  Power:"+power+"  i="+i+"  n="+n);

			if(i>=15){
				break;
			}
			if(map[i][n]==2){
				break;
			}
			if(map[i][n]==0){
				map[i][n]=88;
			}
			if(map[i][n]==1){
				map[i][n]=88;
				break;
			}
			if(map[i][n]>1000&&map[i][n]<1500){
				
				map[i][n]=44;
				if((myX-5)/40==i&&(myY-20)/40==n){
					myLive=0;
				}
			}
		}
		
		for(int i=m;i>=m-power;i--){	
			if(i<0){
				break;
			}
			if(map[i][n]==2){
				break;
			}
			if(map[i][n]==0){
				map[i][n]=88;
			}
			if(map[i][n]==1){
				
				map[i][n]=88;
				break;
			}
			
			if(map[i][n]>1000&&map[i][n]<1500){
				
				map[i][n]=44;
				if((myX-5)/40==i&&(myY-20)/40==n){
					myLive=0;
				}
			}
			
		}
		
		for(int i=n;i<=n+power;i++){		
			if(i>=13){
				break;
			}
			if(map[m][i]==2){
				break;
			}
			if(map[m][i]==0){
				map[m][i]=88;
			}
			if(map[m][i]==1){
				
				map[m][i]=88;
				break;
			}
			if(map[m][i]>1000&&map[m][i]<1500){
				
				map[m][i]=44;
				
				if((myX-5)/40==m&&(myY-20)/40==i){
					myLive=0;
				}
			}
			
		}
		
		for(int i=n;i>=n-power;i--){	
		
			if(i<0){
				break;
			}
			if(map[m][i]==2){
				break;
			}
			if(map[m][i]==0){
				map[m][i]=88;
			}
			if(map[m][i]==1){
				
				map[m][i]=88;
				break;
			}
			
			if(map[m][i]>1000&&map[m][i]<1500){
				
				map[m][i]=44;
				if((myX-5)/40==m&&(myY-20)/40==i){
					myLive=0;
				}
			}
			
		}
		map2[m][n]=0;
		
		repaint();
		
		if(myLive==0){
			Message msg =new Message();
			msg=meMessage;
			msg.isLive=0;
			msg.type=104;
			MessageControl.sendMessage(msg,out);
			
		}
		
	}
	public void cleanBomb(){
		int j,i;
		for (j=0;j<NumY;j++){
			for(i=NumX-1;i>=0;i--){
				if(map[i][j]==88){
					map[i][j]=0;
				//	map2[i][j]=0;
				}
			}
		}
		repaint();
	}
	public void cleanPlayer(){
		int j,i;
		for (j=0;j<NumY;j++){
		
			for(i=NumX-1;i>=0;i--){
				if(map[i][j]==44){
					map[i][j]=0;
				//	map2[i][j]=0;
				}
			}
		}
		repaint();
		
	}
	public void printPad(Graphics g){
		
		
		int i=0;
		int j=0;
		for (j=0;j<NumY;j++){
		
			for(i=NumX-1;i>=0;i--){
				if(map[i][j]==1)g.drawImage(Box1,i*DX,8+j*DX,this);
				if(map[i][j]==2)g.drawImage(tree,5+i*DX,-10+j*DX,this);
				
				if(map[i][j]==21)
					g.drawImage(Player21,(i+1)*DX,(j+1)*DX,this);
             	if(map[i][j]==22)
             	g.drawImage(Player22,(i+1)*DX,(j+1)*DX,this);
             	if(map[i][j]==23)
             	g.drawImage(Player23,(i+1)*DX,(j+1)*DX,this);
             	if(map[i][j]==24)
             	g.drawImage(Player24,(i+1)*DX,(j+1)*DX,this);
             	
             	
             	if(map[i][j]==13)
             	g.drawImage(Player13,(i+1)*DX,(j+1)*DX,this);
             	
             	//if(map2[i][j]==8)g.drawImage(paopao,i*DX+5,j*DX+20,this);
             	
             	if(map[i][j]==8){
             		g.drawImage(paopao,i*DX+5,j*DX+20,this);
             	}   
             	
             	if(map[i][j]==88){
             		g.drawImage(bombing,i*DX+5,j*DX+20,this);
             	}     
             	
             	if(map[i][j]==44)
             	g.drawImage(Player132,-5+i*DX,-45+j*DX,this);      	            	
//team+head+mTypeX+mTypeY
             	if(map[i][j]==1111)
             	g.drawImage(Player11,-5+i*DX-10,-45+j*DX-10,this);
             	if(map[i][j]==1112)
             	g.drawImage(Player11,-5+i*DX-10,-45+j*DX,this);
             	if(map[i][j]==1113)
             	g.drawImage(Player11,-5+i*DX-10,-45+j*DX+10,this);
             	if(map[i][j]==1110)
             	g.drawImage(Player11,-5+i*DX-10,-45+j*DX-20,this);	
//team+head+mTypeX+mTypeY
             	if(map[i][j]==1121)
             	g.drawImage(Player11,-5+i*DX,-45+j*DX-10,this);
             	if(map[i][j]==1122)
             	g.drawImage(Player11,-5+i*DX,-45+j*DX,this);
             	if(map[i][j]==1123)
             	g.drawImage(Player11,-5+i*DX,-45+j*DX+10,this);
             	if(map[i][j]==1120)
             	g.drawImage(Player11,-5+i*DX,-45+j*DX-20,this);	
             	
//team+head+mTypeX+mTypeY
             	if(map[i][j]==1131)
             	g.drawImage(Player11,-5+i*DX+10,-45+j*DX-10,this);
             	if(map[i][j]==1132)
             	g.drawImage(Player11,-5+i*DX+10,-45+j*DX+0,this);
             	if(map[i][j]==1133)
             	g.drawImage(Player11,-5+i*DX+10,-45+j*DX+10,this);
             	if(map[i][j]==1130)
             	g.drawImage(Player11,-5+i*DX+10,-45+j*DX-20,this);	
//team+head+mTypeX+mTypeY
             	if(map[i][j]==1101)
             	g.drawImage(Player11,-5+i*DX-20,-45+j*DX-10,this);
             	if(map[i][j]==1102)
             	g.drawImage(Player11,-5+i*DX-20,-45+j*DX+0,this);
             	if(map[i][j]==1103)
             	g.drawImage(Player11,-5+i*DX-20,-45+j*DX+10,this);
             	if(map[i][j]==1100)
             	g.drawImage(Player11,-5+i*DX-20,-45+j*DX-20,this);	
  


//team+head+mTypeX+mTypeY
             	if(map[i][j]==1211)
             	g.drawImage(Player12,-5+i*DX-10,-45+j*DX-10,this);
             	if(map[i][j]==1212)
             	g.drawImage(Player12,-5+i*DX-10,-45+j*DX,this);
             	if(map[i][j]==1213)
             	g.drawImage(Player12,-5+i*DX-10,-45+j*DX+10,this);
             	if(map[i][j]==1210)
             	g.drawImage(Player12,-5+i*DX-10,-45+j*DX-20,this);	
//team+head+mTypeX+mTypeY
             	if(map[i][j]==1221)
             	g.drawImage(Player12,-5+i*DX,-45+j*DX-10,this);
             	if(map[i][j]==1222)
             	g.drawImage(Player12,-5+i*DX,-45+j*DX,this);
             	if(map[i][j]==1223)
             	g.drawImage(Player12,-5+i*DX,-45+j*DX+10,this);
             	if(map[i][j]==1220)
             	g.drawImage(Player12,-5+i*DX,-45+j*DX-20,this);	
             	
//team+head+mTypeX+mTypeY
             	if(map[i][j]==1231)
             	g.drawImage(Player12,-5+i*DX+10,-45+j*DX-10,this);
             	if(map[i][j]==1232)
             	g.drawImage(Player12,-5+i*DX+10,-45+j*DX+0,this);
             	if(map[i][j]==1233)
             	g.drawImage(Player12,-5+i*DX+10,-45+j*DX+10,this);
             	if(map[i][j]==1230)
             	g.drawImage(Player12,-5+i*DX+10,-45+j*DX-20,this);	
//team+head+mTypeX+mTypeY
             	if(map[i][j]==1201)
             	g.drawImage(Player12,-5+i*DX-20,-45+j*DX-10,this);
             	if(map[i][j]==1202)
             	g.drawImage(Player12,-5+i*DX-20,-45+j*DX+0,this);
             	if(map[i][j]==1203)
             	g.drawImage(Player12,-5+i*DX-20,-45+j*DX+10,this);
             	if(map[i][j]==1200)
             	g.drawImage(Player12,-5+i*DX-20,-45+j*DX-20,this);	
            	

//team+head+mTypeX+mTypeY
             	if(map[i][j]==1311)
             	g.drawImage(Player13,-5+i*DX-10,-45+j*DX-10,this);
             	if(map[i][j]==1312)
             	g.drawImage(Player13,-5+i*DX-10,-45+j*DX,this);
             	if(map[i][j]==1313)
             	g.drawImage(Player13,-5+i*DX-10,-45+j*DX+10,this);
             	if(map[i][j]==1310)
             	g.drawImage(Player13,-5+i*DX-10,-45+j*DX-20,this);	
//team+head+mTypeX+mTypeY
             	if(map[i][j]==1321)
             	g.drawImage(Player13,-5+i*DX,-45+j*DX-10,this);
             	if(map[i][j]==1322)
             	g.drawImage(Player13,-5+i*DX,-45+j*DX,this);
             	if(map[i][j]==1323)
             	g.drawImage(Player13,-5+i*DX,-45+j*DX+10,this);
             	if(map[i][j]==1320)
             	g.drawImage(Player13,-5+i*DX,-45+j*DX-20,this);	
             	
//team+head+mTypeX+mTypeY
             	if(map[i][j]==1331)
             	g.drawImage(Player13,-5+i*DX+10,-45+j*DX-10,this);
             	if(map[i][j]==1332)
             	g.drawImage(Player13,-5+i*DX+10,-45+j*DX+0,this);
             	if(map[i][j]==1333)
             	g.drawImage(Player13,-5+i*DX+10,-45+j*DX+10,this);
             	if(map[i][j]==1330)
             	g.drawImage(Player13,-5+i*DX+10,-45+j*DX-20,this);	
//team+head+mTypeX+mTypeY
             	if(map[i][j]==1301)
             	g.drawImage(Player13,-5+i*DX-20,-45+j*DX-10,this);
             	if(map[i][j]==1302)
             	g.drawImage(Player13,-5+i*DX-20,-45+j*DX+0,this);
             	if(map[i][j]==1303)
             	g.drawImage(Player13,-5+i*DX-20,-45+j*DX+10,this);
             	if(map[i][j]==1300)
             	g.drawImage(Player13,-5+i*DX-20,-45+j*DX-20,this);		


//team+head+mTypeX+mTypeY
             	if(map[i][j]==1411)
             	g.drawImage(Player14,-5+i*DX-10,-45+j*DX-10,this);
             	if(map[i][j]==1412)
             	g.drawImage(Player14,-5+i*DX-10,-45+j*DX,this);
             	if(map[i][j]==1413)
             	g.drawImage(Player14,-5+i*DX-10,-45+j*DX+10,this);
             	if(map[i][j]==1410)
             	g.drawImage(Player14,-5+i*DX-10,-45+j*DX-20,this);	
//team+head+mTypeX+mTypeY
             	if(map[i][j]==1421)
             	g.drawImage(Player14,-5+i*DX,-45+j*DX-10,this);
             	if(map[i][j]==1422)
             	g.drawImage(Player14,-5+i*DX,-45+j*DX,this);
             	if(map[i][j]==1423)
             	g.drawImage(Player14,-5+i*DX,-45+j*DX+10,this);
             	if(map[i][j]==1420)
             	g.drawImage(Player14,-5+i*DX,-45+j*DX-20,this);	
             	
//team+head+mTypeX+mTypeY
             	if(map[i][j]==1431)
             	g.drawImage(Player14,-5+i*DX+10,-45+j*DX-10,this);
             	if(map[i][j]==1432)
             	g.drawImage(Player14,-5+i*DX+10,-45+j*DX+0,this);
             	if(map[i][j]==1433)
             	g.drawImage(Player14,-5+i*DX+10,-45+j*DX+10,this);
             	if(map[i][j]==1430)
             	g.drawImage(Player14,-5+i*DX+10,-45+j*DX-20,this);	
//team+head+mTypeX+mTypeY
             	if(map[i][j]==1401)
             	g.drawImage(Player14,-5+i*DX-20,-45+j*DX-10,this);
             	if(map[i][j]==1402)
             	g.drawImage(Player14,-5+i*DX-20,-45+j*DX+0,this);
             	if(map[i][j]==1403)
             	g.drawImage(Player14,-5+i*DX-20,-45+j*DX+10,this);
             	if(map[i][j]==1400)
             	g.drawImage(Player14,-5+i*DX-20,-45+j*DX-20,this);	             	             		

				if(map2[i][j]==8)g.drawImage(paopao,i*DX+5,j*DX+20,this);
	
             	             	
				
			}
		}
    		
	}
	
	
	public void mousePressed(MouseEvent e){
		
		
	}
	public void mouseReleased(MouseEvent e){
		
	}
	public void mouseEntered(MouseEvent e){
		
		
	}
	public void mouseExited(MouseEvent e){
		
	}
	public void mouseClicked(MouseEvent e){

	}
	
	public void keyPressed(KeyEvent e){
		
		int m,n;
		int isNext=0;
		
		if(myLive==0){
			return;
		}
		
		
		if(e.getKeyCode()==KeyEvent.VK_UP){
			int m1=(myX-5)/40;
			int n1=(myY-20)/40;
			
			myY=myY-MoveD;
			if(myY<40){
				myY=40;
				return;
			}
			
			else{
			
//			switch((myX-5)%40){
//				case 10: moveTypeX=10;break;
//				case 20: moveTypeX=20;break;
//				case 30: moveTypeX=30;break;
//				case 0: moveTypeX=0;break;
//				default: break;
//			}
			if(n1-1>=0){
				if((map[m1][n1-1]==1||map2[m1][n1-1]==8||map[m1][n1-1]==2)&&moveTypeY <=20){
					myY=myY+MoveD;
				}else {

					switch((myY-20)%40){
						case 10: moveTypeY=10;break;
						case 20: moveTypeY=20;break;
						case 30: moveTypeY=30;break;
						case 0: moveTypeY=0;break;
						default: break;
					}
					
				}
			}
			
			m=(myX-5)/40;
			n=(myY-20)/40;
			if(n1!=n){
				isNext=1;
			}
			myHead=Key.PLAYER_HEADER_UP;
			
			Message test =new Message();
			test.isNext=isNext;
			test.type=Key.MESSAGE_TYPE_PLAYER_MOVE;
			test.x=m;
			test.y=n;
			test.head=myHead;
			test.team=myTeam;
			test.moveTypeX=this.moveTypeX;
			test.moveTypeY=this.moveTypeY;
			MessageControl.sendMessage(test,out);
			System.out.println("UP Pressed "+test.team+test.head+test.moveTypeX/10+test.moveTypeY/10);
			}
		}
		if(e.getKeyCode()==KeyEvent.VK_RIGHT){
			int m1=(myX-5)/40;
			int n1=(myY-20)/40;
			myX=myX+MoveD;
			if(myX>NumX*DX-DX/2+5){
				myX=NumX*DX-DX/2+5;
				return;
			}
			
			else{
//			switch((myY-20)%40){
//				case 10: moveTypeY=10;break;
//				case 20: moveTypeY=20;break;
//				case 30: moveTypeY=30;break;
//				case 0: moveTypeY=0;break;
//				default: break;
//			}
			switch((myX-5)%40){
				case 10: moveTypeX=10;break;
				case 20: moveTypeX=20;break;
				case 30: moveTypeX=30;break;
				case 0: moveTypeX=0;break;
				default: break;
			}
			
			if(m1+1<=14){
				if((map[m1+1][n1]==1||map2[m1+1][n1]==8||map[m1+1][n1]==2)){
					myX=myX-MoveD;
				}
			}	
			
			
			
			m=(myX-5)/40;
			n=(myY-20)/40;
			if(m1!=m){
				isNext=2;
			}
			
			myHead=Key.PLAYER_HEADER_RIGHT;
			Message test = new Message();
			test.isNext=isNext;
			test.type=Key.MESSAGE_TYPE_PLAYER_MOVE;
			test.x=m;
			test.y=n;
			test.head=myHead;
			test.team=myTeam;
			test.moveTypeX=this.moveTypeX;
			test.moveTypeY=this.moveTypeY;
			MessageControl.sendMessage(test,out);
			
			}
			System.out.println("RIGHT Pressed ");
		}
		if(e.getKeyCode()==KeyEvent.VK_DOWN){
			int m1=(myX-5)/40;
			int n1=(myY-20)/40;
			myY=myY+MoveD;
			if(myY>NumY*DX){
				myY=NumY*DX;
				return;
			}
			else{
			

				switch((myY-20)%40){	
					case 10: moveTypeY=10;break;
					case 20: moveTypeY=20;break;
					case 30: moveTypeY=30;break;
					case 0: moveTypeY=0;break;
					default: break;
				}
			
			if(n1+1<=12){
				if((map[m1][n1+1]==1||map2[m1][n1+1]==8||map[m1][n1+1]==2)){
					myY=myY-MoveD;
				}
				
			}
			m=(myX-5)/40;
			n=(myY-20)/40;
			if(n1!=n){
				isNext=3;
			}
			myHead=Key.PLAYER_HEADER_DOWN;
			Message test = new Message();
			test.isNext=isNext;
			test.type=Key.MESSAGE_TYPE_PLAYER_MOVE;
			test.x=m;
			test.y=n;
			test.head=myHead;
			test.team=myTeam;
			test.moveTypeX=this.moveTypeX;
			test.moveTypeY=this.moveTypeY;
			MessageControl.sendMessage(test,out);
			}
			System.out.println("DOWN Pressed ");
			
		}
		
		if(e.getKeyCode()==KeyEvent.VK_LEFT){
			
			int m1=(myX-5)/40;
			int n1=(myY-20)/40;
					
			myX=myX-MoveD;
			boolean hasBlock = false ;
			if(m1-1>=0){
				if((map[m1-1][n1]==1||map2[m1-1][n1]==8||map[m1-1][n1]==2)&&moveTypeX<=20){
					myX=myX+MoveD;
					hasBlock = true ;
				}
			}
			if(myX<20+5){
				myX=20+5;
				return;
			}
			else {
				if(hasBlock == false){
		//			switch((myY-20)%40){
		//				case 10: moveTypeY=10;break;
		//				case 20: moveTypeY=20;break;
		//				case 30: moveTypeY=30;break;
		//				case 0: moveTypeY=0;break;
		//				default: break;
		//			}
					switch((myX-5)%40){
						case 10: moveTypeX=10;break;
						case 20: moveTypeX=20;break;
						case 30: moveTypeX=30;break;
						case 0: moveTypeX=0;break;
						default: break;
					}
				}
			m=(myX-5)/40;
			n=(myY-20)/40;
			
			if(m1!=m){
				isNext=4;
			}
			myHead=Key.PLAYER_HEADER_LEFT;
			
			Message test = new Message();
			test.isNext=isNext;
			test.type=Key.MESSAGE_TYPE_PLAYER_MOVE;
			test.x=m;
			test.y=n;
			test.head=myHead;
			test.team=myTeam;
			test.moveTypeX=moveTypeX;
			test.moveTypeY=moveTypeY;
			
			MessageControl.sendMessage(test,out);
			}
			System.out.println("LEFT Pressed ");
		}
		
		if(e.getKeyCode()==KeyEvent.VK_SPACE){
			
			int m1=(myX-5)/40;
			int n1=(myY-20)/40;
			
			Message test= new Message();
			test.type=Key.MESSAGE_TYPE_LAYOUT_BOMB;
			test.x=m1;
			test.y=n1;
			test.BombPower=myBombPower;
			MessageControl.sendMessage(test,out);
			
		}
		
	}
	public void keyTyped(KeyEvent e){}
	public void keyReleased(KeyEvent e){}
}
