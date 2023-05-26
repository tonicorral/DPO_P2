package Business;

import Business.Game;
import Persistance.GameDAO;
import Presentation.MainView;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.util.ArrayList;

public class SaveGame {
    private GameDAO gameDAO;
    private String user;
    public static final int EVERYTHING_OK = 0;
    public static final int EMPTY_FIELD = 1;

    public static final int INCORRECT_USER = 2;
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
        } catch (Exception e) { //CAMBIAR , EXCEPTION NUNCA
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

    public int extraerAtaqueMasAlto(String user){
        int ataqueMasAlto = gameDAO.obtenerAtaqueMasAlto(user);

        return ataqueMasAlto;
    }

    public ArrayList<Integer> extraerAtaques(String user){
        ArrayList<Integer> gameResults = gameDAO.extraerArrayAtaques(user);

        return gameResults;
    }

    public ArrayList<String> getUsers(String user){
        ArrayList<String> usersList = gameDAO.extraerArrayUsers(user);

        return usersList;
    }

    public int searchUser(String user) {

        if (user.equals("")) {
            return EMPTY_FIELD;
        }
        if (!gameDAO.checkUser(user)) {
            return INCORRECT_USER;
        }

        return EVERYTHING_OK;
    }
}
