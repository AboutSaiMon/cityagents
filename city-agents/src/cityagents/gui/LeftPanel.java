package cityagents.gui;

import static cityagents.core.WorldObject.CAR;
import static cityagents.core.WorldObject.GRASS;
import static cityagents.core.WorldObject.HOUSE;
import static cityagents.core.WorldObject.STREET;

import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

import cityagents.gui.listeners.ObjectButtonListner;

public class LeftPanel extends JPanel {

	/**
	 * 
	 */
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