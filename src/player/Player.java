package player;

import game.GameConstants;
import items.ActiveItem;
import map.Map;

/**
 * The player can be controlled by the keyboard. Its motion is related to the
 * maps now.
 *
 * @author Chengsong Xiong, Wang
 * @version 0.9
 */

public class Player implements GameConstants {

    private int playerID;// ID of the player
    private int velocity; // The velocity should be divisible by 60

    private int bombMaxNumber = 1;
    private int bombPlantedNumber = 0;

    private int bombPower;
    private boolean isImmune;
    private boolean isProtectedByItem;

    private int direction;
    private int imageDirection;// This variable is for deciding the image of DIRECTION_STOP
    private int x;
    private int y;

    private int playerHurtDelayCount = 0;
    private int protectedByItemCount = 0;
    private int bulletNum = 0;

    private Map playerMap; // Relate the player with the map
    private int mapX;
    private int mapY;
    private int playerHP;
    private int playerMaxHP;
    private int playerCharacterID;
    private int itemID;
    private ActiveItem playerItem;
    private int velocityLevel;

    private int stopflag = 0;

    private int upflag = 0;
    private int downflag = 0;
    private int leftflag = 0;
    private int rightflag = 0;

    private int futureUpflag = 0;
    private int futureDownflag = 0;
    private int futureLeftflag = 0;
    private int futureRightflag = 0;
    private int isUsingBulletFlag = 0;

    public Player(Map newmap, int id, int playerCharacterID) {
        this.direction = DIRECTION_STOP;
        this.imageDirection = DIRECTION_DOWN;
        
        switch(id) {
        case PLAYER_ID_P1:
            this.x = 0;
            this.y = 0;
            this.mapX = 0;
            this.mapY = 0;
            break;
        case PLAYER_ID_P2:
            this.x = 15*CELL_WIDTH;
            this.y = 15*CELL_HEIGHT;
            this.mapX = 15;
            this.mapY = 15;
            break;      	
        }
  
        this.velocity = 5;
        this.setPlayerMap(newmap);
        this.playerID = id;
        this.playerHurtDelayCount = 0;
        this.protectedByItemCount = 0;
        this.isImmune = false;
        this.isProtectedByItem = false;
        this.bombPower = 1;
        this.playerCharacterID = playerCharacterID;
        this.itemID = NO_ACTIVE_ITEM;
        this.bulletNum = 0;
        this.isUsingBulletFlag = 0;

        this.generateCharacter(playerCharacterID);
    }

    /*
     * For bombs
     */
    public void setBombPlantedNumber(int bombPlantedNum) {
        this.bombPlantedNumber = bombPlantedNum;
    }

    public void reduceBombPlantedNumber() {
        this.bombPlantedNumber--;
    }

    public void addBombPlantedNumber() {
        this.bombPlantedNumber++;
    }

    public int getBombPlantedNumber() {
        return this.bombPlantedNumber;
    }

    public void setBombMaxNumber(int bombMaxNum) {
        this.bombMaxNumber = bombMaxNum;
    }

    public int getBombMaxNumber() {
        return this.bombMaxNumber;
    }

    public int getBombPower() {
        return this.bombPower;
    }

    public void SetBombPower(int power) {
        this.bombPower = power;
    }
    /*
     * For item
     */

    public void setActiveItemID(int iid) {
        this.itemID = iid;

        switch (iid) {
            case BULLET:
                this.bulletNum = 1;
                break;
        }
    }

    public int getActiveItemID() {
        return this.itemID;
    }

    public void setActiveItem(ActiveItem item) {
        this.playerItem = item;
    }

    public ActiveItem getActiveItem() {
        return this.playerItem;
    }

    public void setIsUsingBulletFlag(int flag) {
        this.isUsingBulletFlag = flag;
    }

    public int getIsUsingBulletFlag() {
        return this.isUsingBulletFlag;
    }

    public int getBulletNum() {
        return this.bulletNum;
    }

    public void setBulletNum(int num) {
        this.bulletNum = num;
    }


    /*
     *
     * For ID
     */
    public void setPlayerID(int id) {
        this.playerID = id;
    }

    public int getPlayerID() {
        return this.playerID;
    }

