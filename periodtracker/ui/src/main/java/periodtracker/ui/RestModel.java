package periodtracker.ui;

import java.net.URI;
import java.net.http.HttpRequest;
import java.net.http.HttpRequest.BodyPublishers;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.core.JsonParser.Feature;
import com.fasterxml.jackson.databind.ObjectMapper;
import periodtracker.core.Person;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpResponse;

/**
 * This RestModel class is what is connecting ui to the rest module.
 * All the methods in this class are using Http-requests.
 */
public class RestModel {
  
  private String uri = "http://localhost:8080";
  
  public RestModel() {
    
  }
  
  /**
   * Method for adding the person by their name and age.
   * @param name
   * @param age
   * @return
   */
  public String addPerson(String name, int age) {
    String result = "";
    try {
      HttpRequest request = HttpRequest.newBuilder()
          .uri(new URI(uri + "/addPerson/" + name + "/" + age))
          .POST(BodyPublishers.ofString(result))
          .build();
      final HttpResponse<String> res = HttpClient.newBuilder().build().send(request,
          HttpResponse.BodyHandlers.ofString());
      result = res.body();
    } catch (Exception e) {
      System.out.println("Could not add this person.");
    }
    return result;
  }

  
  /**
   * Method for getting the person by their name.
   * @param name
   * @return
   */
  public Person getPerson(String name) {
    String result = "";
    Person person = new Person();
    try {
      HttpRequest request = HttpRequest.newBuilder()
          .GET()
          .uri(new URI(uri + "/getPerson/" + name))
          .build();
      final HttpResponse<String> res = HttpClient.newBuilder().build().send(request,
          HttpResponse.BodyHandlers.ofString());
      result = res.body();
      ObjectMapper ob = new ObjectMapper();
      person = ob.readValue(result, Person.class);
    } catch (InterruptedException | IOException | URISyntaxException e) {
      System.out.println("Could not get this person, are you sure you have a user?");
    }
    return person;

  }

  /**
   * Method for getting all the persons that are saved.
   * @return
   */
  public List<String> getPersons() {
    List<String> persons = new ArrayList<>();
    String result = "";
    try {
      HttpRequest request = HttpRequest.newBuilder()
          .GET()
          .uri(new URI(uri + "/getPersons"))
          .build();
      final HttpResponse<String> res = HttpClient.newBuilder().build().send(request,
          HttpResponse.BodyHandlers.ofString());
      result = res.body();

      ObjectMapper ob = new ObjectMapper();
      ob.configure(Feature.AUTO_CLOSE_SOURCE, true);
      persons = ob.readValue(result, ob.getTypeFactory().constructCollectionType(List.class, Person.class));
    } catch (InterruptedException | IOException | URISyntaxException e) {
      System.out.println("Could not get persons");
    }
    return persons;

  }

  /**
   * Method for getting a persons symptoms
   * @param name
   * @return
   */
  public String getSymptoms(String name) {
    String result = "";
    try {
      HttpRequest request = HttpRequest.newBuilder()
          .GET()
          .uri(new URI(uri + "/getSymptoms/" + name))
          .build();
      final HttpResponse<String> response = HttpClient.newBuilder()
          .build().send(request, HttpResponse.BodyHandlers.ofString());
      result = response.body();
    } catch (InterruptedException | IOException | URISyntaxException e) {
      System.out.println("Could not get symptoms.");
    }
    return result;
  }

  /**
   * Method for adding symptoms to a person.
   * @param name
   * @param date
   * @param symptom
   * @return
   */
  public String addSymptoms(String name, String date, String symptom) {

    String result = getSymptomsOnDay(name, date) + "";
    try {
      HttpRequest request = HttpRequest.newBuilder()
          .GET()
          .uri(new URI(uri + "/addSymptoms/" + name + "/" + date + "/" + symptom))
          .POST(BodyPublishers.ofString(result))
          .build();
      final HttpResponse<String> response = HttpClient.newBuilder().build().send(request,
          HttpResponse.BodyHandlers.ofString());
      result = response.body();
    } catch (InterruptedException | IOException | URISyntaxException e) {
      System.out.println("Could not add symptom");
    }
    return result;

  }

  /**
   * Method for removing a persons symptoms.
   * @param name
   * @param date
   * @param symptom
   * @return
   */
  public String removeSymptoms(String name, String date, String symptom) {

    String result =  getSymptomsOnDay(name, date) + "";
    try {
      HttpRequest request = HttpRequest.newBuilder()
          .DELETE()
          .uri(new URI(uri + "/removeSymptom/" + name + "/" + date + "/" + symptom))
          .build();

      final HttpResponse<String> response = HttpClient.newBuilder().build().send(request,
          HttpResponse.BodyHandlers.ofString());
      result = response.body();
    } catch (InterruptedException | IOException | URISyntaxException e) {
      System.out.println("Could not remove symptoms");
    }
    return result;

  }

  /**
   * Method for getting the symptoms on a given date.
   * @param name
   * @param date
   * @return
   */
  public List<String> getSymptomsOnDay(String name, String date) {
    
    List<String> result = new ArrayList<>();
    
    try {
      HttpRequest request = HttpRequest.newBuilder()
          .GET()
          .uri(new URI(uri + "/getSymptomsOnDay/" + name + "/" + date))
          .build();

      final HttpResponse<String> response = HttpClient.newBuilder().build().send(request,
          HttpResponse.BodyHandlers.ofString());
      result.add(response.body());
      // S 
    } catch (InterruptedException | IOException | URISyntaxException e) {
      System.out.println("Could not get the symptoms on this day.");
    }

    return result;

  }


}
