package vista;

import controladora.Controladora; // 🟢 Agregado
import javax.swing.JFrame; // 🟢 Agregado
import javax.swing.JOptionPane; // 🟢 Agregado
import modelo.HistoriaClinica; // 🟢 Agregado
import modelo.OperacionException; // 🟢 Agregado

public class VistaTratamiento extends javax.swing.JFrame {
    
    private final Controladora control;
    private final HistoriaClinica historia;
    private final VistaHistoriaClinica vistaPadre; // 🟢 Vista que nos llamó

    /**
     * 🟢 CONSTRUCTOR MODIFICADO
     */
    public VistaTratamiento(Controladora control, HistoriaClinica historia, VistaHistoriaClinica vistaPadre) {
        this.control = control;
        this.historia = historia;
        this.vistaPadre = vistaPadre;
        initComponents();
        // 🟢 Importante: No cerrar la aplicación, solo esta ventana
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }
    
    // Constructor original (para que NetBeans no falle)
    public VistaTratamiento() {
        this.control = null;
        this.historia = null;
        this.vistaPadre = null;
        initComponents();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextAreaDiagnosticoTratamiento = new javax.swing.JTextArea();
        jLabel3 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTextAreaDescripcionTratamiento = new javax.swing.JTextArea();
        btnGuardar = new javax.swing.JButton();
        btnCerrar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jLabel1.setText("Tratamiento");

        jLabel2.setText("Diagnostico:");

        jTextAreaDiagnosticoTratamiento.setColumns(20);
        jTextAreaDiagnosticoTratamiento.setRows(5);
        jScrollPane1.setViewportView(jTextAreaDiagnosticoTratamiento);

        jLabel3.setText("Descripción Tratamiento:");

        jTextAreaDescripcionTratamiento.setColumns(20);
        jTextAreaDescripcionTratamiento.setRows(5);
        jScrollPane2.setViewportView(jTextAreaDescripcionTratamiento);

        btnGuardar.setText("Guardar");
        btnGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarActionPerformed(evt);
            }
        });

        btnCerrar.setText("Cerrar");
        btnCerrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCerrarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(131, 131, 131)
                        .addComponent(jLabel1))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(23, 23, 23)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel2)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 351, Short.MAX_VALUE)
                            .addComponent(jLabel3)
                            .addComponent(jScrollPane2)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(btnCerrar)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnGuardar)))))
                .addContainerGap(26, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnGuardar)
                    .addComponent(btnCerrar))
                .addContainerGap(19, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed
        String diagnostico = jTextAreaDiagnosticoTratamiento.getText().trim(); //
        String descripcion = jTextAreaDescripcionTratamiento.getText().trim(); //
        
        try {
            // Llama a la controladora
            control.agregarTratamientoAHistoria(historia.getidHistoria(), diagnostico, descripcion);
            
            // Avisa al usuario
            JOptionPane.showMessageDialog(this, "Tratamiento guardado con éxito.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
            
            // 🟢 Refresca la tabla de la vista anterior
            vistaPadre.cargarDatos(); 
            
            // Cierra esta ventana (VistaTratamiento)
            this.dispose(); 

        } catch (OperacionException e) {
            JOptionPane.showMessageDialog(this, e.getMessage(), "Error al Guardar", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnGuardarActionPerformed

    private void btnCerrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCerrarActionPerformed
        this.dispose();
    }//GEN-LAST:event_btnCerrarActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCerrar;
    private javax.swing.JButton btnGuardar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextArea jTextAreaDescripcionTratamiento;
    private javax.swing.JTextArea jTextAreaDiagnosticoTratamiento;
    // End of variables declaration//GEN-END:variables
}
