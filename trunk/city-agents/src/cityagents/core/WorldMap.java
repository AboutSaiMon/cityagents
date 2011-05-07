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

import jade.wrapper.StaleProxyException;

import java.awt.Point;
import java.io.Serializable;

import cityagents.core.agents.CarAgent;
import cityagents.core.agents.GraphicAgent;
import cityagents.gui.PrincipalFrame;

/**
 * This class is a singleton class that contains the world map.
 * 
 * @author Deep Blue Team
 */
public class WorldMap implements Serializable {

	private static final long serialVersionUID = 2283101559110941224L;

	private static WorldMap thisInstance;
	private WorldObject[][] world;
	private int worldSize = 20;
	private boolean editable = true;

	private int numberOfAgents = 0;

	/**
	 * 
	 */
	private WorldMap() {
		world = new WorldObject[worldSize * 2][worldSize];
		init();
	}

	public static WorldMap getInstance() {
		if (thisInstance == null) {
			thisInstance = new WorldMap();
		}
		return thisInstance;
	}

	/**
	 * 
	 */
	private void init() {
		for (int i = 0; i < (worldSize * 2); i++) {
			for (int j = 0; j < worldSize; j++) {
				world[i][j] = new Grass();
			}
		}
	}

	public int getWorldSize() {
		return worldSize;
	}

	public void setMap(WorldObject[][] map) {
		world = map;
	}

	public WorldObject[][] getMap() {
		return world;
	}

	public void resize(int newSize) {
		if (editable) {
			worldSize = newSize;
			world = new WorldObject[worldSize * 2][worldSize];
			init();
		}
	}

	public void setDirection(Direction direction, int x, int y) {
		if (editable) {
			if (world[x][y] instanceof Street) {
				Street street = (Street) world[x][y];
				street.setDirection(direction);
			}
		}
	}

	/**
	 * This method returns the direction of the street. It can
	 * returns a null value.
	 * 
	 * @param x
	 * @param y
	 * @return
	 */
	public Direction getDirection(int x, int y) {
		Direction direction = null;
		if( editable) {
			if (world[x][y] instanceof Street) {
				Street street = (Street) world[x][y];
				direction = street.getDirection();
			}
		}
		return direction;
	}

	public void setStreet(int x, int y) {
		if (editable)
			world[x][y] = new Street();
	}

	public void setStreet(Point p) {
		if (editable)
			world[p.x][p.y] = new Street();
	}

	public void setGrass(int x, int y) {
		if (editable)
			world[x][y] = new Grass();
	}

	public void setGrass(Point p) {
		if (editable)
			world[p.x][p.y] = new Grass();
	}

	public void setHouse(int x, int y) {
		if (editable) {
			world[x][y] = new House();
		}
	}

	public void setHouse(Point p) {
		if (editable) {
			world[p.x][p.y] = new House();
		}
	}

	public void setCar(int x, int y, CarAgent c) {
		world[x][y] = c;
	}

	public void setCar(Point p, CarAgent c) {
		world[p.x][p.y] = c;
	}

	public WorldObject getElement(int x, int y) {
		return world[x][y];
	}

	public WorldObject getElement(Point p) {
		return world[p.x][p.y];
	}

	public boolean isEditable() {
		return editable;
	}

	public void setEditable(boolean editable) {
		this.editable = editable;
	}

	/**
	 * 
	 */
	public void startAgents() {
		PrincipalFrame frame = PrincipalFrame.getInstance();
		GraphicAgent g = frame.getGraphicAgent();
		if (g != null) {
			for (int i = 0; i < (worldSize * 2); i++) {
				for (int j = 0; j < worldSize; j++) {
					if (world[i][j] instanceof CarAgent) {
						CarAgent c = (CarAgent) world[i][j];
						try {
							g.getContainerController()
									.acceptNewAgent("Car" + numberOfAgents, c)
									.start();
							numberOfAgents++;
						} catch (StaleProxyException e) {
							e.printStackTrace();
						}
					}
				}
			}
		}
	}

	public void startAgent(int i, int j) {
		PrincipalFrame frame = PrincipalFrame.getInstance();
		GraphicAgent g = frame.getGraphicAgent();
		if (world[i][j] instanceof CarAgent) {
			CarAgent c = (CarAgent) world[i][j];
			try {
				g.getContainerController()
						.acceptNewAgent("Car" + numberOfAgents, c).start();
				numberOfAgents++;
			} catch (StaleProxyException e) {
				e.printStackTrace();
			}
		}
	}

}