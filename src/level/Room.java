package level;

import Game.Camera;
import Game.GameManager;
import Game.Text;
import graphics.Shader;
import graphics.Texture;
import graphics.VertexArray;
import input.Input;
import math.Matrix4f;
import math.Vector3f;
import objects.Entity;
import objects.Image;
import objects.Sprite;
import org.lwjgl.glfw.GLFW;

import java.util.ArrayList;

import static Game.GameManager.camera;


public abstract class Room {

    protected VertexArray background;
    protected Texture bgTexture;
    protected String roomName;
    protected Vector3f position = new Vector3f();
    public ArrayList<Entity> entities = new ArrayList<Entity>();

    public Room(){
        roomName = "Room Name :";

        bgTexture = new Texture("/res/background640.png");


        float[] vertices = new float[]{
                0,  360 / 1.0f, 0.0f, // bottom left
                0, -0, 0.0f, // top left
                640 / 1.0f, 0, 0.0f,  // top right
                640 / 1.0f,  360 / 1.0f, 0.0f,  // bottom right
        };

        byte[] indices = new byte[]{
                0,1,2,
                2,3,0
        };

        float[] tcs = new float[]{
                0,1,
                0,0,
                1,0,
                1,1
        };

        background = new VertexArray(vertices, indices, tcs);
    }

    public void init(){

    }

    public void update(){
        for(Entity entity : entities){
            entity.update();
        }

    }

    public void draw(){

        for(Entity entity : entities){
            entity.draw();
        }

        drawBackground();
    }

    public void drawHud(){

    }

    protected void drawBackground(){
        position.x = -camera.getPosition().x;
        position.y = -camera.getPosition().y;

        Shader.SPRITE.enable();
        Shader.SPRITE.setUniformMat4f("ml_matrix", Matrix4f.translate(position));
        //Shader.SPRITE.setUniForm1i("facing", 1);
        //Shader.SPRITE.setUniForm2f("u_spriteSize", 1.0f, 1.0f);
        //Shader.SPRITE.setUniForm2f("u_spritePosition", 0, 0);
        bgTexture.bind();
        background.render();
        Shader.SPRITE.disable();
    }

    public String getRoomName(){
        return roomName;
    }
}
