package View;

import daoImpl.CategoriaDaoImpl;
import daoImpl.ColorDaoImpl;
import daoImpl.CompraDaoImpl;
import daoImpl.DetalleCompraDaoImpl;
import daoImpl.MarcaDaoImpl;
import daoImpl.ProductoDaoImpl;
import daoImpl.ProveedorDaoImpl;
import daoImpl.TallaDaoImpl;
import java.time.LocalDateTime;
import javax.swing.JDesktopPane;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;
import model.Producto;
import model.DetalleCompra;
import model.Color;
import model.Proveedor;
import model.Compra;
import model.Nodo;

public class JimDetalleCompra extends javax.swing.JInternalFrame {

    private final CompraDaoImpl IDaoCompra;
    private int totalRegistro = 0;
    private String proveedorCorreo = "";
    private int idProveedorCompra = 0;
    private int idCompra = 0;
    private final DetalleCompraDaoImpl crudDetalleCompra;
    private DefaultTableModel modelo;
    private final ProductoDaoImpl IDaoProducto;
    private final CategoriaDaoImpl IDaoCategoria;
    private final MarcaDaoImpl IDaoMarca;
    private final ColorDaoImpl IDaoColor;
    private final ProveedorDaoImpl IDaoProveedor;
    private final TallaDaoImpl IDaoTalla;
    private final Object[] filaDatos;
    private int idDCompra;
    private boolean guardar = false;
    private boolean compraGuardar = false;
    private int idCompraDetalle = 0;

    public JimDetalleCompra() {
        initComponents();
        btnRegresart.setVisible(false);
        int ancho = java.awt.Toolkit.getDefaultToolkit().getScreenSize().width;
        int alto = java.awt.Toolkit.getDefaultToolkit().getScreenSize().height;
        this.setSize(ancho, alto - 106);
        filaDatos = new Object[9];
        crudDetalleCompra = new DetalleCompraDaoImpl();
        IDaoProducto = new ProductoDaoImpl();
        IDaoCategoria = new CategoriaDaoImpl();
        IDaoMarca = new MarcaDaoImpl();
        IDaoColor = new ColorDaoImpl();
        IDaoTalla = new TallaDaoImpl();
        IDaoCompra = new CompraDaoImpl();
        idCompraDetalle = IDaoCompra.obtenerUltimoId();
        IDaoProveedor = new ProveedorDaoImpl();
        modelo = new DefaultTableModel();
        cargarColores();
        cargarProveedores();
        cargarMarcas();
        cargarTallas();
        cargarProductos();
        listarDCompras();
        habilitarCampo(false);
        registroBotones(false);
        crudBotones(false);

    }

    private void cargarProductos() {
        cboProducto.removeAllItems();
        cboProducto.addItem("Seleccionar");
        for (Producto p : IDaoProducto.listar()) {
            if (p != null) {
                cboProducto.addItem(p.getNombre());
            }
        }
    }

    private void cargarMarcas() {
        cboMarca.removeAllItems();
        cboMarca.addItem("Seleccionar");
        Nodo temp = IDaoMarca.inicio;
        while (temp != null) {
            cboMarca.addItem(temp.getMarca().getNombre());
            temp = temp.siguiente;
        }
    }

    private void cargarColores() {
        cboColor.removeAllItems();
        cboColor.addItem("Seleccionar");
        for (Color c : IDaoColor.listar()) {
            if (c != null) {
                cboColor.addItem(c.getNombre());
            }
        }
    }

    private void cargarProveedores() {
        cboProveedor.removeAllItems();
        cboProveedor.addItem("Seleccionar");
        for (Proveedor pr : IDaoProveedor.listar()) {
            if (pr != null) {
                cboProveedor.addItem(pr.getCorreo());
            }
        }
    }

    private void cargarTallas() {
        cboTalla.removeAllItems();
        cboTalla.addItem("Seleccionar");
        for (int i = 0; i < 100; i++) {
            for (int j = 0; j < 5; j++) {
                if (IDaoTalla.listar(i, j) != null) {
                    cboTalla.addItem(String.valueOf(IDaoTalla.listar(i, j).getNumero()));
                }
            }
        }
    }

    private void limpiarTabla() {
        modelo = (DefaultTableModel) tblDCompra.getModel();
        modelo.getDataVector().removeAllElements();
        tblDCompra.removeAll();
    }

