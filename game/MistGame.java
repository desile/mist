package com.mist.game;

import com.badlogic.gdx.Game;
import com.mist.screens.GameScreen;



public class MistGame extends Game{
	
	//TODO: пюгцпеярх мюбнпнвеммне цнбмн б юдейбюрмсч йсвс б йкюяяе Player х ецн пндхрекъу
	
	public static final String GAME_TITLE = "Mist Game";
	public static final int WINDOW_WIDTH = 480;
	public static final int WINDOW_HEIGHT = 320;
	public static final int WINDOW_SCALE = 2;

	
	@Override
	public void create() {
		System.out.println("Game");
		setScreen(new GameScreen(this));
	}
	
	public static int getHeight(){
		return WINDOW_HEIGHT*WINDOW_SCALE;
	}
	
	public static int getWidth(){
		return WINDOW_WIDTH*WINDOW_SCALE;
	}
	
}
