package repository;

import entity.AgentEntity;
import entity.HotelEntity;
import entity.RouteEntity;
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

public class RouteDAO {
    private SessionFactory sessionFactory;
    public RouteDAO(){
        this.sessionFactory = HibernateUtil.getSessionFactory();
    }

    public RouteEntity create(RouteEntity route) {
        Session session= sessionFactory.openSession();
        Transaction trans=session.beginTransaction();
        session.save(route);
        trans.commit();
        session.close();
        return route;
    }


    public RouteEntity findById(int id) {
        List<RouteEntity> routess = findAll();
        return routess.stream().filter(route -> route.getId() == id).collect(Collectors.toList()).get(0);
    }

    public void delete(int id) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        RouteEntity route = session.get(RouteEntity.class, id);
        session.delete(route);
        transaction.commit();
    }

    public List<RouteEntity> findAll() {
        List<RouteEntity> route = null;
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<RouteEntity> query = builder.createQuery(RouteEntity.class);
            Root<RouteEntity> root = query.from(RouteEntity.class);
            query.select(root);
            Query<RouteEntity> q = session.createQuery(query);
            route = q.getResultList();
            transaction.commit();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return route;
    }

    public List<RouteEntity> findByName(String name) {
        List<RouteEntity> routes = findAll();
        return routes.stream().filter(route -> route.getName().equals(name)).collect(Collectors.toList());
    }
}
