# REST MODULE

This is the module we use for our webserver application.
The API uses the Spring Boot framework to create a web server with a REST API.

<ins> PersonService

PersonService is the Serviceclass for our RestApplication. PersonService interacts with PersonManager by using this class. This interaction is whats combines the rest module with storage and ui. All the controllers in ui uses PersonService in their methods.


<ins> RestAPIController

RestAPIController is the controller class for our springboot webserver. This class contains methods so we can get, post, put and delete. The methods we use are:


- @GetMapping
	- "/getPerson/{name}"
	- "/getPersons"
	- "/getSymptoms/{name}"
	- "/getSymptomsOnDay/{name}/{date}"

- @PostMapping
	- "/addPerson/{name}/{age}"
	- "/addSymptoms/{name}/{date}/{symptom}"
	
- @DeleteMapping
	- "/removeSymptom/{name}/{date}/{symptom}"

<ins> RestApplication

The RestApplication is the Application class for our springboot webserver.


## Tests
When we run the tests for the rest module, we write to users.json. Ideally we want to separate the tests and when we run the app normally, but this just did not work for us. When the tests worked perfectly when we wrote to users.json we decided to prioritize other important things for the functionality of the app.


## Test coverage
When we run the test for our springboot webserver. When we run the app, the port used is always 8080, and the tests use a random port each time.

This is our test coverage in the entire rest module:

![TestCoverageRest](/docs/images/TestCoverageRest.png)


### PersonService
![TestCoverageRest-PersonService](/docs/images/TestCoverageRestPersonService.png)


### RestAPIController
![TestCoverageRest-RestAPIController](/docs/images/TestCoverageRestAPIController.png)


### RestApplication
![TestCoverageRest-RestApplication](/docs/images/TestCoverageRestApplication.png)
