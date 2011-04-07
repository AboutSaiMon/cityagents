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

import cityagents.gui.PrincipalFrame;
import jade.core.Agent;

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
