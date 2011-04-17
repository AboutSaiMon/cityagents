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

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import cityagents.gui.PrincipalFrame;

/**
 *
 * @author Deep Blue Team
 */
public class CaKeyListener extends KeyAdapter {

	@Override
	public void keyPressed(KeyEvent e) {
		if (e.getKeyChar() == '+') {
			PrincipalFrame.getInstance().increaseDimension();
		} else if (e.getKeyChar() == '-') {
			PrincipalFrame.getInstance().decreaseDimension();
		}
	}
	
}
