package com.mist.world.objects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.mist.controllers.Animation;
import com.mist.game.MistGame;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector;
import com.badlogic.gdx.math.Vector2;

public class DynamicGameObject extends GameObject {
	
	//TODO: Изменить конструкторы с векторов на простые типы
	//TOOD: Убрать тестовые функции из классов и перейти к полной модели
	
	public enum State{
		NONE, WALKING, STAND, DEAD, ACTION
	}
	
	protected final float MIN_SPEED = 1f;
	protected Direction direction;
	protected State state;
	protected Animation animation;
	protected float velocity = 1.5f;
	protected boolean playAnimation = false;

	protected AbleToMove ableToMove = new AbleToMove();

	public DynamicGameObject(float x, float y, float width, float height,Direction dir, String textureName) {
		super(x, y, width, height,textureName);
		direction = dir;
		state = State.NONE;
		animation = new Animation();
		initIMG();
	}
	
	public DynamicGameObject(Vector2 position, Vector2 rec,String textureName){
		super(position, rec,textureName);
		direction = Direction.SOUTH;
		state = State.NONE;
		animation = new Animation();
		initIMG();
	}
	
	public DynamicGameObject(Vector2 position, Vector2 rec, Direction dir,String textureName){
		super(position, rec,textureName);
		direction = dir;
		state = State.NONE;
		animation = new Animation();
	//	initIMG();
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
	
	public void update(float dt, Vector2 globalCoordinates){
		animation.update(dt);
	}
	
	public void render(SpriteBatch sb) {
		sb.begin();
		sb.draw(animation.getFrame(),bounds.x,bounds.y);
		sb.end();
	}
	
protected void initIMG(){
		
		int dirCoeffitient = 0;
		switch (direction) {
		case SOUTH:
			dirCoeffitient = 0;
			break;
		case WEST:
			dirCoeffitient = 1;
			break;
		case EAST:
			dirCoeffitient = 2;
			break;
		case NORTH:
			dirCoeffitient = 3;
			break;
			
		default:
			break;
		}
		
		tex = MistGame.content.getTexture(textureName);//TODO: Создать класс-каталог/коллекцию для работы с ресурсами
			TextureRegion[] sprites;//Четыре кадра анимации (собираем куски из текстуры в массив регионов)
		
		if(playAnimation){
			sprites = new TextureRegion[4];//Четыре кадра анимации (собираем куски из текстуры в массив регионов);
			for(int i = 0; i < sprites.length-1; i++) {
				sprites[i] = new TextureRegion(tex, i * 32, dirCoeffitient*32, 32, 32);
			}
			sprites[3] = new TextureRegion(tex, 1 * 32, dirCoeffitient*32, 32, 32);
		}
		else{
			sprites = new TextureRegion[1];
			sprites[0] = new TextureRegion(tex, 1*32,dirCoeffitient*32,32,32);
		}
			
		animation.setFrames(sprites, 1 / 4f);
		
		bounds.width = sprites[0].getRegionWidth();
		bounds.height = sprites[0].getRegionHeight();
		
	}

public float getVelocity() {
	return velocity;
}

public void setVelocity(float velocity) {
	this.velocity = velocity;
}

public State getState() {
	return state;
}

public void setState(State state) {
	this.state = state;
	switch (state) {
	case ACTION:
		playAnimation = false;
		initIMG();
		break;
	case WALKING:
		playAnimation = true;
		initIMG();
		break;
	case STAND:
		playAnimation = false;
		initIMG();
		break;
	default:
		break;
	}
}

public boolean isPlayAnimation() {
	return playAnimation;
}

public void setPlayAnimation(boolean playAnimation) {
	this.playAnimation = playAnimation;
}




	

}
