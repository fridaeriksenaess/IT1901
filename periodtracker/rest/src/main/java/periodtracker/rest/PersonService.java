package periodtracker.rest;

import java.util.List;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;


import org.springframework.stereotype.Service;

import periodtracker.storage.PersonManager;
import periodtracker.core.Person;

/**
 * Serviceclass for our RestApplication.
 */
@Service
public class PersonService {

  private PersonManager personManager = new PersonManager("users.json");
  private List<Person> persons = new ArrayList<>(personManager.getPersons());

  /**
   * Empty constructor.
   */
  public PersonService() {

  }

  /**
   * Contstructor for declaring for ui to user test.json file instead of
   * users.json.
   *
   * @param isTest
   */
  public PersonService(boolean isTest) {
    this.personManager = new PersonManager("test.json");
    this.persons = new ArrayList<>(personManager.getPersons());
  }

  /**
   * Method for getting active person.
   *
   * @return the active person.
   */
  public Person getActivePerson() {
    return personManager.getActivePerson();
  }

  /**
   * Method for getting the list of persons.
   *
   * @return the list of persons.
   */
  public List<Person> getPersonList() {
    return personManager.getPersons();
  }

  /**
   * Methods that saves the persons to the file.
   */
  public void savePersons() {
    try {
      personManager.updateFile(persons);
    } catch (IllegalStateException e) {
      System.out.println("Could not save");
    }
  }

  /**
   * Method that gets the person with the given name.
   *
   * @param name
   * @return the person with the given name, (null if there is no person with this
   *         name).
   */
  public Person getPerson(String name) {
    for (Person p : persons) {
      if (p.getName().equals(name)) {
        personManager.setActivePerson(name);
        personManager.getActivePerson();
        return p;
      }
    }
    return null;
  }

  /**
   * Method for creating a person, when you want to add a new person to the list.
   *
   * @param person
   * @return returns a String based on wether or not the person is created.
   */
  public String createPerson(String name, int age) {
    try {
      Person person = new Person(name, age, new HashMap<String, List<String>>());
      personManager.addPerson(person);
      persons.add(person);
      return "200 OK";
    } catch (Exception e) {
      System.out.println("Can not add this person.");
      return "BAD_REQUEST";
    }
  }

  
  /**
   * Method for clearing the file, used for testing the rest api.
   */
  public void clearFile() {
    personManager.updateFile(new ArrayList<>());
  }

  /**
   * Method for getting the symptomsMap of the given persons name.
   *
   * @param name
   * @return this persons symptomMap.
   */
  public HashMap<String, List<String>> getSymptomsMap(String name) {
    if (!persons.contains(personManager.getPerson(name))) {
      return null;
    }
    HashMap<String, List<String>> s = personManager.getPerson(name).getSymptomMap();
    return s;
  }

  /**
   * Method for adding symptom to list.
   *
   * @param date
   * @param symtpom
   * @param name
   * @return a String wether or not the symptoms are added.
   */
  public String addSymptom(String date, String symptom, String name) {
    personManager.setActivePerson(name);
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-d");
    LocalDate tempdate = LocalDate.parse(date, formatter);
    try {
      personManager.addSymptoms(tempdate, symptom);
      return "200 OK";
    } catch (Exception e) {
      return "BAD REQUEST";
    }
  }

  /**
   * Method for getting symptoms on a given date.
   * 
   * @param date
   * @return list containing symptoms.
   */
  public List<String> getSymptomsOnDay(LocalDate date) {
    return getActivePerson().getDateSymptom(date);
  }

  /**
   * Method for removing symptom from list.
   * 
   * @param date
   * @param symptom
   * @return a String wether or not the symptom has been removed.
   */
  public String removeSymptom(LocalDate date, String symptom) {
    try {
      personManager.removeSymptoms(date, symptom);
      return "200 OK";
    } catch (Exception e) {
      System.out.println("Could not remove symptom.");
      return "BAD REQUEST";
    }

  }

}