package daoImpl;

import dao.IDaoGenerico;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import javax.swing.JOptionPane;
import model.Usuario;

public class UsuarioDoalmpl implements IDaoGenerico<Usuario> {

    private static final String FILE_USUARIOS = "usuarios.txt";
    private static final String FILE_IDSUSUARIOS = "idsusuarios.txt";
    Usuario[] usuarios = new Usuario[100];
    private Usuario[] stack;
    private int top;

    public UsuarioDoalmpl() {
        this.top = -1;
    }

    public boolean push(Usuario u) {
        if (top == usuarios.length - 1) {
            System.out.println("La pila esta llena");
            return false;
        } else {
            top++;
            usuarios[top] = u;
            return true;
        }
    }

    public Usuario pop() {
        if (top == -1) {
            return null;
        } else {
            top--;
            return usuarios[top + 1];
        }
    }

    public Usuario peek() {
        if (top == -1) {
            return null;
        } else {
            return usuarios[top];
        }

    }

    public int size() {
        return top + 1;
    }

    public boolean isEmpty() {
        return top == -1;
    }

    public void clear() {
        for (int i = 0; i < usuarios.length; i++) {
            usuarios[i] = null;
        }
        top = -1;
    }

    @Override
    public int obtenerUltimoId() {
        int id = 1;
        // Verificar si el archivo existe
        if (!Files.exists(Paths.get(FILE_IDSUSUARIOS))) {
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_IDSUSUARIOS))) {
                writer.write("0\n");
            } catch (IOException e) {
                JOptionPane.showConfirmDialog(null, "Error al crear el archivo idUsuario", "ERROR", JOptionPane.ERROR_MESSAGE);
            }
        }
        // Leer el último código del archivo
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_IDSUSUARIOS))) {
            List<String> lines = Files.readAllLines(Paths.get(FILE_IDSUSUARIOS));
            if (!lines.isEmpty()) {
                try {
                    int lastCode = Integer.parseInt(lines.get(lines.size() - 1).strip());
                    id = lastCode + 1;
                } catch (NumberFormatException e) {
                    id = 1;
                }
            }
        } catch (IOException e) {
            JOptionPane.showConfirmDialog(null, "Error al obtener el ultimo ID de Usuario", "ERROR", JOptionPane.ERROR_MESSAGE);
        }
        return id;
    }

    @Override
    public boolean agregar(Usuario obj) {
        try (BufferedWriter codigos = new BufferedWriter(new FileWriter(FILE_IDSUSUARIOS, true))) {
            codigos.write(obj.getIdUsuario() + "\n");
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "Error al agregar el codigo del Usuario", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        if (top == usuarios.length - 1) {
            JOptionPane.showMessageDialog(null, "La pila Usuario esta llena", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        } else {
            top++;
            usuarios[top] = obj;
            return true;
        }
    }

    @Override
    public void guardarEnArchivo() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_USUARIOS))) {
            for (int i = 0; i < usuarios.length; i++) {
                if (usuarios[i] != null) {
                    writer.write(usuarios[i].getIdUsuario()+ ";" + usuarios[i].getUsuario()+";"+usuarios[i].getContrasena());
                    writer.newLine();
                }
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Error al guardar los Usuarios", "ERROR", JOptionPane.ERROR_MESSAGE);
        }
    }

    @Override
    public void cargarDatos() {
        File fileUsuarios = new File(FILE_USUARIOS);
        if (fileUsuarios.exists()) {
            try (BufferedReader reader = new BufferedReader(new FileReader(fileUsuarios))) {
                String linea;
                while ((linea = reader.readLine()) != null) {
                    String[] datos = linea.split(";");
                    int id = Integer.parseInt(datos[0].strip());
                    String nombre = datos[1].strip();
                    String contra = datos[2].strip();
                    for (int i = 0; i < usuarios.length; i++) {
                        if (usuarios[i] == null) {
                            usuarios[i] = new Usuario(id, nombre, contra);
                            break;
                        }
                    }
                }
            } catch (IOException e) {
                JOptionPane.showMessageDialog(null, "Error al cargar los Usuarios", "ERROR", JOptionPane.ERROR_MESSAGE);
            }
        }

    }

    @Override
    public int total() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public boolean actualizar(Usuario obj) {
        for (Usuario usuario : usuarios) {
            if (usuario.getUsuario().equalsIgnoreCase(obj.getUsuario()) && usuario.getIdUsuario() != obj.getIdUsuario()) {
                return false;
            }
        }
        for (int i = 0; i < usuarios.length; i++) {
            if (usuarios[i].getIdUsuario() == obj.getIdUsuario()) {
                usuarios[i] = obj;
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean eliminar(Usuario obj) {
        for (int i = 0; i < usuarios.length; i++) {
            if (usuarios[i] != null && usuarios[i].getIdUsuario()== obj.getIdUsuario()) {
                usuarios[i] = null;
                return true;
            }
        }
        return false;
    }
    public Usuario[] listar() {
        guardarEnArchivo();
        return usuarios;
    }

    }