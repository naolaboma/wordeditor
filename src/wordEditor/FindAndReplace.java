package wordEditor;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.text.BadLocationException;
import javax.swing.text.DefaultHighlighter;
import javax.swing.text.Highlighter;

public class FindAndReplace extends JDialog {
	 private Highlighter.HighlightPainter highlightPainter;
	 public FindAndReplace() {
		 highlightPainter = new DefaultHighlighter.DefaultHighlightPainter(Color.YELLOW);
	 }

	 public void findAndReplace(JTextPane textPane) {
		 JDialog findReplaceDialog = new JDialog(this, "Find/Replace");
         findReplaceDialog.setLayout(new GridLayout(3, 2));
         JTextField findTextField = new JTextField(20);
         JTextField replaceTextField = new JTextField(20);
         JButton findButton = new JButton("Find");
         JButton replaceButton = new JButton("Replace");

         // add action listeners to the find and replace buttons
         findButton.addActionListener(new ActionListener() {
             @Override
             public void actionPerformed(ActionEvent e) {
                 String findText = findTextField.getText();

                 // remove any existing highlights
                 textPane.getHighlighter().removeAllHighlights();

                 // find the first occurrence of the search text
                 Pattern pattern = Pattern.compile(findText);
                 Matcher matcher = pattern.matcher(textPane.getText());
                 if (matcher.find()) {
                     try {
                         // highlight the matched text
                         int matchStart = matcher.start();
                         int matchEnd = matcher.end();
                         textPane.getHighlighter().addHighlight(matchStart, matchEnd, highlightPainter);
                         // move the selection to the matched text
                         textPane.setSelectionStart(matchStart);
                         textPane.setSelectionEnd(matchEnd);
                     } catch (BadLocationException ex) {
                         ex.printStackTrace();
                     }
                 }
             }
         });

         replaceButton.addActionListener(new ActionListener() {
             @Override
             public void actionPerformed(ActionEvent e) {
                 String findText = findTextField.getText();
                 String replaceText = replaceTextField.getText();

                 // remove any existing highlights
                 textPane.getHighlighter().removeAllHighlights();

                 // find and replace all occurrences of the search text
                 String text = textPane.getText();
                 text = text.replaceAll(findText, replaceText);
                 textPane.setText(text);
             }
         });

         // add the components to the dialog box
         findReplaceDialog.add(new JLabel("Find:"));
         findReplaceDialog.add(findTextField);
         findReplaceDialog.add(new JLabel("Replace with:"));
         findReplaceDialog.add(replaceTextField);
         findReplaceDialog.add(findButton);
         findReplaceDialog.add(replaceButton);

         // set the size of the dialog box and display it
         findReplaceDialog.pack();
         findReplaceDialog.setVisible(true);
     
	 }
	  
	}


