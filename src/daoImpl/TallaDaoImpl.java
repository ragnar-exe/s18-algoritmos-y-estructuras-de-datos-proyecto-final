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
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import javax.swing.JOptionPane;
import model.Producto;
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
        int id = 1;

        // Verificar si el archivo existe
        if (!Files.exists(Paths.get(FILE_IDSTALLAS))) {
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_IDSTALLAS))) {
                writer.write("0\n");
            } catch (IOException e) {
                JOptionPane.showConfirmDialog(null, "Error al crear el archivo idsmarcas", "ERROR", JOptionPane.ERROR_MESSAGE);
            }
        }

        // Leer el último código del archivo
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_IDSTALLAS))) {
            List<String> lines = Files.readAllLines(Paths.get(FILE_IDSTALLAS));
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

    public List<Talla> BuscarTalla(String texto) {
        List<Talla> resultado = new LinkedList<>();
        String valorBuscar = texto.toLowerCase(); // Convierte el texto a minúsculas para comparación

        // Aquí deberías iterar sobre tu arreglo bidimensional de tallas
        for (int i = 0; i < tallas.length; i++) {
            for (int j = 0; j < tallas[i].length; j++) {
                Talla talla = tallas[i][j]; // Obtén la talla

                if (talla != null) { // Asegúrate de que la talla no sea nula
                    boolean coincideConId = String.valueOf(talla.getIdTalla()).contains(valorBuscar);
                    boolean coincideConNumero = String.valueOf(talla.getNumero()).contains(valorBuscar);

                    // Si coincide con alguna de las condiciones, agregar a la lista de resultados
                    if (coincideConId || coincideConNumero) {
                        resultado.add(talla);
                    }
                }
            }
        }
        return resultado; // Retorna la lista de tallas encontradas
    }

    public Talla[][] ordenarTallasNumero() {
        // Crear una lista para almacenar las tallas no nulas
        List<Talla> tallitasList = new LinkedList<>();

        // Recoger tallas no nulas en una lista
        for (int i = 0; i < tallas.length; i++) {
            for (int j = 0; j < tallas[i].length; j++) {
                if (tallas[i][j] != null) {
                    tallitasList.add(tallas[i][j]);
                }
            }
        }

        // Convertir la lista a un array
        Talla[] tallitas = tallitasList.toArray(new Talla[0]);

        // Ordenar el array unidimensional
        Arrays.sort(tallitas); // Utiliza la implementación de compareTo en la clase Talla

        // Crear el array de tallas ordenadas
        Talla[][] tallasOrdenadas = new Talla[100][5];
        int incremento = 0;

        // Llenar el array 2D con tallas ordenadas
        for (int i = 0; i < tallasOrdenadas.length; i++) {
            for (int j = 0; j < tallasOrdenadas[i].length; j++) {
                if (incremento < tallitas.length) {
                    tallasOrdenadas[i][j] = tallitas[incremento++];
                } else {
                    break; // Salir si no hay más tallas
                }
            }
        }

        return tallasOrdenadas; // Devuelve el array de tallas ordenadas
    }

    public Talla[][] ordenarTallasNumeroDescendente() {
        // Crear una lista para almacenar las tallas no nulas
        List<Talla> tallitasList = new LinkedList<>();

        // Recoger tallas no nulas en una lista
        for (int i = 0; i < tallas.length; i++) {
            for (int j = 0; j < tallas[i].length; j++) {
                if (tallas[i][j] != null) {
                    tallitasList.add(tallas[i][j]);
                }
            }
        }

        // Convertir la lista a un array
        Talla[] tallitas = tallitasList.toArray(new Talla[0]);

        // Ordenar el array unidimensional en orden descendente
        Arrays.sort(tallitas, Collections.reverseOrder()); // Usa la implementación de compareTo en Talla

        // Crear el array de tallas ordenadas
        Talla[][] tallasOrdenadas = new Talla[100][5];
        int incremento = 0;

        // Llenar el array 2D con tallas ordenadas
        for (int i = 0; i < tallasOrdenadas.length; i++) {
            for (int j = 0; j < tallasOrdenadas[i].length; j++) {
                if (incremento < tallitas.length) {
                    tallasOrdenadas[i][j] = tallitas[incremento++];
                } else {
                    break; // Salir si no hay más tallas
                }
            }
        }

        return tallasOrdenadas; // Devuelve el array de tallas ordenadas
    }

    public Talla[][] ordenarTallasPorIdAscendente() {
        // Crear una lista para almacenar las tallas no nulas
        List<Talla> tallitasList = new LinkedList<>();

        // Recoger tallas no nulas en una lista
        for (int i = 0; i < tallas.length; i++) {
            for (int j = 0; j < tallas[i].length; j++) {
                if (tallas[i][j] != null) {
                    tallitasList.add(tallas[i][j]);
                }
            }
        }

        // Convertir la lista a un array
        Talla[] tallitas = tallitasList.toArray(new Talla[0]);

        // Ordenar el array unidimensional por ID en orden ascendente
        Arrays.sort(tallitas, Comparator.comparingInt(Talla::getIdTalla));

        // Crear el array de tallas ordenadas
        Talla[][] tallasOrdenadas = new Talla[100][5];
        int incremento = 0;

        // Llenar el array 2D con tallas ordenadas
        for (int i = 0; i < tallasOrdenadas.length; i++) {
            for (int j = 0; j < tallasOrdenadas[i].length; j++) {
                if (incremento < tallitas.length) {
                    tallasOrdenadas[i][j] = tallitas[incremento++];
                } else {
                    break; // Salir si no hay más tallas
                }
            }
        }

        return tallasOrdenadas; // Devuelve el array de tallas ordenadas
    }

    public Talla[][] ordenarTallasPorIdDescendente() {
        // Crear una lista para almacenar las tallas no nulas
        List<Talla> tallitasList = new LinkedList<>();

        // Recoger tallas no nulas en una lista
        for (int i = 0; i < tallas.length; i++) {
            for (int j = 0; j < tallas[i].length; j++) {
                if (tallas[i][j] != null) {
                    tallitasList.add(tallas[i][j]);
                }
            }
        }

        // Convertir la lista a un array
        Talla[] tallitas = tallitasList.toArray(new Talla[0]);

        // Ordenar el array unidimensional por ID en orden descendente
        Arrays.sort(tallitas, Comparator.comparingInt(Talla::getIdTalla).reversed());

        // Crear el array de tallas ordenadas
        Talla[][] tallasOrdenadas = new Talla[100][5];
        int incremento = 0;

        // Llenar el array 2D con tallas ordenadas
        for (int i = 0; i < tallasOrdenadas.length; i++) {
            for (int j = 0; j < tallasOrdenadas[i].length; j++) {
                if (incremento < tallitas.length) {
                    tallasOrdenadas[i][j] = tallitas[incremento++];
                } else {
                    break; // Salir si no hay más tallas
                }
            }
        }

        return tallasOrdenadas; // Devuelve el array de tallas ordenadas
    }

}
