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

import jade.core.AID;
import jade.core.behaviours.Behaviour;
import jade.lang.acl.ACLMessage;
import spa.simone.cityagents.core.MessageContent;
import spa.simone.cityagents.core.agents.CarAgent;

import java.util.List;

/**
 * @author Deep Blue Team
 */
public class SendMessagesBehaviour extends Behaviour {
    /**
     *
     */
    private static final long serialVersionUID = 6361518040672645588L;

    MessageContent messageContent;
    List<CarAgent> receivers;

    /**
     *
     */
    public SendMessagesBehaviour(MessageContent messageContent, List<CarAgent> receivers) {
        this.messageContent = messageContent;
        this.receivers = receivers;
    }

    @Override
    public void action() {
        sendMessage(messageContent, receivers);
    }

    @Override
    public boolean done() {
        return true;
    }

    private void sendMessage(MessageContent messageContent, List<CarAgent> receivers) {
        ACLMessage message = new ACLMessage(ACLMessage.PROPOSE);
        message.setContent("MSG:::" +
                messageContent.getCrossroad().getPosition().x + ":::" +
                messageContent.getCrossroad().getPosition().y + ":::" +
                messageContent.getSpeed() + ":::" +
                messageContent.getTraffic());

        // Set all recipients.
        for (CarAgent c : receivers) {
            message.addReceiver(new AID(c.getLocalName(), AID.ISLOCALNAME));
        }
        myAgent.send(message);
    }
}
