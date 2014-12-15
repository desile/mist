package com.mist.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Screen;
import com.mist.screens.GameScreen;



public class MistGame extends Game{
	
	//TODO: РАЗГРЕСТИ НАВОРОЧЕННОЕ ГОВНО В АДЕКВАТНУЮ КУЧУ В КЛАССЕ Player И ЕГО РОДИТЕЛЯХ
	
	public static final String GAME_TITLE = "Mist Game";
	public static final int WINDOW_WIDTH = 480;
	public static final int WINDOW_HEIGHT = 320;
	public static final int WINDOW_SCALE = 2;
	
	public static Content content = new Content();
	
	private Screen gameScreen;
	
	@Override
	public void create() {
		System.out.println("Game");
		initTextures();
		setScreen(gameScreen = new GameScreen(this));
	}
	
	public void initTextures(){
		content.loadTexture("test_male.png", "testMale");
		content.loadTexture("test_male2.png", "testMale2");
		content.loadTexture("guardian1.png","guardian1");
		content.loadTexture("guardian2.png","guardian2");
		content.loadTexture("guardian3.png","guardian3");
		content.loadTexture("Ludovic.png", "Ludovic");
		content.loadTexture("Zombie1.png", "Zombie1");
		content.loadTexture("architect.png", "architect");
		content.loadTexture("npc1.png", "npc_01");
		content.loadTexture("npc2.png", "npc_02");
		content.loadTexture("npc3.png", "npc_03");
		content.loadTexture("npc4.png", "npc_04");
		content.loadTexture("partyman1.png", "partyman1");
		content.loadTexture("partyman2.png", "partyman2");
		content.loadTexture("magician1.png", "magician1");
		content.loadTexture("magician2.png", "magician2");
		content.loadTexture("gargoyle.png", "gargoyle");
		content.loadTexture("crystal.png", "QuestCrystal");
		content.loadTexture("barrier.png", "barrier");
	}
	
	public static int getHeight(){
		return WINDOW_HEIGHT*WINDOW_SCALE;
	}
	
	public static int getWidth(){
		return WINDOW_WIDTH*WINDOW_SCALE;
	}
	
	public GameScreen getGameScreen(){
		return (GameScreen)gameScreen;
	}
	
}
