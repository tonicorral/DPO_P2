package Business;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public abstract class Player {

    protected Tablero tablero;
    protected boolean turno;
    private ArrayList<Boat> boats;

    private ArrayList<Integer> barcoPosicionX;
    private ArrayList<Integer> barcoPosicionY;

    private int[] positionAttackedX;
    private int[] positionAttackedY;

    //private boolean turno;

    private int turnToPlay;

    public Player(ArrayList<Boat> boats, int[] positionAttackedX, int[] positionAttackedY, Tablero tablero, int turnToPlay) {
        this.boats = boats;
        this.barcoPosicionX = new ArrayList<>();;
        this.barcoPosicionY = new ArrayList<>();;
        this.positionAttackedX = positionAttackedX;
        this.positionAttackedY = positionAttackedY;
        this.tablero = tablero;
        this.turnToPlay = turnToPlay;
    }
    public void addBarcoPosicion(int posicionX, int posicionY) {
        barcoPosicionX.add(posicionX);
        barcoPosicionY.add(posicionY);
    }

    public List<Integer> getBarcoPosicionX() {
        return barcoPosicionX;
    }

    public List<Integer> getBarcoPosicionY() {
        return barcoPosicionY;
    }
    public abstract void realizarMovimiento(Player oponente);

   /*public int[] getPositionX() {
        return positionX;
    }

    public int[] getPositionY() {
        return positionY;
    }*/

    public boolean isTurno() {
        return turno;
    }

    public int getTurnToPlay() {
        return turnToPlay;
    }
/*
    public void setPositionX(int[] positionX) {
        this.positionX = positionX;
    }

    public void setPositionY(int[] positionY) {
        this.positionY = positionY;
    }
*/
    public void setTurno(boolean turno) {
        this.turno = turno;
    }

    public void setTurnToPlay(int turnToPlay) {
        this.turnToPlay = turnToPlay;
    }

    public ArrayList<Boat> getBoats() {
        return boats;
    }

    public void setBoats(ArrayList<Boat> boats) {
        this.boats = boats;
    }

    public int[] getPositionAttackedX() {
        return positionAttackedX;
    }

    public void setPositionAttackedX(int[] positionAttackedX) {
        this.positionAttackedX = positionAttackedX;
    }

    public int[] getPositionAttackedY() {
        return positionAttackedY;
    }

    public void setPositionAttackedY(int[] positionAttackedY) {
        this.positionAttackedY = positionAttackedY;
    }
}
