package Persistance;

public class UserSQL implements UserDAO{


    public void addUser(String username, String password, String mail) {
        String query = "INSERT INTO registro(Usuario, Contraseña, Mail) VALUES (" +
                username + ", '" +
                password + "', " +
                mail +
                ");";
        SQLConnector.getInstance().insertQuery(query);
    }

}
