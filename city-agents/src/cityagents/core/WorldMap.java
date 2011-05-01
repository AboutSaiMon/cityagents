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

import java.awt.Point;
import java.io.Serializable;

import cityagents.core.agents.CarAgent;

/**
 * This class is a singleton class that contains the world map.
 * @author Deep Blue Team
 */
public class WorldMap implements Serializable 
{	
	private static final long serialVersionUID = 2283101559110941224L;
	
	private static WorldMap thisInstance;
	private WorldObjects[][] world;
	private int worldSize = 30;
	private boolean editable = true;
	
	/**
	 * 
	 */
	private WorldMap() 
	{
		world = new WorldObjects[ worldSize * 2 ][ worldSize ];
		init();
	}
	
	public static WorldMap getInstance() 
	{
		if( thisInstance == null ) 
		{
			thisInstance = new WorldMap();
		}
		return thisInstance;
	}
	
	/**
	 * 
	 */
	private void init() 
	{
		for( int i = 0; i < ( worldSize * 2 ); i++ )
		{
			for( int j = 0; j < worldSize; j++ )
			{
				world[ i ][ j ] = new House();
			}
		}
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
			world = new WorldObjects[ worldSize * 2 ][ worldSize ];
			init();
		}
	}
	
	public void setStreet( int x, int y )
	{		
		world[ x ][ y ] = new Street();
	}
	
	public void setStreet( Point p )
	{		
		world[ p.x ][ p.y ] = new Street();
	}
	
	public void setHouse( int x, int y )
	{
		world[ x ][ y ] = new House();
	}
	
	public void setHouse( Point p )
	{
		world[ p.x ][ p.y ] = new House();
	}
	
	public void setCar( int x, int y, CarAgent c )
	{
		world[ x ][ y ] = c;
	}
	
	public void setCar( Point p, CarAgent c )
	{
		world[ p.x ][ p.y ] = c;
	}
	
	public WorldObjects getElement( int x, int y )
	{
		return world[ x ][ y ];		
	}
	
	public WorldObjects getElement( Point p )
	{
		return world[ p.x ][ p.y ];		
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