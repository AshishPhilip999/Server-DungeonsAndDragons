package DnD.NPCs;

import Generic.DnD.Entity;
import Generic.Math.Vector2;

public class NPC extends Entity implements NPCBehaviour {
    public String npcID;
    protected NPCHandler npcHandler;

    public NPC(String name, float health) {
        this.name = name;
        this.health = health;

        this.npcHandler = new NPCHandler();
    }

    @Override
    public void move(Vector2 by) {
        this.position.moveBy(by);
    }
}
