package daoImpl;

import dao.IDaoExtendido;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import model.Marca;

public class MarcaDaoImpl implements IDaoExtendido<Marca> {

    public MarcaDaoImpl() {
    }

    @Override
    public int obtenerId(String texto) {
        int id = -1;
        try {
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Advertencia Marca", JOptionPane.WARNING_MESSAGE);
        } finally {
            
        }
        return id;
    }

    @Override
    public String obtenerNombre(int id) {
        String nombre = "";
        try {
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Advertencia Marca", JOptionPane.WARNING_MESSAGE);
        } finally {
            
        }
        return nombre;
    }

    @Override
    public boolean agregar(Marca obj) {
        try {
            
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Advertencia Marca", JOptionPane.WARNING_MESSAGE);
            return false;
        } finally {
            
        }
        return false;
    }

    @Override
    public boolean actualizar(Marca obj) {
        try {
            
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Advertencia Marca", JOptionPane.WARNING_MESSAGE);
            return false;
        } finally {
            
        }
        return false;
    }

    @Override
    public boolean eliminar(Marca obj) {
        try {
            
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Advertencia Marca", JOptionPane.WARNING_MESSAGE);
            return false;
        } finally {
            
        }
        return false;
    }

    @Override
    public List<Marca> listar() {
        List<Marca> lista = new ArrayList<>();
        try {
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Advertencia Marca", JOptionPane.WARNING_MESSAGE);
            lista = null;
        } finally {
            
        }
        return lista;
    }

    @Override
    public List<Marca> listar(String texto) {
        List<Marca> lista = new ArrayList<>();
        try {
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(),"Advertencia Marca", JOptionPane.WARNING_MESSAGE);
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
            JOptionPane.showMessageDialog(null, e.getMessage(),"Advertencia Marca", JOptionPane.WARNING_MESSAGE);
        } finally {
            
        }
        return totalRegistros;
    }

}
