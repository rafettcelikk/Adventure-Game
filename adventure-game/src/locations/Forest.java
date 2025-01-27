package locations;

import characters.Player;
import monsters.Lycan;
import monsters.Obstacle;

public class Forest extends BattleLoc{
    public Forest(Player player) {
        super(player, "Orman", new Lycan(), "firewood", 2);
    }
}
