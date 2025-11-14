
package modelo;
import javax.persistence.Entity;





@Entity
public class HogarDefinitivo extends Hogar {

    public HogarDefinitivo() {
        super();
    }

    public HogarDefinitivo(int idHogar, String direccion, FamiliaAdoptante familia) {
        super(idHogar, direccion);
    }

    @Override
    public String getTipoHogar() {
        return "Adopción Definitiva";
    }

    
}


