# RELEASE 3

## RUNNING THE CODE


```bash
cd periodtracker
```

Start by installing the application:
```bash
mvn clean install
```

Start the springboot webserver
```bash
cd rest/
mvn spring-boot:run
```

Then run the application by opening another terminal:
```bash
cd periodtracker/ui
mvn javafx:run
```

For running exe-file
```bash
cd periodtracker/ui/
mvn javafx:jlink -f.fxui/pom.xml
mvn jpackage:jpackage -f ./fxui/pom.xml
```

## HOW THE APP WORKS
- You start the app and you get two choices:
    - "Log in"
    - "New user"
    ![Start-Page](/group14/docs/images/StartPage.png)


    - You can log in with an already existing user or create a new user.
- When you log in, you see a calendar where you can click on different dates. If you have added symptoms on date, it will be marked with a green V.
    

    ![Calendar-Page](/group14/docs/images/Calendar.png)
- You can add symptoms to the day you would like, as long as it is not a future date.

- This is what is looks like when you add symptoms
    ![AddSymptoms-Page](/group14/docs/images/AddSymptoms.png)


## WHAT WE HAVE DONE
- Added a symptom-map to each person. This consist of one LocalDate as string and a list with symptoms they have that particular day.
- Created the calendar page and added a function where you can add symptoms on each day, as long as they are not in the future.
- Changed filewriting, so now we write to a file outside the repository (user home directory).
- Divided the app into 4 controllers and 4 fxml files.
- Created a rest-module for the springboot webserver.
- Created a restModel in ui to connect REST-API and ui
- Fixed spotbugs.
- Created tests for all modules.
- Created a log out button, that logs out a person.
- The project is running in gitpod.
- Made the app into a shippable product by developing an exe file.


##  <strong> WORK HABITS </strong>

To ensure that we had a good workflow and co-operation, we implemented certain elements to our work habits. 

 <strong> <ins> Codetogether/Liveshare </strong>

 We work in pairs of two on the more demanding issues. We experience that this is the best option for us as we make a collaberative work, and get different contributions and ways of working. We have experienced that coding together leads us to fewer bugs and mistaks. 

 Often we use either Codetogether or Liveshare depending on what we are working on. This way we can code together and think of different solutions to problems. 

<strong> <ins> GIT </strong>

<strong> Issues </strong>

In our opinion we have gotten much better at using gitlab since the first release. This has to do with learning through experience, trying and failing. 

For each problem or improvement we we created an issue detailing what this issue was about. This issue contained an appropriate header, a description that informed the other members what the issue was about, an assigne, a milestone and a label. 

The different milestones we had were:
- Deliverable 1
- Deliverable 2
- Deliverable 3

The different labels we had were:
- P1 (short for priority 1)
- P2
- P3

These milestones and labels helped us prioritise which issues we should tackle first, and which could wait until later on. 

Further more, the issues did help every member of the team to be updated on which problems that should be dealt with and which ones that were solved. 

 <strong> Branches </strong>
 
For each issue we created a branch. This was so no unfinished changes were to be made in our main branch (master). These branches often had the same informative name as the issues, which made it easier for the group to see which issue to solve with each branch. If they wanted more information about the branch they can just read the description of the issue. 

<strong> Merge request </strong>
In the startup phase, we decided to merge a choosen branch to master through a merge request. In this merge request the assignee had to write a short despriction of what had been done. Then this assignee assigned a reviewer to see over this work. Since we often work in teams, a person on the other team is set as the reviewer. This reviewer looks over at what has been done, and leaves a comment about the work. If there are any questions, the reviewer will ask them either in the comments or to the person directly. Since we often sit together, the last mentioned is more regurarly used. If the reviewer agrees with the changes done in the branch, and it is working correctly on their computer, she will accept the merge request and the branch will be merged into master. 

<strong> <ins> Trello </strong>

In the start phase we stood between either using boards in gitlab or Trello. Since we already were very familiar with Trello, we chose to go with Trello. In Trello we had different lists that for example contained what needed to be done this week, or one specific work day. This helped us have a more detailed plan, so we felt more structured and motivated. 

<strong> <ins> Scrum meetings </strong>

Around two times a week we had a scrum meeting. We did not have daily meetings as there were days we did not work with the project, it therefor made more sense to have it twice a week. In the weeks before a deadline, we upped the weekly meeting to three times a week. 

