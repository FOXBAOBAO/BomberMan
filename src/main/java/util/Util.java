package util;


import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.Window;

import javax.swing.JFrame;

import logic.Message;
import component.Windows;

public class Util {
	public static void printMessage(String desc ,Message msg){
		System.out.println(desc +"===>>> userName="+msg.getUserName() 
				+ " number=" +msg.getNumber() +" team=" +msg.getTeam() 
				+" type=" +msg.getType()+" ready="+msg.getReady() 
				+" head="+msg.getHead() +" isLive="+ msg.isLive);
		
	}

	public static void setSizeFullOfScreend(Window window){
	    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	    window.setBounds(0, 0, screenSize.width, screenSize.height);
	}
	public static void showWindow(JFrame window){
		int w =window.getWidth();
		int h = window.getHeight();
		if(h==0)h=1;
		if(w==0)w=1;
		int count = 10;
		int cur_h = 0;
		int cur_w = 0 ;
		float w_n = w/h ;

	    new Windows(window);
	    
		while(cur_w < w || cur_h < h){
			if(cur_w <w )
				cur_w = (int) (cur_w + count*w_n) ;
			if(cur_h < h)
				cur_h = cur_h + count ;
			window.setSize(cur_w, cur_h);
			if(cur_w > w && cur_h > h)
				return ;
			try {
				Thread.sleep(20);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	public static void closeWindow(JFrame window){
		int w =window.getWidth();
		int h = window.getHeight();
		int count = 10;
		int cur_h = h;
		int cur_w = w ;
		float w_n = w/h ;

	    new Windows(window);
		while(cur_w > 0  || cur_h > 0){
			if(cur_w > 0)
				cur_w = (int)(cur_w - count*w_n) ;
			if(cur_h > 0)
				cur_h = cur_h - count ;
			
			window.setSize(cur_w, cur_h);
			
			if(cur_w < 0  && cur_h < 0)
				return ;
			
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				e.printStackTrace(System.out);//print the log ,may cause deadlock 
			}
		}
	}

}
