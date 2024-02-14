 package wordEditor;

import javax.swing.*;
import javax.swing.text.BadLocationException;
import javax.swing.text.DefaultHighlighter;
import javax.swing.text.Document;
import javax.swing.text.Highlighter;
import javax.swing.text.MutableAttributeSet;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledEditorKit;
import javax.swing.text.rtf.RTFEditorKit;
import javax.swing.undo.UndoManager;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class App extends JFrame implements ActionListener {

    // ======containers
    private Container container;
    private JTextPane text;
    private JMenuBar menuBar;
    private JPanel mainContainer;
    private JPanel quickAccessBar;
    private JPanel toolsBar;
    private JPanel clipBoard;
    private JPanel styles;
    private JPanel paragraph;
    private JPanel clipItems;
    private JPanel paraItems;
    private JPanel styleItems;
   
    
    //===file 
    private JMenu file;
	private JMenuItem New, open, close, save, save_as, print, exit;
					  

    //====labels
    JLabel styleL;
    JLabel clipBoardL;
    JLabel paraL;
    
    //===quick AccesstoolBars Button
    private JButton undoButton= new JButton(Model.getModel().getCustomImage().resizeImg("./images/undo.png"));
	private JButton redoButton= new JButton( Model.getModel().getCustomImage().resizeImg("./images/redo.png"));
    private JButton newButton= new JButton(Model.getModel().getCustomImage().resizeImg("./images/new.png"));
    private JButton openButton= new JButton(Model.getModel().getCustomImage().resizeImg("./images/save.png"));
	private JButton saveButton= new JButton(Model.getModel().getCustomImage().resizeImg("./images/open.png"));  
	private JButton printButton= new JButton(Model.getModel().getCustomImage().resizeImg("./images/print.png"));
	private JButton saveAsButton= new JButton(Model.getModel().getCustomImage().resizeImg("./images/save.png"));
	private JButton exitButton= new JButton(Model.getModel().getCustomImage().resizeImg("./images/exit.png"));
	 
	//===clipBoard buttons
	private JButton cutButton= new JButton(Model.getModel().getCustomImage().resizeImg("./images/cut.png"));
	private JButton copyButton= new JButton( Model.getModel().getCustomImage().resizeImg("./images/copy.png"));
	private JButton pasteButton= new JButton(Model.getModel().getCustomImage().resizeImg("./images/paste.png"));
	private JButton findReplace= new JButton("FindAndReplace");
   
	//====styles button
	private JButton textColor=new JButton(Model.getModel().getCustomImage().resizeImg("./images/color.jfif"));
	private JButton bgColor=new JButton(Model.getModel().getCustomImage().resizeImg("./images/bgColor.png"));
	private JToggleButton boldButton=new JToggleButton(new StyledEditorKit.BoldAction());
	private JToggleButton underlineButton=new JToggleButton(new StyledEditorKit.UnderlineAction());
	private JToggleButton italicButton=new JToggleButton(new StyledEditorKit.ItalicAction());
	private String fontFamilies[]={"Monospaced","Times New Roman","Courier","Tahoma","MS Serif",
				"Andalus","Monotype Koufi","Simplified Arabic"};
	private JComboBox fontFamily=new JComboBox(fontFamilies);
	private String fontSizes[]={"10","12","14","16","18","20","22","24","26",
				"28","32","36","40","46","52","60","72"};
    private JComboBox  fontSize=new JComboBox(fontSizes);
	   
    //====paragraph buttons
	JButton alignLeft= new JButton(Model.getModel().getCustomImage().resizeImg("./images/leftAlign.png"));
    JButton center = new JButton(Model.getModel().getCustomImage().resizeImg("./images/centerAlign.png"));
    JButton alignRight  = new JButton(Model.getModel().getCustomImage().resizeImg("./images/rightAlign.png"));
    JButton justify = new JButton(Model.getModel().getCustomImage().resizeImg("./images/justify.png"));
    JButton lineSpacing = new JButton(Model.getModel().getCustomImage().resizeImg("./images/lineSpacing.png"));
    JButton paraSpacing = new JButton(Model.getModel().getCustomImage().resizeImg("./images/paragraphSpacing.png"));
     
	   //=====color
	   
    private FlowLayout flow=new FlowLayout(FlowLayout.LEFT,0,0);
    private RTFEditorKit reachTextField=new RTFEditorKit();
    private int appFontS=12;
    private String appFontF="Times New Roman";
    private UndoManager undoManager;

    
    private JButton findReplaceButton;
    private Highlighter.HighlightPainter highlightPainter;

    public App() {
        super("Word Processing");
       
       // ====== Default look and feel of the system
     			try
     			{
     				UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
     			}
     			catch(Exception ex)
     			{
     				JOptionPane.showMessageDialog(null,"Cann'      t set look and feel",
     					"Error",JOptionPane.ERROR_MESSAGE);
     			}
     			
		//====containers
        text = new JTextPane();
        menuBar = new JMenuBar();
        mainContainer = new JPanel(new BorderLayout());
        quickAccessBar = new JPanel();
        toolsBar = new JPanel(new BorderLayout());
        clipBoard=new JPanel(new BorderLayout());
        styles=new JPanel(new BorderLayout());
        paragraph=new JPanel(new BorderLayout());
        clipItems=new JPanel();
        paraItems=new JPanel();
        styleItems=new JPanel();
        undoManager=new UndoManager();
        //====
        styleL=new JLabel("Styles");
        clipBoardL=new JLabel("Clipboard");
        paraL=new JLabel("Paragraph");


        findReplaceButton = new JButton("Find/Replace");

        // create the highlighter for matching text
        highlightPainter = new DefaultHighlighter.DefaultHighlightPainter(Color.YELLOW);
 
        //=====adding container to its top container
        container = getContentPane();
        container.setLayout(new BorderLayout());
        container.add(mainContainer, BorderLayout.NORTH);
        container.add(text,BorderLayout.CENTER);
        container.add(new JScrollPane(text), BorderLayout.CENTER);
        setSize(700,700);
        //====add border to container
		mainContainer.setBorder(BorderFactory.createEtchedBorder());
	 	text.setEditorKit(reachTextField);
		text.setBorder(BorderFactory.createEtchedBorder());
       
		//======file bar
		 New= new JMenuItem("New",new ImageIcon("new.png"));
	     open= new JMenuItem("Open", new ImageIcon("new.png"));
	     close= new JMenuItem("Close", new ImageIcon("new.png"));
		 save= new JMenuItem("Save", new ImageIcon("new.png"));
		 save_as= new JMenuItem("Save As", new ImageIcon("new.png"));
		 print= new JMenuItem("Print", new ImageIcon("new.png"));
		 exit= new JMenuItem("Exit", new ImageIcon("new.png"));
		//==add item to file menu
	    file= new JMenu("File");
	    file.add(New);
	    file.add(open);
	    file.add(close);
	    file.addSeparator();
	    file.add(save);
	    file.add(save_as);
	    file.addSeparator();
	    file.add(print);
	    file.addSeparator();
	    file.add(exit);
	    setJMenuBar(menuBar);
	    menuBar.add(file);
	 
		New.addActionListener((e)->Model.getModel().getCustomFile().NEW_FILE(text)); 
		open.addActionListener((e)->Model.getModel().getCustomFile().OPEN_FILE(text));
		close.addActionListener((e)->Model.getModel().getCustomFile().CLOSE_FILE(text));
		save.addActionListener((e)->Model.getModel().getCustomFile().SAVE_FILE(text));
		save_as.addActionListener((e)->Model.getModel().getCustomFile().SAVE_AS_FILE(text));
	    print.addActionListener((e)->new Print(text).peintComponent(text));
        exit.addActionListener((e)->Model.getModel().getCustomFile().EXIT_PROGRAM(text));
	  
        
        
        //===add items to main container
        mainContainer.add(quickAccessBar, BorderLayout.NORTH);
        mainContainer.add(toolsBar, BorderLayout.SOUTH);
        
        //====add items to quickAcceesBar
        quickAccessBar.setLayout(flow);
        quickAccessBar.add(undoButton);
        quickAccessBar.add(redoButton);
        quickAccessBar.add(newButton);
        quickAccessBar.add(openButton);
        quickAccessBar.add(saveButton);
        quickAccessBar.add(printButton);
       // quickAccessBar.add(saveAsButton);
        
        
        //=====action listener to quickAccesBar button
          newButton.addActionListener((e)->Model.getModel().getCustomFile().NEW_FILE(text)); 
	      openButton.addActionListener((e)->Model.getModel().getCustomFile().OPEN_FILE(text));
	      saveButton.addActionListener((e)->Model.getModel().getCustomFile().SAVE_FILE(text));
		  printButton.addActionListener( (e)->new Print(text).peintComponent(text));
		  cutButton.addActionListener((e)->Model.getModel().getCustomEdit().cut(text));
		  copyButton.addActionListener((e)->Model.getModel().getCustomEdit().copy(text));
		  pasteButton.addActionListener((e)->Model.getModel().getCustomEdit().paste(text));
		  undoButton.addActionListener(e -> {
	            if (undoManager.canUndo()) {
	                undoManager.undo();
	            }
	        });
		  Document doc = text.getDocument();
	      doc.addUndoableEditListener(undoManager);

		  redoButton.addActionListener( e -> {
	            if (undoManager.canRedo()) {
	            	 undoManager.redo();
	            }
	        });
		
        
      
        //====add items to toolsBar
        toolsBar.add(clipBoard,BorderLayout.WEST);
        toolsBar.add(styles,BorderLayout.CENTER);
        toolsBar.add(paragraph,BorderLayout.EAST);
        
        //====add items to clipboard
        clipBoard.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 2, Color.BLACK));
        clipBoard.add(clipItems,BorderLayout.NORTH);
        clipBoard.add(clipBoardL,BorderLayout.SOUTH);
        
        //======add items to clip items
        clipItems.setLayout(new GridLayout(1, 4, 5, 0));
        clipItems.setBorder(BorderFactory.createEmptyBorder(3, 10, 1, 10));
        clipItems.add(cutButton);
        clipItems.add(copyButton);
        clipItems.add(pasteButton);
        clipItems.add(findReplaceButton);
        
        //====action listener to clipBoard
          ImageIcon f=new ImageIcon("./images/find.png");
          cutButton.addActionListener((e)->Model.getModel().getCustomEdit().cut(text));
	      copyButton.addActionListener((e)->Model.getModel().getCustomEdit().copy(text));
	      pasteButton.addActionListener((e)->Model.getModel().getCustomEdit().copy(text));
	      findReplaceButton.addActionListener((e)->new FindAndReplace().findAndReplace(text));
	      findReplaceButton.setIcon(Model.getModel().getCustomImage().resizeImg("./images/find.png"));
	    
        
        //====add items to styles
	    styles.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 2, Color.GRAY));
        styles.add(styleItems,BorderLayout.NORTH);
        styles.add(styleL,BorderLayout.CENTER);
        
        //====add items to style Items
        styleItems.setLayout(new GridLayout(1, 6, 5, 0));
        styleItems.setBorder(BorderFactory.createEmptyBorder(3, 10, 1, 10));
        styleItems.add(bgColor);
        styleItems.add(textColor);
        styleItems.add(fontFamily);
        styleItems.add(fontSize);
        styleItems.add(italicButton);
        styleItems.add(underlineButton);
        styleItems.add(boldButton);
        //===add action listener to style button
         bgColor.addActionListener((e)->Model.getModel().getCustomColor().AddCustomBackground(text));
		 textColor.addActionListener((e)->Model.getModel().getCustomColor().AddCustomForeGround(text));
		 fontSize.addItemListener(new ItemListener()
			{
				public void itemStateChanged(ItemEvent event)
				{
					int index=fontSize.getSelectedIndex();
					appFontS=Integer.parseInt(fontSizes[index]);
					MutableAttributeSet attr=new SimpleAttributeSet();
					StyleConstants.setFontSize(attr,appFontS);
					text.setCharacterAttributes(attr,false);
					//text.setFont(new Font(font_name_i,font_style,font_size_i));
					text.requestFocus();
				}
			});
		  fontFamily.addItemListener(new ItemListener()
			{
				public void itemStateChanged(ItemEvent event)
				{
					int index=fontFamily.getSelectedIndex();
					appFontF=fontFamilies[index];
					//font_item[index].setSelected(true);
					MutableAttributeSet attr=new SimpleAttributeSet();
					StyleConstants.setFontFamily(attr,appFontF);
					text.setCharacterAttributes(attr,false);
					//text.setFont(new Font(font_name_i,font_style,font_size_i));
					text.requestFocus();
				}
			});
		   
		  
		  boldButton.addActionListener((e)->Model.getModel().getCustomStyle().bold());
	      italicButton.addActionListener((e)->Model.getModel().getCustomStyle().italic());
	      underlineButton.addActionListener((e)->Model.getModel().getCustomStyle().underline());
	      italicButton.setIcon(Model.getModel().getCustomImage().resizeImg("./images/italic.png"));
	      underlineButton.setIcon(Model.getModel().getCustomImage().resizeImg("./images/underline.png"));
	      boldButton.setIcon(Model.getModel().getCustomImage().resizeImg("./images/bold.png"));
	    
         
        //======add items to paragraph
        paragraph.add(paraItems,BorderLayout.NORTH);
        paragraph.add(paraL,BorderLayout.SOUTH);
       
        //====add items to paraItems
        paraItems.setLayout(new GridLayout(1, 10, 5, 0));
        paraItems.setBorder(BorderFactory.createEmptyBorder(3, 10, 1, 10));
        paraItems.add(alignLeft);
        paraItems.add(alignRight);
        paraItems.add(center);
        paraItems.add(justify);
        paraItems.add(lineSpacing);
        paraItems.add(paraSpacing);
        
        //=====add action listener to para buttons
          alignLeft.addActionListener((e)->Model.getModel().getCustomParagraph().alignLeft(text));
          center.addActionListener((e)->Model.getModel().getCustomParagraph().alignCenter(text));
          alignRight.addActionListener((e)->Model.getModel().getCustomParagraph().alignRight(text));
          justify.addActionListener((e)->Model.getModel().getCustomParagraph().justify(text));
          lineSpacing.addActionListener((e)->Model.getModel().getCustomParagraph().setLineSpacing(text));
          paraSpacing .addActionListener((e)->Model.getModel().getCustomParagraph().setLineSpacing(text));
          
         
        //==========Style and ToolTips
       
		  JButton quickButtons[]= {newButton,openButton,saveButton, printButton ,copyButton,pasteButton,undoButton,redoButton,bgColor,textColor,alignLeft, center,alignRight,  justify,lineSpacing,paraSpacing};
		  Model.getModel().getCustomCss().styleButton(quickButtons);
		  
		 String[] texts= {"new","open","save","print" ,"copy","paste","undo","redo","Background color","foreground color","alignLeft","center","alignRight","justify","lineSpacing","paraSpacing"};
		  Model.getModel().getAddTips().addToolTip(quickButtons, texts);
		 
		  JButton clipButtons[]= {findReplaceButton};
		  Model.getModel().getCustomCss().styleButton(clipButtons);
		  
		//======add toolTips
		  String[] ctext= {"Find and replace"};
		  Model.getModel().getAddTips().addToolTip(clipButtons,ctext);
		  
		//=====style buttons
		  JToggleButton styleButtons[]= {italicButton,underlineButton,boldButton};
		  Model.getModel().getCustomCss().styleButton(styleButtons);
		  
		//======add toolTips
		  String[] stext= {"italic","underline","bold" };
		  Model.getModel().getAddTips().addToolTip(styleButtons, stext);
         
        
         
          
        //===make text area disable by default
          SetDisable_JTextPane();
			 

      	//========= Default Close From Windows =========
      	  addWindowListener(new WindowAdapter()
      			{
      				public void windowClosing(WindowEvent e)
      				{
      					 Model.getModel().getCustomFile().EXIT_PROGRAM(text);
      					
      				}
      			});
      	  setSize(1320,800);
    }

    public static void main(String[] args) {
        App wordApp = new App();
        wordApp.setVisible(true);
        wordApp.setLocation(10,10);
		wordApp.setResizable(true);
		wordApp.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    // Remove the second actionPerformed method
    @Override
    public void actionPerformed(ActionEvent e) {
    }
    
    public void SetEnable_JTextPane()
	{
		text.setEditable(true);
		text.requestFocus();
	}
 
	public void SetDisable_JTextPane()
	{
		text.setEditable(false);
	}
	
	 
}
