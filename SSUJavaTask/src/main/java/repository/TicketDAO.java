package repository;

import entity.AgentEntity;
import entity.SightEntity;
import entity.TicketEntity;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import util.HibernateUtil;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;
import java.util.stream.Collectors;

public class TicketDAO {
    private SessionFactory sessionFactory;
    public TicketDAO(){
        this.sessionFactory = HibernateUtil.getSessionFactory();
    }
    public TicketEntity create(TicketEntity ticket) {
        Session session= sessionFactory.openSession();
        Transaction trans=session.beginTransaction();
        session.save(ticket);
        trans.commit();
        session.close();
        return ticket;
    }

    public TicketEntity findById(int id) {
        List<TicketEntity> tickets = findAll();
        return tickets.stream().filter(ticket -> ticket.getId() == id).collect(Collectors.toList()).get(0);
    }

    public void delete(int id) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        TicketEntity ticket = session.get(TicketEntity.class, id);
        session.delete(ticket);
        transaction.commit();
    }

    public List<TicketEntity> findAll() {
        List<TicketEntity> ticket = null;
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<TicketEntity> query = builder.createQuery(TicketEntity.class);
            Root<TicketEntity> root = query.from(TicketEntity.class);
            query.select(root);
            Query<TicketEntity> q = session.createQuery(query);
            ticket = q.getResultList();
            transaction.commit();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return ticket;
    }

    public List<TicketEntity> findByName(int price) {
        List<TicketEntity> tickets = findAll();
        return tickets.stream().filter(ticket -> ticket.getPrice() == price).collect(Collectors.toList());
    }
}
