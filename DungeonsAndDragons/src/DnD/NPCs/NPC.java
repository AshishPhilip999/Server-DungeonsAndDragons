package DnD.NPCs;

import java.util.ArrayList;
import java.util.List;

import Generic.DnD.Entity;
import Generic.Math.Vector2;

public class NPC extends Entity implements NPCBehaviour {
    public String npcID;

    int destinationRadius;

    Vector2 currDestination;
    protected List<List<Vector2>> destinationVectorMatrix;

    public NPC(String name, float health, Vector2 position, int destinationRadius) {
        this.name = name;
        this.health = health;
        this.position = position;
        this.destinationRadius = destinationRadius;

        this.destinationVectorMatrix = new ArrayList<>();
    }

    @Override
    public void move(Vector2 by) {
        this.position.moveBy(by);
    }

    @Override
    public void moveTo(Vector2 to) {
        this.position.moveTo(to);
    }
}