    public void setPlayerCharacterID(int cid) {
        this.playerCharacterID = cid;
    }

    public int getPlayerCharacterID() {
        return this.playerCharacterID;
    }


    public void generateCharacter(int cid) {
        switch (cid) {
            case 0:
                this.playerHP = PLAYER_CHARACTER1_HP_MAX;
                this.playerMaxHP = PLAYER_CHARACTER1_HP_MAX;
                this.bombMaxNumber = PLAYER_CHARACTER1_BOMB_MAX;
                this.bombPower = PLAYER_CHARACTER1_BOMB_POWER;
                break;
            case 1:
                this.playerHP = PLAYER_CHARACTER2_HP_MAX;
                this.playerMaxHP = PLAYER_CHARACTER2_HP_MAX;
                this.bombMaxNumber = PLAYER_CHARACTER2_BOMB_MAX;
                this.bombPower = PLAYER_CHARACTER2_BOMB_POWER;
                break;
            case 2:
                this.playerHP = PLAYER_CHARACTER3_HP_MAX;
                this.playerMaxHP = PLAYER_CHARACTER3_HP_MAX;
                this.bombMaxNumber = PLAYER_CHARACTER3_BOMB_MAX;
                this.bombPower = PLAYER_CHARACTER3_BOMB_POWER;
                break;
            case 3:
                this.playerHP = PLAYER_CHARACTER4_HP_MAX;
                this.playerMaxHP = PLAYER_CHARACTER4_HP_MAX;
                this.bombMaxNumber = PLAYER_CHARACTER4_BOMB_MAX;
                this.bombPower = PLAYER_CHARACTER4_BOMB_POWER;
                break;
        }
    }

    /*
     * For HP
     */
    public void setHP(int hp) {
        if (hp < 0)
            this.playerHP = 0;
        else
            this.playerHP = hp;
    }

    public int getHP() {
        return this.playerHP;
    }

    public void setMaxHP(int mhp) {
        this.playerMaxHP = mhp;
    }

    public int getMaxHP() {
        return this.playerMaxHP;
    }

    public void getHurt(int hpLoss) {
        if ((!this.isImmune) && (!this.isProtectedByItem)) {
            this.setHP(this.getHP() - hpLoss);
        }
    }

    public boolean isImmune() {
        return this.isImmune;
    }

    public void setIsImmune(boolean isImmune) {
        this.isImmune = isImmune;
    }

    public boolean proectedByItem() {
        return this.isProtectedByItem;
    }

    public void setProtectedByItem(boolean protectedByItem) {
        this.isProtectedByItem = protectedByItem;
    }

    /*
     * For velocity
     */
    public void setVelocity(int v) {
        this.velocity = v;
    }

    public int getVelocity() {
        return this.velocity;
    }

    public void setVelocityLevel(int vl) {
        this.velocityLevel = vl;
    }
    
    public int getVelocityLevel() {
        return this.velocityLevel ;
    }


    public void addVelocityByItems() {
    	
        switch(velocityLevel) {
        case 0:
        	this.setVelocity(VELOCITY_1);
        	this.velocityLevel=1;
        	break;
        case 1:
        	this.setVelocity(VELOCITY_2);
        	this.velocityLevel=2;
        	break;
        }
        
    }

    /*
     * For direction
     */
    public void setDirection(int d) {

        if (d != DIRECTION_STOP) {
            this.direction = d;
            this.imageDirection = d;
        } else {
            this.direction = d;
        }
    }

    public int getDirection() {
        return this.direction;
    }

    public int getImageDirection() {
        return this.imageDirection;
    }

    /*
     * For location
     */
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

    public void setLocation(int X, int Y) {
        this.x = X;
        this.y = Y;
    }

    public void setMapX(int mapx) {
        this.mapX = mapx;
    }

    public void setMapY(int mapy) {
        this.mapY = mapy;
    }

    public int getMapX() {
        return this.mapX;
    }

    public int getMapY() {
        return this.mapY;
    }

    public int getUpflag() {
		return upflag;
	}

	public void setUpflag(int upflag) {
		this.upflag = upflag;
	}

	public int getFutureUpflag() {
		return futureUpflag;
	}

