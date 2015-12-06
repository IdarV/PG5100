# PG5100 - EE project

## Info
Github: https://github.com/IdarV/PG5100 - GitHub user @jarlehansen has access

# Task 3
## Setup
1) Install wildfly and run ```./standalone.sh```<br/>
2) Open project in root and run ```mvn clean package``` <br/>
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

---
### 1.1a)
Java Enterprise Edition is Java's set of building blocks for creating enterprise applications. It offers all the functionality that is needed to create an enterprise application, aswell as being a superset of JavaSE which means it has all of the familiar basic functionality you find there. This makes it a robust environment as web applications, business logic and persistence is all handeled by the same system that uses the same language. JavaEE basically offers <i>services</i> that can be used by containers. WildFly works as a web container

### 1.1b)
An example of situations where I'd want to use JavaEE rather than JavaSE is where the user interacts with a database through one or more different GUI's and view's. A prime example of this is a company's internal enterprise application that handles customers, employees, development cycles, reports, files, information, profiles and more. Instead of having multiple different programs to do this, JavaEE allows building it all as one application. Because of the portability of JavaEE, it's easy to change implementations in the future without rewriting business logic. If, for example, I'd have to change the database from an internal SQLite to and external MySQL, this can all be done with a few changes in the configuration, and the implementation itself needs minimal to no rewriting. This means I'd spend minimal time rewriting logic I have already implemented.

### 1.2)
<i>Inversion of control</i> is a concept of letting the container take control of a specified object in the business code and provide the technical services. Dependency Injection is a design pattern that implements inversion control for resolving dependencies. This means that instead of an object looking up and creating, handling, managing and deleting a dependency, the container will inject and manage those objects for you. In other words, <i>"I want this object , provide it for me."</i> The reason for doing it this way, is the low coupling it gives.

An example of this is the injection of a ```EntityManager```, where we use the annotation ```@PersistenceContext``` to get an EntityManager based on the persistence-unit configured in ```persistence.xml```. The container will take care of the instantiation based on that configuration, and manage the object.


### 1.3a)
A stateful EJB has a conversational state with the user, and is retained over time based on what request scope it has. The instance variables of the bean is relative to the client, and method invocation will always be invoked on the same bean, with the same context. This means the bean can have different states. An example of this is the shopping cart of a website, where you want to contain the context and state of the bean even if the user decides to close the website. You also want to keep the instance variables under the different states of payment and confirmations. The bean is destroyed once the request scope is over.
<br/>
Examples of annotations for a stateful bean is
- ```@ManagedBean``` Creates a stateful bean
- ```@SessionScoped``` Sets the session scope to match the HttpSession


### 1.3b)
A stateless EJB has no conversational state with the user, so there's no guarantee the client will get the same instance. The same EJB context is shared across all users. For example, when listing pictures on an online photo site, there is no need to have a conversational state with the user, simply because there is no need for it.
<br/>
Examples of annotation for a stateless bean is
- ```@Stateless``` Creates a stateless bean
- ```@RequestScoped``` Sets the session scope to

### 1.4)
Each layers in an application has different data constraints, and always needs to ensure they are handling valid data. If you are to implement validation logic in each layer, it is most likely going to result in hard-to-read and redundant code with validations all over the place. Bean validation is a solution to this issue, by allowing a validation to be written once, and then validated in every layer. There exists default validators, like ```@NotNull``` and ```@Size(min=6)```, that really does what they read. You can write your own validators in plain Java, for example validating that a object has a valid enum property. Once an object is passed to another layer in the application, you can validate it with a simple ```@Valid``` to run all validators.

Validators also has a error message property that will be thrown.
An example of setting a new message is  ```@Size(min=6, message="the size of this object must be at least 6 characters long")``` . If you are validating a form in a web page, given that you have defined a place to print error messages, this will be printed there, and the form will not be submitted. Keep in mind that the validation still is only written on the object itself, but passes on through the layers.



<br/>
<br/>
<br/>
<br/>
<br/>
<br/>
<br/>
<br/>
<br/>
<br/>
<br/>




The underlying layers of the application is ran in containes, which are their own runtime environments that offer implementations of the underlying JavaEE components. The containers follows a contract to deliver an implementation of a given functionality, and offer services for the programmer to tell it how to implement it. The containers have low coupling to the application itself, and are unusally only implemented though annotation and/or configuration.

 The main point of building an application like this is changing sut...

Java ee tilbyr et ukomplisert robust miljø (suite of APIs / set of specifications implemented by different containers) for enterprise applikasjoner.

Containers are Java EE runtime environments that provide services such as life-cycle management, dependency injection. JavaEE is also a superset of JavaSE, which means JavSE's API can be used by any JavaEE component.


## Sources
- Dependency Injection:
  - http://stackoverflow.com/questions/130794/what-is-dependency-injection
- Statefull/Stateless:
  - http://www.tutorialspoint.com/ejb/ejb_stateless_beans.htm
  - http://stackoverflow.com/questions/2351220/stateless-and-stateful-enterprise-java-beans
- Beginning Java EE 7 (2013) chapter 1 - 4.
