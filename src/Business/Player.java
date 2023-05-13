package Business;

import javax.swing.*;
import java.util.ArrayList;

public class Player {

    private ArrayList<Boat> boats;

    private int[] positionX;

    private int[] positionY;

    private int[] positionAttackedX;
    private int[] positionAttackedY;

    private boolean turno;

    private int turnToPlay;

    public Player(ArrayList<Boat> boats, int[] positionX, int[] positionY, int[] positionAttackedX, int[] positionAttackedY, boolean turno, int turnToPlay) {
        this.boats = boats;
        this.positionX = positionX;
        this.positionY = positionY;
        this.positionAttackedX = positionAttackedX;
        this.positionAttackedY = positionAttackedY;
        this.turno = turno;
        this.turnToPlay = turnToPlay;
    }

    public int[] getPositionX() {
        return positionX;
    }

    public int[] getPositionY() {
        return positionY;
    }

    public boolean isTurno() {
        return turno;
    }

    public int getTurnToPlay() {
        return turnToPlay;
    }

    public void setPositionX(int[] positionX) {
        this.positionX = positionX;
    }

    public void setPositionY(int[] positionY) {
        this.positionY = positionY;
    }

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
