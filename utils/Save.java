package utils;

import monsters.*;
import player.Player;

import java.io.*;

public class Save {

    private static final String FILE = "save.csv";

    public static void save(Player player) {
        try {
            FileWriter writer = new FileWriter(FILE);

            writer.write("TEAM;" + player.getTeamName() + "\n");

            for (Monster m : player.getTeam()) {
                writer.write("MONSTER;"
                        + m.getType() + ";"
                        + m.getName() + ";"
                        + m.getHp() + ";"
                        + m.getHpMax() + "\n");
            }

            player.getInventory().getItems().forEach((item, quantity) -> {
                try {
                    writer.write("ITEM;" + item + ";" + quantity + "\n");
                } catch (IOException ignored) {}
            });

            writer.write("MONEY;" + player.getMoney() + "\n");

            writer.close();
            System.out.println("Sauvegarde effectuée.");

        } catch (IOException e) {
            System.out.println("Erreur lors de la sauvegarde.");
        }
    }

    public static Player load() {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(FILE));
            String line;
            Player player = null;

            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(";");

                switch (parts[0]) {

                    case "TEAM" -> player = new Player(parts[1]);

                    case "MONSTER" -> {
                        Monster m;
                        if (parts[1].equals("Feu")) {
                            m = new FireMonster(parts[2]);
                        } else if (parts[1].equals("Eau")) {
                            m = new WaterMonster(parts[2]);
                        } else {
                            m = new PlantMonster(parts[2]);
                        }

                        int hp = Integer.parseInt(parts[3]);
                        int hpMax = Integer.parseInt(parts[4]);
                        m.receiveDamage(hpMax - hp);
                        player.addMonster(m);
                    }

                    case "ITEM" ->
                            player.getInventory().addItem(parts[1], Integer.parseInt(parts[2]));

                    case "MONEY" ->
                            player.addMoney(Integer.parseInt(parts[1]));
                }
            }

            reader.close();
            System.out.println("Sauvegarde chargée.");
            return player;

        } catch (IOException e) {
            System.out.println("Aucune sauvegarde trouvée.");
            return null;
        }
    }
}
