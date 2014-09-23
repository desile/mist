package com.mist.renderers;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.Rectangle;
import com.mist.game.MistGame;
import com.mist.world.World;
import com.mist.world.objects.DynamicGameObject;
import com.mist.world.objects.Player;

public class WorldRenderer {
	private World world;
	public OrthographicCamera camera;
	ShapeRenderer renderer = new ShapeRenderer();
	
	public int width;
	public int height;
	
	public WorldRenderer(World world){
		this.world = world;
		//TODO: ZOOM for camera
		this.camera = new OrthographicCamera(MistGame.WINDOW_WIDTH, MistGame.WINDOW_HEIGHT);
		camera.position.set(150, 150, 0);
	}
	
	public void render(){
		
	}
	
	private void renderTestRectangle(){
		renderer.setProjectionMatrix(camera.combined);
		DynamicGameObject testRect = world.testRect;
		renderer.begin(ShapeType.Filled);
		
		Rectangle rect = testRect.getBounds();
		float x1 = testRect.getPosition().x + rect.x;
		float y1 = testRect.getPosition().y + rect.y;
		renderer.setColor(new Color(1, 0, 0, 1));
		renderer.rect(x1, y1, rect.width, rect.height);
		renderer.end();
	}
}
