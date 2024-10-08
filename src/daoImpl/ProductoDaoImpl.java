package daoImpl;

import dao.IDaoObtenerLista;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import javax.swing.JOptionPane;
import model.Categoria;
import model.Producto;

public class ProductoDaoImpl implements IDaoObtenerLista<Producto> {

    LinkedList<Producto> productos = new LinkedList<>();
    private static final String FILE_PRODUCTOS = "productos.txt";
    private static final String FILE_IDSPRODUCTOS = "idsproductos.txt";

    public ProductoDaoImpl() {
        cargarDatos();
    }

    @Override
    public int obtenerId(String texto) {
        int id = -1;
        for (Producto producto : productos) {
            if (producto.getNombre().equalsIgnoreCase(texto)) {
                id = producto.getIdProducto();
            }
        }
        return id;
    }

    @Override
    public String obtenerNombre(int id) {
        String nombre = "";
        for (Producto producto : productos) {
            if (producto.getIdProducto() == id) {
                nombre = producto.getNombre();
            }
        }
        return nombre;
    }

    @Override
    public int obtenerUltimoId() {
        int id = 1;

        // Verificar si el archivo existe
        if (!Files.exists(Paths.get(FILE_IDSPRODUCTOS))) {
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_IDSPRODUCTOS))) {
                writer.write("0\n");
            } catch (IOException e) {
                JOptionPane.showConfirmDialog(null, "Error al crear el archivo idsproductos", "ERROR", JOptionPane.ERROR_MESSAGE);
            }
        }

        // Leer el último código del archivo
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_IDSPRODUCTOS))) {
            List<String> lines = Files.readAllLines(Paths.get(FILE_IDSPRODUCTOS));
            if (!lines.isEmpty()) {
                try {
                    int lastCode = Integer.parseInt(lines.get(lines.size() - 1).strip());
                    id = lastCode + 1;
                } catch (NumberFormatException e) {
                    id = 1;
                }
            }
        } catch (IOException e) {
            JOptionPane.showConfirmDialog(null, "Error al obtener el ultimo ID de productos", "ERROR", JOptionPane.ERROR_MESSAGE);
        }
        return id;
    }

    @Override
    public boolean agregar(Producto obj) {
        try (BufferedWriter codigos = new BufferedWriter(new FileWriter(FILE_IDSPRODUCTOS, true))) {
            codigos.write(obj.getIdProducto() + "\n");
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "Error al agregar el codigo de producto", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        return productos.add(obj);
    }

    @Override
    public boolean actualizar(Producto obj) {
        for (Producto producto : productos) {
            if (producto.getNombre().equalsIgnoreCase(obj.getNombre()) && producto.getIdProducto() != obj.getIdProducto()) {
                return false;
            }
        }

        for (int i = 0; i < productos.size(); i++) {
            if (productos.get(i).getIdProducto() == obj.getIdProducto()) {
                productos.set(i, obj);
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean eliminar(Producto obj) {
        return productos.remove(obj);
    }

    public List<Producto> listar() {
        guardarEnArchivo();
        return productos;
    }

    @Override
    public int total() {
        return productos.size();
    }

    @Override
    public void guardarEnArchivo() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PRODUCTOS))) {
            for (Producto producto : productos) {
                writer.write(producto.getIdProducto() + ";" + producto.getNombre() + ";" + producto.getDescripcion() + ";" + producto.getIdCategoria());
                writer.newLine();
            }
        } catch (IOException e) {
            JOptionPane.showConfirmDialog(null, "Error al guardar los productos", "ERROR", JOptionPane.ERROR_MESSAGE);
        }
    }

    @Override
    public void cargarDatos() {
        File fileProductos = new File(FILE_PRODUCTOS);
        if (fileProductos.exists()) {
            try (BufferedReader reader = new BufferedReader(new FileReader(fileProductos))) {
                String linea;
                while ((linea = reader.readLine()) != null) {
                    String[] datos = linea.split(";");
                    int id = Integer.parseInt(datos[0]);
                    String nombre = datos[1];
                    String descripcion = datos[2];
                    int idCategoria = Integer.parseInt(datos[3]);
                    productos.add(new Producto(id, nombre, descripcion, idCategoria));
                }
            } catch (IOException e) {
                JOptionPane.showConfirmDialog(null, "Error al cargar los productos", "ERROR", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    @Override
    public List<Producto> listar(String texto) {
        List<Producto> resultado = new LinkedList<>();
        String valorBuscar = texto.toLowerCase();  // Convierte el texto de búsqueda a minúsculas para comparación
        IDaoObtenerLista<Categoria> idaoCategoria = new CategoriaDaoImpl();

        for (Producto producto : productos) {
            // Verifica si el texto coincide con el ID o nombre del producto
            boolean coincideConId = String.valueOf(producto.getIdProducto()).contains(valorBuscar);
            boolean coincideConNombre = producto.getNombre().toLowerCase().contains(valorBuscar);
            boolean coincideConDescripcion = producto.getDescripcion().toLowerCase().contains(valorBuscar);

            // También puedes agregar la verificación de idCategoria si lo deseas
            String categoria = idaoCategoria.obtenerNombre(producto.getIdCategoria());
            boolean coincideConCategoria = String.valueOf(categoria).contains(valorBuscar);

            // Si coincide con alguna de las condiciones, agregar a la lista de resultados
            if (coincideConId || coincideConNombre || coincideConDescripcion || coincideConCategoria) {
                resultado.add(producto);
            }
        }
        return resultado;
    }

    @Override
    public List<Producto> listarOrdenarAscendete() {
        Collections.sort(productos, Comparator.comparing(Producto::getNombre));
        return productos;
    }

    @Override
    public List<Producto> listarOrdenarDescendete() {
        Collections.sort(productos, Comparator.comparing(Producto::getNombre).reversed());
        return productos;
    }

    public List<Producto> listarOrdenarIdDescendente() {
        Collections.sort(productos, Comparator.comparing(Producto::getIdProducto).reversed());
        return productos;
    }

    public List<Producto> listarOrdenarIdAscendente() {
        Collections.sort(productos, Comparator.comparing(Producto::getIdProducto));
        return productos;
    }
}
