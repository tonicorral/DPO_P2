package Business;

import java.util.ArrayList;

public class Tablero {
    private int[][] tablero;

    public static final int BARCO = 1;
    public static final int AGUA = 0;

    public static final int IA1 = 2;
    public static final int IA2 = 3;
    public static final int IA3 = 4;
    public static final int IA4 = 5;
    public static final int IA1_TOCADO = -2;
    public static final int IA2_TOCADO = -3;
    public static final int IA3_TOCADO = -4;
    public static final int IA4_TOCADO = -5;
    public static final int IA1_HUNDIDO = -7;
    public static final int IA2_HUNDIDO = -8;
    public static final int IA3_HUNDIDO = -9;
    public static final int IA4_HUNDIDO = -10;

    public static final int MUERTO = -100;
    public static final int USER = 6;
    public static final int USER_TOCADO = -6;
    public static final int USER_HUNDIDO = -11;
    //public static final int TOCADO = -1;
    //public static final int HUNDIDO = -2;
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
