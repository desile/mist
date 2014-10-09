package com.mist.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
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
	
	private Vector2 click;
	
	public GameScreen(MistGame game){
		this.game = game;
		world = new World();
		renderer = new WorldRenderer(world);
		controller = new WorldController(world);
		
		click = new Vector2();
		
		
		//Create own class for inputprocessor
		//TODO: or move to WorldController class
		Gdx.input.setInputProcessor(new InputProcessor() {
			
			@Override
			public boolean touchUp(int screenX, int screenY, int pointer, int button) {
				// TODO Auto-generated method stub
				return false;
			}
			
			@Override
			public boolean touchDragged(int screenX, int screenY, int pointer) {
				// TODO Auto-generated method stub
				return false;
			}
			
			@Override
			public boolean touchDown(int screenX, int screenY, int pointer, int button) {
				if (button == Input.Buttons.LEFT) {
                    click = onMouseDown();
                    //ВРЕМЕННАЯ МЕРА (ОПЦИОНАЛЬНО) для центрирования передвижения персонажа
                    	click.x -= world.hero.getBounds().width/2;
                    	click.y -= world.hero.getBounds().height/2;
                    return true;
                }
                return false;
			}
			
			@Override
			public boolean scrolled(int amount) {
				// TODO Auto-generated method stub
				return false;
			}
			
			@Override
			public boolean mouseMoved(int screenX, int screenY) {
				// TODO Auto-generated method stub
				return false;
			}
			
			@Override
			public boolean keyUp(int keycode) {
				// TODO Auto-generated method stub
				return false;
			}
			
			@Override
			public boolean keyTyped(char character) {
				// TODO Auto-generated method stub
				return false;
			}
			
			@Override
			public boolean keyDown(int keycode) {
				// TODO Auto-generated method stub
				return false;
			}
		});
		
	}
	
	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(1, 1, 1, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		
		controller.update(delta,click);
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
	
	private Vector2 onMouseDown(){
		//Универсальный перевод системы координат
		
		int x = (int) ((Gdx.input.getX() - MistGame.getWidth()/2) * renderer.camera.zoom / MistGame.WINDOW_SCALE + (int)renderer.camera.position.x);
		int y =	(int) (((MistGame.getHeight() - Gdx.input.getY()) - MistGame.getHeight()/2) * renderer.camera.zoom / MistGame.WINDOW_SCALE + (int)renderer.camera.position.y);
		Vector2 v = new Vector2(x,y);
		
		System.out.println("Input occurred at x=" + v.x + ", y=" + v.y);
		oldVec = new Vector2(v);
		
		return v;
	}

}
