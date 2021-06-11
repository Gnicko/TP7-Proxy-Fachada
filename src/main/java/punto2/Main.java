package punto2;

import java.util.List;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        FacadeJdbc fachada = new FacadeJdbc();
        fachada.open();

        List<String[]> lista = fachada.queryResultAsArray("SELECT * FROM personas");

        for (String[] arregloDeValores : lista) {
            for (int i = 0; i < arregloDeValores.length; i++) {
                System.out.print(arregloDeValores[i] + " ");
            }
            System.out.println();
        }


        List<Map<String, String>> listaMap = fachada.queryResultAsAsociation(
                "SELECT * FROM telefonos join personas on (telefonos.idPersona=personas.id)");
        for (Map<String, String> mapaDeValores : listaMap) {
            System.out.println(mapaDeValores);

        }
    }
}
