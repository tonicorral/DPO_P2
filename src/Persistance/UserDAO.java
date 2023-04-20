package Persistance;

public interface UserDAO {
    void addUser(String username, String password, String mail);

    boolean checkUserName(String name);

    boolean checkEmail(String name);

    boolean validPassword(String name,String password);
    //void logOutUserID();




}
