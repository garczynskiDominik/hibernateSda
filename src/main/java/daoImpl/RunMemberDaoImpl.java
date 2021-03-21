package daoImpl;

import dao.RunMemberDao;
import dataBaseSessionFactory.HibernateUtils;
import entity.RunMember;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import javax.persistence.NoResultException;
import javax.persistence.Query;
import java.lang.reflect.Member;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class RunMemberDaoImpl implements RunMemberDao {
    @Override
    public void save(RunMember member) throws SQLException {
        Session session = HibernateUtils
                .getInstance()
                .getSessionFactory()
                .openSession();

        session.beginTransaction();
        session.save(member);
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public List<RunMember> findAll() throws SQLException {
        Session session = HibernateUtils
                .getInstance()
                .getSessionFactory()
                .openSession();
        session.beginTransaction();

        Query q = session.createQuery("from RunMember");
        List<RunMember> members = q.getResultList();
//        List<Run> runs = session
//        .createQuery("from Run", Run.class)
//        .getResultList();
        session.getTransaction().commit();
        session.close();

        return members;
    }

    @Override
    public RunMember findById(Long id) throws SQLException {

        Session session = HibernateUtils
                .getInstance()
                .getSessionFactory()
                .getCurrentSession();

        session.beginTransaction();

        Optional<RunMember> member=  session
                    .createQuery("from RunMember where id= :id")
                    .setParameter("id", id)
                    .uniqueResultOptional();

        session.getTransaction().commit();
        session.close();

        return member.orElse(null);
    }

    @Override
    public void update(Long id) throws SQLException {
        Session session = HibernateUtils
                .getInstance()
                .getSessionFactory()
                .openSession();
        session.beginTransaction();

        Query q = session.createQuery("update RunMember set name=:newName where id= :id")
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

        RunMember member = (RunMember) session
                .createQuery("from RunMember where id= :id")
                .setParameter("id", id)
                .getSingleResult();

        session.delete(member);
        session.getTransaction().commit();
        session.close();

    }

    @Override
    public List<RunMember> findByNameFragment(String fragment) throws SQLException {

        Session session = HibernateUtils
                .getInstance()
                .getSessionFactory()
                .openSession();
        session.beginTransaction();

        List<RunMember> members = session
                .createQuery("from RunMember where name LIKE :fragment")
                .setParameter("fragment", "%" + fragment + "%")
                .getResultList();

        session.getTransaction().commit();
        session.close();

        return members;
    }

    @Override
    public List<RunMember> findByStartNumberRange(int min, int max) throws SQLException {
        Session session = HibernateUtils
                .getInstance()
                .getSessionFactory()
                .openSession();
        session.beginTransaction();

        List<RunMember> members = session
                .createQuery("from RunMember where startNumber BETWEEN :min AND :max")
                .setParameter("min", min)
                .setParameter("max", max)
                .getResultList();

        session.getTransaction().commit();
        session.close();

        return members;

    }
}
