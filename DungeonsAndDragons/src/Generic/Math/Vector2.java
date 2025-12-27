package Generic.Math;

public class Vector2 {
    public float posX;
    public float posY;

    public Vector2() {
    }

    public Vector2(float posX, float posY) {
        this.posX = posX;
        this.posY = posY;
    }

    public void moveBy(Vector2 by) {
        this.posX += by.posX;
        this.posY += by.posY;
    }

    public void moveTo(Vector2 to) {
        this.posX = to.posX;
        this.posY = to.posY;
    }

    public int getIntKey() {
        int x = Math.round(posX);
        int y = Math.round(posY);

        // Pack two 16-bit signed ints into one 32-bit int
        return (x << 16) ^ (y & 0xFFFF);
    }
}
