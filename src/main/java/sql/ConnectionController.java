package sql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionController {
    private static Connection conn;

    public static Connection getConnection(){
        //Create a connection
        try {
            if (conn==null || conn.isClosed()) {
                conn = DriverManager.getConnection("jdbc:mysql://db.diplomportal.dk/s190608?"
                        + "user=s190608&password=5USibIiZSIh7RR85vBFgH");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return conn;
    }

    private ConnectionController(){

    }
}
