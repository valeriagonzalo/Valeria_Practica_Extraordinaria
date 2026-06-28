package model;

import java.util.Random;

public class Enemy implements Comparable<Enemy> {
    private Boolean dead;
    private int currentHealth; //dos tipos de salud porque esta la puede restaurar pero sin pasarse del máximo
    private int maxHealth;
    private int def;
    private int atk;
    private int mov;
    private int x;
    private int y;

    public Enemy() //Generador para cuando se crea uno estándar
    {
        dead = false;
        maxHealth = 5;
        currentHealth = maxHealth;
        def = 2;
        atk = 3;
    }

    public Enemy(Boolean dead, int maxHealth, int def, int atk) //para cuando nos den los datos
    {
        this.dead = dead;
        this.maxHealth = maxHealth;
        currentHealth = maxHealth;
        this.def = def;
        this.atk = atk;
    }

    public int getCurrentHealth()
    {
        return currentHealth;

    }

    public int  getMaxHealth()
    {
        return maxHealth;
    }

    public int getDef()
    {
        return def;
    }

    public int getAtk()
    {
        return atk;
    }

    public int getMov()
    {
        return mov;
    }

    public int getX() { return x; }
    public void setX(int x) { this.x = x; }

    public int getY() { return y; }
    public void setY(int y) { this.y = y; }

    public void receiveAtk(int dealtAtk){
        Random rand = new Random();

        //nextInt de Random es exclusivo del valor máximo entonces hay que añadir un 1
        int randomNum = rand.nextInt((1 - 0) + 1) + 0;
        currentHealth = currentHealth -Math.max(0,dealtAtk*(randomNum*2)-def);
    }

    public void isDead() {
        if(currentHealth <= 0) {
            dead = true;
        }
    }

    @Override
    public int compareTo(Enemy o) {
        if (this == o) return 0;
        // Distinguimos a los enemigos por sus coordenadas en la matriz
        if (this.x != o.x) return Integer.compare(this.x, o.x);
        return Integer.compare(this.y, o.y);
    }
}