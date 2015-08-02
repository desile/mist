package com.mist.world.objects;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.mist.actions.ActionHandler;
import com.mist.game.MistGame;
import com.mist.world.World;

public class GameObject {
	
	//TODO: Убрать тестовые функции
	//TODO: Конструкторы на простых типах
	
	public boolean backRender = false; //Опция для отрисовки - всегда отрисовывать сзади

	protected Rectangle bounds;
	protected Texture tex;
	protected TextureRegion texRegion;
	
	protected Player player; //ТОЛЬКО В PLAYER
	protected World currentWorld;
	
	protected String textureName;
	
	protected boolean obstruction = false; //можно ли пройти сквозь объект (стандартно можно)
	protected boolean interaction = false; //можно ли взаимодействовать с объектом (стандартно нельзя)
	public boolean clicked = false;
	
	public ActionHandler actionHandler;
	private boolean actionWithPlayer = false;

	
	public GameObject(Vector2 position, Vector2 rec, String textureName){ //links constructor
		this.bounds = new Rectangle(position.x, position.y, rec.x, rec.y);
		this.textureName = textureName;
		this.actionHandler = new ActionHandler();
		//initIMG();
	}
	
	public GameObject (float x, float y, float width, float height, String textureName) {
		this.bounds = new Rectangle(x, y, width, height);
		this.textureName = textureName;
		this.actionHandler = new ActionHandler();
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
	
	public void renderAction(OrthographicCamera uiCamera){
			if(actionWithPlayer){
				actionHandler.render(uiCamera);			
			}
	}
	
	public void update(float dt) {
		
	}

	public boolean canInteraction() {
		return interaction;
	}

	public boolean isObstruction() {
		return obstruction;
	}
	
	protected boolean action(boolean inAction, Player p){
		if(inAction) actionHandler.action(player);//только при условии текущего взаимодействия
		//побочный эффект: бесконечный action, функции заканчиваются и возвращают сигнал -1 в actionHandler
		actionWithPlayer = inAction;
		player = p;
		return inAction;
	}
	
	public GameObject setActionHandler(ActionHandler a){
		actionHandler = a;
		return this;
	}
	
	public void setCoordinate(int x, int y){
		bounds.x = x;
		bounds.y = y;
	}
	
	
}
