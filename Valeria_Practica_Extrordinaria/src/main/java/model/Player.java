package model;

import Estructuras.MyLinkedList.ListaSE;

import java.util.Random;

public class Player {
    private Boolean death;
    private int currentHealth;
    private int maxHealth;
    private int def;
    private int atk;
    private int money;
    private int mov;
    private int x;
    private int y;

    private ListaSE<Item> inventory;

    public Player() {
        death = false;
        maxHealth = 10;
        currentHealth = maxHealth;
        def = 3;
        atk = 5;
        money = 0;
        mov = 1;
        inventory = new ListaSE<>();
    }

    public Player(Boolean death, int maxHealth, int def, int atk, int money, int mov, ListaSE<Item> inventory) {
        this.death = death;
        this.maxHealth = maxHealth;
        this.currentHealth = maxHealth;
        this.def = def;
        this.atk = atk;
        this.money = money;
        this.mov = mov;
        this.inventory = inventory;
    }


    public ListaSE<Item> getInventory() { return inventory; }

    // Getters y Setters
    public int getCurrentHealth() { return currentHealth; }
    public int getMaxHealth() { return maxHealth; }
    public int getDef() { return def; }
    public int getAtk() { return atk; }
    public int getMoney() { return money; }
    public int getMov() { return mov; }
    public int getX() { return x; }
    public int getY() { return y; }
    public void setX(int x) { this.x = x; }
    public void setY(int y) { this.y = y; }

    public void takeDamage(int damage) { currentHealth -= damage; }

    public void heal(int health) {
        if(currentHealth + health > maxHealth) {
            currentHealth = maxHealth;
        } else {
            currentHealth += health;
        }
    }

    public void gainDef(int add) { def += add; }
    public void loseDef(int lost) { def = Math.max(0, def - lost); }
    public void gainAtk(int add) { atk += add; }
    public void loseAtk(int lose) { atk = Math.max(0, atk - lose); }
    public void gainMov(int add) { mov += add; }
    public void loseMov(int lose) { mov = Math.max(1, mov - lose); }
    public void gainMoney(int add) { money += add; }

    public boolean spendMoney(int lose) {
        if (money < lose) return false;
        money -= lose;
        return true;
    }

    // Gestión de Objetos
    public void addToInventory(Item item) {
        inventory.add(item);
    }

    public void equipWeapon(Weapon weapon) {
        if(weapon.getIsInInventory() || !weapon.getEquipped()) {
            gainAtk(weapon.getAtkBuff());
            weapon.setEquipped(true);
        }
    }

    public void usePotion(Potion potion) {
        if(potion.getIsInInventory() || !potion.getUsed()) {
            heal(potion.getHeal());
            potion.setUsed(true);
        }
    }

    public Boolean useKey(Key key, Door door) {
        if(door.getLocked()) {
            if(key.getIsInInventory() && key.getDoor() == door) {
                key.setIsInInventory(false);
                return true;
            }
            return false;
        } else {
            door.setLocked(false);
            return true;
        }
    }

    public void receiveAtk(int dealtAtk) {
        Random rand = new Random();
        int randomNum = rand.nextInt(2);
        currentHealth = currentHealth - Math.max(0, dealtAtk * (randomNum * 2) - def);
    }

    public void isDead() {
        if(currentHealth <= 0) death = true;
    }
}