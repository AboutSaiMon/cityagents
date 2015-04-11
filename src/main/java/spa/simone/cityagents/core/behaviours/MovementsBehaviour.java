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

import jade.core.behaviours.TickerBehaviour;

import java.awt.Point;
import java.util.List;

import org.jgrapht.graph.DefaultEdge;

import cityagents.core.Crossroad;
import cityagents.core.MessageContent;
import cityagents.core.Street;
import cityagents.core.WorldMap;
import cityagents.core.agents.CarAgent;

/**
 *
 * @author Deep Blue Team
 */
public class MovementsBehaviour extends TickerBehaviour
{

	/**
	 * 
	 */
	private static final long serialVersionUID = -3913493298733616546L;

	private CarAgent agent;
	private int nextStep;
	private WorldMap world;
	
	/**
	 * @throws Exception 
	 * 
	 */	
	public MovementsBehaviour( CarAgent c, long period )
	{	
		super( c, period );
		agent = c;
		world = WorldMap.getInstance();
		nextStep = 0;
	}	

	@Override
	protected void onTick()
	{
		DefaultEdge nextEdge = agent.getMyPath().get( nextStep );
		Point currentPosition = world.getWorldGraph().getEdgeSource(  nextEdge );
		Point nextPosition = world.getWorldGraph().getEdgeTarget( nextEdge );
		
		if( world.getElement( nextPosition ) instanceof Street )
		{
			Street next = ( Street ) world.getElement( nextPosition );
			
			if( next.getAgent() == null )
			{		
				Crossroad c = world.getWorldGraph().getCrossroad( nextPosition );
				if( c == null )
				{
					world.removeCar( currentPosition );								
					world.setCar( nextPosition, agent );
					nextStep++;
					agent.incrementStep( 1 );
				}
				else
				{
					if( !agent.isSentMessage() )
					{
						List< CarAgent > receivers = world.getWorldGraph().getNeighbours( c );
						
						if( receivers.size() > 1 )
						{
							MessageContent messageContent = new MessageContent( c, agent.getMySpeed(), agent.getMyTraffic() );
							agent.addBehaviour( new SendMessagesBehaviour( messageContent, receivers ) );
							agent.setSentMessage( true );
						}
						else
						{
							world.removeCar( currentPosition );								
							world.setCar( nextPosition, agent );
							nextStep++;
							agent.incrementStep( 1 );
						}
					}
					
					if( agent.canCross() )
					{
						world.removeCar( currentPosition );
						world.setCar( nextPosition, agent );
						nextStep++;
						agent.incrementStep( 1 );
						agent.setSentMessage( false );
						agent.setCanCross( false );
					}
				}
			}
		}
		
		if( nextStep == agent.getMyPath().size() )
		{
			world.removeCar( nextPosition );
			agent.doDelete();
		}
	}
}
