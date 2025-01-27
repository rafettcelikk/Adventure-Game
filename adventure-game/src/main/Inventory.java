package main;

import armors.Armor;
import characters.Player;
import weapons.Weapon;

import java.util.HashSet;
import java.util.Set;

public class Inventory {
    private Weapon weapon;
    private Armor armor;
    private Player player;
    private final Set<String> rewards;

    public Inventory(Player player) {
        this.weapon = new Weapon( -1, "Yumruk",0, 0);
        this.armor = new Armor(-1, "Kumaş Giysi", 0, 0);
        this.player = player;
        this.rewards = new HashSet<>();
    }

    public Weapon getWeapon() {
        return weapon;
    }

    public void setWeapon(Weapon weapon) {
        this.weapon = weapon;
    }

    public Armor getArmor() {
        return armor;
    }

    public void setArmor(Armor armor) {
        this.armor = armor;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public Set<String> getRewards() {
        return rewards;
    }

    public void addReward(String item) {
        rewards.add(item);
    }

    public void equipReward(String reward){
        switch(reward){
            case "Kılıç":
                setWeapon(new Weapon(1, "Kılıç",18, 5));
                break;
            case "Büyücü Asası":
                setWeapon(new Weapon(2, "Büyücü Asası",24, 10));
                break;
            case "Balta":
                setWeapon(new Weapon(3, "Balta", 15, 7));
                break;
            case "Ok ve Yay":
                setWeapon(new Weapon(4, "Ok ve Yay",12, 3));
                break;
            case "Hafif":
                setArmor(new Armor(1, "Hafif", 1, 10));
                break;
            case "Orta":
                setArmor(new Armor(2, "Orta", 3, 15));
                break;
            case "Ağır":
                setArmor(new Armor(3, "Ağır", 5, 20));
                break;
            case "10 altın":
                this.getPlayer().setMoney(this.getPlayer().getMoney() + 10);
                break;
            case "5 altın":
                this.getPlayer().setMoney(this.getPlayer().getMoney() + 5);
                break;
            case "1 altın":
                this.getPlayer().setMoney(this.getPlayer().getMoney() + 1);
                break;
            default:
                System.out.println("Bilinmeyen ödül: " + reward);
                break;
        }
    }
}
