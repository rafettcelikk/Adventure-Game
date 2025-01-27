package locations;

import characters.Player;
import monsters.Obstacle;
import monsters.Vampire;

public class Castle extends BattleLoc{
    public Castle(Player player) {
        super(player, "Kale", new Vampire(), "coin", 3);
    }
}
