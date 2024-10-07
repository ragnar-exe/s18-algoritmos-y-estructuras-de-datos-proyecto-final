package daoImpl;

import dao.IDaoExtendido;
import java.util.LinkedList;
import model.Producto;


public class ProductoDaoImpl implements IDaoExtendido<Producto>{
    LinkedList<Producto> productos = new LinkedList<>();
    int[] idProductos = new int[200];
    public ProductoDaoImpl() {
        
    }

    @Override
    public int obtenerId(String texto) {
        int id = -1;
        for (Producto producto : productos) {
            if (producto.getNombre().equalsIgnoreCase(texto)) {
                id = producto.getIdProducto();
            }
        }
        return id;
    }

    @Override
    public String obtenerNombre(int id) {
        String nombre = "";
        for (Producto producto : productos) {
            if (producto.getIdProducto() == id) {
                nombre = producto.getNombre();
            }
        }
        return nombre;
    }

    @Override
    public int obtenerIdAutoincrement() {
        int codigo = 0;
        for (int i = 0; i < idProductos.length; i++) {
            if (idProductos[i] != 0) {
                codigo = idProductos[i];
            }
        }
        return codigo + 1;
    }

    @Override
    public void agregarCodigo(int codigo) {
        for (int i = 0; i < idProductos.length; i++) {
            if (idProductos[i] == 0) {
                idProductos[i] = codigo;
                return;
            }
        }
    }

    @Override
    public boolean agregar(Producto obj) {
        return productos.add(obj);
    }

    @Override
    public boolean actualizar(Producto obj) {
        for (Producto producto : productos) {
            if (producto.getNombre().equalsIgnoreCase(obj.getNombre()) && producto.getIdProducto()!= obj.getIdProducto()) {
                return false; 
            }
        }
        
        for (int i = 0; i < productos.size(); i++) {
            if (productos.get(i).getIdProducto() == obj.getIdProducto()) {
                productos.set(i, obj);
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean eliminar(Producto obj) {
       return productos.remove(obj);
    }

    @Override
    public int total() {
        return productos.size();
    }

}
