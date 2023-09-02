package periodtracker.storage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.fasterxml.jackson.core.JsonParseException;

import com.fasterxml.jackson.core.exc.StreamWriteException;
import com.fasterxml.jackson.core.util.DefaultPrettyPrinter;
import com.fasterxml.jackson.databind.DatabindException;
import com.fasterxml.jackson.databind.JsonMappingException;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;

import periodtracker.core.Person;


/**
 * Filehandling class for writing and reading from file.
 */
public class FileHandling implements FileHandlingInterface {

  private final Path filePath;

  /**
   * Constructor that sets default value of "isTest" to false.
   */
  public FileHandling() {
    this(false);
  }

  /**
   * Constructor that takes in a boolean and creates a path dependent on if we run.
   * the normal application or a test.
   * 
   * @param isTest
   */
  public FileHandling(boolean isTest) {
    if (isTest) {
      this.filePath = Paths.get(System.getProperty("user.home"), "TESTFOLDER", "/");
    } else {
      this.filePath = Paths.get(System.getProperty("user.home"), "ORDINARYFOLDER", "/");
    }
    createDirectory();
  }

  /**
   * Method that creates a directory from the field filePath
   */
  public void createDirectory() {
    String path = String.valueOf(filePath);
    path = path.replaceAll("users.json", "");
    if (Files.exists(Path.of(path))) {
      return;
    }
    try {
      Files.createDirectory(Path.of(path));
    } catch (Exception e) {
      e.printStackTrace();
    }
  }


  /**
   * Method that writes the persons to file.
   * 
   * @param fileName
   * @param persons
   */
  public void writeInformation(String fileName, List<Person> persons) {
    File f = new File(filePath + "/" + fileName);

    if (fileName == null) {
      throw new IllegalArgumentException("Unvalid values");
    }
    if (fileName.isBlank()) {
      throw new IllegalArgumentException("Unvalid values");
    }

    try {
      ObjectMapper ob = new ObjectMapper();
      ObjectWriter w = ob.writer(new DefaultPrettyPrinter());

      w.writeValue(f, persons);

    } catch (FileNotFoundException e) {
      e.printStackTrace();
    } catch (StreamWriteException e) {
      e.printStackTrace();
    } catch (DatabindException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  /**
   * Method that reads from file.
   * 
   * @param fileName
   * @return the persons that the file contains.
   */
  public List<Person> readInformation(String fileName) {
    List<Person> persons = new ArrayList<>();

    File f = new File(filePath + "/" + fileName);

    if (!f.exists()) {
      writeInformation(fileName, new ArrayList<>());
    }

    try {
      ObjectMapper ob = new ObjectMapper();
      persons = Arrays.asList(ob.readValue(Paths.get(f.toString()).toFile(), Person[].class));

    } catch (FileNotFoundException e) {
      e.printStackTrace();
    } catch (JsonParseException e) {
      e.printStackTrace();
    } catch (JsonMappingException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    }
    return persons;
  }

}
