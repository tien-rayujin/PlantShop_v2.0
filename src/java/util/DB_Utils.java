
package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author RaeKyo
 */
public class DB_Utils {
    
    public static final Connection makeConnection() throws Exception{
        Connection con = null;
        String driver = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
        String protocol = "jdbc";
        String server = "sqlserver";

        String ip = "localhost";
        String port = "1433";
        String instanceName = "RAEKYO\\SQLEXPRESS2019";
        String DBname = "PlantShop";

        String user = "sa";
        String password = "12345";

        String url = protocol + ":" + server + "://" + ip + ":" + port + ";instanceName=" + instanceName + ";databaseName=" + DBname + ";user=" + user + ";password=" + password;

        Class.forName(driver);
        con = DriverManager.getConnection(url);
        
        return con;
    }
    
    public static final String getUrl(){
        String driver = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
        String protocol = "jdbc";
        String server = "sqlserver";
        
        String ip = "localhost";
        String port = "1433";
        String instanceName = "RAEKYO\\SQLEXPRESS2019";
        String DBname = "PlantShop";
        
        String user = "sa";
        String password = "12345";
        
        String url = protocol + ":" + server + "://" + ip + ":" + port + ";instanceName=" + instanceName +  ";databaseName=" + DBname + ";user=" + user +";password=" + password;
        
        return url;
    }
}
