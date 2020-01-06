package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.User;

/**
 *
 * @author Lenvo
 */
public class UserDAO {
    
    public User get(String username) {
        User item = null;
        
        SQLConnector sqlConnector = new SQLConnector();
        
        try {
            sqlConnector.statement("select * from `user` where `username`=?").setString(1,username);
            ResultSet rs = sqlConnector.query();
            if(rs.next()) item = new User(  rs.getString(1),rs.getString(2),rs.getString(3),
                                            rs.getString(4),rs.getString(5),rs.getBoolean(6) );
            
        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        sqlConnector.close();
        
        return item;
    }
    
    public ArrayList<User> filter(String key, String info, String sort, String role, int i,int c) {
        ArrayList<User> items = new ArrayList<>();
        if(info.equals("")) return items;
        
        switch(sort) {
            case "asc": sort = " order by `"+info+"` asc"; break;
            case "desc": sort = " order by `"+info+"` desc"; break;
            default: sort = ""; break;
        }
        
        key = " where lower(`"+info+"`) like lower('%"+key+"%')";
        
        switch(role) {
            case "customer": role = " and `role`=0"; break;
            case "seller": role = " and `role`=1"; break;
            default: role = ""; break;
        }
        
        SQLConnector sqlConnector = new SQLConnector();
        sqlConnector.statement("select * from `user`" + key + role + sort);
        
        ResultSet rs = sqlConnector.query();
        try {
            for(int j=0;rs.next();++j) {
                if(j>=i)
                    if(j-i<c) items.add(new User(rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getBoolean(6)));
                    else break;
            }
            
        } catch(SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        sqlConnector.close();
        
        return items;
    }
    public int sizeOfFilter(String key, String info, String sort, String role) {
        int l = 0;
        
        switch(sort) {
            case "asc": sort = " order by `"+info+"` asc"; break;
            case "desc": sort = " order by `"+info+"` desc"; break;
            default: sort = ""; break;
        }
        
        key = " where lower(`"+info+"`) like lower('%"+key+"%')";
        
        switch(role) {
            case "customer": role = " and `role`=0"; break;
            case "seller": role = " and `role`=1"; break;
            default: role = ""; break;
        }
        
        SQLConnector sqlConnector = new SQLConnector();
        sqlConnector.statement("select * from `user`" + key + role + sort);
        
        ResultSet rs = sqlConnector.query();
        try {
            for(;rs.next();++l) {}
            
        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        sqlConnector.close();
        
        return l;
    }
    
    public boolean add(String username,String password,String name,String phone,String email,boolean role) {
        SQLConnector sqlConnector = new SQLConnector();
        PreparedStatement ps = sqlConnector.statement("insert into `user`(`username`,`password`,`name`,`phone`,`email`,`role`) values(?,?,?,?,?,?)");
        
        try {
            ps.setString(1,username);
            ps.setString(2,password);
            ps.setString(3,name);
            ps.setString(4,phone);
            ps.setString(5,email);
            ps.setBoolean(6,role);
            
        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        boolean r = sqlConnector.update();
        
        sqlConnector.close();
        
        return r;
    }
    public boolean edit(String username,String password,String name,String phone,String email,boolean role) {
        SQLConnector sqlConnector = new SQLConnector();
        PreparedStatement ps = sqlConnector.statement("update `user` set `password`=?,`name`=?,`phone`=?,`email`=?,`role`=? where `username`=?");
        
        try {
            ps.setString(1,password);
            ps.setString(2,name);
            ps.setString(3,phone);
            ps.setString(4,email);
            ps.setBoolean(5,role);
            ps.setString(6,username);
            
        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        boolean r = sqlConnector.update();
        
        sqlConnector.close();
        
        return r;
    }
    
    public boolean changeRole(String username,boolean role) {
        SQLConnector sqlConnector = new SQLConnector();
        PreparedStatement ps = sqlConnector.statement("update `user` set `role`=? where `username`=?");
        
        try {
            ps.setBoolean(1,role);
            ps.setString(2,username);
            
        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        boolean r = sqlConnector.update();
        
        sqlConnector.close();
        
        return r;
    }
    public boolean changePassword(String username,String password) {
        SQLConnector sqlConnector = new SQLConnector();
        PreparedStatement ps = sqlConnector.statement("update `user` set `password`=? where `username`=?");
        
        try {
            ps.setString(1,password);
            ps.setString(2,username);
            
        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        boolean r = sqlConnector.update();
        
        sqlConnector.close();
        
        return r;
    }
    public boolean editInfo(String username,String name,String phone,String email) {
        SQLConnector sqlConnector = new SQLConnector();
        PreparedStatement ps = sqlConnector.statement("update `user` set `name`=?,`phone`=?,`email`=? where `username`=?");
        
        try {
            ps.setString(1,name);
            ps.setString(2,phone);
            ps.setString(3,email);
            ps.setString(4,username);
            
        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        boolean r = sqlConnector.update();
        
        sqlConnector.close();
        
        return r;
    }
    
}
