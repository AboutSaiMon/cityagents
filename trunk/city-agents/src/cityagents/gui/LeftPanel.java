package cityagents.gui;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

public class LeftPanel extends JPanel
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
		
	private PrincipalPanel superiorPanel;
	private JButton[] buttons;	
	
	public LeftPanel( PrincipalPanel p ) 
	{
	
		// TODO Auto-generated constructor stub
		this.superiorPanel = p;
		this.setPreferredSize( new Dimension( 150, 50 ) );
		this.setOpaque( false );
		
		buttons = new JButton[ superiorPanel.images.length ];
		this.setLayout( new GridLayout( buttons.length, 1 ) );
		for( int i = 0; i < buttons.length; i++ )
		{			
			buttons[ i ] = new JButton( new ImageIcon( superiorPanel.images[ i ] ) );
			this.add( buttons[ i ], i );
		}
		clicked();
	}
	
	private void clicked()
	{
		for( int i = 0; i < buttons.length; i++ )
		{
			final int choice = i;
			buttons[ i ].addMouseListener( new MouseListener() 
			{
				
				@Override
				public void mouseReleased( MouseEvent arg0 ) 
				{
					// TODO Auto-generated method stub
					
				}
				
				@Override
				public void mousePressed( MouseEvent arg0 ) 
				{
					// TODO Auto-generated method stub
					
				}
				
				@Override
				public void mouseExited( MouseEvent arg0 ) 
				{
					// TODO Auto-generated method stub
					
				}
				
				@Override
				public void mouseEntered( MouseEvent arg0 ) 
				{
					// TODO Auto-generated method stub
					
				}
				
				@Override
				public void mouseClicked( MouseEvent arg0 ) 
				{
					// TODO Auto-generated method stub
					superiorPanel.currentChoice = choice;
				}
			});
		}
	}		
}