package daoImpl;

import dao.IDaoExtendido;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import javax.swing.JOptionPane;
import model.Color;

public class ColorDaoImpl implements IDaoExtendido<Color> {

    Color[] colores = new Color[100];
    private static final String FILE_COLORES = "colores.txt";
    private static final String FILE_IDSCOLORES = "idscolores.txt";

    public ColorDaoImpl() {
        cargarDatos();
    }

    @Override
    public int obtenerUltimoId() {
        int id = 1;

        // Verificar si el archivo existe
        if (!Files.exists(Paths.get(FILE_IDSCOLORES))) {
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_IDSCOLORES))) {
                writer.write("0\n");
            } catch (IOException e) {
                JOptionPane.showConfirmDialog(null, "Error al crear el archivo idscolores", "ERROR", JOptionPane.ERROR_MESSAGE);
            }
        }

        // Leer el último código del archivo
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_IDSCOLORES))) {
            List<String> lines = Files.readAllLines(Paths.get(FILE_IDSCOLORES));
            if (!lines.isEmpty()) {
                try {
                    int lastCode = Integer.parseInt(lines.get(lines.size() - 1).strip());
                    id = lastCode + 1;
                } catch (NumberFormatException e) {
                    id = 1;
                }
            }
        } catch (IOException e) {
            JOptionPane.showConfirmDialog(null, "Error al obtener el ultimo ID de color", "ERROR", JOptionPane.ERROR_MESSAGE);
        }
        return id;
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
                try (BufferedWriter codigos = new BufferedWriter(new FileWriter(FILE_IDSCOLORES, true))) {
                    codigos.write(obj.getIdColor() + "\n");
                } catch (IOException ex) {
                    JOptionPane.showMessageDialog(null, "Error al agregar el codigo del color", "Error", JOptionPane.ERROR_MESSAGE);
                    return false;
                }
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
        guardarEnArchivo();
        return colores;
    }

    public Color[] listar(String texto) {
        Color[] colors = new Color[colores.length];
        int index = 0;
        for (int i = 0; i < colores.length; i++) {
            if (colores[i] != null && (String.valueOf(colores[i].getIdColor()).equalsIgnoreCase(texto) || colores[i].getNombre().contains(texto))) {
                colors[index++] = colores[i];
            }
        }
        return colors;
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

    public Color[] ordenarPorIdAscendente() {
        Color[] colors = new Color[colores.length];
        int index = 0;
        for (int i = 0; i < colores.length; i++) {
            if (colores[i] != null ) {
                colors[index++] = colores[i];
            }
        }
        Color[] colorsNoNulos = Arrays.copyOf(colors, index);
        Arrays.sort(colorsNoNulos, Comparator.comparing(Color::getIdColor));
        return colorsNoNulos;
    }

    public Color[] ordenarPorIdDescendente() {
        Color[] colors = new Color[colores.length];
        int index = 0;
        for (int i = 0; i < colores.length; i++) {
            if (colores[i] != null ) {
                colors[index++] = colores[i];
            }
        }
        Color[] colorsNoNulos = Arrays.copyOf(colors, index);
        Arrays.sort(colorsNoNulos, Comparator.comparing(Color::getIdColor).reversed());
        return colorsNoNulos;
    }
    
    public Color[] ordenarPorNombreAscendente() {
        Color[] colors = new Color[colores.length];
        int index = 0;
        for (int i = 0; i < colores.length; i++) {
            if (colores[i] != null ) {
                colors[index++] = colores[i];
            }
        }
        Color[] colorsNoNulos = Arrays.copyOf(colors, index);
        Arrays.sort(colorsNoNulos, Comparator.comparing(Color::getNombre));
        return colorsNoNulos;
    }
    
    public Color[] ordenarPorNombreDescendente() {
        Color[] colors = new Color[colores.length];
        int index = 0;
        for (int i = 0; i < colores.length; i++) {
            if (colores[i] != null ) {
                colors[index++] = colores[i];
            }
        }
        Color[] colorsNoNulos = Arrays.copyOf(colors, index);
        Arrays.sort(colorsNoNulos, Comparator.comparing(Color::getNombre).reversed());
        return colorsNoNulos;
    }

    @Override
    public void guardarEnArchivo() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_COLORES))) {
            for (int i = 0; i < colores.length; i++) {
                if (colores[i] != null) {
                    writer.write(colores[i].getIdColor() + ";" + colores[i].getNombre());
                    writer.newLine();
                }
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Error al guardar los colores", "ERROR", JOptionPane.ERROR_MESSAGE);
        }
    }

    @Override
    public void cargarDatos() {
        File fileColores = new File(FILE_COLORES);
        if (fileColores.exists()) {
            try (BufferedReader reader = new BufferedReader(new FileReader(fileColores))) {
                String linea;
                while ((linea = reader.readLine()) != null) {
                    String[] datos = linea.split(";");
                    if (datos.length == 2) {
                        try {
                            int id = Integer.parseInt(datos[0].strip());
                            String nombre = datos[1].strip();
                            agregar(new Color(id, nombre));
                        } catch (NumberFormatException e) {
                            JOptionPane.showMessageDialog(null, "Error al parsear el ID del color: " + datos[0], "ERROR", JOptionPane.ERROR_MESSAGE);
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "Formato de línea incorrecto: " + linea, "ERROR", JOptionPane.ERROR_MESSAGE);
                    }
                }
            } catch (IOException e) {
                JOptionPane.showMessageDialog(null, "Error al cargar los colores", "ERROR", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

}
