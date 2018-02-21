package graphics;

public class SpriteSheet {

    private Texture sheet;
    private VertexArray mesh;
    private int width;
    private int height;
    private int tileWidth;
    private int tileHeight;

    public SpriteSheet(Texture texture, int tileWidth, int tileHeight){
        this.sheet = texture;
        this.tileWidth = tileWidth;
        this.tileHeight = tileHeight;
        this.width = texture.width;
        this.height = texture.height;
        mesh = VertexArray.getMesh(tileWidth, tileHeight);
    }

    public void draw(int x, int y){

        Shader.SHEET.setUniForm2f("u_spriteSize", 1.0f / 2.0f, 1.0f);
        Shader.SHEET.setUniForm2f("u_spritePosition", 0.0f / 2.0f, 0);
    }
}
