
package me.pokemutt.connectFour.ui;

import java.awt.AlphaComposite;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Stroke;
import java.awt.image.BufferedImage;

import me.pokemutt.connectFour.ConnectFour;
import me.zmsky.core.GameBox;

/**
 * 
 * @author Raul Karim Sabag Ballesteros
 */
public class Button extends GUI{

	/**
	 * Text inside of the button that will be displayed in its center.
	 */
	private String text;
	
	/**
	 * The padding between the inner text and the
	 * button's boundries.
	 */
	private int padding = 15;
	
	/**
	 * Indicates if the cursor is currently ontop of the button.
	 */
	private boolean isHoveredOn;
	
	/**
	 * Indicates if the text should be shifted to the left when drawing.
	 */
	private boolean shiftTextLeft;
	
	/**
	 * Indicates this button's opacity when being drawn.
	 */
	private float opacity = 1.0f;
	
	/**
	 * Creates a basic button with given position and size. If given size is smaller than
	 * the text contained inside, the button will be resized to fit according to.
	 * 
	 * @param stateID The stateID on which this button belongs to.
	 * @param distinctiveID This button's distinctiveID.
	 * @param text The text that will be rendered in the button's center.
	 * @param x The x coordinate of this button.
	 * @param y The y coordinate of this button.
	 * @param width The width of this button in pixels.
	 * @param height The height of this button in pixels.
	 */
	public Button(int stateID, String distinctiveID, String text, int x, int y, int width, int height){
		this.text = text;
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.stateID = stateID;
		this.distinctiveID = distinctiveID;
		
		adjustSize();
	}
	
	/**
	 * Adjusts the button's size incase the text inside is bigger than
	 * the actual button's given width, considering the padding.
	 */
	public void adjustSize(){
		FontMetrics m = (new BufferedImage(1,1, BufferedImage.TYPE_INT_RGB).getGraphics().getFontMetrics(UIFont));
		
		int stringWidth = m.stringWidth(text);
		
		if(stringWidth > width - padding){
			width+= (stringWidth - (width + padding)) + padding*2;
		}
		
		if(m.getHeight() + m.getDescent() > height)
			height = m.getHeight() + m.getDescent();
		
		x-=width/2;
		y-=height/2;
	}

    /**
     * Draws the button.
     * 
     * @param g The current graphical context.
     */
    public void draw(Graphics2D g) { 
    	if(isEnabled){
    		Stroke s = g.getStroke();
			g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

	    	g.setComposite(GameBox.getDefaultComposite().derive(opacity));
	    	
	    	g.setColor(backgroundColor);
	    	g.fillRoundRect(x, y, width, height, 10, 10);
			g.setStroke(new BasicStroke(3,BasicStroke.CAP_BUTT,0));
			g.setColor(isHoveredOn ? Color.ORANGE : Color.WHITE); g.drawRoundRect(x-1, y-1, width+1, height+1, 10, 10);
			g.setStroke(s);
	    	
	    	if(isHighlighted){
	    		g.setColor(highlightColor);
	    		g.fillRect(x, y, width, height);
	    	}
	    	
	    	//Drawing text inside the button.
	    	g.setComposite(GameBox.getDefaultComposite());
	    	g.setColor(Color.WHITE);
	    	g.setFont(UIFont);
	    	
	    	int fontHeight = (int) UIFont.createGlyphVector(g.getFontRenderContext(), text).getVisualBounds().getHeight();
	    	
	    	int textX = (x + width/2) - (g.getFontMetrics().stringWidth(text)/2);
	    	int textY = (y + height/2) + (fontHeight/2);
	    	
	    	if(shiftTextLeft)
	    		textX = x + padding/2;
	    	
	    	g.drawString(text, textX , textY);
	    	g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_OFF);
    	}
    }
    
    public void onMouseMove(int button, int x, int y, boolean Drag) { 
    	if(x >= this.x && x <= this.x + width){
    		if(y >= this.y && y <= this.y + height){
        		isHoveredOn = true;
        		
        		return;
        	}
    	}
    	
    	isHoveredOn = false;
    }
    
    public void onMouseClick(int button, int x, int y) {  
    	if(x >= this.x && x <= this.x + width){
    		if(y >= this.y && y <= this.y + height){
        		fireEvent();
        	}
    	}
    }
	/**
	 * Updates the button.
	 */
    public void update() { 
    	if(isHoveredOn)
    		opacity = opacity < 0.9f ? opacity + 0.05f : 1.0f;
    	else
    		opacity = opacity > 0.6f ? opacity - 0.05f : 0.6f;
    }
    
    public void setShiftLeftMode(boolean value){ shiftTextLeft = value; }
}
