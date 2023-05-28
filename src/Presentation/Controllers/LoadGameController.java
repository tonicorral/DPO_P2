package Presentation.Controllers;

import Business.SaveGame;
import Presentation.MainView;
import Presentation.Views.LoadGameGUI;
import Presentation.Views.StatisticsMenuGUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static Business.SaveGame.*;

public class LoadGameController implements ActionListener {

    private MainView mainView;
    private SaveGame saveGame;
    private LoadGameGUI loadGameGUI;
    private GameStageController gameStageController;

    /**
     * Contructor del controlador de la partida
     * @param mainView contine la informacion de la clase de las vistas principales
     * @param saveGame contiene la información del guardado de partida
     * @param loadGameGUI Contiene la partida que se desea cargar
     * @param gameStageController Clase que contiene la lógica para empezar la partida
     */
    public LoadGameController( MainView mainView, SaveGame saveGame,LoadGameGUI loadGameGUI, GameStageController gameStageController){

        this.mainView = mainView;
        this.saveGame = saveGame;
        this.loadGameGUI = loadGameGUI;
        this.gameStageController = gameStageController;

        mainView.setListeners(this);
    }

    /**
     * Detecta si el botón de cargar partida se ha pulsado.
     * @param e the event to be processed
     */
    public void actionPerformed(ActionEvent e) {


        switch (e.getActionCommand()) {
            case LoadGameGUI.CARGAR_BTN:
                mainView.switchView(MainView.MENU_VIEW);
                /*
                String partidaSeleccionada = loadGameGUI.getPartidaJList();
                mainView.switchView(MainView.GAME_STAGE_VIEW);
                gameStageController.loadSaveTable(saveGame.leerPartidaString(partidaSeleccionada));
                 */
                break;

        }

    }

}
