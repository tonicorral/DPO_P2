package Business;

public class Boat {
    private String name;
    private int size;
    private char referenceName;
    private int[] firstCoordinate;
    private int[] lastCoordinate;
    private boolean alive;

    public Boat(String name, int size, char referenceName) {
        this.name = name;
        this.size = size;
        this.referenceName = referenceName;
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

    public int[] getFirstCoordinate() {
        return firstCoordinate;
    }

    public void setFirstCoordinate(int[] firstCoordinate) {
        this.firstCoordinate = firstCoordinate;
    }

    public int[] getLastCoordinate() {
        return lastCoordinate;
    }

    public void setLastCoordinate(int[] lastCoordinate) {
        this.lastCoordinate = lastCoordinate;
    }

    public boolean isAlive() {
        return alive;
    }

    public void setAlive(boolean alive) {
        this.alive = alive;
    }
}
