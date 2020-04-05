package main.java.component;




import java.awt.Dialog;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.Toolkit;

import javax.swing.JFrame;

public class Windows {

	   public   Windows(Dialog  window)   {//target is window
	       window.setResizable(false);//don't allow resize
	       //Center   the   window   
	       Dimension   screenSize   =   Toolkit.getDefaultToolkit().getScreenSize();   
	       Dimension   dialogSize   =   window.getSize();   
	       if   (dialogSize.height   >   screenSize.height)   {   
	           dialogSize.height   =   screenSize.height;   
	       }   
	       if   (dialogSize.width   >   screenSize.width)   {   
	           dialogSize.width   =   screenSize.width;   
	       }   
	       window.setLocation((screenSize.width   -   dialogSize.width)   /   2,   
	           (screenSize.height   -   dialogSize.height)   /   2);   
	   } 
	   public   Windows(JFrame   window)   {
	       window.setResizable(false);  
	       Dimension   screenSize   =   Toolkit.getDefaultToolkit().getScreenSize();   
	       Dimension   dialogSize   =   window.getSize();   
	       if   (dialogSize.height   >   screenSize.height)   {   
	           dialogSize.height   =   screenSize.height;   
	       }   
	       if   (dialogSize.width   >   screenSize.width)   {   
	           dialogSize.width   =   screenSize.width;   
	       }   
	       window.setLocation((screenSize.width   -   dialogSize.width)   /   2,   
	           (screenSize.height   -   dialogSize.height)   /   2);   
	   }   
	   public   Windows(Frame   window)   {  
	       window.setResizable(false);
	       Dimension   screenSize   =   Toolkit.getDefaultToolkit().getScreenSize();   
	       Dimension   dialogSize   =   window.getSize();   
	       if   (dialogSize.height   >   screenSize.height)   {   
	           dialogSize.height   =   screenSize.height;   
	       }   
	       if   (dialogSize.width   >   screenSize.width)   {   
	           dialogSize.width   =   screenSize.width;   
	       }   
	       window.setLocation((screenSize.width   -   dialogSize.width)   /   2,   
	           (screenSize.height   -   dialogSize.height)   /   2);   
	   } 

}
