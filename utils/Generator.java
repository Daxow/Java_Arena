package utils;

import monsters.*;

import java.util.ArrayList;
import java.util.Random;

public class Generator {

    private static Random random = new Random();

    private static ArrayList<String> fireNames = new ArrayList<>();
    private static ArrayList<String> waterNames = new ArrayList<>();
    private static ArrayList<String> plantNames = new ArrayList<>();

    static {
        fireNames.add("Braselion");
        fireNames.add("Pyroflam");
        fireNames.add("Cendrix");
        fireNames.add("Ignivol");
        fireNames.add("Flambryx");
        fireNames.add("Charbête");
        fireNames.add("Volcanix");
        fireNames.add("Pyronard");
        fireNames.add("Fumelion");
        fireNames.add("Brasek");

        waterNames.add("Aquarion");
        waterNames.add("Ondulys");
        waterNames.add("Marinex");
        waterNames.add("Hydrelle");
        waterNames.add("Rivaplouf");
        waterNames.add("Coralyx");
        waterNames.add("Néréideau");
        waterNames.add("Abyssin");
        waterNames.add("Vaporelle");
        waterNames.add("Goutaly");

        plantNames.add("Floréon");
        plantNames.add("Sylviflor");
        plantNames.add("Chloronix");
        plantNames.add("Florimyr");
        plantNames.add("Verdalys");
        plantNames.add("Herbinou");
        plantNames.add("Lianyx");
        plantNames.add("Herbafée");
        plantNames.add("Bourgeonix");
        plantNames.add("Boskys");
    }

    public static Monster createRandomMonster() {
        int type = random.nextInt(3);

        return switch (type) {
            case 0 -> new FireMonster(pickName(fireNames));
            case 1 -> new WaterMonster(pickName(waterNames));
            default -> new PlantMonster(pickName(plantNames));
        };
    }

    private static String pickName(ArrayList<String> names) {
        int index = random.nextInt(names.size());
        return names.remove(index);
    }
}