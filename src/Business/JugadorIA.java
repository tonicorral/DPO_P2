package Business;

import Business.Boat;
import Business.Player;
import Business.Tablero;

import java.util.ArrayList;

public class JugadorIA extends Player {

    public JugadorIA(ArrayList<Boat> boats, ArrayList<Integer> positionAttackedX, ArrayList<Integer> positionAttackedY, Tablero tablero,boolean alive) {
        super(boats, positionAttackedX, positionAttackedY,tablero,alive);
    }

}