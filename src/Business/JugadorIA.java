package Business;

import java.util.ArrayList;
import java.util.Random;

public class JugadorIA extends Player {
    public JugadorIA(ArrayList<Boat> boats, int[] positionAttackedX, int[] positionAttackedY, Tablero tablero) {
        super(boats, positionAttackedX, positionAttackedY,tablero);
    }



    private int generarNumeroAleatorio(int max) {
        return (int) (Math.random() * max);
    }

    /*public void realizarMovimiento(Player oponente) {
        Tablero tableroOponente = oponente.getTablero().getTablero();
        int fila, columna;
        boolean ataqueExitoso = false;
        boolean intentoHundir = false;

        // Atacar casillas aleatoriamente hasta conseguir un impacto
        while (!ataqueExitoso) {
            if (!intentoHundir) {
                fila = generarNumeroAleatorio(15);
                columna = generarNumeroAleatorio(15);
            } else {
                // Intentar hundir el barco de manera inteligente
                // Implementa aquí la lógica para atacar de manera inteligente
                // Puedes usar la información del último ataque exitoso para orientar tus ataques
                // y buscar las casillas adyacentes al último ataque para intentar hundir el barco
                // de forma estratégica
                // Por ejemplo, podrías intentar atacar las casillas adyacentes en dirección vertical u horizontal

                // En este ejemplo, se realiza un ataque aleatorio si no se ha encontrado una estrategia
                fila = generarNumeroAleatorio(15);
                columna = generarNumeroAleatorio(15);
            }

            if (tableroOponente[fila][columna] == Tablero.AGUA || tableroOponente[fila][columna] == Tablero.BARCO) {
                if (tableroOponente[fila][columna] == Tablero.BARCO) {
                    tableroOponente[fila][columna] = Tablero.TOCADO;
                    ataqueExitoso = true;
                    intentoHundir = true; // Indicar que se ha logrado un impacto y se intentará hundir el barco
                } else {
                    tableroOponente[fila][columna] = Tablero.AGUA;
                }
            }
        }

        // Actualizar el estado del tablero del oponente
        oponente.getTablero().setTablero(tableroOponente);
    }*/


}