	public void setFutureUpflag(int futureUpflag) {
		this.futureUpflag = futureUpflag;
	}

	public int getDownflag() {
		return downflag;
	}

	public void setDownflag(int downflag) {
		this.downflag = downflag;
	}

	public int getFutureDownflag() {
		return futureDownflag;
	}

	public void setFutureDownflag(int futureDownflag) {
		this.futureDownflag = futureDownflag;
	}

	public int getLeftflag() {
		return leftflag;
	}

	public void setLeftflag(int leftflag) {
		this.leftflag = leftflag;
	}

	public int getFutureLeftflag() {
		return futureLeftflag;
	}

	public void setFutureLeftflag(int futureLeftflag) {
		this.futureLeftflag = futureLeftflag;
	}

	public int getRightflag() {
		return rightflag;
	}

	public void setRightflag(int rightflag) {
		this.rightflag = rightflag;
	}

	public int getFutureRightflag() {
		return futureRightflag;
	}

	public void setFutureRightflag(int futureRightflag) {
		this.futureRightflag = futureRightflag;
	}

	public int getStopflag() {
		return stopflag;
	}

	public void setStopflag(int stopflag) {
		this.stopflag = stopflag;
	}

	/**
     * Use map to decide the player's location after moving. Can not receive any
     * command before it moves completely into a new integral cell.
     *
     * @throws InterruptedException
     */
    public void setLocationOnMap(Player player, Map mi, int mapX, int mapY) {

        if (mi.isAvailable(mapX, mapY)) {
            player.setMapX(mapX);
            player.setMapY(mapY);
        }
    }

    public Map getPlayerMap() {
		return playerMap;
	}

	public void setPlayerMap(Map playerMap) {
		this.playerMap = playerMap;
	}

	/**
     * Use playerInMap() to ensure that the player is in the frame and avoid errors.
     */
    public boolean playerInMap() {

        boolean canMove = true;
        switch (this.getDirection()) {
            case DIRECTION_UP:
                if (this.mapY > 0)
                    canMove = true;
                else
                    canMove = false;
                break;
            case DIRECTION_DOWN:
                if (this.mapY < this.getPlayerMap().getSizeY() - 1)
                    canMove = true;
                else
                    canMove = false;
                break;
            case DIRECTION_LEFT:
                if (this.mapX > 0)
                    canMove = true;
                else
                    canMove = false;
                break;
            case DIRECTION_RIGHT:
                if (this.mapX < this.getPlayerMap().getSizeX() - 1)
                    canMove = true;
                else
                    canMove = false;
                break;
        }
        return canMove;
    }

    /**
     * Stop the player and clear the flag
     */
    public void playerStop() {
        // Stop only when the x and y of the player are multiples of 45
        if (this.getX() % CELL_WIDTH == 0 && this.getY() % CELL_HEIGHT == 0) {
            if (this.getFutureUpflag() == 1) {
                this.setDirection(DIRECTION_UP);
            }
            if (this.getFutureDownflag() == 1) {
                this.setDirection(DIRECTION_DOWN);
            }
            if (this.getFutureRightflag() == 1) {
                this.setDirection(DIRECTION_RIGHT);
            }
            if (this.getFutureLeftflag() == 1) {
                this.setDirection(DIRECTION_LEFT);
            }
            if (this.getFutureDownflag() == 0 & this.getFutureLeftflag() == 0 & this.getFutureRightflag() == 0
                    & this.getFutureUpflag() == 0) {
                if (this.getStopflag() == 1) {
                    this.setDirection(DIRECTION_STOP);
                    this.setStopflag(0);
                }
            }
            // update mapX and mapY
            this.setLocationOnMap(this, this.getPlayerMap(), this.x / CELL_WIDTH, this.y / CELL_HEIGHT);
            clearFutureFlag();
        }
    }

