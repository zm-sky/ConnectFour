package me.pokemutt.connectFour.ui;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.MouseWheelEvent;
import java.util.ArrayList;
import java.util.List;

import me.pokemutt.connectFour.network.C4Client;
import me.zmsky.core.GameBox;

/**
 *
 * @author Raul Karim Sabag Ballesteros
 */
public abstract class GUI{

	/**
	 * Coordinates on screen where this button will be drawn.
	 */
	protected int x, y;
	
	/**
	 * The size of this button in pixels.
	 */
	protected int width, height;
	
	/**
	 * The stateID of this button. GUI items have both a state
	 * and a distinctiveID, and are rendered/updated/listening for input depending
	 * if a GuiManager's stateID is equal to the one below. 
	 */
	protected int stateID;
	
	/**
	 * The distinctiveID of this GUI.
	 */
	protected String distinctiveID = "NOID";
	
	/**
	 * The default UI font.
	 */
	public static final Font UIFont = GameBox.getDefaultFont().deriveFont(18f);
	
	/**
	 * Indicates the color of the UI.
	 */
	protected Color backgroundColor = Color.BLACK;
	
	/**
	 * Indicates the highlight color of the UI.
	 */
	protected Color highlightColor = new Color(210, 210, 210);
	
	/**
	 * Determines if the mouse is currently hovering over this UI element.
	 */
	protected boolean isHighlighted;
	
	/**
	 * Determines if a GUI element is enabled.
	 */
	protected boolean isEnabled = true;
	
	/**
	 * Determines if this GUI element should be drawn on screen.
	 */
	protected boolean noDraw;
	
	/**
	 * List that contains all the UIListeners that will
	 * be listening for input whenever this UI element fires
	 * an action.
	 */
	private List<UIListener> listeners = new ArrayList<>();
	
	/**
	 * Notifies all listeners that this UI element has been
	 * done a certain action.
	 */
	public final void fireEvent(){
		for(UIListener listener : listeners)
			listener.onUIAction(this, distinctiveID);
	}
	

    public void onMouseMove(int button, int x, int y, boolean Drag) { 
    	if(x >= this.x  && x <= this.x + width){
			if(y >= this.y  && y <= this.y + width){
				isHighlighted = true;
				
				return;
			}
		}
    	
		isHighlighted = false;
    }
	
	/**
	 * Sets the enabled flag of this UI element.
	 * If this element is not enabled, a UIManager will
	 * not send any input to this UI element.
	 * 
	 * If the noDraw flag is active, it will become disabled.
	 * 
	 * @param bool The boolean indicating to enable/disable this UI element.
	 */
    protected final void setEnabled(boolean bool){
    	isEnabled = bool;
    	noDraw = false;
    }
	
    /**
     * Disables this UI element and marks it to not be drawn.
     */
    public final void DisableAndNoDraw(){ 
    	setEnabled(false);
    	noDraw = true; 
    }
    
	/**
	 * Adds a UIListener that will listen to UI actions.
	 * 
	 * @param uiListener
	 */
	public final void addUIListener(UIListener uiListener){
		listeners.add(uiListener);
	}

    /**
     * Sets the background color of this UI element.
     * 
     * @param color The desired color to be set as the background.
     */
    public final void setBackgroundColor(Color color){
    	backgroundColor = color;
    }
	
    /**
     * Sets the highlight color of this UI element.
     * 
     * @param color The desired color to be set as the background.
     */
    public final void setHighlightColor(Color color){
    	highlightColor = color;
    }
    
    /**
     * Sets the x position of this UI element.
     * 
     * @param x The desired X position to be set to.
     */
    public void setX(int x){
    	this.x = x;
    }
    
    /**
     * Sets the y position of this UI element.
     * 
     * @param y The desired X position to be set to.
     */
    public void setY(int y){
    	this.y = y;
    }
    
    /**
     * Sets the width of this UI element.
     * 
     * @param width The desired width to set this UI element to.
     */
    public void setWidth(int width){
    	this.width = width;
    }
    
    /**
     * Sets the height of this UI element.
     * 
     * @param width The desired height to set this UI element to.
     */
    public void setHeight(int height){
    	this.height = height;
    }
    
    /**
     * Returns the x position of this UI element.
     * 
     * @return The x position of this UI element.
     */
    public int getX(){
    	return x;
    }
    
    /**
     * Returns the y position of this UI element.
     * 
     * @return The y position of this UI element.
     */
    public int getY(){
    	return y;
    }
    
    /**
     * Returns the width of this UI element.
     * 
     * @return The width of this UI element.
     */
    public int getWidth(){
    	return width;
    }
    
    /**
     * Returns the height of this UI element.
     * 
     * @return The height of this UI element.
     */
    public int getHeight(){
    	return height;
    }
    
    public final int getStateID(){ return stateID; }
    public final String getDistinctiveID(){ return distinctiveID; }
    public final boolean isEnabled(){ return isEnabled; }
    public final boolean shouldDraw(){ return !noDraw; }
    public void onMouseWheelMove(MouseWheelEvent e) {}
    public void onKeyPressed(KeyEvent e) { }
    public void onKeyReleased(KeyEvent e) { }
    public void onMouseClick(int button, int x, int y) {}
    public void onMouseRelease(int button, int x, int y) {}
    public void update(){}
    public void draw(Graphics2D g){}
}
