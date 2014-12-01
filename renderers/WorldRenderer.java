package com.mist.renderers;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.function.Function;
import java.util.function.ToDoubleFunction;
import java.util.function.ToIntFunction;
import java.util.function.ToLongFunction;

import org.w3c.dom.css.Rect;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShaderProgram;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.mist.game.MistGame;
import com.mist.world.World;
import com.mist.world.objects.DynamicGameObject;
import com.mist.world.objects.GameObject;
import com.mist.world.objects.Player;
import com.sun.javafx.geom.Vec3f;

public class WorldRenderer {
	
	//TODO: Привести в порядок
	//TODO: Возможно разделить на классы, отвественные за рендер различных типов объектов
	
	private World world;
	public OrthographicCamera camera;
	public OrthographicCamera uiCamera;
	
	private ShapeRenderer dbgrenderer = new ShapeRenderer(); //TODO: Орагнизовать возможности дебагмода (рендерятся только контуры объектов, без текстур)
	private ShapeRenderer dialogShape = new ShapeRenderer();
	
	private SpriteBatch sb;
	
	private SpriteBatch uiBatch; //Статичен на экране
	
	private BitmapFont font = new BitmapFont();
	
	private boolean debug = false;
	
	private Rectangle testrec = new Rectangle(-180, -130, 360, 50); //для проверки области нажатий
	
	private ArrayList<GameObject> renderQueue;
	
	
	final String VERT =  
			"attribute vec4 "+ShaderProgram.POSITION_ATTRIBUTE+";\n" +
			"attribute vec4 "+ShaderProgram.COLOR_ATTRIBUTE+";\n" +
			"attribute vec2 "+ShaderProgram.TEXCOORD_ATTRIBUTE+"0;\n" +
			
			"uniform mat4 u_projTrans;\n" + 
			" \n" + 
			"varying vec4 vColor;\n" +
			"varying vec2 vTexCoord;\n" +
			
			"void main() {\n" +  
			"	vColor = "+ShaderProgram.COLOR_ATTRIBUTE+";\n" +
			"	vTexCoord = "+ShaderProgram.TEXCOORD_ATTRIBUTE+"0;\n" +
			"	gl_Position =  u_projTrans * " + ShaderProgram.POSITION_ATTRIBUTE + ";\n" +
			"}";
	
	
	//This will be dumped to System.out for clarity
	final String FRAG = 
			//GL ES specific stuff
			  "#ifdef GL_ES\n" //
			+ "#define LOWP lowp\n" //
			+ "precision mediump float;\n" //
			+ "#else\n" //
			+ "#define LOWP \n" //
			+ "#endif\n" + //
			"//texture 0\n" + 
			"uniform sampler2D u_texture;\n" + 
			"\n" + 
			"//our screen resolution, set from Java whenever the display is resized\n" + 
			"uniform vec2 resolution;\n" + 
			"\n" + 
			"//\"in\" attributes from our vertex shader\n" + 
			"varying LOWP vec4 vColor;\n" +
			"varying vec2 vTexCoord;\n" + 
			"\n" + 
			"//RADIUS of our vignette, where 0.5 results in a circle fitting the screen\n" + 
			"const float RADIUS = 0.75;\n" + 
			"\n" + 
			"//softness of our vignette, between 0.0 and 1.0\n" + 
			"const float SOFTNESS = 0.45;\n" + 
			"\n" + 
			"//sepia colour, adjust to taste\n" + 
			"const vec3 SEPIA = vec3(1.2, 1.0, 0.8); \n" + 
			"\n" + 
			"void main() {\n" + 
			"	//sample our texture\n" + 
			"	vec4 texColor = texture2D(u_texture, vTexCoord);\n" + 
			"		\n" + 
			"	//1. VIGNETTE\n" + 
			"	\n" + 
			"	//determine center position\n" + 
			"	vec2 position = (gl_FragCoord.xy / resolution.xy) - vec2(0.5);\n" + 
			"	\n" + 
			"	//determine the vector length of the center position\n" + 
			"	float len = length(position);\n" + 
			"	\n" + 
			"	//use smoothstep to create a smooth vignette\n" + 
			"	float vignette = smoothstep(RADIUS, RADIUS-SOFTNESS, len);\n" + 
			"	\n" + 
			"	//apply the vignette with 50% opacity\n" + 
			"	texColor.rgb = mix(texColor.rgb, texColor.rgb * vignette, 0.5);\n" + 
			"		\n" + 
			"	//2. GRAYSCALE\n" + 
			"	\n" + 
			"	//convert to grayscale using NTSC conversion weights\n" + 
			"	float gray = dot(texColor.rgb, vec3(0.299, 0.587, 0.114));\n" + 
			"	\n" + 
			"	//3. SEPIA\n" + 
			"	\n" + 
			"	//create our sepia tone from some constant value\n" + 
			"	vec3 sepiaColor = vec3(gray) * SEPIA;\n" + 
			"		\n" + 
			"	//again we'll use mix so that the sepia effect is at 75%\n" + 
			"	texColor.rgb = mix(texColor.rgb, sepiaColor, 0.75);\n" + 
			"		\n" + 
			"	//final colour, multiplied by vertex colour\n" + 
			"	gl_FragColor = texColor * vColor;\n" + 
			"}";
	
