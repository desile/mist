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
	protected Texture tex;
	protected TextureRegion texRegion;
	
	public GameObject(Vector2 position, Vector2 rec){ //links constructor
		this.position = position;
		this.bounds = new Rectangle(position.x, position.y, rec.x, rec.y);
		initTest();
	}
	
	public GameObject (float x, float y, float width, float height) {
		this.position = new Vector2(x, y);
		this.bounds = new Rectangle(x, y, width, height);
		initTest();
	}
	
	public Vector2 getPosition(){
		return position;
	}
	
	public Rectangle getBounds(){
		return bounds;
	}
	
	private void initTest(){
		tex = new Texture("test_male.png");
		bounds.width = tex.getWidth();
		bounds.height = tex.getHeight();
	}
	
	public void render(SpriteBatch sb){//рисует всю переданную картинку
		sb.begin();
		sb.draw(tex, position.x, position.y);
		sb.end();
	}
	
}
