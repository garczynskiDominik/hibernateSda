package main;

import daoImpl.RunDaoImpl;
import daoImpl.RunMemberDaoImpl;
import dataBaseSessionFactory.HibernateUtils;
import entity.Run;
import entity.RunMember;
import org.hibernate.Session;

import javax.persistence.Query;
import java.sql.SQLException;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        RunDaoImpl runDao = new RunDaoImpl();
        RunMemberDaoImpl memberDao = new RunMemberDaoImpl();
        try {
        runDao.save(new Run("Run of Freedom", 10, 5));
        runDao.save(new Run("Indepedence Run", 15, 10));
        runDao.save(new Run("LongDistanceRun", 20, 50));

        memberDao.save(new RunMember("Dominik", 1, 1));
        memberDao.save(new RunMember("Tomek", 1, 2));
        memberDao.save(new RunMember("Krzysiek", 1, 3));
        memberDao.save(new RunMember("Marek", 2, 4));
        memberDao.save(new RunMember("Eryk", 1, 5));
        memberDao.save(new RunMember("Szymon", 1, 6));
        memberDao.save(new RunMember("Piotrek", 1, 7));
        memberDao.save(new RunMember("Zbyszek", 2, 8));
        memberDao.save(new RunMember("Grzesiek", 3, 9));

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

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }


    }


}
