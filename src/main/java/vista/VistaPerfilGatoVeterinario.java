
package vista;


import java.awt.Dimension;
import java.awt.image.BufferedImage;
import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.WindowConstants;
import javax.swing.JFrame; // 🟢 Agregado

// --- Imports del Modelo (Lógica) ---
import controladora.Controladora;
import modelo.FamiliaAdoptante;
import modelo.Gato;
import modelo.OperacionException;
import modelo.Zona;
import modelo.Veterinario; // 🟢 Agregado
import modelo.HistoriaClinica; // 🟢 Agregado

// --- Imports para QR (Librería Zxing) ---
import com.google.zxing.BarcodeFormat;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;

// --- Imports para MAPA (NUEVA LIBRERÍA JXMapViewer2) ---
import org.jxmapviewer.JXMapViewer;
import org.jxmapviewer.OSMTileFactoryInfo;
import org.jxmapviewer.viewer.DefaultTileFactory;
import org.jxmapviewer.viewer.GeoPosition;
import org.jxmapviewer.viewer.TileFactoryInfo;
import org.jxmapviewer.viewer.DefaultWaypoint;
import org.jxmapviewer.viewer.Waypoint;
import org.jxmapviewer.viewer.WaypointPainter;
import java.util.HashSet;
import java.util.Set;


public class VistaPerfilGatoVeterinario extends javax.swing.JFrame {
    
   private Gato gato; // 🟢 Hecho modificable para refrescar
    private final Veterinario veterinario; // 🟢 Guardamos al veterinario
    private final Controladora control;
    private final javax.swing.JLabel lblFoto;
    private final JFrame vistaAnterior; // 🟢 Guardamos la vista anterior

    /**
     * 🟢 CONSTRUCTOR MODIFICADO
     * Recibe los objetos necesarios y la vista anterior para poder volver.
     */
    public VistaPerfilGatoVeterinario(Controladora control, Gato gato, Veterinario veterinario, JFrame vistaAnterior) {
        this.control = control;
        this.gato = gato;
        this.veterinario = veterinario;
        this.vistaAnterior = vistaAnterior; 
        
        this.lblFoto = new javax.swing.JLabel();
        initComponents(); 
        
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); 
        
