
package modelo;

import javax.persistence.Entity;





@Entity
public class HogarTransito extends Hogar {
   
    
    public HogarTransito() {
        super();
    }

    public HogarTransito(int idHogar, String direccion) {
        super(idHogar, direccion);
    }

    @Override
    public String getTipoHogar() {
        return "Tránsito";
    }
}
