package objects;

import graphics.Texture;
import input.Input;
import org.lwjgl.glfw.GLFW;
import utils.Utility;

public class Player extends Mob {



    public Player(int x, int y){
        super(x,y);
    }

    public void update(){
        int verDir = 0;
        int horDir = 0;
        if((Input.isKeyDown(GLFW.GLFW_KEY_W) || Input.isKeyDown(GLFW.GLFW_KEY_UP)) && getState() != states.HOVERING){
            dy -= 10;
            setState(states.HOVERING);
        }
        //if(Input.isKeyDown(GLFW.GLFW_KEY_S) || Input.isKeyDown(GLFW.GLFW_KEY_UP)){
        //    verDir++;
        //}
        //dy = Utility.approach(dy,max_speed * verDir,3);

        if(Input.isKeyDown(GLFW.GLFW_KEY_A) || Input.isKeyDown(GLFW.GLFW_KEY_LEFT)){
            //dx = Utility.approach(dx, -max_speed, accel);
            horDir--;
            setFacing(-1);
        }
        if(Input.isKeyDown(GLFW.GLFW_KEY_D) || Input.isKeyDown(GLFW.GLFW_KEY_RIGHT)){
            //dx = Utility.approach(dx, max_speed, accel);
            horDir++;
            setFacing(1);
        }
        dx = Utility.approach(dx, max_speed * horDir, accel);

        if(Input.isKeyDown(GLFW.GLFW_KEY_Q)){
            setAngle(getAngle() + 5);
            if(getAngle() >= 360) setAngle(0);
        }else if(Input.isKeyDown(GLFW.GLFW_KEY_E)) {
            setAngle(getAngle() - 5);
            if (getAngle() < 0) setAngle(355);
        }
        super.update();
    }

    public void draw(){
        super.draw();

    }
}
