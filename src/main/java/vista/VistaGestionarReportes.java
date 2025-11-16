package vista;

import java.time.LocalDate;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import controladora.Controladora;
import java.awt.Panel;
import javax.swing.JFrame;
import modelo.FamiliaAdoptante;
import modelo.OperacionException;
import modelo.Reporte;
import modelo.Usuario;
import modelo.Visita;
import modelo.Voluntario;

public class VistaGestionarReportes extends javax.swing.JFrame {
    private final Controladora control;
    private final VistaAdministrador vistaAnterior;
    private DefaultTableModel modeloTabla;
    private TableRowSorter<TableModel> sorter;
    
    public VistaGestionarReportes(Controladora control, VistaAdministrador vistaAnterior) {
        this.control = control;
        this.vistaAnterior = vistaAnterior;
        initComponents();
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        configurarTabla();
        cargarReportes(); 
    }
    
    public VistaGestionarReportes() {
        this.control = null;
        this.vistaAnterior = null;
        initComponents();
    }
    
    // MÉTODOS DE SOPORTE DE TABLA
    private void configurarTabla() {
        // Ajuste las columnas según sus atributos de Reporte (ID, Fecha, Cantidad, Descripción)
        String titulos[] = {"ID", "Fecha", "Cantidad", "Descripción"};
        modeloTabla = new DefaultTableModel(null, titulos) {
            @Override
            public boolean isCellEditable(int row, int column) { return false; }
        };
        jTableReportes.setModel(modeloTabla);
        sorter = new TableRowSorter<>(modeloTabla);
        jTableReportes.setRowSorter(sorter);
        
        // Ocultar Columna ID (0)
        jTableReportes.getColumnModel().getColumn(0).setMaxWidth(0);
        jTableReportes.getColumnModel().getColumn(0).setMinWidth(0);
        jTableReportes.getColumnModel().getColumn(0).setPreferredWidth(0);
    }
    
