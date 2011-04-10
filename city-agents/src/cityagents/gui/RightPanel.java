package cityagents.gui;

import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JPanel;

import cityagents.core.WorldMap;
import cityagents.core.WorldObjects;
import cityagents.util.Constants;

public class RightPanel extends JPanel
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
		
	private static final int size = 40;
	
	private PrincipalPanel superiorPanel;
	
	WorldMap world;
	
	private ImagesHandler handler;
	
	public RightPanel( PrincipalPanel p ) 
	{
		// TODO Auto-generated constructor stub
		this.setOpaque( false );
		handler = ImagesHandler.getInstance();
		superiorPanel = p;
		world = WorldMap.getInstance();
		addListeners();
	}

	@Override
	protected void paintComponent( Graphics graphics ) 
	{
		// TODO Auto-generated method stub
		super.paintComponent( graphics );
		for( int i = 0; i < world.getWorldSize() * 2; i++ ) 
		{
			for( int j = 0; j < world.getWorldSize(); j++ )
			{
				WorldObjects element = world.getElement( i , j );
				
				graphics.drawImage( handler.getIMAGE_STREET() , i * size + 20, j * size + 20, null );
				
				if( element.getType().intValue() == Constants.CAR )
				{									
					graphics.drawImage( handler.getIMAGE_CAR() , i * size + 20, j * size + 20, null );
				}
				else if( element.getType().intValue() == Constants.HOUSE ) 
				{
					graphics.drawImage( handler.getIMAGE_HOUSE() , i * size + 20, j * size + 20, null );
				}
				graphics.drawRect( i * size + 20, j * size + 20, size, size );
			}
		}		
	}
	
	private void addListeners()
	{
		this.addMouseListener( new MouseListener() 
		{
			
			@Override
			public void mouseReleased( MouseEvent e ) 
			{
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mousePressed( MouseEvent e ) 
			{
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseExited( MouseEvent e ) 
			{
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseEntered( MouseEvent e ) 
			{
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseClicked( MouseEvent e ) 
			{				
				// TODO Auto-generated method stub
				int x = e.getX();
				int y = e.getY();
				
				int i = ( x - 20 ) / size;
				int j = ( y - 20 ) / size;
				
				if( i >= 0 && i < ( world.getWorldSize() * 2 ) && j >= 0 && j < world.getWorldSize() )
				{
					switch( superiorPanel.currentChoice ) 
					{
					case Constants.STREET:
						world.setStreet( i , j );
						break;					
					
					case Constants.HOUSE:
						world.setHouse( i , j );
						break;

					default:
						break;
					}					
				}
				repaint();
			}
		});
	}
}
