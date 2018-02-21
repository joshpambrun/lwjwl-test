package Game;

import graphics.Shader;
import input.Input;
import org.lwjgl.glfw.GLFWVidMode;
import org.lwjgl.opengl.GL;

import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL13.*;
import static org.lwjgl.system.MemoryUtil.*;

public class Main implements Runnable {

    private int width = 1280;
    private int height = 720;

    private Thread thread;
    private boolean running = false;
    private boolean error = false;

    private long window;

    //private GameManager gm;

    public void start(){
        running = true;
        thread = new Thread(this, "Game");
        thread.start();
    }

    public void init(){
        if(!glfwInit()){
            System.err.println("GLFW initialization failed!");
        }

        glfwWindowHint(GLFW_RESIZABLE, GL_FALSE);
        window = glfwCreateWindow(width, height, "Hello!", NULL, NULL);

        if(window == NULL) {
            System.err.println("Could not create our window!");
        }

        GLFWVidMode vidmode = glfwGetVideoMode(glfwGetPrimaryMonitor());
        glfwSetWindowPos(window,100,100);

        glfwSetKeyCallback(window, new Input());

        glfwMakeContextCurrent(window);
        glfwShowWindow(window);
        GL.createCapabilities();

        glClearColor(1.0f, 1.0f, 1.0f, 1.0f);
        glEnable(GL_DEPTH_TEST);
        glActiveTexture(GL_TEXTURE1);

        Shader.loadAll();
        glfwSwapInterval(1);

        Text.init();
        GameManager.init(window);
        glfwSwapBuffers(window);
    }

    public void run(){
        init();

        long lastTime = System.nanoTime();
        double delta = 0.0;
        double ns = 1000000000.0 / 60.0;
        int updates = 0;
        int frames = 0;
        long timer = System.currentTimeMillis();

        while(running){
            long now = System.nanoTime();
            delta += (now - lastTime) / ns;
            lastTime = now;
            if(delta >= 1.0){
                GameManager.update();//update();
                updates++;
                delta--;
            }
            GameManager.draw();//render();
            frames++;
            if(System.currentTimeMillis() - timer > 1000){
                timer += 1000;
                //System.out.println(updates + " ups, " + frames + " fps");
                frames = 0;
                updates = 0;
            }
            if(glfwWindowShouldClose(window)){
                running = false;
            }
        }
    }

    public static void main(String[] args){
        new Main().start();
    }
}