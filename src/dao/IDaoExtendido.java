package dao;

import java.util.List;

public interface IDaoExtendido<T> extends IDaoGenerico<T>{
    public int obtenerId(String texto);
    public String obtenerNombre(int id);
}
