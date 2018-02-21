package Game;

import input.Input;
import math.Matrix4f;
import math.Vector3f;
import objects.Entity;
import objects.Mob;
import org.lwjgl.glfw.GLFW;

public class Camera {
    private Vector3f position;
    private Matrix4f projection;
    private float width;
    private float height;
    private Mob following;
    private Boolean follow = false;
    private final Matrix4f STATIC_MATRIX;

    public Camera(float width, float height){
        position = new Vector3f();
        this.width = width;
        this.height = height;
        projection = Matrix4f.orthographic(0.0f, width, height, 0.0f, -1.0f, 1.0f);
        STATIC_MATRIX = Matrix4f.orthographic(0.0f, width, height, 0.0f, -1.0f, 1.0f);
    }

    public void update(){
        if(following != null && follow == true){
            if(following.getCenterX() > -position.x + width - 320){
                //addX(following.getDx());
                addX(-(-position.x + width - 320 - following.getCenterX()));
            }else if(following.getCenterX() < -position.x + 320){
                //addX(following.getDx());
                addX(-(-position.x + 320 - following.getCenterX()));
            }
            if(following.getCenterY() > -position.y + height - 140){
                //addY(following.getDy());
                addY(-(-position.y + height - 140 - following.getCenterY()));
            }else if(following.getCenterY() < -position.y + 140){
                //addY(following.getDy());
                addY(-(-position.y + 140 - following.getCenterY()));
            }




            //position.x = -(following.getCenterX() - width / 2);
            //position.y = -(following.getCenterY() - height / 2);
        }

//        if(Input.isKeyPressed(GLFW.GLFW_KEY_EQUAL)){
//            zoomIn();
//        }else if(Input.isKeyPressed(GLFW.GLFW_KEY_MINUS)){
//            zoomOut();
//        }

        if(Input.isKeyDown(GLFW.GLFW_KEY_KP_4)){
            follow = false;
            position.x = position.x + 15;
        }
        if(Input.isKeyDown(GLFW.GLFW_KEY_KP_6)){
            follow = false;
            position.x = position.x - 15;
        }
        if(Input.isKeyDown(GLFW.GLFW_KEY_KP_8)){
            follow = false;
            position.y = position.y + 15;
        }
        if(Input.isKeyDown(GLFW.GLFW_KEY_KP_5)){
            follow = false;
            position.y = position.y - 15;
        }


        if(Input.isKeyDown(GLFW.GLFW_KEY_KP_ENTER)){
            setFollowing(following);
        }
    }

    public void setFollowing(Mob object){
        this.following = object;
        follow = true;
        if(following != null) {
            position.x = (-following.getCenterX() + width / 2);
            position.y = (-following.getCenterY() + height / 2);
        }
    }

    public void setPosition(Vector3f position){
        this.position = position;
    }

    public void addPosition(Vector3f position){
        this.position.add(position);
    }

    public Vector3f getPosition() {
        return position;
    }

    public void setX(float x){
        position.x = x;
    }

    public void addX(float x){
        position.x -= x;
    }

    public void setY(float y){
        position.y = y;
    }

    public void addY(float y){
        position.y -= y;
    }

    public void setZ(float z){
        position.z = z;
    }

    public void addZ(float z){
        position.z -= z;
    }

    public Matrix4f getProjection() {
        Matrix4f target = new Matrix4f();
        Matrix4f pos    = Matrix4f.translate(position);

        target = projection.multiply(pos);
        return target;
    }

    public void zoomIn(){
        width -= 100.0f;
        height -= 75.0f;
        projection = Matrix4f.orthographic(0.0f, width, height, 0.0f, -1.0f, 1.0f);
    }

    public void zoomOut(){
        width += 100.0f;
        height += 75.0f;
        projection = Matrix4f.orthographic(0.0f, width, height, 0.0f, -1.0f, 1.0f);
    }

    public Matrix4f getSTATIC_MATRIX() {
        return STATIC_MATRIX;
    }
}
