package periodtracker.core;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class PersonTest {

	private Person person;
	List<String> symptomsLise = new ArrayList<>();
	HashMap<String, List<String>> symptomsMap = new HashMap<>();
	CalendarLogic cl = new CalendarLogic();
	LocalDate liseDay = cl.getDate();

	@BeforeEach
	public void setUp() {
		symptomsLise.add("Headache");
		symptomsLise.add("Intense-bleedings");
		person = new Person("Lise",23,symptomsMap);
	}

	@Test
	@DisplayName("Test the constructor")
	public void testPerson() {
		assertNotNull(this.person.getName());
		assertNotNull(this.person.getAge());
		assertNotNull(this.person.getSymptomMap());
	}

	@Test
	@DisplayName("Test the toString")
	public void testToString() {
		Person person = new Person();
		String expected = "";

		assertEquals(expected,person.toString());
	}

	@Test
	@DisplayName("Tests setName")
	public void testSetName() throws Exception {
		String name = person.getName();
		checkIfNameIsValid("123",name);
		checkIfNameIsValid("M 2",name);
		checkIfNameIsValid("E",name);
		checkIfNameIsValid("e",name);
		Assertions.assertDoesNotThrow(() -> person.setName("Lise"));
		Assertions.assertEquals("Lise",person.getName());
	}

	/**
	 * Help method to check if a name is valid
	 * 
	 * @param invalidName
	 * @param thisName
	 */
	public void checkIfNameIsValid(String invalidName,String thisName) {
		Assertions.assertThrows(IllegalArgumentException.class,() -> person.setName(invalidName));
		Assertions.assertEquals(thisName,person.getName());
	}

	@Test
	@DisplayName("Tests setAge")
	public void testSetAge() throws Exception {
		int age = person.getAge();
		checkIfAgeIsValid(9,age);
		checkIfAgeIsValid(100,age);
		checkIfAgeIsValid(-10,age);
		checkIfAgeIsValid(71,age);
		checkIfAgeIsValid(0,age);
		Assertions.assertDoesNotThrow(() -> {
			person.setAge(20);
		});
		Assertions.assertEquals(20,person.getAge());
	}

  /**
	 * Help method to check if a ag is valid
	 * 
	 * @param invalidAge
	 * @param thisAge
	 */
	public void checkIfAgeIsValid(int invalidAge,int thisAge) {
		Assertions.assertThrows(IllegalArgumentException.class,() -> person.setAge(invalidAge));
		Assertions.assertEquals(thisAge,person.getAge());
	}

	@Test
	@DisplayName("Test setSymptoms")
	public void testSetSymptoms() throws Exception {
		person.addSymptoms(liseDay,"Small-bleedings");
		Assertions.assertEquals(Arrays.asList("Small-bleedings"),person.getDateSymptom(liseDay));
		Assertions.assertEquals(liseDay,cl.getDate());
		symptomsLise.add("Small-bleedings");
		symptomsMap.put(liseDay.toString(),symptomsLise);
		Assertions.assertThrows(IllegalArgumentException.class,
				() -> person.addSymptoms(liseDay.plusDays(2),"Small-bleedings"));
		Assertions.assertDoesNotThrow(() -> person.addSymptoms(liseDay.minusDays(2),"Small-bleedings"));
	}

	@Test
	@DisplayName("Tests if you can have an invalid symptom 'Hunger'")
	public void testInvalidSymptom() {
		person.addSymptoms(liseDay,"Hunger");
		person.addSymptoms(liseDay,"Headache");

		Assertions.assertEquals(Arrays.asList("Headache"),person.getDateSymptom(liseDay));
		Assertions.assertFalse(person.getDateSymptom(liseDay).contains("Hunger"));
	}

	@Test
	@DisplayName("Test removeSymptoms")
	public void testRemoveSymptoms() {
		person.addSymptoms(liseDay,"Headache");
		person.addSymptoms(liseDay,"Small-bleedings");

		person.removeSymptoms(liseDay,"Headache");
		assertEquals(Arrays.asList("Small-bleedings"),person.getDateSymptom(liseDay));

	}
}
