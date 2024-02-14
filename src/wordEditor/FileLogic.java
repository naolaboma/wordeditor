package wordEditor;

import java.awt.Color;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;

import javax.swing.JFileChooser;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JTextPane;
import javax.swing.text.rtf.RTFEditorKit;

public class FileLogic extends TopParent{

	private String output="";
	private String outputStream="";
	private boolean isUnderlined=false;
	private boolean isOpened=false;
	private boolean isNewFile=false;
	private boolean isSaved=false;
	private File openedFile;
	private File savedFile;
    public FileLogic() {
    	
    }
    
    
    public void NEW_FILE(JTextPane body)
  	{
    	 
  		if(isOpened==true||isNewFile==true)
  		{
  			if(!body.equals(""))
  			{
  				int answer=JOptionPane.showConfirmDialog(null,
  					"Do you want to save this file....?");
  				if(answer==JOptionPane.OK_OPTION)
  				{
  					SAVE_FILE(body);
  				}
  				else
  					if(answer==JOptionPane.CANCEL_OPTION)
  						return;
  			}
  		}
  		SetEnable_JTextPane(body);
  		body.selectAll();
  		int start=body.getSelectionStart();
  		int end=body.getSelectionEnd();
  		try 
  		{
  			body.getDocument().remove(start,end);
  		}
  		catch(Exception ex)
  		{}
  		isOpened=false;
  		isNewFile=true;
  		isSaved=false;
  	}
    
    public void OPEN_FILE(JTextPane body)
	{
		JFileChooser file_chooser=new JFileChooser(".");
		file_chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
		int answer=file_chooser.showOpenDialog(null);
		if(answer==JFileChooser.CANCEL_OPTION)
			return;
		else if(answer==JFileChooser.OPEN_DIALOG)
		{
			openedFile=file_chooser.getSelectedFile();
			if(openedFile!=null||openedFile.getName()!="")
			{
				try
				{
					BufferedReader stream=new BufferedReader(new FileReader (openedFile));
					String line;
					output="";
					while((line=stream.readLine())!=null)
					{
						output+=line;
						output+="\n";
					}
					body.setText(output);
				}	
				catch(IOException ex)
			    {
			    	JOptionPane.showConfirmDialog(null,
			    	"error reading from the file",
			    	"error reading",
			    	JOptionPane.ERROR_MESSAGE);
			    }
			}
		isOpened=true;
		isNewFile=false;
		}
	}
    
    
	public void SAVE_FILE(JTextPane body)
	{
		JFileChooser file_chooser=new JFileChooser();
		file_chooser.setCurrentDirectory(new File("."));
		if(isOpened==true)
		{
			try
			{
				FileOutputStream stream=new FileOutputStream(openedFile);
              	rtfKit.write(stream,body.getDocument(),0,body.getDocument().getLength());
              	stream.close();	
            }
            catch(Exception ex)
            {}
		}
		else if(isSaved==true)
		{
			try
			{
				FileOutputStream stream=new FileOutputStream(savedFile);
              	rtfKit.write(stream,body.getDocument(),0,
              					body.getDocument().getLength());
              	stream.close();
            }
            catch(Exception ex)
            {}
		}
		else
		{
			if(file_chooser.showSaveDialog(null)==0)
			{
				try
				{
					String file_name=file_chooser.getSelectedFile().toString()
											+".wrd";
					File file_out=new File(file_name);
					FileOutputStream stream=new FileOutputStream(file_out);
    	          	rtfKit.write(stream,body.getDocument(),0,
    	          					body.getDocument().getLength());
    	          	savedFile=file_out;
    	          	isSaved=true;
   		           	stream.close();
        	    } 
      		    catch (Exception ex)
      		    {
     	         	ex.printStackTrace();
        	    }
            	file_chooser.rescanCurrentDirectory();
         	}
        }
	}
//********************* SAVE_AS_FILE Function ********************
	public void SAVE_AS_FILE(JTextPane body)
	{
		JFileChooser file_chooser=new JFileChooser();
		file_chooser.setCurrentDirectory(new File("."));
		if(file_chooser.showSaveDialog(null)==0)
		{
			try
			{
				String file_name=file_chooser.getSelectedFile().toString()
										+".wrd";
				File file_out=new File(file_name);
				FileOutputStream stream=new FileOutputStream(file_out);
   	          	rtfKit.write(stream,body.getDocument(),0,
   	          					body.getDocument().getLength());
   	          	savedFile=file_out;
   	          	isSaved=true;
	           	stream.close();
       	    } 
   		    catch (Exception ex)
   		    {
  	         	ex.printStackTrace();
      	    }
           	file_chooser.rescanCurrentDirectory();
       	}
    }
//********************* EXIT_PROGRAM Function ********************
	public void CLOSE_FILE(JTextPane body)
	{
		if(isOpened==true||isNewFile==true)
		{
			int answer=JOptionPane.showConfirmDialog(null,"Would you like to Save this file...?"
				,"Closing File",JOptionPane.YES_NO_CANCEL_OPTION);
			if(answer==JOptionPane.OK_OPTION)
			{
				SAVE_FILE(body);
				body.selectAll();
				try
				{
					body.getDocument().remove(body.getSelectionStart(),
										body.getSelectionEnd());
				}
				catch(Exception ex)
				{}
				SetDisable_JTextPane(body);
			}
			else if(answer==JOptionPane.CANCEL_OPTION)
				return;
			else
			{
				body.selectAll();
				try
				{
					body.getDocument().remove(body.getSelectionStart(),
										body.getSelectionEnd());
				}
				catch(Exception ex)
				{}
				SetDisable_JTextPane(body);
			}
		}
	}
//********************* EXIT_PROGRAM Function ********************
	public void EXIT_PROGRAM(JTextPane body)
	{
		if(!body.equals(""))
		{
			int answer=JOptionPane.showConfirmDialog(null,
					"Do you want to save this file....?");
			if(answer==JOptionPane.OK_OPTION)
			{
				SAVE_FILE(body);
				System.exit(0);
			}
			else if(answer==JOptionPane.NO_OPTION)
				System.exit(0);
			else
				return;
		}
		else
			System.exit(0);
	}
	
	
	
	 
}
