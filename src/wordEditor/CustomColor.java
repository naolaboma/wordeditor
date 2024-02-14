package wordEditor;

import java.awt.Color;

import javax.swing.JColorChooser;
import javax.swing.JTextPane;

public class CustomColor {

	public void AddCustomBackground( JTextPane text) {
		 
		Color color = JColorChooser.showDialog(null, "Choose Background Color", text.getBackground());
        if (color != null) {
            text.setBackground(color);
        }
	}
	public void AddCustomForeGround( JTextPane text) {
		 
		Color color = JColorChooser.showDialog(null, "Choose Background Color", text.getBackground());
        if (color != null) {
            text.setForeground(color);
        }
	}
}
