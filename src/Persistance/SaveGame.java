package Persistance;

import Business.Game;
import Presentation.Views.LoginGUI;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;

public class SaveGame implements ActionListener {
    private GameDAO gameDAO;
    private LoginGUI loginGUI;
    private String user;

    public SaveGame(GameDAO gameDAO, LoginGUI loginGUI, String user) {
        this.gameDAO = gameDAO;
        this.loginGUI = loginGUI;
        this.user = user;
    }

    public Game leerPartidaString(String nameGame) {

        String  jsonString = gameDAO.fileGame(nameGame);
        Gson gson = new Gson();
        Game partida = gson.fromJson(jsonString, Game.class);
        return partida;
    }



    public LocalDate getCurrentDate() {
        LocalDate currentDate = LocalDate.now();
        return currentDate;
    }


    public String guardarPartidaString (Game partida)  {

        try {
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            JsonObject adventureObj = gson.toJsonTree(partida).getAsJsonObject();
            String jsonString = gson.toJson(adventureObj);
            return jsonString;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void  anadirPartida (Game partida, int numAttacks) {
        gameDAO.addGame(this.user, guardarPartidaString(partida), numAttacks, LocalDate.now());
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        this.user = loginGUI.getUser();
        System.out.println(this.user);
        Game game = null;
        anadirPartida(game, 0);
    }
}
