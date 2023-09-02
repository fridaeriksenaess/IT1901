# STORAGE MODULE

The storage module is used for storing data. It is here we have the methods for filewriting and reading. 

<ins> FileHandling

FileHandling is the class that contains the logic for writing and reading from file. Difference from release 2 og 3 is that we now store the files locally on the users computer. We write and read to json files through Jackson library. 

We have a constructure that seperates test and non-test files into different folders. 

<ins> FileHandlingInterface

This is a very small interface that only contains read and write methods. 
For now, we only have one class (FileHandling) that is used for filewriting, but if we would want to expand the app in the future we could have used this interface to create several classes for filewriting.

<ins> PersonManager

PersonManager keeps track over the persons in the file. It is in here that a new person is added, removed or checks if the person already have a user so she can be logged in. 

Other functions is to get the activePerson, which is the user currently logged in, and calls on the Person class for adding, removing and getting the symptom map. 

All these changes are then updated to the file. 


<strong> JSON format </strong>

```json
[ {
  "name" : "PersonOne",
  "age" : 20,
  "loggedIn" : false,
  "symptomMap" : {
    "2022-11-09" : [ "Cramps", "Nausia" ],
    "2022-11-03" : [ "Cramps", "Tiredness" ]
  }
}, {
  "name" : "PersonTwo",
  "age" : 40,
  "loggedIn" : true,
  "symptomMap" : {
    "2022-11-10" : [ "Tiredness", "Headache" ]
  }
} ]
```

Above there is an example of how our json formatting looks when you create a person and save the person to a file.

<strong> Test Coverage </strong>

We have two test classes for storage which test FileHandling and PersonManager. 

<ins> FileHandling

As you can see from the outcast of jacoco, FileHandling has a reasonably amount of testing. 

The minus factor in our testing is that we do not test the all exceptions. In regards to FileNotFoundException we had an issue here because when it comes to the file, it is read before it is written to. This is good when it comes to the logic of the app, but causes a problem the first time a person writes something to the file. We chose to solve this by adding a small fixture in the readInformation method. 

Else we have tested that the main functions of this class works as it should. 

![Test-Coverage-For-FileHandling](/docs/images/TestCoverageFileHandling.jpeg)

<ins> PersonManager

Attached in the outcast of jacoco, you can see that PersonManager has good testcoverage. 

We have tested that the methods in personManager is working correctly by testing that everything works, an also testing that things that are nit supposed to work not are working. 

We have chosen to not focus on things that are already tested in the person test. Although we have tested some of it, it does not show in the outcast, since it gets delegated to other classes. 

![Test-Coverage-For-PersonManager](/docs/images/TestCoveragePersonManager.jpeg)
