package vista;

import controladora.Controladora;
import modelo.OperacionException;
import modelo.Tarea;
import modelo.Voluntario;
import javax.swing.JOptionPane;
import javax.swing.DefaultComboBoxModel;
import java.util.Date;

public class RegistrarTarea extends javax.swing.JFrame {
    private final Controladora control;
    private final Voluntario voluntario;
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(RegistrarTarea.class.getName());

    public RegistrarTarea(Controladora control, Voluntario voluntario) {
        this.control = control;
        this.voluntario = voluntario;
        initComponents();
        cargarTipoTarea();
    }
    
    private void cargarTipoTarea() {
        DefaultComboBoxModel<String> model = new DefaultComboBoxModel<>();
        model.addElement("-");
        for (Tarea.TipoTarea tipo : Tarea.TipoTarea.values()) {
            // Muestra los nombres de los Enums (ej. ALIMENTACION)
            model.addElement(tipo.toString()); 
        }
        jComboBoxTiposTarea.setModel(model); // Asumo que jComboBox1 es el Tipo de Tarea
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

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jLabel1.setText("Registrar Tarea:");

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

        btnVolverAtras.setText("Volver");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(42, 42, 42)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(jLabel4)
                                .addComponent(jLabel2)
                                .addComponent(jFormattedTextFieldFecha)
                                .addComponent(jTextFieldDescripcionTarea)
                                .addComponent(jComboBoxTiposTarea, 0, 244, Short.MAX_VALUE)
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                    .addComponent(btnVolverAtras)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(btnRegistrarTarea)))
                            .addComponent(jLabel3)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(78, 78, 78)
                        .addComponent(jLabel1)))
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
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jTextFieldDescripcionTarea, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnRegistrarTarea)
                    .addComponent(btnVolverAtras))
                .addContainerGap(12, Short.MAX_VALUE))
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
            .addGap(0, 338, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnRegistrarTareaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRegistrarTareaActionPerformed
      String fechaStr = jFormattedTextFieldFecha.getText().trim();
        String tipoTareaStr = jComboBoxTiposTarea.getSelectedItem().toString().trim();
        String descripcion = jTextFieldDescripcionTarea.getText().trim(); // Asumo jTextField1 es la descripción
        String nombreGato = "-";
        
        long idVoluntario = voluntario.getIdUsuario();
        
        try {
            // 2. Llamar al controlador
            control.registrarTarea(
                idVoluntario, 
                nombreGato, 
                fechaStr, 
                tipoTareaStr, 
                descripcion
            );
            
            JOptionPane.showMessageDialog(this, "✅ Tarea registrada con éxito.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
            // Cierra la ventana:
            this.dispose();

        } catch (OperacionException e) {
            JOptionPane.showMessageDialog(this, e.getMessage(), "Error de Registro", JOptionPane.WARNING_MESSAGE);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error crítico: " + e.getMessage(), "Error de Sistema", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnRegistrarTareaActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnRegistrarTarea;
    private javax.swing.JButton btnVolverAtras;
    private javax.swing.JComboBox<String> jComboBoxTiposTarea;
    private javax.swing.JFormattedTextField jFormattedTextFieldFecha;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JTextField jTextFieldDescripcionTarea;
    // End of variables declaration//GEN-END:variables
}
