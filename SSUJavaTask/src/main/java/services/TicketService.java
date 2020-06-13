package services;

import entity.*;
import repository.TicketDAO;

import java.util.Date;
import java.util.List;

public class TicketService {
    private TicketDAO ticketDAO;

    public TicketService() {
        this.ticketDAO = new TicketDAO();
    }

    public void delete(int id) {
        ticketDAO.delete(id);
    }

    public List<TicketEntity> findAll() {
        return ticketDAO.findAll();
    }

    public TicketEntity findById(int id) {
        return ticketDAO.findById(id);
    }

    public boolean create(int idAgent, int idRoute, int idTourist, int price, Date startTravel, Date finishTravel) {
        try {
            TicketEntity ticket = new TicketEntity();
            RouteService routeService = new RouteService();
            AgentService agentService = new AgentService();
            TouristService touristService = new TouristService();
            ticket.setRoute(routeService.findById(idRoute));
            ticket.setTourist(touristService.findById(idTourist));
            ticket.setAgent(agentService.findById(idAgent));
            ticket.setStartTravelDate(startTravel);
            ticket.setFinishTravelDate(finishTravel);
            ticket.setPrice(price);
            ticketDAO.create(ticket);
            return true;
        } catch (Exception e) {

            return false;
        }
    }
}