    public void bloquearAcciones() {
        btnAgregar.setEnabled(false);
        btnCancelar.setEnabled(false);
        btnCancelarCompra.setEnabled(false);
        btnEditar.setEnabled(false);
        btnEliminar.setEnabled(false);
        btnGuardarCompra.setEnabled(false);
        btnNuevo.setEnabled(false);
    }

    public void cargarDatosCompra(int idCompraCliente) {
        btnRegresart.setVisible(true);

        CompraDaoImpl crudCompra = new CompraDaoImpl();
        DetalleCompraDaoImpl crudDetalle = new DetalleCompraDaoImpl();

        // Obtén la compra por ID
        Compra compra = crudCompra.obtenerPorId(idCompraCliente);
        if (compra != null) {
            habilitarCampo(false);

            // Luego cargas los datos de la compra
            DefaultTableModel modeloDetalle = (DefaultTableModel) tblDCompra.getModel();
            modeloDetalle.setRowCount(0); // Limpia la tabla
            for (DetalleCompra dv : crudDetalle.listarPorIdCompra(idCompraCliente)) {
                // Aquí es donde manejas la carga de los datos del detalle de la compra
                if (dv != null) {
                    // Configura los valores en la tabla del detalle
                    filaDatos[0] = dv.getIdDCompra();
                    filaDatos[1] = IDaoProducto.obtenerNombre(dv.getIdProducto());
                    filaDatos[2] = IDaoCategoria.obtenerNombre(IDaoProducto.obtenerCategoria(dv.getIdProducto()));
                    filaDatos[3] = IDaoMarca.obtenerNombre(dv.getIdMarca());
                    filaDatos[4] = IDaoTalla.obtenerNombre(dv.getIdTalla());
                    filaDatos[5] = IDaoColor.obtenerNombre(dv.getIdColor());
                    filaDatos[6] = dv.getPrecio();
                    filaDatos[7] = dv.getCantidad();
                    filaDatos[8] = dv.getCantidad() * dv.getPrecio();
                    modeloDetalle.addRow(filaDatos);
                }
            }
        } else {
            JOptionPane.showMessageDialog(this, "No se encontraron datos para la compra seleccionada.");
        }

        for (Proveedor pro : IDaoProveedor.listar()) {
            if (pro != null && pro.getIdProveedor() == IDaoCompra.obtenerIdProveedor(idCompraCliente)) {
                taProveedor.setEditable(true);
                taProveedor.setText("ID: " + pro.getIdProveedor() + "\nNombres: " + pro.getNombres() + "\nApellidos: " + pro.getApellidos() + "\nCorreo: " + pro.getCorreo() + "\nTelefono: " + pro.getTelefono());
                taProveedor.setEditable(false);
            }
        }

        bloquearAcciones();
        txtTotal.setEditable(true);
        txtTotal.setText(crudDetalleCompra.calcularTotal(idCompraCliente) + "");
        tblDCompra.setEnabled(false);
    }

