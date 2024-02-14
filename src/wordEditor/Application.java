package wordEditor;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.text.*;
import javax.swing.undo.UndoManager;
import java.awt.*;
import java.awt.event.*;
import java.util.Scanner;

public class Application extends JFrame implements ActionListener {
    private Container container;
    private JTextPane text;
    private JMenuBar menuBar;
    private JSpinner fSize;

    private JPanel mainContainer, quickAccessBar, toolsBar, clipBoard, styles, paragraph, clipItems, paraItems, styleItems;
    private JMenu file;
    private JMenuItem New, open, save, save_as, exit;
    private JLabel styleLabel, clipBoardLabel, paragraphLabel;

    private JButton undoButton = new JButton(Model.getModel().getCustomImage().resizeImg("./images/undo.jpeg"));
    private JButton redoButton = new JButton(Model.getModel().getCustomImage().resizeImg("./images/redo.jpeg"));
    private JButton newButton = new JButton(Model.getModel().getCustomImage().resizeImg("./images/new2.jfif"));
    private JButton openButton = new JButton(Model.getModel().getCustomImage().resizeImg("./images/open.png"));
    private JButton saveButton = new JButton(Model.getModel().getCustomImage().resizeImg("./images/save.png"));
    private JButton cutButton = new JButton(Model.getModel().getCustomImage().resizeImg("./images/cut.png"));
    private JButton copyButton = new JButton(Model.getModel().getCustomImage().resizeImg("./images/copy.png"));
    private JButton pasteButton = new JButton(Model.getModel().getCustomImage().resizeImg("./images/paste.png"));
    private JButton WordButton;
    private JTextField counter;
    private JButton findReplace = new JButton("Find And Replace");

    private JButton findReplaceButton;

    ImageIcon originalIcon = new ImageIcon("./images/color.png");
    Image originalImage = originalIcon.getImage();
    int desiredWidth = 70;
    int desiredHeight = 50;
    Image resizedImage = originalImage.getScaledInstance(desiredWidth, desiredHeight, Image.SCALE_SMOOTH);
    ImageIcon resizedIcon = new ImageIcon(resizedImage);
    JButton textColor = new JButton(resizedIcon);

    ImageIcon originalIcon2 = new ImageIcon("./images/bgColor.png");
    Image originalImage2 = originalIcon2.getImage();
    int desiredWidth2 = 50;
    int desiredHeight2 = 30;
    Image resizedImage2 = originalImage2.getScaledInstance(desiredWidth2, desiredHeight2, Image.SCALE_SMOOTH);
    ImageIcon resizedIcon2 = new ImageIcon(resizedImage2);
    JButton bgColor = new JButton(resizedIcon2);

    private JToggleButton boldButton = new JToggleButton(new StyledEditorKit.BoldAction());
    private JToggleButton underlineButton = new JToggleButton(new StyledEditorKit.UnderlineAction());
    private JToggleButton italicButton = new JToggleButton(new StyledEditorKit.ItalicAction());
    private String[] fontStyle = GraphicsEnvironment.getLocalGraphicsEnvironment().getAvailableFontFamilyNames();
    private JComboBox fontFamily = new JComboBox(fontStyle);

    private final int MIN_FONT_SIZE = 11;
    private final int MAX_FONT_SIZE = 90;
    private final int STEP_SIZE = 1;
    private String[] generateFontSizes() {
        int sizeCount = (MAX_FONT_SIZE - MIN_FONT_SIZE) / STEP_SIZE + 1;
        String[] fontSizes = new String[sizeCount];
        for (int i = 0, fontSize = MIN_FONT_SIZE; i < sizeCount; i++, fontSize += STEP_SIZE) {
            fontSizes[i] = String.valueOf(fontSize);
        }
        return fontSizes;
    }
    private String fontSizes[] = generateFontSizes();
    private JComboBox fontSize = new JComboBox(fontSizes);

    JButton alignLeft = new JButton(Model.getModel().getCustomImage().resizeImg("./images/leftAlign.png"));
    JButton center = new JButton(Model.getModel().getCustomImage().resizeImg("./images/centerAlign.png"));
    JButton alignRight = new JButton(Model.getModel().getCustomImage().resizeImg("./images/rightAlign.png"));

