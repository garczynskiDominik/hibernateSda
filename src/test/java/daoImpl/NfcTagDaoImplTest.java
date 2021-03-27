package daoImpl;

import dataBaseSessionFactory.HibernateUtils;
import entity.NfcTag;
import entity.Run;
import org.hibernate.Session;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.persistence.Query;
import java.sql.SQLException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class NfcTagDaoImplTest {
    private NfcTagDaoImpl nfcTagDao = new NfcTagDaoImpl();

    @BeforeEach
    void clearTable() throws SQLException {

        Session session = HibernateUtils
                .getInstance()
                .getSessionFactory()
                .openSession();
        session.beginTransaction();

        Query q = session.createQuery("delete NfcTag");

        int a = q.executeUpdate();
        session.getTransaction().commit();
        session.close();
    }

    @Test
    void save() {

        NfcTag nfcTag = new NfcTag("12321");
        try {

            nfcTagDao.save(nfcTag);
            NfcTag saved = nfcTagDao.findById(nfcTag.getId());

            assertNotNull(saved);
            assertEquals(nfcTag.getId(), saved.getId());
            assertEquals(nfcTag.getSerialNumber(), saved.getSerialNumber());
            assertEquals(nfcTag.getId(), saved.getId());

        } catch (SQLException throwables) {
            fail(throwables);
            throwables.printStackTrace();
        }
    }

    @Test
    void findAll() {
        try {
            NfcTag nfcTag1 = new NfcTag("123");
            NfcTag nfcTag2 = new NfcTag("456");

            nfcTagDao.save(nfcTag1);
            nfcTagDao.save(nfcTag2);

            List<NfcTag> tags = nfcTagDao.findAll();
            NfcTag test = null;
            for (NfcTag tag : tags) {
                if (tag.getId().equals(nfcTag1)) {
                    test = nfcTag1;
                }
            }
            assertNotNull(tags);
            assertEquals(2, tags.size());
            assertEquals(nfcTag1.getId(), test.getId());

        } catch (SQLException e) {
            fail(e);
        }
    }

    @Test
    void findById() {
    }

    @Test
    void update() {
    }

    @Test
    void deleteById() {
    }

    @Test
    void findByNameFragment() {
    }
}