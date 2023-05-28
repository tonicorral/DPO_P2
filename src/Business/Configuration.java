package Business;


import Business.Boat;
import com.google.gson.*;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;

/**
 * Configuacion de la partida jugada
 */
public class Configuration{

    private final Gson gson;

    private Reader config;

    private String IP;
    private int port;
    private String name;
    private String user;
    private String password;
    private int time;

    /**
     * Contructor de la configuació

     * @param config informacio de la configuració
     */
    public Configuration(String config) {
        this.gson = new GsonBuilder().setPrettyPrinting().create();
        try {
            this.config = new FileReader(config);

        } catch (IOException e) {
            e.printStackTrace();
        }

        loadConfigFile();
    }

    /**
     * getter de la ip
     * @return la ip
     */
    public String getIP() {
        return IP;
    }

    /**
     * getter de port
     * @return el port
     */
    public int getPort() {
        return port;
    }

    /**
     * getter del nom
     * @return el nom
     */
    public String getName() {
        return name;
    }

    /**
     * getter del user
     * @return el user
     */
    public String getUser() {
        return user;
    }

    /**
     * getter de la contrasenya
     * @return la contrasenya
     */
    public String getPassword() {
        return password;
    }

    /**
     * descarregar la inforació de la base de dades
     */

    public void loadConfigFile() {

        JsonParser jsonParser = new JsonParser();
        JsonElement object;
        object = jsonParser.parse(config);


        JsonObject conf = object.getAsJsonObject();
        JsonObject dataBase = conf.getAsJsonObject("dataBase");
        this.IP = ((dataBase.get("IP").getAsString()));
        this.port = ((dataBase.get("port").getAsInt()));
        this.name = ((dataBase.get("dbName").getAsString()));
        this.user = ((dataBase.get("dbUser").getAsString()));
        this.password = ((dataBase.get("password").getAsString()));
        this.time = ((dataBase.get("intervalMs").getAsInt()));
    }

    public int getTime() {
        return time;
    }
}