At the start of every week we had a sprint planning where we planned what we wanted to do every day. This was written up in Trello (see picture below). We also created a sprint queue in Trello with issues that had to be done, but were not at the top prioritiy at that moment. This was also created in Trello.  

![TrelloPlan](/group14/docs/images/TrelloPlan.jpeg)

In the sprint meeting, every member told the group what they had done since the last meeting, and what their plans was for the next sprint. Here we had the opportunitie to ask for adivce for certain problems, and if we wished to have any help. The weekly meeting helped every member to be updated on the project as a whole. 

At the end of the week we had a sprint review where we told the group how the assignment went, reviewed the completed work and checked if there were any additional problems that had arisen from the sprint. This helped us feel more in control of our own work and work collabratively to create the best possible product. 


## CODE QUALITY

- Jacoco (jacoco-maven-plugin) 
    - We have jacoco for us to check the test coverage in our code. Jacoco has helped us see which part of the code we need to test more, and create more awareness for us around our own code.
- Spotbugs (spotbugs-maven-plugin)
    - We have implemented spotbugs that is another maven-plugin that helps us to analyze the code for regular bugs. This has assisted us with improving our code a lot.
- Checkstyle (checkstyle-maven-plugin)
    - We have implemented checkstyle that is another maven-plugin. This helps us makes sure we write good and readable code.
    - We are aware that there are some checkstyle warnings. We did not find a good solution to remove these. We acknowledge that minor issues regarding this such as javadoc spacing. 
    - We know that we have a lot of checkstyle warnings, but we chose to ignore this because we wanted to prioritize other things that we think is more important for the functionality of the app.

### Other things: 
- Javadoc
    - We have javadoc on all of our methods, mostly for the person who will look through the code. It is important that they understand the idea behind our classes and the methods that is used.

## ARCHITECHTURE

Our project is divided into four different modules that all cooperates in some way. A visual representation of this is seen in the package diagram. The modules are core (core logic), rest (for the rest api), storage (for saving and storing the data) and ui (user interface).

<strong> CORE </strong>

- Calendarlogic
    - This is used to get the date objects.
- Person
    - The person object that has a name, age and a hashmap that contains the date they got symptoms and the symptoms at the given date. 

<strong> REST </strong>

The rest module uses the Springboot framework for setting up an API. The classes in this module is used for the cooperation between the application and the webserver. 

- RestAPIController
    -  The controller class for the springboot webserver

- PersonService
    - The service for the springboot webserver
    - This class contains methods for get, delete and post. The controller uses this class.
   
- RestApplication
    -  The application class for the springboot webserver

<ins> These are the valid requests for our rest API: 

 - GET requests

        - /getPerson/{name}
        - /getPersons
        - /getSymptoms/{name}
        - /getSymptomsOnDay/{name}{date}

- POST requests

        - /addPerson/{name}/{age}
        - /addSymptoms/{name}/{date}/{symptom}

- DELETE requests

        - /removeSymptom/{name}/{date}/{symptom}
    

<strong> STORAGE </strong>

The storage module is necessary for the storing of data from our app. Without this module, none of the data would be saved and stored.
- FileHandling
    - This class contains methods for writing and reading from file. The file is now stored locally on the users computer. 
- PersonManager
    - This class contains multiple person objects. Among many things this class has control over which persons that is created, creates new persons and keeps track over their symptoms. In another way: PersonManager has control over all the Persons.


<strong> UI </strong>

The ui module is the module that runs the app. We have 4 controllers and 4 fxml files. 
The controllers is the classes that tells the fxml what to do on each button. 
We have named the controllers after what page they belongs to. The UI is connected to the REST API through the RestModel.

- CalendarController
    - Controller for the calendar page. This controls the page with the symptoms calendar, where we can add and remove symptoms and view wich days have symptoms. 
- CreateUserController
    - Controller for the create user page. Here we can create new users. When the user is created with correct name and age, the app goes into the calendar.
- LoginController
    - Controller for logging in a person. When the person is logged in, the app goes to the calendar. 
- PeriodTrackerApp
    - Application class for the app to run.
- StartController
    - Controller for the start page. Here the user can choose between logging in or creating a new person. 
- RestModel
    - This is the class connects REST API and ui by calling on the different HTTP requests. 
