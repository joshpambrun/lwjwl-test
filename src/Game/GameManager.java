package Game;

import graphics.Shader;
import input.Input;
import level.*;
import org.lwjgl.glfw.GLFW;

import static org.lwjgl.glfw.GLFW.glfwDestroyWindow;
import static org.lwjgl.glfw.GLFW.glfwPollEvents;
import static org.lwjgl.glfw.GLFW.glfwSwapBuffers;
import static org.lwjgl.opengl.GL11.*;

public class GameManager {

    private static long window;
    public static Room room;
    public static boolean paused = false;

    public static Camera camera;


    public static void init(long win){
        window = win;

        camera = new Camera(640.0f,360.0f);
        room = new TitleRoom();
        room.init();
        Shader.loadAll();

    }

    public static void update(){
        glfwPollEvents();

        if(Input.isKeyPressed(GLFW.GLFW_KEY_P)) {
            paused = !paused;
        }
        if(!paused) room.update();
        camera.update();
        Input.clearPressed();
    }

    public static void draw(){
        glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);

        Shader.SPRITE.setUniformMat4f("pr_matrix", camera.getProjection());
        Shader.SHEET.setUniformMat4f("pr_matrix", camera.getProjection());
        Shader.TEXT.setUniformMat4f("pr_matrix", camera.getProjection());

        room.draw();

        Shader.SPRITE.disable();
        Shader.SHEET.disable();
        Shader.TEXT.disable();

        drawHud();

        checkErrors();
        glfwSwapBuffers(window);
    }

    public enum Rooms{
        TITLE,
        GAME
    }

    public static void changeRoom(Rooms roomID){
        camera.setFollowing(null);
        switch(roomID){
            case TITLE:
                room = new TitleRoom();
                break;
            case GAME:
                room = new GameRoom();
                break;
        }
        camera.setX(0);
        camera.setY(0);
    }

    public static void end(){
        glfwDestroyWindow(window);
        System.exit(0);
    }

    private static void drawHud(){

        //Shader.SPRITE.setUniformMat4f("pr_matrix", camera.getSTATIC_MATRIX());
        Shader.TEXT.setUniformMat4f("pr_matrix", camera.getSTATIC_MATRIX());
        room.drawHud();

        Text.drawString(room.getRoomName(), 0,16);

        Shader.SPRITE.disable();
        Shader.SHEET.disable();
        Shader.TEXT.disable();
    }

    private static void checkErrors() {
        int i = glGetError();
        if(i != GL_NO_ERROR){
            System.out.println(i);
        }
    }
}
