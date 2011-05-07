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

import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import javax.swing.JSeparator;

import cityagents.gui.listeners.EditDirectionListener;

/**
 *
 * @author Deep Blue Team
 */
public class EditDirectionContextMenu extends JPopupMenu {
	
	private static EditDirectionContextMenu thisInstance;
	private JMenuItem north;
	private JMenuItem south;
	private JMenuItem west;
	private JMenuItem east;
	private JMenuItem close;
	private EditDirectionListener listener;
	
	private EditDirectionContextMenu() {
		listener = new EditDirectionListener();
		north = new JMenuItem("North");
		north.addActionListener(listener);
		south = new JMenuItem("South");
		south.addActionListener(listener);
		west = new JMenuItem("West");
		west.addActionListener(listener);
		east = new JMenuItem("East");
		east.addActionListener(listener);
		close = new JMenuItem("Close");
		close.addActionListener(listener);
		
		add(north);
		add(south);
		add(west);
		add(east);
		add(new JSeparator());
		add(close);
	}
	
	public static EditDirectionContextMenu getInstance() {
		if( thisInstance == null ) {
			thisInstance = new EditDirectionContextMenu();
		}
		return thisInstance;
	}

}
