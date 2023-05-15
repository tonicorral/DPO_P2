package Business;


public class Boat {
    private String name;
    private int size;
    private char referenceName;
    private int positionX;
    private int positionY;
    private boolean alive;

    private boolean orientation;

    public Boat(String name, int size, char referenceName,int positionX,int positionY,boolean orientation) {
        this.name = name;
        this.size = size;
        this.referenceName = referenceName;
        this.positionX = positionX;
        this.positionY = positionY;
        this.orientation = orientation;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public char getReferenceName() {
        return referenceName;
    }

    public void setReferenceName(char referenceName) {
        this.referenceName = referenceName;
    }

    public boolean isAlive() {
        return alive;
    }

    public void setAlive(boolean alive) {
        this.alive = alive;
    }

    public int getPositionX() {
        return positionX;
    }

    public int getPositionY() {
        return positionY;
    }

    public boolean isOrientation() {
        return orientation;
    }

    public void setPositionX(int positionX) {
        this.positionX = positionX;
    }

    public void setPositionY(int positionY) {
        this.positionY = positionY;
    }

    public void setOrientation(boolean orientation) {
        this.orientation = orientation;
    }
}

