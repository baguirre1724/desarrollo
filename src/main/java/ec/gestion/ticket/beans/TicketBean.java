package ec.gestion.ticket.beans;

import ec.gestion.ticket.servicio.TicketServicio;
import ec.gestion.ticket.utils.Utilitario;
import sun.security.krb5.internal.Ticket;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by baguirre on 19/10/2020.
 */

@ManagedBean
@ViewScoped
public class TicketBean extends Utilitario implements Serializable {

    @EJB
    private TicketServicio ticketServicio;


    private static final long serialVersionUID = 1L;


    private Ticket nuevoTicket;
    private Ticket eliminarTicket;
    private boolean editarTicket;

    private List<String> listaTicketFiltrado;

    @PostConstruct
    public void iniciar() throws Exception{
        descripcion = null;
        estado = null;
        prioridad = null;
        usuarioRegistro = null;
        fecha = null;

        this.setEditarTicket (Boolean.FALSE);

    }



    public void editarTicket(){
        try {

            listaTicketFiltrado = ticketServicio.buscarCodigoTicketXcodigo(codTicket);

            if (listaTicketFiltrado != null) {
                this.ponerMensajeInfo(getBundle("no.existe"), "");
            }else{
                nuevoTicket.setDescripcion(descripcion);
                nuevoTicket.setEstado(estado);
                nuevoTicket.setCodCanton(prioridad);
                nuevoTicket.setCodarea(usuarioRegistro);
                nuevoTicket.setFecha(fecha);

                ticketServicio.editar(nuevoTicket);

                this.ponerMensajeInfo(getBundle("exito"), "");
                iniciar();


            }

        } catch (Exception e) {
            this.ponerMensajeError(getBundle("ec.gestion.ticket.editar.error"), "");
            e.printStackTrace();
        }




    }

    public void guardarTicket(){

    }


    public void eliminarTicket(){

    }


}
