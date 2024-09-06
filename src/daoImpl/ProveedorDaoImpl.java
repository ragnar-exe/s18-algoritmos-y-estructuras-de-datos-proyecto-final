package daoImpl;

import java.util.List;
import javax.swing.JOptionPane;
import model.Proveedor;
import dao.IDaoObtenerId;
import java.util.ArrayList;

public class ProveedorDaoImpl implements IDaoObtenerId<Proveedor> {

    public ProveedorDaoImpl() {

    }
    
    @Override
    public int obtenerId(String texto) {
        int id = -1;
        try {
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Advertencia Proveedor", JOptionPane.WARNING_MESSAGE);
        } finally {
            
        }
        return id;
    }

    @Override
    public String obtenerNombre(int id) {
        String nombre = "";
        try {
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Advertencia Proveedor", JOptionPane.WARNING_MESSAGE);
        } finally {
            
        }
        return nombre;
    }
    
    @Override
    public boolean agregar(Proveedor obj) {
        try {
            
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Advertencia Proveedor", JOptionPane.WARNING_MESSAGE);
            return false;
        } finally {
            
        }
        return false;
    }

    @Override
    public boolean actualizar(Proveedor obj) {
        try {
        
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Advertencia Proveedor", JOptionPane.WARNING_MESSAGE);
            return false;
        } finally {
            
        }
        return false;
    }

    @Override
    public boolean eliminar(Proveedor obj) {
        try {
            
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Advertencia Proveedor", JOptionPane.WARNING_MESSAGE);
            return false;
        } finally {
            
        }
        return false;
    }

    @Override
    public List<Proveedor> listar() {
        List<Proveedor> lista = new ArrayList<>();
        try {
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Advertencia Proveedor", JOptionPane.WARNING_MESSAGE);
            lista = null;
        } finally {
            
        }
        return lista;
    }

    @Override
    public List<Proveedor> listar(String texto) {
        List<Proveedor> lista = new ArrayList<>();
        try {
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Advertencia Proveedor", JOptionPane.WARNING_MESSAGE);
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
            JOptionPane.showMessageDialog(null, e.getMessage(), "Advertencia Proveedor", JOptionPane.WARNING_MESSAGE);
        } finally {
            
        }
        return totalRegistros;
    }

    @Override
    public int obtenerIdForeignKey(int id) {
        int idPersona = -1;
        try {
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Advertencia Proveedor Persona", JOptionPane.WARNING_MESSAGE);
        } finally {
            
        }
        return idPersona;
    }
    
}
