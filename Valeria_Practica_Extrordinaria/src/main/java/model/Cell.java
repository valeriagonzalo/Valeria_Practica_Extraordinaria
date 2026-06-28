package model;

public class Cell {
    private int x;
    private int y;
    private boolean isObstacle;
    private Object content; // Puede ser Enemy, Potion, Weapon, Door, etc.
    private Player player;  // Referencia al jugador si está en esta celda

    public Cell(int x, int y) {
        this.x = x;
        this.y = y;
        this.isObstacle = false;
        this.content = null;
        this.player = null;
    }

    public boolean isEmpty() {
        return !isObstacle && content == null && player == null;
    }

    // Getters y Setters
    public int getX() { return x; }
    public int getY() { return y; }

    public boolean isObstacle() { return isObstacle; }
    public void setObstacle(boolean obstacle) { isObstacle = obstacle; }

    public Object getContent() { return content; }
    public void setContent(Object content) { this.content = content; }

    public Player getPlayer() { return player; }
    public void setPlayer(Player player) { this.player = player; }
}