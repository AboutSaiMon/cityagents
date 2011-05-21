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
package cityagents.core.agents;

import jade.core.Agent;

import java.awt.Point;

import cityagents.core.Street;
import cityagents.core.WorldMap;
import cityagents.core.WorldObject;

/**
 * 
 * @author Deep Blue Team
 */
public class CarAgent extends Agent implements WorldObject
{
	private static final long serialVersionUID = -5719389862334582000L;
	private Point start;
	private Point destination;
	private WorldMap world;
	private int mySpeed;
	private int myTraffic;

	@Override
	protected void setup()
	{
		super.setup();
		Object[] args = this.getArguments();

		if( args == null )
		{
			doDelete();
		}

		world = WorldMap.getInstance();
		try
		{
			if( args[ 0 ] instanceof Point && args[ 1 ] instanceof Point )
			{
				start = ( Point ) args[ 0 ];
				destination = ( Point ) args[ 1 ];
				int worldSize = world.getWorldSize();
				if( start.x >= 0 && start.y < worldSize && destination.x >= 0 && destination.y < ( worldSize * 2 ) )
				{
					WorldObject elementAtStart = world.getElement( start );
					WorldObject elementAtDestination = world.getElement( destination );

					if( elementAtStart instanceof Street && elementAtDestination instanceof Street )
					{
						world.setCar( start, this );
					}
					else
					{
						throw new Exception( "Invalid position." );
					}
				}
				else
				{
					throw new Exception( "Bad Intervals." );
				}
			}
			else
			{
				throw new Exception( "Wrong args." );
			}
		}
		catch( Exception e )
		{
			doDelete();
		}

	}	

	/**
	 * @return the mySpeed
	 */
	public int getMySpeed()
	{
		return mySpeed;
	}

	/**
	 * @param mySpeed the mySpeed to set
	 */
	public void setMySpeed( int mySpeed )
	{
		this.mySpeed = mySpeed;
	}
	
	/**
	 * @return the myTraffic
	 */
	public int getMyTraffic()
	{
		return myTraffic;
	}
	
	/**
	 * @param myTraffic the myTraffic to set
	 */
	public void setMyTraffic( int myTraffic )
	{
		this.myTraffic = myTraffic;
	}
}
