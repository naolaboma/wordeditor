package wordEditor;

import java.awt.Color;

import javax.swing.JTextPane;
import javax.swing.text.rtf.RTFEditorKit;

public class TopParent {
	public RTFEditorKit rtfKit=new RTFEditorKit();
	public void SetEnable_JTextPane(JTextPane body)
	{
		body.setBackground(Color.white);
		body.setEditable(true);
		body.requestFocus();
	}
//********************* SetDisable ****************************
	public void SetDisable_JTextPane(JTextPane body)
	{
		body.setBackground(Color.gray);
		body.setEditable(false);
	}

}
