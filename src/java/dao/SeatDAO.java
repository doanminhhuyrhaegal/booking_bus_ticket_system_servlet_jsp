package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Seat;

/**
 *
 * @author Lenvo
 */
public class SeatDAO {
    
    private Seat get(String id) {
        Seat item = null;
        
        SQLConnector sqlConnector = new SQLConnector();
        
        try {
            sqlConnector.statement("select * from `seat` where `id`=?").setString(1,id);
            ResultSet rs = sqlConnector.query();
            if(rs.next()) item = new Seat(rs.getString(1),rs.getBoolean(2));
            
        } catch (SQLException ex) {
            Logger.getLogger(TicketDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        sqlConnector.close();
        
        return item;
    }
    public int size() {
        int l = 0;
        
        SQLConnector sqlConnector = new SQLConnector();
        sqlConnector.statement("select * from `seat` where `status`=0");
        
        ResultSet rs = sqlConnector.query();
        try {
            for(;rs.next();++l) {}
            
        } catch (SQLException ex) {
            Logger.getLogger(SeatDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        sqlConnector.close();
        
        return l;
    }
    public ArrayList<Seat> getAll(int i,int c) {
        ArrayList<Seat> items = new ArrayList<>();
        
        SQLConnector sqlConnector = new SQLConnector();
        sqlConnector.statement("select * from `seat` where `status`=0");
        
        ResultSet rs = sqlConnector.query();
        try {
            for(int j=0;rs.next();++j) {
                if(j>=i)
                    if(j-i<c) items.add(new Seat(rs.getString(1),rs.getBoolean(2)));
                    else break;
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(SeatDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        sqlConnector.close();
        
        return items;
    }
    public ArrayList<Seat> getAll() {
        ArrayList<Seat> items = new ArrayList<>();
        
        SQLConnector sqlConnector = new SQLConnector();
        sqlConnector.statement("select * from `seat` where `status`=0");
        
        ResultSet rs = sqlConnector.query();
        try {
            for(;rs.next();) items.add(new Seat(rs.getString(1),rs.getBoolean(2)));
            
        } catch (SQLException ex) {
            Logger.getLogger(SeatDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        sqlConnector.close();
        
        return items;
    }
    
    public ArrayList<Seat> filter(String key, String info, String sort) {
        ArrayList<Seat> items = new ArrayList<Seat>();
        if(info.equals("")) return items;
        
        switch(sort) {
            case "asc": sort = " order by `"+info+"` asc"; break;
            case "desc": sort = " order by `"+info+"` desc"; break;
            default: sort = ""; break;
        }
        
        key = " where lower(`"+info+"`) like lower('%"+key+"%')";
        
        SQLConnector sqlConnector = new SQLConnector();
        sqlConnector.statement("select * from `seat`" + key + " and `status`=0" + sort);
        
        ResultSet rs = sqlConnector.query();
        try {
            for(;rs.next();) items.add(new Seat(rs.getString(1),rs.getBoolean(2)));
            
        } catch(SQLException ex) {
            Logger.getLogger(Seat.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        sqlConnector.close();
        
        return items;
    }
    private ArrayList<Seat> filter(String key, String info, String sort, int i,int c) {
        ArrayList<Seat> items = new ArrayList<Seat>();
        if(info.equals("")) return items;
        
        switch(sort) {
            case "asc": sort = " order by `"+info+"` asc"; break;
            case "desc": sort = " order by `"+info+"` desc"; break;
            default: sort = ""; break;
        }
        
        key = " where lower(`"+info+"`) like lower('%"+key+"%')";
        
        SQLConnector sqlConnector = new SQLConnector();
        sqlConnector.statement("select * from `seat`" + key + " and `status`=0" + sort);
        
        ResultSet rs = sqlConnector.query();
        try {
            for(int j=0;rs.next();++j) {
                if(j>=i)
                    if(j-i<c) items.add(new Seat(rs.getString(1),rs.getBoolean(2)));
                    else break;
            }
            
        } catch(SQLException ex) {
            Logger.getLogger(Seat.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        sqlConnector.close();
        
        return items;
    }
    
    private boolean add(String id, boolean status) {
        SQLConnector sqlConnector = new SQLConnector();
        PreparedStatement ps = sqlConnector.statement("insert into `seat`(`id`,`status`) values(?,?)");
        
        try {
            ps.setString(1,id);
            ps.setBoolean(2,status);
            
        } catch (SQLException ex) {
            Logger.getLogger(SeatDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        boolean r = sqlConnector.update();
        
        sqlConnector.close();
        
        return r;
    }
    public boolean edit(String id, boolean status) {
        SQLConnector sqlConnector = new SQLConnector();
        PreparedStatement ps = sqlConnector.statement("update `seat` set `status`=? where `id`=?");
        
        try {
            ps.setBoolean(1,status);
            ps.setString(2,id);
            
        } catch (SQLException ex) {
            Logger.getLogger(SeatDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        boolean r = sqlConnector.update();
        
        sqlConnector.close();
        
        return r;
    }
    
    public String booking() {
        String item = null;
        
        SQLConnector sqlConnector = new SQLConnector();
        sqlConnector.statement("select * from `seat` where `status`=0");
        
        ResultSet rs = sqlConnector.query();
        try {
            if(rs.next()) item = rs.getString(1);
            
        } catch (SQLException ex) {
            Logger.getLogger(TicketDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        sqlConnector.close();
        
        if(item!=null) if(!edit(item,true)) item = null;
        
        return item;
    }
    
}
