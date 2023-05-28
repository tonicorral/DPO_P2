package Business;

import Persistance.GameDAO;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import java.time.LocalDate;
import java.util.ArrayList;

/**
 * Clase que sirve para implementar funciones relacionadas
 * con guardar o leer partidas de la base de datos.
 */
public class SaveGame {
    private GameDAO gameDAO;
    private String user;
    public static final int EVERYTHING_OK = 0;
    public static final int EMPTY_FIELD = 1;
    public static final int INCORRECT_USER = 2;

    /**
     * Contructor de la clase guardar partida.
     * @param gameDAO Interficie con métodos para consultar base de datos.
     * @param user Login del usuario registrado.
     */
    public SaveGame(GameDAO gameDAO, String user) {
        this.gameDAO = gameDAO;
        this.user = user;
    }

    /**
     * Método para poder leer partidas de la base de datos.
     * @param partida Nombre de la partida.
     * @return Clase tipo Game con toda la info de la partida.
     */
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

    /**
     * Método para almacenar la partida en la base de datos.
     * @param partida Partida que se desea guardar.
     * @param numAttacks Ataques realizado en partida.
     * @param nombrePartida  Nombre de la partida.
     * @param victoria 1 si se ha gando. 0 si se a perdido la partida.
     * @param timer Tiempo transcurrido en la partida.
     */
    public void  anadirPartida (Game partida, int numAttacks, String nombrePartida, int victoria, String timer) {
        gameDAO.addGame(this.user, nombrePartida, guardarPartidaString(partida), numAttacks, LocalDate.now(), victoria,timer);
    }

    /**
     * Método para almacenar en la clase el nombre del usuario
     * que ha hecho registro.
     * @param user Nombre usuario.
     */
    public void setUser(String user) {

        this.user = user;
    }

    /**
     * Método para consultar el nombre del usuario
     * que ha hecho registro.
     * @return Nombre usuario.
     */
    public String getUser(){
        return this.user;
    }

    /**
     * Método para calcular victorias totales de un jugador.
     * @param user Nombre usuario registrado.
     * @return
     */
    public int calcularVictorias(String user){
        int victorias = gameDAO.calcularNumeroVictorias(user);

        return victorias;
    }

    /**
     * Método para calcular partidas totales de un jugador.
     * @param user Nombre usuario registrado.
     * @return
     */
    public int calcularPartidas(String user){
        int partidas = gameDAO.calcularNumeroPartidas(user);

        return partidas;
    }

    /**
     * Método para calcular ataque más alto de un jugador.
     * @param user Nombre usuario registrado.
     * @return
     */
    public int extraerAtaqueMasAlto(String user){
        int ataqueMasAlto = gameDAO.obtenerAtaqueMasAlto(user);

        return ataqueMasAlto;
    }

    /**
     * Método para calcular todos los ataques de un jugador.
     * @param user Nombre usuario registrado.
     * @return
     */
    public ArrayList<Integer> extraerAtaques(String user){
        ArrayList<Integer> gameResults = gameDAO.extraerArrayAtaques(user);

        return gameResults;
    }

    /**
     * Método para consultar todos los jugadores registrados.
     * @return Lista de nombre de usuarios.
     */
    public ArrayList<String> getUsers( ){
        ArrayList<String> usersList = gameDAO.extraerArrayUsers();

        return usersList;
    }

    /**
     * Comprueba si el usaurio está registrado.
     * @param user Nombre usuario registrado.
     * @return Error según la condición.
     */
    public int searchUser(String user) {

        if (user.equals("")) {
            return EMPTY_FIELD;
        }
        if (!gameDAO.checkUser(user)) {
            return INCORRECT_USER;
        }

        return EVERYTHING_OK;
    }

    /**
     * Comprueba si el nombre de la partida está repetido.
     * @param user  Nombre usuario registrado.
     * @param nombrePartida Nombre partida.
     * @return Verdadero si ya existe.
     */
    public boolean verificarNombrePartidaRepetido( String user, String nombrePartida){
        boolean verificar= gameDAO.verificarNombrePartidaRepetido(user,nombrePartida);

        return verificar;
    }

    /**
     * Método para consutlar todos los nombres de las partidas almacenados.
     * @return Arraylist con todos los nombres.
     */
    public ArrayList<String> extraerNombresPartidas(){
        ArrayList<String> partidasList = gameDAO.extraerNombresPartidas();

        return partidasList;
    }

}
