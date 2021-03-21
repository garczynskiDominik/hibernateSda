package dao;

import entity.Run;

import java.sql.SQLException;
import java.util.List;

public interface RunDao {
    void save(Run run) throws SQLException; //C

    List<Run> findAll() throws SQLException; //R

    Run findById(Long id) throws SQLException; //R

    void update(Long id) throws SQLException; //U

    void deleteById(Long id) throws SQLException; //D

    List<Run> findByNameFragment(String fragment) throws SQLException;

    List<Run> findByMembersLimitRange(Integer min, Integer max) throws SQLException;
}
