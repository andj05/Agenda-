import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ManejadorContacto {
    private ManejadorBD db;
    private Connection connection;

    public ManejadorContacto(ManejadorBD db) {
        this.db = db;
    }

    public void guardar(Contacto contacto) {
        try {
            connection = db.conectar();
            String query = "INSERT INTO contactos (nombre, apellidos, empresa, telefono, correo) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement pstatement = connection.prepareStatement(query);
            pstatement.setString(1, contacto.getNombre());
            pstatement.setString(2, contacto.getApellidos());
            pstatement.setString(3, contacto.getEmpresa());
            pstatement.setString(4, contacto.getTelefono());
            pstatement.setString(5, contacto.getCorreo());
            pstatement.execute();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<Contacto> obtener() {
        List<Contacto> listaContactos = new ArrayList<>();
        try {
            connection = db.conectar();
            String query = "SELECT id, nombre, apellidos, empresa, telefono, correo FROM contactos";
            Statement statement = connection.createStatement();
            ResultSet resultset = statement.executeQuery(query);

            while (resultset.next()) {
                listaContactos.add(new Contacto(
                    resultset.getInt("id"),
                    resultset.getString("nombre"),
                    resultset.getString("apellidos"),
                    resultset.getString("empresa"),
                    resultset.getString("telefono"),
                    resultset.getString("correo")
                ));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listaContactos;
    }

    public void actualizarCampo(int id, String campo, String nuevoValor) {
        try {
            connection = db.conectar();
            String query = "UPDATE contactos SET " + campo + " = ? WHERE id = ?";
            PreparedStatement pstatement = connection.prepareStatement(query);
            pstatement.setString(1, nuevoValor);
            pstatement.setInt(2, id);
            pstatement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void eliminar(int id) {
        try {
            connection = db.conectar();
            String query = "DELETE FROM contactos WHERE id = ?";
            PreparedStatement pstatement = connection.prepareStatement(query);
            pstatement.setInt(1, id);
            pstatement.execute();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
