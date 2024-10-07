package daoImpl;

import dao.IDaoExtendido;
import java.util.ArrayList;
import java.util.List;
import model.Categoria;

public class CategoriaDaoImpl implements IDaoExtendido<Categoria> {
    ArrayList<Categoria> categorias = new ArrayList<>();
    int[] idCategoria = new int[200];

    public CategoriaDaoImpl() {
    }

    @Override
    public int obtenerId(String texto) {
        int id = -1;
        for (Categoria categoria : categorias) {
            if (categoria.getNombre().equalsIgnoreCase(texto)) {
                id = categoria.getIdCategoria();
            }
        }
        return id;
    }

    @Override
    public String obtenerNombre(int id) {
        String nombre = "";
        for (Categoria categoria : categorias) {
            if (categoria.getIdCategoria() == id) {
                nombre = categoria.getNombre();
            }
        }
        return nombre;
    }

    @Override
    public int obtenerIdAutoincrement() {
        int codigo = 0;
        for (int i = 0; i < idCategoria.length; i++) {
            if (idCategoria[i] != 0) {
                codigo = idCategoria[i];
            }
        }
        return codigo + 1;
    }

    @Override
    public void agregarCodigo(int codigo) {
        for (int i = 0; i < idCategoria.length; i++) {
            if (idCategoria[i] == 0) {
                idCategoria[i] = codigo;
                return;
            }
        }
    }

    @Override
    public boolean agregar(Categoria obj) {
        return categorias.add(obj);
    }

    @Override
    public boolean actualizar(Categoria obj) {
        for (Categoria categoria : categorias) {
            if (categoria.getNombre().equalsIgnoreCase(obj.getNombre()) && categoria.getIdCategoria() != obj.getIdCategoria()) {
                return false; 
            }
        }
        
        for (int i = 0; i < categorias.size(); i++) {
            if (categorias.get(i).getIdCategoria() == obj.getIdCategoria()) {
                categorias.set(i, obj);
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean eliminar(Categoria obj) {
         return categorias.remove(obj);
    }

    public List<Categoria> listar() {
        return categorias;
    }

    @Override
    public int total() {
        return categorias.size();
    }
}
