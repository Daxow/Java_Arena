package player;

import monsters.Monster;
import exceptions.IllegalActionException;

import java.util.ArrayList;

public class Player {

    private String teamName;
    private ArrayList<Monster> team;
    private Inventory inventory;
    private int money;

    public Player(String teamName) {
        this.teamName = teamName;
        this.team = new ArrayList<>();
        this.inventory = new Inventory();
        this.money = 0;
    }

    public String getTeamName() {
        return teamName;
    }

    public ArrayList<Monster> getTeam() {
        return team;
    }

    public Inventory getInventory() {
        return inventory;
    }

    public int getMoney() {
        return money;
    }

    public void addMoney(int amount) {
        if (amount <= 0) return;
        money += amount;
    }

    public boolean withdrawMoney(int amount) {
        if (amount <= 0) return true;
        if (money < amount) return false;
        money -= amount;
        return true;
    }

    public void addMonster(Monster monster) {
        if (monster == null) return;
        team.add(monster);
    }

    public void displayTeam() {
        System.out.println("Equipe : " + teamName);
        if (team.isEmpty()) {
            System.out.println("Aucun monstre.");
            return;
        }
        for (int i = 0; i < team.size(); i++) {
            System.out.println((i + 1) + ". " + team.get(i));
        }
    }

    public Monster getMonster(int index) {
        int i = index - 1;
        if (i < 0 || i >= team.size()) return null;
        return team.get(i);
    }

    public void usePotion(Monster monster) throws IllegalActionException {
        if (!inventory.consumeItem("Potion de soin")) {
            throw new IllegalActionException("Aucune potion disponible.");
        }
    monster.heal(30);
    }

    public void useRevive(Monster monster) throws IllegalActionException {
        if (!inventory.consumeItem("Rappel")) {
            throw new IllegalActionException("Aucun rappel disponible.");
        }
    if (!monster.isKO()) {
        throw new IllegalActionException("Le monstre n'est pas KO.");
        }
    monster.heal(monster.getHpMax() / 2);
    }

    public boolean tryCapture(Monster monster) throws IllegalActionException {
        if (!inventory.consumeItem("PokéBall")) {
            throw new IllegalActionException("Aucune PokéBall disponible.");
        }

    if (monster.getHp() > monster.getHpMax() * 30 / 100) {
        throw new IllegalActionException("Le monstre a trop de PV pour être capturé.");
    }

    team.add(monster);
    return true;
    }
}
