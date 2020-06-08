package main.rest;

import main.ObjectToJson;
import main.SpringBootMvcApplication;
import main.beans.entity.Address;
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

import java.util.UUID;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = SpringBootMvcApplication.class)
@AutoConfigureMockMvc
@AutoConfigureTestDatabase
@DirtiesContext
public class AddressControllerTest {
    @Autowired private MockMvc mvc;
    @Autowired private WebApplicationContext webApplicationContext;

    @Before()
    public void setup() {
        mvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Test
    public void testThatAnAddressCanBeCreated() throws Exception {
        Address mainAddress = new Address(UUID.randomUUID().toString(), "USA", "NY", "1A", "10");

        mvc.perform(post("/saveAddressObject").contentType(MediaType.APPLICATION_JSON)
                .content(ObjectToJson.objectToJson(mainAddress)))
                .andExpect(status().isOk());
    }

    //todo add tests for listing, deleting and exception scenarios
}
