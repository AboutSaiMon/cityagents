package cityagents.gui;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

import cityagents.core.Grass;
import cityagents.core.House;
import cityagents.core.Street;
import cityagents.core.WorldMap;
import cityagents.core.WorldObject;
import cityagents.core.agents.CarAgent;

public class RightPanel extends JPanel
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
		
	private static final int size = 30;
	
	private PrincipalPanel superiorPanel;
	
	WorldMap world;
	
	private ImagesHandler images;
	private boolean startDraw = false;
	private boolean addAnAgent = false;	
	private Point agentStart;
	private List< Point > cellsToDraw;
	
	public RightPanel( PrincipalPanel p ) 
	{
		this.setOpaque( false );
		images = ImagesHandler.getInstance();
		superiorPanel = p;
		world = WorldMap.getInstance();
		this.setPreferredSize( new Dimension( 52 * 2 * size, 52 * size ) );
		cellsToDraw = new ArrayList< Point >();
		addListeners();
	}

	@Override
	protected void paintComponent( Graphics graphics ) 
	{
		super.paintComponent( graphics );
		for( int i = 0; i < world.getWorldSize() * 2; i++ ) 
		{
			for( int j = 0; j < world.getWorldSize(); j++ )
			{
				WorldObject element = world.getElement( i , j );
								
				if( element instanceof CarAgent )
				{
					graphics.drawImage( images.getStreet() , i * size + 20, j * size + 20, null );
					graphics.drawImage( images.getCar() , i * size + 20, j * size + 20, null );
				}
				else if( element instanceof Grass ) 
				{
					graphics.drawImage( images.getGrass() , i * size + 20, j * size + 20, null );
				}
				else if( element instanceof House )
				{
					graphics.drawImage( images.getGrass() , i * size + 20, j * size + 20, null );
					graphics.drawImage( images.getHouse() , i * size + 20, j * size + 20, null );
				}
				else if( element instanceof Street )
				{
					graphics.drawImage( images.getStreet() , i * size + 20, j * size + 20, null );
				}
			}
		}		
	}
	
	private void addListeners()
	{
		this.addMouseMotionListener( new MouseMotionListener() 
		{
		
			@Override
			public void mouseMoved( MouseEvent e ) 
			{
			}
			
			@Override
			public void mouseDragged( MouseEvent e ) 
			{
				if( startDraw )
				{
					int x = e.getX();
					int y = e.getY();
					
					int i = ( x - 20 ) / size;
					int j = ( y - 20 ) / size;
					
					if( i >= 0 && i < ( world.getWorldSize() * 2 ) && j >= 0 && j < world.getWorldSize() )
					{
						Point p = new Point( i, j );
						cellsToDraw.add( p );
					}
				}
			}
		});
		
		this.addMouseListener( new MouseListener() 
		{
			
			@Override
			public void mouseReleased( MouseEvent e ) 
			{
				for( Point p : cellsToDraw )
				{
					int i = p.x;
					int j = p.y;
					switch( superiorPanel.currentChoice ) 
					{
					case WorldObject.STREET:
						world.setStreet( i , j );
						break;					
					
					case WorldObject.GRASS:
						world.setGrass( i , j );
						break;
						
					case WorldObject.HOUSE:
						world.setHouse(i, j);
						break;

					default:
						break;
					}								
				}
				repaint();
				cellsToDraw.clear();
				startDraw = false;
			}
			
			@Override
			public void mousePressed( MouseEvent e ) 
			{
				startDraw = true;				
			}
			
			@Override
			public void mouseExited( MouseEvent e ) 
			{
				
			}
			
			@Override
			public void mouseEntered( MouseEvent e ) 
			{									
			}
			
			@Override
			public void mouseClicked( MouseEvent e ) 
			{				
				if( e.getButton() == MouseEvent.BUTTON3 )
				{ 	//with right click can cancel the insert.
					addAnAgent = false;
					agentStart = null;
				}
				else
				{
					startDraw = true;
					int x = e.getX();
					int y = e.getY();
					
					int i = ( x - 20 ) / size;
					int j = ( y - 20 ) / size;
					
					if( i >= 0 && i < ( world.getWorldSize() * 2 ) && j >= 0 && j < world.getWorldSize() )
					{
						switch( superiorPanel.currentChoice ) 
						{
						case WorldObject.STREET:
							world.setStreet( i , j );
							break;					
						
						case WorldObject.GRASS:
							world.setGrass( i , j );
							break;
							
						case WorldObject.HOUSE:
							world.setHouse( i, j);
	
						case WorldObject.CAR:
							if( addAnAgent )
							{
								if( world.getElement( i, j ) instanceof Street )
								{
									CarAgent c = new CarAgent();
									Point[] arguments = new Point[ 2 ];
									arguments[ 0 ] = new Point( agentStart );
									arguments[ 1 ] = new Point( i, j );
									c.setArguments( arguments );
									world.setCar( agentStart.x, agentStart.y, c );
									addAnAgent = false;
									agentStart = null;
									PrincipalFrame frame = PrincipalFrame.getInstance();
									JOptionPane.showMessageDialog( frame, "Added Agent", "Added Agent", JOptionPane.INFORMATION_MESSAGE );
									if( !world.isEditable() )
										world.startAgent( i, j );
								}
							}
							else
							{		
								if( world.getElement(i,j) instanceof Street )
								{
									addAnAgent = true;
									agentStart = new Point( i, j );
									PrincipalFrame frame = PrincipalFrame.getInstance();
									JOptionPane.showMessageDialog( frame, "Set Destination.", "Destination", JOptionPane.INFORMATION_MESSAGE );	
								}
							}
							break;	
							
						default:
							break;
						}					
					}
				}
				repaint();
			}
		});
	}	
}
