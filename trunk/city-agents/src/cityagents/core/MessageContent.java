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
package cityagents.core;

import java.io.Serializable;

/**
 *
 * @author Deep Blue Team
 */
public class MessageContent implements Serializable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 6162966011809057417L;
	
	Object crossroad;//TODO: Define what is a crossroad
	int time;
	
	/**
	 * 
	 */
	public MessageContent( Object crossroad, int time ) 
	{
		this.crossroad = crossroad;
		this.time = time;
	}

	public Object getCrossroad() 
	{
		return crossroad;
	}

	public void setCrossroad( Object crossroad ) 
	{
		this.crossroad = crossroad;
	}

	public int getTime() 
	{
		return time;
	}

	public void setTime( int time ) 
	{
		this.time = time;
	}
}
