package player;

import java.util.HashMap;
import java.util.Map;

public class Inventory {

    private HashMap<String, Integer> items;

    public Inventory() {
        this.items = new HashMap<>();
    }

    public int getQuantity(String itemName) {
        return items.getOrDefault(itemName, 0);
    }

    public void addItem(String itemName, int quantity) {
        if (quantity == 0) return;
        items.put(itemName, getQuantity(itemName) + quantity);
    }

    public boolean consumeItem(String itemName) {
        int quantity = getQuantity(itemName);
        if (quantity <= 0) return false;
        items.put(itemName, quantity - 1);
        return true;
    }

    public Map<String, Integer> getItems() {
        return items;
    }

    public void display() {
        if (items.isEmpty()) {
            System.out.println("\nInventaire vide.");
            return;
        }
        System.out.println("\nInventaire :");
        for (Map.Entry<String, Integer> entry : items.entrySet()) {
            System.out.println("- " + entry.getKey() + " x" + entry.getValue());
        }
    }
}
