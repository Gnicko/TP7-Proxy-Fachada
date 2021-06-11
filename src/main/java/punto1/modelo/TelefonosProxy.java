package punto1.modelo;

import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class TelefonosProxy implements Set<Telefono> {
    private int idPersona;
    private Set<Telefono> telefonos;
    private BaseDeDatos baseDeDatos;

    public TelefonosProxy(int id, BaseDeDatos baseDeDatos) {
        idPersona = id;
        telefonos = new HashSet<Telefono>();
        this.baseDeDatos = baseDeDatos;
    }

    @Override
    public <T> T[] toArray(T[] a) {
        telefonos = obtenerTelefonos(idPersona);
        return telefonos.toArray(a);
    }

    private Set<Telefono> obtenerTelefonos(int id) {
        return baseDeDatos.obtenerTelefonos(id);
    }

    @Override
    public int size() {
        return telefonos.size();
    }

    @Override
    public boolean isEmpty() {
        return telefonos.isEmpty();
    }

    @Override
    public boolean contains(Object o) {
        return telefonos.contains(o);
    }

    @Override
    public Iterator<Telefono> iterator() {
        return telefonos.iterator();
    }

    @Override
    public Object[] toArray() {
        return telefonos.toArray();
    }

    @Override
    public boolean add(Telefono telefono) {
        return telefonos.add(telefono);
    }

    @Override
    public boolean remove(Object o) {
        return telefonos.remove(o);
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        return telefonos.containsAll(c);
    }

    @Override
    public boolean addAll(Collection<? extends Telefono> c) {
        return telefonos.addAll(c);
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        return telefonos.retainAll(c);
    }


    @Override
    public boolean removeAll(Collection<?> c) {
        return telefonos.remove(c);
    }

    @Override
    public void clear() {
        telefonos.clear();
    }
}
