package Persistance;

import Business.Configuration;

import java.sql.*;

/**
 * The SQLConnector class will abstract the specifics of the connection to a MySQL database.
 *
 * This class follows the Singleton design pattern to facilitate outside access while maintaining
 * a single instance, as having multiple connectors to a database is generally discouraged.
 *
 * Be aware that this class presents a simplified approach. Configuration parameters SHOULD NOT be
 * hardcoded and the use of Statements COULD be replaced by PreparedStatements to avoid SQL Injection.
 */
public class SQLConnector {

    // The static attribute to implement the singleton design pattern.
    private static SQLConnector instance = null;

    // Attributes to connect to the database.
    private final String username;
    private final String password;
    private final String url;
    private Connection conn;


    /**
     * Contructor del conector SQL
     * @param user nombre de usuario
     * @param pass contraseña
     * @param ip varialbe de la ip
     * @param port variable del puerto
     * @param database variable de la base de datos
     */
    private SQLConnector(String user, String pass, String ip, int port, String database) {
        this.username = user;
        this.password = pass;

        this.url = "jdbc:mysql://" + ip + ":" + port + "/" + database;
        getConexion(url, username, password);
    }


    /**
     * Getter de la conexion remota con la base de datos
     * @param url db url
     * @param user nombre usuario
     * @param password variable de la contraseña
     */
    public void getConexion(String url, String user, String password) {
        try {
            conn = DriverManager.getConnection(url, user, password);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    /**
     * Static method that returns the shared instance managed by the singleton.
     *
     * @return The shared SQLConnector instance.
     */
    public static SQLConnector getInstance(){
        if (instance == null ){
            Configuration config = new Configuration("Files/config.json");
            // NOT a good practice to hardcode connection data! Be aware of this for your project delivery ;)
            instance = new SQLConnector(config.getUser(), config.getPassword(), config.getIP(), config.getPort(), config.getName());
            instance.connect();
        }
        return instance;
    }

    /**
     * Method that starts the inner connection to the database. Ideally, users would disconnect after
     * using the shared instance.
     */
    public void connect() {
        try {
            conn = DriverManager.getConnection(url, username, password);
        } catch(SQLException e) {
            System.err.println("Couldn't connect to --> " + url + " (" + e.getMessage() + ")");
        }
    }

    /**
     *  insertar la cola
     * @param query String de cola
     */
    public void insertQuery(String query){
        try {
            Statement s = conn.createStatement();
            s.executeUpdate(query);
        } catch (SQLException e) {
            System.err.println(query);
            System.err.println("Problem when inserting --> " + e.getSQLState() + " (" + e.getMessage() + ")");
        }
    }

    /**
     * Method that executes a selection query to the connected database.
     *
     * @param query String representation of the query to execute.
     * @return The results of the selection.
     */
    public ResultSet selectQuery(String query){
        ResultSet rs = null;
        try {
            Statement s = conn.createStatement();
            rs = s.executeQuery(query);
        } catch (SQLException e) {
            System.err.println(query);
            System.err.println("Problem when selecting data --> " + e.getSQLState() + " (" + e.getMessage() + ")");
        }
        return rs;
    }


    /**
     * Method that closes the inner connection to the database. Ideally, users would disconnect after
     * using the shared instance.
     */
    public void disconnect(){
        try {
            conn.close();
        } catch (SQLException e) {
            System.err.println("Problem when closing the connection --> " + e.getSQLState() + " (" + e.getMessage() + ")");
        }
    }


}
