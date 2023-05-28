package Persistance;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * SQL del guardado del juego
 */
public class GameSQL implements GameDAO{

    public void addGame(String userName, String nameGame, String fileGame, int numAttacks, LocalDate date, int victoria) {
        String query = "INSERT INTO guardarpartida (Usuario, NombrePartida, FicheroPartidas, AtackPartida, Fecha, Victoria) VALUES ('"+ userName + "', '"+ nameGame +"', '" + fileGame + "', '" + numAttacks + "', '" + date + "', '" + victoria +"');";

        SQLConnector.getInstance().insertQuery(query);
    }

    /**
     * Valida el nombre del juego
     * @param gameName String del nombre del juego
     * @return Devuelve verdadero si ya hay un nombre que coincide en la base de datos. De lo contrario, falso.
     */
    public boolean validGameName(String gameName) {
        String query = "SELECT COUNT(*) FROM guardarpartida WHERE NombrePartida = '" + gameName + "';";
        ResultSet result = SQLConnector.getInstance().selectQuery(query);

        try {
            if (result.next()) {
                int count = result.getInt(1);
                return count > 0;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     *Mostrar partidas guardadas
     * @param userName String nombre del usuario
     * @return Lista de partidas guardadas
     * @throws SQLException una extensión de java. idioma Excepción y proporciona información adicional relacionada con fallas que ocurren en un contexto de base de datos
     */
    public List<String> savedNameGames(String userName) throws SQLException {
        String query = "SELECT FicheroPartidas AS fichero FROM guardarpartida WHERE Usuario = '" + userName + "';";
        ResultSet result = SQLConnector.getInstance().selectQuery(query);

        List<String> savedGames = new ArrayList<>();
        while (result.next()) {
            String game = result.getString("fichero");
            savedGames.add(game);
        }
        return savedGames;
    }

    /**
     * Selecciona las fecha a guardar
     * @param gameName String del nombre de la partida
     * @return la fecha registrada
     */
    public Date dateGame(String gameName) {
        String query = "SELECT Fecha AS fecha FROM guardarpartida WHERE NombrePartida = '" + gameName + "';";
        ResultSet result = SQLConnector.getInstance().selectQuery(query);

        Date date;
        try {
            date = result.getDate("fecha");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return date;
    }


    /**
     * Seleciona el fichero del partida
     * @param gameName String del nombre de la partida
     * @return el fichero con la partida
     */
    public String fileGame(String gameName) {
        String query = "SELECT FicheroPartidas AS fichero FROM guardarpartida WHERE NombrePartida = '" + gameName + "';";
        ResultSet result = SQLConnector.getInstance().selectQuery(query);

        String fileGame;
        try {
            fileGame = result.getString("fichero");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return fileGame;
    }

    /**
     * Registra los ataques durante una partida
     * @param gameName String nombre de la partida
     * @return una lista de ataques
     * @throws SQLException una extensión de java. idioma Excepción y proporciona información adicional relacionada con fallas que ocurren en un contexto de base de datos
     */
    public List<Integer> attacksInGame(String gameName) throws SQLException {
        String query = "SELECT AtackPartida AS Attack FROM guardarpartida WHERE NombrePartida = '" + gameName + "';";
        ResultSet result = SQLConnector.getInstance().selectQuery(query);

        List<Integer> numbers = new ArrayList<>();
        while (result.next()) {
            int num = result.getInt("number");
            numbers.add(num);
        }

        return numbers;
    }

    /**
     * Borrar partida
     * @param gameName nombre de la partida
     */
    public void deleteGame(String gameName) {
        String query = "DELETE FROM guardarpartida WHERE NombrePartida = '" + gameName + "';";
        ResultSet result = SQLConnector.getInstance().selectQuery(query);

    }

    /**
     * Guardar una partida en la base de datos
     * @param gameFile fichero de la partida
     * @param gameName nombre de la partida
     */
    public void saveGame(String gameFile, String gameName) {
        String query = "UPDATE guardar SET '" + gameName + "' WHERE NombrePartida = '" + gameName + "';";
        SQLConnector.getInstance().selectQuery(query);
    }

    /**
     * Calcula el numero de partidas por usuario
     * @param user usuario registrado
     * @return entero con el numero de partidas disputadas
     */
    public int calcularNumeroPartidas(String user) {
        String query = "SELECT COUNT(*) AS row_count FROM guardarpartida WHERE Usuario = '" + user + "';";
        ResultSet result = SQLConnector.getInstance().selectQuery(query);

        try {
            if (result.next()) {
                return result.getInt("row_count");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return 0; // Valor predeterminado en caso de error o si no hay resultados
    }


    /**
     * Calcula el número de partidas victorias
     * @param user usuario registrado
     * @return entero con el numero de victorias
     */
    public int calcularNumeroVictorias(String user) {
        String query = "SELECT COUNT(*) AS row_count FROM guardarpartida WHERE Usuario = '" + user + "' AND Victoria = 1;";
        ResultSet result = SQLConnector.getInstance().selectQuery(query);

        try {
            if (result.next()) {
                return result.getInt("row_count");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return 0; // Valor predeterminado en caso de error o si no hay resultados
    }


    /**
     * Extraer un arraylist de los ataques
     * @param user nombre de usuario
     * @return un arraylist con enteros que representa los ataques de una partida
     */
    public ArrayList<Integer> extraerArrayAtaques(String user) {
        String query = "SELECT AtackPartida FROM guardarpartida WHERE Usuario = '" + user + "';";
        ResultSet result = SQLConnector.getInstance().selectQuery(query);

        ArrayList<Integer> ataques = new ArrayList<>();
        try {
            while (result.next()) {
                int ataque = result.getInt("AtackPartida");
                ataques.add(ataque);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return ataques;
    }

    public ArrayList<String> extraerArrayUsers() {
        String query = "SELECT DISTINCT Usuario FROM guardarpartida;";
        ResultSet result = SQLConnector.getInstance().selectQuery(query);

        ArrayList<String> usuarios = new ArrayList<>();
        try {
            while (result.next()) {
                String usuario = result.getString("Usuario");
                usuarios.add(usuario);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return usuarios;
    }


    /**
     * Partida con el numero de ataques más elevado
     * @param user nombre de usuario
     * @return entero con el numero de ataques mas elevado
     */
    public int obtenerAtaqueMasAlto(String user) {
        String query = "SELECT MAX(AtackPartida) AS maxAtaque FROM guardarpartida WHERE Usuario = '" + user + "';";
        ResultSet result = SQLConnector.getInstance().selectQuery(query);

        int ataqueMasAlto = 0;
        try {
            if (result.next()) {
                ataqueMasAlto = result.getInt("maxAtaque");
                return ataqueMasAlto;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return 0;
    }

    public boolean checkUser(String user) {
        String query = "SELECT COUNT(*) AS count FROM guardarpartida WHERE Usuario = '" + user + "';";
        ResultSet result = SQLConnector.getInstance().selectQuery(query);

        try {
            if (result.next()) {
                int count = result.getInt("count");
                return count > 0;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    public boolean verificarNombrePartidaRepetido(String usuario, String nombrePartida) {


        String query = "SELECT COUNT(*) FROM registro as r, guardarpartida as g  WHERE r.Usuario = '" + usuario + "' AND g.NombrePartida ='" +nombrePartida +"';";
        ResultSet result = SQLConnector.getInstance().selectQuery(query);
        try {
            if (result.next()) {
                int count = result.getInt(1);
                return count > 0;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;


    }


}
