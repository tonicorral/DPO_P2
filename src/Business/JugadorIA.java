package Business;

import java.util.ArrayList;
import java.util.Random;

public class JugadorIA extends Player {
    public JugadorIA(ArrayList<Boat> boats, ArrayList<Integer> positionAttackedX, ArrayList<Integer> positionAttackedY, Tablero tablero) {
        super(boats, positionAttackedX, positionAttackedY,tablero);
    }



    private int generarNumeroAleatorio(int max) {
        return (int) (Math.random() * max);
    }




}
