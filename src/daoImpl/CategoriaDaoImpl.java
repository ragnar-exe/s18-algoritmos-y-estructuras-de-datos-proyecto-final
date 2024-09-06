package daoImpl;

import dao.IDaoExtendido;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import model.Categoria;

public class CategoriaDaoImpl implements IDaoExtendido<Categoria> {

    
    public CategoriaDaoImpl() {
        
    }

    @Override
    public int obtenerId(String texto) {
        int id = -1;
        try {
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Advertencia Categoria", JOptionPane.WARNING_MESSAGE);
        } finally {
            
        }
        return id;
    }

    @Override
    public String obtenerNombre(int id) {
        String nombre = "";
        try {
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Advertencia Categoria", JOptionPane.WARNING_MESSAGE);
        } finally {
            
        }
        return nombre;
    }

    @Override
    public boolean agregar(Categoria obj) {
        try {
            
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Advertencia Categoria", JOptionPane.WARNING_MESSAGE);
            return false;
        } finally {
            
        }
        return false;
    }

    @Override
    public boolean actualizar(Categoria obj) {
        try {
            
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Advertencia Categoria", JOptionPane.WARNING_MESSAGE);
            return false;
        } finally {
            
        }
        return false;
    }

    @Override
    public boolean eliminar(Categoria obj) {
        try {
            
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Advertencia Categoria", JOptionPane.WARNING_MESSAGE);
            return false;
        } finally {
            
        }
        return false;
    }

    @Override
    public List<Categoria> listar() {
        List<Categoria> lista = new ArrayList<>();
        try {
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Advertencia Categoria", JOptionPane.WARNING_MESSAGE);
            lista = null;
        } finally {
           
        }
        return lista;
    }

    @Override
    public List<Categoria> listar(String texto) {
        List<Categoria> lista = new ArrayList<>();
        try {
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Advertencia Color", JOptionPane.WARNING_MESSAGE);
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
            JOptionPane.showMessageDialog(null, e.getMessage(), "Advertencia Categoria", JOptionPane.WARNING_MESSAGE);
            return totalRegistros;
        } finally {
            
        }
        return totalRegistros;
    }

}
