package periodtracker.storage;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import periodtracker.core.Person;


/**
 * PersonManager class that has contains all of the Persons that is stored.
 */
public class PersonManager {

  private List<Person> persons;
  private Person activePerson = new Person();
  private FileHandling fileHandling = new FileHandling(false);
  private String fileName;

  /**
   * Constructor that takes in fileName
   * If the fileName is test.json, the constructor in FileHandling will be sat to
   * true.
   * 
   * @param fileName
   */
  public PersonManager(String fileName) {
    this.fileName = fileName;
    if ("test.json".equals(fileName)) {
      this.fileHandling = new FileHandling(true);
    }
    setPersons(fileHandling.readInformation(fileName));

  }

  /**
   * Empty constructor that is used by PersonService-class to set the fileName to
   * be.
   * users.json
   */
  public PersonManager() {
    this.persons = new ArrayList<>(fileHandling.readInformation(fileName));

  }

  /**
   * Method that updates the file with the new list of persons.
   * 
   * @param persons
   */
  public void updateFile(List<Person> persons) {
    if ("test.json".equals(fileName)) {
      this.fileHandling = new FileHandling(true);
    }
    fileHandling.writeInformation(fileName, persons);
    this.savePersons();
  }

  /**
   * A method which returns the person that is logged in.
   *
   * @return the person that is active.
   */
  public Person getActivePerson() {
    setActivePerson(activePerson.getName());
    return this.activePerson; 

  }
  
  /**
   * A method which sets an activeperson by logging the person in.
   *
   * @param name
   */
  public void setActivePerson(String name) {
    for (Person person : persons) {
      if (name.equals(person.getName())) {
        activePerson = person;
      }
    }
  }

  /**
   * Method for getting the list of persons.
   * 
   * @return the list of persons.
   */
  public List<Person> getPersons() {
    return new ArrayList<>(persons);
  }

  /**
   * Method for getting the names of all the persons.
   * 
   * @return the names of the persons in the list.
   */
  public List<String> getNameList() {
    List<String> personList = new ArrayList<>();

    for (int i = 0; i < persons.size(); i++) {
      String s = persons.get(i).getName();
      personList.add(s);
    }
    return personList;
  }

  /**
   * A method for setting a list of persons.
   *
   * @param persons list of persons.
   */
  public void setPersons(List<Person> persons) {
    this.persons = new ArrayList<>(persons);
  }

  /**
   * Method for overwriting/save the file.
   *
   */
  private void savePersons() {
    fileHandling.writeInformation(fileName, persons);
  }

  /**
   * Method for adding a person to the list.
   * Throws exception if the name is already used.
   * 
   * @param name
   * @param age
   * @param map
   */
  public void addPerson(String name, int age, HashMap<String, List<String>> map) {
    if (persons.stream().anyMatch(p -> p.getName().equals(name))) {
      throw new IllegalArgumentException("Name is used already");
    } else {
      Person p = new Person(name, age, map);
      this.persons.add(p);
      //p.setLoggedIn(true);
      setActivePerson(p.getName());
      this.savePersons();
    }
  }

  /**
   * A method for removing a person from the list. This is only indirectly called
   * on through restapi test.
   *
   * @param name
   * @return a boolean (true if the person was removed, else false).
   */
  public boolean removePerson(String name) {
    for (Person i : persons) {
      if (i.getName().equals(name)) {
        persons.remove(i);
        this.savePersons();
        return true;
      }
    }
    return false;
  }

  /**
   * A method for saving a person.
   * 
   * @param person
   */
  public void addPerson(Person person) {
    if (persons.stream().anyMatch(p -> p.getName().equals(person.getName()))) {
      throw new IllegalArgumentException("Name is used already");
    }
	  setActivePerson(person.getName());
    this.persons.add(person);
    this.savePersons();
  }

  /**
   * A method for getting one person in the list.
   * 
   * @param name
   * @return the person if it exist, or else null.
   */
  public Person getPerson(String name) {
    for (Person person : persons) {
      if (person.getName().equals(name)) {
        setActivePerson(person.getName());
        return person;
      }
    }
    return null;
  }

  /**
   * Method to get the symptoms on the given date.
   * 
   * @param date
   * @return the symptoms on the given date.
   */
  public List<String> getSymptomsOnDay(LocalDate date) {
    return this.activePerson.getDateSymptom(date);
  }

  /**
   * Methods to get a persons symptoms.
   * 
   * @return all the symptoms of this activeperson.
   */
  public HashMap<String, List<String>> getSymptomsMap() {
    return new HashMap<>(this.activePerson.getSymptomMap());
  }

  /**
   * Method that adds symptom to the list of symptoms.
   * 
   * @param date
   * @param list
   */
  public void addSymptomsList(LocalDate date, List<String> list) {
    for (String symptom : list) {
      addSymptoms(date, symptom);
    }
    this.savePersons();
  }

  /**
   * Method that adds symptom to the list of symptoms and also updates file with
   * this new list.
   * 
   * @param date
   * @param symptom
   */
  public void addSymptoms(LocalDate date, String symptom) {
    activePerson.addSymptoms(date, symptom);
    this.savePersons();
    updateFile(persons);

  }

  /**
   * Method for removing a persons symptoms and also updates file with this new
   * list.
   * 
   * @param date
   * @param symptom
   */
  public void removeSymptoms(LocalDate date, String symptom) {
    activePerson.removeSymptoms(date, symptom);
    updateFile(persons);
  }

}
