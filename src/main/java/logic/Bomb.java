package logic;

import ui.Background;

public class Bomb extends Thread{
	Background bg;
	int m;
	int n;
	int sleepTime=4000;
	int power;
	boolean stopMark;
	
  public Bomb(int x,int y,int pp,Background p){
    m=x;
    n=y;
    bg=p;
		
    power=pp;
		
  }
	
	public void run(){
		while(!stopMark){
			
			try{
				bg.setBomb(m,n);
				
				sleep(sleepTime);
				
				bg.Bombing(m,n,power);
				
				sleep(500);
				bg.cleanBomb();
				
				sleep(1000);
				
				bg.cleanPlayer();
				
			}
      catch(InterruptedException e){
				
      }
			stopMark=true;
		}
	}
}
