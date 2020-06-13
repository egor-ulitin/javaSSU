package repository;

import entity.AgentEntity;
import entity.CityEntity;
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

public class CityDAO {
    private SessionFactory sessionFactory;
    public CityDAO(){
        this.sessionFactory = HibernateUtil.getSessionFactory();
    }
    public CityEntity create(CityEntity city) {
        Session session= sessionFactory.openSession();
        Transaction trans=session.beginTransaction();
        session.save(city);
        trans.commit();
        session.close();
        return city;
    }

    public CityEntity findById(int id) {
        List<CityEntity> cities = findAll();
        return cities.stream().filter(city -> city.getId() == id).collect(Collectors.toList()).get(0);
    }

    public void delete(int id) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        CityEntity city = session.get(CityEntity.class, id);
        session.delete(city);
        transaction.commit();
    }

    public List<CityEntity> findAll() {
        List<CityEntity> city = null;
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<CityEntity> query = builder.createQuery(CityEntity.class);
            Root<CityEntity> root = query.from(CityEntity.class);
            query.select(root);
            Query<CityEntity> q = session.createQuery(query);
            city = q.getResultList();
            transaction.commit();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return city;
    }

    public List<CityEntity> findByName(String name) {
        List<CityEntity> cities = findAll();
        return cities.stream().filter(city -> city.getName().equals(name)).collect(Collectors.toList());
    }
}
