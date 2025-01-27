package locations;

import characters.Player;
import monsters.Obstacle;

import java.util.Random;

public abstract class BattleLoc extends Location {
    private Obstacle obstacle;
    private String reward;
    private int maxObstacle;

    public BattleLoc(Player player, String name, Obstacle obstacle, String reward, int maxObstacle) {
        super(player, name);
        this.obstacle = obstacle;
        this.reward = reward;
        this.maxObstacle = maxObstacle;
    }

    public boolean onLocation(){
        int obsNumber = this.randObstacleNumber();
        System.out.println("Güvenli alan dışındasınız! Bulunduğunuz bölge " + this.getName());
        System.out.println("Bu bölge de " + obsNumber + " tane " + this.obstacle.getName() +  " bulunmaktadır!");
        System.out.println("S- Savaş K- Kaç");
        System.out.print("Hamlenizi seçiniz: ");
        String selectCase = scan.nextLine().toUpperCase();
        if(selectCase.equals("S") && combat(obsNumber)){
            System.out.println(this.getName() + " bölgesinde ki tüm canavarları öldürdünüz!");
            if(this.getObstacle().getName().equals("Yılan")){
                grantRandomReward();
            }
            return true;
        }
        if(this.getPlayer().getHealth() <= 0){
            System.out.println("Öldünüz!");
            return false;
        }
        return true;
    }

    private void grantRandomReward(){
        double chance = Math.random() * 330;

        if(chance < 15){
            this.getPlayer().collectReward("Kılıç");
        } else if(chance < 35){
            this.getPlayer().collectReward("Büyücü Asası");
        } else if(chance < 65){
            this.getPlayer().collectReward("Balta");
        } else if(chance < 115){
            this.getPlayer().collectReward("Ok ve Yay");
        } else if(chance < 130){
            this.getPlayer().collectReward("Hafif");
        } else if(chance < 160){
            this.getPlayer().collectReward("Orta");
        } else if(chance < 210){
            this.getPlayer().collectReward("Ağır");
        } else if(chance < 235){
            this.getPlayer().collectReward("10 altın");
        } else if(chance < 265){
            this.getPlayer().collectReward("5 altın");
        } else if(chance < 315){
            this.getPlayer().collectReward("1 altın");
        }
        else{
            System.out.println("Hiç bir şey kazanamadınız!");
        }
    }

    public boolean combat(int obsNumber){
        for(int i = 1; i <= obsNumber; i++){
            this.getObstacle().setHealth(this.getObstacle().getDefaultHealth());
            playerStats();
            System.out.println();
            obstacleStats(i);
            boolean playerTurn = Math.random() <= 0.5;
            while(this.getPlayer().getHealth() > 0 && this.getObstacle().getHealth() > 0){
                System.out.println("S - Saldır K- Kaç");
                String selectMove = scan.nextLine().toUpperCase();
                if(selectMove.equals("S")){
                    System.out.println("----------------------------------");
                    if(playerTurn){
                        System.out.println("Siz vurdunuz!");
                        this.getObstacle().setHealth(this.obstacle.getHealth() - this.getPlayer().getTotalDamage());
                        afterHit();
                        playerTurn = false;
                    }
                    else{
                        if(this.getObstacle().getHealth() > 0){
                            System.out.println(this.getObstacle().getName() + " size vurdu!");
                            int obstacleDamage = this.getObstacle().getDamage() - this.getPlayer().getInv().getArmor().getBlock();
                            if(obstacleDamage < 0){
                                obstacleDamage = 0;
                            }
                            this.getPlayer().setHealth(this.getPlayer().getHealth() - obstacleDamage);
                            if(this.getPlayer().getHealth() < 0){
                                this.getPlayer().setHealth(0);
                            }
                            afterHit();
                            playerTurn = true;
                        }
                    }
                }
                else{
                    return false;
                }
            }
            if(this.getObstacle().getHealth() < this.getPlayer().getHealth()){
                System.out.println();
                System.out.println(this.getObstacle().getName() + " sürüsünü yendiniz!");
                if(!this.getObstacle().getName().equals("Yılan")){
                    System.out.println("İşte ödülünüz: " + this.getObstacle().getReward());
                    this.getPlayer().setMoney(this.getPlayer().getMoney() + this.getObstacle().getReward());
                    System.out.println("Güncel paranız: " + this.getPlayer().getMoney());
                }
            }
            else{
                return false;
            }
        }
        return true;
    }

    public void afterHit(){
        System.out.println("Canınız: " + this.getPlayer().getHealth());
        System.out.println(this.getObstacle().getName() + " canı: " + this.getObstacle().getHealth());
        System.out.println();
    }

    public void playerStats(){
        System.out.println("Karakter Değerleri");
        System.out.println("---------------------------");
        System.out.println("Sağlık: " + this.getPlayer().getHealth());
        System.out.println("Silah: " + this.getPlayer().getInv().getWeapon().getName());
        System.out.println("Hasar: " + this.getPlayer().getTotalDamage());
        System.out.println("Zırh: " + this.getPlayer().getInv().getArmor().getName());
        System.out.println("Bloklama: " + this.getPlayer().getTotalBlok());
        System.out.println("Para: " + this.getPlayer().getMoney());
    }

    public void obstacleStats(int i){
        System.out.println(i + "." + this.getObstacle().getName() + " Değerleri");
        System.out.println("---------------------------");
        System.out.println("Sağlık: " + this.getObstacle().getHealth());
        System.out.println("Hasar: " + this.getObstacle().getDamage());
        System.out.println("Ödül: " + this.getObstacle().getReward());
    }

    public int randObstacleNumber(){
        Random r = new Random();
        return r.nextInt(this.getMaxObstacle()) + 1;
    }

    public Obstacle getObstacle() {
        return obstacle;
    }

    public void setObstacle(Obstacle obstacle) {
        this.obstacle = obstacle;
    }

    public String getReward() {
        return reward;
    }

    public void setReward(String reward) {
        this.reward = reward;
    }

    public int getMaxObstacle() {
        return maxObstacle;
    }

    public void setMaxObstacle(int maxObstacle) {
        this.maxObstacle = maxObstacle;
    }
}
