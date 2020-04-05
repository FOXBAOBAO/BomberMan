package main.java.logic;


import java.io.Serializable;

public class Message implements Serializable{
	
	public int type;	
	public int number;
	public String msg =null ;
	public String UserName = null;
	public String PassWord = null;
	public int team;
	public int ready;
	public int map;
	public int start;
	public int exit;
	public int head;
	public int x;
	public int y;
	public int moveTypeX;
	public int moveTypeY;
	public int BombPower;
	public int isNext;
	
	public int isLive;
	public int isDead;


	public Message(){
		super();
	}
	protected Object clone() throws CloneNotSupportedException {
		return super.clone();
	}



	public int getBombPower() {
		return BombPower;
	}

	public void setBombPower(int bombPower) {
		BombPower = bombPower;
	}

	public int getExit() {
		return exit;
	}

	public void setExit(int exit) {
		this.exit = exit;
	}

	public int getHead() {
		return head;
	}

	public void setHead(int head) {
		this.head = head;
	}

	public int getIsDead() {
		return isDead;
	}

	public void setIsDead(int isDead) {
		this.isDead = isDead;
	}

	public int getIsLive() {
		return isLive;
	}

	public void setIsLive(int isLive) {
		this.isLive = isLive;
	}

	public int getIsNext() {
		return isNext;
	}

	public void setIsNext(int isNext) {
		this.isNext = isNext;
	}

	public int getMap() {
		return map;
	}

	public void setMap(int map) {
		this.map = map;
	}

	public int getMoveTypeX() {
		return moveTypeX;
	}

	public void setMoveTypeX(int moveTypeX) {
		this.moveTypeX = moveTypeX;
	}

	public int getMoveTypeY() {
		return moveTypeY;
	}

	public void setMoveTypeY(int moveTypeY) {
		this.moveTypeY = moveTypeY;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public String getPassWord() {
		return PassWord;
	}

	public void setPassWord(String passWord) {
		PassWord = passWord;
	}

	public int getReady() {
		return ready;
	}

	public void setReady(int ready) {
		this.ready = ready;
	}

	public int getStart() {
		return start;
	}

	public void setStart(int start) {
		this.start = start;
	}

	public int getTeam() {
		return team;
	}

	public void setTeam(int team) {
		this.team = team;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public String getUserName() {
		return UserName;
	}

	public void setUserName(String userName) {
		UserName = userName;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

}
