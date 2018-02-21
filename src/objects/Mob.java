package objects;

import Game.GameManager;
import Game.Text;
import graphics.Shader;
import graphics.Texture;
import graphics.VertexArray;
import input.Input;
import level.GameObject;
import math.Matrix4f;
import math.Vector3f;
import org.lwjgl.glfw.GLFW;
import sun.security.provider.SHA;
import utils.Utility;

import java.awt.*;
import java.lang.reflect.Array;
import java.util.ArrayList;

public class Mob extends Entity {

    private Texture arrowTexture = new Texture("/res/Arrow.png");
    private VertexArray arrowMesh;

    private states state = states.WALKING;
    protected float dy;
    protected float dx;

    protected float max_speed = 10;
    protected float accel = 2;

    public Mob(int x, int y){
        //super(null, x,y);
        //setMesh(32,32);


        super("/res/bird.png",x,y);
        setMesh(30,20);
        setName("Player");
        setWidth(14);
        setHeight(13);
        setxOffset(8);
        setyOffset(4);

        arrowMesh = VertexArray.getmesh(32,32,0.08f,0.5f);
    }

    public void update(){
        super.update();
        // Gravity
        if(dx == 0) dy = Utility.approach(dy, max_speed, 2);
        else dy = Utility.approach(dy, 1, 2);
        checkCollisions(GameManager.room.entities);
    }

    public void draw(){
        if(texture == no_image){
            super.draw();
            return;
        }
        Shader.SHEET.enable();
        Shader.SHEET.setUniformMat4f("ml_matrix", Matrix4f.translate(getPosition()));//.multiply(Matrix4f.rotate(angle)));
        Shader.SHEET.setUniForm1i("facing", getFacing());
        Shader.SHEET.setUniForm2f("u_spriteSize", 1.0f / 2.0f, 1.0f);
        if (state == states.WALKING) {
            Shader.SHEET.setUniForm2f("u_spritePosition", 0.0f / 2.0f, 0);
        } else if (state == states.HOVERING) {
            Shader.SHEET.setUniForm2f("u_spritePosition", 1.0f / 2.0f, 0);
        }
        texture.bind();
        mesh.render();
        Shader.SHEET.disable();
    }

    private void checkCollisions(ArrayList<Entity> entities) {
        state = states.HOVERING;
        Rectangle bounds = new Rectangle((int)(getX() + getxOffset() + getDx()), (int)(getY() + getyOffset() + getDy()), getWidth(), getHeight());
        for(Entity entity : entities){
            if(entity != this) {
                Rectangle entityBounds = new Rectangle((int) entity.getX() + entity.getxOffset(), (int) entity.getY(), entity.getWidth(), entity.getHeight());
                if (bounds.intersects(entityBounds)) {
                    bounds.setLocation((int)(getX() + getxOffset() + getDx()), (int)getY() + getyOffset());
                    while(bounds.intersects(entityBounds)){
                        if(getDx() > 0) setDx(getDx() - 1);
                        else if(getDx() < 0) setDx(getDx() + 1);
                        bounds.setLocation((int)(getX() + getxOffset() + getDx()), (int)getY() + getyOffset());
                    }
                    bounds.setLocation((int)getX() + getxOffset(), (int)(getY() + getyOffset() + getDy()));
                    while(bounds.intersects(entityBounds)){
                        if(getDy() > 0){
                            setDy(getDy() - 1);
                            setState(states.WALKING);
                        }
                        else if(getDy() < 0) setDy(getDy() + 1);
                        bounds.setLocation((int)getX() + getxOffset(), (int)(getY() + getyOffset() + getDy()));
                    }
                    bounds.setLocation((int)(getX() + getxOffset() + getDx()), (int)(getY() + getyOffset() + getDy()));
                    if(bounds.intersects(entityBounds)) {
                        System.out.println("ERROR");
                        while(bounds.intersects(entityBounds) && getDx() != 0){
                            if(getDx() > 0) setDx(getDx() - 1);
                            else if(getDx() < 0) setDx(getDx() + 1);
                            bounds.setLocation((int)(getX() + getxOffset() + getDx()), (int)(getY() + getyOffset() + getDy()));
                        }
                        bounds.setLocation((int)(getX() + getxOffset() + getDx()), (int)(getY() + getyOffset() + getDy()));
                        while(bounds.intersects(entityBounds) && getDy() != 0){
                            if(getDy() > 0) setDy(getDy() - 1);
                            else if(getDy() < 0) setDy(getDy() + 1);
                            bounds.setLocation((int)(getX() + getxOffset() + getDx()), (int)(getY() + getyOffset() + getDy()));
                        }

                    }
                }
            }
        }

        setX(getX() + dx);
        setY(getY() + dy);
    }

    public enum states{
        WALKING,
        HOVERING
    }

    // Getters

    public float getDx(){
        return dx;
    }

    public void setDx(float dx){
        this.dx = dx;
    }

    public float getDy(){
        return dy;
    }

    public void setDy(float dy){
        this.dy = dy;
    }

    public states getState(){
        return state;
    }

    public void setState(states state){
        this.state = state;
    }
}
