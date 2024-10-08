package daoImpl;

import dao.IDaoExtendido;
import model.Color;

public class ColorDaoImpl implements IDaoExtendido<Color> {
    Color[] colores = new Color[100];
    int[] idColores = new int[colores.length * 2];
    
    public ColorDaoImpl() {
    }

    @Override
    public int obtenerUltimoId() {
        int codigo = 0;
        for (int i = 0; i < idColores.length; i++) {
            if (idColores[i] != 0) {
                codigo = idColores[i];
            }
        }
        return codigo + 1;
    }

    @Override
    public int obtenerId(String texto) {
        int id = -1;
        for (int i = 0; i < colores.length; i++) {
            if (colores[i] != null && colores[i].getNombre().equalsIgnoreCase(texto)) {
                id = colores[i].getIdColor();
            }
        }
        return id;
    }

    @Override
    public String obtenerNombre(int id) {
        String nombre = "";
        for (int i = 0; i < colores.length; i++) {
            if (colores[i].getIdColor() == id) {
                nombre = colores[i].getNombre();
            }
        }
        return nombre;
    }

    @Override
    public boolean agregar(Color obj) {
        for (int i = 0; i < colores.length; i++) {
            if (colores[i] == null) {
                colores[i] = obj;
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean actualizar(Color obj) {
        for (int i = 0; i < colores.length; i++) {
            if (colores[i] != null && colores[i].getNombre().equalsIgnoreCase(obj.getNombre()) && colores[i].getIdColor() != obj.getIdColor()) {
                return false; 
            }
        }

        for (int i = 0; i < colores.length; i++) {
            if (colores[i] != null && colores[i].getIdColor() == obj.getIdColor()) {
                colores[i] = obj;
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean eliminar(Color obj) {
        for (int i = 0; i < colores.length; i++) {
            if (colores[i] != null && colores[i].getIdColor() == obj.getIdColor()) {
                colores[i] = null;
                return true;
            }
        }
        return false;
    }

    public Color[] listar() {
        return colores;
    }

    @Override
    public int total() {
        int totalRegistros = 0;
        for (int i = 0; i < colores.length; i++) {
            if (colores[i] != null) {
                totalRegistros++;
            }
        }
        return totalRegistros;
    }

    @Override
    public void guardarEnArchivo() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void cargarDatos() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}