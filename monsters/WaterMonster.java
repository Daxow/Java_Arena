package monsters;

public class WaterMonster extends Monster {

    public WaterMonster(String name) {
        super(name, 110, 18);
    }

    @Override
    public int attack(Monster target) {
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
