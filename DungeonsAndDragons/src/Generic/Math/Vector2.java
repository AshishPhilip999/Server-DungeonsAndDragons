package Generic.Math;

public class Vector2 {
    public float posX;
    public float posY;

    public Vector2() { }

    public Vector2(float posX, float posY) {
        this.posX = posX;
        this.posY = posY;
    }

    public void moveBy(Vector2 by) {
        this.posX += by.posX;
        this.posY += by.posY;
    }
}
