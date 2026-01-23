package player;

import monsters.Monster;

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
        System.out.println("Equipe :" + teamName);
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
}
