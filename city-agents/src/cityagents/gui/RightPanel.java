package cityagents.gui;

import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JPanel;

public class RightPanel extends JPanel
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
		
	private static final int size = 60;
	
	private PrincipalPanel superiorPanel;
	
	int worldSize = 10;
	int[][] world;
	
	public RightPanel( PrincipalPanel p ) 
	{
		// TODO Auto-generated constructor stub
		this.setOpaque( false );
		superiorPanel = p;
		world = new int[ worldSize * 2 ][ worldSize ];
		addListeners();
	}
	
	public int getWorldsize() 
	{
		return worldSize;
	}
	
	public void setWorldSize( int worldSize ) 
	{
		this.worldSize = worldSize;
	}

	@Override
	protected void paintComponent( Graphics graphics ) 
	{
		// TODO Auto-generated method stub
		super.paintComponent( graphics );
		for( int i = 0; i < worldSize * 2; i++ ) 
		{
			for( int j = 0; j < worldSize; j++ )
			{
				graphics.drawImage( superiorPanel.images[ 0 ] , i * size + 20, j * size + 20, null );
				graphics.drawImage( superiorPanel.images[ world[ i ][ j ] ] , i * size + 20, j * size + 20, null );
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
				
				if( i >= 0 && i < ( worldSize * 2 ) && j >= 0 && j < worldSize )
				{
					world[ i ][ j ] = superiorPanel.currentObject;
				}
				repaint();
			}
		});
	}
}
