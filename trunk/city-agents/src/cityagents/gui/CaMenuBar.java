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

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JSeparator;

import cityagents.gui.listeners.DecreaseDimensionListener;
import cityagents.gui.listeners.ExitListener;
import cityagents.gui.listeners.IncreaseDimensionListener;
import cityagents.gui.listeners.LoadMapListener;
import cityagents.gui.listeners.StoreMapListener;

/**
*
* @author Deep Blue Team
*/
public class CaMenuBar extends JMenuBar {
	
	private static final long serialVersionUID = -3591968585365805316L;
	
	private JMenu file;
	private JMenuItem loadMap;
	private JMenuItem storeMap;
	private JMenuItem exit;
	
	private JMenu world;
	private JMenuItem increaseDimension;
	private JMenuItem decreaseDimension;
	
	private JMenu agent;
	private JMenuItem addNewAgent;
	private JMenuItem addRandomAgent;
	
	private JMenu help;
	private JMenuItem about;
	
	/**
	 * 
	 */
	public CaMenuBar() {
		super();
		setFileMenu();
		setWorldMenu();
		setAgentMenu();
		setHelpMenu();
	}
	
	private void setFileMenu() {
		file = new JMenu("File");
		
		loadMap = new JMenuItem("Load map");
		loadMap.addActionListener(new LoadMapListener());
		storeMap = new JMenuItem("Store map");
		storeMap.addActionListener(new StoreMapListener());
		exit = new JMenuItem("Exit");
		exit.addActionListener(new ExitListener());
		
		file.add(loadMap);
		file.add(storeMap);
		file.add(new JSeparator());
		file.add(exit);
		
		add(file);
	}
	
	private void setWorldMenu() {
		world = new JMenu("World");
		
		increaseDimension = new JMenuItem("Increase dimension");
		increaseDimension.addActionListener(new IncreaseDimensionListener());
		decreaseDimension = new JMenuItem("Decrease dimension");
		decreaseDimension.addActionListener(new DecreaseDimensionListener());
		
		world.add(increaseDimension);
		world.add(decreaseDimension);
		
		add(world);
	}
	
	private void setAgentMenu() {
		agent = new JMenu("Agent");
		
		addNewAgent = new JMenuItem("Add new");
		addNewAgent.setEnabled(false);
		addRandomAgent = new JMenuItem("Add random");
		
		agent.add(addNewAgent);
		agent.add(addRandomAgent);
		
		add(agent);
	}
	
	private void setHelpMenu() {
		help = new JMenu("Help");
		
		about = new JMenuItem("About City Agents");
		help.add(about);
		
		add(help);
	}
	
	public void disableMenu() {
		addNewAgent.setEnabled(false);
		increaseDimension.setEnabled(false);
		decreaseDimension.setEnabled(false);
		addRandomAgent.setEnabled(false);
		loadMap.setEnabled(false);
	}

}
