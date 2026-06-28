package model;

public class Money implements Item {
    private Boolean isInInventory;
    private Cell position;

    public Money() {
        this.isInInventory = false;
        this.position = new Cell(1,1);
    }

    public Money(Boolean isInInventory, Cell position) {
        this.isInInventory = isInInventory;
        this.position = position;
    }

    public void addToInventory(Player player) {
        if (!isInInventory) {
            player.addToInventory(this);
            isInInventory = true;
        }
    }

    public Boolean getIsInInventory() { return isInInventory; }
    public void setIsInInventory(Boolean isInInventory) { this.isInInventory = isInInventory; }

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

