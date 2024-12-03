package View;

import dao.IDaoExtendido;
import daoImpl.PersonaDaoImpl;
import daoImpl.ProveedorDaoImpl;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import model.Persona;
import model.Proveedor;
import dao.IDaoObtenerLista;
import java.util.List;

public class JimProveedor extends javax.swing.JInternalFrame {
    
    private ProveedorDaoImpl pv = new ProveedorDaoImpl();
    private DefaultTableModel modelo;
    
    private IDaoExtendido<Persona> crudPersona;
    private Object[] filaDatos;
    private int idProveedor;
    private int idPersona;
    private boolean guardar = false;

    public JimProveedor() {
        initComponents();
        int ancho = java.awt.Toolkit.getDefaultToolkit().getScreenSize().width;
        int alto = java.awt.Toolkit.getDefaultToolkit().getScreenSize().height;
        this.setSize(ancho, alto - 106);
        filaDatos = new Object[5];
        modelo = new DefaultTableModel();
        limpiarTabla();
        listarProveedores();
        habilitarCampo(false);
        crudBotones(false);
        registroBotones(false);
    }

    private void listarProveedores() {
        modelo = (DefaultTableModel) tblProveedor.getModel();
        for (Proveedor p : pv.listar()) {
            filaDatos[0] = p.getIdProveedor();
            filaDatos[1] = p.getNombres();
            filaDatos[2] = p.getApellidos();
            filaDatos[3] = p.getCorreo();
            filaDatos[4] = p.getTelefono();
            modelo.addRow(filaDatos);
        }
        if (pv.total() > 1) {
            buscarCampo(true);
        } else {
            buscarCampo(false);
        }
        
        if (pv.total() > 0) {
            btnClear.setEnabled(false);
            btnDequeee.setEnabled(false);
        }
    }

    private void limpiarTabla() {
        modelo = (DefaultTableModel) tblProveedor.getModel();
        modelo.getDataVector().removeAllElements();
        tblProveedor.removeAll();
    }

    private void registroBotones(boolean f) {
        btnGuardar.setEnabled(f);
        btnCancelar.setEnabled(f);
    }

    private void crudBotones(boolean f) {
        btnNuevo.setEnabled(!f);
        btnEditar.setEnabled(f);
        btnEliminar.setEnabled(f);
    }

    private void habilitarCampo(boolean f) {
        txtNombres.setEnabled(f);
        txtApellidos.setEnabled(f);
        txtCorreo.setEnabled(f);
        txtTelefono.setEnabled(f);
    }

    private void limpiarCampos() {
        txtNombres.setText("");
        txtApellidos.setText("");
        txtCorreo.setText("");
        txtTelefono.setText("");
    }

