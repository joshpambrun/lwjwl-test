package level;

import Game.GameManager;
import input.Input;
import objects.*;
import org.lwjgl.glfw.GLFW;

public class GameRoom extends Room{

    public GameRoom(){
        roomName = "Game Room :";

        entities.add(new Player(200,200));
        entities.add(new Wall(640,320,0,-320));
        entities.add(new Wall(640,320,0,320));

        entities.add(new Wall(321,960,-321,-320)); //Added one to fill a gap
        entities.add(new Wall(320,960,640,-320));

        //entities.add(new Wall(200,100,300,620));
        GameManager.camera.setFollowing((Mob)entities.get(0));
    }

    public void update(){
        super.update();

        if(Input.isKeyPressed(GLFW.GLFW_KEY_ESCAPE)){
            GameManager.changeRoom(GameManager.Rooms.TITLE);
        }
    }

    public void draw(){
        super.draw();
    }
}
