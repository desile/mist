package com.mist.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.mist.controllers.WorldController;
import com.mist.game.MistGame;
import com.mist.renderers.WorldRenderer;
import com.mist.world.World;

public class GameScreen implements Screen { //implements InputProcessor?

	//TODO: Составить схему/диаграмму для модели MVC
	//TODO: Вероятно, лучше всего организовать множество классов-участников MVC, разделяемых по типам объектов
	
	//TODO: Логика для передвижения игроков,объектов (только в восемь сторон, можно пока в четыре). Придумать, где реализовать эту логику.
	
	private MistGame game;
	private World world;
	private WorldRenderer renderer;
	private WorldController controller;
	
	public GameScreen(MistGame game){
		this.game = game;
		world = new World();
		renderer = new WorldRenderer(world);
		controller = new WorldController(world);
	}
	
	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(1, 1, 1, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		controller.update(delta);
		renderer.render();
		
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
