package com.mist.renderers;

import org.w3c.dom.css.Rect;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.Rectangle;
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
	ShapeRenderer dbgrenderer = new ShapeRenderer(); //TODO: Орагнизовать возможности дебагмода (рендерятся только контуры объектов, без текстур)
	SpriteBatch sb;
	
	public int width;
	public int height;
	
	public boolean debug = true;
	
	//TODO: Составить регламент по работе камер
	
	public WorldRenderer(World world){
		this.world = world;
		//TODO: ZOOM for camera
		this.camera = new OrthographicCamera(MistGame.WINDOW_WIDTH, MistGame.WINDOW_HEIGHT);
		//camera.setToOrtho(false);
		camera.position.set(50,70,0);
		camera.zoom = 1.3f;
		camera.update();
		sb = new SpriteBatch();
		sb.setProjectionMatrix(camera.combined);
		
		dbgrenderer.setProjectionMatrix(camera.combined);
		
		//System.out.println("")
	}
	
	public void render(){
		//worldGrid();
		//renderTestRectangle();
		renderTexture(world.dynamTest);
		renderTexture(world.testTex);
		renderTexture(world.test2);
		renderTexture(world.test3);
		renderTexture(world.test4);
		renderTexture(world.hero);
	}
	
	public void worldGrid(){
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
	
	private void renderTestRectangle(){
		DynamicGameObject testRect = world.testRect;
		dbgrenderer.begin(ShapeType.Line);
		
		Rectangle rect = testRect.getBounds();
		float x1 = testRect.getPosition().x + rect.x;
		float y1 = testRect.getPosition().y + rect.y;
		dbgrenderer.setColor(new Color(1, 0, 0, 1));
		dbgrenderer.rect(x1, y1, rect.width, rect.height);
		dbgrenderer.end();
	}
	
	private void renderTexture(GameObject obj){
		obj.render(sb);
		if(debug){
			dbgrenderer.begin(ShapeType.Line);
			Rectangle rect = obj.getBounds();
			float x = obj.getPosition().x;
			float y = obj.getPosition().y;
			dbgrenderer.setColor(new Color(1, 0, 0, 1));
			dbgrenderer.rect(x, y, rect.width, rect.height);
			dbgrenderer.end();
			
		}
	}
}
