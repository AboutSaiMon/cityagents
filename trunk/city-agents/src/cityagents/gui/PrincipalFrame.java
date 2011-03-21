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

/**
 * 
 * @author Deep Blue Team
 */
public class PrincipalFrame extends JFrame {

	private static final long serialVersionUID = 1L;

	final PrincipalPanel panel;
	
	public PrincipalFrame() {
		panel = new PrincipalPanel( this );
		Toolkit toolkit = Toolkit.getDefaultToolkit();
		Dimension dimension = toolkit.getScreenSize();		
		
		this.setContentPane( panel );
		this.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );		
		
		this.setSize( dimension );		
		this.setTitle( "Traffic World" );
		this.setResizable( false );
		this.setVisible( true );
		
		JMenuBar menuBar = new JMenuBar();
		JMenu file = new JMenu( "File" );
		
		JMenuItem load = new JMenuItem( "Open" );		
		file.add( load );		
				
		JMenu world = new JMenu( "World" );
		JMenuItem increaseDimension = new JMenuItem( "Increase Dimension" );
		increaseDimension.addActionListener( new ActionListener() 
		{
			
			@Override
			public void actionPerformed( ActionEvent arg0 ) 
			{
				// TODO Auto-generated method stub
				increase();
			}
		});
		JMenuItem decreaseDimension = new JMenuItem( "Decrease Dimension" );
		decreaseDimension.addActionListener( new ActionListener() 
		{
			
			@Override
			public void actionPerformed( ActionEvent e ) 
			{
				// TODO Auto-generated method stub
				decrease();
			}
		});
		this.setFocusable( true );
		this.addKeyListener( new KeyListener() 
		{
			
			@Override
			public void keyTyped( KeyEvent e ) 
			{
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void keyReleased( KeyEvent e ) 
			{
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void keyPressed( KeyEvent e ) 
			{
				// TODO Auto-generated method stub
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
		
		world.add( increaseDimension );
		world.add( decreaseDimension );
		
		JMenu about = new JMenu( "About" );
		
		JMenuItem help = new JMenuItem( "Help" );
		about.add( help );
		
		menuBar.add( file );
		menuBar.add( world );
		menuBar.add( about );

		this.setJMenuBar( menuBar );
	}
	
	private void increase()
	{
		if( panel.getRight().worldSize < 50 )
		{
			panel.getRight().worldSize++;
			panel.getRight().world = new int[ panel.getRight().worldSize * 2 ][ panel.getRight().worldSize ];
			panel.getRight().repaint();
		}			
	}
	
	private void decrease()
	{
		if( panel.getRight().worldSize > 1 )
		{
			panel.getRight().worldSize--;
			panel.getRight().world = new int[ panel.getRight().worldSize * 2 ][ panel.getRight().worldSize ];
			panel.getRight().repaint();
		}
	}
}