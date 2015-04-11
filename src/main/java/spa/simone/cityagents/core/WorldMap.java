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
package spa.simone.cityagents.core;

import jade.wrapper.StaleProxyException;
import spa.simone.cityagents.core.agents.CarAgent;
import spa.simone.cityagents.core.agents.GraphicAgent;
import spa.simone.cityagents.gui.PrincipalFrame;

import java.awt.*;
import java.io.Serializable;
import java.util.UUID;

/**
 * This class is a singleton class that contains the world map.
 *
 * @author Deep Blue Team
 */
public class WorldMap implements Serializable {

    private static final long serialVersionUID = 2283101559110941224L;
    private static WorldMap thisInstance;
    private WorldGraph worldGraph;
    private WorldObject[][] world;
    private int worldSize = 20;
    private boolean editable = true;

    private int numberOfAgents = 0;

    private int globalTime = 0;
    private int numberOfAgentsAdded = 0;
    private int numberOfAccidents = 0;

    /**
     *
     */
    private WorldMap() {
        world = new WorldObject[worldSize][worldSize * 2];
        init();
    }

    public static WorldMap getInstance() {
        if (thisInstance == null) {
            thisInstance = new WorldMap();
        }
        return thisInstance;
    }

    public void generateWorldGraph() {
        worldGraph = new WorldGraph(world);
    }

    /**
     *
     */
    private void init() {
        for (int i = 0; i < world.length; i++) {
            for (int j = 0; j < world[i].length; j++) {
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
            world = new WorldObject[worldSize][worldSize * 2];
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
     * This method returns the direction of the street. It can return a null
     * value.
     *
     * @param x
     * @param y
     * @return
     */
    public Direction getDirection(int x, int y) {
        Direction direction = null;
        if (editable) {
            if (world[x][y] instanceof Street) {
                Street street = (Street) world[x][y];
                direction = street.getDirection();
            }
        }
        return direction;
    }

    /**
     * @return the worldGraph
     */
    public WorldGraph getWorldGraph() {
        return worldGraph;
    }

    public void removeCar(int x, int y) {
        if (world[x][y] instanceof Street) {
            Street s = (Street) world[x][y];
            s.setAgent(null);
        }
    }

    public void removeCar(Point p) {
        removeCar(p.x, p.y);
    }

    public void setStreet(int x, int y) {
        if (editable) {
            world[x][y] = new Street();
        }
    }

    public void setStreet(Point p) {
        setStreet(p.x, p.y);
    }

    public void setGrass(int x, int y) {
        if (editable)
            world[x][y] = new Grass();
    }

    public void setGrass(Point p) {
        setGrass(p.x, p.y);
    }

    public void setHouse(int x, int y) {
        if (editable) {
            world[x][y] = new House();
        }
    }

    public void setHouse(Point p) {
        setHouse(p.x, p.y);
    }

    public void setCar(int x, int y, CarAgent c) {
        if (world[x][y] instanceof Street) {
            Street s = (Street) world[x][y];
            s.setAgent(c);
        }
    }

    public void setCar(Point p, CarAgent c) {
        setCar(p.x, p.y, c);
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
            for (int i = 0; i < world.length; i++) {
                for (int j = 0; j < world[i].length; j++) {
                    if (world[i][j] instanceof Street) {
                        Street s = (Street) world[i][j];

                        CarAgent c = s.getAgent();
                        if (c != null) {
                            try {
                                String uuid = UUID.randomUUID().toString();
                                g.getContainerController().acceptNewAgent(uuid + numberOfAgents, c).start();
                                numberOfAgents++;
                            } catch (StaleProxyException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }
            }
        }
    }

    public void startAgent(int i, int j) {
        PrincipalFrame frame = PrincipalFrame.getInstance();
        GraphicAgent g = frame.getGraphicAgent();
        if (world[i][j] instanceof Street) {
            Street s = (Street) world[i][j];

            CarAgent c = s.getAgent();
            if (c != null) {
                try {
                    String uuid = UUID.randomUUID().toString();
                    g.getContainerController().acceptNewAgent(uuid + numberOfAgents, c).start();
                    numberOfAgents++;
                } catch (StaleProxyException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void addGlobalTime(int gt) {
        globalTime += gt;
    }

    public void incrementNumberOfAgents() {
        numberOfAgentsAdded++;
    }

    public double getPerformanceMeasure() {
        return (globalTime / numberOfAgentsAdded) * Math.pow(5, numberOfAccidents);
    }

}