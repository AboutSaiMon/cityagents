/**
 * Describe Project.
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

import jade.core.Agent;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import cityagents.behaviours.AddAgentRandomlyBehaviour;
import cityagents.core.WorldMap;

/**
 * 
 * @author Deep Blue Team
 */
public class PrincipalFrame extends JFrame 
{
	private static final long serialVersionUID = 1L;

	final PrincipalPanel panel;
	WorldMap world;
	Agent myAgent;
	
	private JMenuItem addNewAgent;
	private JMenuItem increaseDimension;
	private JMenuItem decreaseDimension;
	private JMenuItem addAgentsRandomly;
	private JMenuItem load;
	
	int numberOfAgentsToAdd;
	long seconds;
	
	public PrincipalFrame( Agent agent )
	{
		myAgent = agent;
		numberOfAgentsToAdd = -1;
		seconds = 0;
		panel = new PrincipalPanel( this );
		world = WorldMap.getInstance();
		
		Toolkit toolkit = Toolkit.getDefaultToolkit();
		Dimension dimension = toolkit.getScreenSize();
		
		this.setContentPane( panel );
		this.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
		
		this.setSize( dimension );
		this.setTitle( "City Agents" );
		this.setResizable( false );
		this.setVisible( true );
		
		JMenuBar menuBar = new JMenuBar();
		JMenu file = new JMenu( "File" );
		
		load = new JMenuItem( "Open" );		
		file.add( load );
				
		JMenu world = new JMenu( "World" );
		increaseDimension = new JMenuItem( "Increase Dimension" );
		increaseDimension.addActionListener( new ActionListener() 
		{
			
			@Override
			public void actionPerformed( ActionEvent arg0 )
			{
				increase();
			}
		});
		
		decreaseDimension = new JMenuItem( "Decrease Dimension" );
		decreaseDimension.addActionListener( new ActionListener() 
		{
			
			@Override
			public void actionPerformed( ActionEvent e )
			{
				decrease();
			}
		});
		
		addKeyListener();
		
		world.add( increaseDimension );
		world.add( decreaseDimension );
		
		JMenu agents = new JMenu( "Agents" );
		
		addNewAgent = new JMenuItem( "Add New Agent" );
		addNewAgent.setEnabled( false );
		
		addAgentsRandomly = new JMenuItem( "Add Agents Randomly" );
		addAgentsRandomly.addActionListener( new AddAgentsRandomlyActionListener( this ) );
		
		agents.add( addNewAgent );
		agents.add( addAgentsRandomly );
		
		JMenu about = new JMenu( "About" );
		
		JMenuItem help = new JMenuItem( "Help" );
		about.add( help );
		
		menuBar.add( file );
		menuBar.add( world );
		menuBar.add( agents );
		menuBar.add( about );

		this.setJMenuBar( menuBar );
	}
	
	private void increase()
	{
		Integer size = world.getWorldSize();
		if( size < 50 )
		{
			world.resize( size + 1 );
			panel.getRight().repaint();
		}			
	}
	
	private void decrease()
	{
		Integer size = world.getWorldSize();
		if( size > 1 )
		{
			world.resize( size - 1 );
			panel.getRight().repaint();
		}
	}
	
	void addBehaviour()
	{
		if( numberOfAgentsToAdd != -1 && seconds != 0 )
		{
			myAgent.addBehaviour( new AddAgentRandomlyBehaviour( myAgent, seconds * 1000, numberOfAgentsToAdd ) );
		}
	}
	
	private void addKeyListener()
	{
		this.setFocusable( true );
		this.addKeyListener( new KeyListener()
		{
			
			@Override
			public void keyTyped( KeyEvent e ) 
			{
			}
			
			@Override
			public void keyReleased( KeyEvent e ) 
			{	
			}
			
			@Override
			public void keyPressed( KeyEvent e ) 
			{
				if( e.getKeyChar() == '+' )
				{
					increase();
				}
				else if( e.getKeyChar() == '-' )
				{
					decrease();
				}
			}
		});
	}

	public PrincipalPanel getPanel() 
	{
		return panel;
	}
	
	public void disableMenu()
	{
		addNewAgent.setEnabled( false );
		increaseDimension.setEnabled( false );
		decreaseDimension.setEnabled( false );
		addAgentsRandomly.setEnabled( false );
		load.setEnabled( false );
		addAgentsRandomly.setEnabled( true );		
	}
}