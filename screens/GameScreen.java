package com.mist.screens;

import com.badlogic.gdx.Screen;
import com.mist.controllers.WorldController;
import com.mist.game.MistGame;
import com.mist.renderers.WorldRenderer;
import com.mist.world.World;

public class GameScreen implements Screen { //implements InputProcessor?

	private MistGame game;
	private World world;
	private WorldRenderer renderer;
	private WorldController controller;
	
	public GameScreen(MistGame game){
		this.game = game;
	}
	
	@Override
	public void render(float delta) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void show() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		
	}

}
