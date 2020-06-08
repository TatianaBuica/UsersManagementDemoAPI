package main.repository;

import main.beans.entity.Party;
import org.springframework.data.repository.Repository;

import java.util.List;

public interface PartyRepository extends Repository<Party, String> {
    List<Party> findAll();
    Party findById(String id);
    Party findByMainAddress(String mainAddress);
    void save(Party party);
    void delete(Party party);
}
