package com.mist.world.objects;

import com.badlogic.gdx.graphics.Texture;
import com.mist.controllers.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

public class DynamicGameObject extends GameObject {
	
	//TODO: Изменить конструкторы с векторов на простые типы
	//TOOD: Убрать тестовые функции из классов и перейти к полной модели
	
	public enum State{
		NONE, WALKING, STAND, DEAD, CAST, FIGHT
	}
	
	protected final float MIN_SPEED = 1f;
	protected Direction direction;
	protected State state;
	protected Animation animation;

	public DynamicGameObject(float x, float y, float width, float height) {
		super(x, y, width, height);
		direction = Direction.SOUTH;
		state = State.NONE;
		animation = new Animation();
	}
	
	public DynamicGameObject(Vector2 position, Vector2 rec){
		super(position, rec);
		direction = Direction.SOUTH;
		state = State.NONE;
		animation = new Animation();
	}
	
	public DynamicGameObject(Vector2 position, Vector2 rec, Direction dir){
		super(position, rec);
		direction = dir;
		state = State.NONE;
		animation = new Animation();
		//initTest();
	}
	
	public void setAnimation(TextureRegion reg, float delay) {
		setAnimation(new TextureRegion[] { reg }, delay);
	}
	
	public void setAnimation(TextureRegion[] reg, float delay) {
		animation.setFrames(reg, delay);
		bounds.width = reg[0].getRegionWidth();
		bounds.height = reg[0].getRegionHeight();
	}
	
	public void update(float dt) {
		animation.update(dt);
	}
	
	public void render(SpriteBatch sb) {
		sb.begin();
		sb.draw(animation.getFrame(),position.x,position.y);
		sb.end();
	}
	
	

}
