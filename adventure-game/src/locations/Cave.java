package locations;

import characters.Player;
import monsters.Bear;
import monsters.Obstacle;

public class Cave extends BattleLoc{
    public Cave(Player player) {
        super(player, "Mağara", new Bear(), "water", 4);
    }
}
