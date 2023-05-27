package Business;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

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

    public Player(ArrayList<Boat> boats, ArrayList<Integer> positionAttackedX, ArrayList<Integer> positionAttackedY, Tablero tablero,boolean alive) {
        this.boats = boats;
        this.barcoPosicionX = new ArrayList<>();;
        this.barcoPosicionY = new ArrayList<>();;
        this.positionAttackedX = positionAttackedX;
        this.positionAttackedY = positionAttackedY;
        this.tablero = tablero;
        this.alive = alive;
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
    //public abstract void realizarMovimiento(Player oponente);

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

    public ArrayList<Integer> getPositionAttackedX() {
        return positionAttackedX;
    }

    public void setPositionAttackedX(ArrayList<Integer> positionAttackedX) {
        this.positionAttackedX = positionAttackedX;
    }

    public ArrayList<Integer> getPositionAttackedY() {
        return positionAttackedY;
    }

    public void setPositionAttackedY(ArrayList<Integer> positionAttackedY) {
        this.positionAttackedY = positionAttackedY;
    }


    public Tablero getTablero() {
        return tablero;
    }

    public void setTablero(Tablero tablero) {
        this.tablero = tablero;
    }

    public boolean isAlive() {
        return alive;
    }

    public void setAlive(boolean alive) {
        this.alive = alive;
    }
}
