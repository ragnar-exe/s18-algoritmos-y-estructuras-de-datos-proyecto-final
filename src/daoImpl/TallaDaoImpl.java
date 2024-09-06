package daoImpl;

import dao.IDaoExtendido;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import model.Talla;

public class TallaDaoImpl implements IDaoExtendido<Talla> {

    public TallaDaoImpl() {
    }
    
    @Override
    public int obtenerId(String texto) {
        int id = -1;
        try {
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Advertencia Talla", JOptionPane.WARNING_MESSAGE);
        } finally {
            
        }
        return id;
    }

    @Override
    public String obtenerNombre(int id) {
        String nombre = "";
        try {
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Advertencia Talla", JOptionPane.WARNING_MESSAGE);
        } finally {
            
        }
        return nombre;
    }

    @Override
    public boolean agregar(Talla obj) {
        try {
            //call
            
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Advertencia Talla", JOptionPane.WARNING_MESSAGE);
            return false;
        } finally {
            
        }
        return false;
    }

    @Override
    public boolean actualizar(Talla obj) {
        try {
            
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Advertencia Talla", JOptionPane.WARNING_MESSAGE);
            return false;
        } finally {
            
        }
        return false;
    }

    @Override
    public boolean eliminar(Talla obj) {
        try {
            return true;
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Advertencia Talla", JOptionPane.WARNING_MESSAGE);
            return false;
        } finally {
            
        }
    }

    @Override
    public List<Talla> listar() {
        List<Talla> lista = new ArrayList<>();
        try {
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Advertencia Talla", JOptionPane.WARNING_MESSAGE);
            lista = null;
        } finally {
            
        }
        return lista;
    }

    @Override
    public List<Talla> listar(String texto) {
        List<Talla> lista = new ArrayList<>();
        try {
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(),"Advertencia Talla", JOptionPane.WARNING_MESSAGE);
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
            JOptionPane.showMessageDialog(null, e.getMessage(),"Advertencia Talla", JOptionPane.WARNING_MESSAGE);
        } finally {
            
        }
        return totalRegistros;
    }
    
}
