package Presentation.Controllers;

import Business.*;

import Business.SaveGame;
import Presentation.MainView;
import Presentation.Views.GameStageGUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/**
 * Controlar la funcionalidad de la partida
 */
public class GameStageController implements ActionListener{
    private GameStageGUI gameStageGUI;

    private SaveGame saveGame;

    private boolean mouseClicked = false;

    private int positionBoat;

    private String positionBoatTable;

    private boolean rotation = false;
    private boolean isClickedPorta = false,isClickedDestructor=false,isClickedSubmari=false,isClickedSubmari2=false,isClickedLlanxa=false;

    private MainView mainView;

    private IAModel iaModel;

    private GameModel gameModel;

    private String currentTime;

    private Game game;
    private  ArrayList<Boat> boats;

    private ArrayList<Player> players;
    private TimeThread timeThread;
    private int seconds=0,minuts=0;

    /**
     * Constructor del juego
     * @param gameStageGUI contiene la información del juego
     * @param mainView contine la informacion de la clase de las vistas principales
     * @param gameModel contiene la información de modelo de juego
     * @param saveGame contiene la información del guardado de partida
     */
    public GameStageController(GameStageGUI gameStageGUI, MainView mainView , GameModel gameModel, SaveGame saveGame) {
        this.gameStageGUI = gameStageGUI;
        this.mainView = mainView;
        this.gameModel = gameModel;
        this.saveGame = saveGame;

        mainView.setListeners(this);

        //mainView.setActionMouseListeners(this, this);
    }

    /**
     * Muestra mensajes dependiendo de la accion y el evento del juego
     * @param e variable para controlar la acción
     */
    public void actionPerformed(ActionEvent e) {

        //System.out.println(e.getActionCommand().equals(GameStageGUI.ROTATE));

       /* if (e.getActionCommand().startsWith("cell") && mouseClicked) {
            positionBoatTable = e.getActionCommand();
            showTable(positionBoat,positionBoatTable,rotation);
            rotation = false;
        }
        mouseClicked = false;

        */
        if (e.getActionCommand().startsWith("cell")) {
            positionBoatTable = e.getActionCommand();
            ArrayList<Integer> positionsUser = gameModel.attackUser(positionBoatTable);
            game = gameModel.insertAttack(game,positionsUser);
        }

        switch (e.getActionCommand()) {


            //case SetupStageGUI.BEGIN_BUTTON -> mainView.switchView(MainView.GAME_STAGE_VIEW);
            case GameStageGUI.ABANDONAR:
                gameModel.stopTimer();
                mainView.switchView(MainView.MENU_VIEW);
                break;

            case GameStageGUI.GUARDAR:
                String nombrePartida = gameStageGUI.getNombrePartida();


                if (nombrePartida != null && !nombrePartida.isEmpty()) {
                    String user = saveGame.getUser();
                    boolean nombreRepetido = saveGame.verificarNombrePartidaRepetido(user, nombrePartida);

                    if (!nombreRepetido) {
                        saveGame.anadirPartida(game, game.getPlayer().getPositionAttackedX().size(), nombrePartida, 0);
                    } else {

                        mainView.showError("Error: Ya existe este nombre para la partida");
                    }


                }
                break;
        }
    }


    /**
     * Inicializa el tablero
     */
    public void initTable() {
        game = gameModel.getGame();
        gameStageGUI.forTables(game.getNumberPlayers(),game);
        gameStageGUI.setBoats(game);

        //TODO cambiar jTAble (getStatus());!!!!!!!!!!!!!!!
    }

    /**
     * Actualiza el tiempo de juego
     * @param timer String del timer
     */
    public void updateTimer(String timer){
        gameStageGUI.updateLabel(timer);
    }

    /**
     * Empieza el contador del juego
     */
    public void startTimer(){
        gameModel.startTimer();
    }

    /**
     * Actualiza el tablero de la partida
     * @param game parametro que contiene el juego
     */
    public void updateTable(Game game){
        int numPlayers = game.getNumberPlayers();
        for(int i = 0;i<numPlayers;i++){
            gameStageGUI.updateIATable(game,i);
        }
        gameStageGUI.updateUserTable(game);
        gameModel.updateHundidos(game);
        paintTables(game,numPlayers);
    }

    /**
     * Pinta las casillas de los ataques
     * @param game parametro que contiene el juego
     * @param numPlayers numero de IAs
     */
    private void paintTables(Game game,int numPlayers){
        for(int m = 0;m<numPlayers;m++){
            for(int i = 0;i<15;i++){
                for(int j = 0;j<15;j++){
                    gameStageGUI.paintIA(game,i,j,numPlayers);
                }
            }
        }
    }






   /* public void displayGUI() {
        JFrame frame = new JFrame("Game Stage");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setContentPane(gameStageGUI.getMainPanel());
        frame.pack();
        frame.setVisible(true);
    }*/




}
