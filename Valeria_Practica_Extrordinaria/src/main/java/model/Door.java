package model;

public class Door implements Comparable<Door> {
    private Boolean locked;
    private Key key;
    private Cell position;

    // Atributos que forman las aristas del Grafo de habitaciones
    private Room targetRoom;
    private int targetX;
    private int targetY;

    public Door() {
        this.locked = false;
        if (this.locked) {
            key = new Key();
        } else {
            key = null;
        }
        this.position = new Cell(1,1);
        this.targetRoom = null;
        this.targetX = 0;
        this.targetY = 0;
    }

    public Door(Key key, Boolean locked, Cell position) {
        this.key = key;
        this.locked = locked;
        this.position = position;
        this.targetRoom = null;
    }

    // Configura el destino de la puerta
    public void setTarget(Room room, int x, int y) {
        this.targetRoom = room;
        this.targetX = x;
        this.targetY = y;
    }

    // Getters de destino
    public Room getTargetRoom() { return targetRoom; }
    public int getTargetX() { return targetX; }
    public int getTargetY() { return targetY; }

    // Resto de Getters y Setters
    public Boolean getLocked() { return locked; }
    public void setLocked(Boolean locked) { this.locked = locked; }
    public Key getKey() { return key; }
    public void setKey(Key key) { this.key = key; }
    public Cell getPosition() { return position; }
    public void setPosition(Cell position) { this.position = position; }

    @Override
    public int compareTo(Door o) {
        if (this == o) return 0;
        if (o == null) return 1;
        if (this.position != null && o.position != null) {
            if (this.position.getX() != o.position.getX()) {
                return Integer.compare(this.position.getX(), o.position.getX());
            }
            return Integer.compare(this.position.getY(), o.position.getY());
        }
        return Integer.compare(this.hashCode(), o.hashCode());
    }
}