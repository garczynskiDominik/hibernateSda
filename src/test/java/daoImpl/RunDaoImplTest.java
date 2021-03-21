package daoImpl;

import dataBaseSessionFactory.HibernateUtils;
import entity.Run;
import org.hibernate.Session;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.persistence.Query;

import java.sql.SQLException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class RunDaoImplTest {

    private RunDaoImpl runDao = new RunDaoImpl();

    @BeforeEach
    void clearTable() throws SQLException {

        Session session = HibernateUtils
                .getInstance()
                .getSessionFactory()
                .openSession();
        session.beginTransaction();

        Query q = session.createQuery("delete Run");

        int a = q.executeUpdate();
        session.getTransaction().commit();
        session.close();
    }

    @Test
    void save() {
        Run run = new Run("Testowy", 100, 5);
        try {
            runDao.save(run);

            Run saved = runDao.findById(run.getId());

            assertNotNull(saved);
            assertEquals(run.getId(), saved.getId());
            assertEquals(run.getName(), saved.getName());
            assertEquals(run.getMembersLimit(), saved.getMembersLimit());
        } catch (SQLException throwables) {
            fail(throwables);
            throwables.printStackTrace();
        }
    }

    @Test
    void findAll() {
        try {
            Run run1 = new Run("Testowy", 99, 5);
            Run run2 = new Run("Testowy", 20, 10);

            runDao.save(run1);
            runDao.save(run2);

            List<Run> runList = runDao.findAll();
            Run testRun1 = null;
            for (Run run : runList) {
                if (run.getId() == run1.getId()) {
                    testRun1 = run1;
                }
            }

            assertNotNull(runList);
            assertEquals(2, runList.size());
            assertEquals(run1.getId(), testRun1.getId());

        } catch (SQLException e) {
            fail(e);
        }
    }

    @Test
    void update() {

        Run run = new Run("Testowy", 50, 15);
        try {
            runDao.save(run);
            run.setMembersLimit(20);
            run.setName("Inna nazwa");
            runDao.save(run);
//           runDao.update(run.getId());

            Run update = runDao.findById(run.getId());
            assertNotNull(update);
            assertEquals(run.getMembersLimit(), update.getMembersLimit());
            assertEquals(run.getName(), update.getName());

        } catch (SQLException throwables) {
            fail(throwables);
        }
    }

    @Test
    void deleteById() {
        Run run = new Run("Testowy", 100, 5);
        try {
            runDao.save(run);
            runDao.deleteById(run.getId());
            Run deleted = runDao.findById(run.getId());

            assertNull(deleted);

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Test
    void findByNameFragment() {

        Run run1 = new Run("Tomek", 50, 5);
        Run run2 = new Run("Marek", 40, 10);
        Run run3 = new Run("Roman", 30, 15);
        try {
            runDao.save(run1);
            runDao.save(run2);
            runDao.save(run3);

            List<Run> result = runDao.findByNameFragment("om");
            List<Run> result1 = runDao.findByNameFragment("Marek");
            List<Run> result2 = runDao.findByNameFragment("dsdsds");

            assertNotNull(result);
            assertEquals(2, result.size());
            assertEquals(run1.getId(), result.get(0).getId());
            assertEquals(run1.getName(), result.get(0).getName());
            assertEquals(run1.getMembersLimit(), result.get(0).getMembersLimit());
            assertEquals(run1.getDistance(), result.get(0).getDistance());
            assertEquals(1, result1.size());
            assertEquals(0, result2.size());

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }


    @Test
    void findByMembersLimitRange() {

        try {
            Run run1 = new Run("Tomek", 50, 5);
            Run run2 = new Run("Marek", 100, 10);
            Run run3 = new Run("Roman", 150, 15);
            runDao.save(run1);
            runDao.save(run2);
            runDao.save(run3);

            List<Run> result = runDao.findByMembersLimitRange(70, 155);
            List<Run> result1 = runDao.findByMembersLimitRange(60, 64);
            List<Run> result2 = runDao.findByMembersLimitRange(80, 120);

            assertNotNull(result);
            assertEquals(2, result.size());
            assertEquals(0, result1.size());
            assertEquals(1, result2.size());
            assertEquals(run2.getId(), result2.get(0).getId());
            assertEquals(run2.getName(), result2.get(0).getName());
            assertEquals(run2.getMembersLimit(), result2.get(0).getMembersLimit());
            assertEquals(run2.getDistance(), result2.get(0).getDistance());

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}