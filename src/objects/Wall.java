package objects;

import level.GameObject;

public class Wall extends Entity{

    public Wall(int width, int height, int x, int y){
        super(null,x,y);
        setName("Wall");
        setMeshRepeat(width, height);
        setWidth(width);
        setHeight(height);
    }
}
