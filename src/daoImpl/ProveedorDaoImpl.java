package daoImpl;

import java.util.List;
import javax.swing.JOptionPane;
import model.Proveedor;

import dao.IDaoExtendido;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.LinkedList;

public class ProveedorDaoImpl implements IDaoExtendido<Proveedor> {

    LinkedList<Proveedor> proveedores = new LinkedList<>();
    private static final String FILE_PROVEEDORES = "proveedores.txt";
    private static final String FILE_IDSPROVEEDORES = "idsproveedores.txt";

    public ProveedorDaoImpl() {
        cargarDatos();
    }

    public Proveedor peek() {
        return proveedores.getFirst();
    }

    public void clear() {
        proveedores.clear();
    }

    public Proveedor dequeue() {  //remover al inicio
        if (proveedores.size() > 0) {
            Proveedor e = proveedores.getFirst();
            proveedores.remove(0);
            return e;
        } else {
            return null;
        }
    }

    public boolean isEmpty() {
        return proveedores.isEmpty();
    }

    public int size() {
        return proveedores.size();
    }

    public boolean existeCorreo(String correo) {
        for (Proveedor proveedor : proveedores) {
            if (proveedor.getCorreo().equalsIgnoreCase(correo)) {
                return true; // El correo ya esta en uso
            }
        }
        return false; // El correo no existe en la lista
    }
    
    public boolean existeTelefono(String telefono) {
        for (Proveedor proveedor : proveedores) {
            if (proveedor.getTelefono().equalsIgnoreCase(telefono)) {
                return true; 
            }
        }
        return false;
    }

    public List<Proveedor> listar(String texto) {
        List<Proveedor> resultado = new LinkedList<>();
        String valorBuscar = texto.toLowerCase();  // Convierte el texto de b?squeda a min?sculas para comparaci?n

        for (Proveedor proveedor : proveedores) {
            // Verifica si el texto coincide con el ID o nombre del proveedor
            boolean coincideConId = String.valueOf(proveedor.getIdProveedor()).contains(valorBuscar);
            boolean coincideConNombre = proveedor.getNombres().toLowerCase().contains(valorBuscar);
            boolean coincideConApellido = proveedor.getApellidos().toLowerCase().contains(valorBuscar);
            // Si coincide con alguna de las condiciones, agregar a la lista de resultados
            if (coincideConId || coincideConNombre || coincideConApellido) {
                resultado.add(proveedor);
            }
        }
        return resultado;
    }

    @Override
    public int obtenerId(String texto) {
        int id = -1;
        for (Proveedor proveedor : proveedores) {
            if (proveedor.getNombres().equalsIgnoreCase(texto)) {
                id = proveedor.getIdPersona();
            }
        }
        return id;
    }

    @Override
    public String obtenerNombre(int id) {
        String nombre = "";
        for (Proveedor proveedor : proveedores) {
            if (proveedor.getIdPersona() == id) {
                nombre = proveedor.getNombres();
            }
        }
        return nombre;
    }

    @Override
    public int obtenerUltimoId() {
        int id = 1;

        // Verificar si el archivo existe
        if (!Files.exists(Paths.get(FILE_IDSPROVEEDORES))) {
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_IDSPROVEEDORES))) {
                writer.write("0\n");
            } catch (IOException e) {
                JOptionPane.showConfirmDialog(null, "Error al crear el archivo idsproveedores", "ERROR", JOptionPane.ERROR_MESSAGE);
            }
        }

        // Leer el ?ltimo c?digo del archivo
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_IDSPROVEEDORES))) {
            List<String> lines = Files.readAllLines(Paths.get(FILE_IDSPROVEEDORES));
            if (!lines.isEmpty()) {
                try {
                    int lastCode = Integer.parseInt(lines.get(lines.size() - 1).strip());
                    id = lastCode + 1;
                } catch (NumberFormatException e) {
                    id = 1;
                }
            }
        } catch (IOException e) {
            JOptionPane.showConfirmDialog(null, "Error al obtener el ultimo ID de proveedores", "ERROR", JOptionPane.ERROR_MESSAGE);
        }
        return id;
    }
    
    public List<Proveedor> listar() {
        guardarEnArchivo();
        return proveedores;
    }
    
    @Override
    public boolean agregar(Proveedor obj) {
        try (BufferedWriter codigos = new BufferedWriter(new FileWriter(FILE_IDSPROVEEDORES, true))) {
            codigos.write(obj.getIdProveedor() + "\n");
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "Error al agregar el codigo de proveedor", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        return proveedores.add(obj);
    }

    @Override
    public boolean actualizar(Proveedor obj) {
        for (Proveedor proveedor : proveedores) {
            if (proveedor.getNombres().equalsIgnoreCase(obj.getNombres()) &&
                    proveedor.getApellidos().equalsIgnoreCase(obj.getApellidos()) &&
                    proveedor.getCorreo().equalsIgnoreCase(obj.getCorreo()) &&
                    proveedor.getTelefono().equalsIgnoreCase(obj.getTelefono()) &&
                    proveedor.getIdProveedor() != obj.getIdProveedor()) {
                JOptionPane.showMessageDialog(null, "Error, el proveedor con estos datos ya existe.", "Error", JOptionPane.ERROR_MESSAGE);
                return false;
            }
        }

        for (int i = 0; i < proveedores.size(); i++) {
            if (proveedores.get(i).getIdProveedor()== obj.getIdProveedor()) {
                proveedores.set(i, obj);
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean eliminar(Proveedor obj) {
        for (int i = 0; i < proveedores.size(); i++) {
            if (proveedores.get(i).getIdProveedor() == obj.getIdProveedor()) {  // Compara por el ID
                proveedores.remove(i);  // Elimina el proveedor de la lista
                return true;  // Retorna true si se elimin? correctamente
            }
        }
        return false;
    }

    @Override
    public void guardarEnArchivo() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PROVEEDORES))) {
            for (Proveedor proveedor : proveedores) {
                writer.write(proveedor.getIdProveedor() + ";" + proveedor.getNombres() + ";" + proveedor.getApellidos() + ";" + proveedor.getCorreo() + ";" + proveedor.getTelefono());
                writer.newLine();
            }
        } catch (IOException e) {
            JOptionPane.showConfirmDialog(null, "Error al guardar los proveedores", "ERROR", JOptionPane.ERROR_MESSAGE);
        }
    }

    @Override
    public void cargarDatos() {
        File fileProveedores = new File(FILE_PROVEEDORES);
        if (fileProveedores.exists()) {
            try (BufferedReader reader = new BufferedReader(new FileReader(fileProveedores))) {
                String linea;
                while ((linea = reader.readLine()) != null) {
                    String[] datos = linea.split(";");
                    int id = Integer.parseInt(datos[0]);
                    String nombre = datos[1];
                    String apellido = datos[2];
                    String correo = datos[3];
                    String telefono= datos[4];
                 
                    proveedores.add(new Proveedor(id, nombre, apellido, correo, telefono));
                }
            } catch (IOException e) {
                JOptionPane.showConfirmDialog(null, "Error al cargar los proveedores", "ERROR", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    @Override
    public int total() {
        return proveedores.size();
    }

}
