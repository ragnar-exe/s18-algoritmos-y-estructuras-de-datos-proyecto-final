package daoImpl;

import dao.IDaoExtendido;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import model.Categoria;

//public class CategoriaDaoImpl implements IDaoExtendido<Categoria> {
public class CategoriaDaoImpl implements IDaoExtendido<Categoria> {
//funcion listar que retorne toda la lista de categorias (Array lIzt)   
    ArrayList<Categoria> categorias=new ArrayList<>();
    int[] idCategoria = new int[200];
    
    public CategoriaDaoImpl() {
       
    }

    public List<Categoria> listar() {
        return categorias;
    }
    
    @Override
    public int obtenerId(String texto) {
       for (Categoria categoria : categorias) {
            if (categoria.getNombre().equalsIgnoreCase(texto)) {
                return categoria.getIdCategoria();
            }
        }
        return -1; // ID no encontrado
    }

    @Override
    public String obtenerNombre(int id) {
        for (Categoria categoria : categorias) {
            if (categoria.getIdCategoria()== id) {
                return categoria.getNombre();
            }
        }
        return null; // Nombre no encontrado
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
 
    // Verificar si ya existe una categoría con el mismo nombre
    for (Categoria categoria : categorias) {
        if (categoria.getNombre().equalsIgnoreCase(obj.getNombre()) && categoria.getIdCategoria()!= obj.getIdCategoria()) {
            return false; // Ya existe una categoría con el mismo nombre
        }
    }
    // Actualizar la categoría si no hay conflictos de nombre
    for (int i = 0; i < categorias.size(); i++) {
        if (categorias.get(i).getIdCategoria()== obj.getIdCategoria()) {
            categorias.set(i, obj);
            return true;
        }
    }
    return false; // Categoría no encontrada
 }

    @Override
    public boolean eliminar(Categoria obj) {
          return categorias.remove(obj);
    }

    @Override
    public int total() {  
         return categorias.size();
    }
}
