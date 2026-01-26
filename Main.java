import battle.Battle;
import exceptions.IllegalActionException;
import monsters.Monster;
import player.Player;
import utils.Generator;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws InterruptedException {

        Scanner scanner = new Scanner(System.in);

        System.out.println("Entrez le nom de votre équipe :");
        System.out.print("> ");
        String teamName = scanner.nextLine();

        if (teamName.isBlank()) {
            teamName = "Equipe";
        }

        Player player = new Player(teamName);

        for (int i = 0; i < 3; i++) {
            player.addMonster(Generator.createRandomMonster());
        }

        player.getInventory().addItem("Potion de soin", 2);
        player.getInventory().addItem("Rappel", 1);
        player.getInventory().addItem("PokéBall", 2);
        player.addMoney(500);

        boolean running = true;

        while (running) {

            System.out.println("\n[ MENU PRINCIPAL ]");
            System.out.println("\n1 -> Mon équipe");
            System.out.println("2 -> Inventaire");
            System.out.println("3 -> Lancer un combat");
            System.out.println("4 -> Boutique");
            System.out.println("5 -> Quitter");

            Thread.sleep(800);

            int choice = readInt(scanner);

            switch (choice) {
                case 1 -> {
                    player.displayTeam();
                    Thread.sleep(800);
                }
                case 2 -> {
                    player.getInventory().display();
                    System.out.println("Crédits : " + player.getMoney());
                    Thread.sleep(800);
                }
                case 3 -> startBattle(player, scanner);
                case 4 -> shop(player, scanner);
                case 5 -> running = false;
                default -> System.out.println("Choix invalide.");
            }
        }

        scanner.close();
    }

    // ===================== BOUTIQUE =====================

    private static void shop(Player player, Scanner scanner) throws InterruptedException {

        boolean inShop = true;

        while (inShop) {
            System.out.println("\n[ BOUTIQUE ]");
            System.out.println("\nCrédits : " + player.getMoney());
            System.out.println("\n1 -> Potion de soin (50 crédits)");
            System.out.println("2 -> Rappel (100 crédits)");
            System.out.println("3 -> PokéBall (75 crédits)");
            System.out.println("4 -> Retour");

            Thread.sleep(800);

            int choice = readInt(scanner);

            switch (choice) {
                case 1 -> buyItem(player, "Potion de soin", 50);
                case 2 -> buyItem(player, "Rappel", 100);
                case 3 -> buyItem(player, "PokéBall", 75);
                case 4 -> inShop = false;
                default -> System.out.println("Choix invalide.");
            }

            Thread.sleep(800);
        }
    }

    private static void buyItem(Player player, String itemName, int price) {
        if (player.getMoney() < price) {
            System.out.println("Crédits insuffisants.");
            return;
        }
        player.withdrawMoney(price);
        player.getInventory().addItem(itemName, 1);
        System.out.println(itemName + " acheté.");
    }

    // ===================== COMBAT =====================

    private static void startBattle(Player player, Scanner scanner) throws InterruptedException {

        Monster active = chooseMonster(player, scanner);
        if (active == null) {
            System.out.println("Aucun monstre disponible.");
            Thread.sleep(800);
            return;
        }

        Monster enemy = Generator.createRandomMonster();
        System.out.println("\nUn monstre sauvage apparaît : "
                + enemy.getName() + " (" + enemy.getType() + ")");
        Thread.sleep(800);

        while (true) {

            if (allTeamKO(player)) {
                System.out.println("Tous vos monstres sont KO. Combat perdu.");
                Thread.sleep(1200);
                return;
            }

            if (active.isKO()) {
                System.out.println(active.getName() + " (" + active.getType() + ") est KO.");
                System.out.println("Choisissez un autre monstre.");
                Thread.sleep(800);
                active = chooseMonster(player, scanner);
                continue;
            }

            if (enemy.isKO()) {
                System.out.println(enemy.getName() + " (" + enemy.getType() + ") est vaincu.");
                player.addMoney(100);
                System.out.println("100 crédits gagnés.");
                Thread.sleep(1200);
                return;
            }

            System.out.println("\n- TOUR DU JOUEUR -");
            System.out.print("\n");
            System.out.println(active.getName() + " (" + active.getType() + ") : "
                    + active.getHp() + "/" + active.getHpMax() + " PV");
            System.out.println(enemy.getName() + " (" + enemy.getType() + ") : "
                    + enemy.getHp() + "/" + enemy.getHpMax() + " PV");

            Thread.sleep(800);

            System.out.println("\n1 -> Attaquer");
            System.out.println("2 -> Changer de monstre");
            System.out.println("3 -> Utiliser un objet");
            System.out.println("4 -> Capturer");
            System.out.println("5 -> Fuir");

            int action = readInt(scanner);
            boolean actionValide = false;

            switch (action) {
                case 1 -> {
                    Battle.attack(active, enemy);
                    actionValide = true;
                }
                case 2 -> {
                    Monster newMonster = chooseMonster(player, scanner);
                    if (newMonster != null) {
                        active = newMonster;
                        System.out.println("Vous envoyez " + active);
                        Thread.sleep(800);
                    }
                    continue;
                }
                case 3 -> actionValide = useItem(player, scanner);
                case 4 -> {
                    try {
                        System.out.println("Vous utilisez une PokéBall.");
                        Thread.sleep(800);
                        player.tryCapture(enemy);
                        System.out.println(enemy.getName() + " (" + enemy.getType() + ") capturé.");
                        Thread.sleep(1200);
                        return;
                    } catch (IllegalActionException e) {
                        System.out.println(e.getMessage());
                        actionValide = true;
                    }
                }
                case 5 -> {
                    System.out.println("Vous prenez la fuite.");
                    Thread.sleep(800);
                    return;
                }
                default -> System.out.println("Choix invalide.");
            }

            Thread.sleep(800);

            if (actionValide && !enemy.isKO()) {
                System.out.println("\nLe monstre adverse attaque.");
                Thread.sleep(800);
                Battle.attack(enemy, active);
                Thread.sleep(800);
            }
        }
    }

    // ===================== UTILITAIRES =====================

    private static Monster chooseMonster(Player player, Scanner scanner) {
        System.out.println("\nChoisissez un monstre vivant :");

        for (int i = 0; i < player.getTeam().size(); i++) {
            Monster m = player.getTeam().get(i);
            if (!m.isKO()) {
                System.out.println((i + 1) + " -> " + m);
            }
        }

        int choice = readInt(scanner);
        if (choice < 1 || choice > player.getTeam().size()) return null;

        Monster selected = player.getTeam().get(choice - 1);
        return selected.isKO() ? null : selected;
    }

    private static boolean allTeamKO(Player player) {
        for (Monster m : player.getTeam()) {
            if (!m.isKO()) return false;
        }
        return true;
    }

    private static boolean useItem(Player player, Scanner scanner) {
        int potions = player.getInventory().getQuantity("Potion de soin");
        int rappels = player.getInventory().getQuantity("Rappel");

        System.out.println("\n1 -> Potion de soin (x" + potions + ")");
        System.out.println("2 -> Rappel (x" + rappels + ")");
        System.out.println("3 -> Retour");

        int choice = readInt(scanner);
        if (choice == 3) return false;

        Monster target = chooseMonster(player, scanner);
        if (target == null) return false;

        try {
            if (choice == 1) {
                player.usePotion(target);
                System.out.println("Potion utilisée sur " + target.getName() + " (" + target.getType() + ") : " + target.getHp() + "/" + target.getHpMax() + " PV");
            } else if (choice == 2) {
                player.useRevive(target);
                System.out.println("Rappel utilisé sur " + target.getName() + " (" + target.getType() + ") : " + target.getHp() + "/" + target.getHpMax() + " PV");
            }
            return true;
        } catch (IllegalActionException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    private static int readInt(Scanner scanner) {
        try {
            return Integer.parseInt(scanner.nextLine());
        } catch (Exception e) {
            return -1;
        }
    }
}
