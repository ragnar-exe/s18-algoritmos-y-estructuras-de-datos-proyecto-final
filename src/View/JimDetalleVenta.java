package View;

import daoImpl.CategoriaDaoImpl;
import daoImpl.ClienteDaoImpl;
import daoImpl.ColorDaoImpl;
import daoImpl.ContieneDaoImpl;
import daoImpl.DetalleVentaDaoImpl;
import daoImpl.MarcaDaoImpl;
import daoImpl.ProductoDaoImpl;
import daoImpl.TallaDaoImpl;
import daoImpl.VentaDaoImpl;
import java.time.LocalDateTime;
import java.util.List;
import javax.swing.JComboBox;
import javax.swing.JDesktopPane;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;
import model.Cliente;
import model.Contiene;
import model.DetalleVenta;
import model.Nodo;
import model.Producto;
import model.Venta;

public class JimDetalleVenta extends javax.swing.JInternalFrame {

    public static JDesktopPane jDesktopPane_menu;
    private VentaDaoImpl IDaoVenta;
    private final DetalleVentaDaoImpl crudDetalleVenta;
    private ContieneDaoImpl IDaoContiene;
    private DefaultTableModel modelo;
    private int idClienteVenta = 0;
    private int totalRegistro = 0;
    private final ProductoDaoImpl IDaoProducto;
    private final CategoriaDaoImpl IDaoCategoria = new CategoriaDaoImpl();
    private final MarcaDaoImpl IDaoMarca = new MarcaDaoImpl();
    private final TallaDaoImpl IDaoTalla = new TallaDaoImpl();
    private final ColorDaoImpl IDaoColor = new ColorDaoImpl();
    private final ClienteDaoImpl IDaoCliente;
    private final Object[] filaDatos;
    private int idDVenta;
    private boolean guardar = false;
    private int idVentaDetalle = 0;

    public JimDetalleVenta() {
        initComponents();
        int ancho = java.awt.Toolkit.getDefaultToolkit().getScreenSize().width;
        int alto = java.awt.Toolkit.getDefaultToolkit().getScreenSize().height;
        this.setSize(ancho, alto - 106);
        filaDatos = new Object[9];
        crudDetalleVenta = new DetalleVentaDaoImpl();
        IDaoProducto = new ProductoDaoImpl();
        IDaoCliente = new ClienteDaoImpl();
        IDaoContiene = new ContieneDaoImpl();
        modelo = new DefaultTableModel();
        IDaoVenta = new VentaDaoImpl();
        idVentaDetalle = IDaoVenta.obtenerUltimoId();

        cargarClientes();
        cargarIdsContiene();
//        cargarProductos();
        habilitarCampo(false);
        registroBotones(false);
        crudBotones(false);
        limpiarFiltroIds();
        habilitarFiltroIds(false);
        listarDVentas();
        btnNuevo.requestFocus();
    }

//    private void cargarProductos() {
//        cboProducto.removeAllItems();
//        cboProducto.addItem("Seleccionar");
//        Nodo temp = IDaoContiene.inicio;
//        while (temp != null) {
//            cboProducto.addItem(IDaoProducto.obtenerNombre(temp.getContiene().getIdProducto()));
//            temp = temp.siguiente;
//        }
//    }
    public void cargarDatosVenta(int idVentaCliente) {
        btnRegresar.setVisible(true);

        VentaDaoImpl crudVenta = new VentaDaoImpl();
        DetalleVentaDaoImpl crudDetalle = new DetalleVentaDaoImpl();

        // Obtén la compra por ID
        Venta compra = crudVenta.obtenerPorId(idVentaCliente);
        if (compra != null) {
            habilitarCampo(false);

            // Luego cargas los datos de la compra
            DefaultTableModel modeloDetalle = (DefaultTableModel) tblDVenta.getModel();
            modeloDetalle.setRowCount(0); // Limpia la tabla
            for (DetalleVenta dv : crudDetalle.listarPorIdVenta(idVentaCliente)) {
                if (dv != null) {
                    filaDatos[0] = dv.getIdDVenta();
                    Nodo aux = IDaoContiene.inicio;
                    while (aux != null) {
                        if (aux.getContiene().getIdContiene() == dv.getIdProducto()) {
                            filaDatos[1] = IDaoProducto.obtenerNombre(aux.getContiene().getIdProducto());
                            filaDatos[2] = IDaoCategoria.obtenerNombre(IDaoProducto.obtenerCategoria(aux.getContiene().getIdProducto()));
                            filaDatos[3] = IDaoMarca.obtenerNombre(aux.getContiene().getIdMarca());
                            filaDatos[4] = IDaoTalla.obtenerNombre(aux.getContiene().getIdTalla());
                            filaDatos[5] = IDaoColor.obtenerNombre(aux.getContiene().getIdColor());
                        }
                        aux = aux.siguiente;
                    }
                    filaDatos[6] = dv.getPrecio();
                    filaDatos[7] = dv.getCantidad();
                    filaDatos[8] = dv.getCantidad() * dv.getPrecio();
                    modelo.addRow(filaDatos);
                }
            }

            for (Cliente c : IDaoCliente.listar()) {
                if (c != null && c.getIdPersona() == IDaoVenta.obtenerIdCliente(idVentaCliente)) {
                    taCliente.setEditable(true);
                    taCliente.setText("ID: " + c.getIdPersona() + "\nDNI: " + c.getDni() + "\nNombres: " + c.getNombres() + "\nApellidos: " + c.getApellidos() + "\nCorreo: " + c.getCorreo() + "\nDireccion: " + c.getDireccion());
                    taCliente.setEditable(false);
                }
            }
        } else {
            JOptionPane.showMessageDialog(this, "No se encontraron datos para la compra seleccionada.");
        }
    }

