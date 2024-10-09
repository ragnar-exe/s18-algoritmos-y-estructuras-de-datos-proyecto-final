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
import java.util.List;
import javax.swing.JOptionPane;
import model.Marca;
import model.Nodo;

public class MarcaDaoImpl implements IDaoExtendido<Marca> {

    private static final String FILE_MARCAS = "marcas.txt";
    private static final String FILE_IDSMARCAS = "idsmarcas.txt";
    public Nodo inicio;
    public Nodo fin;

    public MarcaDaoImpl() {
        cargarDatos();
        this.inicio = null;
        this.fin = null;
    }

    @Override
    public int obtenerId(String texto) {
        int id = -1;
        Nodo temp = inicio;
        while (temp != null) {
            if (temp.getMarca().getNombre().equalsIgnoreCase(texto)) {
                id = temp.getMarca().getIdMarca();
            }
            temp = temp.getSiguiente();
        }
        return id;
    }

    @Override
    public String obtenerNombre(int id) {
        String nombre = "";
        Nodo temp = inicio;
        while (temp != null) {
            if (temp.getMarca().getIdMarca() == id) {
                nombre = temp.getMarca().getNombre();
            }
            temp = temp.getSiguiente();
        }
        return nombre;
    }

    @Override
    public int obtenerUltimoId() {
        int id = 1;

        // Verificar si el archivo existe
        if (!Files.exists(Paths.get(FILE_IDSMARCAS))) {
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_IDSMARCAS))) {
                writer.write("0\n");
            } catch (IOException e) {
                JOptionPane.showConfirmDialog(null, "Error al crear el archivo idsmarcas", "ERROR", JOptionPane.ERROR_MESSAGE);
            }
        }

        // Leer el último código del archivo
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_IDSMARCAS))) {
            List<String> lines = Files.readAllLines(Paths.get(FILE_IDSMARCAS));
            if (!lines.isEmpty()) {
                try {
                    int lastCode = Integer.parseInt(lines.get(lines.size() - 1).strip());
                    id = lastCode + 1;
                } catch (NumberFormatException e) {
                    id = 1;
                }
            }
        } catch (IOException e) {
            JOptionPane.showConfirmDialog(null, "Error al obtener el ultimo ID de marcas", "ERROR", JOptionPane.ERROR_MESSAGE);
        }
        return id;
    }

    @Override
    public boolean agregar(Marca obj) {
        // Agregar al final
        boolean guardar = false;
        Nodo nuevo = new Nodo(obj, null);
        if (inicio == null) {
            fin = nuevo;
            inicio = fin;
            guardar = true;
        } else {
            fin.setSiguiente(nuevo);
            fin = nuevo;
            guardar = true;
        }
        if (guardar) {
            try (BufferedWriter codigos = new BufferedWriter(new FileWriter(FILE_IDSMARCAS, true))) {
                codigos.write(obj.getIdMarca() + "\n");
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(null, "Error al agregar el codigo de marca", "Error", JOptionPane.ERROR_MESSAGE);
                return false;
            }
        }
        return guardar;
    }

    public boolean agregarInicio(Marca obj) {
        Nodo nuevo = new Nodo(obj, inicio);
        inicio = nuevo;
        if (fin == null) {
            fin = inicio;
            try (BufferedWriter codigos = new BufferedWriter(new FileWriter(FILE_IDSMARCAS, true))) {
                codigos.write(obj.getIdMarca() + "\n");
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(null, "Error al agregar el codigo de marca", "Error", JOptionPane.ERROR_MESSAGE);
                return false;
            }
            return true;
        }
        return false;
    }

    public boolean agregarPosicion(Marca obj) {
        Nodo nuevo = new Nodo(obj, inicio);
        inicio = nuevo;
        if (fin == null) {
            fin = inicio;
            try (BufferedWriter codigos = new BufferedWriter(new FileWriter(FILE_IDSMARCAS, true))) {
                codigos.write(obj.getIdMarca() + "\n");
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(null, "Error al agregar el codigo de marca", "Error", JOptionPane.ERROR_MESSAGE);
                return false;
            }
            return true;
        }
        return false;
    }

    @Override
    public boolean actualizar(Marca obj) {
        Nodo temp = inicio;
        while (temp != null) {
            if (temp.getMarca() == obj) {
                return true;
            }
            temp = temp.getSiguiente();
        }
        return false;
    }

    @Override
    public boolean eliminar(Marca obj) {
        // Eliminar por posición (ID)
        // Elimina inicio -- modificar
        if (inicio == null || fin == null) {
            return false;
        } else {
            inicio = inicio.siguiente;
            return true;
        }
    }
    
    public boolean eliminarInicio(Marca obj) {
        if (inicio == null || fin == null) {
            return false;
        } else {
            inicio = inicio.siguiente;
            return true;
        }
    }

    public boolean eliminarFinal(Marca obj) {
        boolean eliminar = false;
        if (inicio == null || fin == null) {
            eliminar = false;
            JOptionPane.showMessageDialog(null, "No hay elementos en la lista.", "Adertencia", JOptionPane.WARNING_MESSAGE);
        } else if (inicio == fin) {
            inicio = null;
            fin = null;
            eliminar = true;
        } else {
            Nodo actual = inicio;
            while (actual.getSiguiente() != fin) {
                actual = actual.getSiguiente();
            }
            actual.setSiguiente(null);
            fin = actual;
            eliminar = true;
        }
        return eliminar;
    }

    @Override
    public int total() {
        int total = 0;
        Nodo temp = inicio;
        while (temp != null) {
            temp.getMarca();
            temp = temp.getSiguiente();
            total++;
        }
        return total;
    }

    @Override
    public void guardarEnArchivo() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_MARCAS))) {
            Nodo temp = inicio;
            while (temp != null) {
                writer.write(temp.getMarca().getIdMarca() + ";" + temp.getMarca().getNombre());
                writer.newLine();
                temp = temp.getSiguiente();
            }
        } catch (IOException e) {
            JOptionPane.showConfirmDialog(null, "Error al guardar las marcas", "ERROR", JOptionPane.ERROR_MESSAGE);
        }
    }

    @Override
    public void cargarDatos() {
        File fileMarcas = new File(FILE_MARCAS);
        if (fileMarcas.exists()) {
            try (BufferedReader reader = new BufferedReader(new FileReader(fileMarcas))) {
                String linea;
                while ((linea = reader.readLine()) != null) {
                    String[] datos = linea.split(";");
                    int id = Integer.parseInt(datos[0]);
                    String nombre = datos[1];
                    Nodo nuevo = new Nodo(new Marca(id, nombre), null);
                    if (inicio == null) {
                        fin = nuevo;
                        inicio = fin;
                    } else {
                        fin.setSiguiente(nuevo);
                        fin = nuevo;
                    }
                }
            } catch (IOException e) {
                JOptionPane.showConfirmDialog(null, "Error al cargar las marcas", "ERROR", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

}
