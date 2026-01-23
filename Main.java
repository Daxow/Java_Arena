import monsters.*;

public class Main {
    public static void main(String[] args) {
        Monster fire = new FireMonster("Dracaufeu");
        Monster water = new WaterMonster("Aquali");
        Monster plant = new PlantMonster("Bulbizarre");

        System.out.println("DEBUT");
        System.out.println(fire);
        System.out.println(water);
        System.out.println(plant);

        System.out.println("\nTEST ATTAQUE");

        System.out.println(fire.getName() + " attaque " + plant.getName() + " -> " + fire.attack(plant) + " degats");
        System.out.println(plant);

        System.out.println(water.getName() + " attaque " + fire.getName() + " -> " + water.attack(fire) + " degats");
        System.out.println(fire);

        System.out.println(plant.getName() + " attaque " + water.getName() + " -> " + plant.attack(water) + " degats");
        System.out.println(water);

        System.out.println("\nTEST KO");
        while (!fire.isKO()) {
            water.attack(fire);
        }
        System.out.println(fire.getName() + " est KO ? " + fire.isKO());
        System.out.println(fire);
    }
}