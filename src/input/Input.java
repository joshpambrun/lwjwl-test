package input;

import org.lwjgl.glfw.GLFW;
import org.lwjgl.glfw.GLFWKeyCallback;

public class Input extends GLFWKeyCallback {

    public static boolean[] keyPressed = new boolean[348];
    public static boolean[] keyHold = new boolean[348];
    public static int[] pressed = new int[348];

    @Override
    public void invoke(long window, int key, int scancode, int action, int mods) {

        if(action != GLFW.GLFW_REPEAT) {
            keyHold[key] = action != GLFW.GLFW_RELEASE;
            keyPressed[key] = action != GLFW.GLFW_RELEASE;
            int counter = 0;
            while (pressed[counter] != 0) {
                counter++;
            }
            pressed[counter] = key;
        }
    }

    public static boolean isKeyDown(int keycode){
        return keyHold[keycode];
    }

    public static boolean isKeyPressed(int keycode) {
        return keyPressed[keycode];
    }

    public static void clearPressed(){
        int counter = 0;
        while(pressed[counter] != 0){
            keyPressed[pressed[counter]] = false;
            pressed[counter] = 0;
            counter++;
        }
    }
}
