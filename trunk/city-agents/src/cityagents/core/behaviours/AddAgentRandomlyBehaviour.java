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

import java.awt.Point;

import cityagents.core.agents.CarAgent;
import jade.core.Agent;
import jade.core.behaviours.TickerBehaviour;
import jade.wrapper.StaleProxyException;

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
		//STUFF TO DO		
//		CarAgent c = new CarAgent();
//		Point[] arguments = new Point[ 2 ];
//		arguments[ 0 ] = new Point( 2, 3 );
//		arguments[ 1 ] = new Point( 3, 5 );
//		c.setArguments( arguments );
//		try 
//		{
//			myAgent.getContainerController().acceptNewAgent( "Car", c ).start();
//		} catch (StaleProxyException e) 
//		{
//			e.printStackTrace();
//		}		
	}

}
