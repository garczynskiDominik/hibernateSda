package daoImpl;

import dao.RunDao;
import dataBaseSessionFactory.HibernateUtils;
import entity.Run;
import entity.RunMember;
import org.hibernate.Hibernate;
import org.hibernate.Session;

import javax.persistence.NoResultException;
import javax.persistence.Query;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class RunDaoImpl implements RunDao {
    @Override
    public void save(Run run) throws SQLException {
        Session session = HibernateUtils
                .getInstance()
                .getSessionFactory()
                .openSession();


        session.beginTransaction();
        session.saveOrUpdate(run);
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public List<Run> findAll() throws SQLException {

        Session session = HibernateUtils
                .getInstance()
                .getSessionFactory()
                .openSession();
        session.beginTransaction();

        Query q = session.createQuery("from Run");
        List<Run> runs = q.getResultList();
//        List<Run> runs = session
//        .createQuery("from Run", Run.class)
//        .getResultList();
        session.getTransaction().commit();
        session.close();

        return runs;
    }

    @Override
    public Run findById(Long id) throws SQLException {
        Session session = HibernateUtils
                .getInstance()
                .getSessionFactory()
                .getCurrentSession();

        session.beginTransaction();

        Optional<Run> run = session
                .createQuery("from Run where id= :id")
                .setParameter("id", id)
                .uniqueResultOptional();

        session.getTransaction().commit();
        session.close();

        return run.orElse(null);
    }

    @Override
    public void update(Long id) throws SQLException {
        Session session = HibernateUtils
                .getInstance()
                .getSessionFactory()
                .openSession();
        session.beginTransaction();

        Query q = session.createQuery("update Run set name=:newName where id= :id")
                .setParameter("newName", "Dominik")
                .setParameter("id", id);

        int a = q.executeUpdate();

        session.getTransaction().commit();
        session.close();

    }

    @Override
    public void deleteById(Long id) throws SQLException {


        Session session = HibernateUtils
                .getInstance()
                .getSessionFactory()
                .openSession();
        session.beginTransaction();

        Run run = (Run) session
                .createQuery("from Run where id= :id")
                .setParameter("id", id)
                .getSingleResult();

        session.delete(run);
        session.getTransaction().commit();
        session.close();

    }

    @Override
    public List<Run> findByNameFragment(String fragment) throws SQLException {
        Session session = HibernateUtils
                .getInstance()
                .getSessionFactory()
                .openSession();
        session.beginTransaction();

        List<Run> runs = session
                .createQuery("from Run where name LIKE :fragment")
                .setParameter("fragment", "%" + fragment + "%")
                .getResultList();

        session.getTransaction().commit();
        session.close();

        return runs;
    }

    @Override
    public List<Run> findByMembersLimitRange(Integer min, Integer max) throws SQLException {
        Session session = HibernateUtils
                .getInstance()
                .getSessionFactory()
                .openSession();
        session.beginTransaction();

        List<Run> runs = session
                .createQuery("from Run where membersLimit BETWEEN :min AND :max")
                .setParameter("min", min)
                .setParameter("max", max)
                .getResultList();

        session.getTransaction().commit();
        session.close();

        return runs;
    }

    public static List join() {
        Session session = HibernateUtils
                .getInstance()
                .getSessionFactory()
                .getCurrentSession();

        session.beginTransaction();
        String select = "FROM entity.Run r INNER JOIN Run_Member rm ON r.id_run=rm.run_id";
        Query query = session.createQuery(select);
        List elist = query.getResultList();

        session.getTransaction().commit();
        session.close();
        return elist;
    }
}
