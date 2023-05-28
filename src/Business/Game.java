package Business;

import java.util.ArrayList;

/**
 * Engloba el juego y sus caracterisiticas
 */
public class Game {

    private Player player;

    private ArrayList<JugadorIA> jugadorIA;

    private int playerTurn;

    private int numberPlayers;

    private boolean finishedGame;


    /**
     * Contructor del juego
     * @param player el propio jugador de la partida
     * @param jugadorIA las IAs que participan
     * @param numberPlayers el numero de IAs generadas
     * @param finishedGame si el juego a terminado
     */
    public Game(Player player,ArrayList<JugadorIA> jugadorIA, int numberPlayers, boolean finishedGame) {
        this.player = player;
        this.jugadorIA = jugadorIA;
        this.numberPlayers = numberPlayers;
        this.finishedGame = finishedGame;
    }



    /**
     * Getter del jugador
     * @return el jugador
     */
    public Player getPlayer() {
        return player;
    }


    /**
     * Getter de los jugadores IA
     * @return los jugadores IA
     */
    public ArrayList<JugadorIA> getJugadorIA() {
        return jugadorIA;
    }


    /**
     * Getter del numero de jugadores IA
     * @return numero jugadores IA
     */
    public int getNumberPlayers() {
        return numberPlayers;
    }
}
