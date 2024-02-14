package wordEditor;

import java.awt.Color;

import javax.swing.JTextPane;
import javax.swing.text.rtf.RTFEditorKit;

public class Model {
	static Model model=new Model();
	private CustomStyle customStyle;
	private CustomColor customColor;
	private Paragraph customParagraph;
	private CustomImage customImage;
	private FileLogic customFile;
	private EditLogic customEdit;
	private QuickAccess customQuick;
	private CssClass customCss;
	private AddToolTips addToolTips;
	public Model() {
		customParagraph=new Paragraph();
		customStyle=new CustomStyle();
		customColor=new CustomColor();
		customImage =new CustomImage();
		customFile=new FileLogic();
		customEdit=new EditLogic();
		customQuick=new QuickAccess();
		customCss=new CssClass();
		addToolTips=new AddToolTips();
	}
	
   public static Model getModel() {
	   if(model==null) {
		   model=new Model();
	   }
	   return model;
	 
  }
   
   public CustomStyle getCustomStyle() {
	   return customStyle;
   }
   public CustomColor getCustomColor() {
	   return customColor;
   }
   
   public Paragraph getCustomParagraph() {
	return customParagraph;
	   
   }
   public CustomImage getCustomImage() {
	return customImage;
	   
   }
   
   public FileLogic getCustomFile() {
	return customFile;
	   
   }
   public EditLogic getCustomEdit() {
		return customEdit;
		   
	   }
   
   public QuickAccess getCustomQuick() {
	return customQuick;
	   
   }
   
   public CssClass getCustomCss() {
	return customCss;
	   
   }
   
   public AddToolTips getAddTips() {
	return addToolTips;
	   
   }
}
