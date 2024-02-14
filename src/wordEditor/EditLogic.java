package wordEditor;

import javax.swing.JTextPane;

public class EditLogic {
	
	public void cut(JTextPane body) {
		 body.cut();
	}
	public void copy(JTextPane body) {
		 body.copy();
	}

	
	public void paste(JTextPane body) {
		 body.paste();
	}


}
