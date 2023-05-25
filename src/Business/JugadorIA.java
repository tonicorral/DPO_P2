package Business;

import Business.Boat;
import Business.Player;
import Business.Tablero;

import java.util.ArrayList;

public class JugadorIA extends Player implements Runnable {

    public JugadorIA(ArrayList<Boat> boats, ArrayList<Integer> positionAttackedX, ArrayList<Integer> positionAttackedY, Tablero tablero) {
        super(boats, positionAttackedX, positionAttackedY,tablero);
    }


    @Override
    public void run() {

    }
}