package dao;

import java.util.List;

public interface IDaoGenerico<T> {
    public boolean agregar(T obj);
    public boolean actualizar(T obj);
    public boolean eliminar(T obj);
    public List<T> listar();
    public List<T> listar(String texto);
    public int total();

}
