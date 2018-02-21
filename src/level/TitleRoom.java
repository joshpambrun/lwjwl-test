package level;

import Game.*;
import Game.Main;
import input.Input;
import objects.*;
import org.lwjgl.glfw.GLFW;
import org.newdawn.slick.Game;

public class TitleRoom extends Room{

    private int selected = 0;
    private String[] options = {"Start Game", "Options", "Exit"};

    public TitleRoom(){
        roomName = "Title Room :";

        entities.add(new Image("/res/velocity_logo.png",400,120));
    }

    public void update(){
        super.update();


        if(Input.isKeyPressed(GLFW.GLFW_KEY_DOWN)){
            selected++;
            if(selected > options.length - 1) selected = 0;
        }
        if(Input.isKeyPressed(GLFW.GLFW_KEY_UP)){
            selected--;
            if(selected < 0) selected = options.length - 1;
        }

        if(Input.isKeyPressed(GLFW.GLFW_KEY_ENTER) || Input.isKeyPressed(GLFW.GLFW_KEY_SPACE)){
            switch(selected){
                case 0:
                    GameManager.changeRoom(GameManager.Rooms.GAME);
                    break;
                case 2:
                    GameManager.end();
                    break;
            }
        }
    }

    public void draw(){
        super.draw();

        for(int i = 0; i < options.length; i++){
            if(selected == i){
                Text.drawString("> " + options[i], 40, 100 + i * 30);
            }else{

                Text.drawString(options[i], 40, 100 + i * 30);
            }
        }
    }
}
