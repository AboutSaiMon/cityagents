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
import jade.lang.acl.UnreadableException;
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
				MessageContent content;
				try 
				{
					content = (MessageContent) message.getContentObject();
					agent.addNewEntryForTimeCrossroadMap( content.getTime(), content.getCrossroad() );
				}
				catch( UnreadableException e ) 
				{
					e.printStackTrace();
				}
			}
		}
	}
}
