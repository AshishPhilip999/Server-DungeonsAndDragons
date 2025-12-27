package Generic.DnD;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import Generic.Math.Vector2;

public class Entity implements Damageable {
    protected String name;
    protected float health;
    protected float armorHealth;

    protected Vector2 position;
    protected Vector2 terrainPosition;


    public float getTotalHealth() {
        return (health + armorHealth);
    }

    public Vector2 getPosition() {
        return position;
    }

    public Vector2 getTerrainPosition() {
        return terrainPosition;
    }

    @Override
    public void onDestroy() {
        System.out.println("[onDestroy] Entity destroyed.");
    }

    @Override
    public void willDestroy() {
        System.out.println("[onDestroy] Entity is going to be destroyed.");
    }

    @Override
    public void onDamage() {
        System.out.println("[onDestroy] Entity damaged.");
    }
    
}
