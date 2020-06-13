package repository;

import entity.AgentEntity;
import entity.RouteEntity;
import entity.SightEntity;
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

public class SightDAO {
    private SessionFactory sessionFactory;
    public SightDAO(){
        this.sessionFactory = HibernateUtil.getSessionFactory();
    }
    public SightEntity create(SightEntity sight) {
        Session session= sessionFactory.openSession();
        Transaction trans=session.beginTransaction();
        session.save(sight);
        trans.commit();
        session.close();
        return sight;
    }

    public SightEntity findById(int id) {
        List<SightEntity> sightes = findAll();
        return sightes.stream().filter(sight -> sight.getId() == id).collect(Collectors.toList()).get(0);
    }

    public void delete(int id) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        SightEntity sight = session.get(SightEntity.class, id);
        session.delete(sight);
        transaction.commit();
    }

    public List<SightEntity> findAll() {
        List<SightEntity> sight = null;
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<SightEntity> query = builder.createQuery(SightEntity.class);
            Root<SightEntity> root = query.from(SightEntity.class);
            query.select(root);
            Query<SightEntity> q = session.createQuery(query);
            sight = q.getResultList();
            transaction.commit();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return sight;
    }

    public List<SightEntity> findByName(String name) {
        List<SightEntity> sightes = findAll();
        return sightes.stream().filter(sight -> sight.getName().equals(name)).collect(Collectors.toList());
    }
}
