package main.java.component;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import javax.swing.*;


public class ImagePanel extends JPanel
{
	private Image img;
	public ImagePanel(String img){
		this(new ImageIcon(img).getImage());
	}
	public ImagePanel(Image img){
		this.img = img;
	// Define image size
		Dimension size = new Dimension(img.getWidth(null), img.getHeight(null));
	
		int imgW = img.getWidth(null);

		System.out.println(imgW);
	
		setPreferredSize(size);
		setMinimumSize(size);
		setMaximumSize(size);
		setSize(size);
		setLayout(null);
		setDoubleBuffered(true);
}
	public void paintComponent(Graphics g){
		g.drawImage(img, 0, 0, null);
	}
}