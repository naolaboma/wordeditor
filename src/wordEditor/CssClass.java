package wordEditor;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JButton;
import javax.swing.JToggleButton;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class CssClass {
	 
	public void styleButton(JButton[]styledButton) {
		 
		for(int i=0; i<styledButton.length;i++) {
         styledButton[i].setPreferredSize(new Dimension(30, 30));
         styledButton[i].setBorderPainted(false);
	      handleEffect(styledButton[i]);
			
		}
		
	 }
	public void styleButton(JToggleButton[]styledButton) {
		 
		for(int i=0; i<styledButton.length;i++) {
         styledButton[i].setPreferredSize(new Dimension(30, 30));
         styledButton[i].setBorderPainted(false);
	      handleEffect(styledButton[i]);
			
		}
		
	 }
	public void handleEffect(JButton styledButton) {
		 // Add a hover effect to the button
        styledButton.addMouseListener(new MouseAdapter() {
        	
            public void mouseEntered(MouseEvent e) {
                styledButton.setBackground(Color.ORANGE);
            }

            public void mouseExited(MouseEvent e) {
            	styledButton.setBackground(Color.WHITE);
                
            }
        });
		
	}
	public void handleEffect(JToggleButton styledButton) {
		 // Add a hover effect to the button
       styledButton.addMouseListener(new MouseAdapter() {
           public void mouseEntered(MouseEvent e) {
               styledButton.setBackground(Color.ORANGE);
           }

           public void mouseExited(MouseEvent e) {
        	   styledButton.setBackground(Color.WHITE);
               
           }
       });
		
	}

}
