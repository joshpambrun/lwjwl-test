package graphics;

import utils.BufferUtils;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL15.*;
import static org.lwjgl.opengl.GL20.*;
import static org.lwjgl.opengl.GL30.*;

public class VertexArray {

    private int vao, vbo, ibo, tbo;
    private int count;

    public VertexArray(float[] vertices, byte[] indices, float[] textureCoordinates){
        count = indices.length;

        vao = glGenVertexArrays();
        glBindVertexArray(vao);

        vbo = glGenBuffers();
        glBindBuffer(GL_ARRAY_BUFFER, vbo);
        glBufferData(GL_ARRAY_BUFFER, BufferUtils.createFloatBuffer(vertices), GL_STATIC_DRAW);
        glVertexAttribPointer(Shader.VERTEX_ATTRIB, 3, GL_FLOAT, false, 0,0);
        glEnableVertexAttribArray(Shader.VERTEX_ATTRIB);

        tbo = glGenBuffers();
        glBindBuffer(GL_ARRAY_BUFFER, tbo);
        glBufferData(GL_ARRAY_BUFFER, BufferUtils.createFloatBuffer(textureCoordinates), GL_STATIC_DRAW);
        glVertexAttribPointer(Shader.TCOORD_ATTRIB, 2, GL_FLOAT, false, 0,0);
        glEnableVertexAttribArray(Shader.TCOORD_ATTRIB);

        ibo = glGenBuffers();
        glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, ibo);
        glBufferData(GL_ELEMENT_ARRAY_BUFFER, BufferUtils.createByteBuffer(indices), GL_STATIC_DRAW);

        glBindBuffer(GL_ARRAY_BUFFER, 0);
        glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, 0);
        glBindVertexArray(0);
    }

    public void bind(){
        glBindVertexArray(vao);
        glBindBuffer(GL_ELEMENT_ARRAY_BUFFER,ibo);
    }

    public void unbind(){
        glBindVertexArray(0);
        glBindBuffer(GL_ELEMENT_ARRAY_BUFFER,0);
    }

    public void draw(){
        glDrawElements(GL_TRIANGLES, count, GL_UNSIGNED_BYTE, 0);
    }

    public void render(){
        bind();
        draw();
    }

    public static VertexArray getMesh(float width, float height){
        float[] vertices = new float[]{
                0, height / 1.0f, 0.1f, // bottom left
                0, -0, 0.1f, // top left
                width / 1.0f, 0, 0.1f,  // top right
                width / 1.0f,  height / 1.0f, 0.1f,  // bottom right
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

        return new VertexArray(vertices, indices, tcs);
    }

    public static VertexArray getMesh(float width, float height, float tex_width, float tex_height){
        float[] vertices = new float[]{
                0, height / 1.0f, 0.1f, // bottom left
                0, -0, 0.1f, // top left
                width / 1.0f, 0, 0.1f,  // top right
                width / 1.0f,  height / 1.0f, 0.1f,  // bottom right
        };

        byte[] indices = new byte[]{
                0,1,2,
                2,3,0
        };

        float xrepeat = width / tex_width;
        float yrepeat = height / tex_height;
        float[] tcs = new float[]{
                0,yrepeat,
                0,0,
                xrepeat,0,
                xrepeat,yrepeat
        };

        return new VertexArray(vertices, indices, tcs);
    }

    public static VertexArray getmesh(float width, float height, float x, float y){
        float[] vertices = new float[]{
                width * -x, height * (1 - y), 0.1f, // bottom left
                width * -x, height * -y, 0.1f, // top left
                width * (1 - x), height * -y, 0.1f,  // top right
                width * (1 - x),  height * (1 - y), 0.1f,  // bottom right
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

        return new VertexArray(vertices, indices, tcs);
    }
}
