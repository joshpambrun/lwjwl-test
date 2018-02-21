package objects;

import graphics.Shader;
import graphics.Texture;
import graphics.VertexArray;
import input.Input;
import level.Room;
import math.Matrix4f;
import math.Vector3f;
import org.lwjgl.glfw.GLFW;

import java.awt.*;
import java.util.ArrayList;

public abstract class Sprite{

    protected Vector3f imagePosition = new Vector3f();
    protected int width;
    protected int height;
    protected int facing = 1;
    protected int angle = 0;
    protected String name;
    protected float xwarp = 1.0f;
    protected float ywarp = 1.0f;
    protected int animationFrame = 0;
    protected int animationCounter = 0;

    public static Texture noTexture = new Texture("/res/no_image.png");
    protected VertexArray mesh;
    protected Texture texture;

    public Sprite(String file, int x, int y){
        this(file, -1,-1,x,y);
    }

    public Sprite(String file, int imageX, int imageY, int x, int y){
        if(file != null) {
            texture = new Texture(file);
        }else{
            texture = noTexture;
        }

        int imageWidth  = texture.width;
        int imageHeight = texture.height;

        if(imageX >= 0 && imageY >= 0){
            imageWidth  = imageX;
            imageHeight = imageY;
        }

        float[] vertices = new float[]{
                0, imageHeight / 1.0f, 0.1f, // bottom left
                0, -0, 0.1f, // top left
                imageWidth / 1.0f, 0, 0.1f,  // top right
                imageWidth / 1.0f,  imageHeight / 1.0f, 0.1f,  // bottom right
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

        mesh = new VertexArray(vertices, indices, tcs);

        imagePosition.x = x;
        imagePosition.y = y;
    }

    public void warp(float xwarp, float ywarp){
        this.xwarp = xwarp;
        this.ywarp = ywarp;
    }

    public void update(){
    }

    public void draw(){
        Shader.SPRITE.enable();
        Shader.SPRITE.setUniformMat4f("ml_matrix", Matrix4f.translate(imagePosition).multiply(Matrix4f.rotate(angle).multiply(Matrix4f.increase(xwarp,ywarp))));
        Shader.SPRITE.setUniForm1i("facing", facing);
        Shader.SPRITE.setUniForm2f("u_spriteSize", 1.0f, 1.0f);
        Shader.SPRITE.setUniForm2f("u_spritePosition", 0, 0);
        texture.bind();
        mesh.render();
        Shader.SPRITE.disable();
    }
}