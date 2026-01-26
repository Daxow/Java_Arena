package monsters;

public abstract class Monster {

    private String name;
    private int hp;
    private int hpMax;
    private int atk;

    public Monster(String name, int hpMax, int atk) {
        this.name = name;
        this.hpMax = hpMax;
        this.hp = hpMax;
        this.atk = atk;
    }

    public String getName() {
        return name;
    }

    public int getHp() {
        return hp;
    }

    public int getHpMax() {
        return hpMax;
    }

    public int getAtk() {
        return atk;
    }

    public boolean isKO() {
        return hp <= 0;
    }

    public void receiveDamage(int damage) {
        hp -= damage;
        if (hp < 0) hp = 0;
    }

    public void heal(int points) {
        hp += points;
        if (hp > hpMax) hp = hpMax;
    }

    public abstract int attack(Monster target);
    
    public abstract boolean isWeakAgainst(Monster attacker);

    @Override
    public String toString() {
        return name + " (" + getType() + ") - PV: " + hp + "/" + hpMax + " - ATK: " + atk;
    }

    public abstract String getType();
}