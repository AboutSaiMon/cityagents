/**
 * 
 * Copyright (C) 2011 Deep Blue Team <see the team details file>
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package cityagents.gui;

import java.awt.BorderLayout;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.MediaTracker;
import java.awt.Toolkit;
import java.io.File;

import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 *
 * @author Deep Blue Team
 */
public class PrincipalPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	private JFrame frame;

	private LeftPanel left;
	private RightPanel right;
	
	int currentObject;
	
	Image background;
	Image[] images = new Image[ 3 ];
	
	public PrincipalPanel( JFrame f ) 
	{
		super();
		this.frame = f;				
		loadImages();
		left = new LeftPanel( this );
		right = new RightPanel( this );
		createPanel();
	}
	
	public JFrame getFrame() 
	{
		return frame;
	}

	public LeftPanel getLeft() 
	{
		return left;
	}

	public RightPanel getRight() 
	{
		return right;
	}

	private void createPanel()
	{
		this.setLayout( new BorderLayout() );
		this.add( left, BorderLayout.WEST );
		this.add( right, BorderLayout.CENTER );
	}
	
	private void loadImages()
	{
		Toolkit t = Toolkit.getDefaultToolkit();
	
		images[ 0 ] = t.getImage( "src" + File.separator + "resources" + File.separator + "street.jpg" );
		images[ 1 ] = t.getImage( "src" + File.separator + "resources" + File.separator + "build.gif" );
		images[ 2 ] = t.getImage( "src" + File.separator + "resources" + File.separator + "car.gif" );
		MediaTracker mt = new MediaTracker( this );		
		for( int i = 0; i < images.length; i++ )
		{			
			mt.addImage( images[ i ], i );
		}
		mt.addImage( background , images.length );
		try 
		{
			for( int i = 0; i < images.length; i++ )
			{
				mt.waitForID( i );
			}
			mt.waitForID( images.length );
			mt.waitForID( images.length + 1 );
			mt.waitForID( images.length + 2 );
		} 
		catch ( InterruptedException e ) 
		{
			e.printStackTrace();
		}		
	}
	
	@Override
	protected void paintComponent( Graphics graphics ) 
	{
		// TODO Auto-generated method stub
		super.paintComponent( graphics );
		graphics.drawImage( background, 0, 0, null );
	}
}