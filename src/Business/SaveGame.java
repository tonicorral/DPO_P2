package Business;

import Business.Game;
import Persistance.GameDAO;
import Presentation.MainView;
import Presentation.Views.LoginGUI;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;

public class SaveGame {
    private GameDAO gameDAO;
    private String user;

    public SaveGame(GameDAO gameDAO, String user) {
        this.gameDAO = gameDAO;
        this.user = user;
    }

    public Game leerPartidaString(String nameGame) {

        String  jsonString = gameDAO.fileGame(nameGame);
        Gson gson = new Gson();
        Game partida = gson.fromJson(jsonString, Game.class);
        return partida;
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

    public void  anadirPartida (Game partida, int numAttacks, String nombrePartida, int victoria) {
        gameDAO.addGame(this.user, nombrePartida, guardarPartidaString(partida), numAttacks, LocalDate.now(), victoria);
    }

    public void setUser(String user) {

        this.user = user;
    }

    public String getUser(){
        return this.user;
    }
    public int calcularVictorias(String user){
        int victorias = gameDAO.calcularNumeroVictorias(user);

        return victorias;
    }

    public int calcularPartidas(String user){
        int partidas = gameDAO.calcularNumeroPartidas(user);

        return partidas;
    }

}
