package main.rest;

import main.beans.entity.Party;
import main.model.PartyModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.List;

@RestController
public class PartyController {
    @Autowired private PartyModel partyModel;

    @GetMapping("/findAllParties")
    List<Party> all() {
        return partyModel.all();
    }

    @GetMapping("/findPartyById")
    Party findPartyById(@RequestParam(name = "id") String id) {
        return partyModel.findPartyById(id);
    }

    @GetMapping("/findPartyByMainAddress")
    Party findPartyByMainAddress(@RequestParam(name = "mainAddress") String mainAddress) {
        return partyModel.findPartyByMainAddress(mainAddress);
    }

    @PostMapping("/savePartyObject")
    @Transactional
    void savePartyObject(@RequestBody Party party) {
        partyModel.savePartyObject(party);
    }

    @PostMapping("/deletePartyObject")
    void deletePartyObject(@RequestBody Party party) {
        partyModel.deletePartyObject(party);
    }
}
