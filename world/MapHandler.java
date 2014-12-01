package com.mist.world;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.glutils.ShaderProgram;
import com.badlogic.gdx.maps.MapLayer;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.MapObjects;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTile;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TiledMapTileSet;
import com.badlogic.gdx.maps.tiled.TiledMapTileSets;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;

public class MapHandler {

	private TiledMap map;
	private OrthogonalTiledMapRenderer renderer;
	
	private MapObjects objects;
	
	public MapHandler(){
		TmxMapLoader loader = new TmxMapLoader();
		map = loader.load("demo.tmx");
		
		renderer = new OrthogonalTiledMapRenderer(map);
		
		for(TiledMapTileSet t : map.getTileSets()){
			for(TiledMapTile i : t){
				if(i.getProperties().containsKey("blocked"))
					System.out.println(i.getId());
			}
		}
		
		for(MapLayer t : map.getLayers()){
			System.out.println(t.getName());
		}
		
		objects = map.getLayers().get("collision").getObjects();
		for(MapObject m : objects){
			System.out.println(m + " " + ((RectangleMapObject) m).getRectangle().toString());
		}
		
	}
	
	public void renderBack(OrthographicCamera camera,ShaderProgram shader){
		renderer.setView(camera);
		int[] renderedLayer = {0,1};
		renderer.getSpriteBatch().setShader(shader);
		renderer.render(renderedLayer);
	}
	
	public void renderFront(OrthographicCamera camera){
		renderer.setView(camera);
		int[] renderedLayer = {2};
		renderer.render(renderedLayer);
	}

	public TiledMap getMap() {
		return map;
	}

	public void setMap(TiledMap map) {
		this.map = map;
	}

	public MapObjects getObjects() {
		return objects;
	}

	public void setObjects(MapObjects objects) {
		this.objects = objects;
	}
	
}
