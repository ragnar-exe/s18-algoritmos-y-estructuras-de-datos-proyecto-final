package View;

import daoImpl.TallaDaoImpl;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import model.Talla;

public class JimTalla extends javax.swing.JInternalFrame {
    private TallaDaoImpl crudTalla;
    private DefaultTableModel modelo;
    private Object[] filaDatos;
    private int idTalla;
    private boolean guardar = false;
    
    public JimTalla() {
        initComponents();
        int ancho = java.awt.Toolkit.getDefaultToolkit().getScreenSize().width;
        int alto = java.awt.Toolkit.getDefaultToolkit().getScreenSize().height;
        this.setSize(ancho, alto - 106);
        filaDatos = new Object[3];
        crudTalla = new TallaDaoImpl();
        modelo = new DefaultTableModel();
        listarTallas();
        habilitarCampo(false);
        crudBotones(false);
        registroBotones(false);
    }
    
    private void listarTallas() {
        modelo = (DefaultTableModel) tblTalla.getModel();
        for (int i = 0; i < 100; i++) {
            for (int j = 0; j < 5; j++) {
                if(crudTalla.listar(i, j) != null) {
                    filaDatos[0] = crudTalla.listar(i, j).getIdTalla();
                    filaDatos[1] = crudTalla.listar(i, j).getNumero();
                    filaDatos[2] = crudTalla.listar(i, j).getDescripcion();
                    modelo.addRow(filaDatos);
                }
            }
        }
        if (crudTalla.total() > 1) {
            txtBuscar.setEnabled(true);
        } else {
            txtBuscar.setEnabled(false);
        }
    }
    
    private void limpiarTabla() {
        modelo = (DefaultTableModel) tblTalla.getModel();
        modelo.getDataVector().removeAllElements();
        tblTalla.removeAll();
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
        cboDescripcion.setEnabled(f);
        txtNumero.setEnabled(f);
    }
    
