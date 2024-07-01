package utils;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBUtil {
    private static final String URL = "jdbc:mysql://localhost:3306/HealthManagementSystem?useUnicode=true&characterEncoding=utf8&useSSL=false";
    //private static final String URL = "jdbc:sinodbms-sqli://192.168.81.130:15151/testdb:SINODBMSSERVER=sinodb;NEWCODESET=utf8,8859-1,819;DB_LOCALE=en_US.8859-1;CLIENT_LOCALE=en_US.8859-1";
    private static final String USER = "root";
    //private static final String USER = "sinodbms";
    private static final String PASSWORD = "qzh040820";
    //private static final String PASSWORD = "Yzj151225";


    public static Connection getConnection() throws SQLException {
        try {
            Class.forName("com.sinodbms.jdbc.IfxDriver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

}