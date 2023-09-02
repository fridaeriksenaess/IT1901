# Periodtracker 💅🏽

[![Gitpod Ready-to-Code](https://img.shields.io/badge/Gitpod-Ready--to--Code-blue?logo=gitpod)](https://gitpod.stud.ntnu.no/#https://gitlab.stud.idi.ntnu.no/it1901/groups-2022/gr2214/gr2214)

## Description
The periodtracker app is a app where you can log your period along with your symptoms. The app is meant to be an easy way to track your cycle and see a correlation between symptoms and your cycle.

## Documentation
Our documentation for each release is found inside the docs folder. The complete documentation is found in the folder release3. We also have a readme inside every module that explains the module and the tests.


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



## Developers

- Elin Bjerve
- Frida Eriksen Næss
- Johanna Wilmers
- Martine Nilsen
