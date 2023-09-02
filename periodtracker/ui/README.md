# UI MODULE

<ins> CalendarController

This is the controller for the calendar pane. This is the main part of the app. It controls the CalendarControlliner.fxml by having methods that tells each button what to do. In this part of the app, a user can set and remove symptoms on days that are not in the future. It is also possible to click left or right to view the past or future months. 

<ins>CreateUserController

This is the controller for the pane where the person can create a user. The name has to be valid with letters a-z, and not been used by another user. The age has to be between 10 and 70. 

<ins>LoginController

This is the controller for the login page. Here a user can log in with a name that is saved in the file. 

<ins>PeriodTrackerApp

This is the class for launching the app. 

<ins>StartController

This is the controller for the start page. Here the user can choose between log in and create a new user. 

<ins> RestModel

This is the class for connecting REST API and UI. This contains different HTTP requests. 

<ins>FXML files</ins>

We have separated the fxml files into 4, these are:

    Calendar-fxml

    CreateUser.fxml

    LogIn.fxml

    Start.fxml

Each fxml file has a controller as explained previously, that stands for the functionality of the pane. 

## TESTS
We have tests for each controller. Here we are testing that all the buttons are working, as well as logging in and creating users with different persons. 

<ins>CalendarControllerTest</ins>

Here we are testing that the calendar is working correctly. The things we are testing are setting symptoms, removing symptoms, setting symptoms on a future date, logging out and clicking in future/past months. 

<ins>CresteUserControllerTest</ins>

Here we are testing to create users with different inputs. The things we are testing are valid names and age, invalid age, numbers as name and other inputs that are not supposed to work. We are also testing the "Back" button. 

<ins>LoginControllerTest</ins>

Here we are testing to log in with different persons that are saved in the file, and persons that dont exist, and therefore not are supposed to work. We are also testing the "Back" button. 

<ins>StartControllerTest</ins>

Here we are testing that we can click on "Log in" and "Create user", and that we are coming to the right pane by doing that. 