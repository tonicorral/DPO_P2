package Persistance;

public interface UserDAO {
    boolean validSignUp(String user, String pass, String mail);
    boolean validLogin(String name, String password);

    boolean checkUserName(String name);
    boolean deleteUser(String user);
    String getUserName(String mail);
    int userID();


    void logOutUserID();


}
