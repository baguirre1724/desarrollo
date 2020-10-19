package ec.gestion.ticket.servicio;

import ec.gestion.ticket.dao.TicketDAO;

import javax.ejb.EJB;
import java.util.List;

/**
 * Created by baguirre on 19/10/2020.
 */
public class TicketServicio {

    @EJB
    private TicketDAO ticketDAO;


    public List<String> buscarCodigoTicketXcodigo(String codigoTicket, Integer codTicket) throws Exception {
        return ticketDAO.buscarCodigoTicketXcodigo(codigoTicket, codTicket);
    }
}
