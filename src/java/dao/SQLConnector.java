package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Lenvo
 */
public class SQLConnector {
    private static final String host = "localhost";
    private static final int port = 3306;
    private static final String database = "bookingbusticketsystem";
    private static final String user = "root";
    private static final String password = "";
    
    private Connection cn;
    private PreparedStatement ps;
    private ResultSet rs;

    
    public SQLConnector() {
        cn = null;
        ps = null;
        rs = null;
        
        try {
            // MySQL Connection
            Class.forName("com.mysql.jdbc.Driver");  
            cn = DriverManager.getConnection("jdbc:mysql://"+host+":"+port+"/"+database,user,password);
            
//            // Derby Connection
//            Class.forName("org.apache.derby.jdbc.ClientDriver");
//            cn = DriverManager.getConnection("jdbc:derby://"+host+":"+port+"/"+database,user,password);
            
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(SQLConnector.class.getName()).log(Level.SEVERE,null,ex);
        }
        
    }
    
    public PreparedStatement statement(String s) {
        try {
            ps = cn.prepareStatement(s);
            
        } catch (SQLException ex) {
            Logger.getLogger(SQLConnector.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return ps;
    }
    
    public ResultSet query() {
        try {
            rs = ps.executeQuery();
            
        } catch (SQLException ex) {
            Logger.getLogger(SQLConnector.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return rs;
    }
    
    public boolean update() {
        boolean r = false;
        try {
            r = ps.executeUpdate()>0;
            
        } catch(SQLException ex) {
            Logger.getLogger(SQLConnector.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return r;
    }
    
    public void close() {
        if(rs!=null) try { rs.close(); } catch(SQLException ex) {} finally { rs = null; }
        if(ps!=null) try { ps.close(); } catch(SQLException ex) {} finally { ps = null; }
        if(cn!=null) try { cn.close(); } catch(SQLException ex) {} finally { cn = null; }
    }
    
}
