# RELEASE 2

## WHY MVN JAVAFX:RUN DOES NOT WORK IN GITPOD

We have tried to get help from student assistants and technical help, 
but no one knew how to fix the problem. 
Filehandling and fileHandlingTest 
are not cooperating (when one of the classes work, the other class don’t) 
because it is something wrong with the path. Therefore we have commented 
out the ReadInformationTest() in FileHandlingTest. Mvn test is only running 
when readInfirmation() is commented out. We know that this is not a complete project, 
but we choose to fix this problem to the next deliverable, because changing our 
whole project now takes too much time for this deliverable.

This is the error we are getting when we try to run in gitpod: 
Failed to execute goal on project ui: Could not resolve dependencies for project it1901:ui:jar:0.0.1-SNAPSHOT: Could not find artifact it1901:core:jar:0.0.1-SNAPSHOT -> [Help 1]

mvn compile and mvn test works in gitpod.

For deliverable 3, we will save to a file that is stored locally on the computer that runs the program. We think that maybe this can solve our problems with filehandling.


## WHAT WE HAVE DONE

In this release our focus has been on the projects architecture and build up.

- Divided the projects into different modules (core and ui). The core module has most of the app`s logic, while ui contains the App, Controller, FXML and the json files.
- More testing with the implementation of checkstyle, spotbugs and jacoco.
- Added testing to filewriting and UI-testing
- Improved the filewriting and reading to using a Jackson library
- The project is still running in git pod
- The project is still built up on Maven


## WORK HABITS

In regards to our work habits, team work has been essensial from the start. We have atleast two meetings a week, often more the closer we get to the deadline. In these meetings we talk about what we have done since the last meeting so everyone keeps up with the changes. In the meetings we often use codetogether and liveshare so everyone is involved. 

Often we work two and twop people on the same issue either using liveshare or codetogether. If we had any problems, we would ask the other pair either in the meetings or sent them a message. 

Our git site has improved significantly from the start. Now we are using milestones, deadlines and issues more and in a better way. We have better communication regarding branches so most of them dont contain number, and we avoid merge conflicts. We chose to not use issue number in our issues and branches, to have structure and consistency on gitlab, although we have forgotten it on a few branches. 

 To be able to merge with master, the assignee must request a merge request in which another person on the group must approve. For a person to approve the request, the person must read through the changes and check if the code is good. If there are any doubts the person will ask the assignee. If we have worked in pair, the merge request will be sent to one in the other pair. This gives the group a more understanding on what has been done. 


## CODE QUALITY

In this release we divided the project into different modules. This was a lot of work, and we ran into some issues which took alot of out time. We have therefore not added much functionality to the app in this release. 

We have implemented spotbugs, checkstyle and jacoco. This gave a more clearer outview of the parts of the code which must be improved. Also in regards to test, we have created filehandlingtest and UI-test to improve our testcoverage. 

## ARCHITECHTURE
We chose a class digram, because it is a clear and structured way to visualize how all the classes are working together, and who are toed together. This is relevant for our project, because there are so many classes that are forced to work together. (The class diagram is in the file architecture.md)
