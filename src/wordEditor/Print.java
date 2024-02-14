package wordEditor;

import java.awt.Component;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;

import javax.swing.JOptionPane;
import javax.swing.RepaintManager;

public class Print implements Printable {
	private Component component_to_be_print;
	public Print(Component component)
	{
		this.component_to_be_print=component;
	}
	
	public void print()
	{
		PrinterJob printer_job = PrinterJob.getPrinterJob();
		printer_job.setPrintable(this);
		if(printer_job.printDialog())
		{
			try
			{
				printer_job.print();
			}
			catch(PrinterException pex)
			{
				JOptionPane.showMessageDialog(null,"Error Printing"+pex,
					"Printer Error",JOptionPane.ERROR_MESSAGE);
			}
		}
	}
	public void peintComponent(Component component)
	{
		new Print(component).print();
	}
	 
	public void disableDoubleBuffering(Component component)
	{
		RepaintManager current_manager=RepaintManager.currentManager(component);
		current_manager.setDoubleBufferingEnabled(false);
	}
	public void enableDoubleBuffering(Component component)
	{
		RepaintManager current_manager=RepaintManager.currentManager(component);
		current_manager.setDoubleBufferingEnabled(true);
	}
	@Override
	public int print(Graphics graphics, PageFormat pageFormat, int pageIndex) throws PrinterException {
		if(pageIndex>0)
			return(NO_SUCH_PAGE);
		else
		{
			Graphics2D graphics2d=(Graphics2D)graphics;
			graphics2d.translate(pageFormat.getImageableX(),
									pageFormat.getImageableY());
			disableDoubleBuffering(component_to_be_print);
			component_to_be_print.paint(graphics2d);
			enableDoubleBuffering(component_to_be_print);
			return(PAGE_EXISTS);
		}
		 
	}

}
