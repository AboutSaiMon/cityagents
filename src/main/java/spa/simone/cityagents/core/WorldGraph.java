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

import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.SimpleDirectedGraph;
import spa.simone.cityagents.core.agents.CarAgent;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import static spa.simone.cityagents.core.Direction.*;

/**
 * @author Deep Blue Team
 */
public class WorldGraph extends SimpleDirectedGraph<Point, DefaultEdge> {

    /**
     *
     */
    private static final long serialVersionUID = 2948548836232432812L;

    private WorldObject[][] worldObject;
    private List<Point> verticesList;
    private List<Crossroad> crossroadsList;

    public WorldGraph(WorldObject[][] worldObject) {
        super(DefaultEdge.class);
        this.worldObject = worldObject;
        this.crossroadsList = new ArrayList<Crossroad>();
        initVertices();
        initEdges();
    }

    private void initVertices() {
        for (int i = 0; i < worldObject.length; i++) {
            for (int j = 0; j < worldObject[i].length; j++) {
                if (worldObject[i][j] instanceof Street) {
                    addVertex(new Point(i, j));
                }
            }
        }
    }

    private void initEdges() {
        verticesList = new ArrayList<Point>(vertexSet());
        for (Point p : verticesList) {
            Street street = (Street) worldObject[p.x][p.y];
            Direction direction = street.getDirection();
            if (direction.equals(NORTH)) {
                linkToNorth(p);
            } else if (direction.equals(SOUTH)) {
                linkToSouth(p);
            } else if (direction.equals(WEST)) {
                linkToWest(p);
            } else if (direction.equals(EAST)) {
                linkToEast(p);
            } else if (direction.equals(NORTH_WEST)) {
                addCrossroad(p);
                linkToNorth(p);
                linkToWest(p);
            } else if (direction.equals(NORTH_EAST)) {
                addCrossroad(p);
                linkToNorth(p);
                linkToEast(p);
            } else if (direction.equals(SOUTH_WEST)) {
                addCrossroad(p);
                linkToSouth(p);
                linkToWest(p);
            } else if (direction.equals(SOUTH_EAST)) {
                addCrossroad(p);
                linkToSouth(p);
                linkToEast(p);
            } else if (direction.equals(NORTH_SOUTH)) {
                addCrossroad(p);
                linkToNorth(p);
                linkToSouth(p);
            } else if (direction.equals(WEST_EAST)) {
                addCrossroad(p);
                linkToWest(p);
                linkToEast(p);
            } else if (direction.equals(NORTH_WEST_EAST)) {
                addCrossroad(p);
                linkToNorth(p);
                linkToWest(p);
                linkToEast(p);
            } else if (direction.equals(SOUTH_WEST_EAST)) {
                addCrossroad(p);
                linkToSouth(p);
                linkToWest(p);
                linkToEast(p);
            } else if (direction.equals(WEST_NORTH_SOUTH)) {
                addCrossroad(p);
                linkToWest(p);
                linkToNorth(p);
                linkToSouth(p);
            } else if (direction.equals(EAST_NORTH_SOUTH)) {
                addCrossroad(p);
                linkToEast(p);
                linkToNorth(p);
                linkToSouth(p);
            }
        }
    }

    private void addCrossroad(Point p) {
        Crossroad c = new Crossroad(p);
        crossroadsList.add(c);
    }

    public boolean isCrossroad(Point p) {
        for (Crossroad c : crossroadsList) {
            if (c.getPosition().equals(p)) {
                return true;
            }
        }
        return false;
    }

    public Crossroad getCrossroad(Point p) {
        for (Crossroad c : crossroadsList) {
            if (c.getPosition().equals(p)) {
                return c;
            }
        }
        return null;
    }

    /**
     * @param p
     */
    private void linkToEast(Point p) {
        Point newPoint = new Point(p.x, p.y + 1);
        if (this.containsVertex(newPoint))
            addEdge(p, newPoint);
    }

    /**
     * @param p
     */
    private void linkToWest(Point p) {
        Point newPoint = new Point(p.x, p.y - 1);
        if (this.containsVertex(newPoint))
            addEdge(p, newPoint);
    }

    /**
     * @param p
     */
    private void linkToSouth(Point p) {
        Point newPoint = new Point(p.x + 1, p.y);
        if (this.containsVertex(newPoint))
            addEdge(p, newPoint);
    }

    /**
     * @param p
     */
    private void linkToNorth(Point p) {
        Point newPoint = new Point(p.x - 1, p.y);
        if (this.containsVertex(newPoint))
            addEdge(p, newPoint);
    }

    public List<CarAgent> getNeighbours(Crossroad c) {
        List<CarAgent> agents = new ArrayList<CarAgent>();
        //The crossroad position in the graph.
        Point p = c.getPosition();

        //Get all edges of the crossroad.
        Set<DefaultEdge> edgesOfP = this.edgesOf(p);

        for (DefaultEdge e : edgesOfP) {
            Point source = this.getEdgeSource(e);
            Point target = this.getEdgeTarget(e);

            if (target.equals(p)) {
                Street s = (Street) worldObject[source.x][source.y];
                CarAgent agent = s.getAgent();

                if (agent != null) {
                    agents.add(agent);
                }
            }
        }
        return agents;
    }
}
