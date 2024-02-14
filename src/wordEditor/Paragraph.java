package wordEditor;

import javax.swing.JOptionPane;
import javax.swing.JTextPane;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;

public class Paragraph {
	public void alignLeft(JTextPane text) {
        SimpleAttributeSet attributes = new SimpleAttributeSet();
        StyleConstants.setAlignment(attributes, StyleConstants.ALIGN_LEFT);
        int start = text.getSelectionStart();
        int end = text.getSelectionEnd();
        text.getStyledDocument().setParagraphAttributes(start, end - start, attributes, false);
  
	}
    

    public void alignRight(JTextPane text) {
        StyledDocument doc = (StyledDocument) text.getDocument();
        SimpleAttributeSet right = new SimpleAttributeSet();
        StyleConstants.setAlignment(right, StyleConstants.ALIGN_RIGHT);
        doc.setParagraphAttributes(0, doc.getLength(), right, false);
    }

    public void justify(JTextPane text) {
        text.setEditable(false);
        
        // Set the text alignment to justify
        StyledDocument doc = text.getStyledDocument();
        SimpleAttributeSet align = new SimpleAttributeSet();
        StyleConstants.setAlignment(align, StyleConstants.ALIGN_JUSTIFIED);
        doc.setParagraphAttributes(0, doc.getLength(), align, false);
        
    }
    public void alignCenter(JTextPane text) {
    	// Get the line spacing value from the user
        String lineSpacingInput = JOptionPane.showInputDialog(null, "Enter line spacing value (e.g. 1.0):");
        if (lineSpacingInput == null) {
            // User clicked "Cancel"
            return;
        }
        float lineSpacingValue = Float.parseFloat(lineSpacingInput);

        // Set the line spacing
        StyledDocument doc = text.getStyledDocument();
        SimpleAttributeSet lineSpacing = new SimpleAttributeSet();
        StyleConstants.setLineSpacing(lineSpacing, lineSpacingValue);
        doc.setParagraphAttributes(0, doc.getLength(), lineSpacing, false);
    }
    
   
    

    public void setLineSpacing(JTextPane text) {
    	System.out.println("funy and go");
    	String lineSpacingInput = JOptionPane.showInputDialog(null, "Enter line spacing value (e.g. 1.0):");
        if (lineSpacingInput == null) {
            // User clicked "Cancel"
            System.exit(0);
        }
        float lineSpacingValue = Float.parseFloat(lineSpacingInput);
        
        // Set the line spacing
        StyledDocument doc = text.getStyledDocument();
        SimpleAttributeSet lineSpacing = new SimpleAttributeSet();
        StyleConstants.setLineSpacing(lineSpacing, lineSpacingValue);
        doc.setParagraphAttributes(0, doc.getLength(), lineSpacing, false);
        
        
    }
    public void setParaSpacing(JTextPane text) {
    	String paragraphSpacingInput = JOptionPane.showInputDialog(null, "Enter paragraph spacing value (e.g. 10):");
        if (paragraphSpacingInput == null) {
            // User clicked "Cancel"
            return;
        }
        int paragraphSpacingValue = Integer.parseInt(paragraphSpacingInput);

        // Set the paragraph spacing
        StyledDocument doc = text.getStyledDocument();
        SimpleAttributeSet paragraphSpacing = new SimpleAttributeSet();
        StyleConstants.setSpaceBelow(paragraphSpacing, paragraphSpacingValue);
        doc.setParagraphAttributes(0, doc.getLength(), paragraphSpacing, false);
        
        
    }
    
    public void setIndent(JTextPane text) {
    	// Get the indentation value from the user
        String indentationInput = JOptionPane.showInputDialog(null, "Enter indentation value (e.g. 50):");
        if (indentationInput == null) {
            // User clicked "Cancel"
            return;
        }
        int indentationValue = Integer.parseInt(indentationInput);

        // Indent the text
        StyledDocument doc = text.getStyledDocument();
        SimpleAttributeSet indentation = new SimpleAttributeSet();
        StyleConstants.setLeftIndent(indentation, indentationValue);
        doc.setParagraphAttributes(0, doc.getLength(), indentation, false);
        
        
        
    }
    
    public void setOutdent(JTextPane text) {
    	// Get the indentation value from the user
        String indentationInput = JOptionPane.showInputDialog(null, "Enter indentation value (e.g. 50):");
        if (indentationInput == null) {
            // User clicked "Cancel"
            return;
        }
        int indentationValue = Integer.parseInt(indentationInput);

        // Indent the text
        StyledDocument doc = text.getStyledDocument();
        SimpleAttributeSet indentation = new SimpleAttributeSet();
        StyleConstants.setLeftIndent(indentation, indentationValue);
        doc.setParagraphAttributes(0, doc.getLength(), indentation, false);
        
        
        
    }
    
    
    
}
