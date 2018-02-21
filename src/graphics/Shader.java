package graphics;

import math.Matrix4f;
import math.Vector3f;
import utils.ShaderUtils;

import java.util.HashMap;
import java.util.Map;

import static org.lwjgl.opengl.GL20.*;

public class Shader {

    public static final int VERTEX_ATTRIB = 0;
    public static final int TCOORD_ATTRIB = 1;

    private boolean enabled = false;

    public static Shader TEXT, SPRITE, SHEET;

    private final int ID;
    private Map<String, Integer> locationCache = new HashMap<String, Integer>();

    public Shader(String vertex, String fragment){
        ID = ShaderUtils.load(vertex, fragment);
    }

    public static void loadAll(){
        TEXT = new Shader("/res/shaders/text.vert", "/res/shaders/text.frag");
        SPRITE = new Shader("/res/shaders/sprite.vert", "/res/shaders/sprite.frag");
        SHEET = new Shader("/res/shaders/sheet.vert", "/res/shaders/sheet.frag");

        Matrix4f pr_matrix = Matrix4f.orthographic(0.0f, 800.0f, 600.0f, 0.0f, -1.0f, 1.0f);
        Shader.TEXT.setUniformMat4f("pr_matrix", pr_matrix);
        Shader.TEXT.setUniForm1i("tex",1);

        Shader.SPRITE.setUniformMat4f("pr_matrix", pr_matrix);
        Shader.SPRITE.setUniForm1i("tex",1);

        Shader.SHEET.setUniformMat4f("pr_matrix", pr_matrix);
        Shader.SHEET.setUniForm1i("tex",1);
    }

    public int getUniform(String name){
        if(locationCache.containsKey(name)){
            return locationCache.get(name);
        }
        int result = glGetUniformLocation(ID, name);
        if(result == -1){
            //System.err.println("Could not find uniform variable '" + name + "'!");
        }else{
            locationCache.put(name, result);
        }
        return result;
    }

    public void setUniForm1i(String name, int value){
        if(!enabled) enable();
        glUniform1i(getUniform(name), value);
    }

    public void setUniForm1f(String name, float value){
        if(!enabled) enable();
        glUniform1f(getUniform(name), value);
    }

    public void setUniForm2f(String name, float x, float y){
        if(!enabled) enable();
        glUniform2f(getUniform(name), x, y);
    }

    public void setUniForm3f(String name, Vector3f vector){
        if(!enabled) enable();
        glUniform3f(getUniform(name), vector.x, vector.y, vector.z);
    }

    public void setUniformMat4f(String name, Matrix4f matrix){
        if(!enabled) enable();
        glUniformMatrix4fv(getUniform(name), false, matrix.toFloatBuffer());
    }

    public void enable(){
        glUseProgram(ID);
        enabled = true;
    }

    public void disable(){
        glUseProgram(0);
        enabled = false;
    }
}
