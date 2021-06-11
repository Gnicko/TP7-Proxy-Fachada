package punto2;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FacadeJdbc implements DBFacade {
    private Connection connection;

    @Override
    public void open() {
        try {
            connection = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/tp7", "root", "");
        } catch (SQLException e) {
            throw new RuntimeException("No se pudo hacer un conexion con la base de datos.", e);
        }
    }

    @Override
    public List<Map<String, String>> queryResultAsAsociation(String sql) {
        List<Map<String, String>> list = new ArrayList<>();

        try {

            PreparedStatement statement = connection.prepareStatement(sql);

            ResultSet result = statement.executeQuery();

            while (result.next()) {

                ResultSetMetaData columasDeLaTabla = result.getMetaData();
                Map<String, String> filasDeTabla = new HashMap<>();
                for (int i = 1; i <= columasDeLaTabla.getColumnCount(); i++) {

                    filasDeTabla.put(columasDeLaTabla.getColumnName(i), result.getString(i));
                }
                list.add(filasDeTabla);

            }

            result.close();
        } catch (SQLException e) {
            throw new RuntimeException("No se pudo obtener la lista de filas en un map", e);
        }
        return list;
    }

    @Override
    public List<String[]> queryResultAsArray(String sql) {
        List<String[]> list = new ArrayList<>();
        try {
            PreparedStatement statement = connection.prepareStatement(sql);

            ResultSet result = statement.executeQuery();

            while (result.next()) {
                ResultSetMetaData clumnasDeTabla = result.getMetaData();
                String[] listaStrings = new String[clumnasDeTabla.getColumnCount()];
                for (int i = 0; i < clumnasDeTabla.getColumnCount(); i++) {
                    listaStrings[i] = result.getString(i + 1);

                }
                list.add(listaStrings);
            }

            result.close();
        } catch (SQLException e) {
            throw new RuntimeException("No se pudo obtener la lista de filas en un arreglo");
        }
        return list;
    }

    @Override
    public void close() {
        try {
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException("No se pudo cerrar la conexion con la base de datos.", e);
        }
    }
}
