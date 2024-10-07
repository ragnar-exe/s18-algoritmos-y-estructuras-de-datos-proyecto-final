package daoImpl;

import dao.IDaoExtendido;
import model.Talla;

public class TallaDaoImpl implements IDaoExtendido<Talla>{
    Talla[][] tallas = new Talla[100][5];
    int[] idTallas = new int[200];
    public TallaDaoImpl() {
    }
    
    @Override
    public int obtenerId(String texto) {
        int id = -1;
        for (int i = 0; i < tallas.length; i++) {
            for (int j = 0; j < tallas[i].length; j++) {
                if (tallas[i][j] != null && tallas[i][j].getNumero() == Integer.parseInt(texto)) {
                    id = tallas[i][j].getIdTalla();
                }
            }
        }
        return id;
    }

    @Override
    public String obtenerNombre(int id) {
        String nombre = "";
        for (int i = 0; i < tallas.length; i++) {
            for (int j = 0; j < tallas[i].length; j++) {
                if (tallas[i][j].getIdTalla() == id) {
                    nombre = String.valueOf(tallas[i][j].getNumero());
                }
            }
        }
        return nombre;
    }

    @Override
    public int obtenerIdAutoincrement() {
        int codigo = 0;
        for (int i = 0; i < idTallas.length; i++) {
            if (idTallas[i] != 0) {
                codigo = idTallas[i];
            }
        }
        return codigo + 1;
    }

    @Override
    public void agregarCodigo(int codigo) {
        for (int i = 0; i < idTallas.length; i++) {
            if (idTallas[i] == 0) {
                idTallas[i] = codigo;
                return;
            }
        }
    }

    @Override
    public boolean agregar(Talla obj) {
        for (int i = 0; i < tallas.length; i++) {
            for (int j = 0; j < tallas[i].length; j++) {
                if (tallas[i][j] == null) {
                    tallas[i][j] = obj;
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public boolean actualizar(Talla obj) {
        for (int i = 0; i < tallas.length; i++) {
            for (int j = 0; j < tallas[i].length; j++) {
                if (tallas[i][j] != null && tallas[i][j].getNumero() == obj.getNumero() && tallas[i][j].getIdTalla() != obj.getIdTalla()) {
                    return false;
                }
            }
        }

        for (int i = 0; i < tallas.length; i++) {
            for (int j = 0; j < tallas[i].length; j++) {
                if (tallas[i][j] != null && tallas[i][j].getIdTalla() == obj.getIdTalla()) {
                    tallas[i][j] = obj;
                    return true;
                }
            }
        }

        return false;
    }

    @Override
    public boolean eliminar(Talla obj) {
        for (int i = 0; i < tallas.length; i++) {
            for (int j = 0; j < tallas[i].length; j++) {
                if (tallas[i][j] != null && tallas[i][j].getIdTalla()== obj.getIdTalla()) {
                    tallas[i][j] = null;
                    return true;
                }
            }
        }
        return false;
    }
    
    public Talla listar(int i, int j) {
        return tallas[i][j];
    }

    @Override
    public int total() {
        int totalRegistros = 0;
        for (int i = 0; i < tallas.length; i++) {
            for (int j = 0; j < tallas[i].length; j++) {
                if (tallas[i] != null) {
                    totalRegistros++;
                }
            }
        }
        return totalRegistros;
    }    
    
}
