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
package cityagents.gui.util;

import java.awt.Image;
import java.awt.MediaTracker;
import java.awt.Toolkit;
import java.io.File;

import javax.swing.JPanel;

/**
 * 
 * @author Deep Blue Team
 */
public class ImagesHandler {

	private static String IMAGES_PATH = "src" + File.separator + "resources"
			+ File.separator;
	private static ImagesHandler instance = null;

	private Image street = null;
	private Image streetButton = null;
	private Image grass = null;
	private Image grassButton = null;
	private Image house = null;
	private Image houseButton = null;
	private Image car = null;
	private Image carNorth = null;
	private Image carSouth = null;
	private Image carEast = null;
	private Image carWest = null;
	private Image north = null;
	private Image south = null;
	private Image west = null;
	private Image east = null;
	private Image northWest = null;
	private Image northEast = null;
	private Image southWest = null;
	private Image southEast = null;
	private Image northSouth = null;
	private Image westEast = null;
	private Image northWestEast = null;
	private Image southWestEast = null;
	private Image westNorthSouth = null;
	private Image eastNorthSouth = null;

	private ImagesHandler() {
		loadImages();
	}

	public static ImagesHandler getInstance() {
		if (instance == null) {
			instance = new ImagesHandler();
		}
		return instance;
	}

	public Image getGrass() {
		return grass;
	}

	public Image getGrassButton() {
		return grassButton;
	}

	public Image getStreet() {
		return street;
	}

	public Image getStreetButton() {
		return streetButton;
	}

	public Image getHouse() {
		return house;
	}

	public Image getHouseButton() {
		return houseButton;
	}

	public Image getCar() {
		return car;
	}

	/**
	 * @return the carEast
	 */
	public Image getCarEast()
	{
		return carEast;
	}
	
	/**
	 * @return the carSouth
	 */
	public Image getCarSouth()
	{
		return carSouth;
	}
	
	/**
	 * @return the carNorth
	 */
	public Image getCarNorth()
	{
		return carNorth;
	}
	
	/**
	 * @return the carWest
	 */
	public Image getCarWest()
	{
		return carWest;
	}
	
	/**
	 * @return the north
	 */
	public Image getNorth() {
		return north;
	}

	/**
	 * @return the south
	 */
	public Image getSouth() {
		return south;
	}

	/**
	 * @return the west
	 */
	public Image getWest() {
		return west;
	}

	/**
	 * @return the east
	 */
	public Image getEast() {
		return east;
	}

	/**
	 * @return the northWest
	 */
	public Image getNorthWest() {
		return northWest;
	}

	/**
	 * @return the northEast
	 */
	public Image getNorthEast() {
		return northEast;
	}

	/**
	 * @return the southWest
	 */
	public Image getSouthWest() {
		return southWest;
	}

	/**
	 * @return the southEast
	 */
	public Image getSouthEast() {
		return southEast;
	}

	/**
	 * @return the northSouth
	 */
	public Image getNorthSouth() {
		return northSouth;
	}

	/**
	 * @return the westEast
	 */
	public Image getWestEast() {
		return westEast;
	}

	/**
	 * @return the northWestEast
	 */
	public Image getNorthWestEast() {
		return northWestEast;
	}

	/**
	 * @return the southWestEast
	 */
	public Image getSouthWestEast() {
		return southWestEast;
	}

	/**
	 * @return the westNorthSouth
	 */
	public Image getWestNorthSouth() {
		return westNorthSouth;
	}

	/**
	 * @return the eastNorthSouth
	 */
	public Image getEastNorthSouth() {
		return eastNorthSouth;
	}

	private void loadImages() {
		Toolkit t = Toolkit.getDefaultToolkit();

		street = t.getImage(IMAGES_PATH + "street-new.jpg");
		streetButton = t.getImage(IMAGES_PATH + "street-button.jpg");
		grass = t.getImage(IMAGES_PATH + "grass-new.gif");
		grassButton = t.getImage(IMAGES_PATH + "grass-button.gif");
		house = t.getImage(IMAGES_PATH + "house.gif");
		houseButton = t.getImage(IMAGES_PATH + "house-button.gif");
		car = t.getImage(IMAGES_PATH + "car_1.gif");		
		north = t.getImage(IMAGES_PATH + "north.png");
		south = t.getImage(IMAGES_PATH + "south.png");
		west = t.getImage(IMAGES_PATH + "west.png");
		east = t.getImage(IMAGES_PATH + "east.png");
		northWest = t.getImage(IMAGES_PATH + "north-west.png");
		northEast = t.getImage(IMAGES_PATH + "north-east.png");
		southWest = t.getImage(IMAGES_PATH + "south-west.png");
		southEast = t.getImage(IMAGES_PATH + "south-east.png");
		northWest = t.getImage(IMAGES_PATH + "north-west.png");
		northSouth = t.getImage(IMAGES_PATH + "north-south.png");
		westEast = t.getImage(IMAGES_PATH + "west-east.png");
		northWestEast = t.getImage(IMAGES_PATH + "north-west-east.png");
		southWestEast = t.getImage(IMAGES_PATH + "south-west-east.png");
		westNorthSouth = t.getImage(IMAGES_PATH + "west-north-south.png");
		eastNorthSouth = t.getImage(IMAGES_PATH + "east-north-south.png");
		carNorth = t.getImage(IMAGES_PATH + "car_north.gif");
		carWest = t.getImage(IMAGES_PATH + "car_west.gif");
		carEast = t.getImage(IMAGES_PATH + "car_east.gif");
		carSouth = t.getImage(IMAGES_PATH + "car_south.gif");

		MediaTracker mt = new MediaTracker(new JPanel());
		mt.addImage(street, 0);
		mt.addImage(streetButton, 1);
		mt.addImage(grass, 2);
		mt.addImage(grassButton, 3);
		mt.addImage(house, 4);
		mt.addImage(houseButton, 5);
		mt.addImage(car, 6);		
		mt.addImage(north, 7);
		mt.addImage(south, 8);
		mt.addImage(west, 9);
		mt.addImage(east, 10);
		mt.addImage(northWest, 11);
		mt.addImage(northEast, 12);
		mt.addImage(southWest, 13);
		mt.addImage(southEast, 14);
		mt.addImage(northSouth, 15);
		mt.addImage(westEast, 16);
		mt.addImage(northWestEast, 17);
		mt.addImage(southWestEast, 18);
		mt.addImage(westNorthSouth, 19);
		mt.addImage(eastNorthSouth, 20);
		mt.addImage(carNorth, 21);
		mt.addImage(carWest, 22);
		mt.addImage(carEast, 23);
		mt.addImage(carSouth, 24);

		try {
			for (int i = 0; i <= 24; i++) {
				mt.waitForID(i);
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
