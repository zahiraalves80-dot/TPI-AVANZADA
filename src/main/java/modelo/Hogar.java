
package modelo;
import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.OneToOne;


 


@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class Hogar implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    // Atributos según el Diagrama de Clases
    private int idHogar; // IdHogar: Int
    private String direccion; // Direccion: String

    public Hogar() {}
    
    public Hogar(int idHogar, String direccion) {
        this.direccion = direccion;
    }


     

  public abstract String getTipoHogar();

    // === Getters y Setters ===
    public int getIdHogar() { return idHogar; }
    public String getDireccion() { return direccion; }
    // [Otros getters y setters]
}
