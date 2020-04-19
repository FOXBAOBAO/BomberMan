package component;

import java.awt.Insets;
import javax.swing.ImageIcon;
import javax.swing.JButton;

public class ImageButton extends JButton{
	public ImageButton(String img){
		this(new ImageIcon(img));
	}

	public ImageButton(ImageIcon icon){
		this.setDoubleBuffered(true);
		//set icon
		setIcon(icon);
		//set margin
		setMargin(new Insets(0, 0, 0, 0));
		//set gap between icon and text
		setIconTextGap(0);
		
		setBorderPainted(false);
		//set border
		setBorder(null);
		//set text
		setText(null);
		setSize(icon.getImage().getWidth(null), icon.getImage().getHeight(null));
	}
	
	 public String check(){
	        return "Check";
	    } 
}