    public void cargarDatosCompraEditar(int idVentaCliente) {
        btnRegresar.setVisible(true);
//        compraGuardar= editar;
        idVentaCliente = idVentaCliente;
        VentaDaoImpl crudVenta = new VentaDaoImpl();
        DetalleVentaDaoImpl crudDetalle = new DetalleVentaDaoImpl();

        // Obtén la compra por ID
        Venta compra = crudVenta.obtenerPorId(idVentaCliente);
        if (compra != null) {
            habilitarCampo(false);

            // Luego cargas los datos de la compra
            DefaultTableModel modeloDetalle = (DefaultTableModel) tblDVenta.getModel();
            modeloDetalle.setRowCount(0); // Limpia la tabla
            for (DetalleVenta dv : crudDetalle.listarPorIdVenta(idVentaCliente)) {
                // Aquí es donde manejas la carga de los datos del detalle de la compra
                if (dv != null) {
                    filaDatos[0] = dv.getIdDVenta();
                    Nodo aux = IDaoContiene.inicio;
                    while (aux != null) {
                        if (aux.getContiene().getIdContiene() == dv.getIdProducto()) {
                            filaDatos[1] = IDaoProducto.obtenerNombre(aux.getContiene().getIdProducto());
                            filaDatos[2] = IDaoCategoria.obtenerNombre(IDaoProducto.obtenerCategoria(aux.getContiene().getIdProducto()));
                            filaDatos[3] = IDaoMarca.obtenerNombre(aux.getContiene().getIdMarca());
                            filaDatos[4] = IDaoTalla.obtenerNombre(aux.getContiene().getIdTalla());
                            filaDatos[5] = IDaoColor.obtenerNombre(aux.getContiene().getIdColor());
                        }
                        aux = aux.siguiente;
                    }
                    filaDatos[6] = dv.getPrecio();
                    filaDatos[7] = dv.getCantidad();
                    filaDatos[8] = dv.getCantidad() * dv.getPrecio();
                    modelo.addRow(filaDatos);
                }
            }
        } else {
            JOptionPane.showMessageDialog(this, "No se encontraron datos para la compra seleccionada.");
        }

        for (Cliente c : IDaoCliente.listar()) {
            if (c != null && c.getIdPersona() == IDaoVenta.obtenerIdCliente(idVentaCliente)) {
                taCliente.setEditable(true);
                taCliente.setText("ID: " + c.getIdPersona() + "\nDNI: " + c.getDni() + "\nNombres: " + c.getNombres() + "\nApellidos: " + c.getApellidos() + "\nCorreo: " + c.getCorreo() + "\nDireccion: " + c.getDireccion());
                taCliente.setEditable(false);
            }
        }

        txtTotal.setEditable(true);
        txtTotal.setText(crudDetalleVenta.calcularTotal(idVentaCliente) + "");
        btnGuardar.setVisible(false);
        btnCancelarDetalle.setVisible(false);
    }

    private void cargarClientes() {
        cboCliente.removeAllItems();
        cboCliente.addItem("Seleccionar");
        for (Cliente c : IDaoCliente.listar()) {
            if (c != null) {
                cboCliente.addItem(c.getDni());
            }
        }

    }

    private void cargarIdsContiene() {
        cboIdsContiene.removeAllItems();
        cboIdsContiene.addItem("Seleccionar");
        Nodo temp = IDaoContiene.inicio;
        while (temp != null) {
            if (temp.getContiene().getStock() > 0) {
                cboIdsContiene.addItem(String.valueOf(temp.getContiene().getIdContiene()));
            }
            temp = temp.siguiente;
        }
    }