    private void buscarCampo(boolean f) {
        txtBuscar.setText("");
        txtBuscar.setEnabled(f);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel4 = new javax.swing.JLabel();
        txtNombres = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        txtApellidos = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        txtBuscar = new javax.swing.JTextField();
        jScrollPane4 = new javax.swing.JScrollPane();
        tblProveedor = new javax.swing.JTable();
        btnEditar = new javax.swing.JButton();
        btnEliminar = new javax.swing.JButton();
        btnCancelar = new javax.swing.JButton();
        btnNuevo = new javax.swing.JButton();
        btnGuardar = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        txtCorreo = new javax.swing.JTextField();
        txtTelefono = new javax.swing.JTextField();
        lblMensaje = new javax.swing.JLabel();
        btnClear = new javax.swing.JButton();
        btnE_Atender = new javax.swing.JButton();
        btnZize = new javax.swing.JButton();
        btnEmpty = new javax.swing.JButton();
        btnDequeee = new javax.swing.JButton();

        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 30)); // NOI18N
        jLabel4.setText("Proveedores");
        getContentPane().add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 20, -1, -1));
        getContentPane().add(txtNombres, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 130, 245, 31));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jLabel1.setText("Nombres:");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(194, 130, 80, 30));

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jLabel2.setText("Apellidos:");
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(196, 200, 80, 30));
        getContentPane().add(txtApellidos, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 200, 245, 31));

        jLabel9.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel9.setText("Buscar:");
        getContentPane().add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 300, -1, -1));

        txtBuscar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtBuscarKeyReleased(evt);
            }
        });
        getContentPane().add(txtBuscar, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 300, 332, 31));

        tblProveedor.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Nombres", "Apellidos", "Correo", "Telefono"
            }
        ));
        tblProveedor.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                tblProveedorMouseReleased(evt);
            }
        });
        jScrollPane4.setViewportView(tblProveedor);

        getContentPane().add(jScrollPane4, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 340, 1160, 250));

        btnEditar.setFont(new java.awt.Font("Segoe UI", 1, 15)); // NOI18N
        btnEditar.setText("Editar");
        btnEditar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditarActionPerformed(evt);
            }
        });
        getContentPane().add(btnEditar, new org.netbeans.lib.awtextra.AbsoluteConstraints(1000, 300, 80, 31));

        btnEliminar.setFont(new java.awt.Font("Segoe UI", 1, 15)); // NOI18N
        btnEliminar.setText("Eliminar");
        btnEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarActionPerformed(evt);
            }
        });
        getContentPane().add(btnEliminar, new org.netbeans.lib.awtextra.AbsoluteConstraints(1090, 300, 90, 31));

        btnCancelar.setFont(new java.awt.Font("Segoe UI", 1, 15)); // NOI18N
        btnCancelar.setText("Cancelar");
        btnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarActionPerformed(evt);
            }
        });
        getContentPane().add(btnCancelar, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 250, 90, 31));

        btnNuevo.setFont(new java.awt.Font("Segoe UI", 1, 15)); // NOI18N
        btnNuevo.setText("Nuevo");
        btnNuevo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNuevoActionPerformed(evt);
            }
        });
        getContentPane().add(btnNuevo, new org.netbeans.lib.awtextra.AbsoluteConstraints(820, 250, 80, 31));

        btnGuardar.setFont(new java.awt.Font("Segoe UI", 1, 15)); // NOI18N
        btnGuardar.setText("Guardar");
        btnGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarActionPerformed(evt);
            }
        });
        getContentPane().add(btnGuardar, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 250, 90, 31));

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jLabel5.setText("Telefono:");
        getContentPane().add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(630, 200, 80, 30));

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jLabel3.setText("Correo:");
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(650, 130, 60, 30));
        getContentPane().add(txtCorreo, new org.netbeans.lib.awtextra.AbsoluteConstraints(710, 130, 240, 31));
        getContentPane().add(txtTelefono, new org.netbeans.lib.awtextra.AbsoluteConstraints(710, 200, 240, 31));

        lblMensaje.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        getContentPane().add(lblMensaje, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 250, 470, 30));

        btnClear.setFont(new java.awt.Font("Segoe UI", 1, 15)); // NOI18N
        btnClear.setText("Eliminar Todo");
        btnClear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnClearActionPerformed(evt);
            }
        });
        getContentPane().add(btnClear, new org.netbeans.lib.awtextra.AbsoluteConstraints(910, 250, -1, -1));

        btnE_Atender.setFont(new java.awt.Font("Segoe UI", 1, 15)); // NOI18N
        btnE_Atender.setText("Elemento Atendender");
        btnE_Atender.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnE_AtenderActionPerformed(evt);
            }
        });
        getContentPane().add(btnE_Atender, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 300, -1, -1));

        btnZize.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnZize.setText("Zize");
        btnZize.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnZizeActionPerformed(evt);
            }
        });
        getContentPane().add(btnZize, new org.netbeans.lib.awtextra.AbsoluteConstraints(770, 300, -1, -1));

        btnEmpty.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnEmpty.setText("IsEmpty");
        btnEmpty.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEmptyActionPerformed(evt);
            }
        });
        getContentPane().add(btnEmpty, new org.netbeans.lib.awtextra.AbsoluteConstraints(870, 300, -1, -1));

        btnDequeee.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnDequeee.setText("Eliminar Ultimo");
        btnDequeee.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDequeeeActionPerformed(evt);
            }
        });
        getContentPane().add(btnDequeee, new org.netbeans.lib.awtextra.AbsoluteConstraints(1050, 250, -1, -1));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnEditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditarActionPerformed
        guardar = true;
        habilitarCampo(true);
        registroBotones(true);
        crudBotones(false);
        limpiarTabla();
        listarProveedores();
        buscarCampo(false);
        btnNuevo.setEnabled(false);
        lblMensaje.setText("");
        tblProveedor.clearSelection();
    }//GEN-LAST:event_btnEditarActionPerformed

    private void btnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarActionPerformed
        int fila = tblProveedor.getSelectedRow();
        if (fila < 0) {
            JOptionPane.showMessageDialog(null, "Debe seleccionar una fila");
        } else {
            if (JOptionPane.showConfirmDialog(null, "Desea eliminar el registro", "Eliminar", JOptionPane.YES_NO_OPTION, 3) == 0) {
                if (pv.eliminar(new Proveedor(idProveedor))) {
                    lblMensaje.setText("El registro se eliminó correctamente");
                } else {
                    lblMensaje.setText("El registro NO se pudo eliminar");
                }
            }
            buscarCampo(true);
            limpiarTabla();
            listarProveedores();
            limpiarCampos();
            registroBotones(false);
            crudBotones(false);
            txtNombres.setText("");
            tblProveedor.clearSelection();
        }                   
    }//GEN-LAST:event_btnEliminarActionPerformed

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        limpiarCampos();
        habilitarCampo(false);
        crudBotones(false);
        registroBotones(false);
        buscarCampo(false);
        limpiarTabla();
        listarProveedores();
        tblProveedor.clearSelection();
        guardar = false;
    }//GEN-LAST:event_btnCancelarActionPerformed

    private void btnNuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNuevoActionPerformed
        habilitarCampo(true);
        limpiarCampos();
        txtNombres.requestFocus();
        registroBotones(true);
        crudBotones(false);
        limpiarTabla();
        listarProveedores();
        buscarCampo(false);
        btnNuevo.setEnabled(false);
        lblMensaje.setText("");
        tblProveedor.clearSelection();
        guardar = false;
    }//GEN-LAST:event_btnNuevoActionPerformed

    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed
        String nombres = txtNombres.getText().strip();
        String apellidos = txtApellidos.getText().strip();
        String correo = txtCorreo.getText().strip();
        String telefono = txtTelefono.getText().strip();
        if (nombres.isEmpty()) {
            JOptionPane.showMessageDialog(null,
                    "Advertencia, Debe ingresar un nombre o nombres.",
                    "Advertencia",
                    JOptionPane.WARNING_MESSAGE);
            txtNombres.requestFocus();
            return;
        }

        if (apellidos.isEmpty()) {
            JOptionPane.showMessageDialog(null,
                    "Advertencia, Debe ingresar un apellido o apellidos.",
                    "Advertencia",
                    JOptionPane.WARNING_MESSAGE);
            txtApellidos.requestFocus();
            return;
        }

        if (correo.isEmpty()) {
            JOptionPane.showMessageDialog(null,
                    "Advertencia, Debe ingresar un correo.",
                    "Advertencia",
                    JOptionPane.WARNING_MESSAGE);
            txtCorreo.requestFocus();
            return;
        }

        if (telefono.isEmpty()) {
            JOptionPane.showMessageDialog(null,
                    "Advertencia, Debe ingresar un telefono.",
                    "Advertencia",
                    JOptionPane.WARNING_MESSAGE);
            txtTelefono.requestFocus();
            return;
        }

        if (nombres.length() > 50) {
            JOptionPane.showMessageDialog(null,
                    "Advertencia, El nombre o nombres es muy largo.",
                    "Advertencia",
                    JOptionPane.WARNING_MESSAGE);
            txtNombres.requestFocus();
            return;
        }
        
        if (apellidos.length() > 50) {
            JOptionPane.showMessageDialog(null,
                    "Advertencia, El apellido o apellidos es muy largo.",
                    "Advertencia",
                    JOptionPane.WARNING_MESSAGE);
            txtApellidos.requestFocus();
            return;
        }
        
        if (correo.length() > 50) {
            JOptionPane.showMessageDialog(null,
                    "Advertencia, El correo ingresado es muy largo.",
                    "Advertencia",
                    JOptionPane.WARNING_MESSAGE);
            txtCorreo.requestFocus();
            return;
        }

        if (telefono.length() != 9) {
            JOptionPane.showMessageDialog(null,
                    "Advertencia, El telefono debe tener 9 digitos.",
                    "Advertencia",
                    JOptionPane.WARNING_MESSAGE);
            txtTelefono.requestFocus();
            return;
        }
        if (guardar) {
            if ( pv.actualizar(new Proveedor(idProveedor, nombres, apellidos, correo,telefono))) {
                lblMensaje.setText("Se actualizo correctamente el proveedor con id " + idProveedor + ".");
                limpiarCampos();
                habilitarCampo(false);
                registroBotones(false);
                crudBotones(false);
                guardar = false;
            } else {
                lblMensaje.setText("No se actualizo el proveedor.");
            }
        } else {
            if (pv.existeCorreo(correo)) {
                JOptionPane.showMessageDialog(null, "Error, el correo ya existe.", "Error", JOptionPane.ERROR_MESSAGE);
                txtCorreo.requestFocus();
                return;
            }
            if (pv.existeTelefono(telefono)) {
                JOptionPane.showMessageDialog(null, "Error, el numero ya existe.", "Error", JOptionPane.ERROR_MESSAGE);
                txtTelefono.requestFocus();
                return;
            }
            if (pv.agregar(new Proveedor(pv.obtenerUltimoId(), nombres, apellidos, correo, telefono))) {
                lblMensaje.setText("Se agrego correctamente el producto.");
                limpiarCampos();
                habilitarCampo(false);
                registroBotones(false);
                crudBotones(false);
            } else {
                lblMensaje.setText("No se agrego el proveedor.");
            }
        }
        tblProveedor.clearSelection();
        limpiarTabla();
        listarProveedores();
    }//GEN-LAST:event_btnGuardarActionPerformed

    private void tblProveedorMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblProveedorMouseReleased
        int fila = tblProveedor.getSelectedRow();
        if (fila < 0) {
            JOptionPane.showMessageDialog(null,
                    "Debe seleccionar una fila.",
                    "Advertencia",
                    JOptionPane.WARNING_MESSAGE);
        } else {
            habilitarCampo(false);
            idProveedor = Integer.parseInt(tblProveedor.getValueAt(fila, 0).toString());
            txtNombres.setText(tblProveedor.getValueAt(fila, 1).toString());
            txtApellidos.setText(tblProveedor.getValueAt(fila, 2).toString());
            txtCorreo.setText(tblProveedor.getValueAt(fila, 3).toString());
            txtTelefono.setText(tblProveedor.getValueAt(fila, 4).toString());
            lblMensaje.setText("");
            buscarCampo(false);
            registroBotones(false);
            crudBotones(true);
            //btnCima.setEnabled(true);
        }
    }//GEN-LAST:event_tblProveedorMouseReleased

    private void txtBuscarKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBuscarKeyReleased
       // TODO add your handling code here:
        limpiarTabla();  // Limpia la tabla antes de mostrar los resultados
        int n = 0;       // Contador de resultados
        modelo = (DefaultTableModel) tblProveedor.getModel();  // Obtiene el modelo de la tabla
        String valorBuscar = txtBuscar.getText().strip();     // Obtiene el texto de búsqueda
        if (valorBuscar.equalsIgnoreCase("")) {
            // Si el campo de búsqueda está vacío, muestra todos los productos
            limpiarTabla();
            listarProveedores();  // Método para listar todos los productos
            lblMensaje.setText("");
        } else {
            // Llama al método buscar() de ProductoDaoImpl
            List<Proveedor> proveedorEncontrados = pv.listar(valorBuscar);

            // Itera sobre los productos encontrados y los agrega a la tabla
            for (Proveedor p : proveedorEncontrados) {
                filaDatos[0] = p.getIdProveedor();
                filaDatos[1] = p.getNombres();
                filaDatos[2] = p.getApellidos();
                filaDatos[3] = p.getCorreo();
                filaDatos[4] = p.getTelefono();
                modelo.addRow(filaDatos);
                n++;  // Incrementa el contador de resultados
            }

            lblMensaje.setText(n + " registros encontrados.");  // Muestra el número de resultados encontrados
        }
        txtNombres.setText("");  // Limpia el campo de nombre
                                
    }//GEN-LAST:event_txtBuscarKeyReleased

    private void btnClearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnClearActionPerformed
        int confirmacion = JOptionPane.showConfirmDialog(
                null,
                "¿Está seguro de que desea eliminar todos los registros?",
                "Confirmar eliminación",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.WARNING_MESSAGE
        );

        if (confirmacion == JOptionPane.YES_OPTION) {
            pv.clear();
            limpiarTabla();
            listarProveedores();
            lblMensaje.setText("Todos los registros se eliminaron correctamente.");
        } else {
            lblMensaje.setText("La eliminación de todos los registros fue cancelada.");
        }
    }//GEN-LAST:event_btnClearActionPerformed

    private void btnE_AtenderActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnE_AtenderActionPerformed
         if (pv.isEmpty()==true) {
            lblMensaje.setText("Aun no hay elemenros en la cola");
        }else {
            Proveedor p = pv.peek();
            lblMensaje.setText("El elemento a atender es: " + p.getNombres() + " " + p.getApellidos());
        }
    }//GEN-LAST:event_btnE_AtenderActionPerformed

    private void btnZizeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnZizeActionPerformed
       lblMensaje.setText("El tamaño de la cola es: " + pv.size());
    }//GEN-LAST:event_btnZizeActionPerformed

    private void btnEmptyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEmptyActionPerformed
         boolean d = pv.isEmpty();
        if ( d ==  true) {
            lblMensaje.setText("Cola esta vacia");
        }else{
            lblMensaje.setText("Cola con datos");
        }
    }//GEN-LAST:event_btnEmptyActionPerformed

    private void btnDequeeeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDequeeeActionPerformed

        Proveedor p = pv.dequeue();
        limpiarTabla();
        listarProveedores();
        lblMensaje.setText("El provedor eliminado es: " + p.getNombres() + " " + p.getApellidos());
    }//GEN-LAST:event_btnDequeeeActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnClear;
    private javax.swing.JButton btnDequeee;
    private javax.swing.JButton btnE_Atender;
    private javax.swing.JButton btnEditar;
    private javax.swing.JButton btnEliminar;
    private javax.swing.JButton btnEmpty;
    private javax.swing.JButton btnGuardar;
    private javax.swing.JButton btnNuevo;
    private javax.swing.JButton btnZize;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JLabel lblMensaje;
    private javax.swing.JTable tblProveedor;
    private javax.swing.JTextField txtApellidos;
    private javax.swing.JTextField txtBuscar;
    private javax.swing.JTextField txtCorreo;
    private javax.swing.JTextField txtNombres;
    private javax.swing.JTextField txtTelefono;
    // End of variables declaration//GEN-END:variables
}