    JButton lineSpacing = new JButton(Model.getModel().getCustomImage().resizeImg("./images/lineSpacing.png"));
    JButton paragraphSpacing = new JButton(Model.getModel().getCustomImage().resizeImg("./images/paragraphSpacing.png"));
    JButton paraIndent = new JButton(Model.getModel().getCustomImage().resizeImg("./images/indent.png"));
    private int applicationFontSize;
    private String appFontF;
    private UndoManager undoManager;
    public Application() {
        super("WORD TEXT EDITOR");
        Image iconImage = Toolkit.getDefaultToolkit().getImage("./images/TITLE.png");
        super.setIconImage(iconImage);

        text = new JTextPane();
        menuBar = new JMenuBar();
        mainContainer = new JPanel(new BorderLayout());
        quickAccessBar = new JPanel();
        toolsBar = new JPanel(new BorderLayout());
        clipBoard = new JPanel(new BorderLayout());
        styles = new JPanel(new BorderLayout());
        paragraph = new JPanel(new BorderLayout());
        clipItems = new JPanel();
        paraItems = new JPanel();
        styleItems = new JPanel();
        undoManager = new UndoManager();
        styleLabel = new JLabel("    Styles");
        clipBoardLabel = new JLabel("    Clipboard");
        paragraphLabel = new JLabel("    Paragraph");
        findReplaceButton = new JButton();
        findReplaceButton.setIcon(Model.getModel().getCustomImage().resizeImg("./images/find.png"));
        container = getContentPane();
        container.setLayout(new BorderLayout());

        WordButton = new JButton();
        WordButton.setText("Word Counter:");
        WordButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String txt = text.getText();
                try (Scanner sc = new Scanner(txt)) {
                    int count = 0;
                    while (sc.hasNext()) {
                        sc.next();
                        count++;
                    }
                    counter.setText(" " + count);
                }
            }
        });

        counter = new JTextField(20);
        JPanel footerPanel = new JPanel();
        footerPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        JLabel footerLabel = new JLabel("Comment");
        JTextField footerTextField = new JTextField(20);
        footerPanel.add(WordButton);
        footerPanel.add(counter);
        footerPanel.add(footerLabel);
        footerPanel.add(footerTextField);
        add(footerPanel, BorderLayout.SOUTH);

        JScrollPane scrollPane;
        scrollPane = new JScrollPane();
        scrollPane.setPreferredSize(new Dimension(1300, 480));
        scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.setViewportView(text);
        container.add(scrollPane, BorderLayout.CENTER);
        container.add(mainContainer, BorderLayout.NORTH);

        setSize(700, 950);

        // Menu Items
        New = new JMenuItem("New");
        open = new JMenuItem("Open");
        save = new JMenuItem("Save");
        save_as = new JMenuItem("Save As");
        exit = new JMenuItem("Exit");

        file = new JMenu("File");

        file.add(New);
        file.addSeparator();
        file.add(open);
        file.addSeparator();
        file.addSeparator();
        file.add(save);
        file.addSeparator();
        file.add(save_as);
        file.addSeparator();
        file.addSeparator();
        file.add(exit);

        setJMenuBar(menuBar);
        menuBar.add(file);

        Font menuFont = menuBar.getFont();
        menuBar.setFont(menuFont.deriveFont(18.0f));
        menuBar.setFont(new Font("Arial", Font.BOLD, 16));  // Set custom font
        menuBar.setForeground(Color.WHITE);  // Set custom foreground color
        menuBar.setBackground(Color.GREEN);  // Set custom background color
        menuBar.setBorder(BorderFactory.createLineBorder(Color.BLUE));

        New.addActionListener((e) -> Model.getModel().getCustomFile().NEW_FILE(text));
        open.addActionListener((e) -> Model.getModel().getCustomFile().OPEN_FILE(text));
        save.addActionListener((e) -> Model.getModel().getCustomFile().SAVE_FILE(text));
        save_as.addActionListener((e) -> Model.getModel().getCustomFile().SAVE_AS_FILE(text));
        exit.addActionListener((e) -> Model.getModel().getCustomFile().EXIT_PROGRAM(text));

        mainContainer.add(quickAccessBar, BorderLayout.NORTH);
        mainContainer.add(toolsBar, BorderLayout.SOUTH);
        quickAccessBar.setBorder(BorderFactory.createMatteBorder(0, 1, 0, 1, Color.BLACK));

        quickAccessBar.setLayout(new FlowLayout(FlowLayout.LEFT, 6, 4));
        JPanel gapPanel = new JPanel();
        gapPanel.setPreferredSize(new Dimension(10, 10));
        quickAccessBar.add(undoButton);
        quickAccessBar.add(redoButton);
        quickAccessBar.add(newButton);
        quickAccessBar.add(openButton);
        quickAccessBar.add(saveButton);

        newButton.addActionListener((e) -> Model.getModel().getCustomFile().NEW_FILE(text));
        openButton.addActionListener((e) -> Model.getModel().getCustomFile().OPEN_FILE(text));
        saveButton.addActionListener((e) -> Model.getModel().getCustomFile().SAVE_FILE(text));
        undoButton.addActionListener(e -> {
            if (undoManager.canUndo()) {
                undoManager.undo();
            }
        });
        Document doc = text.getDocument();
        doc.addUndoableEditListener(undoManager);

        redoButton.addActionListener(e -> {
            if (undoManager.canRedo()) {
                undoManager.redo();
            }
        });

