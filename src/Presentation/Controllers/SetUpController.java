package Presentation.Controllers;

import Business.*;
//import Business.JugadorHumano;

import Presentation.MainView;
import Presentation.Views.SetupStageGUI;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

public class SetUpController implements ActionListener, MouseListener {

    private SetupStageGUI setUpGUI;


    private boolean mouseClicked = false;

    private int positionBoat;

    private String positionBoatTable;


    private boolean isClickedPorta = false,isClickedDestructor=false,isClickedSubmari=false,isClickedSubmari2=false,isClickedLlanxa=false;

    private MainView mainView;

    private GameModel gameModel;

    private GameStageController gameStageController;

    private IAModel iaModel;

    private  ArrayList<Boat> boats;

    private ArrayList<Player> players;

    private Player player;

    private boolean rotation = false;


    public SetUpController(SetupStageGUI setUpGUI, MainView mainView,IAModel iaModel,GameModel gameModel, GameStageController gameStageController) {
        this.setUpGUI = setUpGUI;
        this.mainView = mainView;

        this.iaModel = iaModel;
        this.gameModel = gameModel;
        this.gameStageController = gameStageController;

        mainView.setActionMouseListeners(this, this);

        boats = new ArrayList<>();
        for(int i =0;i<5;i++){
            boats.add(null);
        }
        players = new ArrayList<>();
        for(int i =0;i< setUpGUI.getNumPlayers();i++){
            players.add(null);
        }
    }

    public void actionPerformed(ActionEvent e) {
        System.out.println(e.getActionCommand().equals(SetupStageGUI.ROTATE));

        if (e.getActionCommand().startsWith("cell") && mouseClicked) {
            positionBoatTable = e.getActionCommand();
           // setUpGUI.getStatusBoats(isClickedPorta,isClickedDestructor,isClickedSubmari,isClickedSubmari2,isClickedLlanxa);
            showTable(positionBoat,positionBoatTable,rotation);
            rotation = false;
        }
        mouseClicked = false;

        switch (e.getActionCommand()) {
            //case SetupStageGUI.BEGIN_BUTTON -> mainView.switchView(MainView.GAME_STAGE_VIEW);
            case SetupStageGUI.BEGIN_BUTTON:
                if(!checkAllBoatsPut()){
                    mainView.showError("Put all boats in the table before starting the game!");
                }else{
                    savePlayer(boats);
                    gameModel.getNumberPlayers(getNumberPlayers());
                    gameModel.createGame(savePlayer(boats));
                    //iaModel.createBoats();
                    mainView.switchView(MainView.GAME_STAGE_VIEW);
                    gameStageController.initTable();


                }
                    break;

            case SetupStageGUI.LOGOUT_BTN:
                mainView.switchView(MainView.LOGOUT_VIEW);
                break;
            case SetupStageGUI.DELETE_BTN:
                mainView.switchView(MainView.DELETE_VIEW);
                break;
            case SetupStageGUI.ROTATE:
                rotation = true;
                break;
            case SetupStageGUI.ELIMINATE:
                eliminateBoats();
                setUpGUI.getStatusBoats(isClickedPorta,isClickedDestructor,isClickedSubmari,isClickedSubmari2,isClickedLlanxa);
                break;

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

            mainView.showError("Place the boat in the table please!");
        }
        else if (!rotation && number+size-1 > 15){

            mainView.showError("Place the boat in the table please!");
        } else if (isClickedPorta && boat.equals("PortaAvions")) {
            mainView.showError("Porta avions already used!");

        }
        else if (isClickedDestructor && boat.equals("Destructor")) {
            mainView.showError("Destructor already used!");

        }
        else if (isClickedSubmari && boat.equals("Submari")) {
            mainView.showError("Submari already used!");

        }
        else if (isClickedSubmari2 && boat.equals("Submari2")) {
            mainView.showError("Submari2 already used!");

        }
        else if (isClickedLlanxa && boat.equals("Llanxa")) {
            mainView.showError("Llanxa already used!");

        }else if (!badPositionBoat(number,positionLetter,size,rotation)) {
            mainView.showError("Cell already occupied!");

        }else{
            if (rotation){
                for (int i = 0; i < size; i++){
                    setUpGUI.paintUsedBoats(i,boat);
                    setUpGUI.paintBoatVertical(number,positionLetter,i,boatColor,text);
                }
                saveBoatPosition(boat, true,number,positionLetter,size,text);
            }
            else{
                for (int i = 0; i < size; i++){
                    setUpGUI.paintUsedBoats(i,boat);
                    setUpGUI.paintBoatHorizontal(number,positionLetter,i,boatColor,text);
                }
                saveBoatPosition(boat, false,number,positionLetter,size,text);
            }
            isClickedBoat(boat);
            setUpGUI.getStatusBoats(isClickedPorta,isClickedDestructor,isClickedSubmari,isClickedSubmari2,isClickedLlanxa);
        }
    }
//hola
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

    private void saveBoatPosition(String boat, Boolean orientation, int number, int positionLetter, int size, String text){

       switch(boat){
            case "PortaAvions" -> {
                Boat boat1 = new Boat(boat,size,text,number,positionLetter,orientation);
                boats.set(0,boat1);
            }
            case "Destructor" -> {
                Boat boat2 = new Boat(boat,size,text,number,positionLetter,orientation);
                boats.set(1,boat2);
            }
            case "Submari" ->{
                Boat boat3 = new Boat(boat,size,text,number,positionLetter,orientation);
                boats.set(2,boat3);
            }
            case "Submari2" -> {
                Boat boat4 = new Boat(boat,size,text,number,positionLetter,orientation);
                boats.set(3,boat4);
            }
            case "Llanxa" -> {
                Boat boat5 = new Boat(boat,size,text,number,positionLetter,orientation);
                boats.set(4,boat5);
            }
        }

    }

    private Player savePlayer(ArrayList<Boat> boats){
        player = new JugadorHumano(boats,new ArrayList<>(),new ArrayList<>(),new Tablero(boats));
        return player;
    }

    private void eliminateBoats(){
        isClickedDestructor = false;
        isClickedLlanxa = false;
        isClickedPorta = false;
        isClickedSubmari = false;
        isClickedSubmari2 = false;
        setUpGUI.unPaintTable();
        setUpGUI.unPaintBoats();
    }

    private boolean checkAllBoatsPut(){
        return isClickedSubmari2 && isClickedSubmari && isClickedPorta && isClickedLlanxa && isClickedDestructor;
    }

    public int getNumberPlayers(){
        return setUpGUI.getNumPlayers();
    }









}



