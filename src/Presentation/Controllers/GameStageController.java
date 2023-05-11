package Presentation.Controllers;

import Business.UserModel;

public class GameStageController {
    private UserModel userModel;
    private List<Player> players;
    private Player currentUser;
    private boolean gameEnded;

    public GameStageLogic(UserModel userModel) {
        this.userModel = userModel;
        this.players = new ArrayList<>();
        this.currentUser = null;
        this.gameEnded = false;
    }
//clases player game etc atodo aqui solo controlar lo que le pasa el usuario por la view
//
/*
    public void initializeGame() {
        // Obtener los jugadores del modelo de usuario
        List<String> playerNames = userModel.getPlayerNames();

        // Crear los jugadores y agregarlos a la lista
        for (String playerName : playerNames) {
            Player player = new Player(playerName);
            players.add(player);
        }

        // Establecer el jugador actual como el usuario actual
        String currentUserName = userModel.getUserName();
        currentUser = getPlayerByUsername(currentUserName);
    }
*/
    public void performAttack(String targetPlayerName, int row, int column) {
        // Verificar si el juego ha finalizado
        if (gameEnded) {
            return;
        }

        // Verificar si el jugador actual está en período de recarga
        if (currentUser.isRecharging()) {
            return;
        }

        // Obtener el jugador objetivo
        Player targetPlayer = getPlayerByUsername(targetPlayerName);
        if (targetPlayer == null) {
            return;
        }

        // Realizar el ataque y obtener el resultado
        AttackResult result = targetPlayer.receiveAttack(row, column);

        // Actualizar el estado del juego según el resultado del ataque
        updateGameStatus(result);

        // Actualizar los datos del juego en el modelo de usuario
        userModel.updateGameStatus(players);
    }

    public void receiveAttack(String attackerName, int row, int column) {
        // Verificar si el juego ha finalizado
        if (gameEnded) {
            return;
        }

        // Obtener el jugador atacante
        Player attacker = getPlayerByUsername(attackerName);
        if (attacker == null) {
            return;
        }

        // Realizar el ataque y obtener el resultado
        AttackResult result = currentUser.receiveAttack(row, column);

        // Actualizar el estado del juego según el resultado del ataque
        updateGameStatus(result);

        // Actualizar los datos del juego en el modelo de usuario
        userModel.updateGameStatus(players);
    }

    private Player getPlayerByUsername(String username) {
        for (Player player : players) {
            if (player.getUsername().equals(username)) {
                return player;
            }
        }
        return null;
    }

    private void updateGameStatus(AttackResult result) {
        // Actualizar el estado del juego según el resultado del ataque
        switch (result) {
            case HIT:
                // Acción cuando hay un acierto en el ataque
                break;
            case MISS:
                // Acción cuando hay un fallo en el ataque
                break;
            case SUNK:
                // Acción cuando se hunde un barco
                break;
            case GAME_OVER:
                // Acción cuando el juego ha terminado
                gameEnded = true;
                break;
        }
    }
}
