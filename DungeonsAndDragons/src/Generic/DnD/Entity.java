package Generic.DnD;

import Generic.Math.Vector2;

public class Entity implements Damageable {
    protected String name;
    protected float health;
    protected float armorHealth;

    public Vector2 position;

    public float getTotalHealth() {
        return (health + armorHealth);
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
