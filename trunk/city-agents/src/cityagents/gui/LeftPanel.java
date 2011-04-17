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
		this.superiorPanel = p;
		this.setPreferredSize( new Dimension( 150, 50 ) );
		this.setOpaque( false );
		ImagesHandler handler = ImagesHandler.getInstance();
		
		buttons = new JButton[ 2 ];
		this.setLayout( new GridLayout( buttons.length + 1, 1 ) );
		buttons[ 0 ] = new JButton( new ImageIcon( handler.getIMAGE_STREET() ) );
		buttons[ 1 ] = new JButton( new ImageIcon( handler.getIMAGE_HOUSE() ) );
		JButton start = new JButton( "START ");
		this.add( buttons[ 0 ], 0 );		
		this.add( buttons[ 1 ], 1 );
		this.add( start, 2 );
		start.addMouseListener( new MouseListener() 
		{
			@Override
			public void mouseReleased( MouseEvent arg0 ) 
			{
			}
			
			@Override
			public void mousePressed( MouseEvent arg0 ) 
			{
			}
			
			@Override
			public void mouseExited( MouseEvent arg0 ) 
			{
			}
			
			@Override
			public void mouseEntered( MouseEvent arg0 ) 
			{
			}
			
			@Override
			public void mouseClicked( MouseEvent arg0 ) 
			{
				// TODO: superiorPanel.getFrame().addBehaviour();
				superiorPanel.removeLeftPanel();
			}
		});
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
					
				}
				
				@Override
				public void mousePressed( MouseEvent arg0 ) 
				{
					
				}
				
				@Override
				public void mouseExited( MouseEvent arg0 ) 
				{
					
				}
				
				@Override
				public void mouseEntered( MouseEvent arg0 ) 
				{
					
				}
				
				@Override
				public void mouseClicked( MouseEvent arg0 ) 
				{
					superiorPanel.currentChoice = choice;
				}
			});
		}
	}
}