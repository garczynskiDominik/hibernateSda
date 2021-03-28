package main;

import dao.RunDao;
import dao.RunMemberDao;
import daoImpl.RunDaoImpl;
import daoImpl.RunMemberDaoImpl;
import entity.Run;
import entity.RunMember;

import javax.sound.midi.Soundbank;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class RunCenter {
    public static void main(String[] args) throws SQLException {
        int selected = 0;
        Scanner scanner = new Scanner(System.in);
        do {
            log("[1] Dopisz nowy bieg");
            log("[2] Wyświetl biegi");
            log("[3] Usuń bieg");
            log("[4] Wyświetl listę uczestników dla biegu");
            log("[5] Dopisz uczestnika do biegu");
            log("[6] Usuń uczestnika");
            log("[7] Wyszukaj uczestnika po numerze startowym\n\n");
            log("[0] Zamknij");
            selected = scanner.nextInt();

            switch (selected) {
                case 1:
                    handleAddNewRun();
                    break;
                case 2:
                    handleShowAllRun();
                    break;
                case 3:
                    deleteRun();
                    break;
                case 4:
                    handleShowAllMembersToRun();
                    break;
                case 5:
                    handleAddNewMemberToRun();
                    break;
                case 6:
                    deleteMember();
                    break;
                case 7:
                    findMemberByStartNumber();
                    break;
            }

        } while (selected != 0);
    }

    private static void deleteMember() throws SQLException {
        Scanner scanner = new Scanner(System.in);
        RunMemberDao runMemberDao = new RunMemberDaoImpl();
        log("Podaj id uczestnika");
        RunMember runMember = runMemberDao.findById(scanner.nextLong());
        if (runMember != null) {
            runMemberDao.deleteById(runMember.getId());
        }
    }

    private static void findMemberByStartNumber() throws SQLException {
        Scanner scanner = new Scanner(System.in);
        log("Podaj id biegu");
        Long id = scanner.nextLong();
        log("Podaj numer startowy");
        List<RunMember> list = new RunMemberDaoImpl().findByStartNumber(scanner.nextInt(), id);
        list.forEach(System.out::println);
    }

    private static void handleShowAllMembersToRun() throws SQLException {
        Scanner scanner = new Scanner(System.in);
        log("Podaj id biegu");
        List<RunMember> members = new RunMemberDaoImpl()
                .findAllByRunId(scanner.nextLong());
        members.forEach(System.out::println);
    }

    private static void handleAddNewMemberToRun() throws SQLException {
        Scanner scanner = new Scanner(System.in);
        log("Podaj id biegu");
        Long runId = scanner.nextLong();
        RunMember runMember = new RunMember();
        log("Podaj imie");
        runMember.setName(scanner.next());
        log("Podaj numer startowy");
        runMember.setStartNumber(scanner.nextInt());
        runMember.setRun(new RunDaoImpl().findById(runId));
        new RunMemberDaoImpl().save(runMember);


    }

    private static void deleteRun() throws SQLException {
        Scanner scanner = new Scanner(System.in);
        RunDao runDao = new RunDaoImpl();
        RunMemberDao runMemberDao = new RunMemberDaoImpl();
        log("Podaj id biegu do skasowania");
        Long id = scanner.nextLong();
        Run run = runDao.findById(id);
        if (run != null) {
            runDao.deleteById(id);
        } else {
            log("Nie ma takiego biegu");
        }


    }

    private static void handleShowAllRun() throws SQLException {
        RunDao runDao = new RunDaoImpl();
        List<Run> list = runDao.findAll();
        log("Lista biegów");

        list.forEach(System.out::println);

    }

    private static void handleAddNewRun() throws SQLException {
        Scanner scanner = new Scanner(System.in);
        RunDao runDao = new RunDaoImpl();

        Run run = new Run();
        log("Podaj nazwe biegu.");
        run.setName(scanner.nextLine());
        log("Podaj limit osob");
        run.setMembersLimit(scanner.nextInt());
        log("Podaj dystans");
        run.setDistance(scanner.nextInt());
        runDao.save(run);
    }

    private static void log(String text) {
        System.out.println(text);
    }
}