package dao;

import entity.NfcTag;
import entity.Run;

import java.sql.SQLException;
import java.util.List;

public interface NfcTagDao {
    void save(NfcTag nfcTag) throws SQLException; //C

    List<NfcTag> findAll() throws SQLException; //R

    NfcTag findById(Long id) throws SQLException; //R

    void update(Long id) throws SQLException; //U

    void deleteById(Long id) throws SQLException; //D

    List<NfcTag> findByNameFragment(String fragment) throws SQLException;
}
