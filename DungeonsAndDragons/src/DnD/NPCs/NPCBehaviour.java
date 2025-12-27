package DnD.NPCs;

import Generic.Math.Vector2;

public interface NPCBehaviour {
    public void move(Vector2 by);
    public void moveTo(Vector2 to);
}
