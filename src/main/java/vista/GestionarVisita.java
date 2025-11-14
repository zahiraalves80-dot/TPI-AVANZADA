package vista;

import java.time.LocalDate;
import javax.swing.JOptionPane;
import controladora.Controladora;
import modelo.OperacionException;
import modelo.Voluntario;

public class GestionarVisita extends javax.swing.JFrame {

    private final Controladora control;
    private final Voluntario voluntario;
    
    public GestionarVisita(Controladora control, Voluntario voluntario) {
        this.control = control;
        this.voluntario = voluntario;
        initComponents();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        bttnEliminarVisita = new javax.swing.JButton();
        bttnRegistrarVisita = new javax.swing.JButton();
        bttnModificarVisita = new javax.swing.JButton();
        bttnHistorialVisitas = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jLabel1.setText("Gestionar visitas");

        bttnEliminarVisita.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        bttnEliminarVisita.setText("Eliminar Visita");
        bttnEliminarVisita.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bttnEliminarVisitaActionPerformed(evt);
            }
        });

        bttnRegistrarVisita.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        bttnRegistrarVisita.setText("Registrar Visita");
        bttnRegistrarVisita.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bttnRegistrarVisitaActionPerformed(evt);
            }
        });

        bttnModificarVisita.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        bttnModificarVisita.setText("Modificar Visita");
        bttnModificarVisita.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bttnModificarVisitaActionPerformed(evt);
            }
        });

        bttnHistorialVisitas.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        bttnHistorialVisitas.setText("Historial de Visitas");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(bttnRegistrarVisita, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(bttnEliminarVisita, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(bttnModificarVisita, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(bttnHistorialVisitas))
                .addGap(108, 108, 108))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(96, 96, 96)
                .addComponent(jLabel1)
                .addContainerGap(98, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(49, 49, 49)
                .addComponent(jLabel1)
                .addGap(35, 35, 35)
                .addComponent(bttnRegistrarVisita)
                .addGap(26, 26, 26)
                .addComponent(bttnEliminarVisita)
                .addGap(29, 29, 29)
                .addComponent(bttnModificarVisita)
                .addGap(26, 26, 26)
                .addComponent(bttnHistorialVisitas)
                .addContainerGap(112, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void bttnEliminarVisitaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bttnEliminarVisitaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_bttnEliminarVisitaActionPerformed

    private void bttnModificarVisitaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bttnModificarVisitaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_bttnModificarVisitaActionPerformed

    private void bttnRegistrarVisitaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bttnRegistrarVisitaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_bttnRegistrarVisitaActionPerformed
 
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bttnEliminarVisita;
    private javax.swing.JButton bttnHistorialVisitas;
    private javax.swing.JButton bttnModificarVisita;
    private javax.swing.JButton bttnRegistrarVisita;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    // End of variables declaration//GEN-END:variables
}
