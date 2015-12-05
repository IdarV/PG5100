# PG5100 - EE project

### Setup
1) Install wildfly and run ```./standalone.sh```<br/>
2) Open project in root and run ```mvn wildfly:deploy```

### Code
##### User controller
The user controller is different from the rest of the controllers, and features CRUD functionality on the same site. This came to be because of a bug which would not let me add multiple buttons (delete buttons) on the forms. After trial and error, the only way I found to fix this was to go from @Model to @ManagedBean which forced me to

##### Course and Location controller
These are the simplest of the controllers, and ...

##### Event controller
In event I chose to use the Date class because its compatible with the H2 database, though it's basically depricated. I should have used a new date class, like LocalDateTime that was introduces in Java 8, but due to time constraints I had to make a workaroud. It's not pretty to convert a date to Calendar, set it's hour and minutes and parse it back to Date again like I've done, but I had a development freeze that I had to

### Project
Users are a bit different from the rest of the application. I wanted to make deleting entities work, and didn't really give up until I succeeded. I like to think of this view as a teacher-view, where they can add students to courses, add manage their students while the administration could manage the rest.

### Sources
- Bootstrap theme: http://bootswatch.com/simplex/
- Bootsfaces JSF Framework: http://bootsfaces.net/index.jsf
- UserController inspired by: http://www.tutorialspoint.com/jsf/jsf_managed_beans.htm
- Password regex: http://regexr.com/39mdu


## TODO
Course name minimum 1 or smth
Building in Location  
