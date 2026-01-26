package battle;

import monsters.Monster;
import exceptions.IllegalActionException;

public class Battle {

    public static void attack(Monster attacker, Monster target) {
        try {
            int damage = attacker.attack(target);
            System.out.println(attacker.getName() + " attaque " + target.getName() + " et fait " + damage + " dégâts.");
            if (target.isKO()) {
                System.out.println(target.getName() + " est KO !");
            }
        } catch (IllegalActionException e) {
            System.out.println(e.getMessage());
        }
    }
}
