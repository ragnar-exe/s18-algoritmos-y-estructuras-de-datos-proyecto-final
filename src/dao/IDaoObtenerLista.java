package dao;

import java.util.List;

public interface IDaoObtenerLista<T> extends IDaoExtendido<T> {
    public List<T> listar();
    public List<T> listar(String texto);
    public List<T> listarOrdenarAscendete();
    public List<T> listarOrdenarDescendete();
}
