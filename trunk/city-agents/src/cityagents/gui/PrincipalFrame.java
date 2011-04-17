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
import java.awt.Toolkit;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInput;
import java.io.ObjectInputStream;

import javax.swing.JFrame;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import cityagents.core.WorldMap;
import cityagents.gui.listeners.CaKeyListener;
import cityagents.util.Logger;

/**
 * 
 * @author Deep Blue Team
 */
public class PrincipalFrame extends JFrame {
	
	private static final long serialVersionUID = 1L;
	
	private static PrincipalFrame thisInstance;
	private PrincipalPanel principalPanel;
	private CaMenuBar menuBar;
	
	private WorldMap world;
	private int numberOfAgentsToAdd;
	private long seconds;

	private PrincipalFrame() {
		super("City Agents");
		numberOfAgentsToAdd = -1;
		seconds = 0;
		world = WorldMap.getInstance();
		// sets the look and feel of the OS
		initLookAndFeel();
		// gets the screen size
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		// sets the frame size
		setSize(dim.width / 2, dim.height / 2);
		// sets the position to the center of the screen
		setLocationRelativeTo(null);
		// sets the default action when the exit button is pressed
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		// adds the main panel
		principalPanel = new PrincipalPanel();
		setContentPane(principalPanel);
		// adds the main menu
		menuBar = new CaMenuBar();
		setJMenuBar(menuBar);
		// adds the key listener
		addKeyListener(new CaKeyListener());
		// sets the visibility of the frame
		setVisible(true);
	}
	
	public static PrincipalFrame getInstance() {
		if (thisInstance == null) {
			thisInstance = new PrincipalFrame();
		}
		return thisInstance;
	}
	
	/**
	 * @return the seconds
	 */
	public long getSeconds() {
		return seconds;
	}
	
	/**
	 * @param seconds the seconds to set
	 */
	public void setSeconds(long seconds) {
		this.seconds = seconds;
	}
	
	/**
	 * @return the numberOfAgentsToAdd
	 */
	public int getNumberOfAgentsToAdd() {
		return numberOfAgentsToAdd;
	}
	
	/**
	 * @param numberOfAgentsToAdd the numberOfAgentsToAdd to set
	 */
	public void setNumberOfAgentsToAdd(int numberOfAgentsToAdd) {
		this.numberOfAgentsToAdd = numberOfAgentsToAdd;
	}
	
	public void increaseDimension() {
		Integer size = world.getWorldSize();
		if (size < 50) {
			world.resize(size + 1);
			principalPanel.getRight().repaint();
		}
	}
	
	public void decreaseDimension() {
		Integer size = world.getWorldSize();
		if (size > 1) {
			world.resize(size - 1);
			principalPanel.getRight().repaint();
		}
	}
	
	public PrincipalPanel getPanel() {
		return principalPanel;
	}
	
	public void disableMenu() {
		menuBar.disableMenu();
	}
	
	public void loadMapFromFile(File file) {
		InputStream stream = null;
		InputStream buffer = null;
		ObjectInput input = null;
		try {
			stream = new FileInputStream(file);
			buffer = new BufferedInputStream(stream);
			input = new ObjectInputStream(buffer);
			world = (WorldMap) input.readObject();
		} catch (FileNotFoundException e) {
			Logger.log(this, e.getMessage());
		} catch (IOException e) {
			Logger.log(this, e.getMessage());
		} catch (ClassNotFoundException e) {
			Logger.log(this, e.getMessage());
		} finally {
			if( input != null ) {
				try {
					input.close();
				} catch (IOException e) {
					Logger.log(this, e.getMessage());
				}
			}
		}
	}

	private void initLookAndFeel() {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException e) {
			Logger.log(this, e.getMessage());
		} catch (InstantiationException e) {
			Logger.log(this, e.getMessage());
		} catch (IllegalAccessException e) {
			Logger.log(this, e.getMessage());
		} catch (UnsupportedLookAndFeelException e) {
			Logger.log(this, e.getMessage());
		}
	}

	/*
	 * void addBehaviour() { if (numberOfAgentsToAdd != -1 && seconds != 0) {
	 * myAgent.addBehaviour(new AddAgentRandomlyBehaviour(myAgent, seconds *
	 * 1000, numberOfAgentsToAdd)); } }
	 */

}