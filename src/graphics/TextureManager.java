package graphics;

import java.util.ArrayList;

public class TextureManager {

    private static ArrayList<Texture> textures = new ArrayList<Texture>();
    private static ArrayList<VertexArray> meshes = new ArrayList<VertexArray>();
    private static int size = 0;

    public static int newTexture(String file, VertexArray mesh){
        textures.add(new Texture(file));
        meshes.add(mesh);
        size++;
        return size;
    }

    public static int newTexture(String file, float height, float width){
        textures.add(new Texture(file));
        meshes.add(VertexArray.getMesh(height,width));
        size++;
        return size;
    }

    public static int newTexture(String file, float height, float width, float x, float y){
        textures.add(new Texture(file));
        meshes.add(VertexArray.getmesh(width,height,x,y));
        size++;
        return size;
    }

    public static void removeTexture(int id){
        textures.set(id, null);
        meshes.set(id, null);
        size--;
    }
}
