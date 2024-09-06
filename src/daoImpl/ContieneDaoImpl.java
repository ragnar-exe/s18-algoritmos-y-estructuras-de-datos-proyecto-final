package daoImpl;

import dao.IDaoGenerico;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import model.Contiene;

public class ContieneDaoImpl implements IDaoGenerico<Contiene>{


    public ContieneDaoImpl() {
        
    }
    @Override
    public boolean agregar(Contiene obj) {
        try {

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Advertencia Contiene", JOptionPane.WARNING_MESSAGE);
            return false;
        } finally {
            
        }
        return false;
    }

    @Override
    public boolean actualizar(Contiene obj) {
        try {
            
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Advertencia Contiene", JOptionPane.WARNING_MESSAGE);
            return false;
        } finally {
            
        }
        return false;
    }

    @Override
    public boolean eliminar(Contiene obj) {
        try {
            
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Advertencia Contiene", JOptionPane.WARNING_MESSAGE);
            return false;
        } finally {
            
        }
        return false;
    }

    @Override
    public List<Contiene> listar() {
        List<Contiene> lista = new ArrayList<>();
        try {
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Advertencia Talla", JOptionPane.WARNING_MESSAGE);
            lista = null;
        } finally {
            
        }
        return lista;
    }

    @Override
    public List<Contiene> listar(String texto) {
        List<Contiene> lista = new ArrayList<>();
        try {
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(),"Advertencia Contiene", JOptionPane.WARNING_MESSAGE);
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
            JOptionPane.showMessageDialog(null, e.getMessage(),"Advertencia Contiene", JOptionPane.WARNING_MESSAGE);
        } finally {
            
        }
        return totalRegistros;
    }
    
}