    private void limpiarCampo(){
        txtNumero.setText("");
        cboDescripcion.setSelectedIndex(0);
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel4 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        btnGuardar = new javax.swing.JButton();
        btnNuevo = new javax.swing.JButton();
        btnCancelar = new javax.swing.JButton();
        jLabel9 = new javax.swing.JLabel();
        jScrollPane4 = new javax.swing.JScrollPane();
        tblTalla = new javax.swing.JTable();
        btnEditar = new javax.swing.JButton();
        btnEliminar = new javax.swing.JButton();
        txtBuscar = new javax.swing.JTextField();
        txtNumero = new javax.swing.JTextField();
        lblMensaje = new javax.swing.JLabel();
        cboDescripcion = new javax.swing.JComboBox<>();

        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 30)); // NOI18N
        jLabel4.setText("Tallas");
        getContentPane().add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 30, -1, -1));

        jLabel11.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jLabel11.setText("Numero de la Talla:");
        getContentPane().add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 150, -1, 30));

        jLabel10.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jLabel10.setText("Descripcion:");
        getContentPane().add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(710, 150, -1, 30));

        btnGuardar.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnGuardar.setText("Guardar");
        btnGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarActionPerformed(evt);
            }
        });
        getContentPane().add(btnGuardar, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 190, 90, 31));

        btnNuevo.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnNuevo.setText("Nuevo");
        btnNuevo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNuevoActionPerformed(evt);
            }
        });
        getContentPane().add(btnNuevo, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 190, 80, 30));

        btnCancelar.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnCancelar.setText("Cancelar");
        btnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarActionPerformed(evt);
            }
        });
        getContentPane().add(btnCancelar, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 190, -1, 31));

        jLabel9.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel9.setText("Buscar:");
        getContentPane().add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 290, -1, -1));

        tblTalla.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Talla", "Descripcion"
            }
        ));
        tblTalla.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                tblTallaMouseReleased(evt);
            }
        });
        jScrollPane4.setViewportView(tblTalla);

        getContentPane().add(jScrollPane4, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 330, 1100, 270));

        btnEditar.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnEditar.setText("Editar");
        btnEditar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditarActionPerformed(evt);
            }
        });
        getContentPane().add(btnEditar, new org.netbeans.lib.awtextra.AbsoluteConstraints(930, 290, 70, 29));

        btnEliminar.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnEliminar.setText("Eliminar");
        btnEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarActionPerformed(evt);
            }
        });
        getContentPane().add(btnEliminar, new org.netbeans.lib.awtextra.AbsoluteConstraints(1010, 290, 90, 29));

        txtBuscar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtBuscarKeyReleased(evt);
            }
        });
        getContentPane().add(txtBuscar, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 290, 346, 30));
        getContentPane().add(txtNumero, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 150, 340, 30));

        lblMensaje.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        getContentPane().add(lblMensaje, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 230, 340, 30));

        cboDescripcion.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        cboDescripcion.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Seleccionar", "Bebe", "Niño", "Adolescente", "Adulto" }));
        getContentPane().add(cboDescripcion, new org.netbeans.lib.awtextra.AbsoluteConstraints(840, 152, 180, 30));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        crudBotones(false);
        registroBotones(false);
        limpiarCampo();
        habilitarCampo(false);
        txtBuscar.setEnabled(true);
        tblTalla.clearSelection();
        guardar = false;
    }//GEN-LAST:event_btnCancelarActionPerformed

    private void txtBuscarKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBuscarKeyReleased
        limpiarTabla();
        int n = 0;
        modelo = (DefaultTableModel) tblTalla.getModel();
        String valorBuscar = txtBuscar.getText().strip();
        if (valorBuscar.equalsIgnoreCase("")) {
            limpiarTabla();
            listarTallas();
            lblMensaje.setText("");
        } else {
//            for (Talla ta : crudTalla.listar(valorBuscar)) {
//                filaDatos[0] = ta.getIdTalla();
//                filaDatos[1] = ta.getNumero();
//                filaDatos[2] = ta.getDescripcion();
//                modelo.addRow(filaDatos);
//                n++;
//            }
            lblMensaje.setText(n + " registros encontrados.");
        }
        habilitarCampo(false);
    }//GEN-LAST:event_txtBuscarKeyReleased

    private void tblTallaMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblTallaMouseReleased
        int fila = tblTalla.getSelectedRow();
        if (fila < 0) {
            JOptionPane.showMessageDialog(null,
                    "Debe seleccionar una fila.",
                    "Advertencia",
                    JOptionPane.WARNING_MESSAGE);
        } else {
            habilitarCampo(false);
            idTalla = Integer.parseInt(tblTalla.getValueAt(fila, 0).toString());
            txtNumero.setText(tblTalla.getValueAt(fila, 1).toString());
            if(tblTalla.getValueAt(fila, 2).toString().isEmpty()) {
                cboDescripcion.setSelectedIndex(0);
            } else {
                cboDescripcion.setSelectedItem(tblTalla.getValueAt(fila,2));
            }
            lblMensaje.setText("");
            registroBotones(false);
            crudBotones(true);
            txtBuscar.setEnabled(false);
            btnCancelar.setEnabled(true);
        }
    }//GEN-LAST:event_tblTallaMouseReleased

    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed
        byte numero;
        String descripcion= "";
        try {
            numero = Byte.parseByte(txtNumero.getText().strip());
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,
                    "Advertencia, El numero debe ser numeros enteros.",
                    "Advertencia",
                    JOptionPane.WARNING_MESSAGE);
            txtNumero.requestFocus();
            return;
        }
        if (numero > 0 && numero <= 127) {
            descripcion = cboDescripcion.getSelectedItem().toString();
            if(descripcion.equalsIgnoreCase("Seleccionar")) {
                descripcion = "";
            }
            if (guardar) {
                if (crudTalla.actualizar(new Talla(idTalla, numero, descripcion))) {
                    lblMensaje.setText("Se actualizo correctamente la talla.");
                    limpiarCampo();
                    habilitarCampo(false);
                    registroBotones(false);
                    crudBotones(false);
                    guardar = false;
                } else {
                    lblMensaje.setText("No se actualizo la talla.");
                }
            } else {
                if (crudTalla.obtenerId(String.valueOf(numero)) == -1) {
                    if (crudTalla.agregar(new Talla(crudTalla.obtenerUltimoId(),numero, descripcion))) {
                        lblMensaje.setText("Se agrego correctamente la talla.");
                        limpiarCampo();
                        habilitarCampo(false);
                        registroBotones(false);
                        crudBotones(false);
                    } else {
                        lblMensaje.setText("No se agrego la talla.");
                    }
                } else {
                    txtNumero.requestFocus();
                    lblMensaje.setText("La talla ya existe.");
                }
            }
            tblTalla.clearSelection();
            limpiarTabla();
            listarTallas();
        } else {
            JOptionPane.showMessageDialog(null,
                    "Advertencia, La talla debe estar entre 1 y 127.",
                    "Advertencia",
                    JOptionPane.WARNING_MESSAGE);
            return;
        }
    }//GEN-LAST:event_btnGuardarActionPerformed

    private void btnNuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNuevoActionPerformed
        habilitarCampo(true);
        txtNumero.requestFocus();
        lblMensaje.setText("");
        registroBotones(true);
        crudBotones(false);
        btnNuevo.setEnabled(false);
        txtBuscar.setEnabled(false);
        tblTalla.clearSelection();
        guardar = false;
    }//GEN-LAST:event_btnNuevoActionPerformed

    private void btnEditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditarActionPerformed
        guardar = true;
        habilitarCampo(true);
        registroBotones(true);
        crudBotones(false);
        btnNuevo.setEnabled(false);
        lblMensaje.setText("");
        tblTalla.clearSelection();
    }//GEN-LAST:event_btnEditarActionPerformed

    private void btnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarActionPerformed
        int fila = tblTalla.getSelectedRow();
        if (fila < 0) {
            JOptionPane.showMessageDialog(null, "Debe seleccionar una fila");
        } else {
            if (JOptionPane.showConfirmDialog(null, "Desea eliminar el registro", "Eliminar", JOptionPane.YES_NO_OPTION, 3) == 0) {
                if (crudTalla.eliminar(new Talla(idTalla))) {
                    limpiarTabla();
                    listarTallas();
                    lblMensaje.setText("El registro se eliminó correctamente");
                } else {
                    lblMensaje.setText("El registro NO se pudo eliminar");
                }
            }
            registroBotones(false);
            crudBotones(false);
            habilitarCampo(false);
            tblTalla.clearSelection();
        }
    }//GEN-LAST:event_btnEliminarActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnEditar;
    private javax.swing.JButton btnEliminar;
    private javax.swing.JButton btnGuardar;
    private javax.swing.JButton btnNuevo;
    private javax.swing.JComboBox<String> cboDescripcion;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JLabel lblMensaje;
    private javax.swing.JTable tblTalla;
    private javax.swing.JTextField txtBuscar;
    private javax.swing.JTextField txtNumero;
    // End of variables declaration//GEN-END:variables
}
