package periodtracker.core;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Person class.
 */
public class Person {

	private String name;
	private int age;

	public static final CalendarLogic cl = new CalendarLogic();

	private List<String> validSymptoms = Arrays.asList(
								"Small-bleedings", 
								"Cramps", 
								"Tiredness",
								"Headache", 
								"Intense-bleedings",
								"Nausia",
								"Stress");

	private HashMap<String, List<String>> symptomMap = new HashMap<String, List<String>>();

	/**
	 * Constructor for Personclass that sets name, age and the persons symptoms.
	 * 
	 * @param name
	 * @param age
	 * @param symptomMap
	 */
	public Person(String name, int age, HashMap<String, List<String>> symptomMap) {
		setName(name);
		setAge(age);
		setSymptomMap(symptomMap);
	}

	/**
	 * Empty Constructor.
	 */
	public Person() {

	}

	/**
	 * Sets the persons's name Validates name and throws if the name is invalid.
	 * 
	 * @param name
	 */
	public void setName(String name) {
		if (!name.matches("[a-zA-Z]{2,}")) {
			throw new IllegalArgumentException(
					"The name can only contain letters from the alfabeth A-Z and only contain two names");
		}
		this.name = name;
	}

	/**
	 * Get's the persons name.
	 * 
	 * @return name
	 * 
	 */
	public String getName() {
		return this.name;
	}

	/**
	 * Sets the person's age. Validates age and throws if age is under 10 or over 90.
	 * 
	 * @param age
	 */
	public void setAge(int age) {
		if (age <= 10 || age >= 70) {
			throw new IllegalArgumentException("Age must be between 10 and 70!");
		}
		this.age = age;
	}


	/**
	 * Get's the persons age.
	 * 
	 * @return age
	 * 
	 */
		public int getAge() {
		return this.age;
	}


	/**
	 * ToString for the Person class.
	 */
	@Override
	public String toString() {
		for (Map.Entry<String, List<String>> entry : symptomMap.entrySet()) {
			LocalDate date = LocalDate.parse(entry.getKey());
			List<String> sympt = entry.getValue();
			System.out.println("Name: " + this.getName() + " \n Age: " + this.getAge()
					+ " \n This is your symptoms at " + date + ": " + sympt);
		}
		return "";
	}


	/**
	 * Method for adding symptoms to a person.
	 * 
	 * @param choosenDate, the date when the person had the symptoms.
	 * @param symptom, the symptom(s) that the person had at the given date.
	 */
	public void addSymptoms(LocalDate choosenDate, String symptom) {
		if (choosenDate == null || symptom == null) {
			return;
		}
		if (choosenDate.isAfter(cl.getDate())) {
			throw new IllegalArgumentException("You can not add future symptoms");
		}
		if (!validSymptoms.contains(symptom)) {
			return;
		}
		if (!symptomMap.keySet().contains(choosenDate.toString())) {
			List<String> symptoms = new ArrayList<>();
			symptoms.add(symptom);
			symptomMap.put(choosenDate.toString(), symptoms);

		} else if (!getDateSymptom(choosenDate).contains(symptom)) {
			symptomMap.get(choosenDate.toString()).add(symptom);
		}
	}

	/**
	 * Method for removing symptoms from a person.
	 * 
	 * @param date, the date you want to remove symptoms from.
	 * @param symptom, the symptom(s) that the person had at the given date.
	 */
	public void removeSymptoms(LocalDate date, String symptom) {
		ArrayList<String> symptomsList = new ArrayList<>(getDateSymptom(date));
		symptomsList.remove(symptom);
		symptomMap.put(date.toString(), symptomsList);

	}

	/**
	 * Method for finding the symptom at the given date.
	 * 
	 * @param date
	 * @return the symptoms at the given date.
	 */
	public List<String> getDateSymptom(LocalDate date) {
		if (symptomMap.get(date.toString()) == null) {
			return new ArrayList<>();
		}
		return new ArrayList<>(symptomMap.get(date.toString()));
	}

	public HashMap<String, List<String>> getSymptomMap() {
		return new HashMap<String, List<String>>(this.symptomMap);
	}

	/**
	 * Sets the persons symptomMap.
	 * 
	 * @param symptomMap
	 */
	public void setSymptomMap(HashMap<String, List<String>> symptomMap) {
		this.symptomMap = new HashMap<String, List<String>>(symptomMap);
	}

}
