
package vista;

import controladora.Controladora;
import javax.swing.JOptionPane;
import modelo.Usuario;
import modelo.Voluntario;


public class VistaVoluntario extends javax.swing.JFrame {
    
    private final Controladora control;
    private final Voluntario voluntarioLogueado;

    public VistaVoluntario(Controladora control, Usuario usuario) {
        this.control = control;
        this.voluntarioLogueado = (Voluntario) usuario; // Guardar el voluntario
        initComponents();
        jLabel1.setText("Voluntario, " + usuario.getNombre());
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        btnRegistrarGato = new javax.swing.JButton();
        btnRegistrarTarea = new javax.swing.JButton();
        btnAsignarGato = new javax.swing.JButton();
        btnGestionarVisitas = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        btnRegistrarGato.setText("Registrar Gato");
        btnRegistrarGato.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRegistrarGatoActionPerformed(evt);
            }
        });

        btnRegistrarTarea.setText("Registrar Tarea");
        btnRegistrarTarea.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRegistrarTareaActionPerformed(evt);
            }
        });

        btnAsignarGato.setText("Asignar Gato a Familia");
        btnAsignarGato.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAsignarGatoActionPerformed(evt);
            }
        });

        btnGestionarVisitas.setText("Gestionar Visitas");
        btnGestionarVisitas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGestionarVisitasActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnGestionarVisitas, javax.swing.GroupLayout.PREFERRED_SIZE, 168, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnAsignarGato, javax.swing.GroupLayout.PREFERRED_SIZE, 168, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnRegistrarTarea, javax.swing.GroupLayout.PREFERRED_SIZE, 168, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnRegistrarGato, javax.swing.GroupLayout.PREFERRED_SIZE, 168, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(32, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnRegistrarGato)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnRegistrarTarea)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnAsignarGato)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnGestionarVisitas)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jLabel1.setText("Voluntario, ");

        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel2.setText("¿qué desea realizar?");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(56, 56, 56)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addComponent(jLabel1))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(26, 26, 26)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(30, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(34, 34, 34)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(18, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnRegistrarTareaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRegistrarTareaActionPerformed
        RegistrarTarea regTarea = new RegistrarTarea(this.control, this.voluntarioLogueado);
        
        // 2. Mostrar la ventana
        regTarea.setVisible(true);
        regTarea.setLocationRelativeTo(this);
        this.setVisible(false); // Ocultar el menú
    }//GEN-LAST:event_btnRegistrarTareaActionPerformed

    private void btnAsignarGatoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAsignarGatoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnAsignarGatoActionPerformed

    private void btnGestionarVisitasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGestionarVisitasActionPerformed
        // 1. Crear instancia de la nueva ventana de gestión
        VistaGestionarVisitas gestion = new VistaGestionarVisitas(this.control, this.voluntarioLogueado, this);
        
        // 2. Mostrar la nueva ventana
        gestion.setVisible(true);
        gestion.setLocationRelativeTo(this); // Centrarla
        
        // 3. Ocultar esta ventana (el menú de Voluntario)
        this.setVisible(false);
    }//GEN-LAST:event_btnGestionarVisitasActionPerformed

    private void btnRegistrarGatoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRegistrarGatoActionPerformed
          try {
        // 1. Crear la nueva instancia, enviando 'this' como la vista anterior
        RegistrarGato registrarGato = new RegistrarGato(control, this); 
        
        // 2. Mostrar la nueva ventana
        registrarGato.setVisible(true);
        registrarGato.setLocationRelativeTo(null);
        
        // 3. Ocultar la ventana actual (VistaVoluntario)
        this.setVisible(false);
        
    } catch (Exception e) {
        JOptionPane.showMessageDialog(this,
            "Error al abrir el registro de gato: " + e.getMessage(),
            "Error de Navegación",
            JOptionPane.ERROR_MESSAGE);
        e.printStackTrace();
    } 
    }//GEN-LAST:event_btnRegistrarGatoActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAsignarGato;
    private javax.swing.JButton btnGestionarVisitas;
    private javax.swing.JButton btnRegistrarGato;
    private javax.swing.JButton btnRegistrarTarea;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    // End of variables declaration//GEN-END:variables
}
