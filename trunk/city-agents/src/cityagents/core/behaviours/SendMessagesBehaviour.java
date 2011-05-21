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

import jade.core.AID;
import jade.core.behaviours.Behaviour;
import jade.lang.acl.ACLMessage;

import java.io.IOException;

import cityagents.core.MessageContent;
import cityagents.core.agents.CarAgent;

/**
 * 
 * @author Deep Blue Team
 */
public class SendMessagesBehaviour extends Behaviour
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 6361518040672645588L;	
	
	MessageContent messageContent; 
	CarAgent[] receivers;
	
	/**
	 * 
	 */
	public SendMessagesBehaviour( MessageContent messageContent, CarAgent[] receivers )
	{
		this.messageContent = messageContent;
		this.receivers = receivers;
	}
	
	@Override
	public void action()
	{
		sendMessage( messageContent, receivers );
	}

	@Override
	public boolean done()
	{
		return true;
	}
	
	private boolean sendMessage( MessageContent messageContent, CarAgent[] receivers )
	{
		ACLMessage message = new ACLMessage( ACLMessage.PROPOSE );
		try
		{
			// Define the content of the message.
			message.setContentObject( messageContent );
		}
		catch( IOException e )
		{
			e.printStackTrace();
			return false;
		}

		// Set all recipients.
		for( int i = 0; i < receivers.length; i++ )
		{
			CarAgent c = receivers[ i ];
			message.addReceiver( new AID( c.getLocalName(), AID.ISLOCALNAME ) );
		}
		myAgent.send( message );

		return true;
	}
}
