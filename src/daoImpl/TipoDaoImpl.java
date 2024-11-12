/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
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
import model.Nodo;
import model.Tipo;

/**
 *
 * @author suare
 */
public class TipoDaoImpl implements IDaoExtendido<Tipo> {

    private static final String FILE_TIPO = "tipo.txt";
    private static final String FILE_IDSTIPO = "idtipo.txt";
    public Nodo inicio;
    int tamano = 0;

    public TipoDaoImpl() {
        this.inicio = null;
        cargarDatos();
    }

    @Override
    public int obtenerId(String texto) {
        int id = -1;
        Nodo temp = inicio;

        // Asegurarse de que inicio no sea null antes de iniciar el bucle
        if (inicio != null) {
            do {
                // Verifica si el nombre coincide
                if (temp.getTipo().getNombre().equalsIgnoreCase(texto)) {
                    id = temp.getTipo().getIdTipo();
                    break; // Rompe el bucle si el id ha sido encontrado
                }
                temp = temp.getSiguiente();
            } while (temp != inicio); // Sale del bucle cuando se ha recorrido toda la lista
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
        if (!Files.exists(Paths.get(FILE_IDSTIPO))) {
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_IDSTIPO))) {
                writer.write("0\n");
            } catch (IOException e) {
                JOptionPane.showConfirmDialog(null, "Error al crear el archivo idsmarcas", "ERROR", JOptionPane.ERROR_MESSAGE);
            }
        }

        // Leer el último código del archivo
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_IDSTIPO))) {
            List<String> lines = Files.readAllLines(Paths.get(FILE_IDSTIPO));
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
    public boolean agregar(Tipo obj) {
        // Comprobar si el objeto es null
        if (obj == null) {
            JOptionPane.showMessageDialog(null, "El tipo no puede ser nulo", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        boolean guardar = false;

        // Si la lista está vacía
        if (inicio == null) {
            inicio = new Nodo(null, obj);  // Primer nodo con 'obj'
            inicio.setSiguiente(inicio);  // La lista es circular, apuntamos al mismo nodo
            tamano = 1;  // Asegúrate de tener esta variable 'tamano' bien definida
            guardar = true;
        } else {
            // Buscar el último nodo y agregar el nuevo nodo
            System.out.println("Awuii");
            Nodo aux = inicio;
            while (aux.getSiguiente() != inicio) {
                aux = aux.getSiguiente();
            }
            Nodo nuevoNodo = new Nodo(inicio, obj);  // El nuevo nodo apunta al inicio
            aux.setSiguiente(nuevoNodo);  // El último nodo apunta al nuevo nodo
            tamano++;
            guardar = true;
        }

        // Guardar el tipo en el archivo si se ha agregado correctamente
        if (guardar) {
            try (BufferedWriter codigos = new BufferedWriter(new FileWriter(FILE_IDSTIPO, true))) {
                codigos.write(obj.getIdTipo() + "\n");
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(null, "Error al agregar el codigo de tipo", "Error", JOptionPane.ERROR_MESSAGE);
                return false;
            }
        }

        return guardar;
    }

    public boolean agregarInicio(Tipo obj) {
        // Crear el nuevo nodo con el siguiente nodo apuntando al inicio
        Nodo nuevo = new Nodo(inicio, obj);

        // Si la lista está vacía, el nuevo nodo se apuntará a sí mismo
        if (inicio == null) {
            nuevo.setSiguiente(nuevo);  // Apunta a sí mismo
            inicio = nuevo;             // El inicio es ahora el nuevo nodo
        } else {
            // Si la lista no está vacía, el último nodo debe apuntar al nuevo nodo
            Nodo temp = inicio;
            while (temp.getSiguiente() != inicio) {
                temp = temp.getSiguiente();
            }
            temp.setSiguiente(nuevo);  // El último nodo apunta al nuevo nodo
            inicio = nuevo;            // El nuevo nodo es ahora el inicio
        }
        try (BufferedWriter codigos = new BufferedWriter(new FileWriter(FILE_IDSTIPO, true))) {
            codigos.write(obj.getIdTipo() + "\n");
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "Error al agregar el codigo de marca", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        return true;
    }

    public boolean agregarPosicion(Tipo obj, int posicion) {
        if (posicion < 0) {
            JOptionPane.showMessageDialog(null, "Posición inválida", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        Nodo nuevo = new Nodo(null, obj);
        // Si la lista está vacía
        if (inicio == null) {
            nuevo.setSiguiente(nuevo);  // El nuevo nodo apunta a sí mismo
            inicio = nuevo;             // El nuevo nodo es el inicio (y también el fin, por ahora)
            try (BufferedWriter codigos = new BufferedWriter(new FileWriter(FILE_IDSTIPO, true))) {
                codigos.write(obj.getIdTipo() + "\n");
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(null, "Error al agregar el codigo de marca", "Error", JOptionPane.ERROR_MESSAGE);
                return false;
            }
            return true;

        }
        // Si se quiere insertar en la primera posición (posición 0)
        if (posicion == 0) {
            nuevo.setSiguiente(inicio);
            Nodo temp = inicio;
            // Recorremos la lista hasta llegar al último nodo para que apunte al nuevo nodo
            while (temp.getSiguiente() != inicio) {
                temp = temp.getSiguiente();
            }
            temp.setSiguiente(nuevo); // El último nodo apunta al nuevo nodo
            inicio = nuevo;
            try (BufferedWriter codigos = new BufferedWriter(new FileWriter(FILE_IDSTIPO, true))) {
                codigos.write(obj.getIdTipo() + "\n");
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(null, "Error al agregar el codigo de marca", "Error", JOptionPane.ERROR_MESSAGE);
                return false;
            }            // Actualizamos el inicio
            return true;
        }
        // Si la posición es mayor que 0, buscamos la posición para insertar
        Nodo actual = inicio;
        int contador = 0;
        while (actual.getSiguiente() != inicio && contador < posicion - 1) {
            actual = actual.getSiguiente();
            contador++;
        }

        // Si llegamos al final de la lista sin encontrar la posición
        if (contador != posicion - 1) {
            JOptionPane.showMessageDialog(null, "Posición fuera de rango", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        // Insertamos el nuevo nodo
        nuevo.setSiguiente(actual.getSiguiente());
        actual.setSiguiente(nuevo);

        try (BufferedWriter codigos = new BufferedWriter(new FileWriter(FILE_IDSTIPO, true))) {
            codigos.write(obj.getIdTipo() + "\n");
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "Error al agregar el codigo de marca", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        return true;
    }

    @Override
    public boolean actualizar(Tipo obj) {
        Nodo temp = inicio;
        while (temp != null) {
            // Verificamos si el nodo actual tiene el ID de marca que buscamos
            if (temp.getTipo().getIdTipo() == obj.getIdTipo()) {
                Nodo check = inicio;

                // Recorremos la lista para verificar si el nombre de la marca ya existe (evitar duplicados)
                do {
                    if (check != temp && check.getTipo().getNombre().equalsIgnoreCase(obj.getNombre())) {
                        JOptionPane.showMessageDialog(null, "El tipo de pago ya existe", "Error", JOptionPane.ERROR_MESSAGE);
                        return false;
                    }
                    check = check.getSiguiente();
                } while (check != inicio);  // Recorremos toda la lista circular

                // Si no encontramos duplicados, actualizamos la marca
                temp.setTipo(obj);
                return true;
            }
            temp = temp.getSiguiente();

            // Para lista circular, aseguramos que no salgamos del ciclo infinito
            if (temp == inicio) {
                break;  // Evita un ciclo infinito si la lista está circular
            }
        }
        return false;
    }

    @Override
    public boolean eliminar(Tipo obj) {
        if (inicio == null) {
            return false; // La lista está vacía
        }

        // Si el nodo a eliminar es el primero (inicio)
        if (inicio.getTipo().getIdTipo() == obj.getIdTipo()) {
            // Si hay solo un nodo en la lista (inicio apunta a sí mismo)
            if (inicio.getSiguiente() == inicio) {
                inicio = null;  // La lista queda vacía
            } else {
                // Si hay más de un nodo, el 'inicio' apunta al siguiente
                Nodo temp = inicio;
                while (temp.getSiguiente() != inicio) {  // Recorrer hasta el último nodo
                    temp = temp.getSiguiente();
                }
                // El último nodo ahora apunta al siguiente del inicio (nuevo primer nodo)
                temp.setSiguiente(inicio.getSiguiente());
                inicio = inicio.getSiguiente();  // Se actualiza 'inicio' al siguiente nodo
            }
            return true;
        }

        // Recorrer la lista circular
        Nodo actual = inicio;
        do {
            // Si encontramos el nodo a eliminar
            if (actual.getSiguiente().getTipo().getIdTipo() == obj.getIdTipo()) {
                // Eliminar el nodo
                actual.setSiguiente(actual.getSiguiente().getSiguiente());
                return true;
            }
            actual = actual.getSiguiente();
        } while (actual != inicio);  // Continuar hasta llegar de nuevo al inicio

        return false;  // No se encontró el nodo con el ID especificado
    }

    public boolean eliminarInicio() {
        if (inicio == null) {
            return false; // La lista está vacía
        }

        // Si la lista tiene solo un nodo
        if (inicio.getSiguiente() == inicio) {
            inicio = null;  // La lista queda vacía
        } else {
            // Si la lista tiene más de un nodo
            Nodo ultimo = inicio;
            // Recorrer hasta el último nodo
            while (ultimo.getSiguiente() != inicio) {
                ultimo = ultimo.getSiguiente();
            }
            // El último nodo ahora apunta al siguiente del 'inicio'
            ultimo.setSiguiente(inicio.getSiguiente());
            // Actualizamos el 'inicio' al siguiente nodo
            inicio = inicio.getSiguiente();
        }

        return true;  // El nodo de inicio ha sido eliminado correctamente
    }

    public boolean eliminarFinal() {
        boolean eliminar = false;

        if (inicio == null) {
            JOptionPane.showMessageDialog(null, "No hay elementos en la lista.", "Advertencia", JOptionPane.WARNING_MESSAGE);
            eliminar = false;
        } else if (inicio.getSiguiente() == inicio) {
            // Si solo hay un nodo
            inicio = null;  // La lista queda vacía
            eliminar = true;
        } else {
            // Si hay más de un nodo
            Nodo actual = inicio;
            while (actual.getSiguiente() != inicio) {
                // Recorrer hasta el nodo que apunta al inicio (último nodo)
                actual = actual.getSiguiente();
            }
            // Ahora 'actual' es el último nodo, y su predecesor apunta al inicio
            Nodo penultimo = inicio;
            while (penultimo.getSiguiente() != actual) {
                penultimo = penultimo.getSiguiente();
            }
            // El penúltimo nodo ahora apunta al inicio
            penultimo.setSiguiente(inicio);
            eliminar = true;
        }

        return eliminar;
    }

    @Override
    public void guardarEnArchivo() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_TIPO))) {
            if (inicio != null) {  // Comprobamos si la lista no está vacía
                Nodo temp = inicio;
                do {
                    if (temp.getTipo() != null) {  // Verificamos que el tipo no sea null
                        writer.write(temp.getTipo().getIdTipo() + ";" + temp.getTipo().getNombre());  // Escribimos el ID y nombre
                        writer.newLine();  // Nueva línea para el siguiente registro
                    }
                    temp = temp.getSiguiente();  // Avanzamos al siguiente nodo
                } while (temp != inicio);  // Volvemos al inicio cuando se recorre toda la lista
            }
        } catch (IOException e) {
            JOptionPane.showConfirmDialog(null, "Error al guardar los tipos", "ERROR", JOptionPane.ERROR_MESSAGE);
        }
    }

    @Override
    public void cargarDatos() {
        File fileMarcas = new File(FILE_TIPO);
        if (fileMarcas.exists()) {
            try (BufferedReader reader = new BufferedReader(new FileReader(fileMarcas))) {
                String linea;
                while ((linea = reader.readLine()) != null) {
                    String[] datos = linea.split(";");
                    int id = Integer.parseInt(datos[0]);
                    String nombre = datos[1];
                    Nodo nuevo = new Nodo(null, new Tipo(id, nombre));  // Creamos el nuevo nodo

                    if (inicio == null) {  // Si la lista está vacía
                        inicio = nuevo;  // El nuevo nodo es el inicio
                        nuevo.setSiguiente(inicio);  // El siguiente del nuevo nodo es él mismo, formando un ciclo
                    } else {
                        Nodo temp = inicio;
                        // Buscamos el último nodo, que es el que apunta al inicio
                        while (temp.getSiguiente() != inicio) {
                            temp = temp.getSiguiente();
                        }
                        temp.setSiguiente(nuevo);  // El último nodo apunta al nuevo nodo
                        nuevo.setSiguiente(inicio);  // El nuevo nodo apunta al inicio, cerrando el ciclo
                    }
                }
            } catch (IOException e) {
                JOptionPane.showConfirmDialog(null, "Error al cargar las marcas", "ERROR", JOptionPane.ERROR_MESSAGE);
            }
        }

    }

    @Override
    public int total() {
        int total = 0;
        if (inicio != null) {
            Nodo temp = inicio;
            do {
                // Aquí puedes realizar cualquier operación sobre el nodo, como acceder a su tipo.
                temp = temp.getSiguiente();
                total++;
            } while (temp != inicio); // El ciclo termina cuando volvemos al inicio
        }
        return total;
    }
}
