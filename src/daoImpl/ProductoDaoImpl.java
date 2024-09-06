package daoImpl;

import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import model.Producto;
import dao.IDaoObtenerId;

public class ProductoDaoImpl implements IDaoObtenerId<Producto> {
    
    public ProductoDaoImpl() {
        
    }

    @Override
    public int obtenerId(String texto) {
        int id = -1;
        try {
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Advertencia Producto", JOptionPane.WARNING_MESSAGE);
        } finally {
            
        }
        return id;
    }

    @Override
    public String obtenerNombre(int id) {
        String nombre = "";
        try {
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Advertencia Producto", JOptionPane.WARNING_MESSAGE);
        } finally {
            
        }
        return nombre;
    }

    @Override
    public boolean agregar(Producto obj) {
        try {
            
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Advertencia Producto", JOptionPane.WARNING_MESSAGE);
            return false;
        } finally {
            
        }
        return false;
    }

    @Override
    public boolean actualizar(Producto obj) {
        try {
            
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Advertencia Producto", JOptionPane.WARNING_MESSAGE);
            return false;
        } finally {
            
        }
        return false;
    }

    @Override
    public boolean eliminar(Producto obj) {
        try {
            
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Advertencia Producto", JOptionPane.WARNING_MESSAGE);
            return false;
        } finally {
            
        }
        return false;
    }

    @Override
    public List<Producto> listar() {
        List<Producto> lista = new ArrayList<>();
        try {
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Advertencia Producto", JOptionPane.WARNING_MESSAGE);
            lista = null;
        } finally {
            
        }
        return lista;
    }

    @Override
    public List<Producto> listar(String texto) {
        List<Producto> lista = new ArrayList<>();
        try {
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Advertencia Producto", JOptionPane.WARNING_MESSAGE);
            lista = null;
        } finally {
            
        }
        return lista;
    }

    @Override
    public int total() {
        int totalRegistros = 0;
        try {
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Advertencia Producto", JOptionPane.WARNING_MESSAGE);
        } finally {
            
        }
        return totalRegistros;
    }

    @Override
    public int obtenerIdForeignKey(int id) {
        int idCategoria = -1;
        try {
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Advertencia Producto Categoria", JOptionPane.WARNING_MESSAGE);
        } finally {
            
        }
        return idCategoria;
    }

}
