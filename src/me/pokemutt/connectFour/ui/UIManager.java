package me.pokemutt.connectFour.ui;

import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.KeyEvent;
import java.awt.event.MouseWheelEvent;
import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @author Raul Karim Sabag Ballesteros
 *
 */
public class UIManager{

	/**
	 * List that contains all the UI elements.
	 */
	private List<GUI> items;
	
	/**
	 * Indicates this manager's current state. States are used
	 * to determine which of the elements in the list should
	 * be rendered/updated/listening for input. An example of how
	 * this is meant to be used is like a main menu screen from
	 * a game, after clicking a button more options appear and
	 * the old ui elements arent rendered/updated/etc. If an UI 
	 * element's stateID is equal to the current stateID here,
	 * it will be rendered and updated.
	 */
	private int stateID = 0;
	
	/**
	 * Used to determine if this UIManager should ignore any
	 * sort of input from the user. 
	 */
	private boolean ignoreInput;
	
	/**
	 * Default empty constructor. StateID is equal to 0.
	 */
	public UIManager(){
		items = new ArrayList<>();
	}
	
	/**
	 * Creates an UIManager with a predefined state.
	 * 
	 * @param stateID The default stateID the manager will
	 * start with.
	 */
	public UIManager(int stateID){
		this.stateID = stateID;
		
		items = new ArrayList<>();
	}
	
	/** 
	 * Listens to mouse movements.
	 * 
	 * @param button The mouse button pressed.
	 * @param x The x coordinate of the pointer.
	 * @param y The y coordinate of the pointer.
	 * @param Drag Indicates if the mouse button is pressed down.
	 */
	public void onMouseMove(int button, int x, int y, boolean Drag) {
		if(!ignoreInput){
			for(GUI item : items)
				if(item.getStateID() == stateID && item.isEnabled())
					item.onMouseMove(button, x, y, Drag);
		}
	}

	/** 
	 * Listens to mouse wheel events.
	 * 
	 * @param e The MouseWheelEvent.
	 */
	public void onMouseWheelMove(MouseWheelEvent e) {
		if(!ignoreInput){
			for(GUI item : items)
				if(item.getStateID() == stateID && item.isEnabled())
					item.onMouseWheelMove(e);
		}
	}

	/** 
	 * Listens to mouse clicks.
	 * 
	 * @param button The mouse button pressed.
	 * @param x The x coordinate of the pointer.
	 * @param y The y coordinate of the pointer.
	 */
	public void onMouseClick(int button, int x, int y) {
		if(!ignoreInput){
			for(GUI item : items)
				if(item.getStateID() == stateID && item.isEnabled())
					item.onMouseClick(button, x, y);
		}
	}
	/** 
	 * Listens to mouse click releases.
	 * 
	 * @param button The mouse button pressed.
	 * @param x The x coordinate of the pointer.
	 * @param y The y coordinate of the pointer.
	 */
	public void onMouseRelease(int button, int x, int y) {
		if(!ignoreInput){
			for(GUI item : items)
				if(item.getStateID() == stateID && item.isEnabled())
					item.onMouseRelease(button, x, y);
		}
	}	

	/** 
	 * Listens to keyboard events.
	 * 
	 * @param e The KeyEvent.
	 */
	public void onKeyPressed(KeyEvent e) {
		if(!ignoreInput){
			for(GUI item : items)
				if(item.getStateID() == stateID && item.isEnabled())
					item.onKeyPressed(e);
		}
	}
	
	/** 
	 * Listens to keyboard events.
	 * 
	 * @param e The KeyEvent.
	 */
	public void onKeyReleased(KeyEvent e) {
		if(!ignoreInput){
			for(GUI item : items)
				if(item.getStateID() == stateID && item.isEnabled())
					item.onKeyReleased(e);
		}
	}
	
	/**
	 * Renders UI elements with the same stateID as this
	 * UIManager.
	 */
	public void renderUI(Graphics2D g){
		g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		g.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
		g.setFont(GUI.UIFont);
		
		for(GUI item : items)
			if(item.getStateID() == stateID && item.shouldDraw())
				item.draw(g);
		
		g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_OFF);
		g.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_OFF);
	}
	
	/**
	 * Returns the UI item with the same distinctiveID as the one
	 * in the parameter.
	 * 
	 * @param distinctiveID The distinctiveID to look for.
	 * @return The UI element with the same distinctiveID given.
	 */
	public GUI getUIElement(String distinctiveID){
		for(GUI item : items)
			if(item.getDistinctiveID().equals(distinctiveID))
				return item;
		
		return null;
	}
	
	/**
	 * Updates all of the UI elements, ignoring if the current
	 * stateID of this manager is equal to the UI element currently
	 * being updated.
	 */
	public void update(){
		for(GUI item : items)
			item.update();
	}
	
	/**
	 * Sets the manager's stateID.
	 * 
	 * @param stateID the desired stateID.
	 */
	public void setState(int stateID){
		this.stateID = stateID;
	}
	
	/**
	 * Adds a gui element to the list of UI items.
	 * 
	 * @param gui The desired GUI element to add.
	 */
	public void addUIElement(GUI gui){
		items.add(gui);
	}
	
	/**
	 * Adds an UIListener that will listen to UI events.
	 * All the current UI elements added to the manager will be
	 * given the listener passed as a parameter. This method should
	 * be called last, after adding all the UI elements desired.
	 * 
	 * @param uiListener The desired UIListener to be added.
	 */
	public void addUIListener(UIListener uiListener){
		for(GUI item : items)
			item.addUIListener(uiListener);
	}
	
	/**
	 * Removes a gui element to the list of UI items.
	 * 
	 * @param gui The desired GUI element to remove.
	 */
	public void removeUIElement(GUI gui){
		items.remove(gui);
	}
	
	/**
	 * Returns the UIManager's current stateID.
	 * 
	 * @return The stateID currently set.
	 */
	public int getCurrentState(){ return stateID; }
	
	/**
	 * Returns the list of all the UI elements added
	 * to this UIManager.
	 *  
	 * @return The list of UI items.
	 */
	public List<GUI> getUIElements(){ return items; }
}
