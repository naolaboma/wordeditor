package wordEditor;

import java.awt.Dimension;

import javax.swing.JButton;
import javax.swing.JToggleButton;

public class AddToolTips {
	public void  addToolTip(JButton[]button,String[]text) {
		 
		for(int i=0; i<button.length;i++) {
          button[i].setToolTipText(text[i]);
		}
		
	 }
	
	public void  addToolTip(JToggleButton[]button,String[]text) {
		 
		for(int i=0; i<button.length;i++) {
          button[i].setToolTipText(text[i]);
		}
		
	 }

}
