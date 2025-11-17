package vista;

import controladora.Controladora;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.RowFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import modelo.FamiliaAdoptante;
import modelo.Gato;
import modelo.OperacionException;
import modelo.Tarea;
import modelo.Usuario;
import modelo.Voluntario;

public class VistaTareasVoluntario extends javax.swing.JFrame {
    
   private final Controladora control;
    private final Usuario veterinario; 
    private final VistaVoluntario vistaAnterior;
    private final Usuario voluntarioLogueado;
    private DefaultTableModel modeloTabla;
    private TableRowSorter<TableModel> sorter;

    /**
     * Constructor para la VistaVeterinario.
     * @param control La controladora principal de la aplicación.
     * @param usuario El objeto Usuario (Veterinario) que ha iniciado sesión.
     */
    public VistaTareasVoluntario(Controladora control, Usuario usuario, VistaVoluntario vistaAnterior) {
        this.control = control;
        this.veterinario = usuario;
        this.vistaAnterior = vistaAnterior;
        this.voluntarioLogueado = usuario;
        initComponents();
        configurarTabla(); // Prepara la JTable y el sorter
        cargarTareas();     // Carga los datos iniciales
        
        // Personaliza el saludo
        jLabel1.setText("Gatos (Veterinario: " + usuario.getNombre() + ")");
    }
    /**
     * Configura la JTable, el modelo de tabla y el sistema de filtrado (sorter).
     */
    private void configurarTabla() {
        
        String titulos[] = {"ID", "Fecha", "Tipo Tarea", "Gato Asignado", "Descripción"};
        modeloTabla = new DefaultTableModel(null, titulos) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        jTableTareas.setModel(modeloTabla);

        sorter = new TableRowSorter<>(modeloTabla);
        jTableTareas.setRowSorter(sorter);

        jTableTareas.getColumnModel().getColumn(0).setMaxWidth(0);
        jTableTareas.getColumnModel().getColumn(0).setMinWidth(0);
        jTableTareas.getColumnModel().getColumn(0).setPreferredWidth(0);
    }

    
    public void cargarTareas() { 
        modeloTabla.setRowCount(0); 
        try {
            // 🟢 LLAMADA AL MÉTODO QUE TRAE TODAS LAS TAREAS
            List<Tarea> tareas = control.traerTodasLasTareas(); 

            if (tareas != null && !tareas.isEmpty()) {
                for (Tarea t : tareas) {
                    // Obtener los nombres necesarios para la tabla
                    String nombreGato = (t.getGatoAsignado() != null) ? t.getGatoAsignado().getNombre() : "N/A";
                    
                    // Asegúrese de que el formato de fecha sea legible
                    String fechaStr = (t.getFecha() != null) ? t.getFecha().toString() : "N/A";
                    
                    Object[] fila = {
                        t.getIdTarea(),
                        fechaStr,
                        t.getTipoTarea().toString(),
                        nombreGato,
                        t.getDescripcion()
                    };
                    modeloTabla.addRow(fila);
                }
            }
        } catch (OperacionException e) {
            JOptionPane.showMessageDialog(this, e.getMessage(), "Información", JOptionPane.INFORMATION_MESSAGE);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error inesperado al cargar tareas: " + e.getMessage(), "Error Crítico", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    /**
     * Aplica los filtros de los JTextField a la JTable usando el sorter.
     */
    private void aplicarFiltros() {
        List<RowFilter<Object, Object>> filtros = new java.util.ArrayList<>();
        
        String filtroGato = jTextFieldFiltroFechaTarea.getText().trim();
        String filtroFamilia = jTextFieldFiltroTipoTarea.getText().trim();

        // Columna 1 es "Nombre Gato"
        if (!filtroGato.isEmpty()) {
            filtros.add(RowFilter.regexFilter("(?i)" + filtroGato, 1));
        }
        // Columna 3 es "Familia Adoptante"
        if (!filtroFamilia.isEmpty()) {
            filtros.add(RowFilter.regexFilter("(?i)" + filtroFamilia, 3));
        }
        
        // Aplica los filtros combinados (AND)
        sorter.setRowFilter(RowFilter.andFilter(filtros));
    }
    
    /**
     * Obtiene el ID del gato seleccionado en la tabla.
     * @return El ID del Gato, o -1 si no hay selección.
     */
    private long getIdGatoSeleccionado() {
        int filaVista = jTableTareas.getSelectedRow();
        if (filaVista == -1) {
            JOptionPane.showMessageDialog(this, "Debe seleccionar un gato de la tabla.", "Error", JOptionPane.WARNING_MESSAGE);
            return -1;
        }
        
        // Convierte el índice de la vista (filtrada) al índice del modelo (datos reales)
        int filaModelo = jTableTareas.convertRowIndexToModel(filaVista);
        return (long) modeloTabla.getValueAt(filaModelo, 0); // Columna 0 = ID
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTableTareas = new javax.swing.JTable();
        btnVolver = new javax.swing.JButton();
        btnRegistrarGato = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jTextFieldFiltroFechaTarea = new javax.swing.JTextField();
        jTextFieldFiltroTipoTarea = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jTableTareas.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane1.setViewportView(jTableTareas);

        btnVolver.setText("Volver atrás");
        btnVolver.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVolverActionPerformed(evt);
            }
        });

