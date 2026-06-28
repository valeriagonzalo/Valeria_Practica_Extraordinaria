package model;

public interface Item extends Comparable<Item> {
    // Interfaz que agrupa todos los objetos que pueden ir al inventario.
    // Al extender Comparable<Item>, nos aseguramos de que ListaSE funcione
    // cumpliendo la condición de usar estructuras de datos propias.
}