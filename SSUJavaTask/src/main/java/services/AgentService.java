package services;

import entity.AgentEntity;
import repository.AgentDAO;

import java.util.Date;
import java.util.List;

public class AgentService {
    private AgentDAO agentDAO;
    private TicketService ticketService;

    public AgentService() {
        this.agentDAO = new AgentDAO();
    }

    public boolean create(String name, String phoneNumber, Date birthday) {
       try {
           AgentEntity agent = new AgentEntity();
           if (name.length() < 20 && name.length() > 3) {
               agent.setName(name);
               if (phoneNumber.length() == 10) {
                   agent.setPhoneNumber(phoneNumber);
                   agentDAO.create(agent);
                   return true;
               }
           }
           return false;
       }
        catch (Exception e) {
            return false;
        }
    }
    public void update(String name, int id) {
        AgentEntity agentEntity = agentDAO.findById(id);
        agentEntity.setName(name);
        agentDAO.update(agentEntity, agentEntity.getId());
    }

    public void delete(int id) {
        agentDAO.delete(id);
    }

    public List<AgentEntity> findAll() {
        return agentDAO.findAll();
    }

    public AgentEntity findById(int id) {
        return agentDAO.findById(id);
    }
    public List<AgentEntity> findByName(String name){
        return agentDAO.findByName(name);
    }
}
