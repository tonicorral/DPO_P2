package Business;

/**
 * Engloba los tipos de barcos de la partida
 */
public class Boat {
    private String name;
    private int size;
    private String referenceName;
    private int positionX;
    private int positionY;
    private boolean alive;

    private boolean orientation;

    private String status;

    /**
     * Constructor de la clase Boat.
     * Crea una instancia de Boat con los atributos especificados.
     * @param name El nombre del barco.
     * @param size El tamaño del barco.
     * @param referenceName El nombre de referencia del barco.
     * @param positionX La posición X del barco.
     * @param positionY La posición Y del barco.
     * @param orientation La orientación del barco.
     * @param status El estado del barco.
     **/
    public Boat(String name, int size, String referenceName,int positionX,int positionY,boolean orientation,String status) {
        this.name = name;
        this.size = size;
        this.referenceName = referenceName;
        this.positionX = positionX;
        this.positionY = positionY;
        this.orientation = orientation;
        this.status = status;
    }

    /**
     * Devuelve el tamaño de un objeto.
     *
     * @return El tamaño del objeto.
     **/
    public int getSize() {
        return size;
    }

    /**
     * Devuelve la posición X de un objeto.
     *
     * @return La posición X del objeto.
     **/
    public int getPositionX() {
        return positionX;
    }


    /**
     * Devuelve la posición Y de un objeto.
     *
     * @return La posición Y del objeto.
     **/
    public int getPositionY() {
        return positionY;
    }


    /**
     * Devuelve la orientación de un objeto.
     *
     * @return La orientación del objeto.
     **/
    public boolean getOrientation() {
        return orientation;
    }

    /**
     * Devuelve el estado de un objeto.
     *
     * @return El estado del objeto.
     **/
    public String getStatus() {
        return status;
    }


    /**
     * Establece el estado de un objeto.
     *
     * @param status El estado que se asignará al objeto.
     **/
    public void setStatus(String status) {
        this.status = status;
    }
}

