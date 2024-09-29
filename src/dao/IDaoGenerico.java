package dao;

import java.util.List;

public interface IDaoGenerico<T> {
    public int obtenerIdAutoincrement();
    public void agregarCodigo(int codigo);
    public boolean agregar(T obj);
    public boolean actualizar(T obj);
    public boolean eliminar(T obj);
    public int total();

}
