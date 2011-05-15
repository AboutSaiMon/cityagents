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
package cityagents.gui;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.swing.JPanel;

import cityagents.core.Direction;
import cityagents.core.Grass;
import cityagents.core.House;
import cityagents.core.Street;
import cityagents.core.WorldMap;
import cityagents.core.WorldObject;
import cityagents.core.agents.CarAgent;
import cityagents.gui.listeners.RPanelMouseListener;

public class RightPanel extends JPanel {
	
	private static final long serialVersionUID = 1L;
	private static final int size = 30;
	private PrincipalPanel superiorPanel;
	private WorldMap world;
	private ImagesHandler images;

	private List<Point> cellToDraw;
	private boolean startDraw = false;
	private boolean addAnAgent = false;
	private boolean editDirection = false;
	private Direction currentDirection = Direction.NONE;

	public RightPanel(PrincipalPanel p) {
		this.setOpaque(false);
		images = ImagesHandler.getInstance();
		superiorPanel = p;
		world = WorldMap.getInstance();
		cellToDraw = new ArrayList<Point>();
		this.setPreferredSize(new Dimension(52 * 2 * size, 52 * size));
		addMouseListener(new RPanelMouseListener(this, size));
		addMouseMotionListener(new RPanelMouseListener(this, size));
	}
	
	/**
	 * @return the currentDirection
	 */
	public Direction getCurrentDirection() {
		return currentDirection;
	}
	
	/**
	 * @param currentDirection the currentDirection to set
	 */
	public void setCurrentDirection(Direction currentDirection) {
		this.currentDirection = currentDirection;
	}

	/**
	 * @param editDirection
	 *            the editDirection to set
	 */
	public void setEditDirection(boolean editDirection) {
		this.editDirection = editDirection;
	}

	/**
	 * @return the editDirection
	 */
	public boolean isEditDirection() {
		return editDirection;
	}

	/**
	 * @return the startDraw
	 */
	public boolean isStartDraw() {
		return startDraw;
	}

	public void setStartDraw(boolean flag) {
		startDraw = flag;
	}

	/**
	 * @return the addAnAgent
	 */
	public boolean isAddAnAgent() {
		return addAnAgent;
	}

	public void setAddAnAgent(boolean flag) {
		addAnAgent = flag;
	}

	public void addCellToDraw(Point p) {
		cellToDraw.add(p);
	}

	public List<Point> getCellsToDraw() {
		return Collections.unmodifiableList(cellToDraw);
	}

	public void clearCellToDraw() {
		cellToDraw.clear();
	}

	/**
	 * @return the superiorPanel
	 */
	public PrincipalPanel getSuperiorPanel() {
		return superiorPanel;
	}

	@Override
	protected void paintComponent(Graphics graphics) {
		super.paintComponent(graphics);
		for (int i = 0; i < world.getWorldSize() * 2; i++) {
			for (int j = 0; j < world.getWorldSize(); j++) {
				WorldObject element = world.getElement(i, j);

				if (element instanceof CarAgent) {
					graphics.drawImage(images.getStreet(), i * size + 20, j	* size + 20, null);
					graphics.drawImage(images.getCar(), i * size + 20, j * size	+ 20, null);
				} else if (element instanceof Grass) {
					graphics.drawImage(images.getGrass(), i * size + 20, j * size + 20, null);
				} else if (element instanceof House) {
					graphics.drawImage(images.getGrass(), i * size + 20, j * size + 20, null);
					graphics.drawImage(images.getHouse(), i * size + 20, j * size + 20, null);
				} else if (element instanceof Street) {
					graphics.drawImage(images.getStreet(), i * size + 20, j * size + 20, null);
					if( isEditDirection() ) {
						drawDirection(graphics, element, i, j);
					}
				}
			}
		}
	}
	
	private void drawDirection(Graphics graphics, WorldObject element, int i, int j) {
		Street street = (Street) element;
		Direction dir = street.getDirection();
		if( dir.equals(Direction.NORTH)) {
			graphics.drawImage(images.getNorth(), i * size + 20, j * size + 20, null);
		} else if( dir.equals(Direction.SOUTH)) {
			graphics.drawImage(images.getSouth(), i * size + 20, j * size + 20, null);
		} else if( dir.equals(Direction.WEST)) {
			graphics.drawImage(images.getWest(), i * size + 20, j * size + 20, null);
		} else if( dir.equals(Direction.EAST)) {
			graphics.drawImage(images.getEast(), i * size + 20, j * size + 20, null);
		} else if( dir.equals(Direction.NORTH_WEST)) {
			graphics.drawImage(images.getNorthWest(), i * size + 20, j * size + 20, null);
		} else if( dir.equals(Direction.NORTH_EAST)) {
			graphics.drawImage(images.getNorthEast(), i * size + 20, j * size + 20, null);
		} else if( dir.equals(Direction.SOUTH_WEST)) {
			graphics.drawImage(images.getSouthWest(), i * size + 20, j * size + 20, null);
		} else if( dir.equals(Direction.SOUTH_EAST)) {
			graphics.drawImage(images.getSouthEast(), i * size + 20, j * size + 20, null);
		} else if( dir.equals(Direction.NORTH_SOUTH)) {
			graphics.drawImage(images.getNorthSouth(), i * size + 20, j * size + 20, null);
		} else if( dir.equals(Direction.WEST_EAST)) {
			graphics.drawImage(images.getWestEast(), i * size + 20, j * size + 20, null);
		} else if( dir.equals(Direction.NORTH_WEST_EAST)) {
			graphics.drawImage(images.getNorthWestEast(), i * size + 20, j * size + 20, null);
		} else if( dir.equals(Direction.SOUTH_WEST_EAST)) {
			graphics.drawImage(images.getSouthWestEast(), i * size + 20, j * size + 20, null);
		} else if( dir.equals(Direction.WEST_NORTH_SOUTH)) {
			graphics.drawImage(images.getWestNorthSouth(), i * size + 20, j * size + 20, null);
		} else if( dir.equals(Direction.EAST_NORTH_SOUTH)) {
			graphics.drawImage(images.getEastNorthSouth(), i * size + 20, j * size + 20, null);
		}
	}
}