        jPanel2.setLayout(new java.awt.BorderLayout());
        jPanel2.add(lblFoto, java.awt.BorderLayout.CENTER);
        poblarDatos(); 
    }

    /**
     * Rellena todos los JLabels con la información del Gato.
     */
    private void poblarDatos() {
        // ... (Tu método poblarDatos() está perfecto, no necesita cambios)
        lblNombreDato.setText(gato.getNombre());
        lblRazaDato.setText(gato.getRaza());
        lblGeneroDato.setText(gato.getSexo());
        lblEstadoSaludDato.setText(gato.getestadoFisico().toString());
        lblObservacionesDato.setText(gato.getCaracteristicas());
        
        if (gato.getZona() != null) {
            lblZonaDato.setText(gato.getZona().getNombreZona());
        } else {
            lblZonaDato.setText("Sin zona asignada");
        }
        
        String ruta = gato.getRutaFoto(); 
        if (ruta != null && !ruta.isEmpty()) {
            // ... (lógica de carga de imagen)
        } else {
            lblFoto.setText("Sin foto registrada");
            lblFoto.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        }
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        lblNombreDato = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        lblZonaDato = new javax.swing.JLabel();
        lblRazaDato = new javax.swing.JLabel();
        lblGeneroDato = new javax.swing.JLabel();
        lblEstadoSaludDato = new javax.swing.JLabel();
        btnVerMapa = new javax.swing.JButton();
        btnVerQR = new javax.swing.JButton();
        btnCerrar = new javax.swing.JButton();
        btnEmitirCertificado = new javax.swing.JButton();
        lblObservacionesDato = new javax.swing.JLabel();
        btnCambiarEstadoSalud = new javax.swing.JButton();
        btnVerHistorialClinico = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        lblNombreDato.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        lblNombreDato.setText("NOMBRE");

        jPanel2.setBackground(new java.awt.Color(204, 204, 204));
        jPanel2.setToolTipText("");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 226, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 226, Short.MAX_VALUE)
        );

        lblZonaDato.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        lblZonaDato.setText("ZONA");

        lblRazaDato.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        lblRazaDato.setText("RAZA");

        lblGeneroDato.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        lblGeneroDato.setText("SEXO");

        lblEstadoSaludDato.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        lblEstadoSaludDato.setText("ESTADO DE SALUD");

        btnVerMapa.setText("Ver Mapa");
        btnVerMapa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVerMapaActionPerformed(evt);
            }
        });

        btnVerQR.setText("Ver QR");
        btnVerQR.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVerQRActionPerformed(evt);
            }
        });

        btnCerrar.setText("Cerrar");
        btnCerrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCerrarActionPerformed(evt);
            }
        });

        btnEmitirCertificado.setText("Emitir Certificado Aptitud");
        btnEmitirCertificado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEmitirCertificadoActionPerformed(evt);
            }
        });

        lblObservacionesDato.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        lblObservacionesDato.setText("OBSERVACIONES");

        btnCambiarEstadoSalud.setText("Cambiar estado salud");
        btnCambiarEstadoSalud.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCambiarEstadoSaludActionPerformed(evt);
            }
        });

        btnVerHistorialClinico.setText("Ver Historial Clínico");
        btnVerHistorialClinico.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVerHistorialClinicoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(260, 260, 260)
                        .addComponent(btnCerrar))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(40, 40, 40)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(lblObservacionesDato, javax.swing.GroupLayout.DEFAULT_SIZE, 458, Short.MAX_VALUE)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(lblEstadoSaludDato, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(lblGeneroDato, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(lblZonaDato, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(lblRazaDato, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(18, 18, 18)
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                        .addComponent(btnVerQR, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(btnEmitirCertificado, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 214, Short.MAX_VALUE)
                                        .addComponent(btnCambiarEstadoSalud, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(btnVerHistorialClinico, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(btnVerMapa, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                                .addComponent(lblNombreDato, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))))
                .addContainerGap(39, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(btnVerMapa)
                        .addGap(18, 18, 18)
                        .addComponent(btnVerQR)
                        .addGap(18, 18, 18)
                        .addComponent(btnEmitirCertificado)
                        .addGap(18, 18, 18)
                        .addComponent(btnCambiarEstadoSalud)
                        .addGap(18, 18, 18)
                        .addComponent(btnVerHistorialClinico)))
                .addGap(18, 18, 18)
                .addComponent(lblNombreDato, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(lblRazaDato)
                .addGap(18, 18, 18)
                .addComponent(lblZonaDato)
                .addGap(18, 18, 18)
                .addComponent(lblGeneroDato)
                .addGap(18, 18, 18)
                .addComponent(lblEstadoSaludDato)
                .addGap(18, 18, 18)
                .addComponent(lblObservacionesDato, javax.swing.GroupLayout.DEFAULT_SIZE, 56, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnCerrar)
                .addGap(10, 10, 10))
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
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnCerrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCerrarActionPerformed
        this.vistaAnterior.setVisible(true); // Muestra VistaVeterinario
        this.dispose(); // Cierra esta vista
    }//GEN-LAST:event_btnCerrarActionPerformed

    private void btnVerMapaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVerMapaActionPerformed
        Zona zona = gato.getZona();
        
        if (zona == null || zona.getUbicacionGPS() == null || zona.getUbicacionGPS().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Este gato no tiene una ubicación GPS registrada.", "Sin Ubicación", JOptionPane.INFORMATION_MESSAGE);
            return;
        }

        try {
            String[] coords = zona.getUbicacionGPS().split(",");
            double lat = Double.parseDouble(coords[0]);
            double lon = Double.parseDouble(coords[1]);

            // 1. Crear el JXMapViewer
            JXMapViewer mapa = new JXMapViewer();
            mapa.setPreferredSize(new Dimension(500, 400)); // Tamaño del pop-up

            // 2. Configurar el "TileFactory" (de dónde saca las imágenes del mapa)
            TileFactoryInfo info = new OSMTileFactoryInfo();
            DefaultTileFactory tileFactory = new DefaultTileFactory(info);
            mapa.setTileFactory(tileFactory);

            // 3. Crear la posición
            GeoPosition geoPos = new GeoPosition(lat, lon);

            // 4. Crear el marcador (Waypoint)
            Set<Waypoint> waypoints = new HashSet<>();
            waypoints.add(new DefaultWaypoint(geoPos));

            // 5. Crear el "pintor" de marcadores
            WaypointPainter<Waypoint> waypointPainter = new WaypointPainter<>();
            waypointPainter.setWaypoints(waypoints);
            mapa.setOverlayPainter(waypointPainter);

            // 6. Centrar el mapa y hacer zoom
            mapa.setZoom(7); // Zoom alejado (ej. 7). Un zoom más cercano sería 15.
            mapa.setCenterPosition(geoPos);

            // 7. Mostrar en JOptionPane
            JOptionPane.showMessageDialog(this, mapa, "Ubicación de: " + gato.getNombre(), JOptionPane.PLAIN_MESSAGE);

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error al procesar las coordenadas GPS: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace(); // Muestra el error en consola
        }
    }//GEN-LAST:event_btnVerMapaActionPerformed

    private void btnVerQRActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVerQRActionPerformed
        // 1. Formatear los datos que irán en el QR
        String datosQR = new StringBuilder()
            .append("== Amven Gato ID: ").append(gato.getIdGato()).append(" ==\n")
            .append("Nombre: ").append(gato.getNombre()).append("\n")
            .append("Raza: ").append(gato.getRaza()).append("\n")
            .append("Sexo: ").append(gato.getSexo()).append("\n")
            .append("Esterilizado: ").append(gato.getEsterilizado().name())
            .toString();

        // 2. Generar y mostrar el QR
        try {
            QRCodeWriter qrWriter = new QRCodeWriter();
            BitMatrix bitMatrix = qrWriter.encode(datosQR, BarcodeFormat.QR_CODE, 300, 300);
            BufferedImage qrImage = MatrixToImageWriter.toBufferedImage(bitMatrix);
            
            JDialog qrDialog = new JDialog(this, "QR para: " + gato.getNombre(), true);
            qrDialog.add(new JLabel(new ImageIcon(qrImage)));
            qrDialog.pack();
            qrDialog.setLocationRelativeTo(this);
            qrDialog.setVisible(true);
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error al crear la imagen QR.", "Error Zxing", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnVerQRActionPerformed

    private void btnEmitirCertificadoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEmitirCertificadoActionPerformed
       try {
            // Llama al placeholder en la controladora
            control.emitirCertificadoAptitud(this.gato.getIdGato(), this.veterinario.getIdUsuario());
            
            JOptionPane.showMessageDialog(this, "Certificado emitido con éxito (placeholder).", "Éxito", JOptionPane.INFORMATION_MESSAGE);
            
        } catch (OperacionException e) {
            JOptionPane.showMessageDialog(this, e.getMessage(), "Función No Implementada", JOptionPane.WARNING_MESSAGE);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error inesperado: " + e.getMessage(), "Error Crítico", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnEmitirCertificadoActionPerformed

    private void btnCambiarEstadoSaludActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCambiarEstadoSaludActionPerformed
        // 1. Obtener los posibles estados del Enum
        Gato.EstadoSalud[] opciones = Gato.EstadoSalud.values(); //
        
        // 2. Mostrar el JOptionPane para seleccionar
        Gato.EstadoSalud nuevoEstado = (Gato.EstadoSalud) JOptionPane.showInputDialog(
            this,
            "Seleccione el nuevo estado de salud para " + gato.getNombre() + ":",
            "Cambiar Estado de Salud",
            JOptionPane.QUESTION_MESSAGE,
            null, // sin icono
            opciones, // array de opciones
            gato.getestadoFisico() // opción seleccionada por defecto
        );

        // 3. Procesar la selección
        if (nuevoEstado != null && nuevoEstado != gato.getestadoFisico()) {
            try {
                // 4. Llamar a la controladora
                control.modificarEstadoSaludGato(gato.getIdGato(), nuevoEstado);
                
                // 5. Refrescar los datos en esta vista
                this.gato = control.buscarGatoCompleto((int)gato.getIdGato());
                poblarDatos(); // Recarga los JLabels
                
                JOptionPane.showMessageDialog(this, "Estado de salud actualizado a: " + nuevoEstado.toString(), "Éxito", JOptionPane.INFORMATION_MESSAGE);

            } catch (OperacionException e) {
                JOptionPane.showMessageDialog(this, e.getMessage(), "Error de Operación", JOptionPane.ERROR_MESSAGE);
            }
        }
    }//GEN-LAST:event_btnCambiarEstadoSaludActionPerformed

    private void btnVerHistorialClinicoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVerHistorialClinicoActionPerformed
        try {
            HistoriaClinica hc = gato.getHistoriaClinica();
            if (hc == null) {
                throw new OperacionException("Este gato no tiene una historia clínica asociada.");
            }
            
            // 🟢 CORRECCIÓN: Pasamos el 'gato' completo (que contiene la HC)
            // en lugar de solo 'hc'.
            VistaHistoriaClinica vistaHC = new VistaHistoriaClinica(control, gato, this.veterinario, this);
            
            vistaHC.setVisible(true);
            vistaHC.setLocationRelativeTo(this); // Se centra sobre esta ventana

        } catch (OperacionException e) {
            JOptionPane.showMessageDialog(this, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnVerHistorialClinicoActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCambiarEstadoSalud;
    private javax.swing.JButton btnCerrar;
    private javax.swing.JButton btnEmitirCertificado;
    private javax.swing.JButton btnVerHistorialClinico;
    private javax.swing.JButton btnVerMapa;
    private javax.swing.JButton btnVerQR;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JLabel lblEstadoSaludDato;
    private javax.swing.JLabel lblGeneroDato;
    private javax.swing.JLabel lblNombreDato;
    private javax.swing.JLabel lblObservacionesDato;
    private javax.swing.JLabel lblRazaDato;
    private javax.swing.JLabel lblZonaDato;
    // End of variables declaration//GEN-END:variables
}
