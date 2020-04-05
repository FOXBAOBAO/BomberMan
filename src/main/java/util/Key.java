package main.java.util;

public class Key {
	public static int PORT = 9000;
	public static String SERVER_IP="localhost";
	public static String GAME_NAME = "Bomber Man";
	
	public static int MESSAGE_TYPE_LOGIN = 1;
	public static int MESSAGE_TYPE_CHANGE_TEAM = 2;
	public static int MESSAGE_TYPE_CHART = 2;
	public static int MESSAGE_TYPE_READY = 10;
	public static int MESSAGE_TYPE_EXIT = 11;
	public static int MESSAGE_TYPE_DEAD = 104;
	public static int MESSAGE_TYPE_MOVE = 101;
	public static int MESSAGE_TYPE_LAYOUT_BOMB = 108;
	public static int MESSAGE_TYPE_PLAYER_MOVE = 101;
	
	
	public static int TEAM_BLUE = 1 ;
	public static int TEAM_RED = -1 ;
	
	
	public static int PLAYER_HEADER_UP = 1;
	public static int PLAYER_HEADER_RIGHT = 2;
	public static int PLAYER_HEADER_DOWN = 3;
	public static int PLAYER_HEADER_LEFT = 4;
}