	ShaderProgram shader;
	
	//TODO: Составить регламент по работе камер
	
	public WorldRenderer(World world){
		this.world = world;
		//TODO: ZOOM for camera
		this.camera = new OrthographicCamera(MistGame.WINDOW_WIDTH, MistGame.WINDOW_HEIGHT);
		this.uiCamera = new OrthographicCamera(MistGame.WINDOW_WIDTH, MistGame.WINDOW_HEIGHT);
		
		//camera.setToOrtho(false);
		camera.position.set(world.hero.getBounds().getCenter(new Vector2()).x,world.hero.getBounds().getCenter(new Vector2()).y,0);
		camera.zoom = 1.3f;
		
		uiCamera.update();
		
		sb = new SpriteBatch();
		ShaderProgram.pedantic = false;
		shader = new ShaderProgram(VERT, FRAG);
		if (!shader.isCompiled()) {
			System.err.println(shader.getLog());
			System.exit(0);
		}
		if (shader.getLog().length()!=0)
			System.out.println(shader.getLog());
		
		sb.setShader(shader);
		sb.setProjectionMatrix(camera.combined);
		//sb.setColor(1f, 0, 0, 0.75f);
		
		uiBatch = new SpriteBatch();
		uiBatch.setProjectionMatrix(uiCamera.combined);
		
		dbgrenderer.setProjectionMatrix(camera.combined);
		dialogShape.setProjectionMatrix(uiCamera.combined);
		
		renderQueue = new ArrayList<GameObject>();
		
		camera.update();
		
		//System.out.println("")
	}
	
	public void render(){
		//worldGrid();
		world.mapHandler.renderBack(camera,shader);
		renderObjects();
		
		world.mapHandler.renderFront(camera);
		mapDebugObjects();
		
		renderAction();
		
		debugText();
		
		dialogShape.begin(ShapeType.Line);
		Rectangle rect = testrec;
		dialogShape.setColor(new Color(1, 0, 0, 1));
		dialogShape.rect(rect.x, rect.y, rect.width, rect.height);
		dialogShape.end();
		
		updateCamera();
	}
	
	private void debugText(){
		BitmapFont font = new BitmapFont();
		font.setColor(Color.RED);
		uiBatch.begin();
		font.draw(uiBatch, world.hero.getState().toString(), 0, 0);
		uiBatch.end();
	}
	
	
	private void mapDebugObjects(){
		for (MapObject rectangleObject : world.mapHandler.getMap().getLayers().get("collision").getObjects()) {
			//TODO: Сделать определение классов объектов и по разному их обрабатывать (пока только ректанглы)
		    Rectangle rectangle = ((RectangleMapObject) rectangleObject).getRectangle();
		    if(debug){
				dbgrenderer.begin(ShapeType.Line);
				dbgrenderer.setColor(Color.BLUE);
				dbgrenderer.rect(rectangle.x, rectangle.y, rectangle.width, rectangle.height);
				dbgrenderer.end();
				
			}
		}
	}
	
	private void renderAction(){
		if(world.hero.getGoalObject()!=null)
			world.hero.getGoalObject().renderAction(uiCamera);
	}
	
	private void renderObjects(){
		for(GameObject obj : world.objectHandler.objects){
			renderQueue.add(obj);
		}
		renderQueue.add(world.hero);
		
		//сортировка по y координате объектов
		Collections.sort(renderQueue, new Comparator<GameObject>() {

			@Override
			public int compare(GameObject arg0, GameObject arg1) {
				return (int) (arg1.getBounds().y - arg0.getBounds().y);
			}

			
		});
		
		for(GameObject obj : renderQueue){
			renderTexture(obj);
		}
		
		renderQueue.clear();
		
	}
	
	private void updateCamera(){
		camera.position.set(world.hero.getBounds().getCenter(new Vector2()).x,world.hero.getBounds().getCenter(new Vector2()).y,0);
		camera.zoom = 1.3f;
		camera.update();
		sb.setProjectionMatrix(camera.combined);
		sb.setShader(shader);
		
		dbgrenderer.setProjectionMatrix(camera.combined);
	}
	
	private void worldGrid(){
		Gdx.gl.glLineWidth(2);
		dbgrenderer.begin(ShapeType.Line);
		dbgrenderer.setColor(Color.BLACK);
		for(int i = 0; i < 64; i++){
			dbgrenderer.line(0, 32*i, 2048, 32*i);
		}
		for(int i = 0; i < 64; i++){
			dbgrenderer.line(32*i, 0, 32*i, 2048);
		}
		dbgrenderer.end();
	}
	
	private void renderTexture(GameObject obj){
		obj.render(sb);
		if(debug){
			dbgrenderer.begin(ShapeType.Line);
			Rectangle rect = obj.getBounds();
			float x = obj.getBounds().x;
			float y = obj.getBounds().y;
			dbgrenderer.setColor(new Color(1, 0, 0, 1));
			dbgrenderer.rect(x, y, rect.width, rect.height);
			dbgrenderer.end();
			
		}
	}
}
