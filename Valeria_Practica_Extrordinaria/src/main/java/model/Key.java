package model;

public class Key implements Item {
    private Door door;
    private Boolean isInInventory;
    private Cell position;

    public Key() {
        this.door = new Door();

        this.isInInventory = false;

        if (!this.isInInventory) {
            this.position = new Cell(1,1);
        } else {
            this.position = null;
        }
    }

    public Key(Door door, Boolean isInInventory, Cell position) {
        this.door = door;
        this.isInInventory = isInInventory;
        this.position = position;
    }

    public void addToInventory(Player player) {
        if (!this.isInInventory) {
            player.addToInventory(this);
            this.isInInventory = true;
        }
    }

    public Door getDoor() { return door; }
    public Boolean getIsInInventory() { return isInInventory; }
    public void setIsInInventory(Boolean isInInventory) { this.isInInventory = isInInventory; }
    public Cell getPosition() { return position; }

    @Override
    public int compareTo(Item o) {
        if (this == o) return 0;
        if (o == null) return 1;

        // Primero diferencia por tipo de clase (Ej. Potion frente a Weapon)
        int classComp = this.getClass().getSimpleName().compareTo(o.getClass().getSimpleName());
        if (classComp != 0) return classComp;

        // Si son de la misma clase (Ej. dos pociones distintas), los diferencia por su identificador de memoria
        return Integer.compare(this.hashCode(), o.hashCode());
    }
}
