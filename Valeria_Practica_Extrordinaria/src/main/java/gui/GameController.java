package gui;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import model.*;

public class GameController {
    private Stage stage;
    private Room currentRoom;
    private Player player;
    private GridPane gridPane;
    private Label statusLabel;
    private Label statsLabel; // HUD de información
    private boolean gameWon = false;

    public GameController(Stage stage) {
        this.stage = stage;
        player = new Player();

        // 1. CREAR RED DE 4 HABITACIONES (Alternando 5x5 y 6x6)
        Room room1 = new Room("Habitación 1 (5x5)", 5, 5);
        Room room2 = new Room("Habitación 2 (6x6)", 6, 6);
        Room room3 = new Room("Habitación 3 (5x5)", 5, 5);
        Room room4 = new Room("Habitación 4 (6x6)", 6, 6);

        // 2. POBLAR HABITACIONES
        // Room 1: 1 enemigo, 1 moneda
        room1.addEnemy(new Enemy(), 2, 2);
        room1.getCell(4, 0).setContent(new Money(false, null));

        // Room 2: 2 enemigos, 1 poción, 1 arma
        room2.addEnemy(new Enemy(), 3, 3);
        room2.addEnemy(new Enemy(), 4, 1);
        room2.getCell(0, 5).setContent(new Potion(5, false, false, null));
        room2.getCell(5, 0).setContent(new Weapon("hand", 2, false, false, null));

        // Room 3: MÁS ENEMIGOS Y OBJETOS (5x5)
        room3.addEnemy(new Enemy(), 2, 2);
        room3.addEnemy(new Enemy(), 4, 4);
        room3.getCell(1, 1).setContent(new Money(false, null)); // Moneda
        room3.getCell(2, 3).setContent(new Potion(3, false, false, null));

        // Room 4: JEFE FINAL, LLAVE Y MÁS MONEDAS (6x6)
        room4.addEnemy(new Enemy(false, 10, 5, 5), 3, 3); // Enemigo más fuerte
        room4.getCell(0, 0).setContent(new Money(false, null));
        room4.getCell(0, 5).setContent(new Money(false, null));

        // 3. CONEXIONES
        Door d1 = new Door(); d1.setTarget(room2, 0, 0); room1.getCell(4, 4).setContent(d1);
        Door d2 = new Door(); d2.setTarget(room1, 4, 3); room2.getCell(0, 1).setContent(d2);

        Door d3 = new Door(); d3.setLocked(true); d3.setTarget(room3, 1, 1); room2.getCell(5, 5).setContent(d3);
        room2.getCell(2, 2).setContent(new Key(d3, false, null)); // Llave en Room 2

        Door d4 = new Door(); d4.setTarget(room2, 5, 4); room3.getCell(0, 1).setContent(d4);
        Door d5 = new Door(); d5.setTarget(room4, 2, 2); room3.getCell(4, 0).setContent(d5);
        Door d6 = new Door(); d6.setTarget(room3, 3, 0); room4.getCell(1, 2).setContent(d6);

        // Salida
        Door exit = new Door(); exit.setTarget(null, 0, 0); room4.getCell(5, 5).setContent(exit);

        currentRoom = room1;
        currentRoom.placePlayer(player, 0, 0);

        gridPane = new GridPane();
        gridPane.setAlignment(Pos.CENTER);
        gridPane.setHgap(2);
        gridPane.setVgap(2);

        statusLabel = new Label("¡Bienvenido al calabozo!");
        statsLabel = new Label("Salud: 10 | Oro: 0 | Daño: 5");
        statsLabel.setStyle("-fx-font-size: 14px; -fx-text-fill: blue;");
    }

    // LÓGICA DE JUEGO
    private void handleInput(KeyEvent event) {
    }

    // RENDERIZADO VISUAL
    private void drawRoom() {
        statsLabel.setText("Salud: " + player.getCurrentHealth() + " | Oro: " + player.getMoney() + " | Daño: " + player.getAtk());
    }

    public void start() {
    }
}