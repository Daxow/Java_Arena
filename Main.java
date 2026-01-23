import player.*;
import monsters.Monster;
import utils.Generator;

public class Main {
    public static void main(String[] args) {

        Player player1 = new Player(" TeamTest");

        for (int i = 0; i < 3; i++) {
            Monster monster = Generator.createRandomMonster();
            player1.addMonster(monster);
        }

        player1.getInventory().addItem("PotionSoin", 2);
        player1.getInventory().addItem("Rappel", 1);
        player1.getInventory().addItem("PokéBall", 2);

        player1.displayTeam();
        player1.getInventory().display();
        player1.addMoney(10000);
        System.out.println("Credits: " + player1.getMoney());
        
    }
}