    private void limpiarTabla() {
        modelo = (DefaultTableModel) tblDVenta.getModel();
        modelo.getDataVector().removeAllElements();
        tblDVenta.removeAll();
    }

    private void listarDVentas() {
        modelo = (DefaultTableModel) tblDVenta.getModel();
        for (DetalleVenta dv : crudDetalleVenta.listarDetalle()) {
            if (dv != null && dv.getIdVenta() == idVentaDetalle) {
                filaDatos[0] = dv.getIdDVenta();
                Nodo aux = IDaoContiene.inicio;
                while (aux != null) {
                    if (aux.getContiene().getIdContiene() == dv.getIdProducto()) {
                        filaDatos[1] = IDaoProducto.obtenerNombre(aux.getContiene().getIdProducto());
                        filaDatos[2] = IDaoCategoria.obtenerNombre(IDaoProducto.obtenerCategoria(aux.getContiene().getIdProducto()));
                        filaDatos[3] = IDaoMarca.obtenerNombre(aux.getContiene().getIdMarca());
                        filaDatos[4] = IDaoTalla.obtenerNombre(aux.getContiene().getIdTalla());
                        filaDatos[5] = IDaoColor.obtenerNombre(aux.getContiene().getIdColor());
                    }
                    aux = aux.siguiente;
                }
                filaDatos[6] = dv.getPrecio();
                filaDatos[7] = dv.getCantidad();
                filaDatos[8] = dv.getCantidad() * dv.getPrecio();
                modelo.addRow(filaDatos);
            }
        }
        if (crudDetalleVenta.total() > 1) {
            buscarCampo(true);
        } else {
            buscarCampo(false);
        }
        if (crudDetalleVenta.total() > 0) {
            cboCliente.setEnabled(false);
        }

        txtTotal.setEnabled(true);
        txtTotal.setText("" + crudDetalleVenta.calcularTotal());
        txtTotal.setEnabled(false);
    }

    private void registroBotones(boolean f) {
        btnAgregar.setEnabled(f);
        btnCancelar.setEnabled(f);
    }

    private void crudBotones(boolean f) {
        btnNuevo.setEnabled(!f);
        btnEditar.setEnabled(f);
        btnEliminar.setEnabled(f);

    }

    private void habilitarCampo(boolean f) {
        cboIdsContiene.setEnabled(f);
        cboCliente.setEnabled(f);
        txtCantidad.setEnabled(f);
        txtPrecio.setEnabled(f);
    }

    private void habilitarFiltroIds(boolean f) {
        txtProducto.setEnabled(f);
        txtMarca.setEnabled(f);
        txtTalla.setEnabled(f);
        txtColor.setEnabled(f);
    }

    private void editarFiltroIds(boolean f) {
        txtProducto.setEditable(f);
        txtMarca.setEditable(f);
        txtTalla.setEditable(f);
        txtColor.setEditable(f);
    }

    private void limpiarFiltroIds() {
        txtProducto.setText("");
        txtMarca.setText("");
        txtTalla.setText("");
        txtColor.setText("");
    }

    private void limpiarCampos() {
        cboIdsContiene.setSelectedIndex(0);
        cboCliente.setSelectedIndex(0);
        txtCantidad.setText("");
        txtPrecio.setText("");
    }

    private void buscarCampo(boolean f) {
        txtBuscar.setText("");
        txtBuscar.setEnabled(f);
    }

