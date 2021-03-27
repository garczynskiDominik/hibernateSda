package main;

import dao.RunDao;
import daoImpl.RunDaoImpl;
import entity.Run;

import javax.sound.midi.Soundbank;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class RunCenter {
    public static void main(String[] args) throws SQLException {
        int selected = 0;
        Scanner scanner = new Scanner(System.in);
        do {
            System.out.println("1. Dopisz nowy bieg");
            System.out.println("2. Wyświetl biegi");
            selected = scanner.nextInt();

            switch (selected) {
                case 1:
                    handleAddNewRun();
                    break;
                case 2:
                    handleShowAllRun();
                    break;
            }

        } while (selected != 0);
    }

    private static void handleShowAllRun() throws SQLException {
        RunDao runDao = new RunDaoImpl();
        List<Run> list = runDao.findAll();
        System.out.println("Lista biegów");

        System.out.println("-----------------------------------------");
        list.forEach(System.out::println);

    }

    private static void handleAddNewRun() throws SQLException {
        Scanner scanner = new Scanner(System.in);
        RunDao runDao = new RunDaoImpl();
        System.out.println("Podaj nazwe biegu.");
        String name = scanner.nextLine();
        System.out.println("Podaj limit osob");
        int limit = scanner.nextInt();
        System.out.println("Podaj dystans");
        int distance = scanner.nextInt();
        Run run = new Run(name, limit, distance);
        runDao.save(run);
    }
}