    public void cargarDatosCompraEditar(int idCompraCliente) {
        btnRegresart.setVisible(true);
//        compraGuardar= editar;
        idCompraDetalle = idCompraCliente;
        CompraDaoImpl crudCompra = new CompraDaoImpl();
        DetalleCompraDaoImpl crudDetalle = new DetalleCompraDaoImpl();

        // Obtén la compra por ID
        Compra compra = crudCompra.obtenerPorId(idCompraCliente);
        if (compra != null) {
            habilitarCampo(false);

            // Luego cargas los datos de la compra
            DefaultTableModel modeloDetalle = (DefaultTableModel) tblDCompra.getModel();
            modeloDetalle.setRowCount(0); // Limpia la tabla
            for (DetalleCompra dv : crudDetalle.listarPorIdCompra(idCompraCliente)) {
                // Aquí es donde manejas la carga de los datos del detalle de la compra
                if (dv != null) {
                    // Configura los valores en la tabla del detalle
                    filaDatos[0] = dv.getIdDCompra();
                    filaDatos[1] = IDaoProducto.obtenerNombre(dv.getIdProducto());
                    filaDatos[2] = IDaoCategoria.obtenerNombre(IDaoProducto.obtenerCategoria(dv.getIdProducto()));
                    filaDatos[3] = IDaoMarca.obtenerNombre(dv.getIdMarca());
                    filaDatos[4] = IDaoTalla.obtenerNombre(dv.getIdTalla());
                    filaDatos[5] = IDaoColor.obtenerNombre(dv.getIdColor());
                    filaDatos[6] = dv.getPrecio();
                    filaDatos[7] = dv.getCantidad();
                    filaDatos[8] = dv.getCantidad() * dv.getPrecio();
                    modeloDetalle.addRow(filaDatos);
                }
            }
        } else {
            JOptionPane.showMessageDialog(this, "No se encontraron datos para la compra seleccionada.");
        }

        for (Proveedor pro : IDaoProveedor.listar()) {
            if (pro != null && pro.getIdProveedor() == IDaoCompra.obtenerIdProveedor(idCompraCliente)) {
                taProveedor.setEditable(true);
                taProveedor.setText("ID: " + pro.getIdProveedor() + "\nNombres: " + pro.getNombres() + "\nApellidos: " + pro.getApellidos() + "\nCorreo: " + pro.getCorreo() + "\nTelefono: " + pro.getTelefono());
                taProveedor.setEditable(false);
            }
        }
        
        txtTotal.setEditable(true);
        txtTotal.setText(crudDetalleCompra.calcularTotal(idCompraCliente) + "");
        btnGuardarCompra.setVisible(false);
        btnCancelarCompra.setVisible(false);
    }

    private void listarDCompras() {
        totalRegistro = 0;
        modelo = (DefaultTableModel) tblDCompra.getModel();
        for (DetalleCompra dv : crudDetalleCompra.listarDetalle()) {
            if (dv != null && dv.getIdCompra() == idCompraDetalle) {
                filaDatos[0] = dv.getIdDCompra();
                filaDatos[1] = IDaoProducto.obtenerNombre(dv.getIdProducto());
                filaDatos[2] = IDaoCategoria.obtenerNombre(IDaoProducto.obtenerCategoria(dv.getIdProducto()));
                filaDatos[3] = IDaoMarca.obtenerNombre(dv.getIdMarca());
                filaDatos[4] = IDaoTalla.obtenerNombre(dv.getIdTalla());
                filaDatos[5] = IDaoColor.obtenerNombre(dv.getIdColor());
                filaDatos[6] = dv.getPrecio();
                filaDatos[7] = dv.getCantidad();
                filaDatos[8] = dv.getCantidad() * dv.getPrecio();
                totalRegistro++;
                modelo.addRow(filaDatos);
            }
        }
        txtTotal.setEditable(true);
        txtTotal.setText(crudDetalleCompra.calcularTotal(IDaoCompra.obtenerUltimoId()) + "");
        txtTotal.setEditable(false);

        if (totalRegistro > 0) {
            cboProveedor.setEnabled(false);
        }
//        if (crudDetalleCompra.total() > 1) {
//            buscarCampo(true);
//        } else {
//            buscarCampo(false);
//        }
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
        cboMarca.setEnabled(f);
        cboProveedor.setEnabled(f);
        cboColor.setEnabled(f);
        cboTalla.setEnabled(f);
        cboProducto.setEnabled(f);
        txtCantidad.setEnabled(f);
        txtPrecio.setEnabled(f);
    }

    private void limpiarCampos() {
        cboMarca.setSelectedIndex(0);
        cboProveedor.setSelectedIndex(0);
        cboColor.setSelectedIndex(0);
        cboTalla.setSelectedIndex(0);
        cboProducto.setSelectedIndex(0);
        txtCantidad.setText("");
        txtPrecio.setText("");
    }

