package Persistance;

import Business.Boat;

import java.util.ArrayList;

public interface ConfigurationDAO {
    ArrayList<Boat> loadBoats();

}
