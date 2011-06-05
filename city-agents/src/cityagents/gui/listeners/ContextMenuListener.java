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
package cityagents.gui.listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenuItem;

import cityagents.core.WorldMap;
import cityagents.gui.PrincipalFrame;
import cityagents.gui.RightPanel;
import cityagents.gui.util.Labels;

/**
 * 
 * @author Deep Blue Team
 */
public class ContextMenuListener implements ActionListener {

	private static ContextMenuListener thisInstance;
	private RightPanel panel;

	private ContextMenuListener() {
		panel = PrincipalFrame.getInstance().getRightPanel();
	}

	public static ContextMenuListener getInstance() {
		if (thisInstance == null) {
			thisInstance = new ContextMenuListener();
		}
		return thisInstance;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		JMenuItem item = (JMenuItem) e.getSource();
		if( item.getText().equals(Labels.EDIT_DIRECTIONS)) {
			panel.setEditDirection(true);
			panel.repaint();
		} else if( item.getText().equals(Labels.GENERATE_GRAPH)) {
			WorldMap.getInstance().generateWorldGraph();
		}
	}

}
