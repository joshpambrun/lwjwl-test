package Game;

import graphics.Shader;
import graphics.Texture;
import graphics.VertexArray;
import math.Matrix4f;
import math.Vector3f;
import org.lwjgl.opengl.ARBGetTextureSubImage;

import static org.lwjgl.opengl.GL11.glEnable;
import static org.lwjgl.opengl.GL11.*;

public class Text{

    protected static VertexArray mesh;
    protected static Texture texture = new Texture("/res/blackfont.bmp");;

    public static void init(){

        float[] vertices = new float[]{
                -texture.width / 32.0f, texture.height / 32.0f, 1f,
                -texture.width / 32.0f, -texture.height / 32.0f, 1f,
                texture.width / 32.0f,  -texture.height / 32.0f, 1f,
                texture.width / 32.0f,  texture.height / 32.0f, 1f,
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
    }

    public static void drawString(String text, int x, int y){
        Shader.TEXT.enable();
        texture.bind();
        Vector3f position = new Vector3f();
        position.x = x;
        position.y = y;
        for(int i = 0; i < text.length(); i++) {
            int asciiCode = (int) text.charAt(i);
            final float cellSize = 1.0f / 16.0f;

            float cellX = ((int) asciiCode % 16) * cellSize;
            float cellY = ((int) asciiCode / 16) * cellSize;
            Shader.TEXT.setUniForm2f("u_spriteSize", 1.0f / 16.0f, 1.0f / 16.0f);
            Shader.TEXT.setUniForm2f("u_spritePosition", cellX, cellY);
            position.x += 20;
            Shader.TEXT.setUniformMat4f("ml_matrix", Matrix4f.translate(position));
            mesh.render();
        }

        Shader.TEXT.disable();
    }
}