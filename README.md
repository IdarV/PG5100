# PG5100 - EE project

## Info
Github: https://github.com/IdarV/PG5100 - GitHub user @jarlehansen has access

# Task 3
## Setup
1) Install wildfly and run ```./standalone.sh```<br/>
2) Open project in root and run ```mvn clean install``` <br/>
3) In root, run ```mvn wildfly:deploy```

## Code
The program works in three different levels; views, entities and controllers. When entering a view, the view will communicate with it's respective controller. That controller will communicate with the database through an entity. It also includes tests to ensure that functionality is working as intended through the development.

##### Views
The views are ```jsf``` with ```xhtml``` markup which mainly uses Bootsfaces' framework.

##### Controllers
The controllers acts as layer between the views and persistence layer. It contains ```entities``` that is used for communication with the database, and the state of the current session if any. In my program, all are stateless but the User controller. The UserController is annotated as a ```@ManagedBean``` rather than a ```@Model``` like the rest of them are, because we want to contain it's the session. This allows CRUD operations on the same page, because the session is intact while the user are interacting with that page.

##### Entities
The entities contains most of the business logic, and it's main purpose is to be a communication layer with the database, and uses an ```Entitymanager``` to communicate with a persistence context. The entitymanager is created fom an ```EntitymanagerFactory```, which again is created based on the respective persistence-unit. In example that means it can use a different persistence-unit in the tests.

### Project
Users are a bit different from the rest of the application. I wanted to make deleting entities work, and didn't really give up until I succeeded. I like to think of this view as a teacher-view, where they can add students to courses, add manage their students while the administration could manage the rest.

### Sources
- Bootstrap theme: http://bootswatch.com/simplex/
- Bootsfaces JSF Framework: http://bootsfaces.net/index.jsf
- UserController inspired by: http://www.tutorialspoint.com/jsf/jsf_managed_beans.htm
- Password validation regex: http://regexr.com/39mdu


## TODO
Course name minimum 1 or smth
Building in Location  
