package Persistance;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Interfaz del GameDAO
 */
public interface GameDAO {

     void addGame(String userName, String nameGame, String fileGame, int numAttacks, LocalDate date, int victoria); // Comprobar si la clase LocalDate es la correcta para almacenar la fecha!

     int calcularNumeroPartidas(String user);

     int calcularNumeroVictorias(String user);

     ArrayList<Integer> extraerArrayAtaques(String user);

     int obtenerAtaqueMasAlto(String user);

     ArrayList<String> extraerArrayUsers(String user);

     boolean checkUser(String user);

     boolean validGameName(String gameName);
     String fileGame(String gameName);

     List<String> savedNameGames (String userName) throws SQLException;

     Date dateGame(String gameFile);

     List<Integer> attacksInGame(String userName) throws SQLException; //Para poder calcular la media de ataques por partida te devuelve un array de enteros con los ataques hechos en cada partida

     boolean verificarNombrePartidaRepetido(String usuario, String nombrePartida);

}