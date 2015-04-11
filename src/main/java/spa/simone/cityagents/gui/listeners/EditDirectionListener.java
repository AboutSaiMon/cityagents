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

import spa.simone.cityagents.core.Direction;
import spa.simone.cityagents.gui.PrincipalFrame;
import spa.simone.cityagents.gui.RightPanel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static spa.simone.cityagents.gui.util.Labels.*;

/**
 * @author Deep Blue Team
 */
public class EditDirectionListener implements ActionListener {

    private RightPanel panel;

    /**
     *
     */
    public EditDirectionListener() {
        panel = PrincipalFrame.getInstance().getRightPanel();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JMenuItem source = (JMenuItem) e.getSource();
        if (source.getText().equals(NORTH)) {
            panel.setCurrentDirection(Direction.NORTH);
        } else if (source.getText().equals(SOUTH)) {
            panel.setCurrentDirection(Direction.SOUTH);
        } else if (source.getText().equals(WEST)) {
            panel.setCurrentDirection(Direction.WEST);
        } else if (source.getText().equals(EAST)) {
            panel.setCurrentDirection(Direction.EAST);
        } else if (source.getText().equals(NORTH_WEST)) {
            panel.setCurrentDirection(Direction.NORTH_WEST);
        } else if (source.getText().equals(NORTH_EAST)) {
            panel.setCurrentDirection(Direction.NORTH_EAST);
        } else if (source.getText().equals(SOUTH_WEST)) {
            panel.setCurrentDirection(Direction.SOUTH_WEST);
        } else if (source.getText().equals(SOUTH_EAST)) {
            panel.setCurrentDirection(Direction.SOUTH_EAST);
        } else if (source.getText().equals(NORTH_SOUTH)) {
            panel.setCurrentDirection(Direction.NORTH_SOUTH);
        } else if (source.getText().equals(WEST_EAST)) {
            panel.setCurrentDirection(Direction.WEST_EAST);
        } else if (source.getText().equals(NORTH_WEST_EAST)) {
            panel.setCurrentDirection(Direction.NORTH_WEST_EAST);
        } else if (source.getText().equals(SOUTH_WEST_EAST)) {
            panel.setCurrentDirection(Direction.SOUTH_WEST_EAST);
        } else if (source.getText().equals(WEST_NORTH_SOUTH)) {
            panel.setCurrentDirection(Direction.WEST_NORTH_SOUTH);
        } else if (source.getText().equals(EAST_NORTH_SOUTH)) {
            panel.setCurrentDirection(Direction.EAST_NORTH_SOUTH);
        } else if (source.getText().equals(CLOSE)) {
            panel.setEditDirection(false);
            panel.repaint();
        }
    }

}
