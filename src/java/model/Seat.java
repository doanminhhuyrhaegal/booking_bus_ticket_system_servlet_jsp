package model;

/**
 *
 * @author Lenvo
 */
public class Seat {
    private String id;
    private boolean status;
    
    public Seat() {
        id = "";
        status = false;
    }

    public Seat(String id, boolean status) {
        this.id = id;
        this.status = status;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public boolean getStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    
}
