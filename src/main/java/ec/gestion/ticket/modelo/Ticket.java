package ec.gestion.ticket.modelo;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by baguirre on 19/10/2020.
 */

@Entity
@Table(name="ticket")
@NamedQuery(name="Ticket.findAll", query="SELECT t FROM Ticket t")
public class Ticket  implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int codTicket;

    @Column(name="descripcion")
    private String descripcion;

    @Column(name="estado")
    private String descripcion;

    @Column(name="prioridad")
    private int prioridad;

    @Column(name="usuario_registro")
    private String usuarioRegistro;

    @Column(name="fecha")
    private Date fecha;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public int getCodTicket() {
        return codTicket;
    }

    public void setCodTicket(int codTicket) {
        this.codTicket = codTicket;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getPrioridad() {
        return prioridad;
    }

    public void setPrioridad(int prioridad) {
        this.prioridad = prioridad;
    }

    public String getUsuarioRegistro() {
        return usuarioRegistro;
    }

    public void setUsuarioRegistro(String usuarioRegistro) {
        this.usuarioRegistro = usuarioRegistro;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }
}
