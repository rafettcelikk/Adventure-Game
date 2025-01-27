package locations;

import characters.Player;
import monsters.Obstacle;
import monsters.Snake;

public class Mine extends BattleLoc{
    public Mine(Player player) {
        super(player, "Maden", new Snake(), "random item", 5);
    }
}
