/**
 * 
 * Copyright (C) 2011 Deep Blue Team <see the team details file>
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

import java.awt.BorderLayout;

import javax.swing.JPanel;
import javax.swing.JScrollPane;

import cityagents.core.WorldMap;

/**
 *
 * @author Deep Blue Team
 */
public class PrincipalPanel extends JPanel {

	private static final long serialVersionUID = 1L;

	private LeftPanel left;
	private RightPanel right;
	
	/**
	 * This variable needs to know which element is clicked.
	 */
	int currentChoice;
			
	public PrincipalPanel() 
	{
		super();
		left = new LeftPanel( this );
		right = new RightPanel( this );
		createPanel();
	}

	public LeftPanel getLeft() 
	{
		return left;
	}

	public RightPanel getRight() 
	{
		return right;
	}

	private void createPanel()
	{
		this.setLayout( new BorderLayout() );
		this.add( left, BorderLayout.WEST );
		JScrollPane p = new JScrollPane( right );
		p.setOpaque( false );
		this.add( p, BorderLayout.CENTER );
	}
	
	void removeLeftPanel()
	{
		this.remove( left );
		this.repaint();
		WorldMap world = WorldMap.getInstance();
		world.setEditable( false );
		PrincipalFrame.getInstance().disableMenu();
	}
}