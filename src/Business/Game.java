package Business;

import java.util.ArrayList;

public class Game {


    private ArrayList<Player> player;

    private int playerTurn;

    private int numberPlayers;

    private boolean finishedGame;

    public Game(ArrayList<Player> player, int numberPlayers, boolean finishedGame) {
        this.player = player;
        this.numberPlayers = numberPlayers;
        this.finishedGame = finishedGame;
    }


    public ArrayList<Player> getPlayer() {
        return player;
    }

    public void setPlayer(ArrayList<Player> player) {
        this.player = player;
    }

    public int getNumberPlayers() {
        return numberPlayers;
    }

    public void setNumberPlayers(int numberPlayers) {
        this.numberPlayers = numberPlayers;
    }

    public boolean isFinishedGame() {
        return finishedGame;
    }

    public void setFinishedGame(boolean finishedGame) {
        this.finishedGame = finishedGame;
    }

    public int getPlayerTurn() {
        return playerTurn;
    }

    public void setPlayerTurn(int playerTurn) {
        this.playerTurn = playerTurn;
    }
}
