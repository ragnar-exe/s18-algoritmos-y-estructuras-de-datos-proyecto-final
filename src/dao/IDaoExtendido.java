package dao;

public interface IDaoExtendido<T> extends IDaoGenerico<T>{
    public int obtenerId(String texto);
    public String obtenerNombre(int id);
}
