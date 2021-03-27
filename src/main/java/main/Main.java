package main;

import dao.NfcTagDao;
import dao.RunMemberDao;
import daoImpl.NfcTagDaoImpl;
import daoImpl.RunDaoImpl;
import daoImpl.RunMemberDaoImpl;
import dataBaseSessionFactory.HibernateUtils;
import entity.NfcTag;
import entity.Run;
import entity.RunMember;
import org.hibernate.Session;

import javax.persistence.Query;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class Main {
    public static void main(String[] args) throws SQLException {
//        oneToManySave();
//        oneToManySelectTest();
        manyToManySaveTest();


        Session session = HibernateUtils
                .getInstance()
                .getSessionFactory()
                .getCurrentSession();

        session.beginTransaction();
        session.getTransaction().commit();
        session.close();

//        try {
//        runDao.save(new Run("Run of Freedom", 10, 5));
//        runDao.save(new Run("Indepedence Run", 15, 10));
//        runDao.save(new Run("LongDistanceRun", 20, 50));
//
//        memberDao.save(new RunMember("Dominik", 1, 1));
//        memberDao.save(new RunMember("Tomek", 1, 2));
//        memberDao.save(new RunMember("Krzysiek", 1, 3));
//        memberDao.save(new RunMember("Marek", 2, 4));
//        memberDao.save(new RunMember("Eryk", 1, 5));
//        memberDao.save(new RunMember("Szymon", 1, 6));
//        memberDao.save(new RunMember("Piotrek", 1, 7));
//        memberDao.save(new RunMember("Zbyszek", 2, 8));
//        memberDao.save(new RunMember("Grzesiek", 3, 9));

//           List list =  runDao.join();
//           list.forEach(System.out::println);

//            runDao.update(342L);
//            List<Run> list = runDao.findAll();
//            list.forEach(System.out::println);


//            System.out.println(runDao.findById(5L));

//            runDao.deleteById(2L);

//            List<Run> runs = runDao.findByNameFragment("min");
//            runs.forEach(System.out::println);

//            List<Run> runs = runDao.findByMembersLimitRange(200,400);
//            runs.forEach(System.out::println);

//        } catch (SQLException throwables) {
//            throwables.printStackTrace();
//        }


    }

    private static void oneToManySave() throws SQLException {

        RunDaoImpl runDao = new RunDaoImpl();
        RunMemberDaoImpl memberDao = new RunMemberDaoImpl();

        Run run = new Run();
        run.setName("Bieg na 10: ");
        runDao.save(run);

        for (int i = 0; i < 10; i++) {

            RunMember runMember = new RunMember();
            runMember.setName("Biegacz do biegu z wieloma");
            runMember.setRun(run);
            memberDao.save(runMember);
        }
    }

    private static void oneToManySelectTest() throws SQLException {
        RunDaoImpl runDao = new RunDaoImpl();

        Run run = runDao.findById(160L);

        System.out.println("Bieg " + run.getName());
        System.out.println("Ilosc uczestnikow " + run.getRunMembers().size());

    }

    private static void manyToManySaveTest() throws SQLException {
        RunMemberDao runMemberDao = new RunMemberDaoImpl();
        NfcTagDao nfcTagDao = new NfcTagDaoImpl();

        RunMember runMember1 = new RunMember();
        runMember1.setName("Adam");

        RunMember runMember2 = new RunMember();
        runMember2.setName("wojciech");

        runMemberDao.save(runMember1);
        runMemberDao.save(runMember2);

        NfcTag nfcTag1 = new NfcTag();
        nfcTag1.setSerialNumber("tag numer 1");
        nfcTag1.getMembers().add(runMember1);
        nfcTag1.getMembers().add(runMember2);
        nfcTagDao.save(nfcTag1);


        NfcTag nfcTag2 = new NfcTag();
        nfcTag2.setSerialNumber("tag numer 2");
        nfcTag2.getMembers().add(runMember1);
        nfcTag2.getMembers().add(runMember2);
        nfcTagDao.save(nfcTag2);
    }

    private static void nfcTagReadTest() throws SQLException {
        NfcTagDao nfcTagDao = new NfcTagDaoImpl();
        NfcTag tag = nfcTagDao.findById(253L);



    }


}
