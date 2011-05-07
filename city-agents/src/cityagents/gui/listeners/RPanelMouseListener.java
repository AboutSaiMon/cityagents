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

import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JOptionPane;

import cityagents.core.Direction;
import cityagents.core.Street;
import cityagents.core.WorldMap;
import cityagents.core.WorldObject;
import cityagents.core.agents.CarAgent;
import cityagents.gui.ContextMenu;
import cityagents.gui.EditDirectionContextMenu;
import cityagents.gui.PrincipalFrame;
import cityagents.gui.PrincipalPanel;
import cityagents.gui.RightPanel;

/**
 * 
 * @author Deep Blue Team
 */
public class RPanelMouseListener extends MouseAdapter {

	private PrincipalPanel superiorPanel;
	private RightPanel panel;
	private int size;
	private WorldMap world;
	private Point agentStart;

	public RPanelMouseListener(RightPanel panel, int size) {
		this.panel = panel;
		this.size = size;
		superiorPanel = panel.getSuperiorPanel();
		world = WorldMap.getInstance();
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		// si segna le celle selezionate
		if (panel.isStartDraw()) {
			int x = e.getX();
			int y = e.getY();

			int i = (x - 20) / size;
			int j = (y - 20) / size;

			if (i >= 0 && i < (world.getWorldSize() * 2) && j >= 0
					&& j < world.getWorldSize()) {
				Point p = new Point(i, j);
				panel.addCellToDraw(p);
			}
		}
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// se è stato cliccato il tasto sinistro
		if (e.getButton() == MouseEvent.BUTTON1) {
			// se è in modalità di editing delle direzioni
			if (panel.isEditDirection()) {
				Direction direction = panel.getCurrentDirection();
				for (Point p : panel.getCellsToDraw()) {
					// setta la direzione di tutte le celle cliccate
					world.setDirection(direction, p.x, p.y);
				}
			} else {
				for (Point p : panel.getCellsToDraw()) {
					int i = p.x;
					int j = p.y;
					switch (superiorPanel.currentChoice) {
					case WorldObject.STREET:
						world.setStreet(i, j);
						break;

					case WorldObject.GRASS:
						world.setGrass(i, j);
						break;

					case WorldObject.HOUSE:
						world.setHouse(i, j);
						break;

					default:
						break;
					}
				}
			}
			panel.repaint();
			panel.clearCellToDraw();
			panel.setStartDraw(false);
		}
	}

	@Override
	public void mousePressed(MouseEvent e) {
		if (e.getButton() == MouseEvent.BUTTON1) {
			panel.setStartDraw(true);
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// se non è nella modalità di editing delle direzioni
		if (!panel.isEditDirection()) {
			// ed è stato premuto il tasto destro
			if (e.getButton() == MouseEvent.BUTTON3) {
				/*
				 * with right click can cancel the insert. addAnAgent = false;
				 * agentStart = null;
				 */

				// visualizza il menù contestuale per l'editing delle direzioni
				ContextMenu.getInstance().show(e.getComponent(), e.getX(), e.getY());
			} else if (e.getButton() == MouseEvent.BUTTON1) {
				// se invece è stato premuto il tasto sinistro
				panel.setStartDraw(true);
				int x = e.getX();
				int y = e.getY();

				int i = (x - 20) / size;
				int j = (y - 20) / size;

				if (i >= 0 && i < (world.getWorldSize() * 2) && j >= 0
						&& j < world.getWorldSize()) {
					switch (superiorPanel.currentChoice) {
					case WorldObject.STREET:
						world.setStreet(i, j);
						break;
					case WorldObject.GRASS:
						world.setGrass(i, j);
						break;
					case WorldObject.HOUSE:
						world.setHouse(i, j);
					case WorldObject.CAR:
						if (panel.isAddAnAgent()) {
							if (world.getElement(i, j) instanceof Street) {
								CarAgent c = new CarAgent();
								Point[] arguments = new Point[2];
								arguments[0] = new Point(agentStart);
								arguments[1] = new Point(i, j);
								c.setArguments(arguments);
								world.setCar(agentStart.x, agentStart.y, c);
								panel.setAddAnAgent(false);
								agentStart = null;
								PrincipalFrame frame = PrincipalFrame
										.getInstance();
								JOptionPane.showMessageDialog(frame,
										"Added Agent", "Added Agent",
										JOptionPane.INFORMATION_MESSAGE);
								if (!world.isEditable())
									world.startAgent(i, j);
							}
						} else {
							if (world.getElement(i, j) instanceof Street) {
								panel.setAddAnAgent(true);
								agentStart = new Point(i, j);
								PrincipalFrame frame = PrincipalFrame
										.getInstance();
								JOptionPane.showMessageDialog(frame,
										"Set Destination.", "Destination",
										JOptionPane.INFORMATION_MESSAGE);
							}
						}
						break;
					}
				}
			}
			panel.repaint();
		} else {
			if (e.getButton() == MouseEvent.BUTTON3) {
			// altrimenti se ci troviamo già nella modalità di editing delle
			// direzioni e viene cliccato il tasto tre, viene visualizzato il menù contestuale di editing
			EditDirectionContextMenu.getInstance().show(e.getComponent(),
					e.getX(), e.getY());
			} else if (e.getButton() == MouseEvent.BUTTON1 ) {
				panel.setStartDraw(true);
				int x = e.getX();
				int y = e.getY();

				int i = (x - 20) / size;
				int j = (y - 20) / size;

				if (i >= 0 && i < (world.getWorldSize() * 2) && j >= 0 && j < world.getWorldSize()) {
					Direction direction = panel.getCurrentDirection();
					world.setDirection(direction, i, j);
				}
			}
		}
	}

}
