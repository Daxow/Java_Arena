package monsters;

import exceptions.IllegalActionException;

public class WaterMonster extends Monster {

    public WaterMonster(String name) {
        super(name, 110, 18);
    }

    @Override
    public int attack(Monster target) throws IllegalActionException {
        if (this.isKO()) {
            throw new IllegalActionException("Le monstre est KO et ne peut pas attaquer.");
        }

        if (target.isKO()) {
            throw new IllegalActionException("La cible est déjà KO.");
        }

        int damage = getAtk();

        if (target.isWeakAgainst(this)) {
            damage *= 2;
        }

        target.receiveDamage(damage);
        return damage;
    }

    @Override
    public boolean isWeakAgainst(Monster attacker) {
        return attacker instanceof PlantMonster;
    }

    @Override
    public String getType() {
        return "Eau";
    }
}