    public boolean obtenerDatosComboBox(JComboBox<String> comboBox, String idCon) {
        int itemCount = comboBox.getItemCount();
        for (int i = 0; i < itemCount; i++) {
            String item = comboBox.getItemAt(i);
            if (item.equalsIgnoreCase(idCon)) {
                return true;
            }
        }
        return false;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lblMensaje = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        txtPrecio = new javax.swing.JTextField();
        txtCantidad = new javax.swing.JTextField();
        cboCliente = new javax.swing.JComboBox<>();
        btnNuevo = new javax.swing.JButton();
        btnAgregar = new javax.swing.JButton();
        btnCancelar = new javax.swing.JButton();
        btnEditar = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblDVenta = new javax.swing.JTable();
        btnEliminar = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        txtBuscar = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        txtTotal = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        cboIdsContiene = new javax.swing.JComboBox<>();
        jLabel6 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        txtMarca = new javax.swing.JTextField();
        txtProducto = new javax.swing.JTextField();
        txtTalla = new javax.swing.JTextField();
        txtColor = new javax.swing.JTextField();
        btnGuardar = new javax.swing.JButton();
        btnCancelarDetalle = new javax.swing.JButton();
        jLabel12 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        taCliente = new javax.swing.JTextArea();
        btnRegresar = new javax.swing.JButton();

        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblMensaje.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        getContentPane().add(lblMensaje, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 230, 580, 30));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 30)); // NOI18N
        jLabel1.setText("Detalle de Venta");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 0, -1, -1));

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel4.setText("Producto:");
        getContentPane().add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 60, 70, 30));

        jLabel7.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel7.setText("Talla:");
        getContentPane().add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 140, 50, 30));

        jLabel8.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel8.setText("Cantidad:");
        getContentPane().add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 100, -1, 30));

        jLabel9.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel9.setText("Cliente:");
        getContentPane().add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 140, 50, 30));

        txtPrecio.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        getContentPane().add(txtPrecio, new org.netbeans.lib.awtextra.AbsoluteConstraints(630, 60, 240, -1));

        txtCantidad.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        getContentPane().add(txtCantidad, new org.netbeans.lib.awtextra.AbsoluteConstraints(630, 100, 240, -1));

        cboCliente.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        cboCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboClienteActionPerformed(evt);
            }
        });
        getContentPane().add(cboCliente, new org.netbeans.lib.awtextra.AbsoluteConstraints(630, 140, 240, -1));

        btnNuevo.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnNuevo.setText("Nuevo");
        btnNuevo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNuevoActionPerformed(evt);
            }
        });
        getContentPane().add(btnNuevo, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 190, 90, 30));

        btnAgregar.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnAgregar.setText("Agregar");
        btnAgregar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAgregarActionPerformed(evt);
            }
        });
        getContentPane().add(btnAgregar, new org.netbeans.lib.awtextra.AbsoluteConstraints(670, 190, 90, 30));

        btnCancelar.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnCancelar.setText("Cancelar");
        btnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarActionPerformed(evt);
            }
        });
        getContentPane().add(btnCancelar, new org.netbeans.lib.awtextra.AbsoluteConstraints(780, 190, 90, 30));

        btnEditar.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnEditar.setText("Editar");
        btnEditar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditarActionPerformed(evt);
            }
        });
        getContentPane().add(btnEditar, new org.netbeans.lib.awtextra.AbsoluteConstraints(970, 260, 90, 30));

        tblDVenta.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Producto", "Categoria", "Marca", "Talla", "Color", "Precio", "Cantidad", "Total"
            }
        ));
        tblDVenta.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                tblDVentaMouseReleased(evt);
            }
        });
        jScrollPane1.setViewportView(tblDVenta);

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 300, 1170, 250));

        btnEliminar.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnEliminar.setText("Eliminar");
        btnEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarActionPerformed(evt);
            }
        });
        getContentPane().add(btnEliminar, new org.netbeans.lib.awtextra.AbsoluteConstraints(1070, 260, 110, 30));

        jLabel2.setText("Buscar:");
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 260, -1, 30));

        txtBuscar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtBuscarKeyReleased(evt);
            }
        });
        getContentPane().add(txtBuscar, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 260, 310, 30));

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel3.setText("Total:");
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(1070, 560, 50, 30));
        getContentPane().add(txtTotal, new org.netbeans.lib.awtextra.AbsoluteConstraints(1120, 560, 60, 30));

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel5.setText("ID:");
        getContentPane().add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 60, 20, 30));

        cboIdsContiene.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Seleccionar" }));
        cboIdsContiene.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboIdsContieneActionPerformed(evt);
            }
        });
        getContentPane().add(cboIdsContiene, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 60, 110, 30));

        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel6.setText("Marca:");
        getContentPane().add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 100, 50, 30));

        jLabel10.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel10.setText("Precio:");
        getContentPane().add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 60, 50, 30));

        jLabel11.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel11.setText("Color:");
        getContentPane().add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 180, 50, 30));
        getContentPane().add(txtMarca, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 100, 190, 30));
        getContentPane().add(txtProducto, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 60, 190, 30));
        getContentPane().add(txtTalla, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 140, 190, 30));
        getContentPane().add(txtColor, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 180, 190, 30));

        btnGuardar.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnGuardar.setText("Guardar");
        btnGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarActionPerformed(evt);
            }
        });
        getContentPane().add(btnGuardar, new org.netbeans.lib.awtextra.AbsoluteConstraints(860, 560, -1, 30));

        btnCancelarDetalle.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnCancelarDetalle.setText("Cancelar");
        btnCancelarDetalle.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarDetalleActionPerformed(evt);
            }
        });
        getContentPane().add(btnCancelarDetalle, new org.netbeans.lib.awtextra.AbsoluteConstraints(950, 560, -1, 30));

        jLabel12.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel12.setText("Datos del Cliente");
        getContentPane().add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(900, 60, -1, -1));

        taCliente.setColumns(20);
        taCliente.setRows(5);
        jScrollPane2.setViewportView(taCliente);

        getContentPane().add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(900, 90, 280, 120));

        btnRegresar.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        btnRegresar.setText("REGRESAR");
        btnRegresar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRegresarActionPerformed(evt);
            }
        });
        getContentPane().add(btnRegresar, new org.netbeans.lib.awtextra.AbsoluteConstraints(1070, 10, 110, 30));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnNuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNuevoActionPerformed
        cargarIdsContiene();
        habilitarCampo(true);
        limpiarCampos();
        registroBotones(true);
        limpiarFiltroIds();
        habilitarFiltroIds(true);
        crudBotones(false);
        limpiarTabla();
        buscarCampo(false);
        btnNuevo.setEnabled(false);
        lblMensaje.setText("");
        tblDVenta.clearSelection();
        cboCliente.requestFocus();
        guardar = false;
        cboIdsContiene.requestFocus();
        listarDVentas();
    }//GEN-LAST:event_btnNuevoActionPerformed

    private void btnAgregarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregarActionPerformed
        String cantidadStr = txtCantidad.getText().strip();
        String precioStr = txtPrecio.getText().strip();

        // Validar que se haya seleccionado un producto
        if (cboIdsContiene.getSelectedItem().equals("Seleccionar")) {
            JOptionPane.showMessageDialog(null,
                    "Advertencia, debe seleccionar un producto.",
                    "Advertencia", JOptionPane.WARNING_MESSAGE);
            cboIdsContiene.requestFocus();
            return;
        }

        // Validar que se haya seleccionado un cliente
        // Validar que la cantidad sea un número flotante positivo
        float cantidad;
        try {
            cantidad = Float.parseFloat(cantidadStr);
            if (cantidad <= 0) {
                JOptionPane.showMessageDialog(null,
                        "Advertencia, la cantidad debe ser mayor a cero.",
                        "Advertencia", JOptionPane.WARNING_MESSAGE);
                txtCantidad.requestFocus();
                return;
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null,
                    "Advertencia, la cantidad debe ser un número válido.",
                    "Advertencia", JOptionPane.WARNING_MESSAGE);
            txtCantidad.requestFocus();
            return;
        }

        // Validar que el precio sea un número flotante positivo
        float precio;
        try {
            precio = Float.parseFloat(precioStr);
            if (precio <= 0) {
                JOptionPane.showMessageDialog(null,
                        "Advertencia, el precio debe ser mayor a cero.",
                        "Advertencia", JOptionPane.WARNING_MESSAGE);
                txtPrecio.requestFocus();
                return;
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null,
                    "Advertencia, el precio debe ser un número válido.",
                    "Advertencia", JOptionPane.WARNING_MESSAGE);
            txtPrecio.requestFocus();
            return;
        }

        // Obtener los IDs de producto y cliente a través de sus DAO
        int idContiene = Integer.parseInt(cboIdsContiene.getSelectedItem().toString());

        // Si 'guardar' es verdadero, actualizar el producto; si no, agregar uno nuevo
        if (guardar) {
            if (crudDetalleVenta.total() == 1) {
                if (cboCliente.getSelectedItem().equals("Seleccionar")) {
                    JOptionPane.showMessageDialog(null,
                            "Advertencia, debe seleccionar un cliente.",
                            "Advertencia", JOptionPane.WARNING_MESSAGE);
                    cboCliente.requestFocus();
                    return;
                }
            }
            int idClient = IDaoCliente.obtenerId(cboCliente.getSelectedItem().toString());
            if (crudDetalleVenta.actualizar(new DetalleVenta(idDVenta, idContiene, idClient, Integer.parseInt(cantidadStr), precio, cantidad * precio, idVentaDetalle))) {
                lblMensaje.setText("Se actualizó correctamente el detalle con id " + idDVenta + ".");
                limpiarCampos();
                habilitarCampo(false);
                limpiarFiltroIds();
                habilitarFiltroIds(false);
                registroBotones(false);
                crudBotones(false);
                guardar = false;
            } else {
                lblMensaje.setText("No se actualizó el detalle con id " + idDVenta + ".");
            }
        } else {
            if (crudDetalleVenta.total() == 0) {
                if (cboCliente.getSelectedItem().equals("Seleccionar")) {
                    JOptionPane.showMessageDialog(null,
                            "Advertencia, debe seleccionar un cliente.",
                            "Advertencia", JOptionPane.WARNING_MESSAGE);
                    cboCliente.requestFocus();
                    return;
                }
            }
            int idCliente = IDaoCliente.obtenerId(cboCliente.getSelectedItem().toString());
            if (crudDetalleVenta.agregar(new DetalleVenta(crudDetalleVenta.obtenerUltimoId(), idContiene, idCliente, Integer.parseInt(cantidadStr), precio, cantidad * precio, idVentaDetalle))) {
                lblMensaje.setText("Se agregó correctamente el detalle.");
                limpiarCampos();
                habilitarCampo(false);
                limpiarFiltroIds();
                habilitarFiltroIds(false);
                registroBotones(false);
                crudBotones(false);
            } else {
                lblMensaje.setText("No se agregó el detalle.");
            }
        }

        tblDVenta.clearSelection();
        limpiarTabla();
        listarDVentas();
    }//GEN-LAST:event_btnAgregarActionPerformed

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        limpiarCampos();
        limpiarTabla();
        habilitarCampo(false);
        crudBotones(false);
        limpiarFiltroIds();
        habilitarFiltroIds(false);
        registroBotones(false);
        lblMensaje.setText("");
        tblDVenta.clearSelection();
        guardar = false;
        btnNuevo.requestFocus();
        listarDVentas();
    }//GEN-LAST:event_btnCancelarActionPerformed

    private void btnEditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditarActionPerformed
        guardar = true;
        habilitarCampo(true);
        habilitarFiltroIds(true);
        registroBotones(true);
        crudBotones(false);
        limpiarTabla();
        listarDVentas();
        buscarCampo(false);
        btnNuevo.setEnabled(false);
        lblMensaje.setText("");
        tblDVenta.clearSelection();
        cboIdsContiene.requestFocus();
