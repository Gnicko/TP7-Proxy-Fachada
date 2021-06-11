package punto1.persistencia;

import punto1.modelo.BaseDeDatos;
import punto1.modelo.Persona;
import punto1.modelo.Telefono;
import punto1.modelo.TelefonosProxy;

import java.sql.*;
import java.util.HashSet;
import java.util.Set;

public class PersonaDao implements BaseDeDatos {
    private Connection obtenerConexion() throws SQLException {
        return DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/tp7", "root", "");
    }

    public Persona personaPorId(int id) {

        try (Connection conn = obtenerConexion();
             PreparedStatement statement =
                     conn.prepareStatement("select * from personas where id=?");) {
            statement.setInt(1, id);
            ResultSet result = statement.executeQuery();
            Set<Telefono> telefonos = new TelefonosProxy(id, this);
            String nombrePersona = null;
            while (result.next()) {
                nombrePersona = result.getString(1);
            }
            return new Persona(id, nombrePersona, telefonos);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Set<Telefono> obtenerTelefonos(int id) {
        try (Connection conn = obtenerConexion();
             PreparedStatement statement =
                     conn.prepareStatement("select * from telefonos where idPersona=?");) {
            statement.setInt(1, id);
            ResultSet result = statement.executeQuery();
            Set<Telefono> telefonos = new HashSet<Telefono>();
            while (result.next()) {
                telefonos.add(new Telefono(result.getString(2)));
            }
            return telefonos;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
