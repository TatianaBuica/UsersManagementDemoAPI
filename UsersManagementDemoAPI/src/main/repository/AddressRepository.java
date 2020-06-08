package main.repository;

import main.beans.entity.Address;
import org.springframework.data.repository.Repository;

import java.util.List;

public interface AddressRepository extends Repository<Address, String> {
    List<Address> findAll();
    Address findById(String id);
    Address findByCountryAndCityAndStreetAndNumber(String country, String city, String street, String number);
    void save(Address party);
    void delete(Address party);
}
