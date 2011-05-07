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

import jade.core.AID;
import jade.core.Agent;
import jade.lang.acl.ACLMessage;

import java.awt.Point;
import java.io.IOException;
import java.util.HashMap;

import cityagents.core.MessageContent;
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
	private HashMap< Integer, Object > mapTimeCrossroad; 
	
	@Override
	protected void setup() 
	{
		super.setup();
		Object[] args = this.getArguments();
		mapTimeCrossroad = new HashMap< Integer, Object >();
		
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
	
	public void addNewEntryForTimeCrossroadMap( Integer time, Object crossroad )
	{
		mapTimeCrossroad.put( time, crossroad );
	}
	
	public Object getCrossroadAtTime( Integer time )
	{
		return mapTimeCrossroad.get( time );		
	}
	
	public boolean sendMessage( MessageContent messageContent, CarAgent[] receivers )
	{
		Integer time = messageContent.getTime();
		if( mapTimeCrossroad.get( time ) == null )
		{
			ACLMessage message  = new ACLMessage( ACLMessage.INFORM );
			try 
			{
				//Define the content of the message.
				message.setContentObject( messageContent );
			} 
			catch( IOException e ) 
			{
				e.printStackTrace();
				return false;
			}
			
			//Set all recipients.
			for( int i = 0; i < receivers.length; i++ )
			{			
				CarAgent c = receivers[ i ];
				message.addReceiver( new AID( c.getLocalName(), AID.ISLOCALNAME) );
			}
			this.send( message );
		}
		else
		{
			return false;
		}
		return true;
	}
}
