/*
 * Database Connector
 */
package digitalhomeo;

import com.mysql.jdbc.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBC {

    public static Connection getConnection() throws SQLException {
        Connection connection = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/digihomeo?zeroDateTimeBehavior=convertToNull", "root", "");
        return connection;
    }

}
