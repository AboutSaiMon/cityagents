/**
 * City Agents is a framework for intelligent mobile agents.
 * Copyright (C) 2011 Deep Blue Team
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

import java.awt.Image;
import java.awt.MediaTracker;
import java.awt.Toolkit;

import javax.swing.JPanel;

import cityagents.util.Constants;


/**
 *
 * @author Deep Blue Team
 */
public class ImagesHandler 
{
	private static ImagesHandler instance = null;	
	
	private Image IMAGE_CAR = null;
	private Image IMAGE_STREET = null;
	private Image IMAGE_HOUSE = null;
	
	/**
	 * 
	 */
	private ImagesHandler() 
	{
		loadImages();
	}
	
	public static ImagesHandler getInstance()
	{
		if( instance == null )
		{				
			instance = new ImagesHandler();
		}
		return instance;
	}
	
	/**
	 * @return the iMAGE_HOUSE
	 */
	public Image getIMAGE_HOUSE() 
	{
		return IMAGE_HOUSE;
	}
	
	/**
	 * @return the iMAGE_STREET
	 */
	public Image getIMAGE_STREET() 
	{
		return IMAGE_STREET;
	}
	
	/**
	 * @return the iMAGE_CAR
	 */
	public Image getIMAGE_CAR() 
	{
		return IMAGE_CAR;
	}
	
	private void loadImages()
	{
		Toolkit t = Toolkit.getDefaultToolkit();
	
		IMAGE_STREET = t.getImage( Constants.IMAGES_PATH + "street_small.jpg" );
		IMAGE_HOUSE = t.getImage( Constants.IMAGES_PATH + "build_small.gif" );
		IMAGE_CAR = t.getImage( Constants.IMAGES_PATH + "car_small.gif" );
		MediaTracker mt = new MediaTracker( new JPanel() );
		mt.addImage( IMAGE_STREET, 0 );
		mt.addImage( IMAGE_HOUSE, 1 );
		mt.addImage( IMAGE_CAR, 2 );
		
		try 
		{
			for( int i = 0; i < 2; i++ )
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
