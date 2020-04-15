package test;
import org.junit.Test;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

import java.awt.Graphics;

import javax.swing.ImageIcon;

import component.*;

public class ImageButtonTest {
	 @Test
	    public void testImageButton(){
		 
		 	ImageButton button = new ImageButton("images/login_bt1.png");

	        
	        
	        assertThat(button.check(), is("Check."));
	    } 
	 




}
