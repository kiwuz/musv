package panel_glowny;

import java.awt.Component;
import java.awt.*;
import javax.swing.*;
import java.awt.Graphics;

import javax.swing.Icon;

public class RolloverIcon implements Icon {

	public class RolloverIcon implements Icon
	{
	  protected Icon icon;

	  public RolloverIcon(Icon icon)
	  {
	    this.icon = icon;
	  }

	  public int getIconHeight()
	  {
	    return icon.getIconHeight();
	  }

	  public int getIconWidth()
	  {
	    return icon.getIconWidth();
	  }

	  public void paintIcon(Component c, Graphics g, int x, int y)
	  {
	    Graphics2D graphics2d = (Graphics2D) g;
	    Composite oldComposite = graphics2d.getComposite();
	    graphics2d.setComposite(RolloverComposite.DEFAULT);
	    icon.paintIcon(c, g, x, y);
	    graphics2d.setComposite(oldComposite);
	  }
	}

	@Override
	public int getIconHeight() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getIconWidth() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void paintIcon(Component c, Graphics g, int x, int y) {
		// TODO Auto-generated method stub
		
	}
}