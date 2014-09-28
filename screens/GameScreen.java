package com.mist.screens;

import sun.net.www.content.audio.x_aiff;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.math.Vector2;
import com.mist.controllers.WorldController;
import com.mist.game.MistGame;
import com.mist.renderers.WorldRenderer;
import com.mist.world.World;

public class GameScreen implements Screen { //implements InputProcessor?

	//TODO: Составить схему/диаграмму для модели MVC
	//TODO: Вероятно, лучше всего организовать множество классов-участников MVC, разделяемых по типам объектов
	
	//TODO: Логика для передвижения игроков,объектов (только в восемь сторон, можно пока в четыре). Придумать, где реализовать эту логику.
	
	private Vector2 oldVec = new Vector2(-1000,1000);
	
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
		
		
		if (Gdx.input.isTouched()) { //TODO: В последствии заменить делитель с масштаба на зум
			Vector2 v = getWorldCoordinates(Gdx.input.getX(), Gdx.input.getY());
			if(oldVec.x == v.x && oldVec.y == v.y){
				//return;
			}
			else{
				System.out.println("Input occurred at x=" + v.x + ", y=" + v.y);
				oldVec = new Vector2(v);
			}
		}
		
		
		controller.update(delta,getWorldCoordinates(Gdx.input.getX(), Gdx.input.getY()));
		renderer.render();
		
	}

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub
		
	}
	
	public Vector2 getWorldCoordinates(int screenX,int screenY){
		int x = (int) ((Gdx.input.getX() - MistGame.getWidth()/2) * renderer.camera.zoom / MistGame.WINDOW_SCALE + (int)renderer.camera.position.x);
		int y =	(int) (((MistGame.getHeight() - Gdx.input.getY()) - MistGame.getHeight()/2) * renderer.camera.zoom / MistGame.WINDOW_SCALE + (int)renderer.camera.position.y);
		return new Vector2(x, y);
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
