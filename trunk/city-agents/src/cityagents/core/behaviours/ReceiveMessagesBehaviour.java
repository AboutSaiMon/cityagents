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

import jade.core.behaviours.CyclicBehaviour;
import jade.lang.acl.ACLMessage;

import java.awt.Point;

import cityagents.core.Crossroad;
import cityagents.core.MessageContent;
import cityagents.core.agents.CarAgent;

/**
 * 
 * @author Deep Blue Team
 */
public class ReceiveMessagesBehaviour extends CyclicBehaviour
{

	/**
	 * 
	 */
	private static final long serialVersionUID = -7033032060839647236L;

	@Override
	public void action()
	{
		if( myAgent instanceof CarAgent )
		{
			CarAgent agent = ( CarAgent ) myAgent;
			//Get incoming message.
			ACLMessage message = agent.receive();
			if( message != null )
			{
				MessageContent content = null;						
				String receivedMessage = message.getContent();			
				
				if( receivedMessage.startsWith( "MSG" ) )
				{
					String[] splittedMessage = receivedMessage.split( ":::" );
					int x = Integer.parseInt( splittedMessage[ 1 ] );
					int y = Integer.parseInt( splittedMessage[ 2 ] );
					int speed = Integer.parseInt( splittedMessage[ 3 ] );
					int traffic = Integer.parseInt( splittedMessage[ 4 ] );
					
					content = new MessageContent( new Crossroad( new Point( x, y ) ), speed, traffic );
				
					ACLMessage reply = message.createReply();
					if( agent.getMyTraffic() == content.getTraffic() )
					{
						if( agent.getMySpeed() > content.getSpeed() )
						{
							reply.setContent( "RPL:" + agent.getMyTraffic() + "," + agent.getMySpeed());
							reply.setPerformative( ACLMessage.REJECT_PROPOSAL );
						}
						else
						{
							reply.setContent( "RPL:" + agent.getMyTraffic() + "," + agent.getMySpeed());
							reply.setPerformative( ACLMessage.ACCEPT_PROPOSAL );
						}
					}
					else
					{
						if( agent.getMyTraffic() < content.getTraffic() )
						{
							reply.setContent( "RPL:" + agent.getMyTraffic() + "," + agent.getMySpeed());
							reply.setPerformative( ACLMessage.REJECT_PROPOSAL );
						}
						else
						{
							reply.setContent( "RPL:" + agent.getMyTraffic() + "," + agent.getMySpeed());
							reply.setPerformative( ACLMessage.ACCEPT_PROPOSAL );
						}
					}
					
					agent.send( reply );
				}
				else
				{
					if( receivedMessage.startsWith( "RPL" ) )
					{
						if( message.getPerformative() == ACLMessage.ACCEPT_PROPOSAL )
						{									
							agent.setCanCross( true );
						}
					}
				}//End if msg
			}//Message != null
		}
	}
}