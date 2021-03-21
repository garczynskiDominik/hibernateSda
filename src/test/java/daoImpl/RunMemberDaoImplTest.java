package daoImpl;

import dataBaseSessionFactory.HibernateUtils;
import entity.Run;
import entity.RunMember;
import org.hibernate.Session;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.persistence.Query;
import java.lang.reflect.Member;
import java.sql.SQLException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;


class RunMemberDaoImplTest {

    private RunMemberDaoImpl runDao = new RunMemberDaoImpl();

    @BeforeEach
    void clearTable() throws SQLException {

        Session session = HibernateUtils
                .getInstance()
                .getSessionFactory()
                .openSession();
        session.beginTransaction();

        Query q = session.createQuery("delete RunMember");

        int a = q.executeUpdate();
        session.getTransaction().commit();
        session.close();
    }

    @Test
    void save() {

        RunMember runMember = new RunMember("Dominik", 1, 1);
        try {
            runDao.save(runMember);

            RunMember saved = runDao.findById(runMember.getId());

            assertNotNull(saved);
            assertEquals(runMember.getId(), saved.getId());
            assertEquals(runMember.getName(), saved.getName());
            assertEquals(runMember.getStartNumber(), saved.getStartNumber());
            assertEquals(runMember.getRunId(), saved.getRunId());
        } catch (SQLException throwables) {
            fail(throwables);
            throwables.printStackTrace();
        }
    }

    @Test
    void findAll() {
        try {

            RunMember runMember1 = new RunMember("Dominik", 99, 5);
            RunMember runMember2 = new RunMember("Tomek", 20, 10);

            runDao.save(runMember1);
            runDao.save(runMember2);

            List<RunMember> runList = runDao.findAll();
            RunMember testRun1 = null;
            for (RunMember runMember : runList) {
                if (runMember.getId().equals(runMember1.getId())) {
                    testRun1 = runMember1;
                }
            }

            assertNotNull(runList);
            assertEquals(2, runList.size());
            assertEquals(runMember1.getId(), testRun1.getId());

        } catch (SQLException e) {
            fail(e);
        }
    }

    @Test
    void update() {

        RunMember runMember = new RunMember("Tomek", 20, 10);
        try {
            runDao.save(runMember);
            runMember.setStartNumber(50);
            runMember.setName("Inna nazwa");
            runDao.save(runMember);
//            runDao.update(run.getId());

            RunMember update = runDao.findById(runMember.getId());
            assertNotNull(update);
            assertEquals(runMember.getStartNumber(), update.getStartNumber());
            assertEquals(runMember.getName(), update.getName());

        } catch (SQLException throwables) {
            fail(throwables);
        }
    }

    @Test
    void deleteById() {

        RunMember runMember = new RunMember("Tomek", 20, 10);
        try {
            runDao.save(runMember);
            runDao.deleteById(runMember.getId());
            RunMember deleted = runDao.findById(runMember.getId());

            assertNull(deleted);

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Test
    void findByNameFragment() {

        RunMember runMember1 = new RunMember("Dominik", 50, 5);
        RunMember runMember2 = new RunMember("Marek", 40, 10);
        RunMember runMember3 = new RunMember("Tomek", 30, 15);
        try {
            runDao.save(runMember1);
            runDao.save(runMember2);
            runDao.save(runMember3);

            List<RunMember> result = runDao.findByNameFragment("om");
            List<RunMember> result1 = runDao.findByNameFragment("Marek");
            List<RunMember> result2 = runDao.findByNameFragment("dsdsds");

            assertNotNull(result);
            assertEquals(2, result.size());
            assertEquals(runMember1.getId(), result.get(0).getId());
            assertEquals(runMember1.getName(), result.get(0).getName());
            assertEquals(runMember1.getStartNumber(), result.get(0).getStartNumber());
            assertEquals(runMember1.getRunId(), result.get(0).getRunId());
            assertEquals(1, result1.size());
            assertEquals(0, result2.size());

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }


    @Test
    void findByMembersLimitRange() {

        try {

            RunMember runMember1 = new RunMember("Dominik", 50, 5);
            RunMember runMember2 = new RunMember("Marek", 100, 10);
            RunMember runMember3 = new RunMember("Tomek", 150, 15);
            runDao.save(runMember1);
            runDao.save(runMember2);
            runDao.save(runMember3);

            List<RunMember> result = runDao.findByStartNumberRange(70, 155);
            List<RunMember> result1 = runDao.findByStartNumberRange(60, 64);
            List<RunMember> result2 = runDao.findByStartNumberRange(80, 120);

            assertNotNull(result);
            assertEquals(2, result.size());
            assertEquals(0, result1.size());
            assertEquals(1, result2.size());
            assertEquals(runMember2.getId(), result2.get(0).getId());
            assertEquals(runMember2.getName(), result2.get(0).getName());
            assertEquals(runMember2.getStartNumber(), result2.get(0).getStartNumber());
            assertEquals(runMember2.getRunId(), result2.get(0).getRunId());

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}