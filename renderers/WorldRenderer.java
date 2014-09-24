package com.mist.renderers;

import org.w3c.dom.css.Rect;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.Rectangle;
import com.mist.game.MistGame;
import com.mist.world.World;
import com.mist.world.objects.DynamicGameObject;
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
		camera.position.set(150, 150, 0);
		sb = new SpriteBatch();
		sb.setProjectionMatrix(camera.combined);
		
		dbgrenderer.setProjectionMatrix(camera.combined);
	}
	
	public void render(){
		//renderTestRectangle();
		renderTexture();
		world.dynamTest.render(sb);
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
	
	private void renderTexture(){
		world.testTex.render(sb);
		if(debug){
			dbgrenderer.begin(ShapeType.Line);
			Rectangle rect = world.testTex.getBounds();
			float x = world.testTex.getPosition().x;
			float y = world.testTex.getPosition().y;
			dbgrenderer.setColor(new Color(1, 0, 0, 1));
			dbgrenderer.rect(x, y, rect.width, rect.height);
			dbgrenderer.end();
			
		}
	}
}
