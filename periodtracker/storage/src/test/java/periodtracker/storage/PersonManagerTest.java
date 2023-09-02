package periodtracker.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import periodtracker.core.Person;

public class PersonManagerTest {

	private FileHandling fileHandling;
	private String fileName = "test.json";
	private PersonManager pm = new PersonManager(fileName);
	private List<Person> persons = new ArrayList<>();
	private List<Person> emptyList = new ArrayList<>();

	private HashMap<String, List<String>> map1 = new HashMap<String, List<String>>();
	private HashMap<String, List<String>> map2 = new HashMap<String, List<String>>();

	private Person p1 = new Person("personONE",25,map1);
	private Person p2 = new Person("personTWO",40,map2);

	@BeforeEach
	public void setUp() {
		this.fileHandling = new FileHandling(true);

	}

	@AfterEach
	public void delete() {
		fileHandling.writeInformation(fileName,emptyList);
	}

	@Test
	@DisplayName("Test getPersons")
	public void getPersons() {
		Assertions.assertEquals(emptyList.toString(),pm.getPersons().toString());

		persons.add(p1);
		persons.add(p2);

		pm.addPerson(p1);
		pm.addPerson(p2);

		Assertions.assertEquals(persons.size(),pm.getPersons().size());
		Assertions.assertEquals(persons.toString(),pm.getPersons().toString());
	}

	@Test
	@DisplayName("Test addPersons")
	public void testAddPerson() {

		Assertions.assertEquals(emptyList.toString(),pm.getPersons().toString());
		pm.addPerson(p1.getName(),p1.getAge(),map1);
		pm.addPerson(p2.getName(),p2.getAge(),map2);

		Assertions.assertEquals(Arrays.asList(p1.getName(),p2.getName()),pm.getNameList());
		Assertions.assertThrows(IllegalArgumentException.class,
				() -> pm.addPerson(p1));

	}

	@Test
	@DisplayName("Test savePerson")
	public void testSavePerson() {
		fileHandling.writeInformation(fileName,pm.getPersons());
		assertEquals(pm.getPersons().toString(),fileHandling.readInformation(fileName).toString());
	}


	@Test
	@DisplayName("Test updateFile")
	public void testUpdateFile() {

		pm.updateFile(pm.getPersons());
		assertEquals(new ArrayList<>(),fileHandling.readInformation(fileName));
		pm.updateFile(pm.getPersons());
		assertEquals(pm.getPersons().toString(),fileHandling.readInformation(fileName).toString());

	}

}
