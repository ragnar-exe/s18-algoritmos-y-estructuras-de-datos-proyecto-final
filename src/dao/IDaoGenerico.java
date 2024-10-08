package dao;

public interface IDaoGenerico<T> {
    public int obtenerUltimoId();
    public boolean agregar(T obj);
    public boolean actualizar(T obj);
    public boolean eliminar(T obj);
    public void guardarEnArchivo();
    public void cargarDatos();
    public int total();
}
