package Persistance;

public interface UserDAO {
    void addUser(String username, String password, String mail);

    boolean checkUserName(String name);


    //void logOutUserID();




}
