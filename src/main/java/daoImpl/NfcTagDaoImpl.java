package daoImpl;

import dao.NfcTagDao;
import dataBaseSessionFactory.HibernateUtils;
import entity.NfcTag;
import entity.Run;
import org.hibernate.Session;

import javax.persistence.Query;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class NfcTagDaoImpl implements NfcTagDao {

    @Override
    public void save(NfcTag nfcTag) throws SQLException {
        Session session = HibernateUtils
                .getInstance()
                .getSessionFactory()
                .getCurrentSession();


        session.beginTransaction();
        session.saveOrUpdate(nfcTag);
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public List<NfcTag> findAll() throws SQLException {
        Session session = HibernateUtils
                .getInstance()
                .getSessionFactory()
                .getCurrentSession();
        session.beginTransaction();

        Query q = session.createQuery("from NfcTag");
        List<NfcTag> tags = q.getResultList();

        session.getTransaction().commit();
        session.close();

        return tags;
    }

    @Override
    public NfcTag findById(Long id) throws SQLException {
        Session session = HibernateUtils
                .getInstance()
                .getSessionFactory()
                .getCurrentSession();

        session.beginTransaction();

        Optional<NfcTag> tag = session
                .createQuery("from NfcTag where id= :id")
                .setParameter("id", id)
                .uniqueResultOptional();

        session.getTransaction().commit();
        session.close();

        return tag.orElse(null);
    }

    @Override
    public void update(Long id) throws SQLException {

        Session session = HibernateUtils
                .getInstance()
                .getSessionFactory()
                .getCurrentSession();
        session.beginTransaction();

        Query q = session.createQuery("update NfcTag set serialNumber =:newName where id= :id")
                .setParameter("newName", "Nowy serial")
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
                .getCurrentSession();
        session.beginTransaction();

        Run run = (Run) session
                .createQuery("from NfcTag where id= :id")
                .setParameter("id", id)
                .getSingleResult();

        session.delete(run);
        session.getTransaction().commit();
        session.close();

    }

    @Override
    public List<NfcTag> findByNameFragment(String fragment) throws SQLException {
        Session session = HibernateUtils
                .getInstance()
                .getSessionFactory()
                .getCurrentSession();
        session.beginTransaction();

        List<NfcTag> tags = session
                .createQuery("from NfcTag where name LIKE :fragment")
                .setParameter("fragment", "%" + fragment + "%")
                .getResultList();

        session.getTransaction().commit();
        session.close();

        return tags;


    }
}
