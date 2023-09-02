package periodtracker.rest;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ResponseStatus;

import periodtracker.core.Person;

/**
 * Controller class for springboot webserver.
 */

@RestController
public class RestAPIController {

  @Autowired
  private PersonService personService;

  /**
   * Empty constructor.
   */
  public RestAPIController() {

  }

  /**
   * Constructor that takes in a personManager and a personService.
   * 
   * @param personManager
   * @param personService
   */
  public RestAPIController(PersonService personService) {
    this.personService = new PersonService();

  }

  /**
  * Method for getting all the persons in the list. 
  */

  @GetMapping("/getPersons")
  public List<Person> getPersonList() {
    return this.personService.getPersonList();
  }

  /**
   * Method for getting a persons age.
   * 
   * @param name
   * @return the persons age.
   */
  @GetMapping("/getPerson/{name}")
  public Person getPerson(@PathVariable("name") String name) {
    return this.personService.getPerson(name);
  }

  /**
   * 
   * Method for adding a new person to the list of persons.
   * 
   * @param name
   * @param int
   * @return a String based on if added person was successful. 
   */
  @PostMapping(value = "/addPerson/{name}/{age}")
  @ResponseStatus(code = HttpStatus.CREATED)
  public String addPerson(@PathVariable String name, @PathVariable int age) {
    return personService.createPerson(name, age);
  };

  /**
   * Method that gets the persons symptom(s).
   * 
   * @param name
   * @return the persons symptom(s).
   */
  @GetMapping("/getSymptoms/{name}")
  public HashMap<String, List<String>> getSymptoms(@PathVariable("name") String name) {
    return this.personService.getSymptomsMap(name);
  }

  /**
   * Method that adds symptom a person.
   * This method only adds one symptom at one time.
   * 
   * @param name
   * @param date
   * @param symptom
   * @return a String wether or not the symptom has been added.
   */
  @PostMapping(value = "/addSymptoms/{name}/{date}/{symptom}")
  @ResponseStatus(code = HttpStatus.CREATED)
  public String addSymptom(@PathVariable String name, @PathVariable String date, @PathVariable String symptom) {
    return personService.addSymptom(date, symptom, name);
  }

  /**
   * Method for removing symptoms from a person.
   * 
   * @param date
   * @param symptom
   */
  @DeleteMapping("/removeSymptom/{name}/{date}/{symptom}")
  public void removeSymptom(@PathVariable("date") String date, @PathVariable("symptom") String symptom) {
    LocalDate d = LocalDate.parse(date);
    personService.removeSymptom(d, symptom);
  }

  /**
   * Method for getting all the symptoms on the given date for the person.
   * 
   * @param name
   * @param date
   * @return the given person's symptoms on the given date.
   */
  @GetMapping("/getSymptomsOnDay/{name}/{date}")
  public List<String> getSymptomsOnDay(@PathVariable("name") String name, @PathVariable String date) {
    LocalDate d = LocalDate.parse(date);
    return this.personService.getSymptomsOnDay(d);
  }
}
