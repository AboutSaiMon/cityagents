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
package spa.simone.cityagents.gui;

import static spa.simone.cityagents.core.WorldObject.CAR;
import static spa.simone.cityagents.core.WorldObject.GRASS;
import static spa.simone.cityagents.core.WorldObject.HOUSE;
import static spa.simone.cityagents.core.WorldObject.STREET;

import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

import spa.simone.cityagents.gui.listeners.ObjectButtonListner;
import spa.simone.cityagents.gui.util.ImagesHandler;

public class LeftPanel extends JPanel {

	private static final long serialVersionUID = -5538974501052082795L;	
	private PrincipalPanel superiorPanel;
	private JButton street;
	private JButton grass;
	private JButton house;
	private JButton car;
	private JButton start;

	public LeftPanel(PrincipalPanel p) {
		setLayout(new GridLayout(5, 1));
		superiorPanel = p;
		setPreferredSize(new Dimension(150, 50));
		setOpaque(false);
		
		ImagesHandler handler = ImagesHandler.getInstance();
		street = new JButton(new ImageIcon(handler.getStreetButton()));
		street.addMouseListener(new ObjectButtonListner(superiorPanel, STREET));
		grass = new JButton(new ImageIcon(handler.getGrassButton()));
		grass.addMouseListener(new ObjectButtonListner(superiorPanel, GRASS));
		house = new JButton(new ImageIcon(handler.getHouseButton()));
		house.addMouseListener(new ObjectButtonListner(superiorPanel, HOUSE));
		car = new JButton(new ImageIcon(handler.getCar()));
		car.addMouseListener(new ObjectButtonListner(superiorPanel, CAR));
		start = new JButton("START");
		start.addMouseListener(new ObjectButtonListner(superiorPanel));
		add(street, 0);
		add(grass, 1);
		add(house, 2);
		add(car, 3);
		add(start, 4);
	}
}