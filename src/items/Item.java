package items;

import player.Player;
import game.GameConstants;

/**
 * The super class for all the items.
 * 
 * @author Haozhe Zhan, Junyue Zheng
 * @version 0.2
 */
public class Item implements GameConstants {

	private int x = 45;
	private int y = 45;
	private int itemID = VELOCITY_UP; // saved in GameConstants to judge which item is created and used
	private boolean isAcquired = false;

	public Item(int xPos, int yPos) {

	}

	/**
	 * select item to use
	 * 
	 * @param player
	 * 
	 */
	public void getItem(Player player) {
		if (!this.isAcquired) {
			switch (this.getItemID()) {
			case VELOCITY_UP:
				// Correctly set the location of the player to avoid bugs
				player.addVelocityByItems();
				break;
			/*
			 * case BOMB_UP: this.setY(this.getY() + this.getVelocity()); break; case
			 * POWER_UP: this.setX(this.getX() - this.getVelocity()); break;
			 */
			}
		}
	}

	public void setItemID(int i) {
		this.itemID = i;
	}

	public int getItemID() {
		return this.itemID;
	}

	public void setX(int X) {
		this.x = X;
	}

	public void setY(int Y) {
		this.y = Y;
	}

	public int getX() {
		return this.x;
	}

	public int getY() {
		return this.y;
	}

	public void setIsAcquired(boolean f) {
		this.isAcquired = f;
	}

	public boolean getIsAcquired() {
		return this.isAcquired;
	}

}
