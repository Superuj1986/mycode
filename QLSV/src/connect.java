import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import com.microsoft.sqlserver.jdbc.SQLServerDataSource;
public class connect {
    public Connection getConnection(){
        Connection conn = null;
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDataSource");
            String url = "jdbc:sqlserver://THECONQUERER\\SUPERUJ:1433;"
                    + "databaseName=QLSV;"
                    + "encrypt = False";
            conn = DriverManager.getConnection(url, "sa", "jffggj");
            System.out.println("Connect successfully");
        }catch (Exception e){
            System.out.println("Lỗi" + e.getMessage());
        }
        return conn;
    }
}
