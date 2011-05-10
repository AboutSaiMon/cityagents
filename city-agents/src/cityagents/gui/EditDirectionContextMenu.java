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

import static cityagents.gui.util.Labels.*;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import javax.swing.JSeparator;

import cityagents.gui.listeners.EditDirectionListener;
import cityagents.gui.util.Labels;

/**
 *
 * @author Deep Blue Team
 */
public class EditDirectionContextMenu extends JPopupMenu {
	
	private static EditDirectionContextMenu thisInstance;
	private JMenu northMenu;
	private JMenu southMenu;
	private JMenu westMenu;
	private JMenu eastMenu;
	
	private JMenuItem north;
	private JMenuItem south;
	private JMenuItem west;
	private JMenuItem east;
	private JMenuItem northWest;
	private JMenuItem northEast;
	private JMenuItem southWest;
	private JMenuItem southEast;
	private JMenuItem northSouth;
	private JMenuItem westEast;
	private JMenuItem northWestEast;
	private JMenuItem southWestEast;
	private JMenuItem westNorthSouth;
	private JMenuItem eastNorthSouth;
	private JMenuItem close;
	private EditDirectionListener listener;
	
	private EditDirectionContextMenu() {
		listener = new EditDirectionListener();
		setNorth();
		setSouth();
		setWest();
		setEast();
		setClose();
	}
	
	private void setNorth() {
		northMenu = new JMenu(NORTH_MENU);
		
		north = new JMenuItem(NORTH);
		north.addActionListener(listener);
		northWest = new JMenuItem(NORTH_WEST);
		northWest.addActionListener(listener);
		northEast = new JMenuItem(NORTH_EAST);
		northEast.addActionListener(listener);
		northSouth = new JMenuItem(NORTH_SOUTH);
		northSouth.addActionListener(listener);
		northWestEast = new JMenuItem(NORTH_WEST_EAST);
		northWestEast.addActionListener(listener);
		
		northMenu.add(north);
		northMenu.add(northWest);
		northMenu.add(northEast);
		northMenu.add(northSouth);
		northMenu.add(northWestEast);
		
		add(northMenu);
	}
	
	private void setSouth() {
		southMenu = new JMenu(SOUTH_MENU);
		
		south = new JMenuItem(SOUTH);
		south.addActionListener(listener);
		southWest = new JMenuItem(SOUTH_WEST);
		southWest.addActionListener(listener);
		southEast = new JMenuItem(SOUTH_EAST);
		southEast.addActionListener(listener);
		southWestEast = new JMenuItem(SOUTH_WEST_EAST);
		southWestEast.addActionListener(listener);
		
		southMenu.add(south);
		southMenu.add(southWest);
		southMenu.add(southEast);
		southMenu.add(southWestEast);
		
		add(southMenu);
	}
	
	private void setWest() {
		westMenu = new JMenu(WEST_MENU);
		
		west = new JMenuItem(WEST);
		west.addActionListener(listener);
		westEast = new JMenuItem(WEST_EAST);
		westEast.addActionListener(listener);
		westNorthSouth = new JMenuItem(WEST_NORTH_SOUTH);
		westNorthSouth.addActionListener(listener);
		
		westMenu.add(west);
		westMenu.add(westEast);
		westMenu.add(westNorthSouth);
		
		add(westMenu);
	}
	
	private void setEast() {
		eastMenu = new JMenu(EAST_MENU);
		
		east = new JMenuItem(EAST);
		east.addActionListener(listener);
		eastNorthSouth = new JMenuItem(EAST_NORTH_SOUTH);
		eastNorthSouth.addActionListener(listener);
		
		eastMenu.add(east);
		eastMenu.add(eastNorthSouth);
		
		add(eastMenu);
	}
	
	private void setClose() {
		add(new JSeparator());
		close = new JMenuItem(CLOSE);
		close.addActionListener(listener);
		add(close);
	}
	
	public static EditDirectionContextMenu getInstance() {
		if( thisInstance == null ) {
			thisInstance = new EditDirectionContextMenu();
		}
		return thisInstance;
	}

}
