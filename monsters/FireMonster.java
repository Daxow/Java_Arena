package monsters;

public class FireMonster extends Monster {

    public FireMonster(String name) {
        super(name, 100, 20);
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
        return attacker instanceof WaterMonster;
    }

    @Override
    public String getType() {
        return "Feu";
    }
}
