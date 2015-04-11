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
package spa.simone.cityagents.core;

import java.io.Serializable;

/**
 * @author Deep Blue Team
 */
public class MessageContent implements Serializable {
    /**
     *
     */
    private static final long serialVersionUID = 6162966011809057417L;

    Crossroad crossroad;
    int speed;
    int traffic;

    /**
     *
     */
    public MessageContent(Crossroad crossroad, int mySpeed, int traffic) {
        this.crossroad = crossroad;
        this.speed = mySpeed;
        this.traffic = traffic;
    }

    public Crossroad getCrossroad() {
        return crossroad;
    }

    public void setCrossroad(Crossroad crossroad) {
        this.crossroad = crossroad;
    }

    /**
     * @return the Speed
     */
    public int getSpeed() {
        return speed;
    }

    /**
     * @param mySpeed the speed to set
     */
    public void setSpeed(int mySpeed) {
        this.speed = mySpeed;
    }

    /**
     * @return the traffic
     */
    public int getTraffic() {
        return traffic;
    }

    /**
     * @param traffic the traffic to set
     */
    public void setTraffic(int traffic) {
        this.traffic = traffic;
    }
}
