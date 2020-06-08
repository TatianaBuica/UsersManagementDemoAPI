package main.rest;

import main.beans.entity.Address;
import main.repository.AddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class AddressController {
    @Autowired
    private AddressRepository repository;

    @GetMapping("/findAddressById")
    Address findAddressById(@RequestParam(name = "id") String id) {
        return repository.findById(id);
    }

    @GetMapping("/findAddressByDetails")
    Address findAddressByDetails(@RequestParam(name = "country") String country, @RequestParam(name = "city") String city,
                                 @RequestParam(name = "street") String street, @RequestParam(name = "number") String number) {
        return repository.findByCountryAndCityAndStreetAndNumber(country, city, street, number);
    }

    @PostMapping("/saveAddressObject")
    void saveAddressObject(@RequestBody Address address) {
        repository.save(address);
    }

    @PostMapping("/deleteAddressObject")
    void deleteAddressObject(@RequestBody Address address) {
        repository.delete(address);
    }
}