//        cboProducto.requestFocus();
    }//GEN-LAST:event_btnEditarActionPerformed

    private void btnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarActionPerformed
        int fila = tblDVenta.getSelectedRow();
        if (fila < 0) {
            JOptionPane.showMessageDialog(null, "Debe seleccionar una fila");
        } else {
            if (JOptionPane.showConfirmDialog(null, "Desea eliminar el registro", "Eliminar", JOptionPane.YES_NO_OPTION, 3) == 0) {
                if (crudDetalleVenta.eliminar(new DetalleVenta(idDVenta))) {
                    lblMensaje.setText("El registro se eliminó correctamente");
                } else {
                    lblMensaje.setText("El registro NO se pudo eliminar");
                }
            }
            buscarCampo(true);
            limpiarTabla();
            listarDVentas();
            limpiarCampos();
            registroBotones(false);
            crudBotones(false);
            txtCantidad.setText("");
            txtPrecio.setText("");
            tblDVenta.clearSelection();
        }
    }//GEN-LAST:event_btnEliminarActionPerformed

    private void tblDVentaMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblDVentaMouseReleased
        int fila = tblDVenta.getSelectedRow();
        if (fila < 0) {
            JOptionPane.showMessageDialog(null,
                    "Debe seleccionar una fila.",
                    "Advertencia",
                    JOptionPane.WARNING_MESSAGE);
        } else {
            habilitarCampo(false);
            habilitarFiltroIds(false);
            idDVenta = Integer.parseInt(tblDVenta.getValueAt(fila, 0).toString());
            boolean esta = obtenerDatosComboBox(cboIdsContiene, String.valueOf(crudDetalleVenta.obtenerIdProducto(idDVenta)));
            if (!esta) {
                cboIdsContiene.addItem(String.valueOf(crudDetalleVenta.obtenerIdProducto(idDVenta)));
            }
            cboIdsContiene.setSelectedItem(String.valueOf(crudDetalleVenta.obtenerIdProducto(idDVenta)));
            txtPrecio.setText(tblDVenta.getValueAt(fila, 6).toString());
            txtCantidad.setText(tblDVenta.getValueAt(fila, 7).toString());
            if (crudDetalleVenta.total() == 1) {
                cboCliente.setSelectedItem(IDaoCliente.obtenerNombre(crudDetalleVenta.obtenerIdCliente(idDVenta)));
                System.out.println("if" + IDaoCliente.obtenerNombre(crudDetalleVenta.obtenerIdCliente(idDVenta)));
            }
            lblMensaje.setText("");
            buscarCampo(false);
            registroBotones(false);
            crudBotones(true);
            btnCancelar.setEnabled(true);
        }
    }//GEN-LAST:event_tblDVentaMouseReleased

    private void txtBuscarKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBuscarKeyReleased
        limpiarTabla();  // Limpia la tabla antes de mostrar los resultados
        int n = 0;       // Contador de resultados
        String valorBuscar = txtBuscar.getText().strip();     // Obtiene el texto de búsqueda
        if (valorBuscar.equalsIgnoreCase("")) {
            // Si el campo de búsqueda está vacío, muestra todos los productos
            limpiarTabla();
            listarDVentas();  // Método para listar todos los productos
            lblMensaje.setText("");
        } else {
            // Llama al método buscar() de ProductoDaoImpl
            List<DetalleVenta> detallesEncontrados = crudDetalleVenta.listar(valorBuscar);

            // Itera sobre los productos encontrados y los agrega a la tabla
            for (DetalleVenta dv : detallesEncontrados) {
                filaDatos[0] = dv.getIdDVenta();
                filaDatos[1] = IDaoProducto.obtenerNombre(dv.getIdProducto());
                filaDatos[2] = dv.getPrecio();
                filaDatos[3] = dv.getCantidad();
                filaDatos[4] = IDaoCliente.obtenerNombre(dv.getIdCliente());
                filaDatos[5] = dv.getCantidad() * dv.getPrecio();
                modelo.addRow(filaDatos);  // Agrega los datos a la tabla
                n++;  // Incrementa el contador de resultados
            }

            lblMensaje.setText(n + " registros encontrados.");  // Muestra el número de resultados encontrados
        }
        txtCantidad.setText("");  // Limpia el campo de nombre
        txtPrecio.setText("");
    }//GEN-LAST:event_txtBuscarKeyReleased

    private void cboIdsContieneActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboIdsContieneActionPerformed
        Object idCon = cboIdsContiene.getSelectedItem();
        if (idCon != null) {
            String idFiltro = idCon.toString();
            editarFiltroIds(true);
            limpiarFiltroIds();
            if (idFiltro.equalsIgnoreCase("Seleccionar")) {
            } else {
                int idCont = Integer.parseInt(idFiltro);
                Nodo temp = IDaoContiene.inicio;
                while (temp != null) {
                    if (temp.getContiene().getIdContiene() == idCont) {
                        txtProducto.setText(IDaoProducto.obtenerNombre(temp.getContiene().getIdProducto()));
                        // IDaoCategoria.obtenerNombre(IDaoProducto.obtenerCategoria(temp.getContiene().getIdProducto()))
                        txtMarca.setText(IDaoMarca.obtenerNombre(temp.getContiene().getIdMarca()));
                        txtTalla.setText(IDaoTalla.obtenerNombre(temp.getContiene().getIdTalla()));
                        txtColor.setText(IDaoColor.obtenerNombre(temp.getContiene().getIdColor()));
                    }
                    temp = temp.siguiente;
                }
            }
            editarFiltroIds(false);
        }
    }//GEN-LAST:event_cboIdsContieneActionPerformed

    private void cboClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboClienteActionPerformed
        // TODO add your handling code here:
        Object selectedItem = cboCliente.getSelectedItem();
        if (selectedItem != null) {
            String valorProveedor = selectedItem.toString();
            for (Cliente cliente : IDaoCliente.listar()) {
                if (cliente != null && cliente.getDni().equalsIgnoreCase(valorProveedor)) {
                    idClienteVenta = cliente.getIdPersona();

                    taCliente.setText("ID: " + cliente.getIdPersona() + "\nDNI: " + cliente.getDni() + "\nNombres: " + cliente.getNombres() + "\nApellidos: " + cliente.getApellidos() + "\nCorreo: " + cliente.getCorreo() + "\nDireccion: " + cliente.getDireccion());
                }
            }
        } else {
            System.out.println("No se seleccionó ningún proveedor.");
        }
    }//GEN-LAST:event_cboClienteActionPerformed

    private void btnRegresarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRegresarActionPerformed
        // TODO add your handling code here:
        FrmMenu menu = (FrmMenu) SwingUtilities.getWindowAncestor(this);  // Obtiene la ventana principal (FrmMenu)
        JDesktopPane jDesktopPane_menu = menu.getDesktopPane();  // Accede al JDesktopPane

        // Crear la vista de detalles de venta
        JimVenta vistaVentas = new JimVenta();

        // Asegúrate de que jDesktopPane_menu no sea null
        if (jDesktopPane_menu != null) {
            // Agregar el JInternalFrame (vistaDVentas) al JDesktopPane
            jDesktopPane_menu.add(vistaVentas);

            // Hacer visible el JInternalFrame
            vistaVentas.setVisible(true);

            // Establecer el JInternalFrame en el centro
            try {
                vistaVentas.setSelected(true);  // Asegura que esté al frente
//                JimCompra cargarCompras = new JimCompra();
//                cargarCompras.listarCompras();
            } catch (java.beans.PropertyVetoException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("El JDesktopPane no está inicializado.");
        }
    }//GEN-LAST:event_btnRegresarActionPerformed

    private void btnCancelarDetalleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarDetalleActionPerformed
        // TODO add your handling code here:
        if (totalRegistro > 0) {
            for (DetalleVenta dv : crudDetalleVenta.listarDetalle()) {
                if (dv != null && dv.getIdVenta()== IDaoVenta.obtenerUltimoId()) {
                    crudDetalleVenta.eliminar(new DetalleVenta(dv.getIdVenta(), dv.getIdProducto(), dv.getIdCliente(), dv.getCantidad(), dv.getPrecio(), dv.getTotal(), dv.getIdVenta()));
                    crudDetalleVenta.guardarEnArchivo();

                }
            }

            crudDetalleVenta.clearIdVenta(new Venta(IDaoVenta.obtenerUltimoId()));
            System.out.println("conficonal if");
        }
        FrmMenu menu = (FrmMenu) SwingUtilities.getWindowAncestor(this);  // Obtiene la ventana principal (FrmMenu)
        JDesktopPane jDesktopPane_menu = menu.getDesktopPane();  // Accede al JDesktopPane

        // Crear la vista de detalles de venta
        JimVenta vistaVenta = new JimVenta();

        // Asegúrate de que jDesktopPane_menu no sea null
        if (jDesktopPane_menu != null) {
            // Agregar el JInternalFrame (vistaDVentas) al JDesktopPane
            jDesktopPane_menu.add(vistaVenta);

            // Hacer visible el JInternalFrame
            vistaVenta.setVisible(true);

            // Establecer el JInternalFrame en el centro
            try {
                vistaVenta.setSelected(true);  // Asegura que esté al frente
//                JimCompra cargarCompras = new JimCompra();
//                cargarCompras.listarCompras();
            } catch (java.beans.PropertyVetoException e) {
                e.printStackTrace();
            }
        }
    }//GEN-LAST:event_btnCancelarDetalleActionPerformed

    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed
        // TODO add your handling code here:
        LocalDateTime hoy = LocalDateTime.now();
        String fechaYhora = hoy.getDayOfMonth() + "-" + hoy.getMonthValue() + "-" + hoy.getYear() + " " + hoy.getHour() + ":" + hoy.getMinute() + ":" + hoy.getSecond();
        float totalImpu = crudDetalleVenta.calcularTotal(IDaoVenta.obtenerUltimoId()) / 1.18f;
        float totalCompra = totalImpu + crudDetalleVenta.calcularTotal(IDaoVenta.obtenerUltimoId());
        IDaoVenta.agregar(new Venta(IDaoVenta.obtenerUltimoId(), idClienteVenta, crudDetalleVenta.calcularTotal(IDaoVenta.obtenerUltimoId()), totalCompra, totalImpu, fechaYhora, true));
        IDaoVenta.guardarEnArchivo();
        FrmMenu menu = (FrmMenu) SwingUtilities.getWindowAncestor(this);  // Obtiene la ventana principal (FrmMenu)
        JDesktopPane jDesktopPane_menu = menu.getDesktopPane();  // Accede al JDesktopPane

        // Crear la vista de detalles de venta
        JimVenta vistaVentas = new JimVenta();

        // Asegúrate de que jDesktopPane_menu no sea null
        if (jDesktopPane_menu != null) {
            // Agregar el JInternalFrame (vistaDVentas) al JDesktopPane
            jDesktopPane_menu.add(vistaVentas);

            // Hacer visible el JInternalFrame
            vistaVentas.setVisible(true);

            // Establecer el JInternalFrame en el centro
            try {
                vistaVentas.setSelected(true);  // Asegura que esté al frente
//                JimCompra cargarCompras = new JimCompra();
//                cargarCompras.listarCompras();
            } catch (java.beans.PropertyVetoException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("El JDesktopPane no está inicializado.");
        }
    }//GEN-LAST:event_btnGuardarActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAgregar;
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnCancelarDetalle;
    private javax.swing.JButton btnEditar;
    private javax.swing.JButton btnEliminar;
    private javax.swing.JButton btnGuardar;
    private javax.swing.JButton btnNuevo;
    private javax.swing.JButton btnRegresar;
    private javax.swing.JComboBox<String> cboCliente;
    private javax.swing.JComboBox<String> cboIdsContiene;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lblMensaje;
    private javax.swing.JTextArea taCliente;
    private javax.swing.JTable tblDVenta;
    private javax.swing.JTextField txtBuscar;
    private javax.swing.JTextField txtCantidad;
    private javax.swing.JTextField txtColor;
    private javax.swing.JTextField txtMarca;
    private javax.swing.JTextField txtPrecio;
    private javax.swing.JTextField txtProducto;
    private javax.swing.JTextField txtTalla;
    private javax.swing.JTextField txtTotal;
    // End of variables declaration//GEN-END:variables
}
