package test;
import org.junit.Test;
import static org.hamcrest.core.Is.is;


import java.awt.Graphics;

import javax.swing.ImageIcon;

import component.*;
import static org.junit.Assert.*;
public class ImageButtonTest {
	 @Test
	    public void testCheck(){
		 
		 	ImageButton button = new ImageButton("images/login_bt1.png");
		 	
	        
	        
	        assertEquals(button.check(), "Check");
	    } 
	 




}
