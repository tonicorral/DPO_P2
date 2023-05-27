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

/**
 * Controla la funcionalidad de la pantalla de carga de partida
 */
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


    /**
     * Constructor del setUp del juego
     * @param setUpGUI contiene la información de la vista del setup del juego
     * @param mainView contine la informacion de la clase de las vistas principales
     * @param iaModel contiene la información de la IA
     * @param gameModel contiene la información deL juego
     * @param gameStageController contiene la información del controlador de la partida
     */

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

    /**
     * Muestra mensajes dependiendo de la accion y el evento del setup de la partida
     * @param e variable para controlar la acción
     */
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
                    //gameStageController = new GameStageController()
                    gameStageController.startTimer();

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
                break;

        }


    }


    /**
     * Detecta si la accion del raton ha sido usada
     * @param e variable para controlar la acción
     */
    @Override
    public void mouseClicked(MouseEvent e) {
        mouseClicked = true;
        positionBoat = e.getComponent().getY();
        System.out.println("mouse click");
        System.out.println(e.getComponent());

    }


    /**
     * Detecta si el raton sigue pulsado
     * @param e the event to be processed
     */
    @Override
    public void mousePressed(MouseEvent e) {

    }
    /**
     * Detecta si el ratón ya no esta pulsado
     * @param e variable para controlar la acción
     */
    @Override
    public void mouseReleased(MouseEvent e) {

    }


    /**
     * Detecta si el ratón esta en la zona a detectar
     * @param e the event to be processed
     */
    @Override
    public void mouseEntered(MouseEvent e) {

    }

    /**
     * Detecta si el ratón ya no esta en la zona para pulsar
     * @param e variable para controlar la acción
     */
    @Override
    public void mouseExited(MouseEvent e) {

    }

    /**
     *  detecta si el barco esta pulsado
     * @param boat String de barco
     */
    private void isClickedBoat(String boat){

        switch(boat){
            case "PortaAvions" -> isClickedPorta = true;
            case "Destructor" -> isClickedDestructor = true;
            case "Submari" -> isClickedSubmari = true;
            case "Submari2" -> isClickedSubmari2 = true;
            case "Llanxa" -> isClickedLlanxa = true;
        }
    }

    /**
     * Muestra la tabla
     * @param positionBoat entero con la posicion del barco
     * @param positionBoatTable String con la posion del barco
     * @param rotation booleano que determina la direccion del barco
     */
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

    /**
     * Detecta si el barco esta mal posicionado
     * @param number entero con el numero de barco
     * @param positionLetter posion de la letra
     * @param size entero tamaño
     * @param rotation booleano que indica la direcion del barco
     * @return
     */
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


    /**
     * Guardar la posicion del barco
     * @param boat el tipo de barco
     * @param orientation orientacion del barco
     * @param number numero del barco
     * @param positionLetter tamaño de la letra
     * @param size tamaño del barco
     * @param text texto a añadir al barco
     */
    private void saveBoatPosition(String boat, Boolean orientation, int number, int positionLetter, int size, String text){

       switch(boat){
            case "PortaAvions" -> {
                Boat boat1 = new Boat(boat,size,text,number,positionLetter,orientation,"Alive");
                boats.set(0,boat1);
            }
            case "Destructor" -> {
                Boat boat2 = new Boat(boat,size,text,number,positionLetter,orientation,"Alive");
                boats.set(1,boat2);
            }
            case "Submari" ->{
                Boat boat3 = new Boat(boat,size,text,number,positionLetter,orientation,"Alive");
                boats.set(2,boat3);
            }
            case "Submari2" -> {
                Boat boat4 = new Boat(boat,size,text,number,positionLetter,orientation,"Alive");
                boats.set(3,boat4);
            }
            case "Llanxa" -> {
                Boat boat5 = new Boat(boat,size,text,number,positionLetter,orientation,"Alive");
                boats.set(4,boat5);
            }
        }

    }


    /**
     * Guardar jugador
     * @param boats arraylist de los barcos marcados
     * @return el jugador guardado
     */
    private Player savePlayer(ArrayList<Boat> boats){
        player = new Player(boats,new ArrayList<>(),new ArrayList<>(),new Tablero(boats),true);
        return player;
    }


    /**
     * eliminar los barcos
     */
    public void eliminateBoats(){
        isClickedDestructor = false;
        isClickedLlanxa = false;
        isClickedPorta = false;
        isClickedSubmari = false;
        isClickedSubmari2 = false;
        setUpGUI.unPaintTable();
        setUpGUI.unPaintBoats();
        setUpGUI.getStatusBoats(isClickedPorta,isClickedDestructor,isClickedSubmari,isClickedSubmari2,isClickedLlanxa);
    }


    /**
     * Cmprueba que todos los barcos haya sido puestos antes de empezar la partida
     * @return si han colocado todos los barcos
     */
    private boolean checkAllBoatsPut(){
        return isClickedSubmari2 && isClickedSubmari && isClickedPorta && isClickedLlanxa && isClickedDestructor;
    }


    /**
     * getter del numero de jugadores IA regirstrados
     * @return el numero de IAs
     */
    public int getNumberPlayers(){
        return setUpGUI.getNumPlayers();
    }



}



