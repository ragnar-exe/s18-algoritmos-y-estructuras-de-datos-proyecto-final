package daoImpl;

import dao.IDaoExtendido;
import java.util.List;
import javax.swing.JOptionPane;
import model.Persona;

public class PersonaDaoImpl {

    public PersonaDaoImpl() {
    }

//    @Override
    public int obtenerId(String texto) {
        int id = -1;
        try {
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Advertencia Persona", JOptionPane.WARNING_MESSAGE);
        } finally {
            
        }
        return id;
    }

//    @Override
    public String obtenerNombre(int id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

//    @Override
    public boolean agregar(Persona obj) {
        try {
           
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Advertencia Persona", JOptionPane.WARNING_MESSAGE);
            return false;
        } finally {
            
        }
        return false;
    }

//    @Override
    public boolean actualizar(Persona obj) {
        try {
            
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Advertencia Persona", JOptionPane.WARNING_MESSAGE);
            return false;
        } finally {
            
        }
        return false;
    }

//    @Override
    public boolean eliminar(Persona obj) {
        try {
           
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Advertencia Persona", JOptionPane.WARNING_MESSAGE);
            return false;
        } finally {
            
        }
        return false;
    }

//    @Override
    public List<Persona> listar() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

//    @Override
    public List<Persona> listar(String texto) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

//    @Override
    public int total() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
