package periodtracker.storage;

import java.util.List;

import periodtracker.core.Person;

/**
 * Interface for our FileHandling.
 */
public interface FileHandlingInterface {
	public void writeInformation(String fileName, List<Person> persons);

	public List<Person> readInformation(String fileName);
}
