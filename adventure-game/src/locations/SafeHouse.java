package locations;

import characters.Player;

public class SafeHouse extends NormalLoc{
    public SafeHouse(Player player) {
        super(player, "Güvenli Alan");
    }

    @Override
    public boolean onLocation() {
        System.out.println("Güvenli alandasınız.");
        System.out.println("Canınız yenileniyor!");
        this.getPlayer().setHealth(this.getPlayer().getDefaultHealth());
        return true;
    }
}
