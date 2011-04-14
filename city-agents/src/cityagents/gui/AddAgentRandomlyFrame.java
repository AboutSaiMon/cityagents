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

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 *
 * @author Deep Blue Team
 */
public class AddAgentRandomlyFrame extends JFrame 
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private PrincipalFrame frame;
	/**
	 * 
	 */
	public AddAgentRandomlyFrame( PrincipalFrame f ) 
	{
		frame = f;
		this.setTitle( "Add Agent Randomly" );
		this.setResizable( false);
		this.setVisible( true );
		this.setDefaultCloseOperation( JFrame.DISPOSE_ON_CLOSE );
		this.setSize( 270, 150 );
		this.setLocation( 300, 300 );
		
		JPanel panel = new JPanel();
		
		panel.setLayout( new GridLayout( 3, 2 ) );			
		
		JLabel numberOfAgentsLabel = new JLabel( "Number of agents" );		
		panel.add( numberOfAgentsLabel );
	
		final JTextField numberOfAgentsNumber = new JTextField();
		numberOfAgentsNumber.setToolTipText( "Number of agents" );		
		panel.add( numberOfAgentsNumber );
		
		JLabel timerLabel = new JLabel( "Timer" );		
		panel.add( timerLabel );
		
		final JTextField timerNumber = new JTextField();
		timerNumber.setToolTipText( "Distance from the next inserted" );
		panel.add( timerNumber );
				
		JButton generate = new JButton( "Set" );
		generate.addActionListener( new ActionListener() 
		{			
			@Override
			public void actionPerformed( ActionEvent arg0 ) 
			{						
				// TODO Auto-generated method stub
				String numberOfAgentsText = numberOfAgentsNumber.getText();
				String timerText = timerNumber.getText();
				try
				{
					int agents = Integer.parseInt( numberOfAgentsText );					
					long timer = Long.parseLong( timerText );
					
					if( agents > 10 || agents < 0 || timer < 0 || timer > 60 )
					{						
						throw new Exception("Bad values intervals");
					}				
					frame.seconds = timer;
					frame.numberOfAgentsToAdd = agents;
					JOptionPane.showMessageDialog( null, "Save completed", "OK", JOptionPane.OK_OPTION );					
				}				
				catch( Exception ex )
				{
					ex.printStackTrace();
					JOptionPane.showMessageDialog( null, ex.toString(), "Error", JOptionPane.ERROR_MESSAGE );					
				}				
			}
		});
		
		panel.add( generate );	
		
		this.setContentPane( panel );
	}
}
