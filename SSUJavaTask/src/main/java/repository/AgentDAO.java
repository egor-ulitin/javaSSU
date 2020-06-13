package repository;
import entity.AgentEntity;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import util.HibernateUtil;
import org.hibernate.SessionFactory;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;
import java.util.stream.Collectors;

public class AgentDAO {
    private SessionFactory sessionFactory;
    public AgentDAO(){
        this.sessionFactory = HibernateUtil.getSessionFactory();
    }

    public AgentEntity create(AgentEntity agent) {
        Session session= sessionFactory.openSession();
        Transaction trans=session.beginTransaction();
        session.save(agent);
        trans.commit();
        session.close();
        return agent;
    }


    public AgentEntity findById(int id) {
        List<AgentEntity> agents = findAll();
        return agents.stream().filter(agent -> agent.getId() == id).collect(Collectors.toList()).get(0);
    }

    public List<AgentEntity> findByName(String name) {
        List<AgentEntity> agents = findAll();
        return agents.stream().filter(agent -> agent.getName().equals(name)).collect(Collectors.toList());
    }

    public void delete(int id) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        AgentEntity agent = session.get(AgentEntity.class, id);
        session.delete(agent);
        transaction.commit();
    }

    public List<AgentEntity> findAll() {
        List<AgentEntity> agents = null;
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<AgentEntity> query = builder.createQuery(AgentEntity.class);
            Root<AgentEntity> root = query.from(AgentEntity.class);
            query.select(root);
            Query<AgentEntity> q = session.createQuery(query);
            agents = q.getResultList();
            transaction.commit();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return agents;
    }
    public void update(AgentEntity agent, int id) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        AgentEntity agentDb = (AgentEntity) session.get(AgentEntity.class, id);
        if (agent.getId() == id) {
            agentDb.setName(agent.getName());
        }
        session.update(agentDb);
        transaction.commit();
    }
}
