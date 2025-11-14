package main;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import vista.IniciarSesion;

public class TPI {

    public static void main(String[] args) {
        try {
            // 🔹 Esto crea todas las tablas automáticamente según tus entidades
            System.out.println("Creando tablas en la base de datos...");
            EntityManagerFactory emf = Persistence.createEntityManagerFactory("tpiPU");
            emf.close();
            System.out.println("✅ Tablas creadas correctamente.");

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("❌ Error al intentar crear las tablas: " + e.getMessage());
        }

        // 🔹 Luego abrís tu ventana normalmente
        IniciarSesion ini = new IniciarSesion();
        ini.setVisible(true);
        ini.setLocationRelativeTo(null);
    }
}

