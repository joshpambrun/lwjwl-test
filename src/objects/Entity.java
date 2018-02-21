package objects;

import graphics.Shader;
import graphics.Texture;
import graphics.VertexArray;
import math.Matrix4f;
import math.Vector3f;

public abstract class Entity {

    // Entity properties
    private Vector3f position = new Vector3f();
    private int angle;
    private String name;
    private int width;
    private int height;
    private int xOffset = 0;
    private int yOffset = 0;
    private int Facing = 1;


    // Graphics properties
    protected Texture texture = no_image;
    protected VertexArray mesh;

    // Constants
    protected static Texture no_image = new Texture("/res/no_image.png");

    public Entity(String file, int x, int y){
        if(file != null) texture = new Texture(file);
        //setMesh();
        mesh = VertexArray.getMesh(texture.width, texture.height);
        setX(x);
        setY(y);
    }

    public void update(){

    }

    public void draw(){

        Shader.SPRITE.enable();
        Shader.SPRITE.setUniformMat4f("ml_matrix", Matrix4f.translate(position));//.multiply(Matrix4f.rotate(angle)));
        //Shader.SPRITE.setUniForm2f("u_spriteSize", 1.0f, 1.0f);
        //Shader.SPRITE.setUniForm2f("u_spritePosition", 0, 0);

        if(texture != null) texture.bind();
        else no_image.bind();

        mesh.render();
        Shader.SPRITE.disable();
    }

    // Private Methods

    protected void setMesh(int width, int height){
        mesh = VertexArray.getMesh(width,height);
        //setMeshRepeat(width,height);
    }

    protected void setMeshRepeat(int width, int height){
        mesh = VertexArray.getMesh(width,height,texture.width,texture.height);
    }

    // Getters

    public int getAngle() {
        return angle;
    }

    public void setAngle(int angle) {
        this.angle = angle;
    }

    public float getX(){
        return position.x;
    }

    public void setX(float x){
        position.x = x;
    }

    public float getY(){
        return position.y;
    }

    public float getCenterX(){
        return position.x + width / 2;
    }

    public float getCenterY(){
        return position.y + height / 2;
    }

    public void setY(float y){
        position.y = y;
    }

    public Vector3f getPosition() {
        return position;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getFacing() {
        return Facing;
    }

    public void setFacing(int facing) {
        Facing = facing;
    }

    public int getyOffset() {
        return yOffset;
    }

    public void setyOffset(int yOffset) {
        this.yOffset = yOffset;
    }

    public int getxOffset() {
        return xOffset;
    }

    public void setxOffset(int xOffset) {
        this.xOffset = xOffset;
    }
}
