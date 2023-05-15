package Presentation.Controllers;

import Presentation.MainController;
import Presentation.MainView;
import Presentation.Views.SetupStageGUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.SQLOutput;

public class SetUpController implements ActionListener, MouseListener {

    private SetupStageGUI setUpGUI;

    private boolean mouseClicked = false;

    private int positionBoat;

    private String positionBoatTable;

    private boolean rotation = false;
    private boolean isClickedPorta = false,isClickedDestructor=false,isClickedSubmari=false,isClickedSubmari2=false,isClickedLlanxa=false;

    // MainController mainController;
    private MainView mainView;
    private MainController mainController;

    public SetUpController(SetupStageGUI setUpGUI, MainView mainView,MainController mainController) {
        this.setUpGUI = setUpGUI;
        this.mainView = mainView;

        setUpGUI.setUpButtonController(this,this);

        this.mainController = mainController;
        //mainView.setListeners(this);
    }

    public void actionPerformed(ActionEvent e) {
        System.out.println(e.getActionCommand().equals(SetupStageGUI.ROTATE));

        if (e.getActionCommand().startsWith("cell") && mouseClicked) {
            positionBoatTable = e.getActionCommand();
            showTable(positionBoat,positionBoatTable,rotation);
            rotation = false;
        }
        mouseClicked = false;

        switch (e.getActionCommand()) {
            case SetupStageGUI.BEGIN_BUTTON -> mainView.switchView(MainView.GAME_STAGE_VIEW);
            case SetupStageGUI.LOGOUT_BTN -> mainView.switchView(MainView.LOGOUT_VIEW);
            case SetupStageGUI.DELETE_BTN -> mainView.switchView(MainView.DELETE_VIEW);
            case SetupStageGUI.ROTATE -> rotation = true;
        }


    }



    @Override
    public void mouseClicked(MouseEvent e) {
        mouseClicked = true;
        positionBoat = e.getComponent().getY();
        System.out.println("mouse click");
        System.out.println(e.getComponent());

    }



    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
    private void isClickedBoat(String boat){

        switch(boat){
            case "PortaAvions" -> isClickedPorta = true;
            case "Destructor" -> isClickedDestructor = true;
            case "Submari" -> isClickedSubmari = true;
            case "Submari2" -> isClickedSubmari2 = true;
            case "Llanxa" -> isClickedLlanxa = true;
        }
    }

    private void showTable(int positionBoat, String positionBoatTable, boolean rotation){
        int size = 0;
        String boat = "boat";
        String text = "text";
        Color boatColor = Color.white;
        switch (positionBoat) {
            case 55,54 -> {
                size = 5;
                boat = "PortaAvions";
                text = "P";
                boatColor = new Color(124,136,248);

            }
            case 149,148 -> {
                size = 4;
                boat = "Destructor";
                text = "D";
                boatColor = Color.yellow;

            }
            case 243,242 -> {
                size = 3;
                boat = "Submari";
                text = "S";
                boatColor = Color.pink;

            }
            case 311,310 -> {
                size = 3;
                boat = "Submari2";
                text = "S";
                boatColor = Color.pink;

            }
            case 405,404 -> {
                size = 2;
                boat = "Llanxa";
                text = "L";
                boatColor = Color.green;

            }
        }
        char letter = positionBoatTable.charAt(4);
        int positionLetter = letter - 'A' + 1;
        int number = Integer.parseInt(positionBoatTable.substring(5));
        if (rotation && positionLetter+size-1 > 15){

            mainController.showError("Place the boat in the table please!");
        }
        else if (!rotation && number+size-1 > 15){

            mainController.showError("Place the boat in the table please!");
        } else if (isClickedPorta && boat.equals("PortaAvions")) {
            mainController.showError("Porta avions already used!");

        }
        else if (isClickedDestructor && boat.equals("Destructor")) {
            mainController.showError("Destructor already used!");

        }
        else if (isClickedSubmari && boat.equals("Submari")) {
            mainController.showError("Submari already used!");

        }
        else if (isClickedSubmari2 && boat.equals("Submari2")) {
            mainController.showError("Submari2 already used!");

        }
        else if (isClickedLlanxa && boat.equals("Llanxa")) {
            mainController.showError("Llanxa already used!");

        }else if (!badPositionBoat(number,positionLetter,size,rotation)) {
            mainController.showError("Cell already occupied!");

        }else{
            if (rotation){
                for (int i = 0; i < size; i++){
                    //saveBoatPosition(boat,"vertical",number,positionLetter,size);
                    setUpGUI.paintUsedBoats(i,boat);
                    setUpGUI.paintBoatVertical(number,positionLetter,i,boatColor,text);
                }
            }
            else{
                for (int i = 0; i < size; i++){
                    //saveBoatPosition(boat,"horizontal",number,positionLetter,size);
                    setUpGUI.paintUsedBoats(i,boat);
                    setUpGUI.paintBoatHorizontal(number,positionLetter,i,boatColor,text);
                }
            }
            isClickedBoat(boat);
        }
    }

    private boolean badPositionBoat(int number,int positionLetter,int size,boolean rotation){
        boolean goodPosition = true;
        if (rotation) {
            for (int i = 0; i < size; i++) {
                if (!setUpGUI.checkCellVertical(number,positionLetter,i)) {
                    goodPosition = false;
                    break;
                }
            }
        } else {
            for (int i = 0; i < size; i++) {
                if (!setUpGUI.checkCellHorizontal(number,positionLetter,i)) {
                    goodPosition = false;
                    break;
                }
            }
        }

        return goodPosition;
    }

    private void saveBoatPosition(String boat,String orientation,int number, int positionLetter,int size){
        /*switch(boat){
            //case "PortaAvions" -> //guardar porta aviones(orientation(vertical o horizontal), number(numero casilla),positionletter(casilla de letras)
            //case "Destructor" -> //guardar destructor(orientation(vertical o horizontal), number(numero casilla),positionletter(casilla de letras)
            //case "Submari" -> //guardar submarino(orientation(vertical o horizontal), number(numero casilla),positionletter(casilla de letras)
            //case "Submari2" -> //guardar submarino(orientation(vertical o horizontal), number(numero casilla),positionletter(casilla de letras)
            //case "Llanxa" -> //guardar lancha(orientation(vertical o horizontal), number(numero casilla),positionletter(casilla de letras)
        }*/
    }

}



