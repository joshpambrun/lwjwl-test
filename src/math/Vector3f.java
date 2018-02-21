package math;

public class Vector3f {

    private static Vector3f tempVector3f = new Vector3f();

    public float x,y,z;

    public Vector3f(){
        x = 0.0f;
        y = 0.0f;
        z = 0.0f;
    }

    public Vector3f(float x, float y, float z){
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public void add(Vector3f vector3f){
        x += vector3f.x;
        y += vector3f.y;
        z += vector3f.z;
    }

    public static Vector3f flash(float x, float y, float z){
        tempVector3f.x = x;
        tempVector3f.y = y;
        tempVector3f.z = z;
        return tempVector3f;
    }
}
