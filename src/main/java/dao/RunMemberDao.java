package dao;

import entity.RunMember;

import java.lang.reflect.Member;
import java.sql.SQLException;
import java.util.List;

public interface RunMemberDao {
    void save(RunMember member) throws SQLException; //C

    List<RunMember> findAll() throws SQLException; //R

    RunMember findById(Long id) throws SQLException; //R

    void update(Long idMember, Long idRun) throws SQLException; //U

    void deleteById(Long id) throws SQLException; //D

    List<RunMember> findByNameFragment(String fragment) throws SQLException;

    List<RunMember> findByStartNumberRange(int min, int max) throws SQLException;

    List<RunMember> findAllByRunId(Long id) throws SQLException;

    List<RunMember> findByStartNumber(int startNumber, Long runId) throws SQLException;


}
