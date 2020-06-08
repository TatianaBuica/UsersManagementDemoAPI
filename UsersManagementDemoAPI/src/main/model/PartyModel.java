package main.model;

import main.beans.entity.Address;
import main.beans.entity.Party;
import main.beans.entity.User;
import main.repository.AddressRepository;
import main.repository.PartyRepository;
import main.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import javax.transaction.Transactional;
import java.util.List;

@Controller
public class PartyModel {
    @Autowired private PartyRepository partyrepository;
    @Autowired private UserRepository userRepository;
    @Autowired private AddressRepository addressRepository;

    public List<Party> all() {
        return partyrepository.findAll();
    }

    public Party findPartyById(String id) {
        return partyrepository.findById(id);
    }

    public Party findPartyByMainAddress(String mainAddress) {
        return partyrepository.findByMainAddress(mainAddress);
    }

    @Transactional
    public void savePartyObject(Party party) {
        //todo refactor
        if (party.getMainAddress() != null) {
            if (addressRepository.findById(party.getMainAddress().getId()) == null) {
                addressRepository.save(party.getMainAddress());
            }
        }
        if (!party.getSecondaryAddresses().isEmpty()) {
            for (Address address : party.getSecondaryAddresses()) {
                if (addressRepository.findById(address.getId()) == null) {
                    addressRepository.save(address);
                }
            }
        }
        if (!party.getUsers().isEmpty()) {
            for (User user : party.getUsers()) {
                if (userRepository.findByCnp(user.getCnp()) == null) {
                    userRepository.save(user);
                }
            }
        }
        partyrepository.save(party);
    }

    public void deletePartyObject(Party party) {
        partyrepository.delete(party);
    }
}
