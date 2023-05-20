package Business;

import java.util.ArrayList;

public class Game {


    private Player player;

    private ArrayList<JugadorIA> jugadorIA;

    private int playerTurn;

    private int numberPlayers;

    private boolean finishedGame;

    public Game(Player player,ArrayList<JugadorIA> jugadorIA, int numberPlayers, boolean finishedGame) {
        this.player = player;
        this.jugadorIA = jugadorIA;
        this.numberPlayers = numberPlayers;
        this.finishedGame = finishedGame;
    }


    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public ArrayList<JugadorIA> getJugadorIA() {
        return jugadorIA;
    }

    public void setJugadorIA(ArrayList<JugadorIA> jugadorIA) {
        this.jugadorIA = jugadorIA;
    }

    public int getPlayerTurn() {
        return playerTurn;
    }

    public void setPlayerTurn(int playerTurn) {
        this.playerTurn = playerTurn;
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
}
