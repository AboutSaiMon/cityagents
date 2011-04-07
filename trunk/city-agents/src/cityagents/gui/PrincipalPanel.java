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
import java.awt.Image;
import java.awt.MediaTracker;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JPanel;

import cityagents.util.Constants;

/**
 *
 * @author Deep Blue Team
 */
public class PrincipalPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	private JFrame frame;

	private LeftPanel left;
	private RightPanel right;
	private final int numberOfImages = 2;
	
	/**
	 * This variable needs to know which element is clicked.
	 */
	int currentChoice;
		
	/**
	 * This is the vector of images used in the environment draw.
	 */
	Image[] images = new Image[ numberOfImages ];
	
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
	
		images[ 0 ] = t.getImage( Constants.IMAGES_PATH + "street.jpg" );
		images[ 1 ] = t.getImage( Constants.IMAGES_PATH + "build.gif" );
		MediaTracker mt = new MediaTracker( this );		
		for( int i = 0; i < images.length; i++ )
		{			
			mt.addImage( images[ i ], i );
		}
		try 
		{
			for( int i = 0; i < images.length; i++ )
			{
				mt.waitForID( i );
			}
		} 
		catch ( InterruptedException e ) 
		{
			e.printStackTrace();
		}		
	}
}