package View;

import dao.IDaoExtendido;
import daoImpl.PersonaDaoImpl;
import daoImpl.ProveedorDaoImpl;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import model.Persona;
import model.Proveedor;
import dao.IDaoObtenerId;

public class JimProveedor extends javax.swing.JInternalFrame {

    private DefaultTableModel modelo;
    private IDaoObtenerId<Proveedor> crudProveedor;
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
        crudProveedor = new ProveedorDaoImpl();
        crudPersona = new PersonaDaoImpl();
        modelo = new DefaultTableModel();
        limpiarTabla();
        listarProveedores();
        habilitarCampo(false);
        crudBotones(false);
        registroBotones(false);
    }

    private void listarProveedores() {
        modelo = (DefaultTableModel) tblProveedor.getModel();
        for (Proveedor p : crudProveedor.listar()) {
            filaDatos[0] = p.getIdProveedor();
            filaDatos[1] = p.getNombres();
            filaDatos[2] = p.getApellidos();
            filaDatos[3] = p.getCorreo();
            filaDatos[4] = p.getTelefono();
            modelo.addRow(filaDatos);
        }
        if (crudProveedor.total() > 1) {
            buscarCampo(true);
        } else {
            buscarCampo(false);
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
        getContentPane().add(btnEditar, new org.netbeans.lib.awtextra.AbsoluteConstraints(950, 300, 80, 31));

        btnEliminar.setFont(new java.awt.Font("Segoe UI", 1, 15)); // NOI18N
        btnEliminar.setText("Eliminar");
        btnEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarActionPerformed(evt);
            }
        });
        getContentPane().add(btnEliminar, new org.netbeans.lib.awtextra.AbsoluteConstraints(1040, 300, 90, 31));

        btnCancelar.setFont(new java.awt.Font("Segoe UI", 1, 15)); // NOI18N
        btnCancelar.setText("Cancelar");
        btnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarActionPerformed(evt);
            }
        });
        getContentPane().add(btnCancelar, new org.netbeans.lib.awtextra.AbsoluteConstraints(770, 250, 90, 31));

        btnNuevo.setFont(new java.awt.Font("Segoe UI", 1, 15)); // NOI18N
        btnNuevo.setText("Nuevo");
        btnNuevo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNuevoActionPerformed(evt);
            }
        });
        getContentPane().add(btnNuevo, new org.netbeans.lib.awtextra.AbsoluteConstraints(870, 250, 80, 31));

        btnGuardar.setFont(new java.awt.Font("Segoe UI", 1, 15)); // NOI18N
        btnGuardar.setText("Guardar");
        btnGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarActionPerformed(evt);
            }
        });
        getContentPane().add(btnGuardar, new org.netbeans.lib.awtextra.AbsoluteConstraints(670, 250, 90, 31));

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jLabel5.setText("Telefono:");
        getContentPane().add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(630, 200, 80, 30));

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jLabel3.setText("Correo:");
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(650, 130, 60, 30));
        getContentPane().add(txtCorreo, new org.netbeans.lib.awtextra.AbsoluteConstraints(710, 130, 240, 31));
        getContentPane().add(txtTelefono, new org.netbeans.lib.awtextra.AbsoluteConstraints(710, 200, 240, 31));

        lblMensaje.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        getContentPane().add(lblMensaje, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 250, 270, 30));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnEditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditarActionPerformed

    }//GEN-LAST:event_btnEditarActionPerformed

    private void btnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarActionPerformed

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
        String datos = nombres+' '+apellidos;
        if (guardar) {
            idPersona = crudProveedor.obtenerIdForeignKey(idProveedor);
            if ( crudPersona.actualizar(new Persona(idPersona, nombres, apellidos, correo)) && crudProveedor.actualizar(new Proveedor(idProveedor, telefono))) {
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
            if ( crudPersona.agregar(new Persona(nombres, apellidos, correo))  && crudProveedor.agregar(new Proveedor(telefono, crudPersona.obtenerId(datos)))) {
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

    }//GEN-LAST:event_tblProveedorMouseReleased

    private void txtBuscarKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBuscarKeyReleased

    }//GEN-LAST:event_txtBuscarKeyReleased


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnEditar;
    private javax.swing.JButton btnEliminar;
    private javax.swing.JButton btnGuardar;
    private javax.swing.JButton btnNuevo;
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
