package wordEditor;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;

public class CustomImage {
	
	public ImageIcon resizeImg(String path) {
		 ImageIcon originalIcon = new ImageIcon(path);
		Image originalImage = originalIcon.getImage();

	    // Resize the image to 20x20 pixels
	    BufferedImage resizedImage = new BufferedImage(20, 20, BufferedImage.TYPE_INT_ARGB);
	    Graphics2D g = resizedImage.createGraphics();
	    g.drawImage(originalImage, 0, 0, 20, 20, null);
	    g.dispose();

	    // Create a new ImageIcon with the resized image
	    ImageIcon resizedIcon = new ImageIcon(resizedImage);
	    return  resizedIcon;
		
	}
	
}
