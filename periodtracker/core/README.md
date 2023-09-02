# CORE MODULE

The core module contains two classes, and contains logic. These two classes are: 

<ins> Person 

The Person class contains the logic for one person. One person has a name, age and a hashmap containing a date which is a string and the list of symptoms that day. The class contains different getters and setters for the variables. It also contains methods for removing and adding symptoms on specific days. 


<ins> CalendarLogic

The CalendarLogic class is a small class that only contains three methods that are only getters. The getters are used for getting todays date, year and month. 

To see how these classes interacts with other classes, see the classdiagram in docs/release3. 

## Test coverage

While we have two classes, we only have one test. This is because we deemed it unecessary to test the CalenderLogic class because this class only uses built-in methods from Java. 

![Test-Coverage-CalendarLogic](/docs/images/TestCoverageCoreCalendarLogic.png)

The Person class is well tested, and we test every method as seen in the picture below. Only method that is not well tested is the toString(). Reason why we chose not to test the toString is because it is only relevant for the terminal and not the functionality of the app. 

![Test-Coverage-Person](/docs/images/TestCoverageCorePerson.png)

