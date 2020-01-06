package model;

/**
 *
 * @author Lenvo
 */
public class Ticket {
    private long id;
    private String seat;
    private String customer;
    private boolean status;
    
    public Ticket() {
        id = 0;
        seat = "";
        customer = "";
        status = false;
    }

    public Ticket(long id, String seat, String customer, boolean status) {
        this.id = id;
        this.seat = seat;
        this.customer = customer;
        this.status = status;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getSeat() {
        return seat;
    }

    public void setSeat(String seat) {
        this.seat = seat;
    }

    public String getCustomer() {
        return customer;
    }

    public void setCustomer(String customer) {
        this.customer = customer;
    }

    public boolean getStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

}
