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

import static cityagents.core.Direction.EAST;
import static cityagents.core.Direction.EAST_NORTH_SOUTH;
import static cityagents.core.Direction.NORTH;
import static cityagents.core.Direction.NORTH_EAST;
import static cityagents.core.Direction.NORTH_SOUTH;
import static cityagents.core.Direction.NORTH_WEST;
import static cityagents.core.Direction.NORTH_WEST_EAST;
import static cityagents.core.Direction.SOUTH;
import static cityagents.core.Direction.SOUTH_EAST;
import static cityagents.core.Direction.SOUTH_WEST;
import static cityagents.core.Direction.SOUTH_WEST_EAST;
import static cityagents.core.Direction.WEST;
import static cityagents.core.Direction.WEST_EAST;
import static cityagents.core.Direction.WEST_NORTH_SOUTH;

import java.util.ArrayList;

import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.SimpleDirectedGraph;

/**
 * 
 * @author Deep Blue Team
 */
public class WorldGraph extends SimpleDirectedGraph<Vertex, DefaultEdge> {

	private static final long serialVersionUID = -3259798925128648259L;
	private WorldObject[][] worldObject;
	private int worldSize;

	/**
	 * 
	 */
	public WorldGraph(WorldObject[][] worldObject, int size) {
		super(DefaultEdge.class);
		this.worldObject = worldObject;
		this.worldSize = size;
		initVertexes();
		initEdges();
	}

	private void initVertexes() {
		for (int i = 0; i < worldObject.length; i++) {
			for (int j = 0; j < worldObject[i].length; j++) {
				if (worldObject[i][j] instanceof Street) {
					Street street = (Street) worldObject[i][j];
					addVertex(new Vertex(getInteger(i, j),
							street.getDirection()));
				}
			}
		}
	}

	private void initEdges() {
		ArrayList<Vertex> vertexList = new ArrayList<Vertex>(vertexSet());
		for (Vertex vertex : vertexList) {
			Direction direction = vertex.getDirection();
			if (direction.equals(NORTH)) {
				linkToNorth(vertex, vertexList);
			} else if (direction.equals(SOUTH)) {
				linkToSouth(vertex, vertexList);
			} else if (direction.equals(WEST)) {
				linkToWest(vertex, vertexList);
			} else if (direction.equals(EAST)) {
				linkToEast(vertex, vertexList);
			} else if (direction.equals(NORTH_WEST)) {
				linkToNorth(vertex, vertexList);
				linkToWest(vertex, vertexList);
			} else if (direction.equals(NORTH_EAST)) {
				linkToNorth(vertex, vertexList);
				linkToEast(vertex, vertexList);
			} else if (direction.equals(SOUTH_WEST)) {
				linkToSouth(vertex, vertexList);
				linkToWest(vertex, vertexList);
			} else if (direction.equals(SOUTH_EAST)) {
				linkToSouth(vertex, vertexList);
				linkToEast(vertex, vertexList);
			} else if (direction.equals(NORTH_SOUTH)) {
				linkToNorth(vertex, vertexList);
				linkToSouth(vertex, vertexList);
			} else if (direction.equals(WEST_EAST)) {
				linkToWest(vertex, vertexList);
				linkToEast(vertex, vertexList);
			} else if (direction.equals(NORTH_WEST_EAST)) {
				linkToNorth(vertex, vertexList);
				linkToWest(vertex, vertexList);
				linkToEast(vertex, vertexList);
			} else if (direction.equals(SOUTH_WEST_EAST)) {
				linkToSouth(vertex, vertexList);
				linkToWest(vertex, vertexList);
				linkToEast(vertex, vertexList);
			} else if (direction.equals(WEST_NORTH_SOUTH)) {
				linkToWest(vertex, vertexList);
				linkToNorth(vertex, vertexList);
				linkToSouth(vertex, vertexList);
			} else if (direction.equals(EAST_NORTH_SOUTH)) {
				linkToEast(vertex, vertexList);
				linkToNorth(vertex, vertexList);
				linkToSouth(vertex, vertexList);
			}
		}
	}

	private void linkToNorth(Vertex vertex, ArrayList<Vertex> vertexList) {
		// restituisce l'intero che identifica il vertice target
		// a cui devo collegare "vertex"
		int index = vertex.getIndex() - worldSize;
		// restituisce il vertice target con l'identificativo pari a "index"
		Vertex targetVertex = getVertex(vertexList, index);
		// aggiunge un arco orientato tra il vertice e il vertice target
		addEdge(vertex, targetVertex);
	}

	private void linkToSouth(Vertex vertex, ArrayList<Vertex> vertexList) {
		// restituisce l'intero che identifica il vertice target
		// a cui devo collegare "vertex"
		int index = vertex.getIndex() + worldSize;
		// restituisce il vertice target con l'identificativo pari a "index"
		Vertex targetVertex = getVertex(vertexList, index);
		// aggiunge un arco orientato tra il vertice e il vertice target
		addEdge(vertex, targetVertex);
	}

	private void linkToWest(Vertex vertex, ArrayList<Vertex> vertexList) {
		// restituisce l'intero che identifica il vertice target
		// a cui devo collegare "vertex"
		int index = vertex.getIndex() - 1;
		// restituisce il vertice target con l'identificativo pari a "index"
		Vertex targetVertex = getVertex(vertexList, index);
		// aggiunge un arco orientato tra il vertice e il vertice target
		addEdge(vertex, targetVertex);
	}

	private void linkToEast(Vertex vertex, ArrayList<Vertex> vertexList) {
		// restituisce l'intero che identifica il vertice target
		// a cui devo collegare "vertex"
		int index = vertex.getIndex() + 1;
		// restituisce il vertice target con l'identificativo pari a "index"
		Vertex targetVertex = getVertex(vertexList, index);
		// aggiunge un arco orientato tra il vertice e il vertice target
		addEdge(vertex, targetVertex);
	}

	private Vertex getVertex(ArrayList<Vertex> vertexList, int index) {
		// restituisce la posizione del vertice target nella lista
		int i = vertexList.indexOf(new Vertex(index));
		// restituisce il vertice alla posizione i-esima
		// con valore "index"
		return vertexList.get(i);
	}

	private Pair getPair(Integer elem) {
		Pair pair = new Pair();
		pair.setRowIndex(elem / worldSize);
		pair.setColumnIndex(elem % worldSize);
		return pair;
	}

	private Integer getInteger(int i, int j) {
		return new Integer(worldSize * i + j);
	}

}