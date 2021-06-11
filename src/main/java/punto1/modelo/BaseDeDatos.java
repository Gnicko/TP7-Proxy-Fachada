package punto1.modelo;

import java.util.Set;

public interface BaseDeDatos {
    public Persona personaPorId(int id);
    public Set<Telefono> obtenerTelefonos(int id);
}
