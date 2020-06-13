package repository;

import entity.AgentEntity;
import entity.TicketEntity;
import entity.TouristEntity;
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

public class TouristDAO {
    private SessionFactory sessionFactory;
    public TouristDAO(){
        this.sessionFactory = HibernateUtil.getSessionFactory();
    }
    public TouristEntity create(TouristEntity tourist) {
        Session session= sessionFactory.openSession();
        Transaction trans=session.beginTransaction();
        session.save(tourist);
        trans.commit();
        session.close();
        return tourist;
    }

    public TouristEntity findById(int id) {
        List<TouristEntity> tourists = findAll();
        return tourists.stream().filter(tourist -> tourist.getId() == id).collect(Collectors.toList()).get(0);
    }


    public void delete(int id) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        TouristEntity tourist = session.get(TouristEntity.class, id);
        session.delete(tourist);
        transaction.commit();
    }


    public List<TouristEntity> findAll() {
        List<TouristEntity> tourist = null;
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<TouristEntity> query = builder.createQuery(TouristEntity.class);
            Root<TouristEntity> root = query.from(TouristEntity.class);
            query.select(root);
            Query<TouristEntity> q = session.createQuery(query);
            tourist = q.getResultList();
            transaction.commit();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return tourist;
    }
    public List<TouristEntity> findByName(String name) {
        List<TouristEntity> tourists = findAll();
        return tourists.stream().filter(tourist -> tourist.getName().equals(name)).collect(Collectors.toList());
    }
}
