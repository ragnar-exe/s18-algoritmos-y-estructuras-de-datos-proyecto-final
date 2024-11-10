package daoImpl;

import dao.IDaoExtendido;
import javax.swing.JOptionPane;
import model.Contiene;
import model.Nodo;

public class ContieneDaoImpl implements IDaoExtendido<Contiene>{
    private static final String FILE_CONTIENE = "contienes.txt";
    private static final String FILE_IDSCONTIENE = "idscontienes.txt";
    public Nodo inicio;
    public Nodo fin;
    
    public ContieneDaoImpl() {
        this.inicio = null;
        this.fin = null;
        cargarDatos();
    }

    @Override
    public int obtenerId(String texto) {
        int id = -1;
        
        return id;
    }

    @Override
    public String obtenerNombre(int id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public int obtenerUltimoId() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public boolean agregar(Contiene obj) {
        // agregar al final
        boolean guardar = false;
        if (inicio == null) {
            inicio = new Nodo(null,null, obj);
            fin = inicio;
            guardar = true;
        } else {
            Nodo nuevo = new Nodo(null, null, obj);
            fin.setSiguiente(nuevo);
            fin = nuevo;
            guardar = true;
        }
        return guardar;
    }

    @Override
    public boolean actualizar(Contiene obj) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public boolean eliminar(Contiene obj) {
        boolean res = false;
        Nodo temp = inicio;
        while (temp != null) {
            if (temp.getContiene().getIdContiene() == obj.getIdContiene()) {
                if (temp == inicio) {
                    res = eliminarInicio();
                } else if (temp == fin) {
                    res = eliminarFinal();
                } else {
                    temp.getAnterior().setSiguiente(temp.getSiguiente());
                    temp.getSiguiente().setAnterior(temp.getAnterior());
                    res = true;
                }
            }
            temp = temp.getSiguiente();
        }
        return res;
    }

    public boolean eliminarInicio() {
        inicio = inicio.getSiguiente();
        boolean res = false;
        if (inicio != null) {
            inicio.setAnterior(null);
            res = true;
        } else {
            fin = null;
            res = true;
        }
        return res;
    }

    public boolean eliminarFinal() {
        fin = fin.getAnterior();
        boolean res = false;
        if (fin != null) {
            fin.setSiguiente(null);
            res = true;
        } else {
            inicio = null;
            res = true;
        }
        return res;
    }

    @Override
    public void guardarEnArchivo() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void cargarDatos() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public int total() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
}
