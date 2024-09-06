package daoImpl;

import dao.IDaoExtendido;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import model.Color;

public class ColorDaoImpl implements IDaoExtendido<Color> {

    public ColorDaoImpl() {
    }

    @Override
    public int obtenerId(String texto) {
        int id = -1;
        try {
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Advertencia Color", JOptionPane.WARNING_MESSAGE);
        } finally {
        
        }
        return id;
    }

    @Override
    public String obtenerNombre(int id) {
        String nombre = "";
        try {
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Advertencia Color", JOptionPane.WARNING_MESSAGE);
        } finally {
            
        }
        return nombre;
    }

    @Override
    public boolean agregar(Color obj) {
        try {
            
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Advertencia Color", JOptionPane.WARNING_MESSAGE);
            return false;
        } finally {
            
        }
        return false;
    }

    @Override
    public boolean actualizar(Color obj) {
        try {
            
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Advertencia Color", JOptionPane.WARNING_MESSAGE);
            return false;
        } finally {
            
        }
        return false;
    }

    @Override
    public boolean eliminar(Color obj) {
        try {
            
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Advertencia Color", JOptionPane.WARNING_MESSAGE);
            return false;
        } finally {
            
        }
        return false;
    }

    @Override
    public List<Color> listar() {
        List<Color> lista = new ArrayList<>();
        try {
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Advertencia Color", JOptionPane.WARNING_MESSAGE);
            lista = null;
        } finally {
            
        }
        return lista;
    }

    @Override
    public List<Color> listar(String texto) {
        List<Color> lista = new ArrayList<>();
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
            JOptionPane.showMessageDialog(null, e.getMessage(), "Advertencia Color", JOptionPane.WARNING_MESSAGE);
        } finally {
            
        }
        return totalRegistros;
    }

}
