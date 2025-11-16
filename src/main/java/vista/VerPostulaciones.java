
package vista;

import controladora.Controladora;
import modelo.OperacionException;
import modelo.Postulacion;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import javax.swing.JFrame;
import java.util.List;
import javax.swing.JTable;

public class VerPostulaciones extends javax.swing.JFrame {
    
    private final Controladora control;
    private final JFrame vistaAnterior;
    private DefaultTableModel modeloTabla;
    private TableRowSorter<TableModel> sorter;
   
    public VerPostulaciones(Controladora control, JFrame vistaAnterior) {
       this.control = control;
        this.vistaAnterior = vistaAnterior;
        initComponents();
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        configurarTabla();
        cargarPostulaciones();
    }
    public VerPostulaciones() {
        this.control = null;
        this.vistaAnterior = null;
        initComponents();
    }
    
    private void configurarTabla() {
        String titulos[] = {"ID Postulación", "Gato", "Familia Postulante", "Tipo Adopción", "Estado"};
        modeloTabla = new DefaultTableModel(null, titulos) {
            @Override
            public boolean isCellEditable(int row, int column) { return false; }
        };
        jTable3.setModel(modeloTabla); // 🟢 Usamos su jTable3
        
        // Configuración de tabla
        sorter = new TableRowSorter<>(modeloTabla);
        jTable3.setRowSorter(sorter);
        jTable3.getColumnModel().getColumn(0).setMaxWidth(0);
        jTable3.getColumnModel().getColumn(0).setMinWidth(0);
        jTable3.getColumnModel().getColumn(0).setPreferredWidth(0);
    }
    
    public void cargarPostulaciones() {
        // Validación para evitar NullPointerException en el diseñador
        if (modeloTabla == null || control == null) return;
        
        modeloTabla.setRowCount(0);
        try {
            List<Postulacion> postulaciones = control.traerTodasLasPostulaciones(); 
            if (postulaciones != null) {
                for (Postulacion p : postulaciones) {
                    Object[] fila = {
                        p.getIdPostulacion(),
                        p.getGatoRelacionado().getNombre(),
                        p.getFamiliaPostulante().getNombre(),
                        p.getTipoAdopcion().toString(),
                        p.getEstado().toString()
                    };
                    modeloTabla.addRow(fila);
                }
            }
        } catch (OperacionException e) {
            JOptionPane.showMessageDialog(this, e.getMessage(), "Información", JOptionPane.INFORMATION_MESSAGE);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error al cargar postulaciones: " + e.getMessage(), "Error Crítico", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private long getIdSeleccionado() {
        int filaVista = jTable3.getSelectedRow(); // 🟢 Usamos su jTable3
        if (filaVista == -1) {
            JOptionPane.showMessageDialog(this, "Debe seleccionar una postulación de la tabla.", "Error", JOptionPane.WARNING_MESSAGE);
            return -1;
        }
        int filaModelo = jTable3.convertRowIndexToModel(filaVista);
        return (long) modeloTabla.getValueAt(filaModelo, 0); 
    }

  
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel4 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        jScrollPaneTablaGatosDisponibles2 = new javax.swing.JScrollPane();
        jTable3 = new javax.swing.JTable();
        btnVolverAtras2 = new javax.swing.JButton();
        btnCambiarEstado = new javax.swing.JButton();
        jLabel8 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setPreferredSize(new java.awt.Dimension(567, 440));

        jLabel7.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jLabel7.setText("Postulaciones Disponibles ");

        jTable3.setModel(new javax.swing.table.DefaultTableModel(
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
        jTable3.setName("Tabla Gato"); // NOI18N
        jScrollPaneTablaGatosDisponibles2.setViewportView(jTable3);

        btnVolverAtras2.setText("Volver Atrás");
        btnVolverAtras2.setName("Actualizar"); // NOI18N
        btnVolverAtras2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVolverAtras2ActionPerformed(evt);
            }
        });

        btnCambiarEstado.setText("Cambiar estado de postulación");
        btnCambiarEstado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCambiarEstadoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap(20, Short.MAX_VALUE)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(btnVolverAtras2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnCambiarEstado))
                    .addComponent(jLabel7)
                    .addComponent(jScrollPaneTablaGatosDisponibles2, javax.swing.GroupLayout.PREFERRED_SIZE, 527, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8))
                .addGap(20, 20, 20))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(jLabel7)
                .addGap(18, 18, 18)
                .addComponent(jLabel8)
                .addGap(18, 18, 18)
                .addComponent(jScrollPaneTablaGatosDisponibles2, javax.swing.GroupLayout.PREFERRED_SIZE, 265, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 51, Short.MAX_VALUE)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnVolverAtras2)
                    .addComponent(btnCambiarEstado))
                .addGap(19, 19, 19))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnVolverAtras2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVolverAtras2ActionPerformed
        long idPostulacion = getIdSeleccionado();
        if (idPostulacion == -1) return;
        
        // 1. Obtener las opciones del Enum Estado
        Postulacion.Estado[] opciones = Postulacion.Estado.values(); 
        
        try {
            // 2. Buscar la postulación actual para precargar el estado
            Postulacion postulacionActual = control.buscarPostulacionPorId(idPostulacion);
            
            // 3. Mostrar el selector de opciones
            Postulacion.Estado nuevoEstado = (Postulacion.Estado) JOptionPane.showInputDialog(
                this,
                "Seleccione el nuevo estado para la postulación:",
                "Cambiar Estado",
                JOptionPane.QUESTION_MESSAGE,
                null, 
                opciones, 
                postulacionActual.getEstado() 
            );

            // 4. Procesar la selección
            if (nuevoEstado != null && nuevoEstado != postulacionActual.getEstado()) {
                
                // 5. Llamar a la Controladora
                control.cambiarEstadoPostulacion(idPostulacion, nuevoEstado);
                
                JOptionPane.showMessageDialog(this, 
                    "Estado de la postulación ID " + idPostulacion + " actualizado a: " + nuevoEstado.toString(), 
                    "Éxito", 
                    JOptionPane.INFORMATION_MESSAGE);
                cargarPostulaciones(); // Recargar la tabla
            }
        } catch (OperacionException e) {
            JOptionPane.showMessageDialog(this, e.getMessage(), "Error de Operación", JOptionPane.ERROR_MESSAGE);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error inesperado: " + e.getMessage(), "Error Crítico", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnVolverAtras2ActionPerformed

    private void btnCambiarEstadoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCambiarEstadoActionPerformed
        if (vistaAnterior != null) {
            this.vistaAnterior.setVisible(true);
        }
        this.dispose();
      
    }//GEN-LAST:event_btnCambiarEstadoActionPerformed

    
    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCambiarEstado;
    private javax.swing.JButton btnVerPerfilGatoSeleccionado;
    private javax.swing.JButton btnVerPerfilGatoSeleccionado1;
    private javax.swing.JButton btnVolverAtras;
    private javax.swing.JButton btnVolverAtras1;
    private javax.swing.JButton btnVolverAtras2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPaneTablaGatosDisponibles;
    private javax.swing.JScrollPane jScrollPaneTablaGatosDisponibles1;
    private javax.swing.JScrollPane jScrollPaneTablaGatosDisponibles2;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable jTable2;
    private javax.swing.JTable jTable3;
    private javax.swing.JTextField jTextFieldFiltroNombreGato;
    private javax.swing.JTextField jTextFieldFiltroNombreGato1;
    private javax.swing.JTextField jTextFieldFiltroNombreRazaGato;
    private javax.swing.JTextField jTextFieldFiltroNombreRazaGato1;
    // End of variables declaration//GEN-END:variables
}
