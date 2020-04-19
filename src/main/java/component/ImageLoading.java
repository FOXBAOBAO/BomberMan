package component;

import java.awt.Image;
import java.awt.Toolkit;
import javax.swing.ImageIcon;

public class ImageLoading {

	public static ImageIcon createImageIcon(String path){

		return new ImageIcon(path);
	}
	
	public static Image createImage(String path){
		
		return Toolkit.getDefaultToolkit().getImage(path);
	}
	

}
