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
package spa.simone.cityagents.core.behaviours;

import jade.core.Agent;
import jade.core.behaviours.TickerBehaviour;
import spa.simone.cityagents.core.agents.GraphicAgent;

/**
 * @author Deep Blue Team
 */
public class RefreshPanelBehaviour extends TickerBehaviour {

    /**
     * @param a
     * @param period
     */
    public RefreshPanelBehaviour(Agent a, long period) {
        super(a, period);
    }

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    @Override
    protected void onTick() {
        if (myAgent instanceof GraphicAgent) {
            GraphicAgent g = (GraphicAgent) myAgent;
            g.getPrincipal().getPanel().getRight().repaint();
        } else {
            this.stop();
        }
    }

}
