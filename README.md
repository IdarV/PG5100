# PG5100 - EE project

### Setup
1) Install wildfly and run ```./standalone.sh```<br/>
2) Open project in root and run ```mvn wildfly:deploy```

### Code
##### User controller
The user controller is different from the rest of the controllers, and features CRUD functionality on the same site. This came to be because of a bug which would not let me add multiple buttons (delete buttons) on the forms. After trial and error, the only way I found to fix this was to go from @Model to @ManagedBean which forced me to
### Project
Though the exam requested create and read functionality, I added update and delete aswell. I mainly focused on trying to code the while application in JSF, and though it was hard, I feel like I succeeded.

### Sources
- Bootstrap theme: http://bootswatch.com/simplex/
- Bootsfaces JSF Framework: http://bootsfaces.net/index.jsf
- UserController inspired by: http://www.tutorialspoint.com/jsf/jsf_managed_beans.htm


## TODO
Course name minimum 1 or smth
Building in Location
