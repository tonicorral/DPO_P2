
package Presentation.Controllers;

import Business.*;

import Business.SaveGame;
import Presentation.MainView;
import Presentation.Views.GameStageGUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Controlar la funcionalidad de la partida
 */
public class GameStageController implements ActionListener{
    private GameStageGUI gameStageGUI;
    private SaveGame saveGame;
    private String positionBoatTable;
    private MainView mainView;
    private GameModel gameModel;
    private Game game;
    private String gameTime;
    private String nombrePartida;

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

    }

    /**
     * Muestra mensajes dependiendo de la accion y el evento del juego
     * @param e variable para controlar la acción
     */
    public void actionPerformed(ActionEvent e) {

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
                System.exit(0);
                break;

            case GameStageGUI.GUARDAR:

                nombrePartida = gameStageGUI.getNombrePartida();



                if (nombrePartida != null && !nombrePartida.isEmpty()) {
                    String user = saveGame.getUser();
                    boolean nombreRepetido = saveGame.verificarNombrePartidaRepetido(user, nombrePartida);

                    if (!nombreRepetido) {
                        saveGame.anadirPartida(game, game.getPlayer().getPositionAttackedX().size(), nombrePartida, 0,gameTime);
                        gameModel.stopTimer();
                        mainView.switchView(MainView.MENU_VIEW);
                        System.exit(0);
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
        gameStageGUI.forTableUser(game);
        gameStageGUI.setBoats(game);


    }

    /**
     * Método que sirve para cargar la partida.
     * @param game Variable donde se almacena la partida que se quiere guardar.
     */
    public void loadSaveTable(Game game) {

        gameStageGUI.forTables(game.getNumberPlayers(),game);
        gameStageGUI.forTableUser(game);
        gameStageGUI.setBoats(game);


    }

    /**
     * Actualiza el tiempo de juego
     * @param timer String del timer
     */
    public void updateTimer(String timer){
        gameStageGUI.updateLabel(timer);
        gameTime = timer;
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
        winGame();
        loseGame();
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

    /**
     * Método para comprobar si la partida se ha ganado al finalizar la partida.
     */
    private void winGame(){
        int counter = 0;
        for(int i = 0;i< game.getNumberPlayers();i++){
            if(!game.getJugadorIA().get(i).isAlive()){
                counter ++;
            }
        }
        if (counter == game.getNumberPlayers()){
            gameModel.stopTimer();
            mainView.showError("YOU WON THE GAME!");

            nombrePartida = gameStageGUI.getNombrePartida();
            if (nombrePartida != null && !nombrePartida.isEmpty()) {
                String user = saveGame.getUser();
                boolean nombreRepetido = saveGame.verificarNombrePartidaRepetido(user, nombrePartida);

                if (!nombreRepetido) {
                    saveGame.anadirPartida(game, game.getPlayer().getPositionAttackedX().size(), nombrePartida, 1,gameTime);
                    gameModel.stopTimer();
                    mainView.switchView(MainView.MENU_VIEW);
                    System.exit(0);
                } else {

                    mainView.showError("Error: Ya existe este nombre para la partida");

                }

            }

        }

    }

    /**
     * Método para comprobar si la partida se ha perdido al finalizar la partida.
     */
    private void loseGame(){
        if(!game.getPlayer().isAlive()){
            System.out.println("muertoooo" + game.getPlayer().isAlive());
            gameModel.stopTimer();

            mainView.showError("GAME OVER!");

            nombrePartida = gameStageGUI.getNombrePartida();
            if (nombrePartida != null && !nombrePartida.isEmpty()) {
                String user = saveGame.getUser();
                boolean nombreRepetido = saveGame.verificarNombrePartidaRepetido(user, nombrePartida);

                if (!nombreRepetido) {
                    saveGame.anadirPartida(game, game.getPlayer().getPositionAttackedX().size(), nombrePartida, 0,gameTime);
                    gameModel.stopTimer();
                    mainView.switchView(MainView.MENU_VIEW);
                    System.exit(0);
                } else {

                    mainView.showError("Error: Ya existe este nombre para la partida");

                }
            }
        }
    }


}
