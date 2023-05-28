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

     void addGame(String userName, String nameGame, String fileGame, int numAttacks, LocalDate date, int victoria,String timer); // Comprobar si la clase LocalDate es la correcta para almacenar la fecha!

     int calcularNumeroPartidas(String user);


     int calcularNumeroVictorias(String user);

     ArrayList<Integer> extraerArrayAtaques(String user);

     int obtenerAtaqueMasAlto(String user);

     ArrayList<String> extraerArrayUsers();

     boolean checkUser(String user);

     boolean verificarNombrePartidaRepetido(String usuario, String nombrePartida);

     ArrayList<String> extraerNombresPartidas();

}