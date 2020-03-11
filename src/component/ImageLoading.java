package component;
import java.awt.Image;
import java.swing.ImageIcon;
public class ImageLoading {

	public ImageIcon createImageIcon(String path){
		ImageIcon ima=new ImageIcon(path);
		return ima;
		
	}
	public Image CreatImage(String path){
		Image ima = new Image(path);
		return ima;
		
		
	}
	
	

}