    /**
     * Judge the direction of the player and change the coordinates.
     */
    public void playerMove() {

        double mapx = (double) (this.x) / (double) (CELL_WIDTH);
        double mapy = (double) (this.y) / (double) (CELL_HEIGHT);

        if (playerInMap()) // Move the player only when it's in the range of the map
        {
            switch (this.getDirection()) {
                case DIRECTION_UP:
                    // If the cell which the player is going to step on is available
                    if (this.getPlayerMap().isAvailable((int) (Math.ceil(mapx)), (int) (Math.ceil(mapy) - 1))) {
                        this.setY(this.getY() - this.getVelocity());
                        this.playerStop();
                    }
                    break;
                case DIRECTION_DOWN:
                    if (this.getPlayerMap().isAvailable((int) (Math.floor(mapx)), (int) (Math.floor(mapy) + 1))) {
                        this.setY(this.getY() + this.getVelocity());
                        this.playerStop();
                    }
                    break;
                case DIRECTION_LEFT:
                    if (this.getPlayerMap().isAvailable((int) (Math.ceil(mapx) - 1), (int) (Math.ceil(mapy)))) {
                        this.setX(this.getX() - this.getVelocity());
                        this.playerStop();
                    }
                    break;
                case DIRECTION_RIGHT:
                    if (this.getPlayerMap().isAvailable((int) (Math.floor(mapx) + 1), (int) (Math.floor(mapy)))) {
                        this.setX(this.getX() + this.getVelocity());
                        this.playerStop();
                    }
                    break;
                case DIRECTION_STOP:
                    break;
            }
        }
    }

    public void clearFlag() {
        this.setUpflag(0);
        this.setDownflag(0);
        this.setLeftflag(0);
        this.setRightflag(0);
    }

    public void clearFutureFlag() {
        this.setFutureUpflag(0);
        this.setFutureDownflag(0);
        this.setFutureLeftflag(0);
        this.setFutureRightflag(0);
    }

    

    /**
     * Update map BOMB_INFO
     */
    public void plantBomb(Map mi, int mapx, int mapy) {
        if (this.bombPlantedNumber < this.bombMaxNumber)
            mi.setBomb(mapx, mapy, this.bombPower, this);
    }

    public void acquireItemByMap(Map mi) {
        if (this.x % CELL_WIDTH == 0 && this.y % CELL_HEIGHT == 0 && mi.isWithItem(this.getMapX(), this.getMapY())) {
            mi.giveItem(this.getMapX(), this.getMapY(), this);
        }
    }

    /**
     * Use map and monsters to decide whether the player is alive, whether the
     * player acquire any item, etc.
     */
    public void decideState(Player player, Map mi) {

    }

    /**
     * Create an active item and use it
     */
    public void useActiveItem() {
        if (this.getActiveItemID() == BULLET && this.isUsingBulletFlag == 0 && this.bulletNum > 0) {
            ActiveItem newActiveItem = new ActiveItem(this);
            this.setActiveItem(newActiveItem);
            this.isUsingBulletFlag = 1;
            this.bulletNum--;

            // set ItemID to -1 when one do not have an active item
            this.setActiveItemID(NO_ACTIVE_ITEM);// Contact Status Panel if want to delete
        }

    }

    /**
     * Refresh the state of player. Player will be hurt when collided with bombs.
     * The playerHurtDelayCount and playerCanBeHurt is used to avoid multiple hurts
     * on player at the same time.
     */
    public void refresh(Map mi) {
        if (getUpflag() == 1) {
            this.setDirection(DIRECTION_UP);
        }
        if (getDownflag() == 1) {
            this.setDirection(DIRECTION_DOWN);
        }
        if (getLeftflag() == 1) {
            this.setDirection(DIRECTION_LEFT);
        }
        if (getRightflag() == 1) {
            this.setDirection(DIRECTION_RIGHT);
        }

        this.playerMove();// Change the location of the player

        if (mi.isAtExplosion(this.getMapX(), this.getMapY())) {
            this.getHurt(HP_LOSS_BY_BOMB);
            this.setIsImmune(true);
        }

        if (this.isImmune) {
            this.playerHurtDelayCount++;
            if (this.playerHurtDelayCount == 30) {
                this.playerHurtDelayCount = 0;
                this.setIsImmune(false);
            }
        }

        if (this.isProtectedByItem) {
            this.protectedByItemCount++;
            if (this.protectedByItemCount == 330) {
                this.protectedByItemCount = 0;
                this.setProtectedByItem(false);
            }
        }
    }
    
    
}