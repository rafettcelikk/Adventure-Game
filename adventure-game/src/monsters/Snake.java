package monsters;

import armors.Armor;
import weapons.Weapon;

public class Snake extends Obstacle{
    public Snake() {
        super(4, "Yılan", 12, (int) (Math.random() * 4), 0);
    }
}