    private void cargarReportes() {
        modeloTabla.setRowCount(0);
        try {
            // Se asume que existe un método para traer todos los reportes
            List<Reporte> reportes = control.traerTodosLosReportes(); 
            if (reportes != null) {
                for (Reporte r : reportes) {
                    Object[] fila = {
                        r.getIdReporte(),
                        r.getFechaReporte().toString(),
                        r.getCantidad(),
                        r.getDescripcion()
                    };
                    modeloTabla.addRow(fila);
                }
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error al cargar reportes: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private long getIdReporteSeleccionado() {
        int filaVista = jTableReportes.getSelectedRow();
        if (filaVista == -1) {
            JOptionPane.showMessageDialog(this, "Debe seleccionar un reporte de la tabla.", "Error", JOptionPane.WARNING_MESSAGE);
            return -1;
        }
        int filaModelo = jTableReportes.convertRowIndexToModel(filaVista);
        return (long) modeloTabla.getValueAt(filaModelo, 0); 
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTableReportes = new javax.swing.JTable();
        btnRegistrarReporte = new javax.swing.JButton();
        btnVolver = new javax.swing.JButton();
        btnVerReporte = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jTextFieldFiltroFechaReporte = new javax.swing.JTextField();
        jTextFieldFiltroTipoReporte = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jTableReportes.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(jTableReportes);

        btnRegistrarReporte.setText(" Registrar ");
        btnRegistrarReporte.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRegistrarReporteActionPerformed(evt);
            }
        });

        btnVolver.setText("Volver atrás");
        btnVolver.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVolverActionPerformed(evt);
            }
        });

        btnVerReporte.setText("Modificar");
        btnVerReporte.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVerReporteActionPerformed(evt);
            }
        });

        jButton1.setText("Eliminar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 557, Short.MAX_VALUE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(btnVolver)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnVerReporte)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnRegistrarReporte)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButton1)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 178, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnRegistrarReporte)
                    .addComponent(btnVolver)
                    .addComponent(btnVerReporte)
                    .addComponent(jButton1))
                .addGap(220, 220, 220))
        );

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jLabel1.setText("Gestionar Reportes");

        jLabel2.setText("Fecha de Reporte:");

        jTextFieldFiltroFechaReporte.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldFiltroFechaReporteActionPerformed(evt);
            }
        });

        jTextFieldFiltroTipoReporte.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldFiltroTipoReporteActionPerformed(evt);
            }
        });

        jLabel3.setText("Tipo de Reporte:");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addComponent(jTextFieldFiltroFechaReporte, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(26, 26, 26)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3)
                    .addComponent(jTextFieldFiltroTipoReporte, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jTextFieldFiltroTipoReporte, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jTextFieldFiltroFechaReporte, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(16, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(jLabel1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 240, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(213, 213, 213))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 383, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
/**
     * Cierra esta ventana y muestra la anterior.
     */
    private void btnVolverActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVolverActionPerformed
        if (vistaAnterior != null) {
            this.vistaAnterior.setVisible(true);
        }
        this.dispose();
    }//GEN-LAST:event_btnVolverActionPerformed

    private void btnRegistrarReporteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRegistrarReporteActionPerformed
        JPanel panelForm = new JPanel(new java.awt.GridLayout(0, 1, 5, 5));
        JTextField txtCantidad = new JTextField(10);
        JTextArea txtDescripcion = new JTextArea(5, 20);

        panelForm.add(new JLabel("Cantidad:"));
        panelForm.add(txtCantidad);
        panelForm.add(new JLabel("Descripción:"));
        panelForm.add(new JScrollPane(txtDescripcion));

        int resultado = JOptionPane.showConfirmDialog(this, panelForm, "Registrar Nuevo Reporte", 
                                                    JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

        if (resultado == JOptionPane.OK_OPTION) {
            try {
                int cantidad = Integer.parseInt(txtCantidad.getText().trim());
                String descripcion = txtDescripcion.getText().trim();

                control.registrarReporte(cantidad, descripcion);
                
                JOptionPane.showMessageDialog(this, "✅ Reporte registrado con éxito.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
                cargarReportes();
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(this, "La cantidad debe ser un número entero.", "Error de Validación", JOptionPane.ERROR_MESSAGE);
            } catch (OperacionException e) {
                JOptionPane.showMessageDialog(this, e.getMessage(), "Error de Negocio", JOptionPane.ERROR_MESSAGE);
            }
        }
    }//GEN-LAST:event_btnRegistrarReporteActionPerformed

    private void jTextFieldFiltroFechaReporteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldFiltroFechaReporteActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldFiltroFechaReporteActionPerformed

    private void jTextFieldFiltroTipoReporteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldFiltroTipoReporteActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldFiltroTipoReporteActionPerformed

    private void btnVerReporteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVerReporteActionPerformed
        long idReporte = getIdReporteSeleccionado();
        if (idReporte == -1) return;
        
        try {
            Reporte reporte = control.buscarReporte(idReporte);
            
            JPanel panelForm = new JPanel(new java.awt.GridLayout(0, 1, 5, 5));
            JTextField txtCantidad = new JTextField(String.valueOf(reporte.getCantidad()), 10);
            JTextArea txtDescripcion = new JTextArea(reporte.getDescripcion(), 5, 20);

            panelForm.add(new JLabel("ID: " + idReporte + " (Fecha: " + reporte.getFechaReporte() + ")"));
            panelForm.add(new JLabel("Nueva Cantidad:"));
            panelForm.add(txtCantidad);
            panelForm.add(new JLabel("Nueva Descripción:"));
            panelForm.add(new JScrollPane(txtDescripcion));

            int resultado = JOptionPane.showConfirmDialog(this, panelForm, "Modificar Reporte", 
                                                        JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

            if (resultado == JOptionPane.OK_OPTION) {
                int cantidad = Integer.parseInt(txtCantidad.getText().trim());
                String descripcion = txtDescripcion.getText().trim();
                
                control.modificarReporte(idReporte, cantidad, descripcion);
                
                JOptionPane.showMessageDialog(this, "✅ Reporte modificado con éxito.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
                cargarReportes(); 
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "La cantidad debe ser un número entero.", "Error de Validación", JOptionPane.ERROR_MESSAGE);
        } catch (OperacionException e) {
            JOptionPane.showMessageDialog(this, e.getMessage(), "Error de Negocio", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnVerReporteActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        long idReporte = getIdReporteSeleccionado();
        if (idReporte == -1) return;

        int confirmacion = JOptionPane.showConfirmDialog(this, 
            "¿Está seguro de que desea eliminar el reporte ID: " + idReporte + "?", 
            "Confirmar Eliminación", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);

        if (confirmacion == JOptionPane.YES_OPTION) {
            try {
                control.eliminarReporte(idReporte); 
                JOptionPane.showMessageDialog(this, "✅ Reporte eliminado con éxito.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
                cargarReportes(); 
            } catch (OperacionException e) {
                JOptionPane.showMessageDialog(this, e.getMessage(), "Error de Eliminación", JOptionPane.ERROR_MESSAGE);
            }
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnRegistrarReporte;
    private javax.swing.JButton btnVerReporte;
    private javax.swing.JButton btnVolver;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTableReportes;
    private javax.swing.JTextField jTextFieldFiltroFechaReporte;
    private javax.swing.JTextField jTextFieldFiltroTipoReporte;
    // End of variables declaration//GEN-END:variables
}
