package model;

public class Weapon implements Item {
    private String placement;
    private int atkBuff;
    private Boolean equipped;
    private Boolean isInInventory;
    private Cell position;

    public Weapon() {
        this.placement = "hand";
        this.atkBuff = 1;
        this.equipped = false;
        this.isInInventory = false;

        if (!this.isInInventory) {
            this.position = new Cell(1,1);
        } else {
            this.position = null;
        }
    }

    public Weapon(String placement, int atkBuff, Boolean equipped, Boolean isInInventory, Cell position) {
        this.placement = placement;
        this.atkBuff = atkBuff;
        this.equipped = equipped;
        this.isInInventory = isInInventory;
        this.position = position;
    }

    public void addToInventory(Player player) {
        if (!isInInventory) {
            player.addToInventory(this);
            isInInventory = true;
        }
    }

    public String getPlacement() { return placement; }
    public int getAtkBuff() { return atkBuff; }
    public Boolean getEquipped() { return equipped; }
    public void setEquipped(Boolean equipped) { this.equipped = equipped; }
    public Boolean getIsInInventory() { return isInInventory; }
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