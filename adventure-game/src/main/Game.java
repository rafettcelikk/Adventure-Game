package main;

import characters.Player;
import locations.*;

import java.util.Scanner;

public class Game {
    private Scanner scan = new Scanner(System.in);

    public void start(){
        System.out.println("Macera Oyununa Hoşgeldiniz!");
        System.out.println("Oyucnu adınızı giriniz: ");
        //String playerName =  scan.nextLine();
        Player player = new Player("Rafet");
        System.out.println("Hoşgeldin " + player.getName());
        player.selectChar();
        Location location = null;
        while(true){
            player.printPlayerInfo();
            System.out.println("Bölgeler");
            System.out.println("1- Güvenli Alan --> Buralarda canınızı yenileyip hazırlanabilirsiniz!");
            System.out.println("2- Mağaza --> Silah, zırh gibi ekipmanları satın alabilirsiniz!");
            System.out.println("3- Orman --> Ormana giriyorsunuz bu bölgede kurt adam çıkabilir!");
            System.out.println("4- Mağara --> Mağaraya giriyorsunuz bu bölgede ayı çıkabilir!");
            System.out.println("5- Kale --> Kaleye giriyorsunuz bu bölgede vampir çıkabilir!");
            System.out.println("6- Maden --> Madene giriyorsunuz bu bölgede yılan çıkabilir!");
            System.out.println("0- Çıkış Yap --> Oyunu sonlandır!");
            System.out.print("Gitmek istediğiniz noktayı seçiniz: ");
            int selectLoc = scan.nextInt();
            switch (selectLoc){
                case 0:
                    location = null;
                    break;
                case 1:
                    location = new SafeHouse(player);
                    break;
                case 2:
                    location = new Store(player);
                    break;
                case 3:
                    location = new Forest(player);
                    break;
                case 4:
                    location = new Cave(player);
                    break;
                case 5:
                    location = new Castle(player);
                    break;
                case 6:
                    location = new Mine(player);
                    break;
                default:
                    location = new SafeHouse(player);
            }

            if(location == null){
                System.out.println("Oyundan ayrıldığın için üzgünüz tekrardan görüşmek üzere!");
                break;
            }

            if(!location.onLocation()){
                System.out.println("Oyun Bitti!");
                break;
            }
        }
    }
}
