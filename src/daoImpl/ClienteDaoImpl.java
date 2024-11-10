package daoImpl;

import dao.IDaoExtendido;
import java.util.List;
import model.Cliente;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.LinkedList;
import javax.swing.JOptionPane;

public class ClienteDaoImpl implements IDaoExtendido<Cliente>{
    LinkedList<Cliente> clientes = new LinkedList<>();
    private static final String FILE_CLIENTES = "clientes.txt";
    private static final String FILE_IDSCLIENTES = "idsclientes.txt";

    public ClienteDaoImpl() {
        cargarDatos();
    }
    
    @Override
    public int obtenerId(String texto) {
        int id = -1;
        for (Cliente cliente : clientes) {
            if (cliente.getNombres().equalsIgnoreCase(texto)) {
                id = cliente.getIdPersona();
            }
        }
        return id;
    }

    @Override
    public String obtenerNombre(int id) {
        String nombre = "";
        for (Cliente cliente : clientes) {
            if (cliente.getIdPersona()== id) {
                nombre = cliente.getNombres();
            }
        }
        return nombre;
    }

    @Override
    public int obtenerUltimoId() {
        int id = 1;

        // Verificar si el archivo existe
        if (!Files.exists(Paths.get(FILE_IDSCLIENTES))) {
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_IDSCLIENTES))) {
                writer.write("0\n");
            } catch (IOException e) {
                JOptionPane.showConfirmDialog(null, "Error al crear el archivo idsproductos", "ERROR", JOptionPane.ERROR_MESSAGE);
            }
        }

        // Leer el último código del archivo
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_IDSCLIENTES))) {
            List<String> lines = Files.readAllLines(Paths.get(FILE_IDSCLIENTES));
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
    //TRABAJA CON PILAS ES UN PUSH
    public boolean agregar(Cliente obj) {
        try (BufferedWriter codigos = new BufferedWriter(new FileWriter(FILE_IDSCLIENTES, true))) {
            codigos.write(obj.getIdPersona()+ "\n");
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "Error al agregar el codigo de producto", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        return clientes.add(obj);
    }

    @Override
    public boolean actualizar(Cliente obj) {
        for (Cliente cliente : clientes) {
            if (cliente.getNombres().equalsIgnoreCase(obj.getNombres()) && cliente.getIdPersona()!= obj.getIdPersona()) {
                return false;
            }
        }

        for (int i = 0; i < clientes.size(); i++) {
            if (clientes.get(i).getIdPersona()== obj.getIdPersona()) {
                clientes.set(i, obj);
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean eliminar(Cliente obj) {
        return clientes.remove(obj);
    }
    
    //ELIMINAR EN PILA POP_
    public boolean pop(){
        return clientes.remove(clientes.getLast());
    }
    
    public List<Cliente> listar() {
        guardarEnArchivo();
        return clientes;
    }

    @Override
    public void guardarEnArchivo() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_CLIENTES))) {
            for (Cliente cliente : clientes) {
                writer.write(cliente.getIdPersona()+ ";" + cliente.getDni() + ";" + cliente.getNombres() + ";" + cliente.getApellidos()+ ";" + cliente.getDireccion()+ ";" + cliente.getCorreo());
                writer.newLine();
            }
        } catch (IOException e) {
            JOptionPane.showConfirmDialog(null, "Error al guardar los clientes", "ERROR", JOptionPane.ERROR_MESSAGE);
        }
    }

    @Override
    public void cargarDatos() {
        File fileClientes = new File(FILE_CLIENTES);
        if (fileClientes.exists()) {
            try (BufferedReader reader = new BufferedReader(new FileReader(fileClientes))) {
                String linea;
                while ((linea = reader.readLine()) != null) {
                    String[] datos = linea.split(";");
                    int id = Integer.parseInt(datos[0]);
                    String dni = datos[1];
                    String nombre = datos[2];
                    String apellido = datos[3];
                    String direccion = datos[4];
                    String correo = datos[5];
                    clientes.add(new Cliente(dni,id, nombre, apellido, direccion, correo));
                }
            } catch (IOException e) {
                JOptionPane.showConfirmDialog(null, "Error al cargar los productos", "ERROR", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    @Override
    public int total() {
        return clientes.size();
    }
    
    public Cliente peek(){
        return clientes.getLast();
    }
    
    public void clear(){
        clientes.clear();
    }
    public boolean isEmpty(){
        return clientes.size() == 0;
    }
    public boolean existeCorreo(String correo) {
        for (Cliente cliente : clientes) {
            if (cliente.getCorreo().equalsIgnoreCase(correo)) {
                return true; // El correo ya está en uso
            }
        }
        return false; // El correo no existe en la lista
    }
}
