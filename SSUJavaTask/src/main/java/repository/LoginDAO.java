package repository;

import entity.LoginEntity;
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

public class LoginDAO {
    private SessionFactory sessionFactory;
    public LoginDAO(){
      this.sessionFactory = HibernateUtil.getSessionFactory();
    }

    public List<LoginEntity> findAll() {
        List<LoginEntity> loginsPasswords = null;
        try  {
            Session session = sessionFactory.openSession();
            Transaction transaction = session.beginTransaction();
            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<LoginEntity> query = builder.createQuery(LoginEntity.class);
            Root<LoginEntity> root = query.from(LoginEntity.class);
            query.select(root);
            Query<LoginEntity> q = session.createQuery(query);
            loginsPasswords = q.getResultList();
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return loginsPasswords;
    }
    public LoginEntity create(LoginEntity loginPassword) {
        Session session= sessionFactory.openSession();
        Transaction trans=session.beginTransaction();
        session.save(loginPassword);
        trans.commit();
        session.close();
        return loginPassword;
    }
}
