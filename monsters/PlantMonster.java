package monsters;

public class PlantMonster extends Monster {

    public PlantMonster(String name) {
        super(name, 120, 15);
    }

    @Override
    public int attack(Monster target) {
        int damage = getAtk();

        if (target instanceof WaterMonster) {
            damage *= 2;
        }

        target.receiveDamage(damage);
        return damage;
    }

    @Override
    public String getType() {
        return "Plante";
    }
}
