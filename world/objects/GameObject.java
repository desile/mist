package com.mist.world.objects;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class GameObject {
	
	//TODO: Убрать тестовые функции
	//TODO: Конструкторы на простых типах

	protected Vector2 position;
	protected Rectangle bounds;
	public Texture tex;
	public TextureRegion texRegion;
	
	public GameObject(Vector2 position, Vector2 rec){ //links constructor
		this.position = position;
		this.bounds = new Rectangle(position.x - rec.x/2, position.y - rec.y/2, rec.x, rec.y);
		initTest();
	}
	
	public GameObject (float x, float y, float width, float height) {
		this.position = new Vector2(x, y);
		this.bounds = new Rectangle(x - width / 2, y - height / 2, width, height);
	}
	
	public Vector2 getPosition(){
		return position;
	}
	
	public Rectangle getBounds(){
		return bounds;
	}
	
	public void initTest(){
		tex = new Texture("test_male.png");
	}
	
	public void render(SpriteBatch sb){
		sb.begin();
		sb.draw(tex, position.x, position.y);
		sb.end();
	}
	
}
