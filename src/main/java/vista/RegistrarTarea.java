package vista;

import controladora.Controladora;
import modelo.OperacionException;
import modelo.Tarea;
import modelo.Voluntario;
import javax.swing.JOptionPane;
import javax.swing.DefaultComboBoxModel;
import java.util.Date;
import java.util.List;
import modelo.Gato;
import javax.swing.JFrame;
import modelo.Zona;

public class RegistrarTarea extends javax.swing.JFrame {
    private final Controladora control;
    private final Voluntario voluntario;
    private final VistaTareasVoluntario vistaTareas;
    private javax.swing.JFormattedTextField jFormattedTextFieldFecha1; 
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(RegistrarTarea.class.getName());
    

    public RegistrarTarea(Controladora control, Voluntario voluntario, VistaTareasVoluntario vistaTareas) {
        this.control = control;
        this.voluntario = voluntario;
        this.vistaTareas = vistaTareas;
        initComponents();
        cargarTipoTarea();
        cargarDatosAdicionales();
    }
   
    class ComboBoxItem {
        private long id;
        private String display;
        public ComboBoxItem(long id, String display) {
            this.id = id;
            this.display = display;
        }
        public long getId() { return id; }
        @Override
        public String toString() { return display; }
    }
    
    
    private void cargarDatosAdicionales() {
    try {
        
        List<Gato> gatos = control.traerTodosLosGatos();
        
        DefaultComboBoxModel<ComboBoxItem> modeloGato = new DefaultComboBoxModel<>(); 
        modeloGato.addElement(new ComboBoxItem(0, "--- Seleccionar Gato ---")); 
        for (Gato g : gatos) {
            modeloGato.addElement(new ComboBoxItem(g.getIdGato(), g.getNombre() + " (" + g.getRaza() + ")"));
        }
        
       
        jComboBoxGatoSeleccionado.setModel((DefaultComboBoxModel)modeloGato); 
        
        // --- 2. Cargar Voluntarios ---
        List<Voluntario> voluntarios = control.traerTodosLosVoluntarios();
        DefaultComboBoxModel<ComboBoxItem> modeloVoluntario = new DefaultComboBoxModel<>();
        modeloVoluntario.addElement(new ComboBoxItem(0, "--- Seleccionar Voluntario ---")); 
        for (Voluntario v : voluntarios) {
            modeloVoluntario.addElement(new ComboBoxItem(v.getIdUsuario(), v.getNombre() + " (ID: " + v.getIdUsuario() + ")"));
        }
        
        jComboBoxVoluntarioEncargadoTarea.setModel((DefaultComboBoxModel)modeloVoluntario);
        
       
         for(int i = 0; i < modeloVoluntario.getSize(); i++) {
            if (voluntario != null && modeloVoluntario.getElementAt(i).getId() == voluntario.getIdUsuario()) {
                jComboBoxVoluntarioEncargadoTarea.setSelectedIndex(i);
                break;
            }
        }
        
    } catch (OperacionException e) {
        JOptionPane.showMessageDialog(this, "Error al cargar listas: " + e.getMessage(), "Error de Carga", JOptionPane.ERROR_MESSAGE);
    } catch (Exception e) {
        JOptionPane.showMessageDialog(this, "Error crítico al inicializar las listas.", "Error Crítico", JOptionPane.ERROR_MESSAGE);
    }
}
    private void cargarTipoTarea() {
        DefaultComboBoxModel<String> model = new DefaultComboBoxModel<>();
        model.addElement("-");
        for (Tarea.TipoTarea tipo : Tarea.TipoTarea.values()) {
            // Muestra los nombres de los Enums (ej. ALIMENTACION)
            model.addElement(tipo.toString()); 
        }
        jComboBoxTiposTarea.setModel(model); 
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jFormattedTextFieldFecha = new javax.swing.JFormattedTextField();
        jLabel3 = new javax.swing.JLabel();
        jTextFieldDescripcionTarea = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        btnRegistrarTarea = new javax.swing.JButton();
        jComboBoxTiposTarea = new javax.swing.JComboBox<>();
        btnVolverAtras = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jComboBoxVoluntarioEncargadoTarea = new javax.swing.JComboBox<>();
        jComboBoxGatoSeleccionado = new javax.swing.JComboBox<>();
        jLabel7 = new javax.swing.JLabel();
        jComboBox1 = new javax.swing.JComboBox<>();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jLabel1.setText("Registrar Tarea");

        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel2.setText("Fecha:");

        jFormattedTextFieldFecha.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.DateFormatter(new java.text.SimpleDateFormat("dd/MM/yyyy"))));

        jLabel3.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel3.setText("Tipo de Tarea:");

        jLabel4.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel4.setText("Descripcion:");

        btnRegistrarTarea.setText("Registrar");
        btnRegistrarTarea.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRegistrarTareaActionPerformed(evt);
            }
        });

        jComboBoxTiposTarea.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jComboBoxTiposTarea.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBoxTiposTareaActionPerformed(evt);
            }
        });

        btnVolverAtras.setText("Volver");
        btnVolverAtras.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVolverAtrasActionPerformed(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel5.setText("Ubicación:");

        jLabel6.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel6.setText("Voluntario Encargado:");

        jComboBoxVoluntarioEncargadoTarea.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jComboBoxVoluntarioEncargadoTarea.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBoxVoluntarioEncargadoTareaActionPerformed(evt);
            }
        });

        jComboBoxGatoSeleccionado.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jComboBoxGatoSeleccionado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBoxGatoSeleccionadoActionPerformed(evt);
            }
        });

        jLabel7.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel7.setText("Gato:");

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jComboBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(78, 78, 78)
                        .addComponent(jLabel1))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(42, 42, 42)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel7)
                            .addComponent(jComboBoxGatoSeleccionado, 0, 244, Short.MAX_VALUE)
                            .addComponent(jLabel6)
                            .addComponent(jLabel4)
                            .addComponent(jLabel2)
                            .addComponent(jFormattedTextFieldFecha)
                            .addComponent(jTextFieldDescripcionTarea)
                            .addComponent(jComboBoxTiposTarea, 0, 244, Short.MAX_VALUE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(btnVolverAtras)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnRegistrarTarea))
                            .addComponent(jLabel3)
                            .addComponent(jLabel5)
                            .addComponent(jComboBoxVoluntarioEncargadoTarea, 0, 244, Short.MAX_VALUE)
                            .addComponent(jComboBox1, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap(46, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jFormattedTextFieldFecha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel3)
                .addGap(12, 12, 12)
                .addComponent(jComboBoxTiposTarea, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel7)
                .addGap(12, 12, 12)
                .addComponent(jComboBoxGatoSeleccionado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel6)
                .addGap(12, 12, 12)
                .addComponent(jComboBoxVoluntarioEncargadoTarea, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jTextFieldDescripcionTarea, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnVolverAtras)
                    .addComponent(btnRegistrarTarea))
                .addGap(14, 14, 14))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 332, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 526, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnRegistrarTareaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRegistrarTareaActionPerformed
     String fechaStr = jFormattedTextFieldFecha.getText().trim();
        String tipoTareaStr = jComboBoxTiposTarea.getSelectedItem().toString().trim();
        String descripcion = jTextFieldDescripcionTarea.getText().trim();
        
        // 🟢 Obtener IDs de los ComboBoxes
        ComboBoxItem gatoItem = (ComboBoxItem) jComboBoxGatoSeleccionado.getSelectedItem();
        ComboBoxItem voluntarioItem = (ComboBoxItem) jComboBoxVoluntarioEncargadoTarea.getSelectedItem();
        
        long idVoluntario = voluntarioItem.getId();
        long idGato = gatoItem.getId();
        String ubicacion = jFormattedTextFieldFecha1.getText().trim(); 
        
        try {
           
            if (idGato == 0 || idVoluntario == 0 || ubicacion.isEmpty() || tipoTareaStr.equals("-")) {
                throw new OperacionException("Debe seleccionar Gato, Tipo de Tarea y especificar la Ubicación.");
            }
            
            // 2. Llamar al controlador con los datos completos
            control.registrarTareaCompleta(
                idVoluntario, 
                idGato, 
                ubicacion, 
                fechaStr, 
                tipoTareaStr, 
                descripcion
            );
            
            // 3. Éxito y recarga
            JOptionPane.showMessageDialog(this, " Tarea registrada con éxito.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
            
            this.vistaTareas.cargarTareas();
            this.dispose();

        } catch (OperacionException e) {
            JOptionPane.showMessageDialog(this, e.getMessage(), "Error de Registro", JOptionPane.WARNING_MESSAGE);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error crítico: " + e.getMessage(), "Error de Sistema", JOptionPane.ERROR_MESSAGE);
        }
    
    
    
    
    }//GEN-LAST:event_btnRegistrarTareaActionPerformed

    private void btnVolverAtrasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVolverAtrasActionPerformed
       
        this.vistaTareas.setVisible(true); 
        
        this.dispose();
    
    }//GEN-LAST:event_btnVolverAtrasActionPerformed

    private void jComboBoxGatoSeleccionadoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBoxGatoSeleccionadoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBoxGatoSeleccionadoActionPerformed

    private void jComboBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBox1ActionPerformed

    private void jComboBoxVoluntarioEncargadoTareaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBoxVoluntarioEncargadoTareaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBoxVoluntarioEncargadoTareaActionPerformed

    private void jComboBoxTiposTareaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBoxTiposTareaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBoxTiposTareaActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnRegistrarTarea;
    private javax.swing.JButton btnVolverAtras;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JComboBox<String> jComboBoxGatoSeleccionado;
    private javax.swing.JComboBox<String> jComboBoxTiposTarea;
    private javax.swing.JComboBox<String> jComboBoxVoluntarioEncargadoTarea;
    private javax.swing.JFormattedTextField jFormattedTextFieldFecha;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JTextField jTextFieldDescripcionTarea;
    // End of variables declaration//GEN-END:variables
}
