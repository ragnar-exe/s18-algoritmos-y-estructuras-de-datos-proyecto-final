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
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import model.Categoria;

public class CategoriaDaoImpl implements IDaoExtendido<Categoria> {
    private ArrayList<Categoria> categorias = new ArrayList<>();
    private static final String FILE_CATEGORIAS = "categorias.txt";
    private static final String FILE_IDSCATEGORIAS = "idscategorias.txt";

    public CategoriaDaoImpl() {
        cargarDatos();
    }

    @Override
    public int obtenerId(String texto) {
        int id = -1;
        for (Categoria categoria : categorias) {
            if (categoria.getNombre().equalsIgnoreCase(texto)) {
                id = categoria.getIdCategoria();
            }
        }
        return id;
    }

    @Override
    public String obtenerNombre(int id) {
        String nombre = "";
        for (Categoria categoria : categorias) {
            if (categoria.getIdCategoria() == id) {
                nombre = categoria.getNombre();
            }
        }
        return nombre;
    }

    @Override
    public int obtenerUltimoId() {
        int id = 1;

        // Verificar si el archivo existe
        if (!Files.exists(Paths.get(FILE_IDSCATEGORIAS))) {
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_IDSCATEGORIAS))) {
                writer.write("0\n");
            } catch (IOException e) {
                JOptionPane.showConfirmDialog(null, "Error al crear el archivo idscategoria", "ERROR", JOptionPane.ERROR_MESSAGE);
            }
        }

        // Leer el último código del archivo
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_IDSCATEGORIAS))) {
            List<String> lines = Files.readAllLines(Paths.get(FILE_IDSCATEGORIAS));
            if (!lines.isEmpty()) {
                try {
                    int lastCode = Integer.parseInt(lines.get(lines.size() - 1).trim());
                    id = lastCode + 1;
                } catch (NumberFormatException e) {
                    id = 1;
                }
            }
        } catch (IOException e) {
            JOptionPane.showConfirmDialog(null, "Error al obtener el ultimo ID de categorias", "ERROR", JOptionPane.ERROR_MESSAGE);
        }
        return id;
    }

    @Override
    public boolean agregar(Categoria obj) {
        try (BufferedWriter codigos = new BufferedWriter(new FileWriter(FILE_IDSCATEGORIAS, true))) {
            codigos.write(obj.getIdCategoria() + "\n");
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "Error al agregar el codigo", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        return categorias.add(obj);
    }

    @Override
    public boolean actualizar(Categoria obj) {
        for (Categoria categoria : categorias) {
            if (categoria.getNombre().equalsIgnoreCase(obj.getNombre()) && categoria.getIdCategoria() != obj.getIdCategoria()) {
                return false;
            }
        }

        for (int i = 0; i < categorias.size(); i++) {
            if (categorias.get(i).getIdCategoria() == obj.getIdCategoria()) {
                categorias.set(i, obj);
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean eliminar(Categoria obj) {
        return categorias.remove(obj);
    }

    public List<Categoria> listar() {
        guardarEnArchivo();
        return categorias;
    }

    @Override
    public int total() {
        return categorias.size();
    }

    @Override
    public void guardarEnArchivo() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_CATEGORIAS))) {
            for (Categoria categoria : categorias) {
                writer.write(categoria.getIdCategoria() + ";" + categoria.getNombre());
                writer.newLine();
            }
        } catch (IOException e) {
            JOptionPane.showConfirmDialog(null, "Error al guardar las categorias", "ERROR", JOptionPane.ERROR_MESSAGE);
        }
    }

    @Override
    public void cargarDatos() {
        File fileCategorias = new File(FILE_CATEGORIAS);
        if (fileCategorias.exists()) {
            try (BufferedReader reader = new BufferedReader(new FileReader(fileCategorias))) {
                String linea;
                while ((linea = reader.readLine()) != null) {
                    String[] datos = linea.split(";");
                    int id = Integer.parseInt(datos[0]);
                    String nombre = datos[1];
                    categorias.add(new Categoria(id, nombre));
                }
            } catch (IOException e) {
                JOptionPane.showConfirmDialog(null, "Error al cargar las categorias", "ERROR", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}
