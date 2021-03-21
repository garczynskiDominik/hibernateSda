package main;

import daoImpl.RunDaoImpl;
import daoImpl.RunMemberDaoImpl;
import dataBaseSessionFactory.HibernateUtils;
import entity.Run;
import entity.RunMember;

import java.sql.SQLException;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        try {

            RunDaoImpl runDao = new RunDaoImpl();
            RunMemberDaoImpl memberDao = new RunMemberDaoImpl();


            runDao.save(new Run("Dominik", 100,5));
            runDao.save(new Run("Tomek", 200,10));
            runDao.save(new Run("Krzysiek", 300,15));


//
//            memberDao.save(new RunMember(1L, "Dominik", 12, 2));
//            memberDao.save(new RunMember(2L, "Tomek", 13, 20));
//            memberDao.save(new RunMember(3L, "Krzysiek", 15, 200));

//            runDao.update(342L);
            List<Run> list = runDao.findAll();
            list.forEach(System.out::println);

//

////
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
