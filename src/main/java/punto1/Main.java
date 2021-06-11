package punto1;

import punto1.modelo.Persona;
import punto1.modelo.Telefono;
import punto1.persistencia.PersonaDao;

public class Main {
    public static void main(String args[]) {
    PersonaDao dao = new PersonaDao();
    Persona p = dao.personaPorId(1);
    System.out.println(p.nombre());
    for (Telefono telefono : p.telefonos()) {
        System.out.println(telefono);
    }
}

}
