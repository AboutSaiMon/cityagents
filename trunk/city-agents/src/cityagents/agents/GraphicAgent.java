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
package cityagents.agents;

import jade.core.Agent;
import jade.core.behaviours.TickerBehaviour;
import jade.wrapper.StaleProxyException;

import java.awt.Point;

import cityagents.gui.PrincipalFrame;

/**
 *
 * @author Deep Blue Team
 */
public class GraphicAgent extends Agent 
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * This is the principal frame.
	 */
	PrincipalFrame principal;
	
	/**
	 * This agent creates the graphic environment.
	 */
	@Override
	protected void setup() 
	{
		super.setup();
		principal = new PrincipalFrame();
		
		//TO DO: CREATE A NEW BEHAVIOUR THAT ADDS AGENTS RANDOMLY.
		try 
		{			
			CarAgent c = new CarAgent();
			Point[] arguments = new Point[ 2 ];
			arguments[ 0 ] = new Point( 2, 3 );
			arguments[ 1 ] = new Point( 3, 5 );
			c.setArguments( arguments );
			this.getContainerController().acceptNewAgent( "Ciao", c ).start();
		} 
		catch( StaleProxyException e ) 
		{
			e.printStackTrace();
		}
		
		//THIS IS JUST A TRY. TO DO: ADD A NEW CLASS THAT PERFORM THIS OPERATION.
		this.addBehaviour( new TickerBehaviour( this, 1000 ) 
		{			
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			protected void onTick() 
			{
				principal.getPanel().getRight().repaint();
			}
		}
		);
	}
	
	/**
	 * When the function doDelete is invoked it disposes the principal frame. 
	 */
	@Override
	public void doDelete() 
	{
		super.doDelete();
		if( principal != null )
			principal.dispose();
	}
}
