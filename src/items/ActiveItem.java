package items;

import player.Player;
import game.GameConstants;
import monster.Monster;
import map.Map;

public class ActiveItem implements GameConstants{

	private int x;
	private int y;
	private int itemID ;
	private boolean alive;
	private int velocity;
	private int direction;
	private Player player;
	
	public ActiveItem( Player player) {
		itemID = player.getActiveItemID();
		init(player);
		this.player = player;
	}
	
	private void init(Player player) {
		this.alive = true;
		this.direction = player.getImageDirection();
		int playerX = player.getX();
		int playerY = player.getY();
		switch(this.itemID) {
		case BULLET:
			this.velocity = 9;
			break;
		case LANDMINE:
			this.velocity = 0;
			break;
		}
		switch(this.direction) {
		case DIRECTION_UP:
			x = playerX;
			y = playerY - CELL_HEIGHT;
			break;
		case DIRECTION_DOWN:
			x = playerX;
			y = playerY + CELL_HEIGHT;
			break;
		case DIRECTION_LEFT:
			x = playerX - CELL_WIDTH;
			y = playerY;
			break;
		case DIRECTION_RIGHT:
			x = playerX + CELL_WIDTH;
			y = playerY ;
			break;
		}
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
	
	public boolean IsAlive() {
		return this.alive;
	}
	
	public int getVelocity() {
		return this.velocity;
	}
	
	public int getDirection() {
		return this.direction;
	}
	
	private boolean isCollided(int x, int y, int w, int h) {
		int x1 = Math.max(x, this.x);
		int x2 = Math.min(x + w, this.x + ITEM_WIDTH);
		int y1 = Math.max(y, this.y);
		int y2 = Math.min(y + h, this.y + ITEM_HEIGHT);
		return (x2 > x1) && (y2 > y1);
	}

	public void getItem(Player player) {
		switch(itemID) {
		case BULLET:
			break;
		case LANDMINE:
			break;
		}
	}
	
	public void move() {
	

		if(this.x<0||this.y<0||this.x>CELL_WIDTH*CELL_NUM_X||this.y>CELL_HEIGHT*CELL_NUM_Y) {
			this.player.setIsUsingBulletFlag(0);
		}
		else {
		
		switch(this.direction) {
		case DIRECTION_UP:
			this.setY(this.getY()-this.velocity);
			break;
		case DIRECTION_DOWN:
			this.setY(this.getY()+this.velocity);
			break;
		case DIRECTION_LEFT:
			this.setX(this.getX()-this.velocity);
			break;
		case DIRECTION_RIGHT:		
			this.setX(this.getX()+this.velocity);
			break;
		}
		
		}
	}
	

}
