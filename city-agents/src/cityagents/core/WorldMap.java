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
package cityagents.core;

import cityagents.util.Constants;

/**
 * This class is a singleton class that contains the world map.
 * @author Deep Blue Team
 */
public class WorldMap 
{	
	private int[][] world;
	private int worldSize = 16;
	private boolean editable = true;
	
	private static WorldMap instance = null;	
	
	/**
	 * 
	 */
	private WorldMap() 
	{
		world = new int[ worldSize * 2 ][ worldSize ];
	}
	
	public static WorldMap getInstance()
	{
		if( instance == null )
		{				
			instance = new WorldMap();
		}
		return instance;		
	}
	
	public int getWorldSize()
	{
		return worldSize;
	}
	
	public void resize( int newSize )
	{
		if( editable )
		{
			worldSize = newSize;
			world = new int[ worldSize * 2 ][ worldSize ];
		}
	}
	
	public void setStreet( int x, int y )
	{		
		world[ x ][ y ] = Constants.STREET;
	}
	
	public void setHouse( int x, int y )
	{
		world[ x ][ y ] = Constants.HOUSE;
	}
	
	public void setCar( int x, int y )
	{
		world[ x ][ y ] = Constants.CAR;
	}
	
	public int getElement( int x, int y )
	{
		return world[ x ][ y ];		
	}
	
	public boolean isEditable() 
	{
		return editable;
	}

	public void setEditable( boolean editable ) 
	{
		this.editable = editable;
	}
}