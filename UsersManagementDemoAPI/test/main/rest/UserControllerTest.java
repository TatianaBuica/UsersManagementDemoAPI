package main.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import main.ObjectToJson;
import main.SpringBootMvcApplication;
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
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = SpringBootMvcApplication.class)
@AutoConfigureMockMvc
@AutoConfigureTestDatabase
@DirtiesContext
public class UserControllerTest {
    @Autowired private MockMvc mvc;
    @Autowired private WebApplicationContext webApplicationContext;

    @Before()
    public void setup() {
        mvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Test
    public void testThatAllUsersCanReRetrieved() throws Exception {
        User user = new User("2030919863662", "test3@email.com", "Anna", "Brandon", "nr 2", "ps122");
        User user1 = new User("2040919853662", "test5@email.com", "Bob", "Akon", "nr 4", "ps124");
        mvc.perform(post("/saveUserObject").contentType(MediaType.APPLICATION_JSON)
                .content(ObjectToJson.objectToJson(user))).andExpect(status().isOk());
        mvc.perform(post("/saveUserObject").contentType(MediaType.APPLICATION_JSON)
                .content(ObjectToJson.objectToJson(user1))).andExpect(status().isOk());

        MvcResult result = mvc.perform(get("/findAllUsers").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()).andReturn();

        ObjectMapper objectMapper = new ObjectMapper();
        List<User> users = objectMapper.readValue(result.getResponse().getContentAsString(),
                objectMapper.getTypeFactory().constructCollectionType(List.class, User.class));

        assertEquals(2, users.size());
        assertEquals(user.getCnp(), users.get(0).getCnp());
        assertEquals(user.getAddress(), users.get(0).getAddress());
        assertEquals(user.getEmail(), users.get(0).getEmail());
        assertEquals(user.getfName(), users.get(0).getfName());
        assertEquals(user.getlName(), users.get(0).getlName());
        assertEquals(user.getPassword(), users.get(0).getPassword());

        assertEquals(user1.getCnp(), users.get(1).getCnp());
        assertEquals(user1.getAddress(), users.get(1).getAddress());
        assertEquals(user1.getEmail(), users.get(1).getEmail());
        assertEquals(user1.getfName(), users.get(1).getfName());
        assertEquals(user1.getlName(), users.get(1).getlName());
        assertEquals(user1.getPassword(), users.get(1).getPassword());
    }

    @Test
    public void testThatAnUserObjectCanBeCreated() throws Exception {
        User user = new User("2030919883662", "test2@email.com", "Sarah", "Brad", "nr 1", "ps111");
        mvc.perform(post("/saveUserObject").contentType(MediaType.APPLICATION_JSON)
                .content(ObjectToJson.objectToJson(user)))
                .andExpect(status().isOk());
    }

    @Test
    public void testThatAnUserByDetailsCanBeCreated() throws Exception {
        mvc.perform(post("/saveUserByDetails").contentType(MediaType.APPLICATION_JSON)
                .param("cnp", "2830919885449")
                .param("email", "test1@email.com")
                .param("fName", "Ellen")
                .param("lName", "Spark")
                .param("address", "some address")
                .param("password", "password123"))
                .andExpect(status().isOk());
    }

    @Test
    public void testThatAnUserObjectWithDuplicatedCnpCantBeCreated() throws Exception {
        User user = new User("2030919883663", "test21@email.com", "Sarah", "Brad", "nr 1", "ps111");
        mvc.perform(post("/saveUserObject").contentType(MediaType.APPLICATION_JSON)
                .content(ObjectToJson.objectToJson(user)))
                .andExpect(status().isOk());

        User user1 = new User("2030919883663", "test22@email.com", "Sarah1", "Brad1", "nr 1", "ps111");
        mvc.perform(post("/saveUserObject").contentType(MediaType.APPLICATION_JSON)
                .content(ObjectToJson.objectToJson(user1)))
                .andExpect(status().isOk());

        MvcResult result = mvc.perform(get("/findAllUsers").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()).andReturn();

        ObjectMapper objectMapper = new ObjectMapper();
        List<User> users = objectMapper.readValue(result.getResponse().getContentAsString(),
                objectMapper.getTypeFactory().constructCollectionType(List.class, User.class));
        users.size();
    }

    @Test(expected = Exception.class)
    public void testThatAnUserObjectWithDuplicatedEmailCantBeCreated() throws Exception {
        User user = new User("2030919883663", "test11@email.com", "Sarah", "Brad", "nr 1", "ps111");
        mvc.perform(post("/saveUserObject").contentType(MediaType.APPLICATION_JSON)
                .content(ObjectToJson.objectToJson(user)))
                .andExpect(status().isOk());

        User user1 = new User("2030919883655", "test11@email.com", "Sarah1", "Brad1", "nr 1", "ps111");
        mvc.perform(post("/saveUserObject").contentType(MediaType.APPLICATION_JSON)
                .content(ObjectToJson.objectToJson(user1)));
    }

    @Test(expected = Exception.class)
    public void testThatAnUserObjectWithShorterCnpCantBeCreated() throws Exception {
        User user = new User("103091988", "test11@email.com", "Sarah", "Brad", "nr 1", "ps111");
        mvc.perform(post("/saveUserObject").contentType(MediaType.APPLICATION_JSON)
                .content(ObjectToJson.objectToJson(user)))
                .andExpect(status().is5xxServerError());
    }

    @Test(expected = Exception.class)
    public void testThatAnUserObjectWithLongerCnpCantBeCreated() throws Exception {
        User user = new User("1030919888237238243433", "test11@email.com", "Sarah", "Brad", "nr 1", "ps111");
        mvc.perform(post("/saveUserObject").contentType(MediaType.APPLICATION_JSON)
                .content(ObjectToJson.objectToJson(user)))
                .andExpect(status().is5xxServerError());
    }

    @Test(expected = Exception.class)
    public void testThatAnUserObjectWithEmptyCnpCantBeCreated() throws Exception {
        User user = new User("", "test11@email.com", "Sarah", "Brad", "nr 1", "ps111");
        mvc.perform(post("/saveUserObject").contentType(MediaType.APPLICATION_JSON)
                .content(ObjectToJson.objectToJson(user)))
                .andExpect(status().is5xxServerError());
    }

    @Test(expected = Exception.class)
    public void testThatAnUserObjectWithNullEmailCantBeCreated() throws Exception {
        User user = new User("1630917785449", null, "Sarah", "Brad", "nr 1", "ps111");
        mvc.perform(post("/saveUserObject").contentType(MediaType.APPLICATION_JSON)
                .content(ObjectToJson.objectToJson(user)))
                .andExpect(status().is5xxServerError());
    }

    @Test(expected = Exception.class)
    public void testThatAnUserObjectWithEmptyEmailCantBeCreated() throws Exception {
        User user = new User("1680917785449", "", "Sarah", "Brad", "nr 1", "ps111");
        mvc.perform(post("/saveUserObject").contentType(MediaType.APPLICATION_JSON)
                .content(ObjectToJson.objectToJson(user)))
                .andExpect(status().is5xxServerError());
    }

    @Test(expected = Exception.class)
    public void testThatAnUserObjectWithNullCnpCantBeCreated() throws Exception {
        User user = new User(null, "test11@email.com", "Sarah", "Brad", "nr 1", "ps111");
        mvc.perform(post("/saveUserObject").contentType(MediaType.APPLICATION_JSON)
                .content(ObjectToJson.objectToJson(user)))
                .andExpect(status().is5xxServerError());
    }

    @Test
    public void testThatAnUserByDetailsCanBeDeleted() throws Exception {
        mvc.perform(post("/saveUserByDetails").contentType(MediaType.APPLICATION_JSON)
                .param("cnp", "2830917785449")
                .param("email", "test6@email.com")
                .param("fName", "Frank")
                .param("lName", "Gomez")
                .param("address", "some address nr 2")
                .param("password", "passwd123"))
                .andExpect(status().isOk());

        MvcResult result = mvc.perform(get("/findUserByCnp").contentType(MediaType.APPLICATION_JSON)
                .param("cnp", "2830917785449"))
                .andExpect(status().isOk()).andReturn();

        ObjectMapper objectMapper = new ObjectMapper();
        User foundUser = objectMapper.readValue(result.getResponse().getContentAsString(), User.class);

        assertNotNull(foundUser);
        assertEquals("2830917785449", foundUser.getCnp());
        assertEquals("some address nr 2", foundUser.getAddress());
        assertEquals("test6@email.com", foundUser.getEmail());
        assertEquals("Frank", foundUser.getfName());
        assertEquals("Gomez", foundUser.getlName());
        assertEquals("passwd123", foundUser.getPassword());
    }

    @Test
    public void testThatAnUserObjectCanBeDeleted() throws Exception {
        User user = new User("2030919883962", "test4@email.com", "Chris", "Tiede", "Stree nr 1", "ps221");
        mvc.perform(post("/saveUserObject").contentType(MediaType.APPLICATION_JSON)
                .content(ObjectToJson.objectToJson(user))).andExpect(status().isOk());

        MvcResult result = mvc.perform(get("/findUserByCnp").contentType(MediaType.APPLICATION_JSON)
                .param("cnp", user.getCnp()))
                .andExpect(status().isOk()).andReturn();

        ObjectMapper objectMapper = new ObjectMapper();
        User foundUser = objectMapper.readValue(result.getResponse().getContentAsString(), User.class);

        assertNotNull(foundUser);
        assertEquals(user.getCnp(), foundUser.getCnp());
        assertEquals(user.getAddress(), foundUser.getAddress());
        assertEquals(user.getEmail(), foundUser.getEmail());
        assertEquals(user.getfName(), foundUser.getfName());
        assertEquals(user.getlName(), foundUser.getlName());
        assertEquals(user.getPassword(), foundUser.getPassword());
    }
}
