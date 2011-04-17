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
package cityagents.gui;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
*
* @author Deep Blue Team
*/
public class LoadMapFrame extends JFileChooser {
	
	private static final long serialVersionUID = -8344106388214626875L;
	private static LoadMapFrame instance;
	
	/**
	 * 
	 */
	private LoadMapFrame() {
		super();
		addChoosableFileFilter(new FileNameExtensionFilter("MAP files", "map"));
	}
	
	public static LoadMapFrame getInstance() {
		if( instance == null ) {
			instance = new LoadMapFrame();
		}
		return instance;
	}

}
