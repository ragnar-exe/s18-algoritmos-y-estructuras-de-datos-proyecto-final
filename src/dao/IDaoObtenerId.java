package dao;

import java.util.List;

public interface IDaoObtenerId<T> extends IDaoExtendido<T> {
    public int obtenerIdForeignKey(int id);
    public List<T> listar();
    public List<T> listar(String texto);
}
