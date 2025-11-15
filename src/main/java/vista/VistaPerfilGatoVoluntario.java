
package vista;


import java.awt.Dimension;
import java.awt.image.BufferedImage;
import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.WindowConstants;

// --- Imports del Modelo (Lógica) ---
import controladora.Controladora;
import modelo.FamiliaAdoptante;
import modelo.Gato;
import modelo.OperacionException;
import modelo.Zona;

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


public class VistaPerfilGatoVoluntario extends javax.swing.JFrame {
    
    // --- Variables de Lógica ---
    private final Gato gato;
    private final FamiliaAdoptante familia;
    private final Controladora control;
    private final javax.swing.JLabel lblFoto;
    /**
     * Constructor principal.
     * Recibe los objetos necesarios para mostrar el perfil y operar.
     * @param gato
     * @param familia
     * @param control
     */
    public VistaPerfilGatoVoluntario(Gato gato, FamiliaAdoptante familia, Controladora control) {
        this.gato = gato;
        this.familia = familia;
        this.control = control;
        this.lblFoto = new javax.swing.JLabel();
        initComponents(); // Carga el diseño de la interfaz
        
        jPanel2.setLayout(new java.awt.BorderLayout());
    jPanel2.add(lblFoto, java.awt.BorderLayout.CENTER);
        poblarDatos();    // Rellena la info automáticamente
    }

    /**
     * REQUERIMIENTO 1: Rellena todos los JLabels con la información del Gato.
     */
    private void poblarDatos() {
        // Rellenar los datos del gato
        lblNombreDato.setText(gato.getNombre());
        lblRazaDato.setText(gato.getRaza());
        lblGeneroDato.setText(gato.getSexo());
        lblEstadoSaludDato.setText(gato.getestadoFisico().toString());
        lblObservacionesDato.setText(gato.getCaracteristicas());
        
        // Rellenar la Zona (manejando si es nula)
        if (gato.getZona() != null) {
            lblZonaDato.setText(gato.getZona().getNombreZona());
        } else {
            lblZonaDato.setText("Sin zona asignada");
        }
        
        String ruta = gato.getRutaFoto(); // Usamos el nuevo getter
    if (ruta != null && !ruta.isEmpty()) {
        try {
            // Intentar cargar la imagen desde la ruta.
           
            javax.swing.ImageIcon icon = new javax.swing.ImageIcon(ruta);
            
            // Re-escalar la imagen para que encaje en el panel (ajuste la lógica si es necesario)
            java.awt.Image image = icon.getImage();
            java.awt.Image newImage = image.getScaledInstance(jPanel2.getWidth(), jPanel2.getHeight(), java.awt.Image.SCALE_SMOOTH);
            
            lblFoto.setIcon(new javax.swing.ImageIcon(newImage));
            
        } catch (Exception e) {
            lblFoto.setText("Error al cargar imagen.");
            e.printStackTrace();
        }
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
        btnAsignarGato = new javax.swing.JButton();

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

        btnEmitirCertificado.setText("Gestionar Visitas");
        btnEmitirCertificado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEmitirCertificadoActionPerformed(evt);
            }
        });

        lblObservacionesDato.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        lblObservacionesDato.setText("OBSERVACIONES");

        btnAsignarGato.setText("Asignar Gato a Familia");
        btnAsignarGato.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAsignarGatoActionPerformed(evt);
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
                                    .addComponent(btnVerMapa, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(btnAsignarGato, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 214, Short.MAX_VALUE)))
                            .addComponent(lblNombreDato, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap(39, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(btnVerMapa)
                        .addGap(18, 18, 18)
                        .addComponent(btnVerQR)
                        .addGap(18, 18, 18)
                        .addComponent(btnEmitirCertificado)
                        .addGap(18, 18, 18)
                        .addComponent(btnAsignarGato)
                        .addGap(71, 71, 71)))
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
        // TODO add your handling code here:
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
            // 1. Obtener los IDs (ya los tenemos en las variables de clase)
            int idGato = (int) this.gato.getIdGato();
            int idFamilia = this.familia.getIdUsuario();
            
            // 2. Llamar a la Controladora (Capa de Negocio)
            control.crearPostulacion(idGato, idFamilia);
            
            // 3. Éxito
            JOptionPane.showMessageDialog(this, "¡Postulación enviada con éxito!", "Postulación Registrada", JOptionPane.INFORMATION_MESSAGE);
            
            // 4. Cerrar la ventana
            this.dispose();
            
        } catch (OperacionException e) {
            // Atrapa errores de negocio (ej. "Ya te postulaste a este gato")
            JOptionPane.showMessageDialog(this, e.getMessage(), "Error en la Operación", JOptionPane.ERROR_MESSAGE);
        } catch (Exception e) {
            // Atrapa cualquier otro error
            JOptionPane.showMessageDialog(this, "Error inesperado: " + e.getMessage(), "Error Crítico", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnEmitirCertificadoActionPerformed

    private void btnAsignarGatoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAsignarGatoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnAsignarGatoActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAsignarGato;
    private javax.swing.JButton btnCerrar;
    private javax.swing.JButton btnEmitirCertificado;
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
