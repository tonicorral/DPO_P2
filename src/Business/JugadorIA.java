package Business;

import Business.Boat;
import Business.Player;
import Business.Tablero;

import java.util.ArrayList;


/**
 * Extiende la clase jugador para el control de la IA
 */
public class JugadorIA extends Player {

    /**
     * Constructor de la clase JugadorIA.
     * Crea un objeto JugadorIA con los barcos, posiciones atacadas, tablero y estado de vida especificados.
     * @param boats La lista de barcos del jugador IA.
     * @param positionAttackedX La lista de posiciones X atacadas por el jugador IA.
     * @param positionAttackedY La lista de posiciones Y atacadas por el jugador IA.
     * @param tablero El tablero del jugador IA.
     * @param alive El estado de vida del jugador IA.
     **/
    public JugadorIA(ArrayList<Boat> boats, ArrayList<Integer> positionAttackedX, ArrayList<Integer> positionAttackedY, Tablero tablero,boolean alive) {
        super(boats, positionAttackedX, positionAttackedY,tablero,alive);
    }

}