    private void hablitarComboBox(boolean f) {
        cboProveedor.requestFocus(f);
        cboTalla.requestFocus(f);
        cboMarca.requestFocus(f);
        cboColor.requestFocus(f);
        cboProducto.requestFocus(f);
    }

//    private int obtenerIdCompra() {
//        int idCompra = IDaoCompra.obtenerUltimoId();
//        return idCompra;
//    }
    private void cargarProveedorCorreo() {
        for (DetalleCompra detalleCompra : crudDetalleCompra.listarDetalle()) {
            if (detalleCompra != null) {

            }
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        cboMarca = new javax.swing.JComboBox<>();
        cboProducto = new javax.swing.JComboBox<>();
        cboTalla = new javax.swing.JComboBox<>();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        lblMensaje = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        cboColor = new javax.swing.JComboBox<>();
        txtPrecio = new javax.swing.JTextField();
        txtCantidad = new javax.swing.JTextField();
        cboProveedor = new javax.swing.JComboBox<>();
        btnNuevo = new javax.swing.JButton();
        btnAgregar = new javax.swing.JButton();
        btnCancelar = new javax.swing.JButton();
        btnEditar = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblDCompra = new javax.swing.JTable();
        btnEliminar = new javax.swing.JButton();
        btnGuardarCompra = new javax.swing.JButton();
        btnCancelarCompra = new javax.swing.JButton();
        jLabel10 = new javax.swing.JLabel();
        txtTotal = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        taProveedor = new javax.swing.JTextArea();
        jLabel8 = new javax.swing.JLabel();
        btnRegresart = new javax.swing.JButton();

        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 30)); // NOI18N
        jLabel1.setText("Detalle de Compra");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 30, -1, -1));

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel3.setText("Marca");
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 140, -1, -1));

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel4.setText("Producto");
        jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 90, -1, -1));

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel5.setText("Talla");
        jPanel1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 190, 40, 30));

        cboMarca.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        cboMarca.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jPanel1.add(cboMarca, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 140, 190, -1));

        cboProducto.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        cboProducto.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jPanel1.add(cboProducto, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 90, 190, -1));

        cboTalla.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        cboTalla.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jPanel1.add(cboTalla, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 190, 190, -1));

        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel6.setText("Color");
        jPanel1.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 90, -1, -1));

        jLabel7.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel7.setText("Precio");
        jPanel1.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 140, -1, -1));

        lblMensaje.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jPanel1.add(lblMensaje, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 580, 560, 30));

        jLabel9.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel9.setText("Proveedor");
        jPanel1.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 240, -1, -1));

        cboColor.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        cboColor.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jPanel1.add(cboColor, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 90, 180, -1));

        txtPrecio.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jPanel1.add(txtPrecio, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 140, 180, -1));

        txtCantidad.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jPanel1.add(txtCantidad, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 190, 180, -1));

        cboProveedor.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        cboProveedor.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cboProveedor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboProveedorActionPerformed(evt);
            }
        });
        jPanel1.add(cboProveedor, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 240, 180, -1));

        btnNuevo.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnNuevo.setText("Nuevo");
        btnNuevo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNuevoActionPerformed(evt);
            }
        });
        jPanel1.add(btnNuevo, new org.netbeans.lib.awtextra.AbsoluteConstraints(690, 90, 90, 30));

        btnAgregar.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnAgregar.setText("Agregar");
        btnAgregar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAgregarActionPerformed(evt);
            }
        });
        jPanel1.add(btnAgregar, new org.netbeans.lib.awtextra.AbsoluteConstraints(690, 140, 90, 30));

        btnCancelar.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnCancelar.setText("Cancelar");
        btnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarActionPerformed(evt);
            }
        });
        jPanel1.add(btnCancelar, new org.netbeans.lib.awtextra.AbsoluteConstraints(690, 190, 90, 30));

        btnEditar.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnEditar.setText("Editar");
        btnEditar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditarActionPerformed(evt);
            }
        });
        jPanel1.add(btnEditar, new org.netbeans.lib.awtextra.AbsoluteConstraints(690, 240, 90, 30));

        tblDCompra.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Producto", "Categoria", "Marca", "Talla", "Color", "Precio", "Cantidad", "Total"
            }
        ));
        tblDCompra.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                tblDCompraMouseReleased(evt);
            }
        });
        jScrollPane1.setViewportView(tblDCompra);

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 300, 1170, 250));

        btnEliminar.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnEliminar.setText("Eliminar");
        btnEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarActionPerformed(evt);
            }
        });
        jPanel1.add(btnEliminar, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 580, 110, -1));

        btnGuardarCompra.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnGuardarCompra.setText("Guardar");
        btnGuardarCompra.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarCompraActionPerformed(evt);
            }
        });
        jPanel1.add(btnGuardarCompra, new org.netbeans.lib.awtextra.AbsoluteConstraints(780, 570, 90, -1));

        btnCancelarCompra.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnCancelarCompra.setText("Cancelar");
        btnCancelarCompra.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarCompraActionPerformed(evt);
            }
        });
        jPanel1.add(btnCancelarCompra, new org.netbeans.lib.awtextra.AbsoluteConstraints(890, 570, 100, -1));

        jLabel10.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel10.setText("Total:");
        jPanel1.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(1010, 570, -1, -1));
        jPanel1.add(txtTotal, new org.netbeans.lib.awtextra.AbsoluteConstraints(1070, 570, 110, -1));

        jLabel11.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel11.setText("Cantidad");
        jPanel1.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 190, -1, -1));

        taProveedor.setColumns(20);
        taProveedor.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        taProveedor.setRows(5);
        jScrollPane2.setViewportView(taProveedor);

        jPanel1.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(820, 120, 330, 150));

        jLabel8.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel8.setText("Datos del proveedor:");
        jPanel1.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(820, 90, -1, -1));

        btnRegresart.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        btnRegresart.setText("REGRESAR");
        btnRegresart.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRegresartActionPerformed(evt);
            }
        });
        jPanel1.add(btnRegresart, new org.netbeans.lib.awtextra.AbsoluteConstraints(1000, 20, 130, 30));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 1190, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(60, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 6, Short.MAX_VALUE)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 663, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnCancelarCompraActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarCompraActionPerformed
        if (totalRegistro > 0) {
            for (DetalleCompra dv : crudDetalleCompra.listarDetalle()) {
                if (dv != null && dv.getIdCompra() == IDaoCompra.obtenerUltimoId()) {
                    crudDetalleCompra.eliminar(new DetalleCompra(dv.getIdDCompra(), dv.getIdProducto(), dv.getIdMarca(), dv.getIdTalla(), dv.getIdColor(), dv.getPrecio(), dv.getCantidad(), dv.getTotal(), dv.getIdCompra()));
                    crudDetalleCompra.guardarEnArchivo();

                }
            }

            crudDetalleCompra.clearIdVenta(new Compra(IDaoCompra.obtenerUltimoId()));
            System.out.println("conficonal if");
        }
        FrmMenu menu = (FrmMenu) SwingUtilities.getWindowAncestor(this);  // Obtiene la ventana principal (FrmMenu)
        JDesktopPane jDesktopPane_menu = menu.getDesktopPane();  // Accede al JDesktopPane

        // Crear la vista de detalles de venta
        JimCompra vistaCompras = new JimCompra();

        // Asegúrate de que jDesktopPane_menu no sea null
        if (jDesktopPane_menu != null) {
            // Agregar el JInternalFrame (vistaDVentas) al JDesktopPane
            jDesktopPane_menu.add(vistaCompras);

            // Hacer visible el JInternalFrame
            vistaCompras.setVisible(true);

            // Establecer el JInternalFrame en el centro
            try {
                vistaCompras.setSelected(true);  // Asegura que esté al frente
//                JimCompra cargarCompras = new JimCompra();
//                cargarCompras.listarCompras();
            } catch (java.beans.PropertyVetoException e) {
                e.printStackTrace();
            }
        }
    }//GEN-LAST:event_btnCancelarCompraActionPerformed

    private void btnAgregarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregarActionPerformed
        if (cboProducto.getSelectedItem().equals("Seleccionar")) {
            JOptionPane.showMessageDialog(null,
                    "Advertencia, Debe seleccionar una producto.",
                    "Advertencia",
                    JOptionPane.WARNING_MESSAGE);
            cboProducto.requestFocus();
            return;
        }

        if (cboMarca.getSelectedItem().equals("Seleccionar")) {
            JOptionPane.showMessageDialog(null,
                    "Advertencia, Debe seleccionar una marca.",
                    "Advertencia",
                    JOptionPane.WARNING_MESSAGE);
            cboMarca.requestFocus();
            return;
        }

        if (cboTalla.getSelectedItem().equals("Seleccionar")) {
            JOptionPane.showMessageDialog(null,
                    "Advertencia, Debe seleccionar una talla.",
                    "Advertencia",
                    JOptionPane.WARNING_MESSAGE);
            cboTalla.requestFocus();
            return;
        }

        if (cboColor.getSelectedItem().equals("Seleccionar")) {
            JOptionPane.showMessageDialog(null,
                    "Advertencia, Debe seleccionar un color.",
                    "Advertencia",
                    JOptionPane.WARNING_MESSAGE);
            cboColor.requestFocus();
            return;
        }

        if (txtPrecio.getText().strip().isEmpty()) {
            JOptionPane.showMessageDialog(null,
                    "Advertencia, Debe ingresar un precio.",
                    "Advertencia",
                    JOptionPane.WARNING_MESSAGE);
            cboMarca.requestFocus();
            return;
        }

        float precio;
        try {
            precio = Float.parseFloat(txtPrecio.getText().strip());
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,
                    "Advertencia, el precio debe ser numeros decimales.",
                    "Advertencia",
                    JOptionPane.WARNING_MESSAGE);
            txtPrecio.requestFocus();
            return;
        }
        int cantidad;
        try {
            cantidad = Integer.parseInt(txtCantidad.getText().strip());
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,
                    "Advertencia, la cantidad debe ser solo números.",
                    "Advertencia",
                    JOptionPane.WARNING_MESSAGE);
            txtCantidad.requestFocus();
            return;
        }
        int idProducto = IDaoProducto.obtenerId(cboProducto.getSelectedItem().toString());
        int idMarca = IDaoMarca.obtenerId(cboMarca.getSelectedItem().toString());
        int idTalla = IDaoTalla.obtenerId(cboTalla.getSelectedItem().toString());///
        int idColor = IDaoColor.obtenerId(cboColor.getSelectedItem().toString());

        if (guardar) {
            if (totalRegistro == 1) {
                if (cboProveedor.getSelectedItem().equals("Seleccionar")) {
                    JOptionPane.showMessageDialog(null,
                            "Advertencia, debe seleccionar un proveedor.",
                            "Advertencia", JOptionPane.WARNING_MESSAGE);
                    cboProveedor.requestFocus();
                    return;
                }
            }
            if (crudDetalleCompra.actualizar(new DetalleCompra(idDCompra, idProducto, idMarca, idTalla, idColor, precio, cantidad, cantidad * precio, idCompraDetalle))) {
                lblMensaje.setText("Se actualizo correctamente el detalle con id " + idDCompra + ".");
                limpiarCampos();
                habilitarCampo(false);
                registroBotones(false);
                crudBotones(false);
                guardar = false;
            } else {
                lblMensaje.setText("No se actualizo el detalle.");
            }
        } else {
            if (totalRegistro == 0) {
                if (cboProveedor.getSelectedItem().equals("Seleccionar")) {
                    JOptionPane.showMessageDialog(null,
                            "Advertencia, debe seleccionar un proveedor.",
                            "Advertencia", JOptionPane.WARNING_MESSAGE);
                    cboProveedor.requestFocus();
                    return;
                }
            }
            if (crudDetalleCompra.agregar(new DetalleCompra(crudDetalleCompra.obtenerUltimoId(), idProducto, idMarca, idTalla, idColor, precio, cantidad, cantidad * precio, idCompraDetalle))) {
                lblMensaje.setText("Se agrego correctamente el detalle.");
                limpiarCampos();
                habilitarCampo(false);
                registroBotones(false);
                crudBotones(false);
            } else {
                lblMensaje.setText("No se agrego el detalle.");
            }
        }
        tblDCompra.clearSelection();
        limpiarTabla();
        listarDCompras();
    }//GEN-LAST:event_btnAgregarActionPerformed

    private void btnNuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNuevoActionPerformed
        habilitarCampo(true);
        limpiarCampos();
        registroBotones(true);
        crudBotones(false);
        limpiarTabla();
        listarDCompras();
        btnNuevo.setEnabled(false);
        hablitarComboBox(false);
        guardar = false;
    }//GEN-LAST:event_btnNuevoActionPerformed

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        limpiarCampos();
        limpiarTabla();
        listarDCompras();
        habilitarCampo(false);
        crudBotones(false);
        registroBotones(false);
        lblMensaje.setText("");
        tblDCompra.clearSelection();
        guardar = false;
    }//GEN-LAST:event_btnCancelarActionPerformed

    private void btnEditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditarActionPerformed
        guardar = true;
        habilitarCampo(true);
        registroBotones(true);
        crudBotones(false);
        limpiarTabla();
        listarDCompras();
        btnNuevo.setEnabled(false);
        lblMensaje.setText("");
        tblDCompra.clearSelection();
        hablitarComboBox(false);
        if (totalRegistro == 1) {
            cboProveedor.setEnabled(true);
        }
    }//GEN-LAST:event_btnEditarActionPerformed

    private void btnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarActionPerformed
        int fila = tblDCompra.getSelectedRow();
        System.out.println("" + idDCompra);
        if (fila < 0) {
            JOptionPane.showMessageDialog(null, "Debe seleccionar una fila");
        } else {
            if (JOptionPane.showConfirmDialog(null, "Desea eliminar el registro", "Eliminar", JOptionPane.YES_NO_OPTION, 3) == 0) {
                int idPro = IDaoProducto.obtenerId(cboProducto.getSelectedItem().toString());
                int idMa = IDaoMarca.obtenerId(cboMarca.getSelectedItem().toString());
                int idTa = IDaoTalla.obtenerId(cboTalla.getSelectedItem().toString());
                int idCo = IDaoColor.obtenerId(cboColor.getSelectedItem().toString());
                float price = Float.parseFloat(txtPrecio.getText().strip());
                int count = Integer.parseInt(txtCantidad.getText().strip());
                float to = price * count;
                if (crudDetalleCompra.eliminar(new DetalleCompra(idDCompra, idPro, idMa, idTa, idCo, price, count, to, IDaoCompra.obtenerUltimoId()))) {
                    lblMensaje.setText("El registro se eliminó correctamente");
                } else {
                    lblMensaje.setText("El registro NO se pudo eliminar");
                }
            }
            limpiarTabla();
            listarDCompras();
            limpiarCampos();
            registroBotones(false);
            crudBotones(false);
            txtCantidad.setText("");
            txtPrecio.setText("");
            tblDCompra.clearSelection();
        }
    }//GEN-LAST:event_btnEliminarActionPerformed

    private void tblDCompraMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblDCompraMouseReleased
        int fila = tblDCompra.getSelectedRow();
        if (fila < 0) {
            JOptionPane.showMessageDialog(null,
                    "Debe seleccionar una fila.",
                    "Advertencia",
                    JOptionPane.WARNING_MESSAGE);
        } else {
            habilitarCampo(false);
            idDCompra = Integer.parseInt(tblDCompra.getValueAt(fila, 0).toString());
            cboProducto.setSelectedItem(tblDCompra.getValueAt(fila, 1));
            cboMarca.setSelectedItem(tblDCompra.getValueAt(fila, 3));
            cboTalla.setSelectedItem(tblDCompra.getValueAt(fila, 4));
            cboColor.setSelectedItem(tblDCompra.getValueAt(fila, 5));
            txtPrecio.setText(tblDCompra.getValueAt(fila, 6).toString());
            txtCantidad.setText(tblDCompra.getValueAt(fila, 7).toString());
            if (totalRegistro == 1) {
                cboProveedor.setSelectedItem(proveedorCorreo);
                System.out.println("-" + proveedorCorreo);
            }
            lblMensaje.setText("");
            registroBotones(false);
            crudBotones(true);
            btnCancelar.setEnabled(true);
        }
    }//GEN-LAST:event_tblDCompraMouseReleased

    private void cboProveedorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboProveedorActionPerformed
        Object selectedItem = cboProveedor.getSelectedItem();
        if (selectedItem != null) {
            String valorProveedor = selectedItem.toString();
            for (Proveedor proveedor : IDaoProveedor.listar()) {
                if (proveedor != null && proveedor.getCorreo().equalsIgnoreCase(valorProveedor)) {
                    taProveedor.setEditable(true);
                    taProveedor.setText("ID: " + proveedor.getIdProveedor() + "\nNombres: " + proveedor.getNombres() + "\nApellidos: " + proveedor.getApellidos() + "\nCorreo: " + proveedor.getCorreo() + "\nTelefono: " + proveedor.getTelefono());
                    taProveedor.setEditable(false);
                    proveedorCorreo = proveedor.getCorreo();
                    idProveedorCompra = proveedor.getIdProveedor();
                }
            }
        } else {
//            System.out.println("No se seleccionó ningún proveedor.");
        }

    }//GEN-LAST:event_cboProveedorActionPerformed

    private void btnGuardarCompraActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarCompraActionPerformed
