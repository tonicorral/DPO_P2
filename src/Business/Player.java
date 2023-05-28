package Business;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;



/**
 * Engloba tot el jugador y sus caracteristicas
 */
public class Player {

    protected Tablero tablero;
    protected boolean turno;
    private ArrayList<Boat> boats;

    private ArrayList<Integer> barcoPosicionX;
    private ArrayList<Integer> barcoPosicionY;

    private ArrayList<Integer> positionAttackedX;
    private ArrayList<Integer> positionAttackedY;

    //private boolean turno;

    private int turnToPlay;

    private boolean alive;

    /**
     * Constructor de la clase Player.
     * Crea un objeto Player con los barcos, posiciones atacadas, tablero y estado de vida especificados.
     *
     * @param boats             La lista de barcos del jugador.
     * @param positionAttackedX La lista de posiciones X atacadas por el jugador.
     * @param positionAttackedY La lista de posiciones Y atacadas por el jugador.
     * @param tablero           El tablero del jugador.
     * @param alive             El estado de vida del jugador.
     **/
    public Player(ArrayList<Boat> boats, ArrayList<Integer> positionAttackedX, ArrayList<Integer> positionAttackedY, Tablero tablero,boolean alive) {
        this.boats = boats;
        this.barcoPosicionX = new ArrayList<>();;
        this.barcoPosicionY = new ArrayList<>();;
        this.positionAttackedX = positionAttackedX;
        this.positionAttackedY = positionAttackedY;
        this.tablero = tablero;
        this.alive = alive;
    }

    /**
     * Obtiene la lista de barcos del jugador.
     *
     * @return La lista de barcos del jugador.
     **/
    public ArrayList<Boat> getBoats() {
        return boats;
    }

    /**
     * Obtiene la lista de posiciones X atacadas por el jugador.
     *
     * @return La lista de posiciones X atacadas por el jugador.
     **/
    public ArrayList<Integer> getPositionAttackedX() {
        return positionAttackedX;
    }

    /**
     * Obtiene la lista de posiciones Y atacadas por el jugador.
     *
     * @return La lista de posiciones Y atacadas por el jugador.
     **/
    public ArrayList<Integer> getPositionAttackedY() {
        return positionAttackedY;
    }



    /**
     * Obtiene el tablero del jugador.
     *
     * @return El tablero del jugador.
     **/
    public Tablero getTablero() {
        return tablero;
    }

    /**
     * Verifica si el jugador está vivo.
     *
     * @return true si el jugador está vivo, false en caso contrario.
     **/
    public boolean isAlive() {
        return alive;
    }

    /**
     * Establece el estado de vida del jugador.
     *
     * @param alive true si el jugador está vivo, false en caso contrario.
     **/
    public void setAlive(boolean alive) {
        this.alive = alive;
    }
}
