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
package spa.simone.cityagents.gui.listeners;

import spa.simone.cityagents.gui.PrincipalPanel;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * @author Deep Blue Team
 */
public class ObjectButtonListner extends MouseAdapter {

    private PrincipalPanel panel;
    private int choice;
    private boolean startButton;

    public ObjectButtonListner(PrincipalPanel panel) {
        this.panel = panel;
        startButton = true;
    }

    public ObjectButtonListner(PrincipalPanel panel, int choice) {
        this.panel = panel;
        this.choice = choice;
        startButton = false;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (startButton) {
            panel.removeLeftPanel();
        } else {
            panel.currentChoice = choice;
        }
    }

}
