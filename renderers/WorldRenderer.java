package com.mist.renderers;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.function.Function;
import java.util.function.ToDoubleFunction;
import java.util.function.ToIntFunction;
import java.util.function.ToLongFunction;

import org.w3c.dom.css.Rect;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.mist.game.MistGame;
import com.mist.world.World;
import com.mist.world.objects.DynamicGameObject;
import com.mist.world.objects.GameObject;
import com.mist.world.objects.Player;

public class WorldRenderer {
	
	//TODO: Привести в порядок
	//TODO: Возможно разделить на классы, отвественные за рендер различных типов объектов
	
	private World world;
	public OrthographicCamera camera;
	private ShapeRenderer dbgrenderer = new ShapeRenderer(); //TODO: Орагнизовать возможности дебагмода (рендерятся только контуры объектов, без текстур)
	private SpriteBatch sb;
	
	private boolean debug = false;
	
	private ArrayList<GameObject> renderQueue; 
	
	//TODO: Составить регламент по работе камер
	
	public WorldRenderer(World world){
		this.world = world;
		//TODO: ZOOM for camera
		this.camera = new OrthographicCamera(MistGame.WINDOW_WIDTH, MistGame.WINDOW_HEIGHT);
		//camera.setToOrtho(false);
		camera.position.set(world.hero.getBounds().getCenter(new Vector2()).x,world.hero.getBounds().getCenter(new Vector2()).y,0);
		camera.zoom = 1.3f;
		camera.update();
		sb = new SpriteBatch();
		sb.setProjectionMatrix(camera.combined);
		
		dbgrenderer.setProjectionMatrix(camera.combined);
		
		renderQueue = new ArrayList<GameObject>();
		
		//System.out.println("")
	}
	
	public void render(){
		//worldGrid();
		world.mapHandler.renderBack(camera);
		renderObjects();
		
		world.mapHandler.renderFront(camera);
		mapDebugObjects();
		updateCamera();
	}
	
	
	private void mapDebugObjects(){
		for (MapObject rectangleObject : world.mapHandler.getMap().getLayers().get("collision").getObjects()) {
			//TODO: Сделать определение классов объектов и по разному их обрабатывать (пока только ректанглы)
		    Rectangle rectangle = ((RectangleMapObject) rectangleObject).getRectangle();
		    if(debug){
				dbgrenderer.begin(ShapeType.Line);
				dbgrenderer.setColor(Color.BLUE);
				dbgrenderer.rect(rectangle.x, rectangle.y, rectangle.width, rectangle.height);
				dbgrenderer.end();
				
			}
		}
	}
	
	private void renderObjects(){
		for(GameObject obj : world.objectHandler.objects){
			renderQueue.add(obj);
		}
		renderQueue.add(world.hero);
		
		//сортировка по y координате объектов
		Collections.sort(renderQueue, new Comparator<GameObject>() {

			@Override
			public int compare(GameObject arg0, GameObject arg1) {
				return (int) (arg1.getBounds().y - arg0.getBounds().y);
			}

			
		});
		
		for(GameObject obj : renderQueue){
			renderTexture(obj);
		}
		
		renderQueue.clear();
		
	}
	
	private void updateCamera(){
		camera.position.set(world.hero.getBounds().getCenter(new Vector2()).x,world.hero.getBounds().getCenter(new Vector2()).y,0);
		camera.zoom = 1.3f;
		camera.update();
		sb.setProjectionMatrix(camera.combined);
		
		dbgrenderer.setProjectionMatrix(camera.combined);
	}
	
	private void worldGrid(){
		Gdx.gl.glLineWidth(2);
		dbgrenderer.begin(ShapeType.Line);
		dbgrenderer.setColor(Color.BLACK);
		for(int i = 0; i < 64; i++){
			dbgrenderer.line(0, 32*i, 2048, 32*i);
		}
		for(int i = 0; i < 64; i++){
			dbgrenderer.line(32*i, 0, 32*i, 2048);
		}
		dbgrenderer.end();
	}
	
	private void renderTexture(GameObject obj){
		obj.render(sb);
		if(debug){
			dbgrenderer.begin(ShapeType.Line);
			Rectangle rect = obj.getBounds();
			float x = obj.getBounds().x;
			float y = obj.getBounds().y;
			dbgrenderer.setColor(new Color(1, 0, 0, 1));
			dbgrenderer.rect(x, y, rect.width, rect.height);
			dbgrenderer.end();
			
		}
	}
}
