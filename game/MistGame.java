package com.mist.game;

import com.badlogic.gdx.Game;
import com.mist.screens.GameScreen;



public class MistGame extends Game{
	
	

	@Override
	public void create() {
		System.out.println("Game");
		setScreen(new GameScreen(this));
	}
	
}
