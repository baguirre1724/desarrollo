package ec.gestion.ticket.dao;

import ec.gestion.ticket.modelo.Ticket;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by baguirre on 19/10/2020.
 */


@Stateless
public class TicketDAO extends Generico<Ticket> {

    @PersistenceContext(unitName = "gestion-ticket")
    private EntityManager em;

    public TicketDAO()  {super(Ticket.class); }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public List<String> buscarCodigoTicketXcodigo(String codigoTicket, Integer codTicket) throws Exception {
        List<String> result = new ArrayList<>();
        String jpql = "SELECT n.codTicket FROM Ticket n   "
                + "AND n.codTicket =:codTicket AND n.codTicket!=:codTicket";
        Query query = em.createQuery(jpql);
        query.setParameter("codigoTicket", codigoTicket);
        query.setParameter("codTicket", codTicket);
        result = query.getResultList();
        if (result != null && !result.isEmpty()) {
            return result;
        } else {
            return null;
        }
    }

}
