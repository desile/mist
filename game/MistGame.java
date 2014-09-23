package com.mist.game;

import com.badlogic.gdx.Game;
import com.mist.screens.GameScreen;



public class MistGame extends Game{
	
	

	@Override
	public void create() {
		System.out.println("Nikita's branch");
		setScreen(new GameScreen(this));
	}
	
}
