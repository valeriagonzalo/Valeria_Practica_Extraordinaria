package model;

public class Shield implements Item {
    private String placement; //Dónde se lleva en el cuerpo?
    private int defBuff; //Cuánto aumenta el daño?
    private Boolean equipped; //si el personaje lo tiene equipado
    private Boolean isInInventory;
    private Cell position;

    public Shield() {
        this.placement = "hand";
        this.defBuff = 1;
        this.equipped = false;

        this.isInInventory = false;

        if (!this.isInInventory) {
            this.position = new Cell(1,1);
        } else {
            this.position = null;
        }
    }

    public Shield(String placement, int defBuff, Boolean equipped, Boolean isInInventory, Cell position) {
        this.placement = placement;
        this.defBuff = defBuff;
        this.equipped = equipped;
        this.isInInventory = isInInventory;
        this.position = position;
    }

    public void addToInventory(Player player) {
        if (!this.isInInventory) {
            player.addToInventory(this);
            this.isInInventory = true;
        }
    }

    public String getPlacement() { return placement; }
    public int getDefBuff() { return defBuff; }
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