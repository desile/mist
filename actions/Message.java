package com.mist.actions;

import java.util.ArrayList;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.Rectangle;
import com.mist.world.objects.Player;

public class Message implements Action {

	private int nextAction = -1;
	private ArrayList<String> textMessage;
	public Rectangle box;
	
	BitmapFont font;
	SpriteBatch batch;
	ShapeRenderer messageBox;
	
	public Message(){
		textMessage = new ArrayList<String>();
		box = new Rectangle(-180, -130, 360, 50);
		font = new BitmapFont();
		batch = new SpriteBatch();
		messageBox = new ShapeRenderer();
	}
	
	@Override
	public void render(OrthographicCamera uiCamera) {
		if(!textMessage.isEmpty()){
			batch.setProjectionMatrix(uiCamera.combined);
			messageBox.setProjectionMatrix(uiCamera.combined);
			
			messageBox.begin(ShapeType.Filled);
			messageBox.setColor(Color.GRAY);
			messageBox.rect(box.x, box.y, box.width, box.height);
			messageBox.end();
			batch.begin();
			font.setColor(Color.WHITE);
				
			for(int i = 0; i < textMessage.size();i++)
				font.draw(batch, textMessage.get(i), -170, -90 - i*20);
			batch.end();
		}
	}

	public int action(Player player) {
		return nextAction;
	}
	
	public Message setTextMessage(String ... strings){
		if(!textMessage.isEmpty()) textMessage.clear();
		for(String s : strings){
			textMessage.add(s);
		}
		return this;
	}
	
	public Message setTextMessage(int nextAction ,String ... strings){
		if(!textMessage.isEmpty()) textMessage.clear();
		for(String s : strings){
			textMessage.add(s);
		}
		this.nextAction = nextAction;
		return this;
	}

}
