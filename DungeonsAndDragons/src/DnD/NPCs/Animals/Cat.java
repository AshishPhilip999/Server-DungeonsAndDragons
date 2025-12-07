package DnD.NPCs.Animals;

import DnD.NPCs.NPC;
import Generic.Math.Vector2;

public class Cat extends NPC {
    public Cat(float health) {
        super("CAT", health);
        this.position = new Vector2(-0.5f, 0.0f);
    }

    @Override
    public void move(Vector2 by) {
       this.npcHandler.move(this, by);
    }
}
