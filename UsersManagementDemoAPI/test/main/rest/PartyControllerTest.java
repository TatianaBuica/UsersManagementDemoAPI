package main.rest;

import main.ObjectToJson;
import main.SpringBootMvcApplication;
import main.beans.entity.Address;
import main.beans.entity.Party;
import main.beans.entity.User;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.Collections;
import java.util.UUID;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = SpringBootMvcApplication.class)
@AutoConfigureMockMvc
@AutoConfigureTestDatabase
@DirtiesContext
public class PartyControllerTest {
    @Autowired private MockMvc mvc;
    @Autowired private WebApplicationContext webApplicationContext;

    @Before()
    public void setup() {
        mvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Test
    public void testThatAPartyCanBeCreated() throws Exception {
        Address mainAddress = new Address(UUID.randomUUID().toString(), "USA", "NY", "1A", "10");
        Address secondaryAddress = new Address(UUID.randomUUID().toString(), "USA", "Chicago", "1A", "10");
        User user = new User("2030919883662", "test2@email.com", "Sarah", "Brad", "nr 1", "ps111");

        Party party = new Party("Party 1", mainAddress, Collections.singletonList(secondaryAddress), Collections.singletonList(user));
        mvc.perform(post("/savePartyObject").contentType(MediaType.APPLICATION_JSON)
                .content(ObjectToJson.objectToJson(party)))
                .andExpect(status().isOk());
    }

    //todo add tests for listing, deleting and exception scenarios
}
