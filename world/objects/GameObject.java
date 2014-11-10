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

	protected Rectangle bounds;
	protected Texture tex;
	protected TextureRegion texRegion;
	
	protected World currentWorld; //временно?
	
	protected String textureName;
	
	protected boolean obstruction = false; //можно ли пройти сквозь объект (стандартно можно)
	protected boolean interaction = false; //можно ли взаимодействовать с объектом (стандартно нельзя)
	public boolean clicked = false;
	
	public GameObject(Vector2 position, Vector2 rec, String textureName){ //links constructor
		this.bounds = new Rectangle(position.x, position.y, rec.x, rec.y);
		this.textureName = textureName;
		initIMG();
	}
	
	public GameObject (float x, float y, float width, float height, String textureName) {
		this.bounds = new Rectangle(x, y, width, height);
		this.textureName = textureName;
		initIMG();
	}
	
	
	public Rectangle getBounds(){
		return bounds;
	}
	
	private void initIMG(){
		tex = MistGame.content.getTexture(textureName);
		bounds.width = tex.getWidth();
		bounds.height = tex.getHeight();
	}
	
	public void render(SpriteBatch sb){//рисует всю переданную картинку
		sb.begin();
		sb.draw(tex, bounds.x, bounds.y);
		sb.end();
	}
	
	public void update(float dt) {
		
	}

	public boolean canInteraction() {
		return interaction;
	}

	public boolean isObstruction() {
		return obstruction;
	}
	
	public void action(){
		System.out.println("Action!!!");
		clicked = false;
	}
	
}
