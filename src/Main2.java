import java.sql.Connection;
import java.sql.DriverManager;

public class Main2 {
    public static void main(String[] args) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver"); // opcional pero útil para depurar

            //tring url = "jdbc:mysql://34.202.137.5:3306/mysql";
            String url = "jdbc:postgresql://34.202.137.5:5432/postgres";

            Connection conn = DriverManager.getConnection(url, "alumno", "alumno123");

            System.out.println("Conexión OK");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
