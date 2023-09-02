package periodtracker.rest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
// import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

import periodtracker.core.Person;

@AutoConfigureMockMvc
@ActiveProfiles("test")
@ContextConfiguration(classes = {RestAPIController.class, PersonService.class, RestApplication.class})
@SpringBootTest(classes = RestApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class RestAPITest {


  @Autowired
  private PersonService personService = new PersonService();

  @Autowired
  private MockMvc mockMvc;

  Person testPerson;

  @BeforeEach
  public void setUp() throws JsonProcessingException, Exception {

    RestApplication.isTest(true);

    mockMvc
        .perform(post("/addPerson/testPerson/24")
            .content(new ObjectMapper().writeValueAsString(testPerson))
            .contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
        .andReturn();

  }

  @AfterEach
  public void clearFile() throws Exception {
    personService.clearFile();

  }

  @Test
  @Order(1)
  public void testGetPerson() throws Exception {

    this.mockMvc.perform(get("/getPerson/testPerson")).andDo(print()).andExpect(status().isOk());

  }

  @Test
  public void testAddPerson() throws Exception {
    MvcResult result = mockMvc.perform(post("/addPerson/test/22")
        .contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON)).andReturn();

    assertEquals(result.getResponse().getStatus(), 201);

  }

  @Test
  public void testAddSymptoms() throws Exception {

    MvcResult result = mockMvc
        .perform(post("/addSymptoms/testPerson/2022-04-04/Cramps")
            .contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
        .andReturn();

    assertEquals(result.getResponse().getStatus(), 201);

  }

  @Test
  @Order(2)
  public void testGetSymptoms() throws Exception {

    this.mockMvc.perform(get("/getSymptoms/testPerson")).andDo(print()).andExpect(status().isOk());

  }

  @Test
  public void removeSymptom() throws Exception {

    mockMvc.perform(post("/addPerson/testPerson/24").contentType(MediaType.APPLICATION_JSON)
        .accept(MediaType.APPLICATION_JSON)).andReturn();

    mockMvc
        .perform(post("/addSymptoms/testPerson/2022-04-04/Cramps")
            .contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
        .andReturn();



    this.mockMvc.perform(delete("/removeSymptom/testPerson/2022-04-04/Cramps")).andDo(print())
        .andExpect(status().isOk());

  }

  @Test
  public void getSymptomsOnDay() throws Exception {
    mockMvc
        .perform(post("/addSymptoms/testPerson/2022-04-04/Cramps")
            .contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
        .andReturn();

    this.mockMvc.perform(get("/getSymptomsOnDay/testPerson/2022-04-04")).andDo(print())
        .andExpect(status().isOk());

    assertTrue(this.mockMvc.perform(get("/getSymptomsOnDay/testPerson/2022-04-04")).andReturn()
        .getResponse().getContentAsString().contains("Cramps"));

  }

}
