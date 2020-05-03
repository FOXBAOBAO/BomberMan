package component;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class ImageLabel extends JLabel{
  public ImageLabel(String img){
    this(new ImageIcon(img));
  }
  
  public ImageLabel(ImageIcon icon){
    //Set icon
    setIcon(icon);
	//set gap between icon and text
    setIconTextGap(0);
	//set border
    setBorder(null);
	//set text
    setText(null);
    setSize(icon.getImage().getWidth(null), icon.getImage().getHeight(null));
  }
}
