package level;

import math.Vector3f;
import objects.Sprite;

import java.awt.*;

public abstract class GameObject extends Sprite{

    protected int dx;
    protected int dy;
    protected int accel = 2;
    protected int max_speed = 10;
    protected Vector3f position = new Vector3f();

    protected int xOffset;
    protected int yOffset;

    public GameObject(String file, int imageWidth, int imageHeight, int x, int y){
        super(file,imageWidth,imageHeight,x,y);
        position.x = x;
        position.y = y;
    }

    protected void setCollision(int xOffset, int yOffset, int width, int height){
        this.xOffset = xOffset;
        this.yOffset = yOffset;
        this.width = width;
        this.height = height;
    }

    public void draw(){
        imagePosition.x = position.x - xOffset;
        imagePosition.y = position.y - yOffset;
        super.draw();
    }

    public int getDx(){
        return dx;
    }

    public void setDx(int dx){
        this.dx = dx;
    }

    public int getDy(){
        return dy;
    }

    public void setDy(int dy){
        this.dy = dy;
    }

    public Rectangle getRectangle(){
        return new Rectangle((int)position.x + dx, (int)position.y + dy, (int)(width * xwarp),(int)(height * ywarp));
    }

    public Rectangle getRectangleDx(){
        return new Rectangle((int)position.x + dx, (int)position.y, (int)(width * xwarp),(int)(height * ywarp));
    }

    public Rectangle getRectangleDy(){
        return new Rectangle((int)position.x, (int)position.y + dy, (int)(width * xwarp),(int)(height * ywarp));
    }
}
