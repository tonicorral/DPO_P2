package Business;


public class Boat {
    private String name;
    private int size;
    private String referenceName;
    private int positionX;
    private int positionY;
    private boolean alive;

    private boolean orientation;

    private String status;

    public Boat(String name, int size, String referenceName,int positionX,int positionY,boolean orientation,String status) {
        this.name = name;
        this.size = size;
        this.referenceName = referenceName;
        this.positionX = positionX;
        this.positionY = positionY;
        this.orientation = orientation;
        this.status = status;
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

    public String getReferenceName() {
        return referenceName;
    }

    public void setReferenceName(String referenceName) {
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

    public boolean getOrientation() {
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}

