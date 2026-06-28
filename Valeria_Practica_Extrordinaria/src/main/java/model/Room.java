package model;

import Estructuras.MyLinkedList.ListaSE;
import Estructuras.MyMatrix.MyMatrix;

public class Room {
    private String id;
    private MyMatrix<Cell> grid;
    private ListaSE<Enemy> enemies; // Estructura propia obligatoria
    private ListaSE<Door> doors;

    public Room(String id, int rows, int cols) {
        this.id = id;
        this.grid = new MyMatrix<>(rows, cols);
        this.enemies = new ListaSE<>();
        this.doors = new ListaSE<>();
        initGrid();
    }

    private void initGrid() {
        for (int i = 0; i < grid.getRows(); i++) {
            for (int j = 0; j < grid.getCols(); j++) {
                grid.set(i, j, new Cell(i, j));
            }
        }
    }

    public Cell getCell(int r, int c) {
        return grid.get(r, c);
    }

    public void addEnemy(Enemy enemy, int r, int c) {
        Cell cell = grid.get(r, c);
        if (cell.isEmpty()) {
            cell.setContent(enemy);
            enemy.setX(r);
            enemy.setY(c);
            enemies.add(enemy);
        } else {
            throw new IllegalStateException("No se puede colocar un enemigo: la celda está ocupada.");
        }
    }

    public void placePlayer(Player player, int r, int c) {
        Cell cell = grid.get(r, c);
        if (!cell.isObstacle()) {
            cell.setPlayer(player);
            player.setX(r);
            player.setY(c);
        } else {
            throw new IllegalStateException("No se puede colocar al jugador en un obstáculo.");
        }
    }

    public void movePlayer(Player player, int newR, int newC) {
        // Validación de coordenadas controlada por MyMatrix
        Cell currentCell = grid.get(player.getX(), player.getY());
        Cell newCell = grid.get(newR, newC);

        // Verifica invariante: Una celda no contiene múltiples entidades (excepto objetos pisables/puertas)
        if (newCell.isEmpty() || newCell.getContent() instanceof Door) {
            currentCell.setPlayer(null); // Liberar celda actual
            newCell.setPlayer(player);   // Ocupar nueva celda
            player.setX(newR);
            player.setY(newC);
        } else {
            throw new IllegalStateException("Movimiento inválido: la casilla destino está ocupada.");
        }
    }

    public String getId() { return id; }
    public int getRows() { return grid.getRows(); }
    public int getCols() { return grid.getCols(); }
    public ListaSE<Enemy> getEnemies() { return enemies; }
}
