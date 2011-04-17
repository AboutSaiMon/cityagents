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
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;

import javax.swing.JFrame;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import cityagents.core.WorldMap;

/**
 * 
 * @author Deep Blue Team
 */
public class PrincipalFrame extends JFrame {
	private static final long serialVersionUID = 1L;
	private static PrincipalFrame thisInstance;
	private PrincipalPanel principalPanel;
	private CaMenuBar menuBar;

	WorldMap world;

	int numberOfAgentsToAdd;
	long seconds;

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
		addKeyListener();
		// sets the visibility of the frame
		setVisible(true);
	}

	private void initLookAndFeel() {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException e) {
		} catch (InstantiationException e) {
		} catch (IllegalAccessException e) {
		} catch (UnsupportedLookAndFeelException e) {
		}
	}

	public static PrincipalFrame getInstance() {
		if (thisInstance == null) {
			thisInstance = new PrincipalFrame();
		}
		return thisInstance;
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

	/*
	void addBehaviour() {
		if (numberOfAgentsToAdd != -1 && seconds != 0) {
			myAgent.addBehaviour(new AddAgentRandomlyBehaviour(myAgent,
					seconds * 1000, numberOfAgentsToAdd));
		}
	}
	*/
	
	private void addKeyListener() {
		this.setFocusable(true);
		this.addKeyListener(new KeyListener() {

			@Override
			public void keyTyped(KeyEvent e) {
			}

			@Override
			public void keyReleased(KeyEvent e) {
			}

			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyChar() == '+') {
					increaseDimension();
				} else if (e.getKeyChar() == '-') {
					decreaseDimension();
				}
			}
		});
	}

	public PrincipalPanel getPanel() {
		return principalPanel;
	}

	public void disableMenu() {
		menuBar.disableMenu();
	}
	
	public void loadMapFromFile(File file) {
		/*
		FileInputStream fis = null;
		ObjectInputStream ois = null;
		
		try {
			fis = new FileInputStream("maps/world.ser");
			ois = new ObjectInputStream(fis);
			// car = (Car) ois.readObject();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		*/
	}

}