        btnRegistrarGato.setText("Registrar Tarea");
        btnRegistrarGato.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRegistrarGatoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 493, Short.MAX_VALUE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(btnVolver)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnRegistrarGato)
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
                    .addComponent(btnVolver)
                    .addComponent(btnRegistrarGato))
                .addGap(220, 220, 220))
        );

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jLabel1.setText("Tareas");

        jLabel2.setText("Fecha Tarea:");

        jTextFieldFiltroFechaTarea.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldFiltroFechaTareaActionPerformed(evt);
            }
        });

        jTextFieldFiltroTipoTarea.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldFiltroTipoTareaActionPerformed(evt);
            }
        });

        jLabel3.setText("Tipo Tarea:");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addComponent(jTextFieldFiltroFechaTarea, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(26, 26, 26)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3)
                    .addComponent(jTextFieldFiltroTipoTarea, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(225, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jTextFieldFiltroTipoTarea, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jTextFieldFiltroFechaTarea, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
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
                .addGap(16, 16, 16)
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

    private void jTextFieldFiltroFechaTareaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldFiltroFechaTareaActionPerformed
        aplicarFiltros();
    }//GEN-LAST:event_jTextFieldFiltroFechaTareaActionPerformed

    private void jTextFieldFiltroTipoTareaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldFiltroTipoTareaActionPerformed
        aplicarFiltros();
    }//GEN-LAST:event_jTextFieldFiltroTipoTareaActionPerformed
/**
     * Cierra esta ventana y muestra la anterior.
     */
    private void btnVolverActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVolverActionPerformed
      // 🟢 CORRECCIÓN: Hace visible la ventana del menú principal (VistaVoluntario)
        if (this.vistaAnterior != null) {
            this.vistaAnterior.setVisible(true); 
        }
        
        // Cierra la ventana actual
        this.dispose();
     
      
        
    }//GEN-LAST:event_btnVolverActionPerformed

    private void btnRegistrarGatoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRegistrarGatoActionPerformed
       try {
      
        Voluntario voluntario = (Voluntario)this.voluntarioLogueado; 
        
        // 🟢 LLAMADA CORREGIDA: Pasa el control, el Voluntario, y 'this' (la vista de lista de tareas)
        RegistrarTarea regTarea = new RegistrarTarea(
            this.control, 
            voluntario, 
            this // VistaTareasVoluntario se pasa como padre
        );

        regTarea.setVisible(true);
        regTarea.setLocationRelativeTo(null);
        this.setVisible(false); // Oculta la vista de lista

    } catch (Exception e) {
        JOptionPane.showMessageDialog(this,
            "Error al abrir el registro de tarea: " + e.getMessage(),
            "Error de Navegación",
            JOptionPane.ERROR_MESSAGE);
        e.printStackTrace();
    } 
    }//GEN-LAST:event_btnRegistrarGatoActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnRegistrarGato;
    private javax.swing.JButton btnVolver;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTableTareas;
    private javax.swing.JTextField jTextFieldFiltroFechaTarea;
    private javax.swing.JTextField jTextFieldFiltroTipoTarea;
    // End of variables declaration//GEN-END:variables
}
