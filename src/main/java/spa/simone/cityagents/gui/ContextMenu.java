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

import cityagents.gui.listeners.ContextMenuListener;
import cityagents.gui.util.Labels;

/**
 * 
 * @author Deep Blue Team
 */
public class ContextMenu extends JPopupMenu {

	private static final long serialVersionUID = 4224252607713811091L;
	private static ContextMenu thisInstance;
	private JMenuItem editDirection;
	private JMenuItem generateGraph;

	private ContextMenu() {
		editDirection = new JMenuItem(Labels.EDIT_DIRECTIONS);
		editDirection.addActionListener(ContextMenuListener.getInstance());
		add(editDirection);
		generateGraph = new JMenuItem(Labels.GENERATE_GRAPH);
		generateGraph.addActionListener(ContextMenuListener.getInstance());
		add(generateGraph);
	}

	public static ContextMenu getInstance() {
		if (thisInstance == null) {
			thisInstance = new ContextMenu();
		}
		return thisInstance;
	}

}