package Business;

import java.util.ArrayList;

public class Tablero {
    private int[][] tablero;

    public static final int BARCO = 1;
    public static final int AGUA = 0;
    public static final int TOCADO = -1;
    public static final int HUNDIDO = -2;
    private ArrayList<Boat> boats;

    public Tablero(ArrayList<Boat> boats) {
        this.boats = boats;
        tablero = new int[15][15];
        inicializarTablero();
    }

    private void inicializarTablero() {
        for (int i = 0; i < 15; i++) {
            for (int j = 0; j < 15; j++) {
                tablero[i][j] = AGUA;
                for(int m = 0;m<5;m++){
                    int size = boats.get(m).getSize();
                    boolean orientation = boats.get(m).getOrientation();
                    int posX = boats.get(m).getPositionX();
                    int posY = boats.get(m).getPositionY();
                    if(orientation){
                        for(int k = 0;k<size;k++){
                            tablero[posX-1][posY+k-1] = BARCO;
                        }
                    } else{
                        for(int k = 0;k<size;k++){
                            tablero[posX+k-1][posY-1] = BARCO;
                        }
                    }

                }
            }
        }
    }

    public ArrayList<Boat> getBoats() {
        return boats;
    }

    public void setBoats(ArrayList<Boat> boats) {
        this.boats = boats;
    }

    public int[][] getTablero() {
        return tablero;
    }

    public void setPosicion(int x, int y, int valor) {
        tablero[x][y] = valor;
    }

    public int getPosicion(int x, int y) {
        return tablero[x][y];
    }
}
