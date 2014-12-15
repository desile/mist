package com.mist.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.glutils.ShaderProgram;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.mist.actions.Message;
import com.mist.controllers.GameInputController;
import com.mist.controllers.WorldController;
import com.mist.game.MistGame;
import com.mist.renderers.WorldRenderer;
import com.mist.world.World;
import com.mist.world.objects.DynamicGameObject.State;
import com.sun.xml.internal.ws.api.pipe.NextAction;

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

	private Vector2 uiClick;
	
	public GameScreen(MistGame game){
		this.game = game;
		world = new World();
		renderer = new WorldRenderer(world);
		controller = new WorldController(world);
		
		click = new Vector2();
		uiClick = new Vector2();
		
		//Create own class for inputprocessor
		//TODO: or move to WorldController class
		Gdx.input.setInputProcessor(new GameInputController() {
			@Override
			public boolean touchDown(int screenX, int screenY, int pointer, int button) {
				//if (button == Input.Buttons.LEFT) {
                    click = onMouseDown();
                    uiClick = onUIDown();
                    clickedObject = world.objectHandler.searchObject(click);
                    
                    /*switch (world.hero.getState()) {
					case WALKING:
						world.hero.setGoalObject(null);
						break;
					case ACTION:
	                    if(world.hero.getGoalObject().getBounds().contains(click.x, click.y)){
	                    	world.hero.getGoalObject().actionHandler.nextAction();
	                    }
						break;
					default:
						world.hero.setState(State.WALKING);
						break;
					}*/
                    
                    
                    if(world.hero.getState() == State.ACTION){ //MAKE PATTERN
                    	//try{
                    	if(world.hero.getGoalObject().actionHandler.getCurrentAction() instanceof Message)
                    		if(((Message)world.hero.getGoalObject().actionHandler.getCurrentAction()).box.contains(uiClick.x, uiClick.y)){
                    		//if(testrec.contains(uiClick.x, uiClick.y)){
                    			world.hero.getGoalObject().actionHandler.nextAction();
                    			world.hero.getGoalObject().actionHandler.dialogLogic(world.hero);
                    			System.out.println("CLICK");
                    			return true;
                    		}
                    	//}catch(NullPointerException e){world.hero.setState(State.WALKING);}
                    }
                    else{
                    	world.hero.setState(State.WALKING);
                    }
                    
                    if(world.hero.getState() == State.WALKING) 
                    	world.hero.setGoalObject(null);
                    
                    if(clickedObject!=null){
                    	System.out.println("hello, yes, this is the object");
                    	clickedObject.clicked = true;
                    	world.hero.setGoalObject(clickedObject);
                    }
                    else{
                    	world.hero.setState(State.WALKING);
                    }
                    
                    
                    
                    
                    if(previousClickedObject!= null && previousClickedObject!=clickedObject)
                    	previousClickedObject.clicked = false;//если было произведено нажатие на другой объект, то предыдущий перестает быть выбраным
                    previousClickedObject = clickedObject;
                    
                    //ВРЕМЕННАЯ МЕРА (ОПЦИОНАЛЬНО) для центрирования передвижения персонажа
                    	click.x -= world.hero.getBounds().width/2;
                    	click.y -= world.hero.getBounds().height/2;
                    	
                    return true;
                //}
                //return false;
			}
			
		private void setGoal(){
			
		}
			
		});
		

		
	}
	
	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(0, 0, 0, 1);
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
	
	private Vector2 onUIDown(){
		Vector2 v = new Vector2((Gdx.input.getX() - MistGame.getWidth()/2)/MistGame.WINDOW_SCALE,-(Gdx.input.getY() - MistGame.getHeight()/2)/MistGame.WINDOW_SCALE);
		System.out.println("Origin at x=" + v.x + ", y=" + v.y);
		return v;
	}
	
	public World getWorld(){
		return world;
	}
	

}