// Add items to toolsBar
        toolsBar.add(clipBoard, BorderLayout.WEST);
        toolsBar.add(styles, BorderLayout.CENTER);
        toolsBar.add(paragraph, BorderLayout.EAST);

        clipBoard.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 0, Color.BLACK));
        clipBoard.add(clipItems, BorderLayout.SOUTH);
        clipBoard.add(clipBoardLabel, BorderLayout.NORTH);

        clipItems.setLayout(new GridLayout(1, 4, 5, 0));
        clipItems.setBorder(BorderFactory.createEmptyBorder(3, 10, 1, 10));
        clipItems.add(cutButton);
        clipItems.add(copyButton);
        clipItems.add(pasteButton);
        clipItems.add(findReplaceButton);

        //Add action listener for clipItems
        cutButton.addActionListener((e) -> Model.getModel().getCustomEdit().cut(text));
        copyButton.addActionListener((e) -> Model.getModel().getCustomEdit().copy(text));
        pasteButton.addActionListener((e) -> Model.getModel().getCustomEdit().paste(text));
        findReplaceButton.addActionListener((e) -> new FindAndReplace().findAndReplace(text));

        //Styles
        styles.setBorder(BorderFactory.createMatteBorder(1, 5, 1, 5, Color.BLACK));
        styles.add(styleItems, BorderLayout.CENTER);
        styles.add(styleLabel, BorderLayout.NORTH);

        fSize = new JSpinner();
        fSize.setValue(11);
        fSize.addChangeListener(new ChangeListener(){
            @Override
            public void stateChanged(ChangeEvent e) {
                text.setFont(new Font(text.getFont().getFamily(),Font.PLAIN,(int)fSize.getValue()));
            }
        });

        styleItems.setLayout(new GridLayout(1, 6, 5, 0));
        styleItems.setBorder(BorderFactory.createEmptyBorder(3, 10, 1, 10));

        styleItems.add(bgColor);
        styleItems.add(textColor);
        styleItems.add(fontFamily);
        styleItems.add(fontSize);
        styleItems.add(fSize);
        styleItems.add(italicButton);
        styleItems.add(underlineButton);
        styleItems.add(boldButton);

        bgColor.addActionListener((e) -> Model.getModel().getCustomColor().AddCustomBackground(text));
        textColor.addActionListener((e) -> Model.getModel().getCustomColor().AddCustomForeGround(text));
        fontSize.addItemListener(new ItemListener() {
            public void itemStateChanged(ItemEvent event) {
                int index = fontSize.getSelectedIndex();
                applicationFontSize = Integer.parseInt(fontSizes[index]);
                MutableAttributeSet attr = new SimpleAttributeSet();
                StyleConstants.setFontSize(attr, applicationFontSize);
                text.setCharacterAttributes(attr, false);
                //text.setFont(new Font(font_name_i,font_style,font_size_i));
                text.requestFocus();
            }
        });
        fontFamily.addItemListener(new ItemListener() {
            public void itemStateChanged(ItemEvent event) {
                int index = fontFamily.getSelectedIndex();
                appFontF = fontStyle[index];
                //font_item[index].setSelected(true);
                MutableAttributeSet attr = new SimpleAttributeSet();
                StyleConstants.setFontFamily(attr, appFontF);
                text.setCharacterAttributes(attr, false);
                //text.setFont(new Font(font_name_i,font_style,font_size_i));
                text.requestFocus();
            }
        });

        //italicButton.setIcon(Model.getModel().getCustomImage().resizeImg("./images/italic.png"));
        ImageIcon italIcon = new ImageIcon("./images/italic.png");
        Image uImage = italIcon.getImage().getScaledInstance(10,10, Image.SCALE_SMOOTH);
        italicButton.setIcon(new ImageIcon(uImage));

        //underlineButton.setIcon(Model.getModel().getCustomImage().resizeImg("./images/underline.png"));
        ImageIcon uIcon = new ImageIcon("./images/underline.png");
        Image unlImage = uIcon.getImage().getScaledInstance(10,10, Image.SCALE_SMOOTH);
        underlineButton.setIcon(new ImageIcon(unlImage));

        //boldButton.setIcon(Model.getModel().getCustomImage().resizeImg("./images/bold.png"));

        ImageIcon boldIcon = new ImageIcon("./images/bold.png");
        Image boldImage = boldIcon.getImage().getScaledInstance(10, 10, Image.SCALE_SMOOTH);
        boldButton.setIcon(new ImageIcon(boldImage));
        boldButton.addActionListener((e) -> Model.getModel().getCustomStyle().bold());
        italicButton.addActionListener((e) -> Model.getModel().getCustomStyle().italic());
        underlineButton.addActionListener((e) -> Model.getModel().getCustomStyle().underline());

        //======add items to paragraph
        paragraph.add(paraItems, BorderLayout.SOUTH);
        paragraph.add(paragraphLabel, BorderLayout.NORTH);
        paragraph.setBorder(BorderFactory.createMatteBorder(1, 0, 1, 1, Color.BLACK));

        //====add items to paraItems
        paraItems.setLayout(new GridLayout(1, 10, 5, 0));
        paraItems.setBorder(BorderFactory.createEmptyBorder(3, 10, 1, 10));
        paraItems.add(alignLeft);
        paraItems.add(alignRight);
        paraItems.add(center);
        paraItems.add(lineSpacing);
        paraItems.add(paragraphSpacing);
        paraItems.add(paraIndent);


        //=====add action listener to para buttons
        alignLeft.addActionListener((e) -> Model.getModel().getCustomParagraph().alignLeft(text));
        center.addActionListener((e) -> Model.getModel().getCustomParagraph().alignCenter(text));
        alignRight.addActionListener((e) -> Model.getModel().getCustomParagraph().alignRight(text));
        lineSpacing.addActionListener((e) -> Model.getModel().getCustomParagraph().setLineSpacing(text));
        paragraphSpacing.addActionListener((e) -> Model.getModel().getCustomParagraph().setParaSpacing(text));
        paraIndent.addActionListener((e) -> Model.getModel().getCustomParagraph().setIndent(text));

        //==========Style and ToolTips

        JButton quickButtons[] = {newButton, openButton, saveButton, copyButton, pasteButton, undoButton, redoButton, bgColor, textColor, alignLeft, center, alignRight, lineSpacing, paragraphSpacing, paraIndent};
        Model.getModel().getCustomCss().styleButton(quickButtons);

        String[] texts = {"New", "Open", "Save", "Copy", "Paste", "Undo", "Redo", "Background Color", "Foreground Color", "Align Left", "Center", "AlignRight", "LineSpacing", "ParaSpacing", "Indent" };
        Model.getModel().getAddTips().addToolTip(quickButtons, texts);

        JButton clipButtons[] = {findReplaceButton};
        Model.getModel().getCustomCss().styleButton(clipButtons);

        //======add toolTips
        String[] ctext = {"Find and Replace"};
        Model.getModel().getAddTips().addToolTip(clipButtons, ctext);

        //=====style buttons
        JToggleButton styleButtons[] = {italicButton, underlineButton, boldButton};
        Model.getModel().getCustomCss().styleButton(styleButtons);

        //======add toolTips
        String[] stext = {"Italic", "Underline", "Bold"};
        Model.getModel().getAddTips().addToolTip(styleButtons, stext);


        StyledDocument docm = text.getStyledDocument();
        SimpleAttributeSet margin = new SimpleAttributeSet();
        StyleConstants.setLeftIndent(margin, 80);
        StyleConstants.setRightIndent(margin, 60);
        StyleConstants.setSpaceAbove(margin, 10);
        StyleConstants.setSpaceBelow(margin, 10);

        docm.setParagraphAttributes(0, doc.getLength(), margin, false);

        //========= Default Close From Windows =========
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                Model.getModel().getCustomFile().EXIT_PROGRAM(text);

            }
        });
        setSize(1320, 800);
    }
    public static void main(String[] args) {
        Application wordApplication = new Application();
        wordApplication.setVisible(true);
        wordApplication.setLocation(20, 20);
        wordApplication.setResizable(true);
        wordApplication.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
    }
}
