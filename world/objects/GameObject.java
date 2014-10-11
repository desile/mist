package com.mist.world.objects;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.mist.game.MistGame;
import com.mist.world.World;

public class GameObject {
	
	//TODO: Убрать тестовые функции
	//TODO: Конструкторы на простых типах

	protected Vector2 position;
	protected Rectangle bounds;
	protected Texture tex;
	protected TextureRegion texRegion;
	
	protected World currentWorld; //временно?
	
	protected String textureName;
	
	public GameObject(Vector2 position, Vector2 rec, String textureName){ //links constructor
		this.position = position;
		this.bounds = new Rectangle(position.x, position.y, rec.x, rec.y);
		this.textureName = textureName;
		initIMG();
	}
	
	public GameObject (float x, float y, float width, float height, String textureName) {
		this.position = new Vector2(x, y);
		this.bounds = new Rectangle(x, y, width, height);
		this.textureName = textureName;
		initIMG();
	}
	
	public Vector2 getPosition(){
		return position;
	}
	
	public Rectangle getBounds(){
		return bounds;
	}
	
	public Vector2 centerPosition(){
		return new Vector2(position.x + bounds.width/2, position.y + bounds.height/2);
	}
	
	private void initIMG(){
		tex = MistGame.content.getTexture(textureName);
		bounds.width = tex.getWidth();
		bounds.height = tex.getHeight();
	}
	
	public void render(SpriteBatch sb){//рисует всю переданную картинку
		sb.begin();
		sb.draw(tex, position.x, position.y);
		sb.end();
	}
	
}
