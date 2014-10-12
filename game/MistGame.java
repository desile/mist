package com.mist.game;

import com.badlogic.gdx.Game;
import com.mist.screens.GameScreen;



public class MistGame extends Game{
	
	//TODO: РАЗГРЕСТИ НАВОРОЧЕННОЕ ГОВНО В АДЕКВАТНУЮ КУЧУ В КЛАССЕ Player И ЕГО РОДИТЕЛЯХ
	
	public static final String GAME_TITLE = "Mist Game";
	public static final int WINDOW_WIDTH = 480;
	public static final int WINDOW_HEIGHT = 320;
	public static final int WINDOW_SCALE = 2;
	
	public static Content content = new Content();

	
	@Override
	public void create() {
		System.out.println("Game");
		initTextures();
		setScreen(new GameScreen(this));
	}
	
	public void initTextures(){
		content.loadTexture("test_male.png", "testMale");
		content.loadTexture("test_male2.png", "testMale2");
	}
	
	public static int getHeight(){
		return WINDOW_HEIGHT*WINDOW_SCALE;
	}
	
	public static int getWidth(){
		return WINDOW_WIDTH*WINDOW_SCALE;
	}
	
}
