package periodtracker.storage;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import periodtracker.core.Person;
import periodtracker.core.CalendarLogic;

public class FileHandlingTest {

	private FileHandling fileHandling;

	CalendarLogic cl = new CalendarLogic();

	private String fileName = "test.json";
	private PersonManager personManager;

	@AfterEach
	public void clearFile() {
		personManager.updateFile(new ArrayList<>());
	}

	@BeforeEach
	public void setUp() {
    fileHandling = new FileHandling(true);
		personManager = new PersonManager(fileName);
	
	}

	@Test
	@DisplayName("Test constructor")
	public void testConstructor() {
		assertNotNull(this.fileHandling);
	}


	@Test
	@DisplayName("Test writeInformation")
	public void testWriteInformation() {

		fileHandling.writeInformation(fileName,personManager.getPersons());

		assertThrows(IllegalArgumentException.class,
				() -> fileHandling.writeInformation("",personManager.getPersons()));
		assertThrows(IllegalArgumentException.class,
				() -> fileHandling.writeInformation(null,personManager.getPersons()));

	}

	@Test
	@DisplayName("Test readInformation")
	public void testReadInformation() {

		fileHandling.writeInformation(fileName,personManager.getPersons());

		List<Person> test = fileHandling.readInformation(fileName);

		Assertions.assertEquals(test.size(),personManager.getPersons().size());
		for (int i = 0; i < personManager.getPersons().size(); i++) {
			Assertions.assertEquals(personManager.getPersons().get(i).getName(),test.get(i).getName());
			Assertions.assertEquals(personManager.getPersons().get(i).getAge(),test.get(i).getAge());
			Assertions.assertEquals(personManager.getPersons().get(i).getSymptomMap(),test.get(i).getSymptomMap());
		}

		Assertions.assertThrows(IllegalArgumentException.class,
				() -> fileHandling.readInformation(null));

	}
}
