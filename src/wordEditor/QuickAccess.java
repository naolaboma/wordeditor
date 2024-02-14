package wordEditor;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextPane;
import javax.swing.text.Document;
import javax.swing.undo.UndoManager;

public class QuickAccess {
	 
	public QuickAccess() {
		   
	}
	
	//====undo
	 public void undo( UndoManager  undoManager) {
		 if (undoManager.canUndo()) {
             undoManager.undo();
         }
	    }
	 
	  
	 
	public void redo(UndoManager  undoManager) {
		 System.out.println("Working");
	        if (undoManager.canRedo()) {
	            undoManager.redo();
	        }
	    }
}
