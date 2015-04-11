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
package cityagents.core.behaviours;

import jade.core.Agent;
import jade.core.behaviours.TickerBehaviour;

import java.awt.Point;
import java.util.Random;

import cityagents.core.Direction;
import cityagents.core.Street;
import cityagents.core.WorldMap;
import cityagents.core.agents.CarAgent;

/**
 *
 * @author Deep Blue Team
 */
public class AddAgentRandomlyBehaviour extends TickerBehaviour 
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	int numberOfAgentsToAdd;	
	/**
	 * @param a
	 * @param period
	 * @param numberOfAgentsToAdd 
	 */
	public AddAgentRandomlyBehaviour( Agent a, long period, int numberOfAgentsToAdd ) 
	{
		super( a, period );
		this.numberOfAgentsToAdd = numberOfAgentsToAdd;
	}	

	@Override
	protected void onTick() 
	{
		for( int i = 0; i < numberOfAgentsToAdd && i < 5; i++ )
		{
			boolean crossroad = true;
			WorldMap world = WorldMap.getInstance();
			if( !world.isEditable() )
			{			
				Random r = new Random();
				Point start = new Point();
				Point destination = new Point();
				
				int count = 0;
				do
				{
				
				start.x = r.nextInt( world.getWorldSize() );
				start.y = r.nextInt( world.getWorldSize() * 2 );
				
				destination.x = r.nextInt( world.getWorldSize() );
				destination.y = r.nextInt( world.getWorldSize() * 2 );
							
				if( world.getElement( start ) instanceof Street )
				{
					Street s = ( Street ) world.getElement( start );
					if( s.getDirection() != Direction.EAST ||
						s.getDirection() != Direction.WEST ||
						s.getDirection() != Direction.NORTH || 
						s.getDirection() != Direction.SOUTH )
					{
						crossroad = false;						
					}
				}
				}while( ( !( world.getElement( start ) instanceof Street ) || 						
						  !( world.getElement( destination ) instanceof Street )						  
						 ) && 
						 count++ < 3 && crossroad						 
				);
				
				if( ( world.getElement( start ) instanceof Street ) && ( world.getElement( destination ) instanceof Street ) )
				{
					CarAgent c = new CarAgent();
					Point[] arguments = new Point[ 2 ];
					arguments[ 0 ] = start;
					arguments[ 1 ] = destination;
					c.setArguments( arguments );
					
					
					world.setCar( start.x, start.y, c );		
					world.startAgent( start.x, start.y );
				}
			}
		}
	}

}
