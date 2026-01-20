package monsters;

public class WaterMonster extends Monster {

    public WaterMonster(String name) {
        super(name, 110, 18);
    }

    @Override
    public int attack(Monster target) {
        int damage = getAtk();

        if (target instanceof FireMonster) {
            damage *= 2;
        }

        target.receiveDamage(damage);
        return damage;
    }

    @Override
    public String getType() {
        return "Eau";
    }
}
