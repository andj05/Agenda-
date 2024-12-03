import java.sql.Connection;
import java.sql.DriverManager;

public class ManejadorBD {
    String URl = "jdbc:mysql://localhost:3306/agenda";
    String user = "root";
    String pass = "";
    Connection connection;

    public ManejadorBD() {}

    public Connection conectar() throws Exception {
        Class.forName("com.mysql.cj.jdbc.Driver");
        connection = DriverManager.getConnection(URl, user, pass);
        return connection;
    }

    public void desconectar() {
        try {
            if (connection != null && !connection.isClosed()) {
                connection.close();
                System.out.println("Conexión cerrada correctamente.");
            }
        } catch (Exception e) {
            System.out.println("Error al cerrar la conexión: " + e.getMessage());
        }
    }
}
