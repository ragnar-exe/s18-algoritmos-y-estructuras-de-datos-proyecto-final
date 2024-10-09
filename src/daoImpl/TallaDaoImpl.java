package daoImpl;

import dao.IDaoExtendido;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import javax.swing.JOptionPane;
import model.Talla;

public class TallaDaoImpl implements IDaoExtendido<Talla> {

    Talla[][] tallas = new Talla[100][5]; // Estructura bidimensional para las tallas
    private static final String FILE_TALLAS = "tallas.txt";
    private static final String FILE_IDSTALLAS = "idstallas.txt";

    public TallaDaoImpl() {
        cargarDatos(); // Cargar los datos desde el archivo
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
                if (tallas[i][j] != null && tallas[i][j].getIdTalla() == id) {
                    nombre = String.valueOf(tallas[i][j].getNumero());
                }
            }
        }
        return nombre;
    }

    @Override
    public int obtenerUltimoId() {
        int codigo = 0;
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_IDSTALLAS))) {
            String linea;
            while ((linea = reader.readLine()) != null) {
                codigo = Integer.parseInt(linea);
            }
        } catch (IOException e) {
            JOptionPane.showConfirmDialog(null, "Error al obtener el ultimo ID de tallas", "ERROR", JOptionPane.ERROR_MESSAGE);
        }
        return codigo + 1;
    }

    @Override
    public boolean agregar(Talla obj) {
        for (int i = 0; i < tallas.length; i++) {
            for (int j = 0; j < tallas[i].length; j++) {
                if (tallas[i][j] == null) {
                    tallas[i][j] = obj;

                    // Guardar el ID de la talla en el archivo de IDs
                    try (BufferedWriter codigos = new BufferedWriter(new FileWriter(FILE_IDSTALLAS, true))) {
                        codigos.write(obj.getIdTalla() + "\n");
                    } catch (IOException ex) {
                        JOptionPane.showMessageDialog(null, "Error al agregar el codigo de talla", "Error", JOptionPane.ERROR_MESSAGE);
                        return false;
                    }
                    guardarEnArchivo(); // Guardar todas las tallas en el archivo
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
                    guardarEnArchivo(); // Guardar cambios en el archivo
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
                if (tallas[i][j] != null && tallas[i][j].getIdTalla() == obj.getIdTalla()) {
                    tallas[i][j] = null;
                    guardarEnArchivo(); // Guardar cambios en el archivo
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public int total() {
        int totalRegistros = 0;
        for (int i = 0; i < tallas.length; i++) {
            for (int j = 0; j < tallas[i].length; j++) {
                if (tallas[i][j] != null) {
                    totalRegistros++;
                }
            }
        }
        return totalRegistros;
    }

    @Override
    public void guardarEnArchivo() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_TALLAS))) {
            for (int i = 0; i < tallas.length; i++) {
                for (int j = 0; j < tallas[i].length; j++) {
                    if (tallas[i][j] != null) {
                        writer.write(tallas[i][j].getIdTalla() + ";" + tallas[i][j].getNumero());
                        writer.newLine();
                    }
                }
            }
        } catch (IOException e) {
            JOptionPane.showConfirmDialog(null, "Error al guardar las tallas", "ERROR", JOptionPane.ERROR_MESSAGE);
        }
    }

    @Override
    public void cargarDatos() {
        File fileTallas = new File(FILE_TALLAS);
        if (fileTallas.exists()) {
            try (BufferedReader reader = new BufferedReader(new FileReader(fileTallas))) {
                String linea;
                while ((linea = reader.readLine()) != null) {
                    String[] datos = linea.split(";");
                    int id = Integer.parseInt(datos[0]);
                    byte numero = Byte.parseByte(datos[1]);

                    // Colocar la talla en la primera posición libre de la matriz
                    outerLoop:
                    for (int i = 0; i < tallas.length; i++) {
                        for (int j = 0; j < tallas[i].length; j++) {
                            if (tallas[i][j] == null) {
                                tallas[i][j] = new Talla(id, numero);
                                break outerLoop;
                            }
                        }
                    }
                }
            } catch (IOException e) {
                JOptionPane.showConfirmDialog(null, "Error al cargar las tallas", "ERROR", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    // Método adicional para obtener una talla en una posición específica
    public Talla listar(int i, int j) {
        return tallas[i][j];
    }
}
