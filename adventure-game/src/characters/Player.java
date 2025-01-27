package characters;

import main.Inventory;

import java.util.Scanner;

public class Player {
    private String name;
    private String charName;
    private int damage, health, money, block, defaultHealth;
    private Scanner scan = new Scanner(System.in);
    private Inventory inv;

    public Player(String name){
        this.name = name;
        this.inv = new Inventory(this);
    }
    public void selectChar(){
        System.out.println("------------------------------------------------");
        GameCharacter[] charList = {new Warrior(), new Pyromancer(), new Knight(), new Hunter()};
        System.out.printf("%-5s %-15s %-10s %-10s %-10s%n", "ID", "Karakter", "Hasar", "Sağlık", "Para");
        System.out.println("------------------------------------------------");
        for (GameCharacter gameCharacter : charList) {
            System.out.printf("%-5d %-15s %-10d %-10d %-10d%n",
                    gameCharacter.getId(),
                    gameCharacter.getCharacterClass(),
                    gameCharacter.getDamage(),
                    gameCharacter.getHealth(),
                    gameCharacter.getMoney());
        }
        System.out.println("------------------------------------------------");
        System.out.print("Karakter sınıfınız için Id giriniz: ");
        int selectCharId = scan.nextInt();
        switch (selectCharId) {
            case 1:
                initPlayer(new Warrior());
                break;
            case 2:
                initPlayer(new Pyromancer());
                break;
            case 3:
                initPlayer(new Knight());
                break;
            case 4:
                initPlayer(new Hunter());
                break;
            default:
                initPlayer(new Knight());
                break;
        }
    }

    public void initPlayer(GameCharacter gameCharacter){
        this.setDamage(gameCharacter.getDamage());
        this.setHealth(gameCharacter.getHealth());
        this.setDefaultHealth(gameCharacter.getHealth());
        this.setMoney(gameCharacter.getMoney());
        this.setCharName(gameCharacter.getCharacterClass());
    }

    public void printPlayerInfo(){
        System.out.printf("Silah: %s, Zırh: %s, Hasarınız: %d, Bloklama: %d, Sağlık Değeriniz: %d, Paranız: %d%n",
                this.getInv().getWeapon().getName(),
                this.getInv().getArmor().getName(),
                this.getTotalDamage(),
                this.getTotalBlok(),
                this.getHealth(),
                this.getMoney());
    }

    public void collectReward(String reward){
        int totalCoin = 0;
        if(!this.inv.getRewards().contains(reward)) {
            this.inv.addReward(reward);
            System.out.println("İşte ödülünüz: " + reward);
            if (reward.equals("Kılıç") ||
                    reward.equals("Büyücü Asası") ||
                    reward.equals("Balta") ||
                    reward.equals("Ok ve Yay") ||
                    reward.equals("Hafif") ||
                    reward.equals("Orta") ||
                    reward.equals("Ağır")) {
                System.out.println(reward + " bu eşyayı kuşanmak ister misiniz ? E: evet - H: hayır");
                scan.nextLine();
                String selectReward = scan.nextLine().toUpperCase();
                if (selectReward.equals("E")) {
                    this.inv.equipReward(reward);
                    System.out.println("Yeni eşya kuşanıldı: " + reward);
                } else {
                    System.out.println("Mevcut ekipmanlar kullanılmaya devam edilecek");
                }
            } else if(reward.equals("10 altın") || reward.equals("5 altın") || reward.equals("1 altın")) {
                if (reward.equals("10 altın")) {
                    totalCoin += 10;
                }
                if (reward.equals("5 altın")) {
                    totalCoin += 5;
                }
                if (reward.equals("1 altın")) {
                    totalCoin += 1;
                }
                int newBalance = this.getMoney() + totalCoin;
                this.setMoney(newBalance);
                System.out.println("Güncel bakiye: " + newBalance);
            } else{
                System.out.println("----------------");
            }
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCharName() {
        return charName;
    }

    public void setCharName(String charName) {
        this.charName = charName;
    }

    public int getTotalDamage(){
        return damage + this.getInv().getWeapon().getDamage();
    }
    public int getDamage() {
        return damage;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    public Inventory getInv() {
        return inv;
    }

    public void setInv(Inventory inv) {
        this.inv = inv;
    }

    public int getTotalBlok(){
        return block + this.getInv().getArmor().getBlock();
    }

    public int getBlock() {
        return block;
    }

    public void setBlock(int block) {
        this.block = block;
    }

    public int getDefaultHealth() {
        return defaultHealth;
    }

    public void setDefaultHealth(int defaultHealth) {
        this.defaultHealth = defaultHealth;
    }
}
