package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Ticket;

/**
 *
 * @author Lenvo
 */
public class TicketDAO {
    
    public Ticket get(long id) {
        Ticket item = null;
        
        SQLConnector sqlConnector = new SQLConnector();
        
        try {
            sqlConnector.statement("select * from `ticket` where `id`=?").setLong(1,id);
            ResultSet rs = sqlConnector.query();
            if(rs.next()) item = new Ticket(rs.getLong(1),rs.getString(2),rs.getString(3),rs.getBoolean(4));
            
        } catch (SQLException ex) {
            Logger.getLogger(TicketDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        sqlConnector.close();
        
        return item;
    }
    public int size() {
        int l = 0;
        
        SQLConnector sqlConnector = new SQLConnector();
        sqlConnector.statement("select * from `ticket` where `status`=0");
        
        ResultSet rs = sqlConnector.query();
        try {
            for(;rs.next();++l) {}
            
        } catch (SQLException ex) {
            Logger.getLogger(TicketDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        sqlConnector.close();
        
        return l;
    }
    public ArrayList<Ticket> getAll(int i,int c) {
        ArrayList<Ticket> items = new ArrayList<>();
        
        SQLConnector sqlConnector = new SQLConnector();
        sqlConnector.statement("select * from `ticket` where `status`=0 order by `id` desc");
        
        ResultSet rs = sqlConnector.query();
        try {
            for(int j=0;rs.next();++j) {
                if(j>=i)
                    if(j-i<c) items.add(new Ticket(rs.getLong(1),rs.getString(2),rs.getString(3),rs.getBoolean(4)));
                    else break;
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(TicketDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        sqlConnector.close();
        
        return items;
    }
    public List<Ticket> getAll() {
        ArrayList<Ticket> items = new ArrayList<>();
        
        SQLConnector sqlConnector = new SQLConnector();
        sqlConnector.statement("select * from `ticket` where `status`=0 order by `id` desc");
        
        ResultSet rs = sqlConnector.query();
        try {
            for(;rs.next();) items.add(new Ticket(rs.getLong(1),rs.getString(2),rs.getString(3),rs.getBoolean(4)));
            
        } catch (SQLException ex) {
            Logger.getLogger(TicketDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        sqlConnector.close();
        
        return items;
    }
    public ArrayList<Ticket> getCustomerAll(String customer,int i,int c) {
        ArrayList<Ticket> items = new ArrayList<>();
        
        SQLConnector sqlConnector = new SQLConnector();
        PreparedStatement ps = sqlConnector.statement("select * from `ticket` where `customer`=? and `status`=0 order by `id` desc");
        
        try {
            ps.setString(1,customer);
            
            ResultSet rs = sqlConnector.query();
            for(int j=0;rs.next();++j) {
                if(j>=i)
                    if(j-i<c) items.add(new Ticket(rs.getLong(1),rs.getString(2),rs.getString(3),rs.getBoolean(4)));
                    else break;
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(TicketDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        sqlConnector.close();
        
        return items;
    }
    public ArrayList<Ticket> getCustomerAll(String customer) {
        ArrayList<Ticket> items = new ArrayList<>();
        
        SQLConnector sqlConnector = new SQLConnector();
        PreparedStatement ps = sqlConnector.statement("select * from `ticket` where `customer`=? and`status`=0 order by `id` desc");
        
        try {
            ps.setString(1,customer);
            
            ResultSet rs = sqlConnector.query();
            for(;rs.next();) items.add(new Ticket(rs.getLong(1),rs.getString(2),rs.getString(3),rs.getBoolean(4)));
            
        } catch (SQLException ex) {
            Logger.getLogger(TicketDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        sqlConnector.close();
        
        return items;
    }
    
    public ArrayList<Ticket> filter(String key, String info, String sort) {
        ArrayList<Ticket> items = new ArrayList<>();
        if(info.equals("")) return items;
        
        switch(sort) {
            case "asc": sort = " order by `"+info+"` asc"; break;
            case "desc": sort = " order by `"+info+"` desc"; break;
            default: sort = ""; break;
        }
        
        key = " where lower(`"+info+"`) like lower('%"+key+"%')";
        
        SQLConnector sqlConnector = new SQLConnector();
        sqlConnector.statement("select * from `ticket`" + key + sort);
        
        ResultSet rs = sqlConnector.query();
        try {
            for(;rs.next();) items.add(new Ticket(rs.getLong(1),rs.getString(2),rs.getString(3),rs.getBoolean(4)));
            
        } catch(SQLException ex) {
            Logger.getLogger(TicketDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        sqlConnector.close();
        
        return items;
    }
    private ArrayList<Ticket> filter(String key, String info, String sort, int i,int c) {
        ArrayList<Ticket> items = new ArrayList<Ticket>();
        if(info.equals("")) return items;
        
        switch(sort) {
            case "asc": sort = " order by `"+info+"` asc"; break;
            case "desc": sort = " order by `"+info+"` desc"; break;
            default: sort = ""; break;
        }
        
        key = " where lower(`"+info+"`) like lower('%"+key+"%')";
        
        SQLConnector sqlConnector = new SQLConnector();
        sqlConnector.statement("select * from `ticket`" + key + sort);
        
        ResultSet rs = sqlConnector.query();
        try {
            for(int j=0;rs.next();++j) {
                if(j>=i)
                    if(j-i<c) items.add(new Ticket(rs.getLong(1),rs.getString(2),rs.getString(3),rs.getBoolean(4)));
                    else break;
            }
            
        } catch(SQLException ex) {
            Logger.getLogger(TicketDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        sqlConnector.close();
        
        return items;
    }
    
    public boolean remove(long id) {
        SQLConnector sqlConnector = new SQLConnector();
        PreparedStatement ps = sqlConnector.statement("delete from `ticket` where `id`=?");
        
        try {
            ps.setLong(1,id);
            
        } catch (SQLException ex) {
            Logger.getLogger(TicketDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        boolean r = sqlConnector.update();
        
        sqlConnector.close();
        
        return r;
    }
    
    public boolean booking(String seat,String customer) {
        SQLConnector sqlConnector = new SQLConnector();
        PreparedStatement ps = sqlConnector.statement("insert into `ticket`(`id`,`seat`,`customer`,`status`) values(?,?,?,?)");
        
        try {
            ps.setLong(1,Calendar.getInstance().getTime().getTime());
            ps.setString(2,seat);
            ps.setString(3,customer);
            ps.setBoolean(4,false);
            
        } catch(SQLException ex) {
            Logger.getLogger(TicketDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        boolean r = sqlConnector.update();
        
        sqlConnector.close();
        
        return r;
    }
    public boolean confirm(long id) {
        SQLConnector sqlConnector = new SQLConnector();
        PreparedStatement ps = sqlConnector.statement("update `ticket` set `status`=1 where `id`=?");
        
        try {
            ps.setLong(1,id);
            
        } catch (SQLException ex) {
            Logger.getLogger(TicketDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        boolean r = sqlConnector.update();
        
        sqlConnector.close();
        
        return r;
    }
    public boolean cancel(long id) {
        Ticket item = get(id);
        if(item==null) return false;
        if(item.getStatus()) return false;
        
        return remove(item.getId());
    }
    public boolean clear(String customer) {
        SQLConnector sqlConnector = new SQLConnector();
        PreparedStatement ps = sqlConnector.statement("delete from ticket where `customer`=?");
        try {
            ps.setString(1,customer);
        } catch (SQLException ex) {
            Logger.getLogger(TicketDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        boolean r = sqlConnector.update();
        
        return r;
    }
    public boolean clear() {
        SQLConnector sqlConnector = new SQLConnector();
        sqlConnector.statement("delete from ticket");
        
        boolean r = sqlConnector.update();
        
        return r;
    }
    
}
