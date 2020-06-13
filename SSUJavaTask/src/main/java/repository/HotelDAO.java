package repository;

import entity.AgentEntity;
import entity.CityEntity;
import entity.HotelEntity;
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

public class HotelDAO {
    private SessionFactory sessionFactory;
    public HotelDAO(){
        this.sessionFactory = HibernateUtil.getSessionFactory();
    }

    public HotelEntity create(HotelEntity hotel) {
        Session session= sessionFactory.openSession();
        Transaction trans=session.beginTransaction();
        session.save(hotel);
        trans.commit();
        session.close();
        return hotel;
    }

    public HotelEntity findById(int id) {
        List<HotelEntity> hotels = findAll();
        return hotels.stream().filter(hotel -> hotel.getId() == id).collect(Collectors.toList()).get(0);
    }

    public void delete(int id) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        HotelEntity hotel = session.get(HotelEntity.class, id);
        session.delete(hotel);
        transaction.commit();
    }

    public List<HotelEntity> findAll() {
        List<HotelEntity> hotel = null;

        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<HotelEntity> query = builder.createQuery(HotelEntity.class);
            Root<HotelEntity> root = query.from(HotelEntity.class);
            query.select(root);
            Query<HotelEntity> q = session.createQuery(query);
            hotel = q.getResultList();
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return hotel;
    }

    public List<HotelEntity> findByName(String name) {
        List<HotelEntity> hotels = findAll();
        return hotels.stream().filter(hotel -> hotel.getName().equals(name)).collect(Collectors.toList());
    }
}
