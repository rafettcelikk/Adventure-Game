package locations;

import armors.Armor;
import characters.Player;
import weapons.Weapon;

public class Store extends NormalLoc {

    public Store(Player player) {
        super(player, "Mağaza");
    }

    @Override
    public boolean onLocation() {
        System.out.println("-------Mağazaya Hoşgeldin! Neye bakmıştın dostum?-------");
        boolean showMenu = true;
        while(showMenu){
            System.out.println("1- Silahlar");
            System.out.println("2- Zırh");
            System.out.println("3- Mağazadan çık!");
            System.out.print("Yapmak istediğiniz işlemi seçiniz: ");
            int selectCase = scan.nextInt();
            while(selectCase < 1 || selectCase > 3){
                System.out.print("Geçersiz değer! Lütfen bir daha giriniz: ");
                selectCase = scan.nextInt();
            }
            switch (selectCase){
                case 1:
                    printWeapon();
                    buyWeapon();
                    break;
                case 2:
                    printArmor();
                    buyArmor();
                    break;
                case 3:
                    System.out.println("Sonra görüşürüz!");
                    showMenu = false;
                    break;
            }
        }
        return true;
    }


    public void printWeapon() {
        System.out.println("---------------- Silahlar ----------------");
        System.out.println("-------------------------------------------");
        System.out.printf("%-5s %-15s %-10s %-10s%n", "ID", "Silah", "Hasar", "Değeri");
        System.out.println("-------------------------------------------");;
        for (Weapon w : Weapon.weapons()) {
            System.out.printf("%-5d %-15s %-10d %-10d%n",
                    w.getId(),
                    w.getName(),
                    w.getDamage(),
                    w.getPrice());
        }
        System.out.println("0- Çıkış Yap");
    }

    public void buyWeapon(){
        System.out.print("Almak istediğiniz silahı seçiniz: ");
        int selectWeaponId = scan.nextInt();
        while(selectWeaponId < 0 || selectWeaponId > Weapon.weapons().length){
            System.out.print("Geçersiz değer! Lütfen bir daha giriniz: ");
            selectWeaponId = scan.nextInt();
        }
        if(selectWeaponId != 0){
            Weapon selectedWeapon = Weapon.getWeaponById(selectWeaponId);

            if(selectedWeapon != null){
                if(selectedWeapon.getPrice() > this.getPlayer().getMoney()){
                    System.out.println("Yeterli paranız yok!");
                }
                else{
                    System.out.println(selectedWeapon.getName() + " satın aldınız!");
                    this.getPlayer().setMoney(this.getPlayer().getMoney() - selectedWeapon.getPrice());
                    this.getPlayer().getInv().setWeapon(selectedWeapon);
                    System.out.println("Mevcut bakiyeniz: " + this.getPlayer().getMoney());
                }
            }
        }
    }

    public void printArmor(){
        System.out.println("---------------- Zırhlar ----------------");
        System.out.println("-------------------------------------------");
        System.out.printf("%-5s %-15s %-10s %-10s%n", "ID", "Zırh", "Direnç", "Değeri");
        System.out.println("-------------------------------------------");;
        for(Armor a : Armor.armors()){
            System.out.printf("%-5d %-15s %-10d %-10d%n",
                    a.getId(),
                    a.getName(),
                    a.getBlock(),
                    a.getPrice());
        }
        System.out.println("0- Çıkış Yap");
    }

    public void buyArmor(){
        System.out.print("Almak istediğiniz zırhı seçiniz: ");
        int selectArmorId = scan.nextInt();
        while(selectArmorId < 0 || selectArmorId > Armor.armors().length){
            System.out.print("Geçersiz değer! Lütfen bir daha giriniz: ");
            selectArmorId = scan.nextInt();
        }
        if(selectArmorId != 0){
            Armor selectedArmor = Armor.getArmorById(selectArmorId);

            if(selectedArmor != null){
                if(selectedArmor.getPrice() > this.getPlayer().getMoney()){
                    System.out.println("Yeterli paranız yok!");
                }
                else{
                    System.out.println(selectedArmor.getName() + " satın aldınız!");
                    this.getPlayer().setMoney(this.getPlayer().getMoney() - selectedArmor.getPrice());
                    this.getPlayer().getInv().setArmor(selectedArmor);
                    System.out.println("Mevcut bakiyeniz: " + this.getPlayer().getMoney());
                }
            }
        }
    }
}
