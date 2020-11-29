package nish.jpmc.db.connpool.app;


import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Properties;

class MysqlConn {

    static Properties properties = new Properties();

    static {

        try (InputStream inputStream = ClassLoader.getSystemClassLoader().getResourceAsStream("app.properties")) {
            properties.load(inputStream);
        } catch (Exception e) {
            System.out.println("unable to load prop file");
            e.printStackTrace();
        }
    }

    public static void main(String args[]) {

        String driver = properties.getProperty("db.driver");
        String url = properties.getProperty("db.jdbc.url");
        String userName = properties.getProperty("db.username");
        String pwd = properties.getProperty("db.password");

        try {
            Class.forName(driver);
            Connection con = DriverManager.getConnection(
                    url, userName, pwd);

            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("select * from contact");
            while (rs.next())
                System.out.println(rs.getInt(1) + "  " + rs.getString(2)
                        + "  " + rs.getString(3)
                        + "  " + rs.getString(4) + "  " + rs.getString(5));
            con.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}