//        if (compraGuardar) {
//            IDaoCompra.actualizar(new Compra());
//        }
            
        LocalDateTime hoy = LocalDateTime.now();
        String fechaYhora = hoy.getDayOfMonth() + "-" + hoy.getMonthValue() + "-" + hoy.getYear() + " " + hoy.getHour() + ":" + hoy.getMinute() + ":" + hoy.getSecond();
        float totalImpu = crudDetalleCompra.calcularTotal(IDaoCompra.obtenerUltimoId()) / 1.18f;
        float totalCompra = totalImpu + crudDetalleCompra.calcularTotal(IDaoCompra.obtenerUltimoId());
        IDaoCompra.agregar(new Compra(IDaoCompra.obtenerUltimoId(), fechaYhora, idProveedorCompra, crudDetalleCompra.calcularTotal(IDaoCompra.obtenerUltimoId()), totalImpu, totalCompra, true));
        IDaoCompra.guardarEnArchivo();
        FrmMenu menu = (FrmMenu) SwingUtilities.getWindowAncestor(this);  // Obtiene la ventana principal (FrmMenu)
        JDesktopPane jDesktopPane_menu = menu.getDesktopPane();  // Accede al JDesktopPane
        
        

        // Crear la vista de detalles de venta
        JimCompra vistaCompras = new JimCompra();

        // Asegúrate de que jDesktopPane_menu no sea null
        if (jDesktopPane_menu != null) {
            // Agregar el JInternalFrame (vistaDVentas) al JDesktopPane
            jDesktopPane_menu.add(vistaCompras);

            // Hacer visible el JInternalFrame
            vistaCompras.setVisible(true);

            // Establecer el JInternalFrame en el centro
            try {
                vistaCompras.setSelected(true);  // Asegura que esté al frente
//                JimCompra cargarCompras = new JimCompra();
//                cargarCompras.listarCompras();
            } catch (java.beans.PropertyVetoException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("El JDesktopPane no está inicializado.");
        }
    }//GEN-LAST:event_btnGuardarCompraActionPerformed

    private void btnRegresartActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRegresartActionPerformed
        // TODO add your handling code here:
        FrmMenu menu = (FrmMenu) SwingUtilities.getWindowAncestor(this);  // Obtiene la ventana principal (FrmMenu)
        JDesktopPane jDesktopPane_menu = menu.getDesktopPane();  // Accede al JDesktopPane

        // Crear la vista de detalles de venta
        JimCompra vistaCompras = new JimCompra();

        // Asegúrate de que jDesktopPane_menu no sea null
        if (jDesktopPane_menu != null) {
            // Agregar el JInternalFrame (vistaDVentas) al JDesktopPane
            jDesktopPane_menu.add(vistaCompras);

            // Hacer visible el JInternalFrame
            vistaCompras.setVisible(true);

            // Establecer el JInternalFrame en el centro
            try {
                vistaCompras.setSelected(true);  // Asegura que esté al frente
//                JimCompra cargarCompras = new JimCompra();
//                cargarCompras.listarCompras();
            } catch (java.beans.PropertyVetoException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("El JDesktopPane no está inicializado.");
        }
    }//GEN-LAST:event_btnRegresartActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAgregar;
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnCancelarCompra;
    private javax.swing.JButton btnEditar;
    private javax.swing.JButton btnEliminar;
    private javax.swing.JButton btnGuardarCompra;
    private javax.swing.JButton btnNuevo;
    private javax.swing.JButton btnRegresart;
    private javax.swing.JComboBox<String> cboColor;
    private javax.swing.JComboBox<String> cboMarca;
    private javax.swing.JComboBox<String> cboProducto;
    private javax.swing.JComboBox<String> cboProveedor;
    private javax.swing.JComboBox<String> cboTalla;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lblMensaje;
    private javax.swing.JTextArea taProveedor;
    private javax.swing.JTable tblDCompra;
    private javax.swing.JTextField txtCantidad;
    private javax.swing.JTextField txtPrecio;
    private javax.swing.JTextField txtTotal;
    // End of variables declaration//GEN-END:variables
}
